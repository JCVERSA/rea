# Cantas alpha08 — favoris de dossiers et préférences persistantes

**Livraison TEST vérifiée, pas une version finale.** Version `0.8.0-alpha08`, code9 (`0.8.0-alpha08-test` dans l’APK debug). Source `ca39af1ef6a333d72db8c58becf27e7b3d095a19`, [run39467338138](https://github.com/JCVERSA/rea/actions/runs/39467338138), cinq jobs réussis.

**[Télécharger le ZIP TEST alpha08](https://github.com/JCVERSA/rea/actions/runs/39467338138/artifacts/12285643184)** : `Cantas-0.8.0-alpha08-TEST.apk`, accès GitHub requis, rétention14jours. SHA-256 enregistré par CI : `5a605e6527b279a1af20e843fbad8ff95ac6c2b141d138e83bcc86dd65ae3d14`, non recalculé localement. Rapports et captures dans `analysis/cantas/39467338138/`. [Alpha07 conservée](CANTAS_ALPHA_07.md).

## Fonctionnalités

- Étoile dans l’en-tête du dossier courant pour enregistrer/retirer un favori ; gestionnaire Favoris accessible depuis les trois onglets.
- Favoris de **dossiers locaux et SAF** uniquement, jusqu’à50 raccourcis. État enregistré dans `filesDir/browser-preferences.json`, pas dans un compte ou serveur.
- Ouverture, modification du titre et retrait du raccourci. Les termes Renommer/Retirer concernent uniquement le **raccourci**, pas le dossier ou son contenu. Le retrait depuis le gestionnaire demande confirmation, ne supprime aucun fichier et ne révoque aucun grant Android. L’étoile est une bascule directe de ce raccourci, pas une opération sur le disque utilisateur.
- Les titres sont limités à128 unités UTF-16, sans blancs aux extrémités ni caractères de contrôle/encodage invalide. Le titre automatique est nettoyé et tronqué sans couper une paire de surrogates. Les emplacements sont limités à4 096 unités ; la profondeur SAF mémorisée à33 dossiers.
- Tri et visibilité des fichiers cachés deviennent persistants entre ViewModels/lancements. Recherche courante, limite d’affichage augmentée, sélection et presse-papiers ne sont pas persistés. La navigation continue de remettre recherche et limite à leur valeur initiale.
- L’écran À propos utilise désormais une ressource de version générée par Gradle, au lieu du libellé0.5 figé ; les textes de périmètre FR/EN sont actualisés.

## Restauration et accès SAF

Les chemins locaux restent soumis à `StorageBoundary` et aux permissions Android au moment du listing. Un favori n’accorde aucun accès supplémentaire et ne représente pas un snapshot ou une identité d’inode.

Un favori SAF mémorise le tree URI et la chaîne d’ancêtres découverte, pas une conversion en chemin local. Pour le rouvrir depuis un nouveau `SafFiles`, le contrôleur exige le grant READ persistant, puis vérifie chaque relation parent/enfant par requête de documents. Les identifiants restent opaques. Une URI de la même autorité n’est pas une preuve d’appartenance ; les URI d’un autre arbre, chaînes cycliques, enfants manquants et cibles non dossiers sont refusés.

Chaque scan d’appartenance est limité à100 000 lignes et annulable par CancellationSignal ; au plus33 ancêtres sont retenus. Un fournisseur peut toutefois préallouer son curseur ou ne pas coopérer : pas de garantie de mémoire totale ni de délai absolu. Un provider cloud peut utiliser son propre réseau, sans permission INTERNET ajoutée à Cantas.

Un accès révoqué, une carte absente ou un dossier déplacé/supprimé provoque un état indisponible ; le raccourci est conservé, pas silencieusement effacé ou redirigé. Un Retour depuis une cible non restaurée peut remonter à la racine de la source, puis quitter vers l’accueil. Reconnecter ou réenregistrer le dossier reste une action explicite. Après rétablissement explicite du grant READ, Actualiser refait la vérification de la chaîne conservée tant que le listing n’a pas réussi ; un rafraîchissement ou changement de filtre pendant la restauration ne perd plus cette chaîne. Aucun grant n’est réacquis automatiquement.

## Stockage des métadonnées

Budget **256Kio** pour l’ensemble de ce fichier ; des chemins/ancêtres très longs peuvent atteindre cette limite avant50 favoris. Lecture bornée, schéma versionné, UTF-8 strict, contrôles de noms/doublons/URI, read-modify-write sérialisé par mutex de processus, AtomicFile avec fsync/relecture. Les mises à jour concurrentes utilisent le dernier état relu, pas un snapshot mémoire périmé. Le store partagé publie les favoris à tous les ViewModels du processus ; les vues déjà ouvertes gardent leurs choix de tri de session jusqu’à une action ou une nouvelle initialisation.

En cas d’échec de lecture/enregistrement, l’UI indique que la vue peut ne pas être sauvegardée. Une donnée corrompue, hors schéma ou trop volumineuse n’est pas remplacée automatiquement par défaut : les mises à jour échouent sans l’écraser. Le gestionnaire permet de recharger ou de **réinitialiser explicitement**, après confirmation, seulement favoris/tri/cachés. Cela ne touche ni fichiers utilisateur, grants, thème, journal de copie ni presse-papiers. Les règles d’exclusion de sauvegarde existantes couvrent ce fichier ; pas de nouvelle garantie de transfert OEM.

## Validation vérifiée

**63 tests JVM, zéro échec/ignoré ;29 tests instrumentés réussis sur chacun des émulateurs API24/x86 et API31/x86_64 ; les deux smoke runtime réussissent.** Lint : **0 erreur,28 avertissements** (six suggestions `UseKtx` de plus qu’alpha07), sans suppression ni mise à niveau de dépendances. Identité indépendante, min24/target35, absence d’INTERNET, signature v2 et quatre variantes natives graphiques dont ARMv7 vérifiées par CI. Nouveaux tests : politique Unicode/limites/dédoublonnage, options persistées sans recherche/limite de session, restauration depuis un nouveau store disque, mises à jour concurrentes, corruption/budget/refus d’arbre forgé, appartenance SAF réelle, UI locale ajout/réouverture/renommage/retrait avec marqueur conservé, favori SAF imbriqué dans un nouveau ViewModel puis révocation sans disparition silencieuse, rétablissement READ suivi d’Actualiser et retrait sans grant résiduel. La capture API31 `alpha08-favorites.png` a été inspectée : raccourci « My saved folder », emplacement, actions de titre/retrait et avertissement de périmètre visibles à320×640. Le test UI de tri/cachés vérifie aussi un nouveau store relu depuis le disque et remet explicitement ces métadonnées de fixture à zéro en fin de test.

Le premier run39465615587 a échoué sur les deux émulateurs : signature non-void d’un nouveau test `runBlocking` refusée par JUnit (classe SAF non exécutée), et contrôle IME exécuté pendant une absence transitoire de hiérarchie Compose sur API31. Retour Unit explicite et attente de sémantique ajoutés, sans retrait d’assertion ; diagnostics dédiés en cas de nouvel échec. La revue a aussi corrigé la perte de chaîne SAF lors d’un nouvel essai, avec un scénario de révocation/rétablissement/Actualiser. Seul le run final ci-dessus constitue cette livraison.

XML, syntaxe Python et `git diff --check` vérifiés localement. Pas de compilation Kotlin/Android locale : JDK/SDK et dépôts Gradle/Google/Maven non disponibles ; compilation et émulateurs en CI. Les fixtures et réinitialisations instrumentées appartiennent à l’app de test sur émulateur jetable, pas au téléphone personnel.

Toujours non vérifiés : mort forcée/recréation complète du processus, vrai transfert OEM, stockage plein réel, providers SD/USB/cloud réels, FR/RTL/TalkBack/grandes polices/performance en exécution, Android7–15 complet et Tecno BF6 ARM32/2Go. Les tests de reconstruction de store et de ViewModel ne sont pas présentés comme un test de crash complet.

## Limites de produit

Pas de favoris de fichiers, ordre manuel/synchronisation/export-import de favoris, historique de récents ou nouveau mode grille dans ce lot. Les fonctions restantes du [contrat final](CANTAS_FINAL_ACCEPTANCE.md) restent ouvertes : écriture SAF, déplacement/renommage/suppression réels, corbeille, recherche globale, archives, lecteurs internes, NAS/cloud/serveurs et Shizuku facultatif. Cette livraison ne clôture pas la demande de version finale.

Pour l’essai manuel, tester uniquement avec un dossier jetable local ou SAF : étoile, ouverture via Favoris, titre personnalisé puis retrait ; vérifier que les fichiers et autorisations restent inchangés. Aucun ADB sur le téléphone ni désinstallation automatique. Les clés TEST peuvent varier : protéger les données privées utiles avant toute décision manuelle de réinstallation.
