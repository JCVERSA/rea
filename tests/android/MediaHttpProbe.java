package rea.test;

import android.content.Context;
import android.content.ContextWrapper;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import org.json.JSONObject;

/** Disposable-emulator probe of the actual APK classes, NOT a full app/media test. */
public final class MediaHttpProbe {
    public static final class TestContext extends ContextWrapper {
        public TestContext() { super(null); }
        @Override public Context getApplicationContext() { return this; }
    }

    private static String http(String host, int port) throws Exception {
        try (Socket client = new Socket()) {
            // Both destinations belong to this guest. A loopback source avoids reverse DNS
            // inside NanoHTTPD when testing the guest NIC destination.
            client.bind(new InetSocketAddress("127.0.0.1", 0));
            client.connect(new InetSocketAddress(host, port), 2000);
            client.setSoTimeout(3000);
            OutputStream out = client.getOutputStream();
            out.write(("GET / HTTP/1.1\r\nHost: " + host + "\r\nConnection: close\r\n\r\n").getBytes("US-ASCII"));
            out.flush();
            return new BufferedReader(new InputStreamReader(client.getInputStream(), "US-ASCII")).readLine();
        }
    }

    private static String selfIpv4() throws Exception {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface n = interfaces.nextElement();
            if (!n.isUp() || n.isLoopback()) continue;
            Enumeration<InetAddress> addresses = n.getInetAddresses();
            while (addresses.hasMoreElements()) {
                InetAddress a = addresses.nextElement();
                if (a instanceof Inet4Address && !a.isLoopbackAddress() && !a.isAnyLocalAddress())
                    return a.getHostAddress();
            }
        }
        throw new IllegalStateException("No guest-owned non-loopback IPv4 address");
    }

    public static void main(String[] args) throws Exception {
        boolean patched = args.length == 1 && args[0].equals("patched");
        if (!patched && !(args.length == 1 && args[0].equals("original")))
            throw new IllegalArgumentException("Expected original or patched");
        System.setProperty("java.io.tmpdir", "/data/local/tmp/rea-http-probe");
        Class<?> managerClass = Class.forName("com.alphainventor.filemanager.service.b");
        Constructor<?> managerConstructor = managerClass.getDeclaredConstructor(Context.class);
        managerConstructor.setAccessible(true);
        Object manager = managerConstructor.newInstance(new TestContext());
        Class<?> base = Class.forName("ax.Ba.a");
        Class<?> media = Class.forName("ax.X1.b");
        Object server = media.getConstructor(managerClass, int.class).newInstance(manager, 0);
        JSONObject result = new JSONObject();
        boolean passed = false;
        try {
            // Only this media constructor is under test; port 0 uses a disposable port.
            base.getMethod("u", int.class, boolean.class).invoke(server, 3000, true);
            Field socketField = base.getDeclaredField("c");
            socketField.setAccessible(true);
            ServerSocket socket = (ServerSocket) socketField.get(server);
            String bound = socket.getInetAddress().getHostAddress();
            int port = socket.getLocalPort();
            String loopbackStatus = http("127.0.0.1", port);
            String ownIp = selfIpv4();
            String nonLoopbackStatus = null;
            String nonLoopbackError = null;
            try { nonLoopbackStatus = http(ownIp, port); }
            catch (java.io.IOException e) { nonLoopbackError = e.getClass().getSimpleName(); }
            boolean localOk = loopbackStatus != null && loopbackStatus.contains("404");
            boolean ownNicOk = nonLoopbackStatus != null && nonLoopbackStatus.contains("404");
            passed = localOk && (patched ? (bound.equals("127.0.0.1") && !ownNicOk && nonLoopbackError != null)
                                        : (socket.getInetAddress().isAnyLocalAddress() && ownNicOk));
            result.put("variant", args[0]);
            result.put("bind_address", bound);
            result.put("port", port);
            result.put("loopback_http_status", loopbackStatus);
            result.put("guest_own_ipv4", ownIp);
            result.put("guest_non_loopback_http_status", nonLoopbackStatus == null ? JSONObject.NULL : nonLoopbackStatus);
            result.put("guest_non_loopback_error", nonLoopbackError == null ? JSONObject.NULL : nonLoopbackError);
            result.put("passed", passed);
        } finally {
            base.getMethod("v").invoke(server);
        }
        System.out.println("REA_HTTP_RESULT=" + result.toString());
        // app_process only; exits even if library executors leave an idle thread.
        System.exit(passed ? 0 : 1);
    }
}
