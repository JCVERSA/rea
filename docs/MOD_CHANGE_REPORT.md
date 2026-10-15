# Milestone 1 change report

STATUS: IMPLEMENTED — NOT FULLY VERIFIED

## What Changed

Removed exactly two injected startup invocations in a disposable decoded tree: `Xpk8a.StartGame(Context)` and `com.zx.LB.ll(Activity)`. The original APK, decompilation and bytecode archives remain untouched. Dormant injected helper classes/resources remain; this is entry-point removal, not complete deletion of every third-party byte.

## Why

Disable the identified promotional-dialog and third-party-update startup paths with the smallest behavior change before redesigning the UI. This does not establish that the repackaged APK is fully trustworthy.

## Files Modified / Added

- `scripts/prepare_mod.py`: input hash check, isolated extraction, exact patch and JSON report.
- `scripts/test_mod.py`: four regression tests including known external caller inventory.
- `scripts/verify_built_mod.py`: round-trip checks against the actual assembled APK.
- `.github/workflows/build-mod.yml`: rebuild, ephemeral signing, signature/alignment verification, artifact and public evidence export.
- `patches/remove-third-party-startup.patch`: reviewable zero-context smali diff; preparation script applies the equivalent checked transformation automatically.
- `docs/MOD_BUILD.md`: reproduction, signing, installation and device-test guidance.
- `docs/builds/latest/*`: real CI output, not simulated test results.

## Benefits

Reproducible minimal modification, no new runtime dependency, no source-reference edits, build failures propagated through shell pipelines, and a signed test artifact with retained verification evidence.

## Risks

Dormant injected code remains. Reflection/dynamic entry paths have not been comprehensively audited. Changing the signing certificate can break integrations or runtime checks. Successful assembly does not imply successful startup or safe file operations.

Apksigner reports warnings for `META-INF` entries not covered by the v1/JAR signature. The full warnings are retained in `signature-verification.txt`; v2 and v3 verification both succeed. These metadata/service files are deliberately preserved rather than blindly deleted, as some are used for runtime service discovery. This is not a claim of complete security validation.

## Compatibility Impact

Existing package, permission declarations, component contracts and settings are retained. The current interface and premium/licensing behavior are not intentionally changed. The requested Android 7–15 range has not been tested on devices.

A different signing key normally prevents in-place replacement. The generated test key is ephemeral: subsequent builds have different certificates, so a stable secret-managed signing key is required before expecting update continuity. No private key/password is committed or uploaded.

## Validation

Evidence run: https://github.com/JCVERSA/rea/actions/runs/38660476934

Source commit: `809e703e379c825b068ea9145e0378b8508bc4c0`

Artifact: `file-manager-cleaned-test-2` (14-day retention)

Build: PASS — Apktool assembly completed with pipeline error propagation enabled.

Tests: PASS — 4 regression tests; reference fingerprint, archive CRCs and 1,594 XML parses; rebuilt-APK round-trip assertions passed.

Signature/alignment: PASS — apksigner verifies v1/v2/v3; zipalign check succeeds. See retained warnings above.

Lint: NOT RUN — no configured linter. Python syntax compilation passed locally.

Type Check: NOT RUN — no configured type checker.

Runtime: NOT RUN — no emulator/device execution performed.

Security: PARTIAL — checked input fingerprint, direct caller inventory, hook absence after reassembly and unchanged declared permission/component surfaces; not a comprehensive APK audit.

APK SHA-256: `4c9f57bf09d1d25faaeabad1c3412890597b984a4f58fd3b58500e186d8c0a4d`.

The sandbox cannot download GitHub Actions blob-hosted artifacts/log archives. CI evidence was committed to this branch for inspection; the user can download the artifact from the run page. No APK is stored in Git.

## Remaining Issues / Next Gate

Test cold start and disposable-file operations on an isolated Android device before any UI changes. Confirm absence of the dialog in real use and inspect startup/network behavior. Then begin the Views/XML home/navigation redesign using the SwiftSlate visual/motion reference. No UI redesign is included in this milestone.
