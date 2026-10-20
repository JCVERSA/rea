package app.cantas.jcversa;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.OperationCanceledException;
import android.os.ParcelFileDescriptor;
import android.os.SystemClock;
import android.provider.DocumentsContract;
import android.provider.DocumentsContract.Document;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;

/** Test APK only, framework-only so its separate process needs no tested-APK Kotlin classes. */
public class TreeFixtureProvider extends ContentProvider {
    public static final String AUTHORITY = "app.cantas.jcversa.test.treefixture";
    public static final String ROOT = "root:opaque/id";
    public static final String FOLDER = "folder:not/a/path";
    public static final String INNER = "child:unrelated!";
    public static final Uri TREE = DocumentsContract.buildTreeDocumentUri(AUTHORITY, ROOT);
    private final java.util.LinkedHashMap<String, String> created = new java.util.LinkedHashMap<>();
    private int creationCalls;
    private volatile boolean slowStarted;
    private volatile boolean slowCancelled;
    @Override public boolean onCreate() { return true; }
    @Override public Cursor query(Uri uri, String[] projection, String selection, String[] args, String order, CancellationSignal signal) {
        if ("children".equals(uri.getLastPathSegment()) && "slow-root".equals(DocumentsContract.getDocumentId(uri))) {
            slowStarted = true;
            slowCancelled = false;
            try {
                for (int i = 0; i < 1000; i++) {
                    if (signal != null) signal.throwIfCanceled();
                    SystemClock.sleep(10);
                }
            } catch (OperationCanceledException cancelled) {
                slowCancelled = true;
                throw cancelled;
            }
        }
        return query(uri, projection, selection, args, order);
    }
    private void row(MatrixCursor result, String[] columns, String id, String name, boolean directory, Long size) {
        Object[] values = new Object[columns.length];
        for (int i = 0; i < columns.length; i++) {
            switch (columns[i]) {
                case Document.COLUMN_DOCUMENT_ID: values[i] = id; break;
                case Document.COLUMN_DISPLAY_NAME: values[i] = name; break;
                case Document.COLUMN_MIME_TYPE: values[i] = directory ? Document.MIME_TYPE_DIR : "text/plain"; break;
                case Document.COLUMN_SIZE: values[i] = size; break;
                case Document.COLUMN_FLAGS: values[i] = directory && (id.equals("write-root") || id.startsWith("created-")) ? Document.FLAG_DIR_SUPPORTS_CREATE : 0; break;
            }
        }
        result.addRow(values);
    }
    @Override public synchronized Cursor query(Uri uri, String[] projection, String selection, String[] args, String order) {
        String[] columns = projection != null ? projection : new String[] {
            Document.COLUMN_DOCUMENT_ID, Document.COLUMN_DISPLAY_NAME, Document.COLUMN_MIME_TYPE, Document.COLUMN_SIZE };
        MatrixCursor result = new MatrixCursor(columns);
        String id = DocumentsContract.getDocumentId(uri);
        if ("write-root".equals(DocumentsContract.getTreeDocumentId(uri))) {
            if ("children".equals(uri.getLastPathSegment())) {
                if ("write-root".equals(id)) {
                    row(result, columns, "keep", "KEEP.txt", false, 7L);
                    for (java.util.Map.Entry<String,String> entry : created.entrySet()) row(result, columns, entry.getKey(), entry.getValue(), true, 0L);
                }
            } else if ("write-root".equals(id)) row(result, columns, id, "Writable fixture", true, 0L);
            else if (created.containsKey(id)) row(result, columns, id, created.get(id), true, 0L);
            else if ("keep".equals(id)) row(result, columns, id, "KEEP.txt", false, 7L);
            return result;
        }
        if ("children".equals(uri.getLastPathSegment())) {
            switch (id) {
                case ROOT:
                    row(result, columns, FOLDER, "Nested", true, 0L);
                    row(result, columns, "text", "Résumé.txt", false, 7L);
                    row(result, columns, "hidden", ".secret", false, 7L);
                    for (int i = 0; i <= 1004; i++) row(result, columns, "file-" + i, String.format(Locale.ROOT, "%04d.txt", i), false, i + 10L);
                    break;
                case FOLDER: row(result, columns, INNER, "inner.txt", false, 7L); break;
                case "import-root": row(result, columns, "import-folder", "ImportFolder", true, 0L); break;
                case "import-folder":
                    row(result, columns, "text", "Résumé.txt", false, 7L);
                    row(result, columns, "unknown", "unknown.txt", false, null);
                    row(result, columns, "import-empty", "Empty", true, 0L);
                    break;
                case "null-root": result.close(); return null;
            }
        } else {
            switch (id) {
                case ROOT: case "null-root": case "slow-root": row(result, columns, id, "Synthetic tree", true, 0L); break;
                case FOLDER: row(result, columns, id, "Nested", true, 0L); break;
                case "import-root": row(result, columns, id, "Import fixture", true, 0L); break;
                case "import-folder": row(result, columns, id, "ImportFolder", true, 0L); break;
                case "import-empty": row(result, columns, id, "Empty", true, 0L); break;
                case "unknown": row(result, columns, id, "unknown.txt", false, null); break;
                case INNER: row(result, columns, id, "inner.txt", false, 7L); break;
                case "text": row(result, columns, id, "Résumé.txt", false, 7L); break;
            }
        }
        return result;
    }
    @Override public synchronized Bundle call(String method, String arg, Bundle extras) {
        if ("write-reset".equals(method)) { created.clear(); creationCalls = 0; return new Bundle(); }
        if ("write-state".equals(method)) { Bundle b = new Bundle(); b.putInt("calls", creationCalls); b.putInt("folders", created.size()); return b; }
        if ("android:createDocument".equals(method)) {
            Uri parent = extras.getParcelable("uri");
            if (!"write-root".equals(DocumentsContract.getDocumentId(parent))) throw new IllegalArgumentException();
            if (getContext().checkUriPermission(parent, Binder.getCallingPid(), Binder.getCallingUid(), Intent.FLAG_GRANT_WRITE_URI_PERMISSION) != android.content.pm.PackageManager.PERMISSION_GRANTED) throw new SecurityException();
            if (!Document.MIME_TYPE_DIR.equals(extras.getString(Document.COLUMN_MIME_TYPE))) throw new IllegalArgumentException();
            creationCalls++;
            String name = extras.getString(Document.COLUMN_DISPLAY_NAME);
            if (created.containsValue(name) || "KEEP.txt".equals(name)) throw new IllegalArgumentException();
            String id = "created-" + creationCalls;
            created.put(id, "auto-rename".equals(name) ? "auto-rename (1)" : name);
            if ("throw-after-create".equals(name)) throw new IllegalStateException("Fixture post-creation failure");
            Bundle out = new Bundle(); out.putParcelable("uri", DocumentsContract.buildDocumentUriUsingTree(parent, id)); return out;
        }
        if ("slow-reset".equals(method)) { slowStarted = false; slowCancelled = false; return new Bundle(); }
        if ("slow-state".equals(method)) {
            Bundle state = new Bundle();
            state.putBoolean("started", slowStarted); state.putBoolean("cancelled", slowCancelled);
            return state;
        }
        if (!"grant-read".equals(method) && !"revoke-read".equals(method) && !"grant-write".equals(method) && !"revoke-write".equals(method)) return super.call(method, arg, extras);
        String requested = arg == null ? ROOT : arg;
        if (!(ROOT.equals(requested) || "null-root".equals(requested) || "slow-root".equals(requested) || "import-root".equals(requested) || "write-root".equals(requested))) throw new IllegalArgumentException();
        Uri grant = DocumentsContract.buildTreeDocumentUri(AUTHORITY, requested);
        long identity = Binder.clearCallingIdentity();
        try {
            if ("revoke-read".equals(method) || "revoke-write".equals(method)) getContext().revokeUriPermission(grant, Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            else getContext().grantUriPermission("app.cantas.jcversa", grant, ("grant-write".equals(method) ? Intent.FLAG_GRANT_WRITE_URI_PERMISSION : 0) | Intent.FLAG_GRANT_READ_URI_PERMISSION |
                Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION | Intent.FLAG_GRANT_PREFIX_URI_PERMISSION);
        } finally { Binder.restoreCallingIdentity(identity); }
        return new Bundle();
    }
    @Override public String getType(Uri uri) {
        String id = DocumentsContract.getDocumentId(uri);
        return ROOT.equals(id) || FOLDER.equals(id) || "import-root".equals(id) || "import-folder".equals(id) || "import-empty".equals(id) ? Document.MIME_TYPE_DIR : "text/plain";
    }
    @Override public ParcelFileDescriptor openFile(Uri uri, String mode) throws FileNotFoundException {
        String id = DocumentsContract.getDocumentId(uri);
        if (!"r".equals(mode) || !(INNER.equals(id) || "text".equals(id) || "unknown".equals(id) || "keep".equals(id))) throw new FileNotFoundException();
        try {
            ParcelFileDescriptor[] pipe = ParcelFileDescriptor.createPipe();
            try (ParcelFileDescriptor.AutoCloseOutputStream output = new ParcelFileDescriptor.AutoCloseOutputStream(pipe[1])) {
                output.write("fixture".getBytes(java.nio.charset.StandardCharsets.UTF_8));
            }
            return pipe[0];
        } catch (IOException error) { throw new FileNotFoundException(error.toString()); }
    }
    @Override public Uri insert(Uri uri, ContentValues values) { throw new UnsupportedOperationException(); }
    @Override public int update(Uri uri, ContentValues values, String where, String[] args) { throw new UnsupportedOperationException(); }
    @Override public int delete(Uri uri, String where, String[] args) { throw new UnsupportedOperationException(); }
}
