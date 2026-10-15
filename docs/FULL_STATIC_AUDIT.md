# Audit statique approfondi — base.apk 3.5.4

**Audit initial, amendé : AUD-05 retiré après analyse du parseur Android 12. L’utilisateur a approuvé le plan ; la première phase est une reconstruction témoin, sans patch fonctionnel.**

## Project Overview

Gestionnaire de fichiers Android natif, package `com.alphainventor.filemanager`, version 3.5.4 / 2103054, minSdk 21, targetSdk 34. Source d’analyse : **base.apk**, SHA-256 `0f7dbe666b6be1c1f99f1b324c65899ebbca6cc56fe55d3809bc7684242b762b`.

Appareil **déclaré par l’utilisateur**, non inspecté par ADB : Tecno BF6, Android 12, système 32 bits, 2 Go RAM, 64 Go stockage. L’utilisateur confirme installation et démarrage du nouvel APK, sans problème observé. Toutes les fonctions doivent être conservées. Les tests futurs seront manuels, par APK.

État initial : les contrôles ci-dessous sont ceux de l’audit statique ; les avancées de reconstruction/signature et leurs échecs sont documentés séparément dans [la phase témoin](WITNESS.md). Dix constats sont retenus, un (AUD-05) est retiré.

L’audit est large par catégories, **pas une revue exhaustive de chaque instruction des 14 185 fichiers Java**. Pendant cet audit initial, aucune application n’a été exécutée dans le sandbox et aucun appareil, serveur cloud ou endpoint de l’APK n’a été contacté. La décompilation a 38 erreurs : les chemins non récupérés restent des limites explicites.

## Evidence & Confidence

- **CONFIRMED** : configuration, prédicats et instructions observés ; intégrité des archives ; certificat v1 et contrôles CMS/JAR partiels décrits ci-dessous.
- **LIKELY** : conséquence cohérente avec ces observations mais non reproduite dans Android.
- **UNKNOWN** : exploitabilité réelle, trafic, authenticité éditeur, consommation mémoire et crash effectif.
- **RECOMMENDATION** : amélioration proposée, pas un défaut démontré.
- **USER-REPORTED** : matériel et fonctionnement du fichier sur le téléphone.

Reproduction locale, sans lancer l’APK :

```sh
python3 scripts/verify_base.py
python3 scripts/audit_static.py
python3 scripts/test_audit_static.py -v
```

`audit_static.py` utilise uniquement Python standard et OpenSSL déjà disponible. Il écrit des inventaires dans `analysis/audit/`, pas dans l’APK. Les ancres exactes (entrée ZIP, SHA-256 du source, numéro de ligne, instruction) sont dans [`source-evidence.json`](../analysis/audit/source-evidence.json). Les sources `ax/*` mentionnées plus bas sont dans `decompiled/all-java-sources.zip`, sous `sources/` ; le smali est dans `decompiled/complete-apktool.zip`.

REA 6.5.0 a été réellement exécuté dans le pipeline précédent ; cet audit réutilise ses Evidence retenues (`analysis/rea/`) et les triangule avec les exports et le manifeste binaire original. **Pas de nouvelle session REA runtime ni de prétendue analyse Frida.**

### Couverture effective

| Surface | Travail effectué | Limites |
| --- | --- | --- |
| Installation/signature | Certificat v1, CMS/SF, hash des 1 647 entrées listées ; comparaison certificat AOSP | Initialement sans apksigner ; contrôle CI v1/v2/v3 effectué ensuite (voir WITNESS.md). Appareil OEM non validé |
| Manifeste | 72 composants, 22 explicitement exportés, 31 déclarations de permissions ; attribut binaire provider vérifié | Paramètres installés et adaptations OEM inconnus |
| Démarrage/provenance | FileManagerApp, MainActivity, ApplicationReporter, anciens hooks et providers | Absence de hooks connus ≠ absence de tout ajout tiers |
| Fichiers/archives | Guards d’extraction, provider, ZIP média, flux de commandes, migration favoris | Pas de preuve de sûreté de tous les backends, pas de test de perte de données |
| Réseau/auth | Serveurs FTP/HTTP, allowlist, connexion Nextcloud/WebDAV, stockage SFTP/SMB | Aucun paquet réseau capturé ; pas de compte réel |
| Secrets | Chemins de persistance et crypto ; recherche ciblée de marqueurs | Pas de scanner exhaustif ou lecture des données privées du téléphone |
| Dépendances | 28 fichiers de versions auto-déclarées ; deux requêtes GitHub Advisory | Pas de SBOM Gradle, dépendances obfusquées/transitives non résolues |
| RAM/UI/fiabilité | Threads réseau, attente provider, garde mémoire éditeur, XML et RTL | Aucun profiler, TalkBack, rendu, benchmark ou test UI sur appareil |
| CI/outillage | Workflow d’analyse, scripts, périmètre des tokens et provenance des outils | Pas d’audit complet des moteurs tiers |

