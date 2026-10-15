# base.apk — Android reverse-engineering workspace

The previous APK, mod pipeline, patches and generated analysis have been removed from the current tree at the user's request. Git history is retained. **`base.apk` is the only input APK.**

This workspace performs static analysis, not an installation/rebuild or premium/license modification. It uses checksum-verified JADX 1.5.6, Apktool 3.0.3, REA 6.5.0 and REA's pinned JADX headless 0.7.1 bridge.

## Résultats et recherche

- [Analyse du nouvel APK, architecture et limites](docs/BASE_ANALYSIS.md)
- [Recherche avancée Android : outils, fichiers sources et diagnostic](docs/ANDROID_RESEARCH.md)
- [Catalogue des dépôts et révisions consultées](docs/research-sources.json)

Le nouvel APK est **File Manager + 3.5.4**, minimum SDK 21, cible 34. Il contient des bibliothèques média ARM **32 bits et 64 bits dans une archive des assets** ; ne pas se limiter au répertoire `lib/`. Le précédent échec d’installation reste inexpliqué sans verdict appareil.

## Outputs

- `decompiled/apktool/`: readable manifest, resources, assets and metadata.
- `decompiled/jadx/sources/`: expanded Java for the manifest package.
- `decompiled/all-java-sources.zip`: every recovered Java namespace, including obfuscated/dependency code.
- `decompiled/complete-apktool.zip`: entire decoded tree, including all smali, for later authorized experiments.
- `decompiled/index.json`, hashes and logs: actual source counts and decompiler status.
- `analysis/rea/`: actual REA JSON evidence, stderr and exit codes, not invented reports.
- `docs/`: investigation findings, research sources and a diagnostic plan.

No signing key or generated APK is produced in this analysis phase. No application code is executed. Nonzero JADX/REA results are preserved and must be interpreted, not treated as complete recovery.

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
