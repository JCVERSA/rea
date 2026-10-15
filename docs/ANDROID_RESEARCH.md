# Recherche technique — reverse engineering Android

Recherche effectuée le 14 octobre 2026 (America/Los_Angeles ; 15 octobre UTC). Les sources consultées sont des README, documents et fichiers d’implémentation obtenus via l’API GitHub ; les URL externes non accessibles depuis ce sandbox sont indiquées comme références complémentaires, pas comme pages consultées. Révisions des dépôts : [`research-sources.json`](research-sources.json).

## 1. Méthode adaptée à ce dépôt

```text
base.apk immuable + SHA-256
  ├─ ZIP : DEX, assets, archives imbriquées et ELF
  ├─ Apktool : manifeste, ressources et smali complets
  ├─ JADX CLI : Java lisible + diagnostics conservés
  └─ REA : métadonnées, méthode choisie, références et Evidence liée au hash
       ↓
Triangulation : manifeste original / Java / smali / ressources
       ↓
Diagnostic installation sur appareil (ultérieur, non effectué)
       ↓
Reconstruction sans modification → signature stable de test → validation
       ↓
Petits patches contrôlés → tests → seulement ensuite refonte UI
```

Une sortie Java n’est pas le code source original. Les noms produits par deux versions/configurations de JADX peuvent différer : pour identifier une méthode, conserver le descripteur DEX, le nom brut et le hash de l’APK. Les références statiques ne recensent pas toutes les invocations réflexives/dynamiques.

## 2. Outils retenus et alternatives

