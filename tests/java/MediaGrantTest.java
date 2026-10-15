import rea.security.MediaGrant;

/** Runs on the JVM and inside the Android probe via reflection-based equivalents. */
public final class MediaGrantTest {
    private static int count;
    private static void check(boolean expected, String request, String grant) {
        boolean actual = MediaGrant.allows(request, grant);
        if (actual != expected) throw new AssertionError("Unexpected grant decision at case " + count);
        count++;
    }
    public static void main(String[] args) {
        String file = MediaGrant.media("local://0/storage/movies/clip.mp4");
        String dir = MediaGrant.directory("local://0/storage/movies");
        check(true, "local://0/storage/movies/clip.mp4", file);
        check(true, "local://0/storage/movies/clip.srt", file);
        check(true, "local://0/storage/movies/clip.en.forced.SRT", file);
        check(true, "local://0/storage/movies/clip.ass", file);
        check(true, "local://0/storage/movies/clip.vtt", file);
        check(true, "local://0/storage/movies/clip.idx", file);
        check(false, "local://0/storage/movies/clip-private.mp4", file);
        check(false, "local://0/storage/movies/clip.private.mp4", file);
        check(false, "local://0/storage/movies/clip.txt", file);
        check(false, "local://0/storage/movies/clip/sub.srt", file);
        check(false, "local://0/storage/movies2/clip.srt", file);
        check(false, "local://1/storage/movies/clip.mp4", file);
        check(false, "smb://0/storage/movies/clip.mp4", file);
        check(true, "local://0/storage/movies", dir);
        check(true, "local://0/storage/movies/other.mp4", dir);
        check(true, "local://0/storage/movies/css/site.css", dir);
        check(false, "local://0/storage/movies-private/secret", dir);
        check(false, "local://0/storage/movies.private/secret", dir);
        check(false, "local://0/storage/movies/../private", dir);
        check(false, "local://0/storage/movies/%2e%2e/private", dir);
        check(false, "local://0/storage/movies/%2E%2E/private", dir);
        check(false, "local://0/storage/movies/%2e/private", dir);
        check(false, "local://0/storage/movies/..%5cprivate", dir);
        check(false, "local://0/storage/movies/%00secret", dir);
        check(false, "local://0/storage/movies/clip.mp4?key=x", file);
        check(false, "local://0/storage/movies/clip.mp4#fragment", file);
        check(false, "local://0/storage/movies/clip.mp4", "local://0/storage/movies/clip");
        check(false, "local://0/storage/movies/clip.mp4", "X:local://0/storage/movies");
        check(false, null, file);
        check(false, "not a URI", file);
        check(true, "local://0/any/file", MediaGrant.directory("local://0"));
        check(true, "smb://4/share/a%20b.mp4", MediaGrant.media("smb://4/share/a%20b.mp4"));
        check(true, "smb://4/share/a%20b.fr.srt", MediaGrant.media("smb://4/share/a%20b.mp4"));
        check(true, "local://0/m/%E9%9B%AA%23%3F.mp4", MediaGrant.media("local://0/m/%E9%9B%AA%23%3F.mp4"));
        check(true, "local://0/m/100%25.mp4", MediaGrant.media("local://0/m/100%25.mp4"));
        check(true, "local://0/m/.hidden.srt", MediaGrant.media("local://0/m/.hidden"));
        check(false, "local://0/m/.srt", MediaGrant.media("local://0/m/.hidden"));
        System.out.println("MediaGrant policy cases PASS: " + count);
    }
}
