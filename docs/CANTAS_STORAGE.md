# Cantas — couche commune de stockage, alpha03

Depuis alpha04, les premières créations locales sont isolées de ce contrat : [sélection et opérations locales](CANTAS_OPERATIONS.md). Depuis alpha09, `SafFolders` permet une création de dossier unique avec connexion WRITE explicite, séparée du contrat de lecture : [périmètre et limites](CANTAS_ALPHA_09.md).

Depuis alpha10, `SafFileCopy` ajoute la copie de fichiers locaux/SAF vers SAF, isolée du navigateur : [limites, vérification et incertitude](CANTAS_ALPHA_10.md). Le contrat ci-dessous reste en lecture seule ; WRITE Android n’est utilisé que dans les backends séparés après choix explicite.

## Contrat volontairement en lecture seule

`ReadOnlyStorage` expose racine, liste filtrée/triée, parent, libellé et cible d’ouverture. Il n’expose **aucune** méthode de création, modification, déplacement ou suppression. Un backend n’est pas implicitement inscriptible parce que le fournisseur Android le permet. Les futures opérations devront avoir leurs propres capacités explicites, erreurs et protections ; la création SAF est maintenant isolée dans `SafFolders`, sans ajouter de mutation à ce contrat.

Les identifiants restent opaques à l’UI. Le backend local conserve des chemins canoniques à l’intérieur de `StorageBoundary`. Le backend SAF conserve des URI de documents construites par `DocumentsContract` et les parents réellement découverts, sans convertir un ID en chemin de fichier ni déduire un parent en coupant une chaîne.

`EntrySelection` applique la même recherche, visibilité, priorité des dossiers et sélection des meilleurs limite+1 résultats aux deux backends. Les alias locaux portant des noms distincts restent visibles, même s’ils partagent une cible canonique. Le contrôleur conserve les options pendant la session ; changement de dossier = requête vide et limite1 000. Il sérialise les listings avec un mutex pour éviter plusieurs tableaux locaux complets simultanés lors de frappes rapides.

## SAF : permission, identité et navigation

1. L’utilisateur ouvre le sélecteur Android `ACTION_OPEN_DOCUMENT_TREE` via le contrat Activity Result. L’annulation ne crée aucune connexion.
2. Cantas tente de conserver **uniquement READ** avec `takePersistableUriPermission`. Le sélecteur Android peut aussi fournir des permissions transitoires ; ce parcours READ ne demande pas de WRITE persistant. Depuis alpha09, une seconde action de connexion demande explicitement READ+WRITE, affiche ce niveau et permet un mkdir vérifié et, depuis alpha10, la copie de fichiers seuls vers cette destination. Elle ne transforme pas automatiquement les connexions existantes. Le grant WRITE Android couvre l’arbre, pas seulement mkdir ; déconnecter libère les deux droits. Voir les limites de confiance et de résultat incertain dans les notes alpha09.
3. La liste des connexions vient des autorisations URI persistantes détenues par l’application, pas d’un chemin `/storage/UUID` construit à la main. Les noms sont relus auprès du fournisseur sur IO ; un nom indisponible utilise l’ID opaque comme repli.
4. Seuls la racine et les enfants découverts sont acceptés. Le registre de navigation est limité à l’ascendance et aux enfants affichés ; pas d’index global croissant. Le fournisseur et Android restent responsables de l’application effective du grant d’arbre.
5. Le listing interroge ID, nom, MIME et taille via un curseur fermé avec `use`. Une taille inconnue vaut0 ; les dossiers ne sont pas pesés récursivement. Une réponse nulle, des métadonnées obligatoires absentes, un cycle ou un dossier disparu ne sont pas interprétés comme un dossier vide. Un fournisseur signalant encore `EXTRA_LOADING` impose une actualisation ; aucun observateur automatique n’est encore branché.
6. L’ouverture externe ne transmet que l’URI du fichier choisi et un grant READ sans PREFIX, WRITE ou permission persistante destinée au lecteur. Le MIME est demandé sur IO. Les URI entrantes du launcher restent ignorées.
7. « Déconnecter » demande confirmation avec le nom du dossier, puis libère les permissions persistantes de cette connexion. Ce n’est **pas** une suppression de fichiers, un démontage USB ni une révocation des droits qu’un lecteur externe aurait déjà reçus pour un fichier. En cas d’échec, l’accès peut rester conservé et un message le signale.

