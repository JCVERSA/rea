# Nouvelle base — résultats et limites

> Analyse initiale. Voir désormais [l’audit approfondi](FULL_STATIC_AUDIT.md) pour les contrôles v1/CMS, la rectification du faux positif provider AUD-05 et les risques réseau/stockage. Le démarrage sur Tecno BF6 Android 12/32 bits a depuis été confirmé par l’utilisateur, pas observé par ADB.

## Périmètre et preuve

Le dépôt courant a été réinitialisé autour de **`base.apk`** à la demande de l’utilisateur. L’ancien APK, le pipeline de mod/signature, les patches et leurs rapports ont été supprimés du tree ; `.git` et l’historique restent intacts. Aucun mod de l’ancien APK n’est appliqué au nouveau.

Analyse statique uniquement. Run initial : https://github.com/JCVERSA/rea/actions/runs/38662492012 ; commit d’entrée `5901819d700269b34ba6c6be0011c90395552fe5`. Les sorties proviennent de commandes réellement exécutées, pas d’un audit simulé.

| Propriété | CONFIRMÉ | Preuve |
| --- | --- | --- |
| Taille | 20 810 990 octets | ZIP / `decompiled/index.json` |
| SHA-256 | `0f7dbe666b6be1c1f99f1b324c65899ebbca6cc56fe55d3809bc7684242b762b` | Hash local et Evidence REA |
| Package | `com.alphainventor.filemanager` | Manifeste / `analysis/rea/package.json` |
| Version | **3.5.4**, code **2103054** | Apktool metadata + REA |
| SDK minimum / cible | **21 / 34** (Android 5.0 / Android 14) | Manifeste original décodé par REA / `apktool.yml` |
| DEX | `classes.dex`, `classes2.dex`, `classes3.dex` | Inventaire ZIP |
| Java CLI | **14 185 fichiers**, dont **122** dans le package applicatif nommé | Archive Java / index |
| REA headless | 14 614 classes inventoriées, 1 650 ressources | Métadonnées provider ; unités différentes du nombre de fichiers Java |
| Diagnostics JADX CLI | **38 erreurs**, code de retour **3** | `decompiled/jadx.log`, `jadx-exit-code.txt` |
| Méthode principale | `MainActivity.onCreate(Bundle)` disponible en Java, sans fallback dans REA | `analysis/rea/main-oncreate.json` |
| REA exécuté | Six opérations, retour 0, enveloppes `ok: true` | `analysis/rea/*` |

**UNKNOWN :** authenticité/signature originale, statut premium, installabilité sur le téléphone de l’utilisateur, comportement des fonctions de fichiers et performances.

## Architecture observée

```text
Application Android / FileManagerApp
  → Activities + fragments + Views/XML
  → MainActivity (DrawerLayout, toolbar, tabs, conteneur principal)
  → backends/commandes de fichiers, observateurs et services
  → Java IO / accès document/storage / fournisseurs cloud et réseau
  → viewers/players ; chargeur de bibliothèques média dans les assets
```

Les symboles obfusqués `ax.*` contiennent une partie de la logique ; les 122 fichiers du package principal ne constituent donc pas toute l’application. L’archive `all-java-sources.zip` conserve aussi ces namespaces et les dépendances. `res/layout/activity_main.xml`, les Activity sous `com/alphainventor/filemanager/activity/`, les classes `file/`, `service/`, `viewer/` et les préférences `res/xml/settings.xml` sont des points de départ pour la future refonte.

## Analyse 32/64 bits — information plus complète

**CONFIRMÉ :** aucune entrée native sous `lib/` dans le ZIP principal. Ce constat n’équivaut **pas** à « aucune bibliothèque native dans l’application ».

`assets/exolibs.zip` contient huit bibliothèques : `libex.ffmpeg.exo.so` et `libfm.ffmpeg.exo.so` pour chacune des architectures suivantes :

| Répertoire | Classe ELF | Machine ELF |
| --- | --- | --- |
| armeabi-v7a | 32 bits | ARM (40) |
| arm64-v8a | 64 bits | AArch64 (183) |
| x86 | 32 bits | i386 (3) |
| x86_64 | 64 bits | x86-64 (62) |

