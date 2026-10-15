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
