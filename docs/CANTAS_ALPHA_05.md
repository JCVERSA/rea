# Cantas alpha05 — copie locale de fichiers, TEST validé

**Livré après validation CI.** Version `0.5.0-alpha05` / code5 (`-test` dans le paquet debug), application indépendante `app.cantas.jcversa`.

**[Télécharger le ZIP TEST alpha05](https://github.com/JCVERSA/rea/actions/runs/38982114644/artifacts/12081244398)** : il contient `Cantas-0.5.0-alpha05-TEST.apk` et `SHA256SUMS`. Accès au dépôt GitHub requis, rétention14 jours. Usage personnel uniquement, pas de release publique.

Source binaire vérifié : `d58c88ed957d714f1bd49e7658925e63ba71849c`, [CI38982114644 réussie](https://github.com/JCVERSA/rea/actions/runs/38982114644). SHA-256 enregistré par CI, sans rehash binaire local :

```text
5e3f717ffc635f05e24c734c716fe2117b8513a2384b546972914f15cff9f3c5
```

31 tests JVM réussis,14 tests instrumentés sur chacun des émulateurs API24/x86 et API31/x86_64, smoke de navigation réussi sur les deux. Lint : **0 erreur,21 avertissements** non masqués (all-files, ancienne cible/dépendances,8 suggestions KTX,3 ressources de pluriels, ressource de tri inutilisée et configuration `fullBackupContent` antérieure à Android12 encore non définie malgré `allowBackup=false`). Signature APK v2 vérifiée ; bibliothèque graphique empaquetée pour ARMv7/ARM64/x86/x86_64 ; permission WRITE limitée à API29 et absence de permission INTERNET vérifiées. Cela ne prouve pas une exécution ARM32.

Rapports complets : `analysis/cantas/38982114644/`. Captures réellement inspectées : rapport de copie réussie sur API31 (`Copied (26 B)`) et collision sur API24 (`Already exists — skipped`). Les marqueurs source/destination sont vérifiés par assertions, pas déduits de la capture. [Alpha04 conservée](CANTAS_ALPHA_04.md).

## Périmètre implémenté

- Sélectionner 1 à100 fichiers locaux, puis Copier. Les dossiers ne sont pas acceptés ; la sélection SAF reste disponible mais sa copie reste hors périmètre.
- Le presse-papiers est un plan de session en mémoire. Naviguer vers un dossier local, toucher Coller ici et vérifier le chemin et les noms dans le dialogue avant de confirmer. L’autorisation d’écriture, si nécessaire, ne lance jamais la copie automatiquement.
- Source ouverte en lecture seule, type fichier régulier vérifié sur le descripteur. Destination ouverte par `Os.open` avec `O_CREAT | O_EXCL | O_NOFOLLOW`, sans `O_TRUNC` : aucun remplacement d’un nom déjà présent, y compris un lien symbolique. Les collisions sont rapportées par fichier, pas proposées à l’écrasement.
- Copie sur dispatcher IO avec tampon64Kio et limite100 fichiers, progression par fichier/octet, synchronisation du descripteur de sortie. Ce n’est pas une garantie de mémoire totale de l’application : les limitations de listing/Compose/providers restent celles des étapes précédentes.
- Annulation coopérative entre les appels IO. Elle ne peut pas interrompre instantanément un appel noyau bloqué. Les copies déjà terminées restent terminées ; les suivantes ne démarrent pas.
- Toute sortie créée mais incomplète/non vérifiée **reste sous son nom initial**. Aucune suppression, troncature d’un fichier préexistant, mutation volontaire du contenu source, tentative de rollback, déplacement ou renommage. Le rapport distingue succès, collision, annulation, non-tentative, changement détecté de source et échec. Après un échec d’écriture, le compteur ne prouve que les écritures complètes confirmées ; il peut être inférieur à la taille réelle de la sortie partielle.
- Journal privé `filesDir/copy-journal.json`, écrit avant les sorties utilisateur puis remplacé par le bilan. Il utilise AtomicFile, fsync explicite et vérification de relecture. Échec de préparation détecté : pas de copie. Échec d’enregistrement final : avertissement conservateur. Un journal incomplet/invalide entraîne un avertissement au démarrage, **pas une reprise automatique**.
- Acquitter le rapport efface uniquement ce journal privé. Cela ne nettoie aucun fichier source/destination. Les noms planifiés d’un journal interrompu peuvent appartenir à des fichiers qui existaient déjà ; ce n’est pas une liste de fichiers dont Cantas garantit la propriété.
- `allowBackup=false` conservé ; règles Android12+ d’exclusion cloud/transfert ajoutées pour les domaines utilisés. Pas de test de transfert OEM ni de promesse universelle de non-extraction.

## Limites importantes

Pas de copie de dossiers/récursive/SAF, pas de déplacement/renommage/suppression/corbeille, pas de reprise d’octet après arrêt, pas de service au premier plan ni garantie de survie en arrière-plan. Le presse-papiers ne survit pas à la mort du processus. Le journal est un avertissement/bilan pour la session normale, pas un gestionnaire multi-instance ou une base transactionnelle de toutes les opérations.

La validation canonique des parents n’est **pas** un parcours de chemins sans courses face à un acteur hostile qui les remplace pendant l’opération. `O_EXCL` protège le nom final contre l’écrasement ; cela ne prouve pas une isolation complète des ancêtres. Les métadonnées du descripteur source (identité, taille, date de modification en secondes) permettent certains constats de changement mais ne constituent ni snapshot immuable ni contrôle par hash ; une modification rapide de même taille peut échapper au contrôle. Les dates, permissions et autres métadonnées du fichier source ne sont pas recopiées comme contrat de fidélité. La présence dans les collections MediaStore/galerie n’est pas garantie immédiatement.

Android/OEM peut refuser certains chemins même avec all-files ; aucun contournement Android/data, root ou Shizuku. Rester dans un dossier de test non sensible et garder les originaux pendant cette phase.

## Validation et échec conservé

Premier source `a6da8a98530605a8329c32600907c1a666eacdb2`, [run38981412756](https://github.com/JCVERSA/rea/actions/runs/38981412756) : compilation, tests JVM et lint réussis ;14 tests instrumentés et smoke réussis sur API24. API31 :13/14 tests instrumentés réussis ; le parcours UI de copie échoue avant son premier appui long avec `No compose hierarchies found`. Les3 tests natifs de copie/journal réussissent sur les deux API. Livraison bloquée, aucun APK alpha05 proposé comme validé. Rapports conservés dans `analysis/cantas/38981412756/`.

Source de reprise `d58c88ed957d714f1bd49e7658925e63ba71849c`, [run38982114644](https://github.com/JCVERSA/rea/actions/runs/38982114644) : attente explicite des nœuds Compose après recréation et chargement, diagnostics de fenêtre/capture en cas d’échec, assertions et14 tests conservés. Deux tests JVM supplémentaires couvrent fichier vide et créateurs concurrents ; tests attendus31 JVM/14 instrumentés par API. fsync/relecture du journal, explication de l’acquittement et périmètre visible en sélection ajoutés. **Reprise réussie : tous les jobs build/API24/API31/deliver/publish sont verts. Les31 tests JVM et14 tests instrumentés par API sont confirmés par les rapports archivés.**

Le protocole couvre les octets copiés, les sources conservées, les collisions fichier/dossier/lien, les sorties partielles d’une annulation/écriture simulée en échec, les lots invalides, les changements de source détectés, les limites et la reconstruction du journal. Le test UI passe par sélection, presse-papiers, navigation, confirmation, copie réelle et refus d’un nom occupé. Ce n’est pas un test de clic Annuler pendant une copie lente réelle, de coupure forcée du processus, de disque réellement plein, de transfert OEM, de multi-instance, de carte SD/USB ou du Tecno ARM32/2Go. Aucune exécution locale Kotlin/Android : absence de JDK/SDK et accès Maven/Google/Gradle non disponible dans le sandbox ; validation binaire en CI.

## Essai manuel futur (après livraison validée)

1. Installer uniquement le TEST Cantas confirmé, sans désinstaller l’ancienne application. Si la signature TEST diffère, ne rien désinstaller avant mise à l’abri des données privées utiles.
2. Créer avec les outils habituels un petit répertoire de test et deux petits fichiers jetables. Garder les originaux et une copie de sécurité hors de la destination.
3. Sélectionner un fichier local, Copier, ouvrir un autre dossier de test, Coller ici. Vérifier le chemin complet puis confirmer. Examiner le rapport et comparer le contenu source/destination avec un outil de confiance.
4. Refaire vers le même nom : attendre un résultat « existe », avec contenu préexistant inchangé. Une collision n’est pas un succès de copie.
5. Pour l’annulation, n’utiliser qu’un fichier jetable et une destination vide de test. Un fichier incomplet peut rester même après annulation ; l’acquittement ne l’efface pas. Ne pas éteindre volontairement le téléphone pendant une opération sur des données utiles.
6. Signaler les messages/rapports observés. Aucun ADB sur le téléphone ; pas de test sur des données uniques.