Preuves : `analysis/nested-native.json`, headers ELF vérifiés par `scripts/inspect_base.py`. Le code `sources/ax/p073f2/c.java` de l’archive Java ouvre `exolibs.zip` pour extraire des bibliothèques. Le chargement effectif et les dépendances dynamiques ne sont pas testés.

**RECOMMANDATION :** conserver toutes ces variantes. Ne pas ajouter une bibliothèque/gadget exclusivement ARM64 à un mod destiné à un appareil 32 bits. Obtenir le verdict exact d’installation avant de choisir une correction. Le refus du précédent build n’est pas expliqué par l’analyse de ce nouvel APK.

## Anciens hooks recherchés

La recherche insensible à la casse dans les **14 185 fichiers Java récupérés** n’a trouvé aucune occurrence des chaînes `liteapks`, `update.9mod.com`, `Xpk8a`, `com.zx.LB`. `MainActivity.onCreate` commence par la superclass et l’initialisation du layout, sans les deux appels identifiés dans l’ancien APK.

Preuve reproductible : `analysis/known-hook-scan.json` et JSON REA `main-oncreate`. Portée limitée : sources récupérées et anciens identifiants exacts. Ce n’est pas un scan de malware complet ni une preuve que le fichier est officiel.

## Point d’audit à traiter en contexte

- **Severity : MEDIUM** (surface à examiner, pas exploitation prouvée).
- **Confidence : CONFIRMED** pour la configuration ; **UNKNOWN** pour son exploitation.
- **Category :** politique réseau / TLS.
- **Location :** manifeste et `decompiled/apktool/res/xml/network_security_config.xml`.
- **Problem / Evidence :** trafic clair autorisé, confiance dans les CA système et utilisateur.
- **Impact :** certains flux peuvent accepter HTTP/CA utilisateur selon cette configuration ; aucun flux réel n’a été capturé.
- **Root Cause :** politique globale, possiblement nécessaire à certains backends réseau/serveurs privés.
- **Recommended Fix :** tracer les endpoints et fonctionnalités concernés avant de limiter la politique à des exceptions explicites.
- **Regression Risk :** une interdiction globale peut casser WebDAV/serveurs locaux ou configurations privées.

Ne pas déclarer tous les composants exportés vulnérables par leur seule présence. L’audit suivant devrait examiner leurs validations d’Intent/URI, les traversées de chemins/extractions, les serveurs FTP/HTTP et les tokens stockés.

## Vérifications exécutées

- SHA-256 du nouvel input concordant avec l’export et les Evidence REA.
- Intégrité CRC et sûreté des chemins des deux archives : **PASS**.
- Archive Java : **14 185** fichiers ; archive Apktool complète : **23 893** entrées.
- **1 245 XML** expansés parsés avec succès.
- Python : compilation syntaxique des scripts ; shell : vérification syntaxique des scripts de lancement.
- REA : package, ressources, disponibilité Apktool, recherche de classe, inspection méthode, références **exécutées**.
- Au moment de cette analyse initiale : aucun rebuild, zipalign, signature, installation, émulateur, hooking, benchmark ou test réseau effectué sur ce nouvel APK. L’avancement ultérieur est conservé dans [la phase témoin](WITNESS.md), sans patch fonctionnel.

## Ordre recommandé

1. Récupérer version Android, ABIs et verdict d’installation de `base.apk` sur l’appareil cible, sans désinstallation automatique.
2. Vérifier signature et complétude/splits avec les outils Android ; distinguer authenticité et validité cryptographique.
3. Réaliser une reconstruction **sans modification**, avec une clé privée stable hors Git.
4. Tester installation/démarrage et opérations sur fichiers jetables.
5. Seulement ensuite : petits patches, puis interface Views/XML inspirée de Material 3.

**Confirmed Problems :** récupération Java partielle (38 erreurs) ; politique réseau permissive observée.

**Likely Problems :** aucune nouvelle panne fonctionnelle n’est démontrée ; obfuscation = coût d’analyse, pas preuve de bug.

**Recommendations :** diagnostic device, reconstruction témoin, signature stable et changements incrémentaux.

**Unknown :** cause du précédent refus d’installation ; résultat d’installation du nouvel APK ; comportement runtime et authenticité.
