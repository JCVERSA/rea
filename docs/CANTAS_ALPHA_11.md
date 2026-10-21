# Cantas alpha11 — copie récursive locale/SAF vers SAF

**TEST vérifié, pas une version finale.** Version0.11.0-alpha11, code12. Source `aa723f3cf6429aa989759177b69a35fe68a589bd`, [run39525354942](https://github.com/JCVERSA/rea/actions/runs/39525354942). Les cinq jobs ont réussi. [Alpha10 conservée](CANTAS_ALPHA_10.md).

**[Télécharger le ZIP TEST alpha11](https://github.com/JCVERSA/rea/actions/runs/39525354942/artifacts/12312751732)** : `Cantas-0.11.0-alpha11-TEST.apk` (debug0.11.0-alpha11-test), accès GitHub requis, rétention14jours. SHA-256 enregistré par CI : `6148cd3ed9c0e854b13d1280f9f60b9b118ae3ad3f5c78db40a339b528dfedfd`, non recalculé localement. Rapports/captures : `analysis/cantas/39525354942/`.

## Fonctionnalité

Le même parcours sélectionner/copier/naviguer/coller permet désormais les dossiers locaux ou SAF vers une connexion SAF persistante READ+WRITE choisie explicitement. Fichiers cachés, sous-dossiers et dossiers vides sont inclus indépendamment des filtres de l’écran. Les sources SAF restent utilisables en READ seul. Pas de nouveau privilège manifeste, dépendance ou permission INTERNET.

Limites visibles dans la confirmation et le texte de sélection : **1–100 racines,1000entrées totales et profondeur32** depuis chaque racine (la racine est à profondeur0). Les copies locales et imports SAF→local existants restent disponibles. Ce n’est pas une implémentation de déplacement, renommage, suppression ou corbeille.

## Préflight et capacités

`SafTreePlanner` construit un plan en lecture seule sur le contrat `ImportSource` : identités opaques, noms valides, absence de cycle/doublon d’identité ou de nom de frère sans distinction de casse, limites de profondeur/compte, chemins source≤16384unités et budget4M unités UTF-16 cumulées source+relatif. Aucun flux de fichier n’est ouvert pendant ce préflight. Tout le plan, les sources et les parents relatifs sont revalidés avant les premières écritures, même si un appelant fournit un plan construit manuellement.

L’adaptateur local utilise `StorageBoundary`, refuse les alias/symlinks détectés et les types spéciaux, puis ouvre les fichiers par descripteur natif sans suivi au dernier composant. `File.listFiles` alloue toutefois tout le dossier immédiat avant la limite : pas de pagination à mémoire totale bornée, ni de protection universelle contre des ancêtres réécrits concurremment.

La destination reçoit un cache **privé à l’opération**, initialisé uniquement avec le parent déjà découvert par le navigateur. Les répertoires ajoutés à ce cache sont exclusivement ceux dont `SafFolders` vient de vérifier URI canonique, ID nouveau, nom/type et appartenance réelle aux enfants du parent. Au plus1000répertoires créés sont mémorisés ; aucune URI non vérifiée ou reconstruite à partir d’un préfixe n’est acceptée comme nouveau parent. Ce cache n’est pas partagé avec l’écran et disparaît avec l’opération.

Les ID des sources SAF de même autorité sont calculés une fois pour le lot (pas reparsés pour chacun des fichiers) et exclus des ID de nouveaux dossiers/fichiers avant d’y écrire. L’ID de la racine de l’arbre ne peut pas être accepté comme une sortie nouvellement créée. Cela ne prouve pas l’absence d’alias physiques entre providers ou entre chemins locaux et URI : le provider reste une frontière de confiance.

## Refus de fusion et résultats

- Un dossier de destination déjà existant n’est **jamais** réutilisé/fusionné, même s’il est vide. La racine concernée est signalée existante, tout son sous-arbre est non tenté ; les autres racines indépendantes peuvent continuer.
- Après création vérifiée d’un nouveau dossier, seuls ses descendants planifiés peuvent y être écrits. Si cette création est incertaine (nom imposé, exception après création, URI/ID invalide), aucun descendant n’est tenté.
- Les fichiers réutilisent le moteur alpha10 : collision détectée refusée, buffer64Kio, longueur capturée ou512Mio/fichier inconnu, sonde supplémentaire non écrite, fermeture puis relecture exacte et SHA-256. Les mêmes limites de scans/grants/URI s’appliquent.
- Les dossiers ont le statut **dossier créé**, pas « sous-arbre complet ». Il faut examiner tous les résultats pour connaître les fichiers réellement vérifiés. Le presse-papiers n’est vidé que si toutes les entrées sont vérifiées copiées ou dossiers créés, sans statut d’erreur.
- Annulation, perte d’accès et changement de source interrompent/refusent les entrées concernées ; aucun nettoyage, rollback, réessai automatique ni suppression de source. Les descendants d’un parent non créé ne sont pas tentés. Les sorties déjà créées restent à inspecter.
- Le journal privé existant est écrit/relu avant mutation puis complété en fin de lot ; il ne donne pas de reprise automatique ni de transaction multi-instance/processus.

Le plan n’est pas un snapshot de l’arborescence : des ajouts concurrents postérieurs peuvent être absents, des suppressions/changements provoquer des erreurs. Les vérifications du provider ne sont pas une primitive exclusive-create atomique ou une garantie universelle de non-écrasement. La relecture ne prouve pas la durabilité physique/cloud et peut provoquer du trafic supplémentaire. Aucun quota agrégé de lot ni délai absolu pour des appels bloqués n’est promis. Les sorties incertaines peuvent avoir un nom différent de celui demandé et les compteurs non vérifiés désignent des octets envoyés dans le flux.

## Validation vérifiée

**69tests JVM, zéro échec/ignoré ;44tests instrumentés sur chacun des émulateurs API24/x86 et API31/x86_64 ; les deux smoke runtime réussissent.** Lint : **0erreur,32avertissements**, sans suppression ni changement de dépendances. Identité indépendante, min24/target35, absence d’INTERNET, signature v2 et quatre variantes natives graphiques dont ARMv7 vérifiées par CI. Six nouveaux tests JVM couvrent plan opaque, cachés/vides, cycles/collisions de casse, limites exactes de profondeur/compte, plans forgés, changements de source et annulation/nombre de racines. Quatre nouveaux tests Android couvrent copie native imbriquée avec caché/vide et refus de fusion, véritable source SAF READ seule avec fichier inconnu et dossier vide, dossier incertain/descendants ignorés/annulation conservant la racine créée, et plan forgé/grant révoqué sans mutation. Un second parcours UI de copie SAF sélectionne un dossier local imbriqué, confirme, vérifie les quatre résultats, les octets originaux/destination et le rapport final avant capture `alpha11-saf-tree.png`. La capture API31 a été inspectée à320×640 : Source, Empty et Nested indiqués créés (sans garantie de complétion), `.hidden.txt` vérifié copié19octets et acquittement du rapport visibles. Le premier run39524667502 a également passé les gates ; seul le run final ci-dessus, incluant le calcul unique des exclusions d’ID et le refus de résultats racine, constitue cette livraison.

Les fixtures appartiennent à l’app de test et à son provider synthétique sur émulateur jetable ; aucune suppression hors de ces espaces, aucun ADB sur le téléphone. XML, Python et diff contrôlés localement ; Kotlin/Android et émulateurs via CI.

## Limites de validation et contrat final

Pas d’essai réel Tecno BF6 Android12 ARM32/2Go, SD/USB/cloud, sélecteur système complet, disque plein, mort forcée du processus, concurrence hostile, performance, accessibilité/FR en exécution ou matrice Android7–15 complète. Les tests de journal/reconstruction de ViewModel ne prouvent pas ces scénarios.

Les déplacements/renommages/suppressions réels, corbeille, archives, lecteurs internes, réseaux/cloud/serveurs, Shizuku facultatif et autres critères du [contrat final](CANTAS_FINAL_ACCEPTANCE.md) restent ouverts. La copie récursive SAF n’est pas la clôture de la demande de version finale.

Essai manuel : dossier jetable contenant un sous-dossier, un fichier caché et un dossier vide ; destination SAF distincte choisie en lecture/écriture ; copier puis vérifier tous les résultats et les originaux. Recommencer vers le même parent doit refuser la fusion. Inspecter toute sortie incertaine avant nouvel essai. Aucune désinstallation automatique ; les signatures TEST peuvent varier, protéger les données privées utiles avant toute réinstallation décidée manuellement.