Aucune permission globale de stockage n’est nécessaire pour un arbre SAF choisi. Les restrictions Android du sélecteur restent applicables, notamment les racines et dossiers protégés sur les Android récents. Il faut un montage USB/OTG reconnu par Android et un fournisseur compatible ; Cantas n’intègre pas de pilote USB ni de contournement d’accès. Plusieurs dossiers peuvent être connectés. Au redémarrage, leurs grants sont redécouverts ; le dernier dossier et sa pile de navigation ne sont pas restaurés automatiquement.

## Annulation et limites de mémoire

Le travail se fait sur IO. Chaque listing contrôle l’annulation avant et entre les entrées ; le contrôleur refuse les succès et erreurs dépassés. Pour SAF, un adaptateur `suspendCancellableCoroutine` enregistre un `CancellationSignal` avant les requêtes de fournisseur. Les requêtes de noms des connexions utilisent le même mécanisme. Un fournisseur doit coopérer avec ce signal : Cantas ne peut pas tuer de force un fournisseur bloqué. Un fournisseur non coopératif peut retarder les listings suivants, sérialisés par le mutex.

Le backend local appelle encore `File.listFiles()` : son tableau complet et son blocage natif ne sont pas bornés/forcément interruptibles. Le backend SAF lit un curseur mais effectue encore une énumération complète pour le tri global ; ce n’est pas une pagination fournisseur. La sélection conserve limite+1 métadonnées, l’affichage monte par1 000 jusqu’à100 000 et chaque augmentation rescane. Aucune garantie globale de mémoire constante ou performance2Go n’est formulée.

Pendant un chargement, les lignes ne s’ouvrent pas ; une action issue d’une ancienne composition est contrôlée contre l’état courant avant navigation/ouverture. Aucun changement de fichiers n’est déclenché par un rafraîchissement, une déconnexion ou une erreur de montage.

## Tests et frontière de preuve

- Tests JVM du backend local et de la politique commune : frontières, symlinks, recherche, tri, limites et alias.
- Tests instrumentés de contrat SAF : vrai `ContentResolver`/curseur et fournisseur **synthétique dans l’APK de tests uniquement**, IDs non hiérarchiques, plus de1 000 lignes, lecture d’un flux de test, refus d’URI inconnues, erreur non assimilée au vide, signal d’annulation transmis à une requête lente.
- Test d’intégration : vrais grants URI Android persistants READ sans WRITE, redécouverte dans un nouveau ViewModel, UI commune avec permission globale explicitement absente au niveau du composant, navigation et libération du grant. Le test ne prouve pas un redémarrage complet du processus ni le fonctionnement du sélecteur système.
- Le contrôle APK refuse le fournisseur de fixture dans le manifeste de l’APK livré. La fixture n’expose aucun fichier personnel et n’est pas une fonction simulée de Cantas.

Les résultats exécutés et l’identité exacte de l’APK figurent dans les notes de livraison alpha03 une fois validés. Ces tests ne remplacent pas un essai manuel du sélecteur, d’une vraie carte SD, d’une clé USB, du retrait/remontage et d’une révocation dans les réglages OEM sur le Tecno. Aucun ADB sur le téléphone.

Cantas ne demande toujours pas INTERNET. Un fournisseur choisi par l’utilisateur peut néanmoins accéder au réseau et conserve sa propre politique de confidentialité ; SAF ne constitue pas une implémentation Cantas de NAS/cloud. L’absence de mutations dans ce contrat n’équivaut pas à un audit sécurité complet ou à une garantie d’absence de races du système de fichiers.
