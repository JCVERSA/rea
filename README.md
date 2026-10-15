# File Manager APK investigation

Static reverse-engineering workspace for the supplied **File Manager v3.8.3 (Premium).apk**. This is not the upstream app source or a build-ready Android Studio project.

## Cleaned test build — milestone 1

The two identified third-party startup hooks are disabled in a reproducible test build. This does not redesign the UI or bypass premium/licensing.

- [Build, signing and installation guidance](docs/MOD_BUILD.md)
- [Change report and validation limits](docs/MOD_CHANGE_REPORT.md)
- [Retained CI verification evidence](docs/builds/latest/)
- [Download run / artifacts](https://github.com/JCVERSA/rea/actions/runs/38660476934) — choose `file-manager-cleaned-test-2` (14-day retention).

**Runtime remains unverified.** The test key is ephemeral and differs from the installed app signature; read the installation warnings before uninstalling anything.

## Where to look

| Path | Contents |
| --- | --- |
| `decompiled/jadx/sources/com/alphainventor/filemanager/` | Expanded application Java recovered by JADX |
| `decompiled/jadx/sources/com/` | Other retained `com` namespaces, including bundled libraries and repackaging code |
| `decompiled/all-java-sources.zip` | **All** 15,436 recovered Java files, including obfuscated `ax/` and `defpackage/` helpers |
| `decompiled/apktool/` | Decoded manifest, XML layouts, strings, images, assets and Apktool metadata |
| `decompiled/apktool-bytecode.zip` | Smali and original/unknown/native entries, where present; extract into `decompiled/apktool/` to complete the decoded tree |
| `decompiled/jadx-diagnostics.log` | JADX diagnostics (45 reported errors; exit code 3) |
| `decompiled/APK.sha256` | Input fingerprint |
| `docs/ANALYSIS.md` | Findings, evidence paths, modification/recreation options and limitations |

ZIP archives keep the Git checkout manageable without discarding the obfuscated sources or bytecode. They contain generated reference material, not new original application code. Original rights and third-party licenses still apply; possession of an APK does not grant redistribution rights.

## Reproduce

The workflow `.github/workflows/decompile.yml` runs on this session branch when its workflow or decompilation scripts change. Manual dispatch is also declared, but GitHub generally requires the workflow on the default branch before it appears in the manual-dispatch UI.

It uses **Temurin Java 17**, **JADX 1.5.6**, and **Apktool 3.0.3**. The complete unfiltered output and logs are uploaded as the `file-manager-decompiled` Actions artifact (14-day retention); selected expanded output plus the complete source/bytecode ZIPs are committed to **`arena/3a37ecd7-rea` only**. No merge into `main` is performed.

For local reproduction, install Java 17, put the JADX distribution in `.tools/jadx/` and Apktool in `.tools/apktool.jar`, then run:

```sh
bash scripts/decompile.sh
```

The input APK is inspected as data; the workflow does not launch it. JADX's nonzero status is recorded rather than concealed, and the script requires generated sources to exist.

## Inspect the complete sources

```sh
mkdir -p .work/full-java
unzip -q decompiled/all-java-sources.zip -d .work/full-java
# Full source tree: .work/full-java/sources/
unzip -q decompiled/apktool-bytecode.zip -d decompiled/apktool
```

To experiment with an authorized resource/smali modification, use Apktool to rebuild the completed tree. A rebuilt APK must then be zipaligned and signed with **your own** key using Android SDK build tools. Rebuilding/signing/installing has **not** been tested here; do not treat decompilation success as proof of rebuildability. A differently signed APK cannot normally update the installed original and may lose access to signature-bound integrations. Do not use a personal device or real cloud accounts to test this untrusted repackaged input.

Verify the committed exports without running the APK:

```sh
python3 scripts/verify_decompilation.py
```

This checks the input hash, archive CRCs/path safety, Java file count, decoded XML syntax and manifest package.
