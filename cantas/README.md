# Cantas — native Android foundation

Independent application ID **`app.cantas.jcversa`**. Kotlin2.1.20, Jetpack Compose/Material3 (BOM2025.04.01), AGP8.9.2, Gradle8.11.1, JDK17, minSdk24, target/compileSdk35. These versions are a conservative initial build configuration, not an automatic copy of the reference repository's newest versions.

## First milestone (0.1 alpha)

Implemented source:
- Original launcher icon and Compose home with functioning shared-storage shortcuts.
- Home / Files / Settings bottom navigation; system/light/AMOLED themes saved locally.
- Explicit storage-access request. Android7–10 READ permission; Android11+ all-files settings. Restricted folders are not bypassed.
- Read-only local explorer, folder/parent navigation, refresh, empty/error states.
- Selected file opening with a temporary read-only FileProvider grant to an external app.
- French/English resources, RTL support, no ads/analytics/account/Internet permission.
- Six JVM filesystem tests and Compose instrumentation/navigation/theme test; isolated synthetic-file smoke on Android7/API24 x86 and Android12/API31 x86_64.

**Not full feature parity.** No writes, copy/move/delete/rename, search, true pagination, hidden-file option, SD/USB SAF picker, internal media viewers, archive engine, cloud/NAS/server, recycle bin or Shizuku adapter yet. These are recorded in [the roadmap](../docs/CANTAS_ROADMAP.md), not presented as working buttons.

The visible result is capped at1,000 items. `File.listFiles()` still enumerates the full folder internally, so this is **not bounded-memory pagination** for exceptionally large directories. Scanning/metadata run on an IO dispatcher; cancellation suppresses stale results but does not forcibly interrupt Java's blocking directory enumeration.

## Build

```sh
cd cantas
./gradlew testDebugUnitTest lintDebug assembleDebug
# With a disposable emulator:
./gradlew connectedDebugAndroidTest
```

Android SDK35/build-tools35 and JDK17 required. Network resolution of Google/Maven/Gradle repositories is required for a fresh build. The coding sandbox cannot reach those hosts; compilation is performed in GitHub Actions, not claimed locally.

`../.github/workflows/cantas.yml` gates delivery on build, lint, unit tests, APK identity/permission/native-ABI checks and both emulator jobs. The distribution checksum is now pinned from the successful CI report; CI compares the official HTTPS checksum against that source pin and the wrapper validates the archive with it; the wrapper JAR is fetched from Gradle's `v8.11.1` source tag (SHA-256 `2db75c40782f5e8ba1fc278a5574bab070adccb2d21ca5a6e5ed840888448046`). Validation results, including the resolved distribution checksum, are archived separately.

Debug TEST builds use a temporary Android debug signing key on the runner. Private signing files are never committed/uploaded. Different builds can have different signatures. Never uninstall File Manager+ to install Cantas: the IDs are distinct. Backup any Cantas-private data before a later manual uninstall prompted by a changed TEST signature.

Emulator scripts are intentionally restricted to disposable `emulator-*` devices and use only a fixed synthetic test directory. **Do not run them on the Tecno.**

## Security boundaries

No destructive filesystem API in this milestone. Canonical shared-storage boundary rejects traversal, neighboring-prefix confusion and symlinks escaping the selected root. The exported launcher ignores external data URIs. FileProvider is non-exported and only selected files receive read-only grants; no private preferences/provider root is declared. These checks are not a complete security audit or a race-free filesystem guarantee.

The broad all-files permission is intended for a full file manager; granting it is explicit, and Play-distribution approval is not asserted. This project is currently for personal use. External apps receiving a chosen file follow their own privacy policies.

## Verified first TEST

[CI38678972198](https://github.com/JCVERSA/rea/actions/runs/38678972198) passed build, JVM unit tests, lint, both emulator jobs and delivery. **[Download alpha TEST](https://github.com/JCVERSA/rea/actions/runs/38678972198/artifacts/11947673184)**: `Cantas-0.1.0-alpha01-TEST.apk` inside the ZIP, 14-day GitHub retention. Build source: `87a011927db48498e3851d4d8b3f5017f104b766`. SHA-256 recorded in CI: `ccfc8f9cf8b7f6aa3fa510090ce8f1862ec271b1436ee2d0420b909f7a12a3d1`.

Six JVM tests pass; one Compose instrumentation test per emulator, plus actual browsing of a synthetic folder/file, entering an empty subfolder and back navigation. Four captured day/night surfaces were checked after CI. Lint: **0 errors, 11 warnings**, not “warning-free”: all-files Play policy, older target/dependencies, backup extraction configuration and KTX style suggestions. Details remain in the reports; no blind upgrades or warning suppression.

Native `libandroidx.graphics.path.so` is present for ARMv7, ARM64, x86 and x86_64. The first run38678652899 passed compilation/tests/lint but failed an overly restrictive “no native library” gate; this was corrected to require ARM32 coverage for any packaged native library. No APK from that failed run is delivered.

Reports/captures: `../analysis/cantas/38678972198/`. See [delivery notes](../docs/CANTAS_ALPHA_01.md). No physical Tecno, ARM32 Android12, external-viewer end-to-end, Android8–11/13–15, French UI, SAF/Shizuku/cloud, accessibility or performance validation claimed. The final checksum pin matches the checksum used by the successful build; documentation/pin follow-up does not change application Kotlin/XML source.
