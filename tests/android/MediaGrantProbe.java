package rea.test;

import android.content.Context;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;
import org.json.JSONObject;

/** Real manager a()/j() calls on synthetic URI grants; no user files/backends. */
public final class MediaGrantProbe {
    private static int count;
    private static void check(Object manager, Method matcher, String uri, boolean expected) throws Exception {
        boolean actual = (Boolean) matcher.invoke(manager, uri);
        if (actual != expected) throw new AssertionError("Manager policy case failed: " + count);
        count++;
    }
    public static void main(String[] args) throws Exception {
        boolean patched = args.length == 1 && args[0].equals("patched");
        if (!patched && !(args.length == 1 && args[0].equals("original"))) throw new IllegalArgumentException();
        Class<?> type = Class.forName("com.alphainventor.filemanager.service.b");
        Constructor<?> constructor = type.getDeclaredConstructor(Context.class);
        constructor.setAccessible(true);
        Object manager = constructor.newInstance(new MediaHttpProbe.TestContext());
        Method matcher = type.getMethod("j", String.class);
        Field field = type.getDeclaredField("f");
        field.setAccessible(true);
        @SuppressWarnings("unchecked") Set<String> grants = (Set<String>) field.get(manager);
        type.getMethod("a", String.class).invoke(manager, "local://0/storage/html/index.html");
        if (patched && !grants.contains("D:local://0/storage/html")) throw new AssertionError("Directory not typed");
        check(manager, matcher, "local://0/storage/html/index.html", true);
        check(manager, matcher, "local://0/storage/html/css/site.css", true);
        check(manager, matcher, "local://0/storage/html-private/secret", !patched);
        check(manager, matcher, "local://0/storage/html.private/secret", !patched);
        check(manager, matcher, "local://0/storage/html/../private", !patched);
        check(manager, matcher, "local://0/storage/html/%2e%2e/private", !patched);
        grants.clear();
        if (patched) {
            Class<?> policy = Class.forName("rea.security.MediaGrant");
            grants.add((String) policy.getMethod("media", String.class).invoke(null, "local://0/storage/movies/clip.mp4"));
            // Shared policy suite uses the actual class from this APK, not a probe copy.
            Class.forName("MediaGrantTest").getMethod("main", String[].class).invoke(null, (Object) new String[0]);
        } else {
            grants.add("local://0/storage/movies/clip");
        }
        check(manager, matcher, "local://0/storage/movies/clip.mp4", true);
        check(manager, matcher, "local://0/storage/movies/clip.en.srt", true);
        check(manager, matcher, "local://0/storage/movies/clip.vtt", true);
        check(manager, matcher, "local://0/storage/movies/clip-private.mp4", !patched);
        check(manager, matcher, "local://0/storage/movies/clip.private.mp4", !patched);
        check(manager, matcher, "local://0/storage/movies/clip.pem", !patched);
        check(manager, matcher, "local://0/storage/movies/clip/sub.srt", !patched);
        check(manager, matcher, "local://1/storage/movies/clip.mp4", false);
        check(manager, matcher, "smb://0/storage/movies/clip.mp4", false);
        JSONObject result = new JSONObject();
        result.put("variant", args[0]);
        result.put("manager_cases", count);
        result.put("shared_policy_suite_executed", patched);
        result.put("directory_registration_executed", true);
        result.put("media_registration", "seeded private set via actual factory; b/c file objects not exercised");
        result.put("passed", true);
        System.out.println("REA_GRANT_RESULT=" + result.toString());
        System.exit(0);
    }
}
