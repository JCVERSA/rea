# Cantas 0.2.0-alpha02 — deuxième livraison TEST

Application indépendante `app.cantas.jcversa`, versionCode2. Aucun remplacement de File Manager+, aucune migration automatique. La parité complète reste un objectif par étapes ; cet alpha reste en lecture seule.

## Ajouts fonctionnels

- Recherche par sous-chaîne, sans distinction de casse, **dans le dossier courant uniquement**. Pas de recherche récursive/globale. Saisie temporisée180ms, effacement rapide et action clavier Recherche.
- Tri nom A–Z, nom Z–A ou taille de fichier décroissante, dossiers toujours placés avant les fichiers. Les dossiers ont une taille0 : ce n’est pas une analyse récursive de leur poids.
- Interrupteur pour les noms commençant par un point, masqués par défaut.
- Affichage initial de1 000 résultats, bouton pour augmenter par1 000 jusqu’à100 000 ; ordre calculé sur tous les résultats correspondants, pas uniquement sur un sous-ensemble arbitraire.
- Travail de listing/métadonnées sur IO ; gardes d’annulation empêchant un résultat ou une erreur dépassés de remplacer la requête courante.
- Contrôles traduits FR/EN. Navigation vers un autre dossier réinitialise la recherche et la limite ; tri/cachés restent pendant la session mais ne sont pas persistés après relance du processus.

**Limite importante :** `File.listFiles()` alloue toujours un tableau de tous les enfants avant filtrage. La file de priorité borne les métadonnées retenues à limite+1, pas la mémoire totale du scan. Chaque affichage supplémentaire rescane le dossier. L’annulation ne force pas l’interruption de l’énumération Java bloquante. Aucun benchmark sur dossiers géants ou Tecno2Go n’est revendiqué.

## APK et preuve exacte

- Source application : `8f24ff96246c62a6dc987a709a3069efb5c1d327`.
- [Exécution finale38681612415](https://github.com/JCVERSA/rea/actions/runs/38681612415), tous les jobs réussis.
- [Télécharger le ZIP TEST](https://github.com/JCVERSA/rea/actions/runs/38681612415/artifacts/11948193347) : `Cantas-0.2.0-alpha02-TEST.apk`, conservation GitHub14jours, accès au dépôt requis.
- SHA-256 enregistré par CI : `25704b7607a540c03958f87ad10e0219b6c716025102380c5c7845af71f6c4ad`.
- Rapports/captures : [`analysis/cantas/38681612415/`](../analysis/cantas/38681612415/).

Le binaire n’a pas été téléchargé ni re-haché localement dans le sandbox : checksum issu du build CI. Les modifications documentaires postérieures ne changent pas le code de cette APK.

## Vérifications réalisées

| Vérification | Résultat final |
| --- | --- |
| Compilation, lint, assemblage | Réussis ; lint0erreur/12avertissements |
| Tests JVM filesystem | 10 réussis, sans échec |
| Android7/API24 x86 | 3 tests instrumentés réussis |
| Android12/API31 x86_64 | 3 tests instrumentés réussis |
| UI réelle | Recherche/effacement/action clavier, cachés, tri ; navigation/thèmes |
| Backend Android réel | Filtre, tri et affichage limité sur fixture dédiée |
| Smoke sur chaque émulateur | Dossier/fichier synthétique, sous-dossier vide, retour ; rapports runtime positifs et capture |
| Identité/permissions | ID indépendant, min24/target35, aucune permission INTERNET |
| Signature | Vérification APK Signature Scheme v2 réussie |
| Bibliothèque native graphics-path | ARMv7, ARM64, x86 et x86_64 présents |

Les avertissements lint comprennent politique all-files, ancien target/dépendances, règles d’extraction de données, ressource de libellé de tri inutilisée et suggestions KTX. Ils ne sont ni supprimés ni assimilés à une validation sécurité complète.

Un premier essai alpha02,38680800822, a échoué dans l’étape emulator-runner API24 ; cause exacte non établie. L’essai intermédiaire38680969710 a ensuite réussi les deux émulateurs, puis le run final ci-dessus a validé les gardes supplémentaires d’annulation et le dixième test JVM. Cette livraison n’utilise pas le binaire intermédiaire.

## Test manuel sur le téléphone

1. Télécharger/extracter le ZIP et installer l’APK TEST manuellement ; aucun ADB sur le Tecno.
2. Laisser File Manager+ installé. Accorder explicitement l’accès stockage à Cantas si souhaité.
3. Dans un dossier connu, essayer une partie de nom, effacer la recherche, changer le tri, activer/désactiver les cachés, puis naviguer et revenir. Le bouton d’affichage supplémentaire apparaît seulement si plus de résultats correspondent à la limite.
4. Vérifier aussi clavier/retour, thème clair/AMOLED, noms longs et interface française. Ces essais physiques restent à faire.

**Signature TEST temporaire :** elle peut différer de l’alpha01. Android peut alors refuser la mise à jour. Ne jamais désinstaller File Manager+ ; aucune désinstallation automatique de Cantas. Si une désinstallation manuelle de Cantas est choisie, sauvegarder d’abord ses données privées et ses fichiers de stockage propres : Android peut les supprimer. Aucune clé privée n’est distribuée.

## Ce qui reste hors périmètre

Pas encore de copier/déplacer/renommer/créer/supprimer, sélection multiple, corbeille, recherche globale, pagination streaming, SAF/SD/USB, archives, lecteurs internes, NAS/cloud/serveurs ou intégration Shizuku. Aucun test physique ARM32/Tecno, benchmark2Go, validation complète Android7–15, français en exécution, accessibilité ou ouverture externe de bout en bout n’est revendiqué. Les émulateurs et la présence des ABI ne prouvent pas ces points.

La prochaine étape proposée est la couche commune de stockage et SAF/SD/USB avant les opérations modifiant des données. [Registre de parité et jalons](CANTAS_ROADMAP.md). Les sources, preuves et notes alpha01 restent conservées.
