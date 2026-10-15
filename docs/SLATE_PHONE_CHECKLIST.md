# Contrôle manuel du TEST Slate sur Tecno BF6

Aucun ADB ni script à lancer sur le téléphone. Ne pas supprimer de fichiers pour tester.

## Avant installation

- Vérifier le nom `base-slate-safety-test.apk` et le SHA dans la livraison documentée.
- Signature TEST potentiellement différente : ne pas forcer un remplacement. Sauvegarder les réglages/connexions et les fichiers nécessaires des espaces privés/scopés avant toute désinstallation décidée manuellement ; Android peut les effacer à cette occasion.
- Ne pas envoyer de mots de passe, clés ou captures de préférences privées.

## Vérification rapide

1. **Accueil/navigation :** démarrage, icônes et accès existants présents, défilement fluide, tiroir utilisable, pas de texte tronqué anormalement en français ni de bouton inaccessible.
2. **Thème :** nouvelle installation sans choix enregistré → suit le mode Android. Mode sombre : fond d’accueil noir. Si un choix Light est déjà enregistré, il reste Light ; utiliser le réglage de thème existant si disponible. Le patch ne modifie pas les droits/licences de ce réglage.
3. **Fichiers :** naviguer dans les dossiers existants, afficher une image et lire un document non confidentiel. Ne pas effectuer de migration, restauration ou suppression de favoris pour ce test.
4. **Média :** fichier vidéo local, pause/reprise, seek, playlist ; sous-titre de même nom (éventuellement `.fr.srt`) ; lecteur externe habituel. Ces parcours ne sont pas couverts par les sondes synthétiques CI.
5. **Connexions :** si déjà utilisées, lecture d’un fichier non sensible sur le NAS/cloud/SMB/SFTP/WebDAV ; vérifier les vignettes et métadonnées. Les connexions lentes peuvent maintenant renvoyer des métadonnées vides après 30 secondes, sans annulation du transfert.
6. **FTP :** si utilisé, connexion habituelle, listing, lecture/copie d’un petit fichier synthétique uniquement. Limite de commande de contrôle 8 192 caractères ; pas de limite ajoutée à la taille du fichier transféré.
7. **Nextcloud :** compte valide et comportement en cas de certificat invalide, sans transmettre d’identifiants à un endpoint de test non fiable. Ne pas contourner les erreurs TLS pour terminer le test.

Retour utile : étape, écran, résultat attendu/observé, éventuel message d’erreur expurgé. Pas de promesse de compatibilité Android7–15 ou de release finale à partir d’un simple démarrage réussi.
