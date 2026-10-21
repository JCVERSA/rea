# Cantas alpha10 — fichiers locaux/SAF vers destination SAF

**TEST vérifié, pas une version finale.** Version0.10.0-alpha10, code11. Source `fd242d11735a853e7432b50bf90ce6a77402e5af`, [run39520353291](https://github.com/JCVERSA/rea/actions/runs/39520353291). Cinq jobs réussis. [Alpha09 conservée](CANTAS_ALPHA_09.md).

**[Télécharger le ZIP TEST alpha10](https://github.com/JCVERSA/rea/actions/runs/39520353291/artifacts/12310289166)** : `Cantas-0.10.0-alpha10-TEST.apk` (debug0.10.0-alpha10-test), accès GitHub requis, rétention14jours. SHA-256 enregistré par CI : `15d31f7f17bddc09e2be39ad0d60bdcf2c2078397fdbab0faa6a389f1cd0b5a8`, non recalculé localement. Rapports/captures : `analysis/cantas/39520353291/`.

## Fonctionnalité

Le presse-papiers de copie peut maintenant être collé dans le dossier courant d’une connexion SAF persistante READ+WRITE choisie explicitement. Sources : fichiers locaux réguliers dans la frontière de stockage ou documents SAF autorisés en READ, y compris SAF→SAF. Pas besoin d’un grant WRITE pour la source SAF, ni de WRITE local pour lire une source locale. Le backend natif continue de refuser les liens au dernier composant et les alias détectés ; il ne résout pas toutes les races d’ancêtres.

**Fichiers seuls,1–100 par lot.** Tout dossier dans la sélection fait échouer le préflight entier sans mutation de destination. Les copies récursives locales et imports récursifs SAF→local restent disponibles et inchangés. La copie récursive vers SAF reste à implémenter, comme déplacement/renommage/suppression/corbeille.

Une confirmation propre aux destinations SAF explique ces limites. La sélection décrit désormais les limites distinctes selon destination. Les connexions READ restent non inscriptibles ; les permissions de la destination sont vérifiées côté backend et avant chaque invocation de création.

## Parcours et sécurité

- Préflight complet en lecture seule : accès/capacité du parent connu du navigateur, scan des enfants, noms/chemins uniques, types réguliers et métadonnées des sources. Aucune création si un élément du lot est invalide ou un dossier.
- Journal privé enregistré et relu avant toute écriture, rapport final persistant, avertissement d’interruption après reconstruction du store. Ce journal reste par ViewModel/instance, pas une transaction multi-processus ou une preuve de survie à un vrai arrêt forcé.
- Avant chaque fichier, relecture de la source et de la destination. Les collisions de noms détectées, sans distinction de casse, sont ignorées sans appel de création ni ouverture d’un flux de sortie.
- Un appel `createDocument` par fichier, MIME déduit de l’extension ou octet-stream. Vérification de l’URI canonique du même arbre, ID nouveau distinct du parent/des enfants précédents et des ID de source SAF de même autorité. Vérification d’appartenance, nom exact, type non dossier, unicité du nom et métadonnées antérieures encore présentes avant d’ouvrir en écriture.
- Aucun mode d’ouverture tronquant un URI préexistant connu ; ouverture `w` seulement après cette validation du résultat de création. Le provider reste responsable de respecter son contrat : l’API n’offre pas d’exclusive-create transactionnel portable.
- Buffer64Kio, progression au plus toutes les100ms environ, longueur connue capturée ou512Mio maximum par fichier de taille inconnue ; un octet supplémentaire est sondé sans être écrit pour détecter le dépassement. Pas de quota agrégé de lot, pas de snapshot de source.
- Après fermeture du flux de sortie, relecture du fichier créé, longueur exacte et SHA-256 comparés au flux copié, puis vérification des métadonnées dans le parent. La relecture peut déclencher un téléchargement supplémentaire chez un provider cloud, avec du trafic et des délais dépendant de ce provider. Elle ne prouve ni fsync physique, ni durabilité cloud, ni contenu stable ultérieur ; il n’y a pas de garantie de transaction ou de hash de snapshot d’origine.
- Les scans avant/après retiennent au maximum100000lignes et4M unités UTF-16 chacun ; champs4096unités, chemins/URI d’entrée et URI de sortie16384unités. Plusieurs scans/maps coexistent et le provider peut préallouer les curseurs : aucune garantie de mémoire totale sur des arborescences adverses.

## Incertitude et annulation

Un renommage automatique, résultat absent/étranger, échec après invocation, vérification tronquée ou hash incorrect produit **sortie non vérifiée**, jamais « copié ». Le rapport conserve l’URI connue ou le parent si l’emplacement créé n’a pas pu être obtenu. Le nom demandé n’est pas nécessairement le nom réel du provider. `created` signifie ici qu’une création a été invoquée et qu’une sortie peut exister, pas une preuve de propriété permettant de la supprimer.

Aucun effacement/rollback/renommage/réessai automatique, aucune suppression de source. Les sorties partielles ou incertaines sont à inspecter dans la destination. Le compteur des résultats non vérifiés désigne des octets envoyés dans le flux, pas une taille durable garantie. Le presse-papiers n’est vidé que si tout le lot est vérifié copié ; le fermer ne nettoie aucun fichier.

L’annulation est coopérative entre lectures/écritures/scans ; CancellationSignal atteint les requêtes, pas nécessairement un flux bloqué. Un descripteur ouvert peut rester utilisable après révocation de grant. Aucun délai absolu, service de copie survivant au processus, reprise ou nettoyage automatique n’est promis.

Un provider malveillant ou des alias physiques entre chemins locaux et URI ne peuvent pas être rendus sûrs par comparaison de préfixes/ID. Des modifications concurrentes peuvent survenir entre vérifications. Les contrôles sont conservateurs, pas une preuve de disjonction physique ou une garantie universelle de non-écrasement SAF. Choisir des destinations de test distinctes tant que les backends réels ne sont pas validés.

## Validation vérifiée

**63tests JVM, zéro échec/ignoré ;39tests instrumentés sur chacun des émulateurs API24/x86 et API31/x86_64 ; les deux smoke runtime réussissent.** Lint : **0erreur,32avertissements**, trois suggestions `UseKtx` de plus qu’alpha09, sans suppression ni nouvelle dépendance. Identité indépendante, min24/target35, absence d’INTERNET, signature v2 et quatre variantes natives graphiques dont ARMv7 vérifiées. Quatre nouveaux tests backend : copie connue/inconnue, relecture et collisions avec marqueur intact ; sorties renommées/exception après création/corruption retenues comme incertaines ; refus READ seul/dossiers en lot/annulation au préflight sans création ; vraie source SAF READ sans WRITE, annulation après des octets envoyés et détection de changement de source. Un test UI réalise sélection locale, mise au presse-papiers, navigation SAF, confirmation, vérification de contenu, rapport puis acquittement. Capture API31 `alpha10-saf-copy.png` inspectée à320×640 : rapport final `local.txt — Copied (19 B)`, destination SAF et acquittement visibles. Le test attend désormais la sémantique et l’inactivité du rapport final avant capture ; une capture intermédiaire du run39519495336 montrait encore la progression malgré un résultat déjà disponible dans le modèle. Aucun retrait d’assertion.

Les sources/fixtures appartiennent uniquement à l’APK instrumenté et au cache de son provider sur émulateurs jetables. La suppression en fin de test est limitée à ces fixtures, jamais à un téléphone personnel. Android/Kotlin sont compilés en CI ; XML/Python/diff vérifiés localement. Pas d’ADB sur le téléphone.

## Non vérifié et reste du produit

Pas d’essai réel Tecno BF6 ARM32/2Go, SD/USB/cloud, système-picker complet, disque plein, mort de processus forcée, performances, accessibilité/FR en exécution ni Android7–15 complet. Les tests de flux et de reconstruction de journal ne prouvent pas ces cas.

La copie récursive vers SAF, déplacements/renommages/suppressions réels, corbeille, archives, lecteurs internes, réseaux/cloud/serveurs, Shizuku facultatif et les autres critères du [contrat final](CANTAS_FINAL_ACCEPTANCE.md) restent ouverts. Cette étape ne clôture pas la demande de version finale.

Essai manuel : sélectionner un fichier jetable, choisir une destination SAF distincte en lecture/écriture, coller, vérifier le rapport et le contenu ; recommencer avec un nom existant et confirmer sa conservation. Ne pas supprimer une sortie incertaine sans inspection, ni désinstaller une application pour installer ce TEST sans protéger ses données privées utiles ; signatures temporaires susceptibles de changer.
