# Static analysis and reconstruction plan

## Scope and provenance

Analysis performed on the APK in this repository, without executing or installing it. Tools: JADX 1.5.6 and Apktool 3.0.3, executed in GitHub Actions. The linked `morluto/rea` project was reviewed for tool applicability; its MCP server was not installed or used. Direct JADX/Apktool cover this initial Android static-analysis task.

| Property | Observed value / evidence |
| --- | --- |
| Display name | File Manager + (`apktool/res/values/strings.xml`, `app_name`) |
| Package | `com.alphainventor.filemanager` (`apktool/AndroidManifest.xml`) |
| Version | 3.8.3 / code 2103083 (`apktool/apktool.yml`) |
| Android SDK | Minimum 21; target 36 (`apktool/apktool.yml`) |
| Application | `com.alphainventor.filemanager.FileManagerApp` (manifest) |
| Input bytecode | Five DEX files; no top-level `lib/` native entries in the APK ZIP |
| SHA-256 | `2e0e74887d457d2c67c68045f6c3fba9a218facadeb67b8057d6b1e1389352c2` |
| Decompiled output | 15,436 Java files in total; 140 in the named application package |
| Limitations | JADX exit 3, 45 errors; obfuscation and incomplete method recovery |

Paths below are relative to `decompiled/`. Java evidence uses `jadx/sources/`; the complete archive also contains namespaces not expanded in Git.

## Important: repackaging indicators

`jadx/sources/com/zx/cmz/Strings.java` contains a LITEAPKS title, a promotional message for modded apps/games, and a link to `https://liteapks.com/app.html`. `com/zx/LB.java` implements a promotional dialog. `com/alphainventor/filemanager/activity/MainActivity.java` calls both `Xpk8a.StartGame(this)` and `LB.ll(this)` during startup (lines 2345–2346 in this export).

These are concrete repackaging indicators. They do **not** by themselves establish malware, explain every change, or prove premium entitlements. The filename's “Premium” label is not verification. The default-package `Xpk8a` helper in `all-java-sources.zip` opens an HTTP connection to `https://update.9mod.com/<package>.txt` and parses update URL/version/force/cancel fields. This is static code evidence of a third-party update-check mechanism; the endpoint was not contacted and its current contents/behavior are unknown. Do not trust the input as an official reference build. No upstream signed APK comparison, signature provenance verification or dynamic security audit has been performed.

## Architecture and feature map

The inspected UI is native Android Views/XML, not a web app. `activity_main.xml` uses a DrawerLayout, ConstraintLayout, toolbar/fragment containers, bottom paste controls, playback controls and a Material TabLayout. Java uses AndroidX fragments, RecyclerView/ListView and custom widgets. Significant internals are renamed to short or generated names, including `ax.*` imports.

| Area | Evidence / entry points | What this tells us |
| --- | --- | --- |
| App initialization | `com/alphainventor/filemanager/FileManagerApp.java` | Application-level initialization entry point |
| Browser/navigation | `activity/MainActivity.java`, `apktool/res/layout/activity_main.xml` | Main window, drawer, fragment host, playback and paste controls |
| File backends | `file/AbstractC6238m.java`, `file/AbstractC6239n.java`, `file/C6232g.java`, `file/C6233h.java` | File abstractions and Java IO/DocumentFile-related code; exact contracts need tracing |
| Archive handling | `activity/ArchiveActivity.java`, `file/C6227b.java`, `res/layout/activity_archive.xml` | Archive UI and ZipFile-related implementation |
| Cloud | `file/OneDriveFileHelper.java`, `file/C6236k.java`, `file/C6229d.java`, `file/I.java` | OneDrive, Dropbox, Box and pCloud integration references |
| Network storage | `file/L.java`, `file/M.java`, `file/N.java`, `file/O.java`, `file/P.java`, `file/Q.java`, `file/W.java` | SFTP/SSH, SMB/jcifs and WebDAV references |
| Device sharing | `service/FtpServerService.java`, `service/HttpServerService.java` | Local FTP and HTTP server service entry points |
| File/background jobs | `service/CommandService.java`, `ScanService.java`, `FileObserverService.java` | Background commands, scans and monitoring |
| Recycle bin/settings | `res/xml/settings.xml`, `file/J.java`, `file/K.java` | Recycle-bin preferences and implementation references |
| Text/image/video/music | `texteditor/TextEditorActivity.java`, `viewer/*`, `musicplayer/*`, `res/layout/activity_texteditor.xml` | Built-in editor and viewers/players |
| Favorites and sharing | `bookmark/BookmarkProvider.java`, `provider/MyFileProvider.java`, `sharing/QuickShareProvider.java` | Internal providers and file-sharing surfaces |
| Privileged access | `shizuku/ShizukuUserService.java`, manifest Shizuku permission | Optional Shizuku integration |
| Monetization/analytics | `activity/PaymentActivity.java`, `license/*`, `ads/*`, billing/ad/Firebase manifest components | Bundled payment, advertising and telemetry components remain present |
| Repackaging layer | `com/zx/*`, `defpackage/Xpk8a.java` in the complete archive | Additional startup/promotion code requiring separate review |

