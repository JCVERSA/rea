# Étape 2c — autorisations URI typées (AUD-04b)

Build cumulatif : conserve l’annulation TLS Nextcloud et l’écoute HTTP média sur `127.0.0.1`. Aucun changement d’interface ni de serveur FTP. Les ressources compilées originales restent conservées.

## Build TEST livré — vérifications réussies

**[Télécharger le ZIP avec `base-media-grants-test.apk`](https://github.com/JCVERSA/rea/actions/runs/38671610356/artifacts/11944184267)** — artifact conservé 14 jours, accès GitHub au dépôt requis le cas échéant.

- [Run 38671610356 réussi](https://github.com/JCVERSA/rea/actions/runs/38671610356), source `9c1e1f5`.
- SHA-256 APK : `bde619c376569fc94ebaf7509370a5d3b1b1817897d3c4aa957498cca75dce58`.
- [Rapports réels](../analysis/media-grants/38671610356/) : 32 tests Python en CI ; 37 cas de politique sur JVM puis sur Android ; 15 cas via le vrai gestionnaire original et 15 via le gestionnaire patché, tous conformes à leurs attentes respectives. Le contrôle original accepte les préfixes voisins ; le nouveau les refuse.
- Méthodes du gestionnaire et de la politique assemblées conformes ; TLS et bind local conservés ; probe du listener réel réussi ; installation/démarrage original/build et vérifications apksigner/alignement réussis.
- Manifeste binaire, ressources compilées, 6 443 IDs publics et assets conservés. 21 985 classes, soit une seule classe nouvelle explicitement autorisée. Les trois workflows antérieurs passent aussi sur ce commit.
- **Pas de test de contenu utilisateur, de lecteur réel, de backend NAS/cloud ni de ce build sur le Tecno.** Les tests de politique utilisent des URI synthétiques. [Ancres des comportements préexistants](../analysis/media-grants/source-anchors.json).

Précisions sur les rapports bruts : `http_patch.prefix_authorization_fixed=false` décrit uniquement le sous-patch historique de bind ; la nouvelle politique active est contrôlée séparément dans `grant_patch`. Le libellé générique `media_registration` du probe original ne désigne pas une factory nouvelle dans la base : ce contrôle est alimenté avec l’ancien radical brut ; seule la branche patchée appelle la vraie factory typée. Les deux branches sont visibles dans le source du probe.

## Pourquoi ne pas remplacer simplement startsWith par equals

La méthode `service.b.a(String)` autorise le **parent** d’un document via `V.t`, notamment pour les ressources relatives. `b(List)` et `c(file)` supprimaient l’extension avec `V.p`, puis le même `j(String)` acceptait n’importe quel préfixe. Ce mélange permettait de confondre `movies` et `movies-private`, ou `clip` et `clip-private.mp4`.

Dans `widget.a.L`, les fichiers compagnons proposés par l’app sont ceux de `C0739u.m()` : **ssa, ass, srt, vtt**. La lecture externe peut utiliser des variantes linguistiques ou d’autres formats courants.

## Nouvelle règle

Classe de politique lisible, sans dépendance supplémentaire : `patches/java/rea/security/MediaGrant.java`, compilée en Java 8/D8 puis désassemblée avec le même Apktool vérifié. Une seule nouvelle classe est intégrée au troisième DEX ; les quatre méthodes ciblées du gestionnaire sont patchées par un script gardé par le hash de sa classe d’origine.

- **Dossier (`D:`)** : le parent sélectionné reste le même, mais l’autorisation exige l’égalité ou un descendant délimité par `/`. `movies-private` et `movies.private` ne sont pas des descendants de `movies`.
- **Média/fichier (`M:`)** : conserver le nom **complet**, extension incluse, pour une autorisation exacte. La suppression d’extension ne s’applique plus au fichier lui-même.
- **Sous-titres automatiques** : même dossier, même radical suivi de `.`, suffixe **ssa/ass/srt/vtt/sub/idx/ttml/dfxp/smi/sami**, variantes de langue/forced acceptées. Un autre média `clip.private.mp4`, une clé `clip.pem` ou un sous-dossier `clip/...` ne sont pas autorisés par le grant de `clip.mp4`.
- Les schémas et autorités doivent correspondre ; les queries/fragments ne font pas partie des URI internes acceptées. Les caractères `?`/`#` encodés comme noms de fichiers restent possibles.
- Un décodage URI, vérification des segments `.`/`..` (y compris encodés et séparés par backslash), rejet NUL. **Pas de décodage récursif** qui casserait les fichiers contenant un pourcentage littéral.
- Un format compagnon non listé n’est plus exposé automatiquement par un préfixe arbitraire ; le sélectionner directement conserve l’ouverture exacte. Ne pas annoncer la compatibilité de tous les formats/lecteurs externes sans test.

Les grants ne sont qu’en mémoire : aucune préférence/connexion existante migrée ou supprimée. Le dossier autorisé peut rester large (y compris une racine si celle-ci était le parent sélectionné) ; ce patch ne transforme pas les grants dossier en grants fichier.

## Reproduction et contrôles

Workflow `.github/workflows/media-grants.yml` :

1. Restaurer la référence ; appliquer TLS puis loopback.
2. `bash scripts/build_media_grant.sh` : javac, suite de 37 cas sur JVM, D8, petit conteneur de décodage **non livré** ; Apktool produit l’unique classe smali.
3. `python scripts/media_grant_patch.py apply` : vérifier tous les smali existants contre la base + les deux patches autorisés, modifier seulement `service/b.smali`, ajouter seulement `rea/security/MediaGrant` au troisième DEX.
4. Assembler et signer le TEST comme précédemment. Redécoder avec `REA_BUILD_VARIANT=tls-http-grants-v1` : vérifier intégralité des méthodes du gestionnaire et de la politique, classe ajoutée explicitement whitelistée, TLS/loopback et invariants des ressources/manifeste/assets.
5. Smoke démarrage puis probe HTTP réel précédent, et probe des vraies classes de grants via `app_process` sur émulateur jetable.

Le probe des grants exécute réellement `manager.a()` et `manager.j()` avec des URI synthétiques. Pour les médias, il remplit le set privé avec le résultat de la vraie factory `MediaGrant.media`; il **ne construit pas de vrais objets fichiers pour appeler b/c**. Ces deux méthodes sont contrôlées dans le code assemblé. L’original sert de contrôle positif : ses préfixes voisins passent ; le build doit les refuser tout en gardant les cas autorisés. Les 37 cas de politique sont aussi exécutés sur Android en chargeant la classe depuis l’APK, pas une copie du helper dans le probe.

## Limites qui restent ouvertes

- Pas de test réel Nextcloud/certificats, backend cloud/NAS, contenu de fichier, lecture/seek, playlist ou lecteur tiers sur le Tecno pour ce build.
- Pas de preuve de confinement filesystem : symlinks, canonicalisation propre à chaque backend, identifiants opaques et décodages additionnels de backends nécessitent une revue distincte.
- Les sous-titres de la même famille sont volontairement autorisés, pas uniquement le fichier initial.
- Révocation/durée des grants non changées ; autres apps locales peuvent joindre le listener ; pas de jeton de session ajouté. AUD-04 n’est pas déclaré totalement résolu.
- Classe ajoutée : 21 985 smali attendus contre 21 984 dans la base. Pas de preuve d’équivalence intégrale de tout le DEX ; les delta de source sont bornés avant assemblage.
- Signature TEST temporaire, différente des anciens builds : sauvegarder avant remplacement consenti ; aucun effacement automatique ni release publiée.

À tester sur fichiers jetables : vidéo + srt/ass/vtt et variantes de langue, playlist, média depuis backend de test, document HTML avec CSS/image relatifs, caractères Unicode/espaces/%, ouverture dans un lecteur tiers. Signaler tout compagnon devenu inaccessible avant d’élargir une règle.
