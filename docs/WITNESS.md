# Phase 1 — reconstruction témoin, sans correctif fonctionnel

## Retour du téléphone — témoin accepté pour installation/démarrage

L’utilisateur confirme : « oui tout es installer facilement sans pb », en réponse à la question installation/démarrage. [Compte rendu](../analysis/device-tests/witness-user-report.json). C’est une validation rapportée sur le Tecno BF6, pas une mesure ADB ou un test complet des fichiers/cloud. Le gate témoin installation/démarrage est levé ; la suite commence par le correctif Nextcloud TLS, séparément de HTTP et de l’interface.

## Nouveau build TEST livré

Le [run 38667633766](https://github.com/JCVERSA/rea/actions/runs/38667633766), source `50a9e4f`, a réussi tous les contrôles et livré l’artifact TEST autorisé. Voir [le téléchargement et les instructions](TEST_BUILD.md). Les signatures/alignement, invariants et démarrages original/témoin passent ; la validation Tecno/ARM32 reste ouverte. Les clés stables sont reportées à la future livraison durable, pas bloquantes pour ce test.

## Validation précédente — CI réussie, avant autorisation du build TEST

[Run 38667011032](https://github.com/JCVERSA/rea/actions/runs/38667011032), source `6ed7444` : **build, signature de diagnostic, invariants et essai émulateur réussis**. [Rapports versionnés](../analysis/witness/38667011032/).

- Manifeste binaire et ressources compilées identiques à la base ; 6 443 mappings publics conservés ; assets identiques ; 21 984 classes smali de chaque côté. Aucune preuve d’équivalence de toutes les instructions DEX.
- Original **et** témoin : installation réussie, `am start` OK, processus vivant après six secondes, buffer crash ciblé vide. UID applicatif bloqué en sortie IPv4/IPv6 avant lancement. Émulateur API 31, ABI déclarées `x86_64,arm64-v8a` — **pas ARM 32 bits**.
- Apksigner v1/v2/v3 et alignement vérifiés. La clé utilisée reste **éphémère de diagnostic CI**, non livrée ; aucun APK installable pour le téléphone n’a été publié.
- 21 tests locaux passent (9 inventaire, 5 modèle provider, 7 outils sur fixtures). La CI de ce run contient les 5 tests provider et les 6 tests outils présents à ce commit ; le septième test ajouté ensuite est local.

**Gates ouverts :** configuration d’une clé privée stable ; installation manuelle du témoin sur le Tecno BF6 ; validation des fonctions ; recompilation XML/ressources avant la refonte. Aucun correctif TLS/HTTP/crypto/UI n’a encore été mélangé à cette étape. Le succès de démarrage n’est pas une validation de toutes les fonctionnalités.

## Nouvelle décision utilisateur — APK TEST avant clé de release

L’utilisateur autorise désormais la livraison d’un build de test avec clé temporaire, avant de configurer la clé privée de release. La règle antérieure « aucun APK éphémère livré » est donc remplacée pour les **nouveaux** runs par :

- APK temporairement signé autorisé, artifact `TEST-ONLY-witness-<run-id>`, **uniquement après** signature/alignement, invariants et smoke émulateur réussis ;
- aucune clé privée publiée et aucune GitHub Release créée ;
- la signature change à chaque run temporaire : ni mise à jour en place de l’original ni continuité garantie entre builds TEST ou avec la future release ;
- ne rien désinstaller automatiquement : sauvegarder/exporter les réglages nécessaires avant un remplacement consenti. Ne jamais supprimer les fichiers personnels ;
- le témoin reste sans correctif fonctionnel, avec ressources compilées originales ; le test sur le Tecno BF6 et la recompilation XML restent à faire.

Les mentions de non-livraison des exécutions précédentes restent historiquement exactes. Pour la procédure du nouveau build, voir [TEST_BUILD.md](TEST_BUILD.md).

## But et périmètre

Le plan a été approuvé (« Go »). On valide d’abord la chaîne de reconstruction ; les correctifs TLS, HTTP, crypto et la refonte UI ne sont **pas** mélangés à cette étape. `base.apk` et les exports de référence restent inchangés.

### Rectification AUD-05

L’audit avait omis l’enfant du provider :

```xml
<provider android:grantUriPermissions="false" ...>
    <grant-uri-permission android:pathPrefix="/" />
</provider>
```

Le parseur AOSP Android 12, `ParsedProviderUtils.parseGrantUriPermission`, met `provider.grantUriPermissions = true` quand ce pattern enfant existe. Source vérifiée : [android-12.0.0_r1, lignes 208–270](https://github.com/aosp-mirror/platform_frameworks_base/blob/android-12.0.0_r1/core/java/android/content/pm/parsing/component/ParsedProviderUtils.java#L208-L270), blob `28fd919e2b7e7e19996b5eb7ecf49c738343f8ee`.

**AUD-05 est retiré comme faux positif.** Le parent binaire est bien à zéro, mais il ne suffit pas à décrire le ProviderInfo résultant. Aucun patch du manifeste ni suppression de la garde smali. Le retour utilisateur positif est cohérent avec cette règle. `scripts/test_provider_contract.py` conserve cinq tests de régression sur ce cas.

## Pipeline de témoin

`.github/workflows/witness.yml` :

1. Job **build**, token lecture seule, sans secrets de signature : contrôle du hash, restoration de l’archive complète, reconstruction Apktool sans patch.
2. Job **validate**, runner distinct et token lecture seule : récupération du témoin, alignement et signature, vérification apksigner de l’original et du témoin, destruction immédiate du fichier de clé temporaire, puis nouveau décodage et invariants contrôlés.
3. Émulateur jetable **API 31, x86_64, 2 Go RAM** : installation et tentative de démarrage de l’original, puis du témoin. Le script refuse un appareil réel/ambigu et exige root pour rejeter le réseau IPv4/IPv6 de l’UID applicatif avant lancement. Aucune donnée personnelle ni compte cloud.
4. Job **publish**, seul détenteur d’un token écriture, sur un autre runner : publication des rapports, sans lancer un décompilateur ou l’APK.

Actions épinglées à des SHA, Apktool 3.0.3 contrôlé par digest. Les rapports réels sont dans `analysis/witness/<run-id>/`. Ils peuvent aussi contenir un résultat d’échec : lire chaque résultat avant de conclure.

### Ce que les vérifications ne garantissent pas

Le manifeste et les assets sont comparés, ainsi que le nombre de classes smali ; ce n’est pas une preuve d’équivalence de toutes les instructions ou ressources après réassemblage. Le startup smoke vérifie le résultat `am start`, la présence du processus après six secondes et le buffer crash ciblé. Ce n’est ni un benchmark ni un test de navigation, fichiers, stockage, réseau, comptes ou permissions.

Un émulateur x86_64 Android 12 n’est **pas** un Tecno BF6 ARM 32 bits. Le test sur le téléphone reste un gate avant les modifications suivantes. Un test hors réseau ne valide pas les fonctionnalités cloud.

## Signature : aucune fausse continuité

L’intégration GitHub a retourné HTTP 403 sur la lecture des secrets du dépôt. Elle ne permet donc pas ici de provisionner/vérifier une clé privée durable. Ce n’est pas la preuve que le dépôt n’a aucun secret ; le workflow rapporte son mode effectif.

Deux modes explicites :

- **Secrets privés configurés** : signature par la clé stable et livraison `base-witness-signed.apk` seulement si les contrôles et l’essai émulateur passent.
- **Secrets absents** : clé éphémère de **TEST**. Depuis la nouvelle autorisation utilisateur, l’APK de test peut être livré après tous les contrôles ; la clé reste détruite et jamais publiée. L’artifact interne non signé n’est **pas installable**. Pas de promesse de mise à jour entre runs.

Une configuration partielle/invalide fait échouer la signature ; le pipeline ne retombe pas silencieusement sur une clé différente. V4/`.idsig` est désactivé pour éviter un fichier auxiliaire pris pour l’APK. La clé AOSP partagée de la base n’est jamais utilisée pour signer un mod.

## Configuration requise pour la future signature stable (pas pour le build TEST)

Le propriétaire doit configurer dans GitHub → Settings → Secrets and variables → Actions :

| Secret | Contenu privé |
| --- | --- |
| `MOD_SIGNING_KEYSTORE_B64` | Base64 d’un keystore PKCS12 privé, alias **mod** |
| `MOD_SIGNING_PASSWORD` | Mot de passe commun du store et de sa clé PKCS12 |

**Ne jamais envoyer ces valeurs dans le chat, les logs ou Git.** Conserver le keystore et son mot de passe dans un stockage privé sauvegardé ; GitHub Secrets n’offre pas une récupération de la valeur en clair au propriétaire.

Si aucune clé personnelle n’existe, la générer dans un environnement privé avec un JDK et hors du dépôt (commande à exécuter par le propriétaire, pas exécutée ici) :

```sh
keytool -genkeypair -storetype PKCS12 -alias mod -keyalg RSA -keysize 3072 \
  -validity 10000 -keystore /chemin/prive/file-manager-mod.p12
```

Le mot de passe est saisi dans les prompts, pas dans la commande ou le chat. Depuis une session GitHub personnelle autorisée à gérer les secrets, le keystore peut être transmis par stdin :

```sh
base64 -w0 /chemin/prive/file-manager-mod.p12 | gh secret set MOD_SIGNING_KEYSTORE_B64 --repo JCVERSA/rea
gh secret set MOD_SIGNING_PASSWORD --repo JCVERSA/rea
```

Ces commandes ne sont pas une demande de jeton GitHub. Si la connexion Arena est autorisée ultérieurement à gérer les secrets, une provision contrôlée peut être étudiée sans exposer de valeurs ; l’accès actuel ne le permet pas.

## Installation physique future

Ne rien désinstaller automatiquement. Une signature différente de celle du paquet installé interdit normalement une mise à jour en place. Prévoir une sauvegarde et un remplacement explicitement consenti, ou un package distinct avec ses adaptations — pas un contournement de vérification de signature.

Après livraison d’un témoin TEST temporairement signé ou signé par la clé stable : installer manuellement, relever version/résultat, tester startup et fichiers jetables. Seulement après ce retour, commencer TLS puis HTTP, chaque groupe indépendamment. Aucun droit premium/licence modifié.

## Première exécution : 38666232240

- Reconstruction Apktool : réussie.
- Apksigner : original vérifié v1/v2/v3, certificat SHA-256 AOSP inchangé ; témoin diagnostic signé/vérifié.
- Signature stable : non configurée selon le rapport effectif du workflow.
- Comparaison round-trip : échec ; émulateur **non exécuté**. Aucun APK installable livré.
- Une instrumentation supplémentaire conserve désormais les invariants en échec et le diff du manifeste, sans assouplir le contrôle.

## Deuxième exécution : 38666502774 — écart identifié

Le rapport conserve 21 984 classes smali des deux côtés, les assets inchangés et le même contrat provider. Le diff strict du manifeste montre deux changements de l’outil : ajout d’`android:isSplitRequired="true"` absent de l’original, et résolution d’une référence de chaîne Dropbox en valeur littérale. Il ne faut pas simplement ignorer ces différences, surtout l’exigence de split ajoutée.

La recette témoin utilise donc désormais `apktool b --copy-original`, supporté par Apktool 3.0.3 ([ApkBuilder.copyOriginalFiles](https://github.com/iBotPeaches/Apktool/blob/v3.0.3/brut.apktool/apktool-lib/src/main/java/brut/androlib/ApkBuilder.java#L375-L397)). Le manifeste binaire extrait dans `original/` est contrôlé contre `base.apk` avant build et recopié sans modification. Les anciennes signatures copiées par cette option sont remplacées par apksigner ; seule sa vérification finale fait foi. La vérification stricte n’est pas relâchée : elle exige **aussi** le manifeste binaire identique et l’inventaire exact des IDs publics de ressources, pour éviter un manifeste recopié pointant vers des IDs remappés.

Cette recette vaut pour le témoin inchangé. Elle ne doit pas masquer un futur patch du manifeste : une phase qui en nécessite un devra changer explicitement la recette et définir ses écarts autorisés.

## Troisième exécution : 38666749900 — compilation des ressources bloquée

Le manifeste binaire est désormais identique, ainsi que sa représentation décodée. Le contrôle supplémentaire a trouvé **20 IDs de drawables Material générés remappés**, sur 6 443 ressources publiques. La compilation complète des ressources n’est donc **pas validée** et le contrôle a bien refusé de lancer le témoin.

Pour dissocier le gate DEX/signature/démarrage du futur chantier UI, le témoin est maintenant une **reconstruction du code smali avec ressources compilées originales conservées** : après assemblage, `restore_witness_resources.py` rétablit exactement `resources.arsc` et toutes les entrées `res/` de la base. Le manifeste binaire original reste conservé. Le script refuse toute modification du manifeste/XML décodés avant cette restauration : il ne doit pas masquer une intention de patch UI.

La vérification exige aussi l’égalité octet par octet de ces ressources et l’égalité des 6 443 mappings publics après décodage. Ce choix préserve la référence au lieu d’accepter des IDs modifiés ; **il ne résout ni ne valide la recompilation XML requise avant la refonte visuelle**. Ce sous-gate reste ouvert. Aucun correctif fonctionnel n’est ajouté.
