# Cantas 0.4.0-alpha04 — sélection multiple et création de dossiers locaux

Premier lot d’opérations non destructrices, pas une livraison complète copier/déplacer/supprimer. Application indépendante `app.cantas.jcversa`, versionCode4. Aucun remplacement de File Manager+ ni migration automatique.

## APK TEST vérifiée

- [Télécharger le ZIP alpha04](https://github.com/JCVERSA/rea/actions/runs/38971024911/artifacts/12076543358) : extraire `Cantas-0.4.0-alpha04-TEST.apk`. Accès au dépôt requis, rétention GitHub14jours.
- [CI finale38971024911](https://github.com/JCVERSA/rea/actions/runs/38971024911), tous les jobs réussis.
- Source exacte du build : `510a7abae23b67ba093d0ea30acd14be6f0bdf58` ; dernier changement Kotlin/tests avant ce suivi documentaire : `f16ff10c3b555dd4d86dfc2aec78f20e454e95b7`.
- SHA-256 CI : `2e0f76ab9771204cc14af0b239839fc5a28a9824eccc8a8b14ef452990e0f27e`.
- [Rapports, captures sélection/création et smoke](../analysis/cantas/38971024911/).

Le binaire n’a pas été téléchargé/re-haché dans le sandbox ; checksum issu de CI. Les notes postérieures ne modifient pas le code de cette APK.

## Ajouts

- **Sélection multiple locale et SAF**, par bouton, appui long ou case à cocher. En sélection, toucher un dossier ne l’ouvre pas.
- Compteur, sélection de tous les résultats **chargés** seulement, sortie par Terminer ou Retour. Aucun parcours récursif et aucune sélection de tout le téléphone.
- Réinitialisation sur navigation, filtre/tri/cachés, actualisation, chargement supplémentaire, changement d’onglet ou perte de grant. État ViewModel non persisté après mort du processus.
- **Création d’un dossier local** après saisie et confirmation de la destination ; validation UTF-8/nom, refus des noms existants sans remplacer ni modifier leur contenu. Pas de création récursive de parents manquants.
- Erreurs typées et affichées ; dialogue défilable, action clavier qui masque le clavier sans créer, anti-double soumission. Création sur IO via le mutex commun.
- Après succès, actualisation conservant les filtres et toast avertissant que ces filtres/la limite peuvent masquer le nouveau dossier.
- **SAF reste en lecture seule**, même si le fournisseur ou le système pourrait autoriser davantage. Aucun grant SAF WRITE demandé/conservé.

Détails des règles et limites : [premières opérations locales](CANTAS_OPERATIONS.md).

### Nouvelle permission héritée

`WRITE_EXTERNAL_STORAGE` est ajoutée avec **maxSdkVersion29** pour la création locale sur Android7–10. Si le droit manque, « Autoriser la création » le demande sur action explicite, puis Nouveau dossier ouvre le dialogue. Les anciens Android peuvent déjà avoir accordé ce droit avec le groupe stockage. Rien n’est créé automatiquement lors d’une autorisation.

Android11+ réutilise l’accès all-files déjà demandé explicitement ; sa présence est vérifiée au moment de l’opération. Les restrictions OEM/Android/data ne sont pas contournées. Pas d’INTERNET, root, Shizuku ou nouvelle dépendance dans ce lot.

## Vérifications exécutées

| Contrôle | Résultat final |
| --- | --- |
| Compilation, lint, assemblage | Réussis ; lint0erreur/19avertissements |
| Tests JVM | 21 réussis, 0échec |
| Android7/API24 x86 | 10 tests instrumentés réussis |
| Android12/API31 x86_64 | 10 tests instrumentés réussis |
| Noms/création JVM | Unicode, limites UTF-8, traversals/contrôles/noms invalides, parent absent/fichier/extérieur refusé |
| Collisions | Fichiers/dossiers avec contenu-marqueur conservé ; concurrence avec un seul gagnant ; symlinks non remplacés |
| UI sélection | Bouton/appui long, ensemble chargé, bascule sans navigation, Retour, remise à zéro filtre/onglet |
| UI création réelle | Confirmation, nom invalide désactivé, mkdir vérifié, collisions fichier/dossier, annulation sans dossier créé |
| SAF | Tests alpha03 conservés, sélection de1 000 entrées et création explicitement refusée ; grant READ sans WRITE |
| Smoke local | Fichier synthétique, sous-dossier vide, retour et captures ; logs crash vides sur les deux émulateurs |
| Contrat APK | ID distinct, min24/target35, pas d’INTERNET, WRITE limité à29, fixture absente du manifeste livré |
| Signature/ABI | V2 vérifiée ; graphics-path ARMv7, ARM64, x86 et x86_64 présents |

Tous les fichiers créés par les tests sont des fixtures isolées sur JVM/émulateurs jetables, jamais des données du téléphone. La présence d’ARMv7 n’est pas une exécution physique ARM32. Les ressources FR existent, mais l’exécution de la suite/captures est en anglais.

Les19avertissements lint restent visibles : politique all-files, target/dépendances plus récents disponibles, extraction de données, ressource de tri inutilisée, suggestions KTX et suggestion de ressource plurielle pour le compteur de sélection. Aucun avertissement masqué ni mise à niveau automatique.

### Essais précédents, non livrés

- 38968282479 : 21 JVM réussis,10/10 instrumentés API24 ; API31 échoue sur l’ancien test de thème (« No compose hierarchies »), les nouveaux tests sélection/création et SAF passent.
- 38969265700 : idem, l’attente de sélection seule ne suffit pas pour le contrôle suivant. Dialogue rendu défilable et clavier Done ajouté ; tests de création réussis sur les deux API.
- 38970194980 : API24 réussie ; étape emulator-runner API31 en échec avant tout rapport applicatif disponible, cause exacte non établie. Pas présenté comme un défaut Cantas ni comme une validation API31.
- Ajout d’attentes explicites sur les contrôles de thème et de diagnostics de fenêtre, assertions sélectionnées conservées. La tentative de relance GitHub CLI a été refusée ; le suivi README a redéclenché une exécution complète sans changement applicatif supplémentaire.
- 38971024911 : suite complète et livraison réussies. Pas de suppression d’assertion ni d’APK d’un run échoué livrée.

## Essai manuel conseillé sur le Tecno

1. Installer l’APK manuellement, sans ADB. Laisser File Manager+ installé.
2. Dans un dossier local **de test non sensible**, toucher Sélectionner, cocher/décocher, sélectionner les résultats chargés, puis Retour : aucun fichier ne doit avoir été ouvert ou modifié par la sélection.
3. Choisir Nouveau dossier, vérifier la destination affichée et créer un nom neuf. Sur Android7–10, autoriser la création si nécessaire puis rouvrir le dialogue. Sur le Tecno Android12, l’accès all-files peut être nécessaire.
4. Vérifier sa présence depuis les deux gestionnaires. Réessayer exactement le même nom : attendre le refus, sans remplacer son contenu. Annuler une autre saisie : vérifier l’absence de création.
5. Tester le français, le clavier, les thèmes et les noms longs. Sur une connexion SAF, vérifier la sélection mais l’absence de bouton Nouveau dossier.

**Signature TEST temporaire :** peut différer de l’alpha03 et bloquer une mise à jour directe. Ne jamais désinstaller File Manager+. Aucune désinstallation automatique de Cantas ; sauvegarder ses données privées/fichiers propres avant une éventuelle désinstallation manuelle. Celle-ci peut effacer ses données et nécessitera de réautoriser les connexions SAF.

## Limites restantes

Ni essai physique Tecno/ARM32/2Go, ni mesure mémoire/performance, accessibilité/font scale, français en exécution, Android7–15 complet ou véritable SD/USB ne sont prouvés. La persistance et les restrictions OEM restent à tester sur matériel.

Un mkdir commencé n’a pas d’annulation transactionnelle : le dialogue reste bloqué pendant sa tentative ; si le processus meurt, vérifier le dossier avant une nouvelle tentative. Aucun rollback supprimant un dossier n’est tenté. La frontière canonique n’est pas une garantie complète contre un acteur hostile modifiant les parents entre vérification et syscall.

Les scans/limites alpha03 restent inchangés : pas de pagination streaming ni de mémoire totale constante garantie. Toujours pas de copier/déplacer/renommer/supprimer, fichier vide, sélection persistante, corbeille, archive, lecteur interne, recherche globale, NAS/cloud/serveur ou Shizuku. Prochaine opération proposée : copie sans écrasement, avec progression, annulation et gestion explicite des fichiers partiels, avant tout déplacement ou suppression. [Registre de parité](CANTAS_ROADMAP.md).
