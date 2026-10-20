# Cantas alpha09 — création de dossiers SAF avec accès explicite

**TEST vérifié, pas une version finale.** Version0.9.0-alpha09, code10. Source `46a200f76a4838c810fd64635602e58535013978`, [run39515597009](https://github.com/JCVERSA/rea/actions/runs/39515597009). Les cinq jobs ont réussi. [Alpha08 conservée](CANTAS_ALPHA_08.md).

**[Télécharger le ZIP TEST alpha09](https://github.com/JCVERSA/rea/actions/runs/39515597009/artifacts/12308127489)** : `Cantas-0.9.0-alpha09-TEST.apk` (APK debug0.9.0-alpha09-test), accès GitHub requis, rétention14jours. SHA-256 enregistré par CI : `1ef1fd9ab9191732ee4cce652ce7d328cbefb4079d6621e0340549e92ce3b320`, non recalculé localement. Rapports dans `analysis/cantas/39515597009/`.

## Fonctionnalité

Une seconde action de connexion ouvre le sélecteur Android en demandant explicitement READ et WRITE persistants. L’action historique reste une connexion READ ; elle ne transforme pas automatiquement les grants déjà présents. Choisir à nouveau une connexion READ ne rétrograde pas un grant WRITE préexistant : déconnecter explicitement puis reconnecter si ce retrait est souhaité. Les cartes de connexion indiquent lecture seule ou lecture/écriture. Le grant WRITE Android porte sur l’arbre choisi, pas sur la seule opération mkdir ; Cantas limite son implémentation actuelle à cette création. Pas de nouveau privilège manifeste, de permission INTERNET ou de dépendance.

Dans un dossier SAF avec connexion persistante READ+WRITE, le bouton Nouveau dossier ouvre la confirmation de création. Le backend est séparé de `ReadOnlyStorage` : aucune méthode d’écriture n’est ajoutée au contrat de lecture, aucun accès global au stockage n’est nécessaire pour cette opération. Les créations locales et copies vers le stockage local sont conservées.

Le dossier parent doit avoir été découvert par cette instance du navigateur (ou être sa racine), être encore un dossier et annoncer `FLAG_DIR_SUPPORTS_CREATE`. L’autorisation est vérifiée avant les lectures et immédiatement avant l’appel de création. Les identifiants restent opaques : pas de conversion en chemin local, de concaténation de document ID ni de supposition d’ascendance.

## Collisions et résultat incertain

- Un seul dossier, nom validé selon la politique locale existante : pas de slash/antislash, points seuls, contrôles, Unicode invalide, espaces extrêmes ou plus de255octets UTF-8.
- Scan des enfants, y compris cachés et sans filtre/recherche, avant création. Collision de nom détectée sans distinction de casse : refus, aucun appel de mutation. Cette politique peut refuser des noms distincts sur un provider sensible à la casse.
- Scans avant/après limités chacun à100000lignes et4M unités de métadonnées UTF-16 retenues,4096unités par champ. Les curseurs peuvent être préalloués par le provider ; aucune garantie de mémoire totale.
- Un seul `DocumentsContract.createDocument`, MIME dossier. Aucun appel rename/delete, aucun nettoyage automatique, aucun nouvel essai automatique.
- Réponse canonique du même arbre, ID nouveau distinct du parent et des enfants antérieurs, appartenance dans la liste des enfants, type et titre exacts, unicité du nom et absence de changement observable des ID/titres/types antérieurs vérifiés après création.
- Une erreur, un renommage automatique du provider, une réponse absente/étrangère ou une validation échouée après invocation produit **« création non vérifiée »**. Un dossier peut déjà exister. L’UI demande d’actualiser et d’inspecter ; la confirmation reste désactivée tant que ce résultat incertain est affiché. Rien n’est supprimé pour tenter une restauration.

Ces vérifications **ne sont pas une transaction SAF**, un verrou sur les autres applications ni une preuve d’absence de modification du contenu des fichiers préexistants. Le comportement du provider reste une frontière de confiance ; les modifications concurrentes et un provider malveillant ne peuvent pas être rendus atomiques par cette API. Ne pas présenter la pré-vérification comme une primitive portable `rename-no-replace` ou une garantie absolue de non-écrasement fournie par SAF.

Le mutex de listing/opérations du ViewModel sérialise ce parcours dans cette instance, pas tous les processus/appareils. Les scans coopèrent avec l’état d’annulation entre lignes ; les appels synchrones du provider ne sont pas interrompus et n’ont pas de délai garanti. Pas de journal durable de mkdir, de récupération après mort du processus ou de bouton d’annulation promettant de revenir avant création. Une fermeture forcée peut laisser un dossier dont l’UI n’a pas reçu le résultat.

## Validation vérifiée

**63tests JVM, zéro échec/ignoré ;34tests instrumentés sur chacun des émulateurs API24/x86 et API31/x86_64, les deux smoke runtime réussis.** Lint : **0erreur,29avertissements**, sans suppression. Identité indépendante, min24/target35, signature v2, absence d’INTERNET et quatre variantes natives graphiques dont ARMv7 vérifiées par CI. La capture API31 `alpha09-saf-folder.png` a été inspectée : nom `KEEP.txt`, refus de collision et explication du provider visibles à320×640. Quatre nouveaux tests backend : création opaque/non-ASCII et collisions fichier/dossier/casse ; READ seul et noms invalides sans mutation ; renommage automatique et exception après création avec conservation ; parent inconnu/annulation préalable/révocation et absence de capacité annoncée. Les octets du marqueur préexistant sont relus après création et après les erreurs incertaines. Un nouveau test UI fait créer un dossier, refuser la collision avec `KEEP.txt`, vérifie le nombre d’appels et capture `alpha09-saf-folder.png`. Le test READ historique continue de vérifier l’absence du bouton et le refus direct côté modèle.

Les premières tentatives39514169648/39514299900 ont échoué pendant la compilation de l’APK instrumenté : deux assertions utilisaient `size` au lieu de la propriété `bytes` d’une entrée. Corrigé, sans suppression d’assertion. Le run39514792876 a ensuite compilé mais échoué sur les deux émulateurs : le test READ historique lisait le résultat avant la fin du nouveau parcours asynchrone. Une attente de fin d’opération a été ajoutée, en conservant le refus attendu et toutes les assertions. Les logs Gradle sont désormais archivés avec `pipefail` pour conserver les diagnostics de compilation même lorsque le téléchargement direct des logs GitHub redirige vers un hôte inaccessible au sandbox.

XML, syntaxe Python et diff vérifiés localement ; Android/Kotlin et émulateurs via CI uniquement. Fixtures dans l’APK de test, absentes du produit, grants et réinitialisation limités au provider synthétique sur émulateur jetable. Les tests ne font aucun appel ADB au téléphone de l’utilisateur.

## Limites restantes

La création de dossiers n’est pas une implémentation de copie vers SAF, SAF→SAF, déplacement/renommage/suppression réels ou corbeille. Ces fonctions, archives, lecteurs internes, réseau/cloud, Shizuku et le reste du [contrat final](CANTAS_FINAL_ACCEPTANCE.md) restent ouverts. Pas d’essai système-picker de bout en bout, SD/USB/cloud réel, Tecno BF6 Android12 ARM32/2Go, français/accessibilité/performance, disque plein réel ni Android7–15 complet.

Essai manuel : dossier jetable dans le sélecteur Android via l’action lecture/écriture, créer un nom non existant puis tenter le même nom. Inspecter les sorties avant tout nouvel essai après un message incertain. Ne jamais désinstaller automatiquement une app ni supprimer des données pour installer ce TEST ; les signatures TEST temporaires peuvent différer.