## Architecture Analysis

```text
Intents externes / saisie utilisateur / réseau local
  ↓ [frontières de confiance]
Activities exportées, callbacks OAuth, sockets FTP/HTTP
  ↓
Views/XML + fragments → contrôleurs obfusqués ax.*
  ↓
CommandService / backends de fichiers / providers / lecteurs
  ↓
Stockage Android, documents SAF, cloud/SMB/SFTP/WebDAV
  ↓
SharedPreferences (connexions), SQLite (favoris), caches, ELF média
```

Il n’y a ni frontend web central, ni backend applicatif à déployer, ni microservice à inventer. Les frontières importantes sont les Intents/URI non fiables, les sockets, les serveurs distants et les données de fichiers. Les classes du package visible n’incluent pas toute la logique. Les noms obfusqués et le couplage aux identifiants de ressources rendent une migration de framework plus risquée que des petits patches Views/XML.

## Technology Stack

- Android Views/XML, AndroidX/fragments, Material, Java/DEX récupéré ; pas un projet Gradle original.
- Services, fournisseurs de contenu, WorkManager, MediaSession et bibliothèques cloud/réseau intégrées.
- Java IO/SAF et gestion d’archives ; bibliothèques média dans `assets/exolibs.zip` pour ARMv7, ARM64, x86 et x86_64.
- SharedPreferences et SQLite local ; aucun schéma serveur à migrer.
- Outils d’analyse : JADX 1.5.6, Apktool 3.0.3, REA 6.5.0 avec moteur headless 0.7.1. Les JAR/ZIP téléchargés par CI sont vérifiés par SHA-256.

## Dependency Review

Inventaire : [`dependency-metadata.json`](../analysis/audit/dependency-metadata.json). Exemples confirmés : Google Mobile Ads **23.3.0**, Firebase Analytics **22.0.2**, Play Services Auth **21.2.0**. Ce sont des valeurs contenues dans l’APK, pas la preuve de toutes les versions compilées réellement actives.

`com/jcraft/jsch/Version.java` renvoie **`2.16`** ; ne pas transformer cette chaîne en version Maven supposée `0.2.16` sans vérifier le fork. La configuration observée contient des algorithmes modernes et `enable_strict_kex=yes` : la présence de noms d’algorithmes anciens dans le code ne prouve pas qu’ils sont négociés.

Deux requêtes exactes GitHub Advisory, Maven `com.google.android.gms:play-services-ads@23.3.0` et `com.google.firebase:firebase-analytics@22.0.2`, ont retourné des listes vides. Preuve : [`advisory-lookup.json`](../analysis/audit/advisory-lookup.json). **Ce n’est pas un verdict « sans CVE »** : couverture limitée de la base, coordonnées auto-déclarées et dépendances indirectes non résolues.

Aucune dépendance runtime ajoutée, supprimée ou mise à jour. Les notices/licences doivent être conservées. Une montée de version aveugle de classes décompilées serait risquée et ne correspond pas à un changement normal de dépendance Gradle.

## Security Findings

### AUD-01 — Certificat de test public dans la signature v1

- **Severity : HIGH**, provenance/signature ; **Confidence : CONFIRMED** pour le certificat et les contrôles v1 partiels.
- **Location :** `META-INF/BNDLTOOL.RSA`, `BNDLTOOL.SF`, `MANIFEST.MF` de l’APK ; `analysis/audit/signing.json`.
- **Problem / Evidence :** le certificat extrait a SHA-256 `a40da80a59d170caa950cf15c18c454d47a39b26989d8b640ecd745ba71bf5dc`, identique au certificat public AOSP `testkey.x509.pem`. La signature CMS de SF, le digest global du manifeste JAR et les 1 647 digests d’entrées listées concordent.
- **Impact :** ce certificat de développement ne permet pas d’attribuer le build à l’éditeur ; il ne faut pas le reprendre comme identité de confiance pour le mod. Ni cette observation ni un certificat valide ne prouvent un malware.
- **Root Cause :** utilisation d’une identité de test partagée dans la couche v1, pas d’une identité privée de distribution démontrée.
- **Recommended Fix :** garder l’original pour comparaison ; futur mod signé avec une clé personnelle stable, privée et sauvegardée hors Git. Vérifier aussi v2/v3 avec apksigner avant livraison.
- **Regression Risk :** changement de signature = remplacement direct généralement impossible et intégrations potentiellement affectées. Ne pas désinstaller automatiquement.

