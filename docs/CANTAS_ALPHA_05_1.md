# Cantas 0.5.1-alpha05 — bornage de la copie et exclusions de sauvegarde

## État

**Correctif validé et livré en TEST personnel.** Source `9e72b3af0f3ca6c81640b935c1c795ae9a2bbb50`, [run38982940731](https://github.com/JCVERSA/rea/actions/runs/38982940731). Version `0.5.1-alpha05`, code6. La livraison antérieure [0.5.0-alpha05](CANTAS_ALPHA_05.md) est conservée pour l’historique.

**[Télécharger le ZIP TEST 0.5.1-alpha05](https://github.com/JCVERSA/rea/actions/runs/38982940731/artifacts/12081952940)**, contenant `Cantas-0.5.1-alpha05-TEST.apk` et `SHA256SUMS`. Accès GitHub au dépôt requis, rétention14 jours. Signature TEST temporaire, pas de release publique.

SHA-256 de l’APK enregistré par CI (pas de rehash binaire local) :

```text
b67e8a7bab353049f213713374b5536d9f92efcf6fcbd9f5a7675a9128a83912
```

Rapports archivés : `analysis/cantas/38982940731/`. Tous les jobs build/API24/API31/deliver/publish sont réussis. **36 tests JVM,0 échec/ignoré ;15 tests instrumentés par API24/x86 et API31/x86_64 ; smoke de navigation réussi sur les deux.** Lint : **0 erreur,20 avertissements**, contre21 précédemment ; le diagnostic DataExtractionRules est résolu sans suppression de lint. Les autres avertissements (policy all-files, cible/dépendances, KTX, pluriels, ressource inutilisée) restent visibles. Signature v2 et quatre ABI de bibliothèque graphique vérifiées ; l’empaquetage ARMv7 n’est pas un test d’exécution ARM32.

## Correctif de copie

Le moteur précédent lisait jusqu’à EOF, puis contrôlait taille/métadonnées. Un producteur externe alimentant le même fichier assez vite pouvait repousser EOF et faire écrire plus que la taille initiale, jusqu’à une annulation ou une erreur de stockage. La validation finale seule ne bornait pas cette consommation.

Désormais :

- Taille initiale capturée sur le descripteur source ; au plus cette quantité d’octets est écrite pour ce fichier. Cela ne limite pas la taille choisie initialement par l’utilisateur ni la taille totale du lot.
- Une sonde supplémentaire d’un seul octet, **jamais écrit**, distingue EOF d’une source qui a grandi. Une longueur différente de celle capturée donne `SOURCE_CHANGED`, même si le contrôle de métadonnées affirme « inchangé ».
- Une fin prématurée conserve la sortie courte et la rapporte comme source modifiée, sans la déclarer copiée.
- Une lecture sans progrès (`read == 0` avec une demande non vide) échoue au lieu de boucler. Une taille négative est refusée avant la création d’une sortie ; le flux source est fermé.
- Tous les garde-fous existants restent actifs : fichiers locaux réguliers uniquement,100 par lot, tampon64Kio, refus atomique d’écrasement, annulation coopérative, sources ouvertes en lecture seule, pas de suppression/renommage/nettoyage automatique. Les sorties incomplètes ou non vérifiées restent à inspecter manuellement.

La sonde ne crée **pas** un snapshot ni un hash du contenu. Une modification rapide de même taille peut toujours échapper au contrôle. Les risques d’ancêtres remplacés entre validation et ouverture, le journal de session non multi-instance et les limites d’arrière-plan restent ceux décrits en alpha05. Aucun support de copie de dossiers/SAF ajouté dans ce correctif ; les fonctionnalités futures ne sont pas retirées de la feuille de route.

## Sauvegardes

`allowBackup=false` est conservé. `android:fullBackupContent` référence désormais une règle d’exclusion explicite pour Android7–11 ; `dataExtractionRules` continue de couvrir cloud/transfert Android12+. Les deux générations excluent les domaines root/file/database/sharedpref/external ainsi que leurs quatre variantes device-protected pertinentes. L’application n’y stocke actuellement pas de données device-protected ; ces exclusions sont préventives.

La vérification du manifeste compilé exige les deux attributs de règles. Le contrôle XML local vérifie les neuf domaines et les chemins `.` de chaque section. Cela ne constitue pas un test de sauvegarde/restauration, de transfert OEM ni une garantie contre un acteur privilégié. Aucun compte, backend, publicité, télémétrie ou permission INTERNET ajouté.

## Tests exécutés et limites de preuve

Cinq tests JVM supplémentaires : source sans EOF avec métadonnées inchangées, croissance depuis une taille initiale nulle, EOF prématuré, lecteur sans progrès, taille initiale invalide avec fermeture du flux sans sortie. Un test instrumenté ajoute réellement des octets à la source après l’ouverture du descripteur natif et vérifie que la sortie garde la longueur/contenu initiaux et un statut de changement. Cette écriture de simulation est limitée à une fixture jetable du test, pas effectuée par le moteur de copie.

Confirmés dans les rapports CI :36 tests JVM et15 tests instrumentés par émulateur API24/API31, anciens tests conservés, tous réussis. Contrôles locaux XML/domaines, syntaxe des scripts Python et `git diff --check` réussis. Compilation Kotlin/Android non exécutée localement faute de JDK/SDK et d’accès aux dépôts Gradle/Google/Maven ; elle est confiée à CI.

Toujours non vérifiés : Tecno ARM32/2Go, Android7–15 au complet, UI française/accessibilité/performance, coupure forcée du processus, vrai disque plein, sauvegarde/restauration/OEM et SD/USB physiques. Aucun ADB sur le téléphone. Ne pas désinstaller l’ancienne app ni Cantas automatiquement ; les signatures TEST temporaires peuvent varier entre builds.
