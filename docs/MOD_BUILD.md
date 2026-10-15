# Cleaned test APK — milestone 1

## Scope

Personal test build of the repository APK. It preserves the existing package/name, interface, file operations, permissions, billing/licensing code and integrations. This is not the Material 3 redesign yet.

Exactly two startup instructions are removed from `MainActivity.onCreate`:

```smali
invoke-static/range {p0 .. p0}, LXpk8a;->StartGame(Landroid/content/Context;)V
invoke-static/range {p0 .. p0}, Lcom/zx/LB;->ll(Landroid/app/Activity;)V
```

This disables the identified third-party update check and LITEAPKS dialog startup paths. The dormant helper classes/resources remain for this minimal baseline. Static regression tests check for additional direct callers outside those helpers, but are not a comprehensive reflection/dynamic-code security audit. No claim is made that the whole APK is trustworthy.

The reference decompilation remains unchanged. `scripts/prepare_mod.py` creates a disposable copy under `.work/`, restores the archived bytecode and applies a fingerprint-checked, fail-closed patch. It emits a patch report. Repeating the patch or changing the expected hook occurrences fails rather than silently passing.

## Build pipeline

`.github/workflows/build-mod.yml` runs on matching changes on `arena/3a37ecd7-rea` (also declares manual dispatch). It:

1. Validates reference hashes/archives/XML and runs four regression tests.
2. Uses Java 17, Apktool 3.0.3 and Android build-tools 35.0.0.
3. Builds the patched decoded tree.
4. Aligns and signs with a newly generated **ephemeral test key**.
5. Verifies the signature and alignment.
6. Decodes the signed result and checks hook absence, main initialization, package, component/permission declarations and selected settings.
7. Uploads the APK, SHA-256 and verification reports as a 14-day Actions artifact. Diagnostic uploads explicitly exclude private keys, passwords and the full work directory.

A successful assembly and static check does **not** establish that the app starts or performs file operations correctly. Read workflow results and reports before installing. No runtime/emulator validation is currently automated.

## Signing and installation warning

**Do not uninstall your existing app until the artifact is available and you are ready to test.** The original signing key is unavailable. This test APK normally cannot update the existing app in place: uninstalling first may be necessary and removes app-private settings/favorites/accounts. You indicated no app data needs preserving; nevertheless, back up any important personal files separately. Never delete shared storage to resolve an install issue.

The key is intentionally destroyed with the CI runner. Each CI run has a different certificate, so later test builds also cannot normally update this one in place. Before producing a long-lived mod, provision a stable signing key through an appropriate secret store; do not put it or its password in Git/chat. A test key is not suitable for public distribution. Changed signatures can affect cloud login, OAuth and other integrations.

No Android permissions or TLS trust settings are tightened in this milestone: doing so blindly could break network-storage features. These require a separate scoped audit.

## Manual validation checklist — Android 7 through 15

The desired range is Android 7–15; it is a target, **not a verified support claim**. Start on an isolated device/emulator without real cloud credentials and use a disposable folder:

- Install, cold-start and relaunch; verify no promotional dialog appears.
- Inspect startup logs; verify no fatal exception. Network observation is needed to confirm no unwanted traffic at runtime.
- Grant storage access deliberately; deny/revoke it and check recovery.
- Browse internal storage; open a folder; switch list/grid and sort/search.
- Create/rename/copy/move a disposable file; verify contents and destination.
- Delete/restore a disposable file using the recycle bin; test an operation cancellation/conflict.
- Open a text file and image; background/foreground and rotate.
- Repeat on available Android versions, recording device/version and failures.

Do not use important files to validate destructive operations. Cloud/SMB/SFTP/sharing functionality remains unverified until tested separately.

## Future UI milestone

Keep native Views/XML. Use SwiftSlate-ng's `Theme.kt` and `SlateMotion.kt` as design references: lavender-led Material 3 direction, light/dark AMOLED surfaces, coherent rounded shapes, deliberate motion and respect for disabled system animations. These Compose implementations are not drop-in replacements for the decoded app. Prioritize the home screen and navigation only after this baseline is validated on device.
