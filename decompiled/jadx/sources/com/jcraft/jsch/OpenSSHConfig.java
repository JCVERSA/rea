package com.jcraft.jsch;

import ax.p052d1.h;
import j$.util.DesugarArrays;
import j$.util.function.Function$CC;
import j$.util.stream.Collectors;
import j$.util.stream.Stream;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.Vector;
import java.util.function.Function;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes3.dex */
public class OpenSSHConfig implements ConfigRepository {
    private static final Set<String> e = (Set) Stream.CC.of("KexAlgorithms", "Ciphers", "HostKeyAlgorithms", "MACs", "PubkeyAcceptedAlgorithms", "PubkeyAcceptedKeyTypes").map(new Function() { // from class: ax.fa.d
        public /* synthetic */ Function andThen(Function function) {
            return Function$CC.$default$andThen(this, function);
        }

        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return ((String) obj).toUpperCase(Locale.ROOT);
        }

        public /* synthetic */ Function compose(Function function) {
            return Function$CC.$default$compose(this, function);
        }
    }).collect(Collectors.toSet());
    private static final Hashtable<String, String> f;
    private final Hashtable<String, Vector<String[]>> c;
    private final Vector<String> d;

    class MyConfig implements ConfigRepository.Config {
        private String a;
        private Vector<Vector<String[]>> b;

        MyConfig(String str) {
            boolean z;
            Vector<Vector<String[]>> vector = new Vector<>();
            this.b = vector;
            this.a = str;
            vector.addElement((Vector) OpenSSHConfig.this.c.get(HttpUrl.FRAGMENT_ENCODE_SET));
            byte[] bArrV = Util.v(str);
            if (OpenSSHConfig.this.d.size() > 1) {
                for (int i = 1; i < OpenSSHConfig.this.d.size(); i++) {
                    for (String str2 : ((String) OpenSSHConfig.this.d.elementAt(i)).split("[ \t]")) {
                        String strTrim = str2.trim();
                        if (strTrim.startsWith("!")) {
                            strTrim = strTrim.substring(1).trim();
                            z = true;
                        } else {
                            z = false;
                        }
                        if (Util.r(Util.v(strTrim), bArrV)) {
                            if (!z) {
                                this.b.addElement((Vector) OpenSSHConfig.this.c.get(OpenSSHConfig.this.d.elementAt(i)));
                            }
                        } else if (z) {
                            this.b.addElement((Vector) OpenSSHConfig.this.c.get(OpenSSHConfig.this.d.elementAt(i)));
                        }
                    }
                }
            }
        }

        private String e(String str) {
            String upperCase = (OpenSSHConfig.f.get(str) != null ? (String) OpenSSHConfig.f.get(str) : str).toUpperCase(Locale.ROOT);
            String str2 = null;
            for (int i = 0; i < this.b.size(); i++) {
                Vector<String[]> vectorElementAt = this.b.elementAt(i);
                for (int i2 = 0; i2 < vectorElementAt.size(); i2++) {
                    String[] strArrElementAt = vectorElementAt.elementAt(i2);
                    if (strArrElementAt[0].toUpperCase(Locale.ROOT).equals(upperCase)) {
                        str2 = strArrElementAt[1];
                        break;
                    }
                }
                if (str2 != null) {
                    break;
                }
            }
            if (OpenSSHConfig.e.contains(upperCase) && str2 != null && (str2.startsWith("+") || str2.startsWith("-") || str2.startsWith("^"))) {
                String strTrim = JSch.f(str).trim();
                if (str2.startsWith("+")) {
                    return strTrim + "," + str2.substring(1).trim();
                }
                if (str2.startsWith("-")) {
                    List list = (List) DesugarArrays.stream(Util.u(strTrim, ",")).collect(Collectors.toList());
                    for (String str3 : Util.u(str2.substring(1).trim(), ",")) {
                        list.remove(str3.trim());
                    }
                    return h.a(",", list);
                }
                if (str2.startsWith("^")) {
                    return str2.substring(1).trim() + "," + strTrim;
                }
            }
            return str2;
        }

        private String[] f(String str) {
            String str2;
            String upperCase = str.toUpperCase(Locale.ROOT);
            Vector vector = new Vector();
            for (int i = 0; i < this.b.size(); i++) {
                Vector<String[]> vectorElementAt = this.b.elementAt(i);
                for (int i2 = 0; i2 < vectorElementAt.size(); i2++) {
                    String[] strArrElementAt = vectorElementAt.elementAt(i2);
                    if (strArrElementAt[0].toUpperCase(Locale.ROOT).equals(upperCase) && (str2 = strArrElementAt[1]) != null) {
                        vector.remove(str2);
                        vector.addElement(str2);
                    }
                }
            }
            String[] strArr = new String[vector.size()];
            vector.toArray(strArr);
            return strArr;
        }

        @Override // com.jcraft.jsch.ConfigRepository.Config
        public int a() {
            try {
                return Integer.parseInt(e("Port"));
            } catch (NumberFormatException unused) {
                return -1;
            }
        }

        @Override // com.jcraft.jsch.ConfigRepository.Config
        public String[] b(String str) {
            return f(str);
        }

        @Override // com.jcraft.jsch.ConfigRepository.Config
        public String c() {
            return e("User");
        }

        @Override // com.jcraft.jsch.ConfigRepository.Config
        public String d() {
            return e("Hostname");
        }

        @Override // com.jcraft.jsch.ConfigRepository.Config
        public String getValue(String str) {
            if (!str.equals("compression.s2c") && !str.equals("compression.c2s")) {
                return e(str);
            }
            String strE = e(str);
            return (strE == null || strE.equals("no")) ? "none,zlib@openssh.com,zlib" : "zlib@openssh.com,zlib,none";
        }
    }

    static {
        Hashtable<String, String> hashtable = new Hashtable<>();
        f = hashtable;
        hashtable.put("kex", "KexAlgorithms");
        hashtable.put("server_host_key", "HostKeyAlgorithms");
        hashtable.put("cipher.c2s", "Ciphers");
        hashtable.put("cipher.s2c", "Ciphers");
        hashtable.put("mac.c2s", "Macs");
        hashtable.put("mac.s2c", "Macs");
        hashtable.put("compression.s2c", "Compression");
        hashtable.put("compression.c2s", "Compression");
        hashtable.put("compression_level", "CompressionLevel");
        hashtable.put("MaxAuthTries", "NumberOfPasswordPrompts");
    }

    @Override // com.jcraft.jsch.ConfigRepository
    public ConfigRepository.Config a(String str) {
        return new MyConfig(str);
    }
}
