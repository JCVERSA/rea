# Cantas 0.3.0-alpha03 — stockage commun et SAF en lecture seule

Nouvelle étape de Cantas, application indépendante `app.cantas.jcversa`, versionCode3. Ni remplacement de File Manager+, ni migration automatique, ni parité complète annoncée.

## Livraison vérifiée

- [Télécharger le ZIP TEST alpha03](https://github.com/JCVERSA/rea/actions/runs/38956279066/artifacts/12069894627) : extraire `Cantas-0.3.0-alpha03-TEST.apk`. Accès au dépôt requis ; rétention GitHub14jours.
- [CI finale38956279066](https://github.com/JCVERSA/rea/actions/runs/38956279066), tous les jobs réussis.
- Source testée : `8d5ea9fb23a642a460ed0f8dae313533f46358ea`.
- SHA-256 produit par CI : `6ba181f7c0d24d35675b06c5415b38826d87c4f556b99831dd1f9f2131d71075`.
- [Rapports et captures réels](../analysis/cantas/38956279066/).

L’APK n’a pas été téléchargée ni re-hachée dans le sandbox : checksum issu de CI. Les modifications documentaires ultérieures ne changent pas le code de cette APK.

## Ajouts

- Contrat commun `ReadOnlyStorage`, utilisé par le stockage local et les arbres SAF ; politique commune de recherche, tri et limite d’affichage.
- Bouton **Connecter SD / USB / dossier** sur l’accueil et dans le panneau sans accès local. Le sélecteur de dossiers est celui d’Android ; aucun chemin de volume n’est deviné.
- Plusieurs connexions possibles, avec accès persistant **READ uniquement**. Les connexions sont redécouvertes depuis les grants Android au lancement. Le dernier dossier ouvert n’est pas restauré après mort du processus.
- Navigation, parent et retour dans l’arbre choisi ; IDs de documents opaques, sans conversion en chemins locaux. Recherche du dossier courant, cachés, tri et affichage supplémentaire réutilisent les contrôles alpha02.
- Ouverture du fichier choisi par URI avec grant externe READ seulement ; pas de grant PREFIX, WRITE ou persistant transmis au lecteur.
- Déconnexion avec confirmation nommée : libère le grant, **ne supprime aucun fichier et ne démonte pas le périphérique**. Ce n’est pas une révocation des droits déjà transmis à un lecteur externe.
- Consultation SAF utilisable sans autorisation globale des fichiers. Le sélecteur peut fournir des droits transitoires ; Cantas ne conserve pas de droit WRITE.
- Annulation des requêtes SAF via `CancellationSignal` ; listings sérialisés pour éviter plusieurs scans locaux complets simultanés. Pas de publication des résultats/erreurs dépassés, ni d’ouverture d’une ligne périmée pendant un chargement.
- FR/EN pour les nouveaux contrôles et messages. Aucun ajout de permission INTERNET, root ou Shizuku ; aucune nouvelle dépendance.

Voir [contrats, permissions et limites](CANTAS_STORAGE.md).

## Résultats exécutés

| Contrôle final | Résultat |
| --- | --- |
| Compilation, lint, assemblage | Réussis ; lint0erreur/18avertissements |
| Tests JVM | 15 réussis, 0échec |
| Android7/API24 x86 | 8 tests instrumentés réussis |
| Android12/API31 x86_64 | 8 tests instrumentés réussis |
| Contrat SAF sur chaque émulateur | Vrai ContentResolver/curseur, IDs non hiérarchiques, filtre/tri/limite, flux de fixture, refus d’URI inconnues, erreurs distinctes du vide |
| Annulation fournisseur | Signal reçu par une requête synthétique lente, test réussi |
| Grants et UI SAF | Vrai grant OS persistant READ sans WRITE, redécouverte dans un nouveau ViewModel, navigation UI avec permission globale déclarée absente au composant, libération du grant |
| Smoke local | Navigation/fichier/sous-dossier vide/retour réussis sur les deux émulateurs ; captures et logs crash vides |
| Contrat APK | ID indépendant, min24/target35, pas d’INTERNET ; fournisseur de tests absent du manifeste livré |
| Signature | APK Signature Scheme v2 vérifiée |
| Bibliothèque native graphics-path | ARMv7, ARM64, x86 et x86_64 présents |

Le fournisseur synthétique est dans **l’APK d’instrumentation uniquement**, pas dans l’application livrée. Il utilise du Java/framework autonome pour son processus séparé et une permission de lecture dédiée afin d’imposer de vrais grants, y compris sur API24. Aucune fixture ne lit de fichier personnel.

Les18avertissements lint restent visibles : politique all-files, ancien target/dépendances, règles d’extraction de données, ressource de tri inutilisée et suggestions KTX (dont les nouvelles conversions URI). Aucun avertissement n’a été masqué et aucune mise à jour aveugle de dépendance n’a été faite.

### Essais précédents conservés

- 38953978102 et38954575179 : compilation réussie mais instrumentation expirée après180s sur les deux API ; pas de sortie instrumentée conservée dans ces deux premiers rapports. Cause exacte de ces délais non établie. La fixture a ensuite été isolée de la runtime Kotlin de l’APK testé et la capture des diagnostics renforcée.
- 38955298803 : 15 tests JVM et7/8 tests instrumentés réussis sur chaque API. Le test d’intégration échouait pour grant non persistable disponible sur API24 et absence de hiérarchie Compose sur API31. Correction du montage de test : permission dédiée pour réellement exiger un grant et hôte MainActivity avec synchronisation Compose. Les assertions READ sans WRITE ont été conservées.
- 38956279066 : tous les contrôles finaux réussis. Seul ce run constitue cette livraison.

## Test manuel SD/USB sur le Tecno — à faire

Aucun ADB sur le téléphone. Utiliser un dossier non sensible déjà disponible, sans opération de suppression ni de migration.

1. Installer manuellement l’APK TEST. Laisser File Manager+ installé.
2. Brancher une clé USB/OTG ou insérer une carte SD reconnue par Android. Sur l’accueil Cantas, choisir **Connecter SD / USB / dossier** puis un dossier autorisé dans le sélecteur Android et confirmer. Faire défiler l’accueil si nécessaire.
3. Vérifier affichage, sous-dossier, parent/retour, recherche, tri/cachés et ouverture d’un fichier non sensible dans un lecteur externe.
4. Fermer puis relancer Cantas : vérifier que la connexion réapparaît sur l’accueil et qu’elle s’ouvre à nouveau. Ce test physique de relance est distinct du test automatisé de nouveau ViewModel.
5. Avec aucune opération en cours, retirer/remonter le support et actualiser ; vérifier le message d’indisponibilité puis la reconnexion. Respecter les consignes Android d’éjection.
6. Choisir **Déconnecter**, confirmer, vérifier que la connexion disparaît et que les fichiers sont toujours présents depuis le gestionnaire d’origine. Rechoisir le dossier pour reconnecter.
7. Vérifier le français, le clavier, les noms longs et les thèmes sur l’écran réel. La révocation dans les réglages OEM est aussi à essayer manuellement si elle est disponible.

Android doit monter le support et l’exposer via un fournisseur SAF. Cantas n’a ni pilote USB ni contournement des restrictions du sélecteur, notamment des racines et dossiers protégés des versions récentes. Un fournisseur choisi peut utiliser son propre réseau malgré l’absence d’INTERNET dans Cantas.

**Signature TEST temporaire :** elle peut différer de l’alpha02 et faire refuser une mise à jour directe. Aucune désinstallation automatique. Ne jamais désinstaller File Manager+ pour installer Cantas. Si une désinstallation manuelle de Cantas est choisie, sauvegarder d’abord ses données privées et ses fichiers de stockage propres ; les connexions seront à autoriser à nouveau. Aucune clé privée n’est distribuée.

## Limites et suite

Ni essai du sélecteur système de bout en bout, ni vraie carte SD/USB, redémarrage complet du processus, retrait/remontage, révocation OEM, Tecno ARM32/2Go, français en exécution, accessibilité ou Android7–15 complet ne sont prouvés par ces émulateurs.

Ce n’est pas une pagination streaming : le local alloue encore le tableau complet `File.listFiles()`, SAF parcourt tous les résultats pour le tri global et les fournisseurs peuvent allouer leurs propres données. La limite augmente par1 000 jusqu’à100 000, avec rescan. Un fournisseur ignorant l’annulation peut retarder les listings suivants. Aucune garantie de mémoire totale constante ni benchmark2Go.

Toujours aucune opération copier/déplacer/renommer/créer/supprimer, sélection multiple, corbeille, recherche globale, archive, lecteur interne, NAS/cloud/serveur ou Shizuku. Prochaine étape : capacités et erreurs adaptées aux opérations, sélection et protections contre collisions/pertes avant toute modification de fichiers. Les essais matériels SAF restent nécessaires en parallèle. [Registre de parité](CANTAS_ROADMAP.md).
