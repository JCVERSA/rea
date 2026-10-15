package rea.security;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

/** FTP control commands only. Data sockets and file contents never use this reader. */
public final class ControlReader extends BufferedReader {
    private boolean skipLF;

    public ControlReader(Reader reader, int bufferSize) {
        super(reader, bufferSize);
    }

    @Override public String readLine() throws IOException {
        // Preserve BufferedReader's CR/LF/CRLF and EOF behavior without an unbounded line.
        synchronized (lock) {
            StringBuilder line = new StringBuilder(128);
            while (true) {
                int ch = super.read();
                if (skipLF) {
                    skipLF = false;
                    if (ch == '\n') continue;
                }
                if (ch == -1) return line.length() == 0 ? null : line.toString();
                if (ch == '\r') { skipLF = true; return line.toString(); }
                if (ch == '\n') return line.toString();
                if (line.length() == 8192) throw new IOException("FTP control command too long");
                line.append((char) ch);
            }
        }
    }
}
