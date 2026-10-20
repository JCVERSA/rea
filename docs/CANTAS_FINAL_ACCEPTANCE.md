# Cantas — contrat de sortie finale

L’utilisateur demande maintenant de poursuivre jusqu’à la version finale et à l’implémentation complète. Cette demande **ne** transforme pas les alphas en version finale et ne retire aucune fonction du registre de parité. Le périmètre de référence et les dépendances restent ceux de [CANTAS_ROADMAP.md](CANTAS_ROADMAP.md) et des inventaires de recherche.

## Règle de version

Ne pas publier/présenter une version1.0, « tout implémenté », « équivalent complet à File Manager+ » ou « terminé » tant que les groupes ci-dessous n’ont pas un inventaire détaillé fermé, une implémentation effective et des preuves adaptées. Une compilation verte, un écran ou un bouton ne suffit pas. Une fonction avec limites explicites dans une alpha n’est pas la preuve de toute la parité correspondante.

| Groupe | Travail restant pour une clôture finale |
| --- | --- |
| Opérations | Écriture vers SAF et SAF→SAF, déplacement, renommage, suppression et politiques par backend ; collisions sans perte, changements concurrents, confirmations, annulation et erreurs |
| Corbeille/restauration | Disponibilité par stockage, propriété des sorties, restauration sans écrasement, rétention et nettoyage explicite |
| Navigation et organisation | Favoris de dossiers et tri/cachés persistants livrés partiellement en alpha08 ; restent favoris de fichiers, récents, autres raccourcis/options d’organisation, grille, migrations/export éventuels et inventaire des utilitaires de référence |
| Recherche/catégories/analyse | Recherche globale réelle avec progression/limites visibles, catégories, analyse de tailles, scans annulables et restrictions expliquées |
| Archives | Formats à inventorier exactement ; création/extraction, traversals/liens/bombes, espace, interruptions et collisions |
| Lecteurs/éditeurs | Images, audio, vidéo, texte et formats de référence ; gros fichiers, sous-titres, grants de lecteurs externes, pipeline ARM32 |
| NAS/cloud | Inventaire des providers/protocoles, clients fiables, TLS/authentification/quotas/longues sessions, secrets Keystore et migrations non destructrices |
| Serveurs/partage | Fonctions de référence avec authentification, portée réseau explicite, révocation, quotas et sécurité des grants |
| Apps/APK et utilitaires | Inventaire et implémentation réelle des comportements autorisés, sans réutiliser d’assets propriétaires |
| Shizuku facultatif | Backend utile testé, disponibilité/permission/binder death/refus ; l’application reste utilisable sans lui et sans root |
| Fiabilité | Parcours de chemins et identités durcis avant opérations destructrices, état multi-instance/processus, reprise explicite, disque plein/révocations/démontages, stratégie mémoire |
| Interface | FR/EN en exécution, RTL, TalkBack, grandes polices, animations réduites, navigation et état restauré sans perte |
| Compatibilité | Android7–15 selon matrice de comportement, variantes natives, essais physiques Tecno BF6 Android12 ARM32/2Go, SD/USB, OEM et performances mesurées |
| Livraison durable | Signature privée stable sans clés dans Git/chat/artifacts, compatibilité des mises à jour, sauvegarde/migration explicites, checklist de non-régression et usage personnel |

## Ce qui nécessite une intervention externe

Les opérations autorisées sur émulateurs ne remplacent pas les essais manuels sur le téléphone de l’utilisateur. Aucun ADB sur ce téléphone. Les comptes cloud/NAS réels et certains comportements de cartes/OEM exigent des environnements de test autorisés ; ne jamais demander mots de passe, tokens ou clés privées dans le chat. La signature durable exige un canal de secrets adapté et autorisé, pas l’exposition d’une clé dans les sources.

Ces dépendances externes ne sont **pas** une excuse pour déclarer implémentées les fonctions manquantes ou pour arrêter les autres travaux disponibles. En cas de limite de session ou de blocage, consigner exactement le travail réalisé, les tests réellement exécutés et les fonctions encore absentes. Ne jamais annoncer une poursuite autonome après la fin d’un tour si aucun travail correspondant n’est effectivement en cours.

## Invariants maintenus

Application indépendante `app.cantas.jcversa`, pas de migration automatique depuis l’ancienne application ; Kotlin/Compose/Material3 ; FR/EN ; pas de publicité/télémétrie/compte obligatoire/backend imposé ; réseau seulement pour les fonctions choisies. Aucun retrait silencieux de fonctionnalité, désinstallation automatique, effacement personnel ou contournement de privilèges. Git et CI restent exclusivement sur `arena/3a37ecd7-rea`. Les livraisons intermédiaires portent clairement leur statut TEST et leurs limites.
