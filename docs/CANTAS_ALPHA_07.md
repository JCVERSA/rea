# Cantas alpha07 — import SAF vers stockage local

**TEST personnel validé et livré ; pas une version finale.** Version `0.7.0-alpha07`, code8. Source `f79d67b4eac0674c0bb03ed218c0c071a09c5da3`, [CI39079535597](https://github.com/JCVERSA/rea/actions/runs/39079535597). Livraison antérieure conservée : [alpha06](CANTAS_ALPHA_06.md).

**[Télécharger le ZIP TEST alpha07](https://github.com/JCVERSA/rea/actions/runs/39079535597/artifacts/12118436850)** : `Cantas-0.7.0-alpha07-TEST.apk` et `SHA256SUMS`, accès au dépôt requis, rétention14 jours.

SHA-256 enregistré par CI, sans rehash binaire local :

```text
b46a826221996bb603223dbfce6b09fdf72dae522fe1a60075ceb16b8d7dc002
```

Tous les jobs build/API24/API31/deliver/publish réussis. **55 tests JVM,0 échec/ignoré ;22 tests instrumentés sur chacun des émulateurs API24/x86 et API31/x86_64 ; les deux smoke réussis.** Lint : **0 erreur,22 avertissements** conservés, sans suppression. Signature v2 vérifiée, bibliothèque graphique pour quatre ABI dont ARMv7, identité indépendante/min24/target35/WRITE max29/règles de sauvegarde et absence de permission INTERNET vérifiées.

Rapports : `analysis/cantas/39079535597/`. La capture API31 `alpha07-saf-import.png` a été inspectée : dossier ImportFolder, fichier connu et flux de taille inconnue copiés (7 octets chacun), dossier Empty créé. Les assertions vérifient les octets et le grant READ sans WRITE ; les captures seules ne suffisent pas à prouver ces propriétés. La demande de version finale est formalisée dans [le contrat de clôture](CANTAS_FINAL_ACCEPTANCE.md), **non satisfait à ce stade**.

## Périmètre

Sélectionner des fichiers/dossiers dans une connexion SAF ayant un grant READ persistant, Copier, naviguer vers un dossier **local**, Coller ici puis confirmer. Le presse-papiers garde l’arbre d’autorisation et les URI opaques sélectionnées ; une navigation vers le stockage local ne les convertit pas en chemins.

L’import prépare tout le plan avant les écritures, inclut les enfants cachés, conserve les dossiers vides, refuse les noms invalides, doublons/cycles, documents virtuels, providers encore en chargement, limites dépassées et métadonnées indisponibles. Limites :100 racines,1 000 entrées, profondeur32. Le plan est revalidé avant exécution. Pas de troncature silencieuse du plan.

Destination locale exclusive avec les protections alpha06 : aucun écrasement, aucune fusion de dossier existant, enfants ignorés si le parent n’a pas été créé par cette tentative. Les créations de dossiers ne sont pas déclarées comme copies complètes de tous les descendants. Annulation coopérative, progression, sorties partielles conservées, journal privé d’avertissement/bilan, jamais de reprise ou nettoyage automatique.

Le backend SAF n’appelle **aucune** API de création/renommage/suppression/écriture de document et n’escalade pas les grants en WRITE. `openAssetFileDescriptor` est appelé avec `r` et une CancellationSignal, puis le flux AssetFileDescriptor respecte sa plage éventuelle. Le grant est contrôlé à nouveau avant la préparation ; Android fait aussi respecter les autorisations à chaque accès au provider.

Les requêtes de métadonnées/enfants et l’ouverture du flux partagent un CancellationSignal. Annuler le transmet hors du thread UI ; la destruction du ViewModel tente également de le transmettre. Une lecture déjà bloquée sur un descripteur ne peut pas forcément être interrompue par ce signal : providers et kernel doivent coopérer. Aucun délai d’annulation absolu garanti.

## Taille inconnue et sécurité des chemins

Une taille connue borne l’écriture comme en0.5.1. Pour une taille inconnue, le même moteur de flux accepte EOF avant un budget explicite de **512 Mio par fichier**. Une sonde d’un octet supplémentaire n’est jamais écrite ; si le flux dépasse le budget, la sortie conservée est signalée non vérifiée, pas comme copie complète. Les lecteurs sans progrès échouent. Le budget ne garantit ni l’espace disponible ni une limite totale pour le lot ; un échec de stockage peut laisser des sorties partielles.

Aucun identifiant de document n’est découpé comme chemin. L’adaptateur ne décrit/ouvre que ses racines sélectionnées et les enfants découverts, avec arbre/autorité/URI vérifiés. Les chemins relatifs de sortie sont validés avant toute création ; les entrées locales sources ne sont jamais lues par l’adaptateur d’import.

**Limite importante :** un identifiant SAF opaque peut désigner le même stockage physique que la destination locale sans révéler de correspondance portable. L’import ne prétend donc pas prouver leur séparation physique. Choisir une destination de test séparée. Le plan est fixé avant les écritures, sans nouvelle énumération pendant la copie, ce qui évite une croissance récursive due aux créations ; les sorties existantes sont toujours protégées. Ce n’est pas une garantie d’isolation contre un provider malveillant, un remplacement d’ancêtres ou des modifications concurrentes.

## Ce qui n’est pas livré

Écriture **vers** SAF, copie SAF→SAF, déplacement, renommage, suppression/corbeille, archives, lecteurs internes, recherche globale, favoris, analyse du stockage, NAS/cloud/serveurs propres et Shizuku restent à implémenter. Un provider SAF cloud peut télécharger selon sa politique ; cela ne constitue pas un backend cloud Cantas, et aucune permission INTERNET n’est ajoutée à Cantas.

Pas de snapshot/hash immuable, service au premier plan, reprise transactionnelle/multi-instance, promesse de mémoire totale bornée des providers ou nouvelle garantie de transfert OEM/sauvegarde. Les limites alpha06 restent applicables.

## Validation exécutée

55 tests JVM et22 tests instrumentés par API24/API31 confirmés réussis dans les rapports archivés. Nouveaux cas : arbre opaque/fichier/dossier vide, tailles inconnues et EOF/budget, conservation de marqueurs et non-fusion, cycles/doublons, racine renommée, annulation et plans invalides. Sur Android : grant révoqué avant préparation, arbre forgé refusé, cancellation d’une requête réellement bloquée dans un provider séparé, import UI d’un dossier avec flux pipe de taille connue et inconnue, dossier vide, puis collision sans fusion et grant toujours READ seul. La fixture reste uniquement dans l’APK de test.

Contrôles XML/Python/diff locaux réussis. Pas de compilation Kotlin/Android locale (JDK/SDK et dépôts réseau non disponibles) ; compilation et exécution émulateur confiées à CI. Les résultats ci-dessus proviennent des rapports effectivement inspectés, pas de la seule présence des tests. Toujours aucun test sur le Tecno ARM32/2Go, SD/USB physique, vrai compte cloud, français/accessibilité/performance, sauvegarde OEM ou coupure forcée du processus. Aucun ADB sur le téléphone.

## Installation et essai après validation

Ne pas désinstaller automatiquement l’ancienne application ou Cantas. Les clés TEST temporaires peuvent varier ; mettre les données privées utiles à l’abri avant toute décision manuelle de remplacement. Après livraison validée seulement, utiliser une connexion SAF avec données jetables et une destination locale de test séparée, vérifier le chemin confirmé, le rapport et les octets avec un outil de confiance. Conserver les originaux ; fermer le rapport ne nettoie aucun fichier.
