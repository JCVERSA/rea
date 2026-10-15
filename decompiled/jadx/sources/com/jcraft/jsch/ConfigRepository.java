package com.jcraft.jsch;

/* JADX INFO: loaded from: classes3.dex */
public interface ConfigRepository {
    public static final Config a = new Config() { // from class: com.jcraft.jsch.ConfigRepository.1
        @Override // com.jcraft.jsch.ConfigRepository.Config
        public int a() {
            return -1;
        }

        @Override // com.jcraft.jsch.ConfigRepository.Config
        public String[] b(String str) {
            return null;
        }

        @Override // com.jcraft.jsch.ConfigRepository.Config
        public String c() {
            return null;
        }

        @Override // com.jcraft.jsch.ConfigRepository.Config
        public String d() {
            return null;
        }

        @Override // com.jcraft.jsch.ConfigRepository.Config
        public String getValue(String str) {
            return null;
        }
    };
    public static final ConfigRepository b = new ConfigRepository() { // from class: com.jcraft.jsch.ConfigRepository.2
        @Override // com.jcraft.jsch.ConfigRepository
        public Config a(String str) {
            return ConfigRepository.a;
        }
    };

    public interface Config {
        int a();

        String[] b(String str);

        String c();

        String d();

        String getValue(String str);
    }

    Config a(String str);
}
