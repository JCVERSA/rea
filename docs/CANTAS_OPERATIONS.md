# Cantas — premières opérations locales, alpha04

> Dernière livraison : [alpha11 — copie récursive locale/SAF vers SAF sans fusion](CANTAS_ALPHA_11.md). [Alpha10 — fichiers vers SAF vérifiés](CANTAS_ALPHA_10.md) conservée. [Alpha09 — création de dossier SAF et accès WRITE explicite](CANTAS_ALPHA_09.md) conservée. Le refus SAF décrit ci-dessous est historique : READ seul reste refusé, mais la connexion WRITE permet maintenant un mkdir séparé. [Import SAF→local alpha07](CANTAS_ALPHA_07.md) et [copie de dossiers locaux alpha06](CANTAS_ALPHA_06.md) sont conservés.

> Ce document conserve le périmètre livré alpha04. La copie locale de fichiers est désormais livrée dans le **TEST alpha05 validé en CI** : [état et limites](CANTAS_ALPHA_05.md).

## Périmètre

Ce lot commence la sélection multiple et les opérations non destructrices. Il **ne** livre pas encore la copie, le déplacement, le renommage, la suppression, la corbeille ou les opérations récursives. Le contrat `ReadOnlyStorage` reste en lecture seule ; la création est isolée dans `LocalFolders` et le contrôleur la refuse pour une source SAF.

## Sélection

- Bouton Sélectionner ou appui long sur une ligne ; en mode sélection, toucher une ligne/case bascule sa sélection au lieu de l’ouvrir.
- Terminer ou Retour ferme la sélection avant de remonter le dossier.
- « Sélectionner tous les résultats chargés » vise uniquement le snapshot actuellement chargé, pas tout le téléphone, pas tous les descendants ni nécessairement tous les résultats du dossier. La liste initiale est limitée à1 000 ; les limites alpha03 restent applicables.
- Une entrée est identifiée par emplacement + nom affiché : deux alias locaux d’une même cible canonique ne sont pas fusionnés.
- Navigation, recherche, tri, cachés, actualisation et chargement supplémentaire réinitialisent la sélection. Quitter l’onglet Fichiers la réinitialise également ; une révocation de grant la vide.
- État de session/ViewModel, pas de sélection persistée sur disque après mort du processus. Les lignes périmées ou en chargement ne déclenchent pas d’ouverture/sélection.

Ce lot prépare les futures actions sur un ensemble ; sélectionner ne copie, ne déplace ni ne supprime rien.

## Créer un dossier local

1. Le bouton n’apparaît que sur le backend local, avec un listing disponible. SAF reste en lecture seule même si le fournisseur annonce des capacités d’écriture.
2. Android7–10 : la nouvelle permission `WRITE_EXTERNAL_STORAGE` est déclarée avec `maxSdkVersion=29`. Si elle manque, « Autoriser la création » la demande uniquement après action explicite. Le bouton devient ensuite Nouveau dossier, à toucher pour ouvrir le dialogue. Les règles de groupe des anciens Android peuvent déjà accorder ce droit avec le groupe stockage ; aucune création ne s’exécute automatiquement pour autant.
3. Android11+ : le droit all-files existant est vérifié à nouveau lors de l’opération. Les restrictions Android/OEM restent effectives et peuvent encore faire échouer un mkdir.
4. Le dialogue montre la destination capturée et un nom à saisir ; seul le bouton Créer déclenche l’opération. Fermer/annuler avant confirmation n’écrit rien. Le nom est limité à256 unités de saisie puis validé :1–255octets UTF-8, encodage valide, pas de blanc aux extrémités, `/`, `\`, caractère de contrôle, `.` ou `..` seuls. Les espaces internes, accents et emoji valides sont permis.
5. Le contrôleur vérifie encore le backend et le dossier courant, le droit d’écriture puis utilise le mutex IO commun. Une action capturée dans un autre dossier est refusée, pas redirigée vers le dossier courant.
6. `StorageBoundary` canonicalise/vérifie le parent. Une seule tentative `File.mkdir()` crée un seul enfant : aucun `mkdirs()`, remplacement, suppression, renommage, création de fichier ou rollback automatique. L’opération ne réutilise pas un dossier déjà existant comme un succès.
7. Un fichier, dossier ou symlink existant n’est jamais remplacé par mkdir. Une collision concurrente doit donner un seul gagnant ; un symlink cassé peut produire l’erreur générique plutôt que « déjà utilisé », mais n’est pas supprimé.
8. Résultat typé : nom invalide, collision, accès refusé, destination indisponible, backend non pris en charge ou échec générique. Les erreurs errno détaillées ne sont pas toutes exposées par `File.mkdir()` ; accès, espace et limitations de nom peuvent donc aboutir au message générique.
9. En cas de succès, le dossier courant est actualisé, sans changer ses filtres. Un toast indique le nom créé et avertit que le filtre/cachés/la limite peuvent le masquer. Un échec garde le dialogue pour correction ; aucun écrasement proposé.

Pendant la tentative, les boutons et la fermeture du dialogue sont désactivés pour éviter les doublons et une fausse annulation. Un syscall mkdir commencé n’est pas annulable de façon transactionnelle : si le processus est tué ou l’activité détruite, vérifier le dossier avant de réessayer. Un dossier créé n’est jamais supprimé automatiquement pour simuler une annulation.

## Limites de sécurité et preuve

La création atomique d’un enfant ne constitue pas un parcours de chemins entièrement sans races face à un acteur hostile qui réécrit les parents entre canonicalisation et syscall. Aucun des tests ne prouve cette garantie ; des primitives de fichiers/capacités plus fortes seront nécessaires avant les opérations destructrices. Pas de contournement d’Android/data, de privilège root ou de Shizuku.

Les tests JVM ciblent noms/Unicode, collisions fichier/dossier avec marqueurs conservés, parents invalides/extérieurs, liens et concurrence. Les tests UI instrumentés utilisent uniquement un répertoire de fixture unique appartenant à Cantas dans des émulateurs jetables : sélection/retour/filtre, confirmation, nom invalide, collisions et annulation sans création. La suite SAF vérifie aussi la sélection et le refus explicite de création, sans WRITE persistant. Les résultats d’exécution sont dans les notes alpha04, pas inférés de la présence des tests.

Les tests ne créent rien sur le Tecno ; aucun ADB sur le téléphone. Les essais manuels de création doivent viser un dossier de test non sensible, après confirmation de la destination, sans utiliser les fonctions d’opération sur des données uniques tant que la validation matérielle manque.
