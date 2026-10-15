# Cantas — recherche interface, animations et Kotlin

Date de consultation : **14 octobre 2026**. Projet envisagé : `app.cantas.jcversa`, Kotlin/Compose/Material3, français/anglais, Android7–15 visés, priorité Tecno Android12 ARM32/2Go. Recherche uniquement : aucune dépendance installée, aucun nouveau module applicatif créé, aucun projet exécuté ou benchmarké.

## Conclusion

**Recommandation :** combiner l’identité et la politique de mouvement de SwiftSlate, les principes de disposition de Buge, les exemples Compose officiels et une architecture Kotlin à flux d’état unidirectionnel. Éviter une collection de bibliothèques d’animations et le clonage complet d’un autre projet. Les mouvements essentiels peuvent être écrits avec les API Compose/Material3 déjà nécessaires.

13 dépôts consultés, avec niveaux différents : métadonnées/README/build pour la sélection ; lecture ciblée de code pour SwiftSlate, Buge, Jetsnack, AndroidX et un exemple Canopas ; documentation d’architecture de Now in Android. **Ce n’est pas un audit exhaustif des 13 projets.** Les fichiers lus sont archivés dans `source-excerpts.zip` avec index SHA-256, sans modification de leur contenu. Commits, licences déclarées, fichiers récupérés et limites sont conservés dans [`analysis/cantas-research/`](../analysis/cantas-research/).

## 1. Références de conception prioritaires