Java paths in the table omit the common prefix `jadx/sources/com/alphainventor/filemanager/`, except explicitly qualified external namespaces. Resource paths omit `apktool/` where obvious. Backend associations are static references, not confirmation that remote services still work.

## Permissions and integration implications

The manifest declares all-files access, legacy external-storage permissions, package enumeration, install/delete-package requests, usage statistics, Internet/network state, notifications, foreground media/data services and Shizuku access. Android OS and store policies govern whether these can actually be granted. Several activities are exported, while the app's FTP/HTTP/command services and file providers are declared non-exported; any modification should audit exported intent input handling and URI grants rather than copying manifest settings blindly.

Cloud OAuth redirects, application IDs, provider authorities, billing, Firebase configuration, and some SDK integrations are bound to package names, signing certificates and/or external accounts. An independent build needs its own configuration. No credentials or original signing key were recovered or are required for analysis.

## What can be modified?

### Small authorized modifications: Apktool + smali

Best candidates: strings, translations, colors, icons, XML layouts and preference defaults. The decoded resources are directly inspectable. Restore `apktool-bytecode.zip` into the decoded tree before attempting a build. Behavior changes generally require smali edits, especially where JADX output is incomplete. Keep the original APK unchanged, work on a copy, rebuild, align, sign with a development key, and test in an isolated emulator.

Tradeoffs: decompiled XML is not original authored XML; resource recompilation can fail; signing changes prevent normal in-place updates; integrations can break. We have not attempted a rebuild or shipped any modified APK. No premium/billing bypass has been implemented.

### Larger changes or recreation: new Android project (recommended)

Use the recovered UI structure and behavior as reference, subject to your rights, not as a promise that the decompiled Java can compile. Prefer an independently maintained Kotlin project with original branding/assets and explicitly selected open-source dependencies.

Suggested slices:

1. **Local-browser MVP:** storage picker/permissions, directory list/grid, breadcrumbs, selection, sort and search; safe copy/move/rename/delete with progress, cancellation and conflict handling.
2. **Reliability:** recycle/restore, persisted document grants, error recovery, large-file handling, process-death recovery, storage-full tests and scoped-storage tests.
3. **Utilities:** text editor, image/media viewers, ZIP browsing/extraction, favorites/history and storage summaries.
4. **Remote storage:** add one backend at a time behind a common interface; separate authentication from file operations; use your own OAuth app registrations and safe credential storage.
5. **Advanced access/sharing:** optional Shizuku and local server modes, explicit user consent, authentication, scoped access and lifecycle tests.

Native Android is the better match for on-device file access. A browser recreation could imitate the interface, but would not offer equivalent Android storage permissions, package management or background integrations.

## Verification status

- Successful CI decode and source export; manifest and resource XML inspected locally.
- Input fingerprint retained; all Java and smali preserved in compact archives. Both ZIP CRC checks passed; all decoded XML files parsed successfully.
- Not a recovered Gradle project: original source comments, local variable names, build scripts, signing key and reliable unobfuscated class names are absent.
- No emulator/device run, rebuild/sign/install test, cloud login, premium-status verification or comprehensive security review.
- Recovered source is reference material. Smali/original bytecode is the fallback for methods that JADX could not recover correctly.
