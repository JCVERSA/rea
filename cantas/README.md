# Cantas — native Android foundation

Independent application ID **`app.cantas.jcversa`**. Kotlin2.1.20, Jetpack Compose/Material3 (BOM2025.04.01), AGP8.9.2, Gradle8.11.1, JDK17, minSdk24, target/compileSdk35. These versions are a conservative initial build configuration, not an automatic copy of the reference repository's newest versions.

## Current milestone (0.2.0-alpha02)

Implemented source:
- Original launcher icon and Compose home with functioning shared-storage shortcuts.
- Home / Files / Settings bottom navigation; system/light/AMOLED themes saved locally.
- Explicit storage-access request. Android7–10 READ permission; Android11+ all-files settings. Restricted folders are not bypassed.
- Read-only local explorer, folder/parent navigation, refresh, empty/error states.
- Selected file opening with a temporary read-only FileProvider grant to an external app.
- French/English resources, RTL support, no ads/analytics/account/Internet permission.
- Current-folder case-insensitive substring search; folders-first name A–Z/Z–A or descending file-size sorting; hidden dot-name switch.
- Load more in steps of1,000, up to100,000 displayed matches. Search changes are debounced180ms and superseded results/errors are suppressed.
- Ten JVM filesystem tests and three instrumentation tests per Android7/API24 x86 and Android12/API31 x86_64 emulator, including backend controls, real UI search/hidden/sort, navigation/themes and isolated synthetic browsing.

**Not full feature parity.** No writes, copy/move/delete/rename, recursive/global search, true streaming pagination, SD/USB SAF picker, internal media viewers, archive engine, cloud/NAS/server, recycle bin or Shizuku adapter yet. These are recorded in [the roadmap](../docs/CANTAS_ROADMAP.md), not presented as working buttons.

The initial visible result is capped at1,000 items; load-more raises the display limit in steps of1,000 to100,000. A priority queue retains only the best limit+1 metadata entries, with sorting applied across all matches. Each increase rescans the folder. Sort/hidden choices are session-only; folder navigation resets the query and display limit. `File.listFiles()` still enumerates the full folder internally, so this is **not bounded-memory pagination** for exceptionally large directories. Scanning/metadata run on an IO dispatcher; cancellation suppresses stale results but does not forcibly interrupt Java's blocking directory enumeration.

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

## Verified current TEST

[CI38681612415](https://github.com/JCVERSA/rea/actions/runs/38681612415) passed all delivery gates from source `8f24ff96246c62a6dc987a709a3069efb5c1d327`. **[Download alpha02 TEST](https://github.com/JCVERSA/rea/actions/runs/38681612415/artifacts/11948193347)**: `Cantas-0.2.0-alpha02-TEST.apk` inside the ZIP (14-day retention).

SHA-256 recorded in CI: `25704b7607a540c03958f87ad10e0219b6c716025102380c5c7845af71f6c4ad`. Ten JVM tests pass, three instrumentation tests pass on each emulator, and both synthetic runtime checks pass. Lint: **0 errors, 12 warnings**, including one unused sort-label resource, policy/target/dependency notices, extraction-rule configuration and KTX suggestions. Warnings remain visible; no automatic dependency upgrades or suppressions.

APK signature v2 verifies; packaged native graphics-path library has ARMv7, ARM64, x86 and x86_64 variants. Reports and actual captures: `../analysis/cantas/38681612415/`. No physical Tecno, ARM32 Android12, French UI, complete Android7–15, accessibility, external-viewer end-to-end or performance validation claimed. FR/EN resources exist; that is not a translated-UI runtime test.

See [alpha02 delivery notes](../docs/CANTAS_ALPHA_02.md). [Alpha01 notes](../docs/CANTAS_ALPHA_01.md) and its evidence remain preserved. An earlier alpha02 run38680800822 failed the API24 emulator-runner stage without an established cause; the subsequent intermediate38680969710 and final38681612415 runs passed both emulator jobs. Only the final source/run above is this delivery.
