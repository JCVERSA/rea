# base.apk — Android reverse-engineering workspace

The previous APK, mod pipeline, patches and generated analysis have been removed from the current tree at the user's request. Git history is retained. **`base.apk` is the only input APK.**

This workspace contains the static audit and an authorized, unchanged rebuild validation phase. No premium/license modification is made. It uses checksum-verified JADX 1.5.6, Apktool 3.0.3, REA 6.5.0 and REA's pinned JADX headless 0.7.1 bridge.

## Audit approfondi — approuvé, témoin code validé en CI

Le [rapport complet](docs/FULL_STATIC_AUDIT.md) couvre signature/provenance, TLS, stockage des identifiants, serveurs, providers, fichiers, dépendances, RAM et CI. Les [inventaires et validations](analysis/audit/) sont reproductibles avec `python3 scripts/audit_static.py` puis `python3 scripts/test_audit_static.py -v`. **Aucun correctif fonctionnel appliqué.** AUD-05 est retiré : l’enfant de grant du provider avait été omis dans l’analyse initiale. Voir [la phase témoin et les exigences de signature privée](docs/WITNESS.md). Le fonctionnement sur Tecno BF6 Android 12/32 bits est rapporté par l’utilisateur, pas vérifié par ADB.

Le [run témoin 38667011032](https://github.com/JCVERSA/rea/actions/runs/38667011032) réussit : code smali réassemblé, manifeste/ressources compilées d’origine préservés, installation/démarrage original et témoin sur émulateur Android 12. **Pas encore de signature privée stable ni de test sur le Tecno.** La livraison d’un APK **TEST** temporairement signé est maintenant autorisée ; voir [la procédure et ses limites](docs/TEST_BUILD.md). La recompilation XML reste non validée après détection de 20 IDs générés remappés.

**[Télécharger le build TEST autorisé](https://github.com/JCVERSA/rea/actions/runs/38667633766/artifacts/11942766815)** (ZIP contenant `base-witness-test.apk`). [CI 38667633766 réussie](https://github.com/JCVERSA/rea/actions/runs/38667633766) ; signature temporaire, pas une release, aucune correction fonctionnelle. Voir les précautions de remplacement dans [TEST_BUILD.md](docs/TEST_BUILD.md).

## Premier correctif — Nextcloud TLS

Le témoin est confirmé installable/démarrable par l’utilisateur sur Tecno BF6. Le [nouveau build TEST TLS](https://github.com/JCVERSA/rea/actions/runs/38668804678/artifacts/11942843917) remplace uniquement la poursuite automatique après erreur de certificat Nextcloud par une annulation. [Détails, limites et vérifications](docs/NEXTCLOUD_TLS.md). CI réussie ; pas encore de test Nextcloud réel ni de changement d’interface.

## Correctif suivant — pont média HTTP local + TLS

**[Nouveau build TEST cumulatif](https://github.com/JCVERSA/rea/actions/runs/38669941478/artifacts/11943661077)** : conserve TLS et borne le listener média à `127.0.0.1`. [Probe réel et limites](docs/HTTP_LOOPBACK.md). CI réussie, écoute locale et refus de la destination IP non-loopback du guest vérifiés. **Autorisation par préfixe encore à corriger ; pas de refonte visuelle ni de suite média complète validée.**

## Autorisations média — étape précédente

**[Build TEST TLS + HTTP local + grants typés](https://github.com/JCVERSA/rea/actions/runs/38671610356/artifacts/11944184267)**. Les dossiers ont une borne de chemin ; les fichiers gardent leur nom complet, avec une règle explicite pour les sous-titres. [Résultats JVM/Android, compatibilité et limites](docs/MEDIA_GRANTS.md). CI réussie ; pas de lecture réelle de fichiers validée sur le Tecno pour ce build. Révocation des grants, accès des autres apps locales et résolution canonique des backends restent ouverts.

## Dernier TEST — interface Slate et protections cumulatives

**[Télécharger `base-slate-safety-test.apk`](https://github.com/JCVERSA/rea/actions/runs/38675070344/artifacts/11946460786)** (dans le ZIP, signature temporaire, pas une release). [CI réussie 38675070344](https://github.com/JCVERSA/rea/actions/runs/38675070344).

Cette fois, **l’apparence change** : palette lavande, surfaces claires/AMOLED noir, accueil et navigation aérés, cartes arrondies. Nouvelles installations sans choix de thème → mode système ; choix enregistré conservé par le getter. Pas de migration Compose, pas encore de migration complète des composants Material3. Les IDs de ressources sont conservés, sans le relinking qui remappait 20 IDs Material.

Conserve TLS + loopback + grants typés, ajoute attente de métadonnées bornée, taille des commandes FTP bornée, frontière ZIP canonique et expurgation de deux logs sensibles. **62 tests Python locaux, contrôle assemblé des 21 987 classes, probes Android et rendu jour/nuit réussis.** [Bilan, captures réelles, patchs reproductibles et constats encore ouverts](docs/SLATE_SAFETY.md). [Contrôle manuel Tecno sans ADB](docs/SLATE_PHONE_CHECKLIST.md).

SHA-256 APK enregistré par CI : `48cb08c2c97de481cdb870783b4e2b2a372e07cd432a3bbc1a51cb5a62d93b04`. Signature différente possible : ne pas désinstaller sans mettre à l’abri les réglages/connexions et fichiers nécessaires des espaces privés/scopés. **Keystore/migrations de favoris, quotas réseau, authentification locale/révocation et validation réelle des backends restent ouverts** ; pas de prétention « tout corrigé » ou Android7–15 entièrement testé.

## Résultats et recherche

- [Analyse du nouvel APK, architecture et limites](docs/BASE_ANALYSIS.md)
- [Recherche avancée Android : outils, fichiers sources et diagnostic](docs/ANDROID_RESEARCH.md)
- [Catalogue des dépôts et révisions consultées](docs/research-sources.json)

Le nouvel APK est **File Manager + 3.5.4**, minimum SDK 21, cible 34. Il contient des bibliothèques média ARM **32 bits et 64 bits dans une archive des assets** ; ne pas se limiter au répertoire `lib/`. Le précédent échec d’installation reste inexpliqué sans verdict appareil.

## Outputs

- `decompiled/apktool/`: readable manifest, resources, assets and metadata.
- `decompiled/jadx/sources/`: expanded Java for the manifest package.
- `decompiled/all-java-sources.zip`: every recovered Java namespace, including obfuscated/dependency code.
- `decompiled/complete-apktool.zip`: entire decoded tree, including all smali, for authorized, unchanged reconstruction.
- `decompiled/index.json`, hashes and logs: actual source counts and decompiler status.
- `analysis/rea/`: actual REA JSON evidence, stderr and exit codes, not invented reports.
- `docs/`: investigation findings, research sources and a diagnostic plan.

The original static-analysis workflow does not execute application code. The separate witness workflow rebuilds and attempts isolated emulator checks; stable private signing is reserved for the durable delivery channel. Private keys are never delivered. The user now authorizes a clearly labeled temporary-signed TEST APK after passing checks, without a release. Nonzero JADX/REA results are preserved and must be interpreted, not treated as complete recovery.

## Reproduce

The workflow `.github/workflows/analyze-base.yml` triggers on the session branch when the APK, workflow or analysis scripts change. It commits output only to `arena/3a37ecd7-rea` and retains a full Actions artifact for 14 days. Manual dispatch is declared but GitHub may require presence on the default branch before exposing the dispatch UI.

Local tools must be supplied as in that workflow; Java must be a full JDK 17+, not only a JRE. Run:

```sh
bash scripts/decompile_base.sh
bash scripts/run_rea.sh
python3 scripts/verify_base.py
python3 scripts/inspect_base.py
```

The extraction script requires a fresh `.work/base-analysis` directory to avoid accidentally mixing inputs. Tools/scratch trees are ignored. Existing exported output is preserved in `.work/base-analysis/previous-export/` before regeneration so resources from different inputs cannot be mixed. Source ZIPs preserve complete output without checking tens of thousands of dependency/smali files into Git.

Review the original rights and third-party licenses before sharing recovered material. This repository does not grant ownership of the original application.

## Cantas — nouvelle application indépendante

Recherche préalable terminée : [interface Compose, animations et architecture Kotlin](docs/CANTAS_OPEN_SOURCE_RESEARCH.md). 13 dépôts examinés à des niveaux précisés, références épinglées et licences documentées. Projet visé `app.cantas.jcversa`, identité SwiftSlate, organisation inspirée de Buge, sans publicité ni télémétrie, Shizuku facultatif. La recherche a précédé la création du nouveau module [`cantas/`](cantas/), désormais compilé et testé sur émulateurs Android7 et12. [Dernier TEST alpha09 : création de dossiers SAF avec accès explicite](docs/CANTAS_ALPHA_09.md). [Alpha08 : favoris locaux/SAF et préférences persistantes](docs/CANTAS_ALPHA_08.md). [Alpha07 : import SAF vers stockage local en lecture seule](docs/CANTAS_ALPHA_07.md). **La version finale demandée reste à achever : [critères ouverts](docs/CANTAS_FINAL_ACCEPTANCE.md).** [Alpha06 : copie de dossiers locaux sans fusion, avec préflight et rapports](docs/CANTAS_ALPHA_06.md). [Maintenance 0.5.1-alpha05 : copie bornée face aux sources grandissantes et règles de sauvegarde complétées](docs/CANTAS_ALPHA_05_1.md). [Alpha05 : copie locale sans écrasement, progression/annulation, rapports et limites](docs/CANTAS_ALPHA_05.md). [Alpha04 sélection/création conservée](docs/CANTAS_ALPHA_04.md). [Alpha03 SAF conservée](docs/CANTAS_ALPHA_03.md). [Alpha02 conservée](docs/CANTAS_ALPHA_02.md). [Premier jalon conservé](docs/CANTAS_ALPHA_01.md). Pas encore de parité complète ni de benchmark Tecno. Les anciennes analyses de l’APK restent des références, sans suppression.
