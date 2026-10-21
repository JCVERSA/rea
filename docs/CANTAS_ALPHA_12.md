# Cantas alpha12 — renommage réel d’un élément SAF

**TEST vérifié, pas une version finale.** Version0.12.0-alpha12, code13. Source `23fe213dd33ebff9f727ff8a1eae4cdeafec92f4`, [run39530215958](https://github.com/JCVERSA/rea/actions/runs/39530215958). Les cinq jobs ont réussi. [Alpha11 conservée](CANTAS_ALPHA_11.md).

**[Télécharger le ZIP TEST alpha12](https://github.com/JCVERSA/rea/actions/runs/39530215958/artifacts/12314794771)** : `Cantas-0.12.0-alpha12-TEST.apk` (debug0.12.0-alpha12-test), accès GitHub requis, rétention14jours. SHA-256 enregistré par CI : `df6371dc7990b8c0e2dcd529c3b015b06d4022611088dd38a85a6d59f0ad14ab`, non recalculé localement. Rapports/captures : `analysis/cantas/39530215958/`.

## Fonctionnalité et consentement

Sélectionner exactement un fichier ou dossier d’une connexion SAF autorisée en READ+WRITE fait apparaître Renommer la sélection. Le dialogue montre le nom original, le parent, la distinction avec le titre d’un favori et les limites du provider ; le nom doit être changé et confirmé explicitement. Annuler avant confirmation n’invoque aucun renommage. Aucune connexion READ n’est automatiquement transformée en WRITE.

Il s’agit du **véritable fichier/dossier SAF**, pas du raccourci favori. Le renommage natif local reste absent : choisir un dossier via Android est un backend distinct, pas un contournement automatique des permissions locales. Les fonctions précédentes de copie/création/navigation sont conservées, sans nouveau privilège manifeste, permission INTERNET ou dépendance.

## Contrôles et résultat

- Parent connu du navigateur, enfant effectivement découvert dans ce parent, URI canonique du même arbre, refus de la racine et du parent eux-mêmes. Le contrôleur capture une sélection unique, puis vérifie encore dossier courant, entrée et disponibilité au moment de confirmer. Un rafraîchissement lors de recréation d’Activity peut vider la sélection visuelle, sans effacer l’intention explicite déjà capturée dans le dialogue ; l’entrée doit toujours correspondre au listing rafraîchi.
- Nom validé selon la politique existante (1–255octets UTF-8, pas de chemin/contrôle/Unicode invalide/espaces extrêmes) et différent du nom initial.
- Scan complet des enfants avant/après, sans filtre de recherche ou cachés ; refus d’un autre nom détecté sans distinction de casse. Jusqu’à100000lignes et4M unités UTF-16 par scan,4096unités par champ. Les curseurs préalloués du provider ne sont pas à mémoire totale garantie.
- Grant READ+WRITE persistant vérifié avant lecture et juste avant mutation ; source encore présente sous le nom/type attendus, taille connue du fichier correspondant au listing, flag `FLAG_SUPPORTS_RENAME` exigé. Pas de fallback par copie/suppression si le provider refuse.
- **Un seul** appel `renameDocument`, jamais delete, déplacement ou rollback. Le résultat peut garder ou changer l’ID ; URI canonique du même arbre≤16384unités, ID≤4096unités, pas la racine/parent ni un autre ID déjà présent.
- Après retour, vérification dans le même parent : nom exact, MIME/taille observés identiques, unicité du nouveau nom, disparition de l’ancien ID si remplacé, et ID/noms/types/tailles des autres entrées encore présents sans changement observable.

Un résultat renommé vérifié provoque l’actualisation du parent et un message de succès à acquitter. Une collision détectée n’invoque aucun renommage ; les erreurs préalables exigent de fermer/actualiser/sélectionner à nouveau. Une erreur après invocation, réponse manquante/étrangère, nom imposé ou vérification échouée signifie **renommage non vérifié** : le nom/URI peut déjà avoir changé. L’application actualise le parent, conserve l’avertissement et ne réessaie/ne restaure rien automatiquement.

Ces contrôles ne sont **pas** un verrou inter-apps, une primitive atomique portable de non-écrasement, un hash du contenu ni un snapshot de sous-arbre. Une modification concurrente ou un provider malveillant peut échapper aux métadonnées comparées. Un changement de MIME/taille légitime du provider peut aussi rendre le résultat incertain. Le provider reste responsable de son contrat de renommage et de la conservation du contenu.

## État et limites de récupération

Le dialogue/son nom sont restaurables lors d’une recréation d’Activity ; le ViewModel conserve la requête explicite. Confirmation désactivée pendant un rafraîchissement ou une erreur de listing. Pendant l’opération, le dialogue ne se ferme pas et les autres opérations de copie/création/renommage du même ViewModel sont refusées.

Pas de journal durable de renommage, historique d’annulation ou reprise après mort du processus. Les requêtes et l’appel synchrones peuvent bloquer ; pas de bouton promettant d’annuler un renommage déjà invoqué ni de délai absolu. Une fermeture forcée peut laisser un nom changé sans compte rendu. Le mutex IO est celui du ViewModel, pas un verrou global multi-instance/processus.

Favoris, titres personnalisés et presse-papiers ne sont pas réécrits automatiquement. Un changement d’ID peut rendre un favori indisponible ; il reste enregistré au lieu d’être remappé vers une identité supposée. Une copie mise au presse-papiers sous l’ancien nom doit repasser ses validations, pas lire silencieusement un remplacement.

## Validation vérifiée

**69tests JVM, zéro échec/ignoré ;49tests instrumentés sur chacun des émulateurs API24/x86 et API31/x86_64 ; les deux smoke runtime réussissent.** Lint : **0erreur,33avertissements**, sans suppression ni changement de dépendances. Identité indépendante, min24/target35, absence d’INTERNET, signature v2 et quatre variantes natives graphiques dont ARMv7 vérifiées par CI. Quatre nouveaux tests backend : fichier avec nom non-ASCII et ID inchangé puis changé, octets conservés ; dossier/enfant conservé et collision sans appel ; READ seul/révocation/absence de flag/métadonnées périmées/nom invalide sans mutation ; nom imposé/exception post-renommage signalés incertains, sans rollback. Un test UI fait sélection unique, annulation sans appel, nouveau nom confirmé après recréation d’Activity, vérifie le nom sauvegardé et les octets puis capture `alpha12-saf-rename.png`. La capture API31 a été inspectée à320×640 : portée réelle de l’action/avertissement provider, nom `Renamed.txt`, résultat vérifié et acquittement visibles.

Le run39528583336 a réussi sur API24 et tous les nouveaux tests de renommage ont réussi aussi sur API31, mais le gate API31 a échoué dans le test historique de retour accueil/thème : assertion de titre pendant une absence transitoire de hiérarchie Compose. Une première attente de sémantiques seule (run39529388697) a encore rencontré le détachement entre lookup et toucher du bouton système. Le test de thème/navigation utilise désormais les actions sémantiques idempotentes avec une attente bornée qui ne réessaie que l’exception précise « No compose hierarchies found ». Les assertions et autres exceptions ne sont pas masquées ; la préférence system est aussi vérifiée. Ce helper n’est utilisé pour aucune confirmation d’opération de fichier. Seul le run final ci-dessus constitue cette livraison.

Fixtures dans le provider de l’APK de test sur émulateur jetable ; la remise à zéro ne vise que ces fixtures. Aucun ADB ni mutation sur le téléphone de l’utilisateur. XML/Python/diff contrôlés localement ; Kotlin/Android, lint et émulateurs via CI uniquement.

## Suite et validation manquante

Restent renommage natif local et parité détaillée par backend, déplacement, suppression/corbeille, archives, lecteurs internes, réseau/cloud/serveurs, Shizuku et le reste du [contrat final](CANTAS_FINAL_ACCEPTANCE.md). Aucun essai réel Tecno BF6 ARM32/2Go, SD/USB/cloud, sélecteur système complet, concurrence hostile, disque plein, mort forcée du processus, FR/accessibilité/performance ou Android7–15 complet revendiqué.

Essai manuel : uniquement un fichier/dossier jetable, connexion SAF distincte choisie en lecture/écriture ; annuler d’abord, confirmer ensuite un nouveau nom et contrôler le contenu. Une collision doit être refusée sans remplacement. Si résultat incertain, inspecter le parent avant toute autre action. Aucune désinstallation automatique ; signatures TEST temporaires susceptibles de changer, protéger les données privées utiles avant toute réinstallation décidée manuellement.