Comparaison externe réellement effectuée avec le **certificat public seulement** : [AOSP testkey.x509.pem](https://github.com/aosp-mirror/platform_build/blob/main/target/product/security/testkey.x509.pem) et [README de sécurité AOSP](https://github.com/aosp-mirror/platform_build/blob/main/target/product/security/README), qui déconseille explicitement ces clés pour les images publiques. Aucune clé privée de test n’a été téléchargée ou réutilisée.

**Limite cryptographique :** OpenSSL `-noverify` ne valide pas une chaîne de confiance. Ces contrôles ne remplacent pas la sémantique complète du vérificateur Android, ne vérifient pas v2/v3 ni toutes les règles anti-ambiguïté ZIP. On ne prétend pas que le signer effectif de chaque schéma est ainsi validé **par OpenSSL**. Ajout phase 1 : un contrôle Android apksigner distinct a depuis vérifié v1/v2/v3 et le même certificat AOSP ; [sortie réelle CI](../analysis/witness/38666232240/original-signature.txt). Cela ne prouve pas la provenance éditeur.

### AUD-02 — Poursuite automatique après erreur TLS dans Nextcloud

- **Severity : HIGH** ; **Confidence : CONFIRMED** pour le contournement, exploitation **UNKNOWN**.
- **Category / Location :** authentification/TLS ; `sources/ax/S1/b.java:200–208`, classe WebViewClient `f`. Smali `smali/ax/S1/b$f.smali`, `onReceivedSslError`, appel `SslErrorHandler.proceed()` confirmé.
- **Problem / Evidence :** la branche `NEXT_CLOUD` affiche un indicateur, marque `invalid_cert`, puis poursuit le chargement malgré l’erreur TLS. Les autres branches délèguent au comportement par défaut. Le même flux récupère `user`/`password` lors du callback.
- **Impact :** un utilisateur qui connecte Nextcloud sur un réseau/serveur intercepté peut exposer sa connexion ou ses identifiants ; aucun compte ou MITM réel testé.
- **Root Cause :** tolérance aux certificats invalides appliquée automatiquement à un flux d’authentification.
- **Recommended Fix :** annuler par défaut ; si serveurs privés indispensables, ajouter une exception explicite, limitée à l’origine, après affichage/confirmation du certificat, sans contourner globalement les erreurs.
- **Regression Risk :** les serveurs autosignés légitimes nécessiteront un parcours de confiance explicite. Ne pas supprimer Nextcloud.

**Suivi après audit :** [build TEST TLS](NEXTCLOUD_TLS.md) : remplacement ciblé `proceed` → `cancel`, contrôlé dans le code assemblé et démarrage émulateur réussi. Les sources de référence restent celles de la base. Tests réels Nextcloud/certificats et validation du build patché sur Tecno encore à faire ; pas de prétention de validation TLS de bout en bout.

### AUD-03 — Protection insuffisante/fail-open des identifiants persistés

- **Severity : MEDIUM** ; **Confidence : CONFIRMED** pour les chemins de code ; confidentialité effective sur appareil **UNKNOWN**.
- **Category / Location :** stockage/crypto ; `sources/ax/J1/U.java:50–94`, `file/G.java:502–562`, `file/L.java:110–157`, `sources/ax/X1/e.java:9–32`.
- **Problem / Evidence :** les mots de passe SFTP/SMB passent le hostname `nVar.d()` comme entrée de dérivation ; helper PBE à 256 itérations, sel aléatoire et AES-GCM, mais pas de secret lié à Android Keystore dans ce chemin. Sur exception de chiffrement, `U.e` retourne le texte original ; ce fallback est confirmé en smali. Les clés SFTP sont écrites telles que fournies dans `SFTPKeyPrefs`, et le passcode serveur est un `putString` direct.
- **Impact :** résistance faible si les préférences sont obtenues via root/compromission/export. Le sandbox Android MODE_PRIVATE limite normalement les autres apps ; on n’affirme pas une lecture sans permission. Une clé PEM importée peut déjà être chiffrée par sa passphrase : la copie directe ne prouve pas qu’elle est en clair.
- **Root Cause :** chiffrement sans secret device-bound et échec toléré comme succès apparent.
- **Recommended Fix :** chiffrement authentifié avec clé privée Keystore ; aucun fallback plaintext ; migration versionnée et réversible, UX de reconnexion si clé perdue. Garder un accès compatible aux anciennes entrées pendant migration.
- **Regression Risk :** verrouillage de comptes/clés si migration incorrecte ; jamais supprimer les anciennes données avant validation de relecture.

**Sauvegarde :** `enable_backup=false` dans ce build. Les règles XML qui incluent les préférences sont présentes mais ne démontrent pas une fuite cloud active. Si backup est réactivé, il faudra retirer ou protéger les fichiers sensibles et revoir `disableIfNoEncryptionCapabilities=false` sur Android 12.

### AUD-04 — Serveur média HTTP : exposition réseau et allowlist par préfixe

- **Severity : HIGH**, conditionnel à un serveur actif et à la portée réseau ; **Confidence : CONFIRMED** pour bind/prédicat, exploitation de bout en bout **UNKNOWN**.
- **Category / Location :** exposition/autorisation ; `sources/ax/X1/b.java` constructeur et `G`, `sources/ax/Ba/a.java` constructeurs/bind, `service/b.java:134–141`. Ancres `HTTP_*` dans `source-evidence.json`.
- **Problem / Evidence :** les URL produites pointent sur `127.0.0.1`, mais le constructeur passe seulement le port à NanoHTTPD, qui lie le socket sans adresse spécifique. La protection applicative est une liste de préfixes `startsWith`, sans égalité/bornes de chemin ; `V.p()` retire en outre l’extension lors de certains ajouts. La route sert un fichier d’un opérateur actif après ce test, sans jeton de session dans ce chemin.
- **Impact :** l’URL locale n’assure pas que le serveur soit local ; un pair atteignant le port et connaissant/devinant un chemin peut potentiellement lire au-delà du fichier sélectionné. Pare-feu OEM, état des services et origine atteignable non testés.
- **Root Cause :** scope réseau implicite et préfixe textuel confondu avec une autorisation de fichier/répertoire.
- **Recommended Fix :** séparer média local (bind loopback explicite) et partage distant volontaire ; jeton aléatoire de session si distant ; URI/fichiers normalisés et autorisation exacte ou descendants avec séparateur ; autoriser les sous-titres via règles explicites, pas un préfixe libre.
- **Regression Risk :** casting/lecteurs externes et sidecars peuvent dépendre du comportement actuel : cartographier les consommateurs avant de restreindre. Ne pas transformer tout partage LAN en loopback aveuglément.

Le test Python de préfixe est un **modèle du prédicat**, pas une exploitation HTTP du téléphone.

### AUD-05 — RETIRÉ : faux positif de lecture du manifeste

- **Disposition : RETRACTED / NOT A DEFECT.** Aucun patch du provider.
- **Erreur de l’audit initial :** l’analyse avait lu uniquement l’attribut parent `grantUriPermissions=false` et le contrôle smali, sans intégrer l’enfant `<grant-uri-permission android:pathPrefix="/"/>`.
- **Preuve complémentaire :** Android 12 parse cet enfant et positionne `provider.grantUriPermissions = true` lorsqu’un pattern est valide : [ParsedProviderUtils.java, android-12.0.0_r1, lignes 208–270](https://github.com/aosp-mirror/platform_frameworks_base/blob/android-12.0.0_r1/core/java/android/content/pm/parsing/component/ParsedProviderUtils.java#L208-L270), blob `28fd919e2b7e7e19996b5eb7ecf49c738343f8ee`.
- **Conclusion corrigée :** l’attribut binaire zéro était correctement lu mais l’inférence d’incohérence était fausse. Le comportement attendu est compatible avec le démarrage rapporté par l’utilisateur. Ce n’est pas une anomalie du téléphone.
- **Régression évitée :** ne pas supprimer le contrôle smali ni changer les grants pour « réparer » un défaut inexistant. Tests `test_provider_contract.py` : parent seul, enfant valide/invalide et manifeste réel.
- **Limite :** règle confirmée par source AOSP, pas une mesure du ProviderInfo du Tecno. Le large périmètre des grants mérite sa revue propre, sans confondre cela avec un blocage au démarrage.

### AUD-06 — Ressources réseau non bornées sur certains chemins

- **Severity : MEDIUM** ; **Confidence : CONFIRMED** pour structures/lectures, DoS réel **UNKNOWN**.
- **Category / Location :** disponibilité/RAM ; `sources/ax/Ba/a.java`, callback `g.c` ; `service/FtpServerService.java:80–105,271–283`.
- **Problem / Evidence :** HTTP crée un thread par client dans le runner par défaut, sans plafond dans ce runner. FTP utilise `BufferedReader.readLine()` sans limite de longueur de commande ni délai de socket de contrôle observé dans ce chemin. FTP possède un rejet à partir de 30 clients et un mécanisme de suivi d’échecs : ils existent et ne doivent pas être ignorés.
- **Impact :** un client lent ou de très longues commandes peuvent épuiser mémoire/threads quand ces services sont actifs. Les 2 Go du téléphone rendent ce scénario important, sans mesure de consommation disponible.
- **Root Cause :** absence de quotas/délais uniformes aux entrées réseau.
- **Recommended Fix :** délais de contrôle, limite de ligne, pool/quotas bornés, fermeture garantie et tests de cancellation. Conserver les transferts longs légitimes.
- **Regression Risk :** délais trop courts ou pool trop petit interrompent transferts et lecture média.

### AUD-07 — Politique réseau globale permissive

- **Severity : MEDIUM** ; **Confidence : CONFIRMED**, interception réelle **UNKNOWN**.
- **Category / Location :** TLS/transport ; manifeste et `res/xml/network_security_config.xml`.
- **Problem / Evidence :** cleartext global autorisé et trust anchors système **et utilisateur**. WebDAV possède aussi une option persistée `ignorecert`, faux par défaut ; ne pas présenter toute connexion comme non vérifiée.
- **Impact :** périmètre plus large que des exceptions ciblées, selon les flux et la configuration du device.
- **Root Cause :** politique globale probablement liée aux backends/serveurs privés, justification non documentée.
- **Recommended Fix :** distinguer HTTP volontaire et HTTPS ; limiter les exceptions à un compte/origine ; aucune acceptation silencieuse pendant l’authentification.
- **Regression Risk :** réseaux locaux et PKI privées peuvent dépendre des CA utilisateur et de HTTP. Ne pas supprimer ces fonctions globalement.

### AUD-08 — Attente provider sans borne

- **Severity : MEDIUM** ; **Confidence : CONFIRMED** pour l’attente, blocage réel **UNKNOWN**.
- **Category / Location :** fiabilité/concurrence ; `provider/MyFileProvider.java`, `query`, `countDownLatch.await()`.
- **Problem / Evidence :** une branche proxy déclenche une tâche puis attend sans timeout. Selon le backend et l’achèvement des callbacks, un appelant peut attendre indéfiniment.
- **Impact :** thread Binder occupé, appelant bloqué ; aucune ANR reproduite.
- **Root Cause :** contrat d’attente sans deadline ni résultat temporaire explicite.
- **Recommended Fix :** deadline et cancellation, traitement de l’interruption, countdown garanti en `finally`, état d’échec prévisible.
- **Regression Risk :** un résultat vide prématuré casse les lecteurs ; choisir le délai après tests de backends lents.

### AUD-09 — Migrations SQLite destructives

- **Severity : MEDIUM**, conditionnel à une migration ; **Confidence : CONFIRMED** pour les branches, perte actuelle **non observée**.
- **Category / Location :** intégrité de données ; `bookmark/BookmarkProvider.java:36–48`.
- **Problem / Evidence :** `onDowngrade` supprime la table `bookmarks` ; `onUpgrade` fait de même pour une ancienne version <3.
- **Impact :** favoris/historique perdus lors de ces transitions ; aucune suppression démontrée sur le schéma courant v3, aucun fichier utilisateur externe visé par ces instructions.
- **Root Cause :** stratégie drop/recreate au lieu de migration conservatrice.
- **Recommended Fix :** ne pas changer le numéro de schéma pour un redesign ; si évolution nécessaire, migration transactionnelle, sauvegarde et tests upgrade/downgrade explicites.
- **Regression Risk :** modifier un ancien schéma sans fixtures peut perdre ou dupliquer des favoris.

### AUD-10 — Garde de chemin incomplète dans l’extracteur média embarqué

- **Severity : LOW** ; **Confidence : CONFIRMED** pour le prédicat ; atteignabilité par une archive hostile **UNKNOWN / non démontrée**.
- **Category / Location :** durcissement ; `sources/ax/p073f2/c.java:505–513`, extracteur de `assets/exolibs.zip`.
- **Problem / Evidence :** comparaison canonique `startsWith(canonicalPath)` sans séparateur : un répertoire frère portant le même préfixe satisfait ce prédicat.
- **Impact :** garde incorrecte en général, mais l’entrée observée vient d’un asset signé embarqué, pas d’un ZIP utilisateur arbitraire. **Ne pas déclarer une Zip Slip distante démontrée.**
- **Root Cause :** frontière de répertoire exprimée comme préfixe libre.
- **Recommended Fix :** comparaison du descendant avec séparateur ou API de chemin adaptée, après tests ; conserver la sélection ABI.
- **Regression Risk :** chemins racine/séparateurs/variantes ABI à tester. L’autre extraction examinée dans `ax/J1/C0744z.m` utilise déjà canonique + séparateur et exige un dossier vide : point positif.

### AUD-11 — Logs sensibles latents, désactivés au niveau courant

- **Severity : LOW** ; **Confidence : CONFIRMED** pour le code, émission réelle **non démontrée**.
- **Category / Location :** confidentialité ; `service/a.java:718` journalise `FTP INPUT` avec la commande entière ; `sources/ax/S1/b.java` journalise les URL de redirection à FINE.
- **Problem / Evidence :** ces messages peuvent inclure PASS/URL sensibles si FINE est activé. `ax/p338y1/c.a()` retourne faux ; `g.c()` configure alors le logger root à **WARNING**.
- **Impact :** risque latent lors d’un futur debug ; pas de preuve que les mots de passe partent actuellement dans logcat.
- **Root Cause :** journalisation brute plutôt que structurée/rédigée.
- **Recommended Fix :** supprimer/masquer commandes de mot de passe, query et fragments d’URL avant d’activer des logs de diagnostic.
- **Regression Risk :** perdre des informations utiles ; préserver uniquement codes d’erreur et métadonnées non sensibles.

## Critical Issues

**Aucun problème CRITICAL confirmé dans le périmètre examiné.** Ce résultat ne garantit pas l’absence de vulnérabilité critique dans les chemins non examinés ou mal décompilés.

## High Priority Issues

AUD-01 (signature/provenance), AUD-02 (TLS Nextcloud), AUD-04 (HTTP média). AUD-05 a été retiré après examen des enfants du manifeste et du parseur Android 12 ; aucun correctif provider n’est nécessaire.

## Medium Priority Issues

AUD-03, AUD-06, AUD-07, AUD-08, AUD-09. AUD-05 est un faux positif retiré. Les corrections de crypto, partage et migrations nécessitent des tests de compatibilité ; elles ne doivent pas être mélangées à un premier changement de couleurs/layout.

## Low Priority Issues

AUD-10 et AUD-11. UI : `supportsRtl=false` est confirmé ; l’effet sur une future interface multilingue doit être décidé, pas activé à l’aveugle dans des layouts décompilés.

## Code Quality

Classe MainActivity et moteurs de protocole volumineux, couplage aux ressources, noms obfusqués et erreurs JADX compliquent les modifications. Certains artifacts Java impossibles ou redondants sont des effets de décompilation : ne pas les compter automatiquement comme bugs originaux. Plusieurs garde-fous existent : contrôle `exported=false` dans le provider, services principaux non exportés, protection par permissions système de certains composants SDK, garde mémoire éditeur et contrôles d’archive.

Le provider large n’est pas un vol de fichiers par n’importe quelle application : il est non exporté et les grants effectifs doivent être analysés. Le SQL du provider de favoris n’est pas une injection distante démontrée : provider privé et appels externes limités. Le manifeste contient des clés de configuration SDK ; leur présence seule ne signifie pas qu’une clé privée a fuité.

## Performance

**Pas de benchmark.** Sur le Tecno 32 bits/2 Go déclaré, prioriser les limites de concurrence réseau (AUD-06), les miniatures/listes volumineuses et les chargements simultanés avant d’ajouter animations/effets.

Point positif : `TextEditorActivity.m.z` filtre les gros fichiers avec une limite de 30 Mio et des contrôles liés à `memoryClass` et au heap disponible. Ce calcul ne garantit pas l’absence d’OOM, mais interdit de prétendre qu’il n’existe aucune protection. Les allocations FTP observées pour les données sont des buffers de 64 Kio : ne pas les confondre avec un chargement systématique du fichier entier.

Recommandations à mesurer : jeux de 1 000/10 000 fichiers, gros texte, miniatures, transfert + lecture média, rotation et retour après pression mémoire. Pas de promesse « plus rapide » sans avant/après.

## Reliability

Risques prioritaires : attentes sans délai, concurrence réseau, compatibilité de signature. Le supposé conflit de contrat provider AUD-05 est retiré. Un chemin de cache observé (`ax/J1/I`) supprime un ancien fichier avant `renameTo` sans résultat explicitement vérifié à cet endroit : **piste de revue, pas preuve de perte de fichiers utilisateur**. Pas d’affirmation de sûreté complète des déplacements/copie/corbeille ; ces scénarios doivent être vérifiés avec des fichiers jetables, annulation, espace insuffisant, destination existante et mort du processus.

## Testing

| Contrôle | Résultat / portée |
| --- | --- |
| Vérification exports | PASS : CRC des archives, hash, compte Java et 1 245 XML |
| Audit source / smali | Contrôles ponctuels effectués ; 15 ensembles d’ancres vérifiés |
| CMS/SF + digests JAR listés | PASS : 1 647 entrées, aucune discordance ; **pas apksigner complet** |
| Tests du collecteur | **8/8 PASS** ; parser ciblé, fingerprint, ancres, modèles de préfixe |
| Compilation syntaxique Python | PASS |
| Build de l’application | **NOT RUN** pendant cet audit |
| Lint / Type Check app | **NOT RUN** : pas de projet source/configuration originale |
| Instrumentation / réseau / runtime | **NOT RUN** |
| Security Validation | **PARTIAL**, statique et limitée aux surfaces détaillées |

Les tests du collecteur ne sont ni des tests de corrections ni des preuves d’exploitation sur Android. Aucune fonction de l’app n’a été modifiée ; aucun nouveau build à installer.

## UI/UX

Direction retenue : conserver Views/XML ; référence SwiftSlate-ng, Material 3, clair/sombre AMOLED et animations respectant les réglages système. Les XML montrent un drawer, tabs, commandes basses et contrôles média ; une refonte doit préserver les zones de collage/transfert et états de sélection.

**NOT VERIFIED :** contrastes réels, cibles tactiles, ordre TalkBack, grossissement texte, états vide/erreur, retour arrière, rotation, densités, navigation clavier et animations sur Tecno. Pas de rendu/émulateur utilisé ; aucune « conformité accessibilité » déclarée à partir du XML seul.

Recommandation : moderniser d’abord accueil/navigation avec tokens cohérents et garder des animations courtes, annulables, désactivables ; ne pas migrer vers Compose ou ajouter un second framework pour corriger les risques identifiés.

## Feature Opportunities

Conserver toutes les fonctions. Améliorations possibles après validation : indicateurs explicites d’état du serveur (local vs LAN), état de confiance TLS par compte, erreurs de stockage compréhensibles, progression/cancellation et récupération après interruption, consentement aux connexions réseau. Ce sont des propositions, pas des fonctionnalités promises ou déjà réalisées.

## Technical Debt

- Pas de Gradle/lockfile original, mapping d’obfuscation ni suite Android de tests.
- 38 erreurs de décompilation : smali nécessaire pour les méthodes concernées.
- Versions/forks de dépendances partiellement identifiables ; pas de SBOM résolu.
- CI : actions par tags plutôt que SHA, package REA direct épinglé mais pas de lockfile des transitives ; job qui exécute des décompilateurs avec permission `contents:write` pour publier les résultats. **Recommandations**, pas preuve d’une compromission : séparer analyse read-only et publication de confiance, pinner les actions et enregistrer les dépendances.
- Impossible de conserver les mises à jour signées entre builds si la clé de mod est éphémère. Aucune nouvelle clé n’a été créée pour cet audit.

## Recommended Improvements

| Priorité | Proposition | Pourquoi / garde-fou |
| --- | --- | --- |
| P0 | AUD-05 résolu sans patch ; conserver la règle parent/enfant | Éviter la réintroduction du faux positif |
| P0 | Certificat privé stable + vérification Android des signatures | Identité propre, pas la testkey partagée |
| P1 | Reconstruction témoin sans autre modification | Isoler les défauts d’assemblage avant les changements |
| P1 | Corriger TLS Nextcloud et politique du serveur média | Réduire les principaux risques réseau sans supprimer de backend |
| P2 | Quotas/délais et logs expurgés | Robustesse et diagnostics adaptés aux 2 Go RAM |
| P2 | Migration des identifiants vers Keystore | Préserver comptes/clés et traiter les échecs explicitement |
| P3 | Attente provider, migrations SQLite et guard ZIP média | Fiabilité avec fixtures/test de régression |
| P4 | Refonte accueil/navigation | Après base fonctionnelle validée, pas en même temps que la crypto |

## Proposed Execution Plan

**Plan approuvé par l’utilisateur (« Go »).** Commencer par le témoin ; pas de mélange des correctifs TLS/HTTP/UI avec cette phase.

1. AUD-05 est expliqué par les règles du parseur Android ; préserver intégralement le provider et vérifier ce contrat après rebuild.
2. Préparer la stratégie de signature privée et remplacement/sauvegarde ; ne jamais demander un mot de passe ou une clé dans le chat.
3. Produire un build témoin avec **zéro changement fonctionnel**, vérifier apksigner/zipalign et installation/démarrage sur le Tecno.
4. Corriger un seul groupe à la fois : TLS, puis média HTTP, puis limites réseau. Revue des patches, rollback documenté et tests des fonctions concernées avant le groupe suivant.
5. Aborder les migrations sensibles uniquement avec données de test et reconnexion de secours.
6. Commencer la refonte visuelle après validation des gates précédents. Pas de suppression de fonctionnalités ni de modification des droits premium/licence.

## Validation Plan

Sur le téléphone de test, sans compte réel au début : startup/relaunch ; permissions accordées/refusées ; dossier jetable ; copie/déplacement/renommage ; conflit ; annulation ; suppression/restauration ; stockage faible ; lecture média locale/cloud ; partage vers une autre app ; texte volumineux ; rotation et arrière-plan. Rapporter appareil/version et étapes précises, sans fichiers personnels.

Tests réseau ultérieurs en environnement autorisé : certificat Nextcloud invalide/valide/autosigné, HTTP média uniquement local, partage LAN explicitement activé, fichier exact/sidecar/sibling non autorisé, délais FTP et longues commandes, saturation bornée. **Aucun de ces tests n’a encore été exécuté.**

Avant chaque patch : enregistrer le hash et conserver l’APK témoin ; comparer manifeste, permissions et surfaces de partage ; vérifier les instructions smali ; build + signature ; valider sur device ; rollback si régression.

## Unknowns / Information Gaps

- Le paquet du Tecno n’a pas été inspecté par ADB ; son démarrage rapporté est cohérent avec le contrat provider corrigé.
- Comportement complet de l’installateur Android/OEM. Le certificat v1/v2/v3 est désormais contrôlé séparément par apksigner en CI (phase 1).
- Déclenchement effectif des SDK Ads/Analytics, consentement et données envoyées : présence de code/config ≠ trafic observé. Le collector `ApplicationReporter.EVENT_COLLECTOR_URL` est vide ; cela ne désactive pas à lui seul les SDK Firebase/Ads.
- Exploitabilité distante des risques réseau, chaînes réflexives/dynamiques et portée de chaque back-end.
- Performance sur appareil 32 bits, chargeur média effectif et toutes les dépendances ELF.
- Pas d’audit exhaustif CVE, crypto-providers Android, comptes cloud, ni rétro-ingénierie complète des binaires natifs.

Recherche de secrets ciblée : deux marqueurs PEM trouvés sont des délimiteurs de parseur dans JSch (RSA/ECDSA), pas des clés privées complètes démontrées. Les valeurs de secrets ne sont pas reproduites dans les rapports. Cette recherche limitée ne prouve pas l’absence de secrets cachés/obfusqués.

---

**Confirmed Problems :** certificat v1 de test partagé ; poursuite TLS Nextcloud ; crypto fail-open/stockage à durcir ; serveur HTTP wildcard avec allowlist par préfixe ; limites réseau/attentes/migrations et deux durcissements bas niveau décrits.

**Likely Problems :** risque de fuite ou de saturation quand les chemins concernés sont actifs ; aucune panne provider imputable à AUD-05 : constat retiré. Pas de panne utilisateur actuelle démontrée.

**Recommendations :** signature stable personnelle, reconstruction témoin, corrections réseau incrémentales, migration sécurisée testée, puis UI Views/XML.

**Unknown :** authenticité éditeur, comportement runtime réel, exploitabilité de bout en bout, performances et identité du paquet installé.

**Execution Order :**
1. Préserver le provider (AUD-05 retiré) et établir la stratégie de signature.
2. Build témoin et test manuel Tecno.
3. TLS et autorisation média, puis quotas réseau.
4. Identifiants/provider/migrations avec tests.
5. Refonte visuelle après validation, toutes fonctions conservées.

**Gate suivante : témoin assemblé/signé/testé avant les corrections fonctionnelles.**
