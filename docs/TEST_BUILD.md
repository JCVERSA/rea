# APK TEST — témoin avant release

Autorisé par l’utilisateur : un build de test temporairement signé peut être livré avant la configuration d’une clé privée durable. **Ce n’est pas une release.** Aucune clé privée n’est livrée et aucune GitHub Release n’est créée.

## Build livré — run 38667633766

**[Télécharger l’artifact TEST (ZIP contenant l’APK)](https://github.com/JCVERSA/rea/actions/runs/38667633766/artifacts/11942766815)** — connexion GitHub autorisée au dépôt éventuellement nécessaire.

- Fichier : `base-witness-test.apk`, 23 379 172 octets.
- SHA-256 APK : `d2b838724e1b52fe0de056b93e32618679c0391d083f23d9cf8494861bb5e050`.
- [CI réussie](https://github.com/JCVERSA/rea/actions/runs/38667633766) : invariants, signatures, alignement et démarrage de l’original/témoin réussis. 5 tests provider et 7 tests outils exécutés en CI.
- Source : `50a9e4f`. Manifeste et ressources compilées inchangés ; aucun correctif fonctionnel. Test physique Tecno non exécuté.

## Télécharger

Le workflow `Unchanged witness build and isolated startup checks` fournit, après réussite de tous les contrôles, l’artifact **`TEST-ONLY-witness-<run-id>`**, contenant **`base-witness-test.apk`**. Ne pas utiliser l’artifact `internal-unsigned-witness` : il n’est pas installable.

Le lien du run et les rapports sont conservés dans `analysis/witness/<run-id>/`. Les artifacts expirent après 14 jours. Les rapports donnent le SHA-256 de l’APK réellement testé.

## Ce que ce build contient

- Code smali réassemblé, sans correctif fonctionnel.
- Manifeste et ressources compilées d’origine conservés octet par octet, assets vérifiés.
- Signature temporaire nouvelle à chaque run si aucune clé stable n’est configurée.
- Contrôles apksigner/alignement, invariants et installation/démarrage sur émulateur Android 12.

**Pas encore :** correctifs TLS/HTTP/crypto, redesign, validation Tecno/ARM32, validation complète de toutes les fonctions. La recompilation XML n’est pas validée : les 20 IDs Material remappés restent un chantier distinct.

## Installer manuellement sur le Tecno BF6

1. Télécharger et extraire l’artifact ZIP pour récupérer l’APK, puis ouvrir l’APK avec l’installateur Android. Autoriser cette source si nécessaire.
2. La signature diffère de l’original installé : une mise à jour en place peut être refusée. **Ne pas désinstaller précipitamment.** Sauvegarder/exporter ce qui est nécessaire et décider explicitement d’un remplacement. Une désinstallation peut effacer réglages, favoris, connexions et fichiers propres à l’application ; ne pas supposer que tout est préservé. Ne supprimer aucun fichier personnel pour ce test.
3. La signature change aussi entre deux runs temporaires et avec la future release : pas de continuité des mises à jour garantie.
4. Commencer hors ligne, sans compte cloud réel, avec un dossier de fichiers jetables.

Le build a les risques de sécurité déjà identifiés dans la base : ne pas le considérer comme corrigé ou prêt pour les comptes/fichiers sensibles.

## Retour attendu, sans ADB

- Installation : succès, ou **texte exact** du message d’échec.
- Démarrage et relance : normal, blocage ou fermeture ?
- Permissions : accord/refus et accès au stockage.
- Dossier jetable : copie, déplacement, renommage, annulation, conflit et suppression/restauration si disponible.
- Ouverture d’image, texte et média local ; partage vers une autre application.

Ne transmettre ni identifiants, ni fichiers personnels, ni capture contenant des secrets. Aucun effacement ou test sur le téléphone n’est automatisé.

## Suite prévue

1. Valider ce témoin sur le téléphone avant de mélanger d’autres changements.
2. Corriger TLS Nextcloud puis le serveur HTTP média, avec cas de compatibilité et tests séparés.
3. Traiter progressivement identifiants, limites réseau, attentes sans délai et migrations, sans enlever de fonctionnalités.
4. Résoudre la chaîne de recompilation des ressources, puis moderniser l’accueil/navigation en Views/XML, thèmes clair/sombre AMOLED et animations respectant le système.
5. Configurer une clé privée stable et vérifier la version finale avant toute release privée. La validation Android 7–15, ARM32 et les scénarios complets restent à planifier ; un émulateur Android 12 ne suffit pas.
