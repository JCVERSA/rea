package rea.test;

import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import java.io.*;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.zip.*;
import org.json.JSONObject;

/** Synthetic files only, inside the Python-guarded disposable emulator scratch root. */
public final class SafetyProbe {
    static byte[] zip(String name) throws Exception {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        try (ZipOutputStream z = new ZipOutputStream(bytes)) {
            z.putNextEntry(new ZipEntry(name)); z.write("synthetic fixture".getBytes(StandardCharsets.UTF_8)); z.closeEntry();
        }
        return bytes.toByteArray();
    }
    public static void main(String[] args) throws Exception {
        if (args.length != 2 || !(args[0].equals("original") || args[0].equals("patched"))) throw new IllegalArgumentException();
        boolean patched = args[0].equals("patched");
        File root = new File("/data/local/tmp/rea-safety-probe/" + args[0] + "/extract");
        if (root.exists()) throw new AssertionError("Fresh scratch directory required");
        Method extract = Class.forName("ax.f2.c").getDeclaredMethod("v", InputStream.class, String.class, String.class);
        extract.setAccessible(true);
        boolean valid = (Boolean)extract.invoke(null, new ByteArrayInputStream(zip("lib/valid.so")),root.getPath(),".so");
        if (!valid || !new File(root,"lib/valid.so").isFile()) throw new AssertionError("Valid ZIP refused");
        boolean escaped = (Boolean)extract.invoke(null, new ByteArrayInputStream(zip("../extract-other/evil.so")),root.getPath(),".so");
        boolean fileWritten = new File(root.getParentFile(),"extract-other/evil.so").isFile();
        if (escaped != !patched || fileWritten != !patched) throw new AssertionError("Unexpected ZIP boundary behavior");
        int paletteCases = 0;
        if (patched) {
            Class.forName("SafetyTest").getMethod("main",String[].class).invoke(null,(Object)new String[0]);
            AssetManager assets = AssetManager.class.getDeclaredConstructor().newInstance();
            Method add = AssetManager.class.getDeclaredMethod("addAssetPath",String.class);
            if ((Integer)add.invoke(assets,args[1]) == 0) throw new AssertionError("APK assets not loaded");
            DisplayMetrics metrics = new DisplayMetrics(); metrics.setToDefaults();
            for (boolean night : new boolean[] {false,true}) {
                Configuration config = new Configuration(); config.setToDefaults();
                config.uiMode = Configuration.UI_MODE_TYPE_NORMAL | (night ? Configuration.UI_MODE_NIGHT_YES : Configuration.UI_MODE_NIGHT_NO);
                Resources resources = new Resources(assets,metrics,config);
                for (int i=0;i<PaletteIds.IDS.length;i++) {
                    int value=resources.getColor(PaletteIds.IDS[i],null);
                    if (value!=(night?PaletteIds.NIGHT[i]:PaletteIds.LIGHT[i])) throw new AssertionError("Palette mismatch: "+PaletteIds.NAMES[i]+" night="+night);
                    paletteCases++;
                }
            }
            assets.close();
        }
        JSONObject result=new JSONObject();result.put("variant",args[0]);result.put("actual_zip_extractor_valid_entry",true);
        result.put("actual_zip_extractor_neighbor_write",fileWritten);result.put("safety_suite_from_actual_apk",patched);
        result.put("android_resource_color_cases",paletteCases);result.put("passed",true);
        System.out.println("REA_SAFETY_RESULT="+result);System.exit(0);
    }
}
