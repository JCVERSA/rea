# Cantas — jalons et parité fonctionnelle

Objectif utilisateur : nouvelle application indépendante, fonctionnellement équivalente à File Manager+, interface très différente, Kotlin/Compose/Material3, référence SwiftSlate et disposition inspirée de Buge. Usage personnel, sans pub/télémétrie, Shizuku facultatif. L'APK et les exports historiques ne sont ni supprimés ni inclus dans le nouveau produit.

## État après alpha05

La fondation est dans [`cantas/`](../cantas/). **Du code original compilable**, pas un autre patch smali ni une coque qui lance l'ancienne application. Le premier jalon est volontairement en lecture seule. La parité complète reste l'objectif final, pas un résultat obtenu par cet alpha.

| Domaine de parité | État actuel Cantas | Condition de validation future |
| --- | --- | --- |
| Accueil/navigation/thèmes | Implémenté dans la fondation | Captures clair/AMOLED, français/anglais, écran compact, TalkBack/font scale |
| Stockage principal local | Listing, dossiers/parent, refresh, erreurs | Permissions OEM, dossiers volumineux, fichiers non ASCII |
| Ouverture dans une application externe | Intent/FileProvider lecture seule implémenté | Lecteurs externes réels, permission effectivement limitée au fichier choisi |
| Cachés, tri et modes liste/grille | Choix cachés et tri nom A–Z/Z–A/taille décroissante ; dossiers d’abord, grille non implémentée | Parité des options de référence et scroll stable |
| SD, USB et SAF | Backend lecture seule, sélection Android, grants READ persistants, navigation et déconnexion implémentés ; tests synthétiques/grants/UI validés sur API24/31, matériel et sélecteur système à vérifier | Autorisations persistantes, montage/retrait, erreurs et URI opaques |
| Sélection multiple, copier/déplacer/renommer/créer/supprimer | Sélection des résultats chargés locale/SAF et mkdir local confirmé sans remplacement ; copie locale de1–100 fichiers sans écrasement livrée en alpha05 ; dossiers/SAF, déplacement/renommage/suppression et autres créations à implémenter | Fichiers synthétiques, collisions, annulation, reprise, pas de perte silencieuse |
| Corbeille/restauration | À implémenter | Politique explicite par backend ; restaurations et intégrité vérifiées |
| Favoris/récents/raccourcis | À implémenter | Persistance locale et migrations non destructrices |
| Recherche/filtres/catégories | Recherche par sous-chaîne dans le dossier courant ; recherche globale et collections à implémenter | Résultats réels, scans bornés et annulables, aucun IO lourd sur UI |
| Analyse du stockage | À implémenter | Calculs progressifs, prise en compte des restrictions, unités et erreurs |
| Archives/compression/extraction | À implémenter | Formats de référence à cataloguer précisément ; traversal, symlinks, bombes et encodages |
| Images, audio, vidéo, texte et autres aperçus | Lecteur externe uniquement | Lecteurs internes, formats, playlists, seek, grandes données, ARM32 |
| Sous-titres et ressources associées | À implémenter | Local, NAS/cloud, sidecars, lecteur externe, droits et durée de vie |
| Réseau local/NAS | À implémenter | SMB/SFTP/FTP/WebDAV selon catalogue vérifié ; réseau réel et erreurs |
| Cloud | À implémenter | Catalogue précis des fournisseurs de référence, OAuth/SDK/licences et consentement |
| Serveurs/partage local | À implémenter | Authentification, grants/révocation, quotas, longues sessions et compatibilité clients |
| Gestion des apps/APK, nouveaux fichiers et utilitaires de référence | À inventorier en détail puis implémenter | Confrontation écrans/source/usage, pas d'invention de fonctions |
| Shizuku facultatif | Non intégré à ce stade | Disponibilité/permission/binder death ; aucun changement si absent/refusé |
| Backends root éventuels | Non autorisés comme dépendance ; hors fondation | Ne pas confondre Shizuku shell avec root |
| Langues, accessibilité et animations | FR/EN et composants Material3 de base | Traductions, RTL, lecteurs d'écran, échelle0/1/ralentie, mesures Tecno |

Repères de catalogue réellement observés : [labels de la référence](../analysis/cantas/reference-feature-labels.json). Ils comprennent notamment Box, Dropbox, Google Drive, OneDrive, Yandex, Nextcloud, ownCloud ; SMB/SFTP/FTP/FTPS/WebDAV ; apps, nouveaux/récents, catégories de documents, ChromeOS, dual apps et stockage optique. La présence d'un label ne prouve pas que chaque fonction soit disponible sur le Tecno ni accessible dans toutes les configurations.

Les groupes ci-dessus sont un **registre de couverture initial**, pas l'inventaire exhaustif final de chaque comportement du binaire. Les formats/providers/options précis doivent être rapprochés de la référence avant d'annoncer une équivalence exacte.

Alpha05 TEST validé et livré : copie de fichiers locaux uniquement, sans écrasement, avec progression/annulation, fichiers incomplets conservés et journal privé. [État des CI et limites](CANTAS_ALPHA_05.md). Pas de copie de dossiers/SAF ni de déplacement/renommage/suppression.

Alpha04 validée : [livraison et preuves](CANTAS_ALPHA_04.md), [règles des premières opérations](CANTAS_OPERATIONS.md). La création locale ne rend pas SAF inscriptible.

Alpha03 validée : [livraison et preuves](CANTAS_ALPHA_03.md). Architecture : [contrat, permissions et limites SAF](CANTAS_STORAGE.md).

Alpha02 validée : [livraison et preuves](CANTAS_ALPHA_02.md). Affichage supplémentaire par1 000 jusqu’à100 000, mais `File.listFiles()` alloue encore le tableau complet et chaque augmentation rescane le dossier : ni pagination réelle ni garantie mémoire totale. Tri/cachés non persistés après relance du processus. Les raccourcis actuels ouvrent des dossiers usuels, pas des collections globales de médias.

## Ordre proposé

1. **Fondation native et navigation locale en lecture seule**, tests et premier APK TEST.
2. Couche commune lecture seule et SAF implémentés en alpha03 ; poursuivre capacités/erreurs typées, essais SD/USB physiques, pagination réelle, sélection, recherche globale et préférences.
3. Sélection et mkdir local livrés en alpha04 ; copie de fichiers locaux sans écrasement/progression/fichiers partiels livrée en alpha05. Poursuivre copie de dossiers/SAF et protections de chemins/reprise, puis déplacement/renommage/suppression, corbeille et favoris avec protections contre les pertes.
4. Archives et aperçus/lecteurs, formats et compagnons, test du pipeline ARM32.
5. NAS/cloud/serveurs/partage ; secrets Keystore versionnés, autorisations minimales et limites réseau.
6. Shizuku facultatif selon les capacités requises, validation OEM, accessibilité et performance ; fermeture du registre de parité.

L'ordre peut être ajusté selon les dépendances techniques, sans retirer des fonctionnalités promises en silence. Pas de migration de mots de passe/favoris depuis les données privées de l'ancienne application sans mécanisme explicite validé.

## Critères d'honnêteté de livraison

Chaque APK indique son périmètre réel. Compilation ≠ installation ≠ parité complète. Les émulateurs ne valident pas le Tecno ni les comptes réels. Les architectures natives empaquetées sont inspectées, et une validation ARM32 physique restera nécessaire. Aucun bug de l'ancienne application n'est reproduit volontairement sous prétexte de réplique exacte.
