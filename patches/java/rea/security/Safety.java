package rea.security;

import java.io.File;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/** Narrow guards; no transfer cancellation, credentials or filesystem mutation. */
public final class Safety {
    private Safety() {}

    /** Arguments are canonical paths obtained by the existing ZIP extractor. */
    public static boolean isInside(String candidate, String root) {
        if (candidate == null || root == null || root.length() == 0) return false;
        return candidate.equals(root) || candidate.startsWith(
                root.endsWith(File.separator) ? root : root + File.separator);
    }

    /** Bound only provider metadata waiting, never a media/file transfer. */
    public static boolean awaitMetadata(CountDownLatch latch) {
        return awaitMetadataFor(latch, 30000L);
    }

    public static boolean awaitMetadataFor(CountDownLatch latch, long millis) {
        try {
            return latch.await(millis, TimeUnit.MILLISECONDS);
        } catch (InterruptedException interrupted) {
            Thread.currentThread().interrupt();
            return false;
        }
    }
}
