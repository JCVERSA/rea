# Cantas alpha06 — copie de dossiers locaux

**TEST personnel validé et livré.** Version `0.6.0-alpha06`, code7. Source de validation `c801738d100aa0c41f42ae659f0cbd89554730dd`, [CI38986939467](https://github.com/JCVERSA/rea/actions/runs/38986939467). La livraison antérieure [0.5.1-alpha05](CANTAS_ALPHA_05_1.md) est conservée.

**[Télécharger le ZIP TEST alpha06](https://github.com/JCVERSA/rea/actions/runs/38986939467/artifacts/12083586993)** : `Cantas-0.6.0-alpha06-TEST.apk` et `SHA256SUMS`. Accès au dépôt GitHub requis, rétention14 jours. Pas de release publique.

SHA-256 enregistré par CI (sans rehash binaire local) :

```text
120db2589605a1c7818f6cdc0712275ff3bb5d7ccadef7d89ddf511e13f3d900
```

Tous les jobs build/API24/API31/deliver/publish réussis. **46 tests JVM,0 échec/ignoré ;18 tests instrumentés par émulateur API24/x86 et API31/x86_64 ; smoke réussi sur les deux.** Lint : **0 erreur,20 avertissements**, non masqués. Signature v2 vérifiée ; quatre variantes de bibliothèque graphique (ARMv7/ARM64/x86/x86_64), identité indépendante, min24/target35, WRITE max29, règles de sauvegarde explicites et absence de permission INTERNET vérifiées.

Rapports et captures : `analysis/cantas/38986939467/`. Captures réellement inspectées : copie du dossier imbriqué/vide et du fichier de26 octets sur API31, puis refus de fusion avec enfants « Not attempted » sur API24. Les assertions vérifient en plus les contenus et marqueurs conservés ; le screenshot seul ne prouve pas cette intégrité.

## Nouveau périmètre

- Sélection de fichiers **et dossiers locaux**, confirmation de destination, puis préparation du lot entier avant création et copie sur IO. Le dialogue prévient que les dossiers incluent tous leurs contenus planifiés, cachés compris, indépendamment du filtre/recherche de la liste.
- Jusqu’à100 racines sélectionnées, **1 000 entrées totales** (fichiers et dossiers compris), profondeur maximale32 sous une racine. Dépassement : lot refusé avant toute création, pas de troncature silencieuse.
- Dossiers imbriqués et vides copiés. Aucune fusion : si un dossier cible existe, son sous-arbre demandé est ignoré. Une collision créée entre préparation et mkdir est également refusée. Les fichiers conservent leur création exclusive `O_EXCL`, sans écrasement.
- Destination dans une source (ou la source elle-même), liens symboliques sources, types non pris en charge, noms invalides, chemin hors frontière et dossier illisible : préparation refusée. Le presse-papiers conserve le chemin d’entrée sélectionné, pas seulement la cible canonique, pour ne pas masquer un alias source.
- Plan interne revalidé avant exécution (chemins relatifs, ordre parents/enfants, noms, types, limites et destination). Les entrées descendantes ne sont copiées que si leur parent a été créé par cette tentative ; jamais dans un parent préexistant après une collision.
- Progression par entrée/octet, annulation coopérative, journal privé avant les écritures et bilan persistant. Les échecs/annulations conservent les dossiers et fichiers déjà créés. Aucun nettoyage, déplacement, renommage ou effacement automatique.
- Le rapport distingue **dossier créé** de **fichier copié**. Un dossier créé ne prouve pas que ses enfants sont tous copiés ; lire leurs résultats. Toute sortie créée avec des résultats non réussis dans le lot déclenche un avertissement de copie partielle/non vérifiée.
- Rapport affiché par100 résultats supplémentaires à la demande, jusqu’à la totalité du plan. Fermer efface uniquement le journal privé, pas les données utilisateur. Un redémarrage n’entraîne ni reprise automatique ni fusion/réessai.

## Limites maintenues

SAF reste en lecture seule et sa copie n’est pas livrée. Aucun déplacement, renommage, suppression, corbeille ou nouvelle connexion réseau dans ce lot. Shizuku reste facultatif et non intégré. Aucune fonction promise n’est retirée de la feuille de route.

Le plan borne les métadonnées retenues, **pas la mémoire totale d’énumération** : `File.listFiles()` alloue encore tout le dossier immédiat sur API24. La préparation peut donc être coûteuse pour un dossier extrêmement volumineux. L’annulation attend aussi les appels bloquants en cours ; pas de garantie de délai ni de survie en arrière-plan/service au premier plan.

La liste planifiée est un relevé, pas un snapshot immuable. Un enfant ajouté après préparation n’est pas automatiquement incorporé. La revalidation ne supprime pas les courses face à un acteur qui remplace des ancêtres/répertoires pendant l’opération. Pas de garantie de parcours entièrement fondé sur des descripteurs ni de snapshot/hash du contenu source. Une modification rapide de même taille peut échapper au contrôle de fichier. Les dates/permissions ne sont pas recopiées comme contrat de fidélité, et la galerie/MediaStore n’est pas immédiatement garantie.

Le journal reste celui de la session normale, pas un gestionnaire transactionnel multi-instance. Les noms d’un journal interrompu ne prouvent pas la propriété des sorties : certains pouvaient déjà exister. Les règles de sauvegarde et l’absence de permission INTERNET de0.5.1 sont conservées ; aucun transfert OEM/sauvegarde réelle vérifié.

## Validation exécutée

Source initiale `09b25c49e8a69e550a2d78088146306f24ff2474`, [run38986873916](https://github.com/JCVERSA/rea/actions/runs/38986873916). La révision de validation ajoute la conservation du chemin original de sélection pour refuser les alias et deux tests supplémentaires de liens racine/collision d’enfant. Les rapports des deux exécutions sont conservés dans `analysis/cantas/` ; la livraison est celle du dernier source/run ci-dessus.

46 tests JVM et18 tests instrumentés par API24/API31 **confirmés réussis** dans les rapports archivés. Les nouveaux tests ciblent dossiers imbriqués/vides/cachés, absence de fusion au niveau racine et enfant concurrent, refus destination interne, liens, quotas de profondeur/nombre, annulation laissant un dossier partiel, plans mal formés/périmés, aller-retour du journal de dossiers et parcours UI sélection/navigation/confirmation/collision. Les anciens tests de copie bornée restent présents.

Contrôles locaux XML, syntaxe Python et diff réussis. Pas de compilation locale Kotlin/Android faute de JDK/SDK et d’accès aux dépôts Gradle/Google/Maven ; compilation et émulateurs en CI. Les fixtures sont jetables et appartenant au test. Aucun ADB sur le Tecno.

Toujours non vérifiés : Tecno ARM32/2Go, français en exécution/accessibilité/performance, Android7–15 complet, carte SD/USB physiques, coupure forcée du processus, disque réellement plein, lecture de tous les1 000 résultats dans l’UI et clic Annuler pendant une copie lente. Les tests de cancellation du moteur et de l’adaptateur ne prouvent pas tous ces cas.

## Essai manuel après validation

Créer un petit dossier de test contenant un fichier jetable, un sous-dossier avec un autre fichier et un sous-dossier vide. Garder une copie séparée des originaux. Sélectionner ce dossier, Copier, naviguer vers un autre dossier de test vide, Coller ici et confirmer le chemin. Vérifier chaque résultat puis les contenus dans un outil de confiance. Refaire vers la même destination doit ignorer le dossier existant, **sans fusion**, en conservant ses marqueurs. Ne pas tester sur des données uniques ; une sortie partielle doit être inspectée manuellement. Les signatures TEST peuvent varier ; aucune désinstallation automatique de Cantas ou de l’ancienne application.