| Projet | Utilité ici | Utilisation / limites |
| --- | --- | --- |
| [skylot/jadx](https://github.com/skylot/jadx) | Lire et rechercher le code DEX | **Utilisé : 1.5.6**. Son README avertit explicitement que la décompilation n’est généralement pas complète ; conserver les erreurs. |
| [iBotPeaches/Apktool](https://github.com/iBotPeaches/Apktool) | XML/resources, smali, reconstruction ultérieure | **Utilisé : 3.0.3** pour décoder. Un decode réussi ne prouve pas qu’un rebuild ou une installation réussira. |
| [morluto/rea](https://github.com/morluto/rea) | Orchestration CLI/MCP, hash, provenance et limites explicites | **Utilisé : 6.5.0 CLI**, pas seulement lu. Ce n’est pas un moteur remplaçant JADX/Apktool. |
| [1013503897/jadx-headless-mcp](https://github.com/1013503897/jadx-headless-mcp) | Moteur headless et métadonnées pour REA | **Utilisé : 0.7.1**, JAR vérifié avec le digest publié par REA. JDK complet requis. |
| [google/smali](https://github.com/google/smali) | Comprendre/assembler le DEX et ses registres | Référence consultée, pas installé séparément. Le README explique le fork Google et le changement de namespace. L’ancien [JesusFreke/smali](https://github.com/JesusFreke/smali) est archivé : ne pas supposer qu’il reste la source maintenue. |
| [OWASP/mastg](https://github.com/OWASP/mastg) | Méthodologie de reverse/security testing | Documents Android consultés ; tests appliqués seulement quand leur prérequis existe. Pas une preuve automatique de vulnérabilité. |
| [google/bundletool](https://github.com/google/bundletool) | Comprendre AAB/APKS, splits et sélection par appareil | Référence consultée, **non exécuté**. Ne fabrique pas les splits absents à partir d’un base.apk incomplet. |
| [frida/frida-core](https://github.com/frida/frida-core) | Instrumentation et observation de méthodes au runtime | Référence consultée, **non installé/exécuté**. Version/ABI compatibles et appareil autorisé indispensables. |
| [MobSF](https://github.com/MobSF/Mobile-Security-Framework-MobSF) | Triage automatisé statique/dynamique | README consulté, **non déployé**. Résultats à vérifier manuellement ; éviter d’envoyer un APK privé à une instance publique. |
| [Ghidra](https://github.com/NationalSecurityAgency/ghidra) | Analyse ELF native ARM/x86 | README consulté, **non installé/exécuté**. Pertinent pour les `.so` imbriqués ; inutile de lancer une analyse native sur chaque classe Java. Vérifier les avis de sécurité avant installation. |

Les licences indiquées par GitHub figurent dans le catalogue JSON ; elles ne remplacent pas une vérification des LICENSE de chaque version. Les outils n’accordent aucun droit de redistribution sur l’application étudiée.

## 3. Fichiers et techniques OWASP réellement consultés

- [MASTG-TECH-0017 — Decompiling Java Code](https://github.com/OWASP/mastg/blob/master/techniques/android/MASTG-TECH-0017.md) : repérer toutes les DEX ; ne pas limiter la logique applicative à une seule classe ou au code Java.
- [0018 — Disassembling Native Code](https://github.com/OWASP/mastg/blob/master/techniques/android/MASTG-TECH-0018.md) : JNI, ELF et Bionic ; inspection séparée des bibliothèques natives.
- [0019 — Retrieving Strings](https://github.com/OWASP/mastg/blob/master/techniques/android/MASTG-TECH-0019.md) : utiliser les chaînes comme pistes, pas comme verdicts de comportement.
- [0020 — Retrieving Cross References](https://github.com/OWASP/mastg/blob/master/techniques/android/MASTG-TECH-0020.md) : relier les pistes aux appelants et aux méthodes concernées.
- [0021 — API Usage](https://github.com/OWASP/mastg/blob/master/techniques/android/MASTG-TECH-0021.md) et [0022 — Network Communication](https://github.com/OWASP/mastg/blob/master/techniques/android/MASTG-TECH-0022.md) : identifier les API/points réseau à examiner ; leur présence seule n’est pas une faille.
- [0023 — Reviewing Decompiled Java Code](https://github.com/OWASP/mastg/blob/master/techniques/android/MASTG-TECH-0023.md) : reconnaître l’obfuscation et ne pas confondre code reconstitué et source authored.
- [0024 — Reviewing Disassembled Native Code](https://github.com/OWASP/mastg/blob/master/techniques/android/MASTG-TECH-0024.md), [0025 — Automated Static Analysis](https://github.com/OWASP/mastg/blob/master/techniques/android/MASTG-TECH-0025.md) : sélectionner l’outil en fonction de la couche et vérifier les résultats automatiques.
- [0026 — Dynamic Analysis on Non-Rooted Devices](https://github.com/OWASP/mastg/blob/master/techniques/android/MASTG-TECH-0026.md) : l’ajout d’un gadget implique repackaging et nouvelle signature ; ce n’est pas une observation passive du binaire original.
- [0027 — Open Files](https://github.com/OWASP/mastg/blob/master/techniques/android/MASTG-TECH-0027.md), [0028 — Open Connections](https://github.com/OWASP/mastg/blob/master/techniques/android/MASTG-TECH-0028.md), [0029 — Loaded Native Libraries](https://github.com/OWASP/mastg/blob/master/techniques/android/MASTG-TECH-0029.md), [0030 — Sandbox Inspection](https://github.com/OWASP/mastg/blob/master/techniques/android/MASTG-TECH-0030.md), [0031 — Debugging](https://github.com/OWASP/mastg/blob/master/techniques/android/MASTG-TECH-0031.md) : future observation runtime, sous réserve des permissions/root/debuggability. **Non exécutées ici.**

## 4. REA : intégration concrète, pas une mention décorative

Documents consultés sur la révision `084e540414f96dc5b93cac4ddf36ce71a0f6dd23` :

- [`docs/android-analysis.md`](https://github.com/morluto/rea/blob/084e540414f96dc5b93cac4ddf36ce71a0f6dd23/docs/android-analysis.md)
- [`docs/apktool-resource-analysis.md`](https://github.com/morluto/rea/blob/084e540414f96dc5b93cac4ddf36ce71a0f6dd23/docs/apktool-resource-analysis.md)
- [`docs/adb-device-analysis.md`](https://github.com/morluto/rea/blob/084e540414f96dc5b93cac4ddf36ce71a0f6dd23/docs/adb-device-analysis.md)
- [`docs/frida-instrumentation.md`](https://github.com/morluto/rea/blob/084e540414f96dc5b93cac4ddf36ce71a0f6dd23/docs/frida-instrumentation.md)
- [`src/android/JadxRelease.ts`](https://github.com/morluto/rea/blob/084e540414f96dc5b93cac4ddf36ce71a0f6dd23/src/android/JadxRelease.ts) : version, révision et SHA-256 du moteur fourni.

Le workflow installe le package npm `rea-agents@6.5.0` avec scripts d’installation désactivés. Il fournit explicitement un JDK, le JAR headless et un launcher Apktool. REA n’installe pas ces moteurs à notre place et demande `ps` compatible procps sur Linux. Aucun service MCP persistant ou configuration personnelle d’agent n’a été modifié.

Opérations effectuées, dont les JSON complets sont dans `analysis/rea/` :

```sh
rea inspect-apktool-client
rea inspect-android-package /chemin/base.apk
rea decode-android-resources /chemin/base.apk --no-strings
rea search-android-classes /chemin/base.apk MainActivity
rea inspect-android-method /chemin/base.apk com.alphainventor.filemanager.activity.MainActivity onCreate
rea trace-android-references /chemin/base.apk com.alphainventor.filemanager.activity.MainActivity --method-name onCreate
```

Chaque lane conserve le code de retour et stderr. Les six commandes ont retourné `0` et leurs enveloppes JSON `ok: true` dans cette analyse. La méthode `onCreate` est déclarée disponible, représentation Java, sans fallback ; cela ne garantit pas toute l’application. La lane références rapporte zéro appelant explicite de `onCreate` : **cela ne signifie pas que la méthode est inutilisée**, car Android appelle les méthodes de lifecycle via son framework.

Les inventaires REA/JADX headless et le nombre de fichiers Java JADX CLI ne sont pas des unités identiques ; ne pas comparer naïvement leurs totaux.

## 5. Diagnostic avancé d’installation : récupérer le vrai motif

Source Android consultée : [`PackageManager.java`](https://github.com/aosp-mirror/platform_frameworks_base/blob/master/core/java/android/content/pm/PackageManager.java), notamment `INSTALL_FAILED_NO_MATCHING_ABIS`, `INSTALL_FAILED_OLDER_SDK`, `INSTALL_FAILED_UPDATE_INCOMPATIBLE`, `INSTALL_FAILED_MISSING_SPLIT`, `INSTALL_FAILED_INVALID_APK`.

L’implémentation [`NativeLibraryHelper.java`](https://github.com/aosp-mirror/platform_frameworks_base/blob/master/core/java/com/android/internal/content/NativeLibraryHelper.java) distingue explicitement l’absence de code natif et l’absence d’ABI compatible. Le message générique de l’installateur constructeur ne permet pas à lui seul de choisir un de ces motifs.

Collecte **en lecture**, sur un appareil personnel connecté via ADB :

```sh
adb devices -l
adb shell getprop ro.build.version.sdk
adb shell getprop ro.product.cpu.abilist
adb shell getprop ro.product.cpu.abilist32
adb shell getprop ro.product.cpu.abilist64
adb shell dumpsys package com.alphainventor.filemanager
adb shell pm path com.alphainventor.filemanager
```

Ne publier que les propriétés utiles, pas un bugreport/logcat intégral contenant comptes et données privées. Aucun appareil ADB n’est connecté à ce sandbox.

Pour obtenir un verdict d’installation, une tentative contrôlée `adb install base.apk` peut produire le code exact ; **cette commande modifie l’appareil** et n’a pas été exécutée ici. Choisir la stratégie de remplacement/sauvegarde avant toute tentative ; ne pas utiliser aveuglément `uninstall`, `-r` ou `-d` pour faire disparaître une erreur.

| Verdict réel | Suite logique |
| --- | --- |
| `NO_MATCHING_ABIS` | Examiner toutes les bibliothèques réellement empaquetées et les ABIs du système ; ne pas simplement renommer un fichier ou une architecture. |
| `OLDER_SDK` | Comparer minSdk au SDK réel. Abaisser un nombre ne rend pas les API du code disponibles. |
| `UPDATE_INCOMPATIBLE` | Vérifier les certificats et le paquet installé ; ne pas détruire les données pour masquer un conflit. |
| `MISSING_SPLIT` | Extraire le jeu complet avec `pm path`/outils appropriés ; un fichier nommé base.apk n’est pas une preuve de complétude. |
| `INVALID_APK` / parsing/certificats | Vérifier téléchargement, SHA-256, structure, manifeste et signature avec les outils Android. |

Dans ce nouvel APK, `lib/` est vide mais `assets/exolibs.zip` contient huit ELF pour quatre ABIs, dont ARMv7 32 bits. Leur présence indique des variantes disponibles, **pas une validation du chargeur JNI ou de la lecture média**. Le chargeur est à examiner dans `sources/ax/p073f2/c.java` de l’archive Java.

## 6. Smali et ressources : stratégie de patch sûre

- Utiliser les sources lisibles pour comprendre et le smali pour vérifier l’instruction exacte.
- Respecter `.locals`/`.registers`, registres `p`/`v`, types, blocs try/catch et règles `invoke-*`/`move-result*`. Références : README [google/smali](https://github.com/google/smali) et documentation DEX liée par ce README (source.android.com, non consultée dans ce sandbox).
- Ne pas modifier les identifiants `public.xml`, attributs/manifeste et autorités de providers sans analyser les dépendances.
- Conserver une copie de référence ; produire un patch minimal, vérifié sur le hash d’entrée.
- Rebuild d’abord sans modification. Signer avec une **clé stable privée** stockée hors Git, puis vérifier avec apksigner/zipalign.
- Tester installation, startup, permissions refusées, accès storage et opérations sur des fichiers jetables. L’APK doit conserver ses fichiers personnels hors de l’aire de test.
- Ne pas toucher aux droits de licence/premium. Toute modification de signature/package peut affecter OAuth, providers et intégrations.

## 7. Sécurité et limites de la recherche

Le nouvel APK déclare du trafic HTTP clair et la confiance dans les CA utilisateur. C’est une surface à analyser en contexte (WebDAV/serveurs privés, etc.), pas une vulnérabilité démontrée par la déclaration seule. Les composants exportés, URI grants, extraction d’archives, chemins de fichiers, serveurs locaux et stockage des tokens sont les prochains objets d’un audit ciblé.

Aucun audit CVE complet, benchmark, test réseau, test de fichiers, signature originale/authenticité, root, hooking ou appareil physique n’a été réalisé. MobSF/Frida/Ghidra restent des options documentées, non des contrôles déjà passés. On ne peut pas conclure que le fichier est officiel ou sûr à partir de l’absence d’anciens noms de hooks.

**Recommandation :** commencer par le diagnostic d’installation du nouvel APK et une reconstruction sans changement ; adopter ensuite des petits patches de Views/XML. Ne pas empiler des frameworks/instrumentations tant que la base n’est pas validée.
