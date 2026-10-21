# Cantas — native Android foundation

Independent application ID **`app.cantas.jcversa`**. Kotlin2.1.20, Jetpack Compose/Material3 (BOM2025.04.01), AGP8.9.2, Gradle8.11.1, JDK17, minSdk24, target/compileSdk35. These versions are a conservative initial build configuration, not an automatic copy of the reference repository's newest versions.

## Current milestone (0.12.0-alpha12 — NOT FINAL)

Implemented source:
- Original launcher icon and Compose home with functioning shared-storage shortcuts.
- Home / Files / Settings bottom navigation; system/light/AMOLED themes saved locally.
- Explicit storage-access request. Android7–10 READ for browsing and legacy WRITE when needed for local creation/copy; Android11+ all-files settings. Restricted folders are not bypassed.
- Local explorer, folder/parent navigation, refresh, empty/error states.
- Multi-selection on local and SAF snapshots, long press, loaded-result selection, Back/Done and stale-selection reset.
- Confirmed creation of a single local folder via `LocalFolders`; name validation and atomic mkdir collision refusal, no replacement or recursive creation. SAF creation uses a separate opt-in read/write connection and `SafFolders`, never the read-only browsing contract.
- Legacy WRITE permission is capped at API29 and requested by local creation/copy actions when needed; modern Android reuses the explicit all-files access.
- Local file/folder copy: 1–100 roots, up to1,000 entries and depth32, whole-batch read-only preflight, nested/empty/hidden contents, no merging existing directories. Source links and destinations inside the source are refused. Files retain the captured-length bound and exclusive no-overwrite creation. Session clipboard, destination confirmation, progress, cooperative cancellation and private retained-partial/restart reports; no resume/cleanup. See [alpha06 scope and limits](../docs/CANTAS_ALPHA_06.md).
- Selected local file opening with a temporary read-only FileProvider grant to an external app.
- SAF → local import of selected files/folders using READ grants only, known/unknown-length streams (unknown capped at512MiB/file), no provider writes, no local overwrite/merge. Opaque IDs are never parsed as filesystem paths; no portable physical-separation proof. [Exact import scope and tests](../docs/CANTAS_ALPHA_07.md).
- Persistent local/SAF folder favorites: add/open/rename shortcut/remove, permission-checked ancestry restoration, corrupt-metadata warning and explicit metadata-only reset. Up to50 folders within256KiB; sort and hidden choices persist, search/selection/clipboard do not. [Exact favorites scope and tests](../docs/CANTAS_ALPHA_08.md).
- Separate explicit read/write SAF connection for single-folder creation; provider capability and scoped-parent checks, collision preflight, post-creation verification, uncertain-output warnings and no cleanup/automatic retry. Existing READ-only connections are not upgraded. [Exact creation scope and trust boundaries](../docs/CANTAS_ALPHA_09.md).
- Verified file-stream engine for local or READ-authorized SAF sources to writable SAF destinations: collision refusal, bounded streams, post-close length/SHA-256 verification, cancellation and retained uncertain-output journal reports. Introduced for files in alpha10 and now reused by recursive copy; no portable provider transaction/no-overwrite guarantee. [File-stream scope and tests](../docs/CANTAS_ALPHA_10.md).
- Recursive local/SAF copy toward SAF via bounded `SafTreePlanner` and operation-private created-directory capabilities:1–100 roots,1,000 entries, depth32, hidden/empty contents, never merge an existing directory. Unknown/failed directory creation skips its subtree; file streams reuse alpha10 hash verification. [Exact recursive scope and tests](../docs/CANTAS_ALPHA_11.md).
- Explicit single-item SAF file/folder rename on a READ+WRITE connection: discovered-child and capability checks, detected collision refusal, same/changed-ID verification, uncertain post-invocation errors without rollback. Activity recreation preserves the confirmation intent/name; favorites and clipboard names are not automatically migrated. Native local rename remains absent. [Exact scope and tests](../docs/CANTAS_ALPHA_12.md).
- Common `ReadOnlyStorage` contract and shared filter/sort/retention policy.
- SAF folder picker, default persistent READ-only connections, opaque document IDs, parent navigation and external opening of selected document URIs. SD/USB must be mounted and exposed by Android.
- Reconnect/disconnect UI; explicit confirmation releases a grant without deleting files. Existing connections are rediscovered from OS grants on relaunch, not by constructing volume paths.
- SAF works independently of broad local storage permission. Provider queries use cancellation signals; listing jobs are serialized. See [storage architecture and boundaries](../docs/CANTAS_STORAGE.md).
- French/English resources, RTL support, no ads/analytics/account/Internet permission.
- Current-folder case-insensitive substring search; folders-first name A–Z/Z–A or descending file-size sorting; hidden dot-name switch.
- Load more in steps of1,000, up to100,000 displayed matches. Search changes are debounced180ms and superseded results/errors are suppressed.
- Sixty-nine JVM tests pass and forty-nine instrumentation tests pass per Android7/API24 and Android12/API31 emulator, including file-copy staging/navigation/confirmation/collision UI, native partial-output cancellation, journal restoration, local mkdir, selection and SAF write refusal. Physical Tecno/SD/USB and the system picker remain untested.

