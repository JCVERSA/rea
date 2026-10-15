package rea.security;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Locale;

/** Typed URI grants. No filesystem resolution and no recursive percent-decoding. */
public final class MediaGrant {
    private MediaGrant() {}

    public static String directory(String uri) { return "D:" + uri; }
    public static String media(String uri) { return "M:" + uri; }

    private static boolean safePath(String path) {
        if (path.indexOf('\0') >= 0) return false;
        // Backslash is checked as a separator for traversal only, not rewritten for matching.
        String check = path.replace('\\', '/');
        for (String segment : check.split("/", -1)) {
            if (segment.equals(".") || segment.equals("..")) return false;
        }
        return true;
    }

    private static String path(URI uri) {
        String path = uri.getPath(); // one decode, as with the application's Uri.getPath().
        return path == null || path.isEmpty() ? "/" : path;
    }

    private static boolean subtitle(String suffix) {
        String lower = suffix.toLowerCase(Locale.ROOT);
        // Four formats in ax.J1.u.m(), plus common external-player subtitle sidecars.
        String[] extensions = {".ssa", ".ass", ".srt", ".vtt", ".sub", ".idx", ".ttml", ".dfxp", ".smi", ".sami"};
        for (String extension : extensions) if (lower.endsWith(extension)) return true;
        return false;
    }

    public static boolean allows(String request, String grant) {
        if (request == null || grant == null || grant.length() < 3) return false;
        boolean directory = grant.startsWith("D:");
        if (!directory && !grant.startsWith("M:")) return false;
        try {
            URI requested = new URI(request);
            URI selected = new URI(grant.substring(2));
            if (requested.getScheme() == null || selected.getScheme() == null
                    || !requested.getScheme().equals(selected.getScheme())
                    || requested.getRawAuthority() == null
                    || !requested.getRawAuthority().equals(selected.getRawAuthority())
                    || requested.getRawQuery() != null || requested.getRawFragment() != null
                    || selected.getRawQuery() != null || selected.getRawFragment() != null) return false;
            String target = path(requested);
            String allowed = path(selected);
            if (!target.startsWith("/") || !allowed.startsWith("/")
                    || !safePath(target) || !safePath(allowed)) return false;
            if (target.equals(allowed)) return true;
            if (directory) {
                String prefix = allowed.endsWith("/") ? allowed : allowed + "/";
                return target.startsWith(prefix);
            }
            // Same-directory subtitle family only, not an arbitrary filename prefix.
            int slash = allowed.lastIndexOf('/');
            int dot = allowed.lastIndexOf('.');
            String stem = dot > slash + 1 ? allowed.substring(0, dot) : allowed;
            String prefix = stem + ".";
            if (!target.startsWith(prefix)) return false;
            String suffix = target.substring(stem.length());
            return suffix.indexOf('/') < 0 && suffix.indexOf('\\') < 0 && subtitle(suffix);
        } catch (URISyntaxException malformed) {
            return false;
        }
    }
}