| Projet | Constat vérifié | Application proposée à Cantas | Licence observée |
| --- | --- | --- | --- |
| [SwiftSlate-ng](https://github.com/JCVERSA/SwiftSlate-ng/tree/9ab06335d0b80df771e3b2db4b6499c6f25ba148) | Compose/Material3 ; `Theme.kt` lavande et AMOLED ; `SlateMotion.kt` centralise springs/tweens/snap et observe les échelles système | Source principale pour les tokens, états visuels et politique de mouvement ; adaptation au gestionnaire de fichiers, pas reprise de son métier | MIT |
| [Buge App Manager](https://github.com/BugeStudioTeam/Buge-App-Manager/tree/a503ff5c56713c22cf633ebe7234de64e053af3b) | Kotlin avec Views/XML. Layout principal : navigation inférieure et rail alternatif. Ligne d’app : icône48dp, textes hiérarchisés, sélection, hauteur minimale72dp. Helper de springs fondé sur Views | Réinterpréter la structure en Compose : navigation principale, listes lisibles, sélection et détails. Ne pas porter le helper Views dans Compose | GPL-3.0-only dans les en-têtes examinés |
| [Compose Samples / Jetsnack](https://github.com/android/compose-samples/tree/a8712b8a641aa54f66a0f7e830b84521a5b6a84e) | Dépôt officiel de samples ; `Home.kt` de Jetsnack utilise Material3, transitions de navigation, animation de sélection et springs expressifs | Exemple concret pour navigation, sélection et transitions cohérentes. Pas copier son interface de restauration ni toutes ses animations | Apache-2.0 |
| [Now in Android](https://github.com/android/nowinandroid/tree/a49ed253d75e61a2b6ab80a8da677b57437b08eb) | Documentation détaillée UI/data/domain, flux d’état unidirectionnel, modularisation, tests et baseline profiles | Architecture de référence et méthode de mesure ; démarrer avec peu de modules plutôt que reproduire tout son outillage | Apache-2.0 |

### Différences importantes entre les deux références demandées

- **Buge n’est pas une application Compose** dans la configuration inspectée : plugins Kotlin Android et layouts XML. Son `minSdk` est26, soit Android8. Un fork intégral de sa configuration ne remplit donc pas notre objectif Android7/API24.
- SwiftSlate déclare `minSdk23`, `compileSdk37`, `targetSdk36`, Kotlin Compose plugin2.4.10 et Compose BOM2026.08.00 dans le snapshot consulté. Ce sont des **valeurs du dépôt, pas une validation de disponibilité ni une sélection de versions pour Cantas**.
- `compileSdk` n’est pas le minimum Android installé. La compatibilité dépend notamment du `minSdk`, des dépendances effectivement choisies et des API utilisées avec leurs gardes.
- Pour Cantas : même famille technique que SwiftSlate, disposition inspirée de Buge, implémentation indépendante et vérification ultérieure des versions Gradle/AGP/Kotlin/Compose.

## 2. Animations : ce qui est retenu et ce qui est écarté

### Sources examinées

1. [Politique de mouvement SwiftSlate](https://github.com/JCVERSA/SwiftSlate-ng/blob/9ab06335d0b80df771e3b2db4b6499c6f25ba148/app/src/main/java/com/jcversa/swiftslate/ui/components/SlateMotion.kt) : specs séparées pour mouvement spatial, effets et transitions de taille ; `snap()` lorsque les animations sont désactivées ; observation des réglages globaux.
2. [AndroidX MotionDurationScale](https://github.com/androidx/androidx/blob/ffadc46c08a3423475e126935917bfa2cf91b9ba/compose/ui/ui/src/commonMain/kotlin/androidx/compose/ui/MotionDurationScale.kt) et [WindowRecomposer Android](https://github.com/androidx/androidx/blob/ffadc46c08a3423475e126935917bfa2cf91b9ba/compose/ui/ui/src/androidMain/kotlin/androidx/compose/ui/platform/WindowRecomposer.android.kt) : Compose dispose déjà d’une échelle de durée et d’une observation du réglage système. Une échelle0 termine le mouvement au prochain frame callback selon le contrat documenté, pas une garantie de travail CPU nul.
3. [Canopas Compose Animations](https://github.com/canopas/compose-animations-examples/tree/67da54a12ef3a341701f287930d7743a0aae4add) : catalogue visuel pédagogique. L’exemple `ProgressAnimation.kt` lu utilise des répétitions infinies et des composants Material anciens. Utile pour comprendre les primitives, **pas une dépendance à intégrer telle quelle**. GitHub ne détecte pas de licence dans ses métadonnées ; le README déclare Apache2.0. Revalider la portée avant toute copie.
4. [SmartToolFactory Compose Tutorials](https://github.com/SmartToolFactory/Jetpack-Compose-Tutorials/tree/23d222d8460fa82843a9a3eb4c77cff32afc8fc8) : référence pédagogique complémentaire, pas un framework applicatif ni une recette de performance. Licence déclarée Apache2.0 ; pas de revue de chaque tutoriel.
5. [Material Components Android](https://github.com/material-components/material-components-android/tree/60ff09436d5d477a4b9d02940f31eb01e1250620) : référence Views/Material, à distinguer du code Compose Material3 dans AndroidX. Ne pas ajouter cette bibliothèque uniquement pour reproduire les layouts XML de Buge.

### Politique proposée — non encore implémentée

| Interaction | Choix proposé |
| --- | --- |
| Changement d’onglet | Transition courte et discrète ; conserver position de défilement et état de chaque section |
| Ouverture d’un dossier | Fondu/déplacement limité ; la navigation ne doit pas attendre un scan ni une animation décorative |
| Appui/sélection | Retour visuel discret, état sélectionné explicite et accessible ; pas de rebond sur chaque ligne |
| Panneau de détails/actions | Bottom sheet Material3 ; aucune animation continue lorsque le panneau est immobile |
| Copie/transfert | Progression liée au travail réel ; état indéterminé seulement si l’avancement est inconnu ; jamais simuler une copie réussie |
| Grands dossiers | Pas d’animation d’entrée pour chaque fichier ; listes/grilles lazy, clés stables et miniatures bornées |
| Réduction du mouvement | Conserver la durée système et offrir une préférence « mouvements réduits » ; rendre immédiatement les états finaux si désactivé, avec indicateur statique compréhensible |

Ne pas multiplier deux fois l’échelle système entre une politique maison et celle de Compose. Tester0×,1×, échelle ralentie et changement à chaud. Les boucles personnalisées, Canvas et gestes demandent une vérification spécifique : un réglage global ne suffit pas à prouver que chaque animation le respecte.

## 3. Kotlin, données et performance

| Projet | Intérêt pour Cantas | Décision proposée |
| --- | --- | --- |
| [kotlinx.coroutines](https://github.com/Kotlin/kotlinx.coroutines/tree/113ee1f3c0b0ca0b7a809e75dd8d2340290121c6) | Flow, annulation structurée, dispatchers, Mutex/Semaphore ; licence Apache2.0 | Base asynchrone. ViewModel + StateFlow pour les états ; concurrence bornée pour scans/miniatures/transferts. Une coroutine n’annule pas magiquement un appel IO bloquant : prévoir fermeture/annulation backend |
| [Coil](https://github.com/coil-kt/coil/tree/955c8a525f2aa2c46fe674e93e14805e3fe3f7db) | Chargeur Kotlin/Compose, cache et réduction d’images présentés dans son README ; Apache2.0 | Candidat pour les miniatures, avec tailles et caches bornés. Module réseau non automatique : local/contentURI d’abord. Performances sur Tecno à mesurer, pas garanties par le README |
| [Tivi](https://github.com/chrisbanes/tivi/tree/a0c62c2c763c83e3a0ecf79b283224374bb06c4a) | Application de référence avancée Kotlin/Compose, structure multi-plateforme/multi-modules observée ; Apache2.0 | Source d’étude complémentaire. Ne pas reprendre toute son architecture, services TV ou intégrations réseau dans un gestionnaire Android |
| [kotlinx.collections.immutable](https://github.com/Kotlin/kotlinx.collections.immutable/tree/9f961e4dd419dff58ffea164dd41ebba68acc8d6) | Collections immuables/persistantes ; README portant un badge Alpha ; Apache2.0 | Option à évaluer, pas un ajout obligatoire. Ne pas utiliser `@Immutable` pour cacher une mutation réelle ; mesurer les allocations/coûts de mise à jour |
| [Shizuku API](https://github.com/RikkaApps/Shizuku-API/tree/a27f6e4151ba7b39965ca47edb2bf0aeed7102e5) | API officielle, MIT ; moduleAPI Java11 dans le build inspecté | Adaptateur facultatif, isolé du stockage normal. Détecter service, demander permission, gérer refus/arrêt/décès du binder ; ne pas promettre un contournement universel des restrictions Android |

### Architecture initiale suggérée

- `app` : navigation et assemblage des écrans.
- `core:designsystem` : couleurs, typographie, composants et politique de mouvement Cantas.
- Couche fichiers : contrats de chemins/URI, capacités et erreurs typées ; backends local/SAF et adaptateur Shizuku facultatif.
- Fonctionnalités distinctes : explorateur, recherche, opérations/transferts, favoris/récents ; cloud/NAS/archives/lecteurs ajoutés selon l’inventaire de parité, pas oubliés.
- États explicites : chargement, vide, succès, permission manquante, indisponibilité et erreur. Pas de faux succès silencieux.
- Choisir navigation, stockage de préférences/base et injection après le cadrage fonctionnel. Hilt/Koin/KMP ne deviennent pas nécessaires simplement parce qu’un exemple les utilise.

Ce découpage est une **proposition**, pas un projet Gradle déjà créé. L’inventaire de parité complète et la conception détaillée des backends restent à faire séparément.

### Contraintes Tecno2Go à intégrer dès le départ

Propositions d’ingénierie, non résultats de benchmark : éviter de charger tout le stockage, paginer ou parcourir progressivement les grands dossiers, ne pas lire les fichiers sur le threadUI, limiter miniatures et travaux simultanés, annuler les demandes devenues invisibles, limiter les recompositions des barres de progression et conserver les états de navigation. Préférer vecteurs et surfaces simples aux flous plein écran, vidéos décoratives et effets permanents.

Prévoir benchmarks de démarrage, navigation de dossiers volumineux, recherche, scroll avec miniatures et transferts. Les Baseline Profiles de Now in Android sont une méthode de référence, pas la preuve que Cantas sera fluide sans mesures. Toute bibliothèque native éventuelle devra avoir une variante ARM32 compatible ; la présence de Kotlin/Compose n’impose pas d’embarquer du natif pour les layouts.

## 4. Licences et réutilisation

- MIT et Apache2.0 : conserver les mentions applicables lors d’une véritable réutilisation ; examiner aussi les assets et notices éventuelles, pas seulement le badge GitHub.
- Buge : les fichiers lus indiquent GPL-3.0-only. Pour l’instant, inspiration de disposition et réécriture Compose indépendante ; **pas de copie de ses XML/Kotlin dans le produit**. Une réutilisation dérivée et une éventuelle distribution demanderaient une revue des obligations de licence. L’usage privé n’entraîne pas à lui seul une obligation générale de publier tout son travail.
- Les sources conservées ici sont des extraits/documents de recherche identifiés et accompagnés de leurs références/licences disponibles, pas des composants déjà incorporés à Cantas.
- Les captures, marques et illustrations tierces ne deviennent pas des assets Cantas. Ne pas reprendre le logo ou le nom File Manager+.

## 5. Portée, méthode et limites

Accès effectué avec GitHubCLI/API, en lecture. Pas de recherche simulée ni de version supposée à partir de mémoire. Sources épinglées par SHA dans les métadonnées et `source-files.json` ; en cas de besoin, le fichier se retrouve via `https://github.com/<repo>/blob/<commit>/<path>`.

Certains chemins présumés (`gradle/libs.versions.toml` chez SwiftSlate/Buge, `app/build.gradle.kts` chez Tivi/Canopas) n’existent pas ; réponses404 conservées dans `fetch_limits`. Ils ne constituent pas une absence de support Kotlin. Canopas utilise notamment des fichiers Gradle Groovy, Tivi un répertoire `android-app`.

Aucune résolution Maven, compilation des projets tiers, vérification de CVE exhaustive, mesure mémoire/CPU ni exécution sur Tecno dans cette recherche. Aucune liste de versions définitives de dépendances n’est annoncée. La conformité Android7–15 de Cantas devra être validée avec les versions réellement retenues et les builds résultants.

**Recherche terminée.** Livrable : sélection et recommandations ci-dessus ; aucune implémentation de Cantas engagée pendant cette phase.