**Not full feature parity or a final version.** No move/delete or native local rename, recursive/global search, true streaming pagination, internal media viewers, archive engine, cloud/NAS/server, recycle bin or Shizuku adapter yet. These are recorded in [the roadmap](../docs/CANTAS_ROADMAP.md), not presented as working buttons.

The initial visible result is capped at1,000 items; load-more raises the display limit in steps of1,000 to100,000. A priority queue retains only the best limit+1 metadata entries, with sorting applied across all matches. Each increase rescans the folder. Sort/hidden choices are saved locally; folder navigation resets the query and display limit. `File.listFiles()` still enumerates the full folder internally, so this is **not bounded-memory pagination** for exceptionally large directories. SAF reads a cursor but still scans all matches for global sorting; providers can allocate their own data. Neither backend promises bounded total memory. Scanning/metadata run on an IO dispatcher; cancellation suppresses stale results but does not forcibly interrupt Java's blocking directory enumeration.

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

Emulator scripts are intentionally restricted to disposable `emulator-*` devices and use only synthetic fixed/uniquely named app-owned fixture directories. **Do not run them on the Tecno.**

## Security boundaries

No destructive filesystem API in this milestone. Canonical shared-storage boundary rejects traversal, neighboring-prefix confusion and symlinks escaping the selected root. The exported launcher ignores external data URIs. FileProvider is non-exported and only selected files receive read-only grants; no private preferences/provider root is declared. These checks are not a complete security audit or a race-free filesystem guarantee.

The broad all-files permission is intended for a full file manager; granting it is explicit, and Play-distribution approval is not asserted. This project is currently for personal use. External apps receiving a chosen file follow their own privacy policies.

## Verified current TEST (alpha12 — not final)

