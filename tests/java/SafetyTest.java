import rea.security.Safety;
import rea.security.ControlReader;
import java.io.*;
import java.util.concurrent.CountDownLatch;

public final class SafetyTest {
    static int cases;
    static void check(boolean good) { cases++; if (!good) throw new AssertionError("case " + cases); }
    public static void main(String[] args) throws Exception {
        check(Safety.isInside("/a/root/file", "/a/root"));
        check(!Safety.isInside("/a/root-other/file", "/a/root"));
        check(Safety.isInside("/a/root", "/a/root"));
        check(Safety.isInside("/a/file", "/"));
        check(Safety.isInside("/a/root/file", "/a/root/"));
        check(!Safety.isInside(null, "/a")); check(!Safety.isInside("/a", ""));
        check(!Safety.isInside("/other", "/a"));
        check(!Safety.isInside("/a/root-other", "/a/root/"));
        check(Safety.awaitMetadata(new CountDownLatch(0)));
        long start = System.nanoTime();
        check(!Safety.awaitMetadataFor(new CountDownLatch(1), 10));
        check((System.nanoTime() - start) / 1000000 < 5000);
        Thread.currentThread().interrupt();
        check(!Safety.awaitMetadata(new CountDownLatch(1)));
        check(Thread.currentThread().isInterrupted()); Thread.interrupted();
        for (String text : new String[] {"", "\n", "\r", "\r\n", "a\rb\r\nc\nlast", "a\r\nb\r\n", "a\r\r", "é中文🙂\nnext", "a\n\n"}) {
            BufferedReader expected = new BufferedReader(new StringReader(text));
            ControlReader actual = new ControlReader(new StringReader(text), 2);
            String a, b;
            do { a = expected.readLine(); b = actual.readLine(); check(a == null ? b == null : a.equals(b)); } while (a != null);
            actual.close(); expected.close();
        }
        StringBuilder longLine = new StringBuilder();
        for (int i = 0; i < 8192; i++) longLine.append('x');
        try (ControlReader r = new ControlReader(new StringReader(longLine + "\r\nNOOP\r\n"), 128)) {
            check(r.readLine().length() == 8192); check("NOOP".equals(r.readLine())); check(r.readLine() == null);
        }
        try (ControlReader r = new ControlReader(new StringReader(longLine + "x\n"), 128)) {
            try { r.readLine(); throw new AssertionError("oversized line accepted"); }
            catch (IOException expected) { check(!expected.getMessage().contains("xxx")); }
        }
        try (ControlReader r = new ControlReader(new StringReader(longLine.toString()), 128)) { check(r.readLine().length() == 8192); }
        System.out.println("PASS " + cases + " safety cases");
    }
}