[CI39530215958](https://github.com/JCVERSA/rea/actions/runs/39530215958) passed all five jobs from source `23fe213dd33ebff9f727ff8a1eae4cdeafec92f4`. **[Download alpha12 TEST](https://github.com/JCVERSA/rea/actions/runs/39530215958/artifacts/12314794771)**: `Cantas-0.12.0-alpha12-TEST.apk`,14-day retention, authenticated GitHub access.

CI SHA-256: `df6371dc7990b8c0e2dcd529c3b015b06d4022611088dd38a85a6d59f0ad14ab` (not rehashed locally). **69 JVM tests,49 instrumentation tests per API24/API31 emulator and both runtime smoke checks pass; lint0errors/33warnings**, no suppression. Independent ID, min24/target35, no INTERNET, signature v2 and four graphics ABIs including ARMv7 verified. Evidence: `../analysis/cantas/39530215958/`; API31 verified rename dialog inspected. [Behavior, earlier smoke failures/correction and exact proof boundaries](../docs/CANTAS_ALPHA_12.md).

Real SAF rename only, not native local rename, move/delete or recycle bin. Provider operations are not portable atomic no-overwrite transactions; uncertain names/URIs require inspection, never automatic rollback. No durable rename recovery journal, physical Tecno/ARM32/2GB, real SD/USB/cloud/system-picker, forced process-kill, full Android7–15, French/accessibility or performance proof.

## Previous verified TEST (alpha11, historical)

[CI39525354942](https://github.com/JCVERSA/rea/actions/runs/39525354942) passed all five jobs from source `aa723f3cf6429aa989759177b69a35fe68a589bd`. **[Download alpha11 TEST](https://github.com/JCVERSA/rea/actions/runs/39525354942/artifacts/12312751732)**: `Cantas-0.11.0-alpha11-TEST.apk`,14-day retention, authenticated GitHub access.

CI SHA-256: `6148cd3ed9c0e854b13d1280f9f60b9b118ae3ad3f5c78db40a339b528dfedfd` (not rehashed locally). **69 JVM tests,44 instrumentation tests per API24/API31 emulator and both runtime smoke checks pass; lint0errors/32warnings**, no suppression. Independent ID, min24/target35, no INTERNET, signature v2 and four graphics ABIs including ARMv7 verified. Evidence: `../analysis/cantas/39525354942/`; API31 recursive SAF report inspected with three directories and a hidden file. [Recursive behavior, boundaries and validation](../docs/CANTAS_ALPHA_11.md).

Bounded recursive copy, not a source snapshot, cross-instance transaction, provider durability or universal no-overwrite proof. Existing directories never merge; unknown parents block descendants. Partial outputs stay, no cleanup/retry. No real Tecno/ARM32/2GB, SD/USB/cloud/system-picker, process-kill, full Android7–15, French/accessibility or performance proof.

## Previous verified TEST (alpha10, historical)

[CI39520353291](https://github.com/JCVERSA/rea/actions/runs/39520353291) passed all five jobs from source `fd242d11735a853e7432b50bf90ce6a77402e5af`. **[Download alpha10 TEST](https://github.com/JCVERSA/rea/actions/runs/39520353291/artifacts/12310289166)**: `Cantas-0.10.0-alpha10-TEST.apk`,14-day retention, authenticated GitHub access.

CI SHA-256: `15d31f7f17bddc09e2be39ad0d60bdcf2c2078397fdbab0faa6a389f1cd0b5a8` (not rehashed locally). **63 JVM tests,39 instrumentation tests per API24/API31 emulator and both runtime smoke checks pass; lint0errors/32warnings**, no suppression. Independent ID, min24/target35, no INTERNET, signature v2 and four graphics ABIs including ARMv7 verified. Evidence: `../analysis/cantas/39520353291/`; settled API31 SAF-copy report inspected. [Behavior, trust boundaries, test scope and limits](../docs/CANTAS_ALPHA_10.md).

Files only toward SAF, not recursive directories. Changed provider names/post-creation errors/hash mismatches stay uncertain, never cleaned up. Hash reread is not a provider durability or source-snapshot guarantee, and may trigger extra cloud traffic. No physical Tecno/ARM32/2GB, real SD/USB/cloud/system-picker, process-kill, full Android7–15, French/accessibility or performance proof.

## Previous verified TEST (alpha09, historical)

[CI39515597009](https://github.com/JCVERSA/rea/actions/runs/39515597009) passed all five jobs from source `46a200f76a4838c810fd64635602e58535013978`. **[Download alpha09 TEST](https://github.com/JCVERSA/rea/actions/runs/39515597009/artifacts/12308127489)**: `Cantas-0.9.0-alpha09-TEST.apk`,14-day retention, authenticated GitHub access.

CI SHA-256: `1ef1fd9ab9191732ee4cce652ce7d328cbefb4079d6621e0340549e92ce3b320` (not rehashed locally). **63 JVM tests,34 instrumentation tests per API24/API31 emulator and both runtime smoke checks pass; lint0errors/29warnings**, no suppression. Signature v2, independent ID, min24/target35, no INTERNET and four graphics ABIs including ARMv7 verified. Reports: `../analysis/cantas/39515597009/`; API31 SAF-folder collision screenshot inspected. [Creation behavior, earlier failures, corrections and limits](../docs/CANTAS_ALPHA_09.md).

Provider creation is not a transaction or portable no-overwrite guarantee against provider misbehavior/concurrent changes. Unexpected names and post-invocation failures remain uncertain; no delete/rename/rollback is attempted. Only one folder, not recursive SAF copy/move/delete. No real system-picker/SD/USB/cloud, physical Tecno, process-kill, accessibility, performance or full Android7–15 proof.

## Previous verified TEST (alpha08, historical)

[CI39467338138](https://github.com/JCVERSA/rea/actions/runs/39467338138) passed all five jobs from source `ca39af1ef6a333d72db8c58becf27e7b3d095a19`. **[Download alpha08 TEST](https://github.com/JCVERSA/rea/actions/runs/39467338138/artifacts/12285643184)**: `Cantas-0.8.0-alpha08-TEST.apk`,14-day retention, authenticated GitHub access.

CI SHA-256: `5a605e6527b279a1af20e843fbad8ff95ac6c2b141d138e83bcc86dd65ae3d14` (not rehashed locally). **63 JVM tests,29 instrumentation tests per API24/API31 emulator and both runtime smoke checks pass; lint0errors/28warnings**, no suppression. Signature v2, independent ID, min24/target35, no INTERNET and four graphics ABIs including ARMv7 verified. Reports/captures: `../analysis/cantas/39467338138/`; API31 favorites screenshot inspected. [Scope, earlier CI failures, correction and limits](../docs/CANTAS_ALPHA_08.md).

Folder favorites are metadata, not access grants or file renames. Revoked favorites remain saved; restoration proves tree membership. Explicit reset touches favorites/sort/hidden only. Fresh-store/ViewModel and Activity recreation tests do not prove forced process-kill recovery, OEM transfer, real SD/USB/cloud or physical Tecno behavior. No file favorites, manual order/sync/export or final parity claim.

## Previous verified TEST (alpha07, historical)

[CI39079535597](https://github.com/JCVERSA/rea/actions/runs/39079535597) passed all gates from source `f79d67b4eac0674c0bb03ed218c0c071a09c5da3`. **[Download alpha07 TEST](https://github.com/JCVERSA/rea/actions/runs/39079535597/artifacts/12118436850)**: `Cantas-0.7.0-alpha07-TEST.apk`,14-day retention.

CI SHA-256: `b46a826221996bb603223dbfce6b09fdf72dae522fe1a60075ceb16b8d7dc002`. 55 JVM tests and22 instrumentation tests per API24/API31 emulator passed, as did both runtime smoke checks. Lint **0 errors,22 warnings**, not suppressed. Real read-only URI grants, known/unknown pipe import, cancellation of blocking provider preparation, revocation/forged-tree refusal and no-merge collision are tested. Reports/captures: `../analysis/cantas/39079535597/`; [proof boundaries and limits](../docs/CANTAS_ALPHA_07.md).

The user requests completion through the final version. [Final acceptance gates](../docs/CANTAS_FINAL_ACCEPTANCE.md) remain open; this alpha does not satisfy them. No physical Tecno/ARM32/2GB, SD/USB, cloud account, French UI/accessibility/performance or full Android7–15 runtime claim.

## Previous verified TEST (alpha06, historical)

[CI38986939467](https://github.com/JCVERSA/rea/actions/runs/38986939467) passed all gates from source `c801738d100aa0c41f42ae659f0cbd89554730dd`. **[Download alpha06 TEST](https://github.com/JCVERSA/rea/actions/runs/38986939467/artifacts/12083586993)**: `Cantas-0.6.0-alpha06-TEST.apk` in the ZIP,14-day retention.

CI SHA-256: `120db2589605a1c7818f6cdc0712275ff3bb5d7ccadef7d89ddf511e13f3d900`. 46 JVM tests,18 instrumentation tests per API24/API31 and both runtime smoke checks passed; lint **0 errors,20 warnings**, no suppression. Folder-copy/empty-dir/collision UI and native folder/journal/link-refusal tests are included. [Exact scope, captures and limitations](../docs/CANTAS_ALPHA_06.md); reports `../analysis/cantas/38986939467/`.

No merging, overwrite or automatic cleanup. Planned entries are not an immutable source snapshot; directory creation does not mean every child completed. Partial files/directories remain. Plan metadata is capped, but `File.listFiles()` still allocates the immediate directory in full; no bounded-total-memory guarantee. Hostile ancestor races, background process survival and multi-instance journaling are not solved. No physical Tecno/ARM32/2GB, French UI, performance or full Android7–15 validation claimed.

## Previous verified TEST (0.5.1-alpha05 maintenance, historical)

[CI38982940731](https://github.com/JCVERSA/rea/actions/runs/38982940731) passed all delivery gates from source `9e72b3af0f3ca6c81640b935c1c795ae9a2bbb50`. **[Download 0.5.1-alpha05 TEST](https://github.com/JCVERSA/rea/actions/runs/38982940731/artifacts/12081952940)**: `Cantas-0.5.1-alpha05-TEST.apk` inside the ZIP,14-day retention.

CI SHA-256: `b67e8a7bab353049f213713374b5536d9f92efcf6fcbd9f5a7675a9128a83912`. 36 JVM tests,15 instrumentation tests per API24/API31 emulator and both runtime smoke checks passed; lint **0 errors,20 warnings**, no suppression. Legacy and modern backup exclusions are explicit; this is not an OEM backup/transfer test. The growing-source regression is tested in JVM and against the real native descriptor adapter. [Maintenance scope and exact proof boundaries](../docs/CANTAS_ALPHA_05_1.md). Reports: `../analysis/cantas/38982940731/`.

## Previous verified TEST (0.5.0-alpha05, historical)

[CI38982114644](https://github.com/JCVERSA/rea/actions/runs/38982114644) passed all delivery gates from source `d58c88ed957d714f1bd49e7658925e63ba71849c`. **[Download alpha05 TEST](https://github.com/JCVERSA/rea/actions/runs/38982114644/artifacts/12081244398)**: `Cantas-0.5.0-alpha05-TEST.apk` inside the ZIP, 14-day retention.

CI SHA-256: `5e3f717ffc635f05e24c734c716fe2117b8513a2384b546972914f15cff9f3c5`. 31 JVM tests, 14 instrumentation tests per API24/API31 emulator and both runtime smoke checks passed; lint **0 errors, 21 warnings**, not suppressed. APK v2 signature and all four native graphics-path ABIs verified. Reports/copy/collision captures: `../analysis/cantas/38982114644/`. [Alpha05 delivery, earlier API31 test failure and limitations](../docs/CANTAS_ALPHA_05.md).

Copy only regular local files, never overwrite existing names. Incomplete/unverified outputs remain under their original names and must be inspected manually; acknowledging the report deletes no user file. No source snapshot/hash guarantee, hostile-ancestor race defense, process-surviving background service, automatic resume or multi-instance journal guarantee. No physical Tecno/ARM32/2GB, French UI, forced process-kill, real disk-full, OEM transfer, full Android7–15, accessibility or performance validation claimed.

## Previous verified TEST (alpha04, historical)

[CI38971024911](https://github.com/JCVERSA/rea/actions/runs/38971024911) passed every delivery gate from source `510a7abae23b67ba093d0ea30acd14be6f0bdf58`. **[Download alpha04 TEST](https://github.com/JCVERSA/rea/actions/runs/38971024911/artifacts/12076543358)**: `Cantas-0.4.0-alpha04-TEST.apk` in the ZIP, 14-day retention.

CI SHA-256: `2e0f76ab9771204cc14af0b239839fc5a28a9824eccc8a8b14ef452990e0f27e`. 21 JVM tests, ten instrumentation tests on each emulator and both synthetic runtime smoke checks passed. Lint: **0 errors, 19 warnings**, including a plural-resource suggestion for the selection counter; no suppression. APK v2 signature and all four native graphics-path ABIs verified; legacy WRITE is checked to remain capped at API29.

Reports and selection/creation captures: `../analysis/cantas/38971024911/`. See [alpha04 notes](../docs/CANTAS_ALPHA_04.md) and [operation rules and limits](../docs/CANTAS_OPERATIONS.md). Earlier API31 theme-test failures and one runner failure are preserved in the notes. No physical ARM32/2Go, performance, French UI, accessibility, full Android7–15 or real SD/USB claim.

## Previous verified TEST (alpha03, historical)

[CI38956279066](https://github.com/JCVERSA/rea/actions/runs/38956279066) passed all delivery gates from source `8d5ea9fb23a642a460ed0f8dae313533f46358ea`. **[Download alpha03 TEST](https://github.com/JCVERSA/rea/actions/runs/38956279066/artifacts/12069894627)**: `Cantas-0.3.0-alpha03-TEST.apk` inside the ZIP (14-day retention).

SHA-256 recorded in CI: `6ba181f7c0d24d35675b06c5415b38826d87c4f556b99831dd1f9f2131d71075`. Fifteen JVM tests, eight instrumentation tests per emulator and both local synthetic smoke checks pass. Lint: **0 errors, 18 warnings**, including new KTX URI-conversion suggestions; no suppression or dependency upgrade. APK v2 signature verifies, ARMv7/ARM64/x86/x86_64 native coverage remains present and the fixture provider is absent from the delivered manifest.

Reports/captures: `../analysis/cantas/38956279066/`. See [alpha03 delivery notes and manual SD/USB checklist](../docs/CANTAS_ALPHA_03.md) for earlier failed attempts and exact proof boundaries. The SAF fixture uses real Android URI grants and cursors, not real SD/USB hardware. No system-picker end-to-end, full process restart, Tecno/ARM32 runtime, French UI runtime, accessibility, full Android7–15 or performance verification claimed.

## Previous verified TEST (alpha02, historical)

[CI38681612415](https://github.com/JCVERSA/rea/actions/runs/38681612415) passed all delivery gates from source `8f24ff96246c62a6dc987a709a3069efb5c1d327`. **[Download alpha02 TEST](https://github.com/JCVERSA/rea/actions/runs/38681612415/artifacts/11948193347)**: `Cantas-0.2.0-alpha02-TEST.apk` inside the ZIP (14-day retention).

SHA-256 recorded in CI: `25704b7607a540c03958f87ad10e0219b6c716025102380c5c7845af71f6c4ad`. Ten JVM tests pass, three instrumentation tests pass on each emulator, and both synthetic runtime checks pass. Lint: **0 errors, 12 warnings**, including one unused sort-label resource, policy/target/dependency notices, extraction-rule configuration and KTX suggestions. Warnings remain visible; no automatic dependency upgrades or suppressions.

APK signature v2 verifies; packaged native graphics-path library has ARMv7, ARM64, x86 and x86_64 variants. Reports and actual captures: `../analysis/cantas/38681612415/`. No physical Tecno, ARM32 Android12, French UI, complete Android7–15, accessibility, external-viewer end-to-end or performance validation claimed. FR/EN resources exist; that is not a translated-UI runtime test.

See [alpha02 delivery notes](../docs/CANTAS_ALPHA_02.md). [Alpha01 notes](../docs/CANTAS_ALPHA_01.md) and its evidence remain preserved. An earlier alpha02 run38680800822 failed the API24 emulator-runner stage without an established cause; the subsequent intermediate38680969710 and final38681612415 runs passed both emulator jobs. Only the final source/run above is this delivery.
