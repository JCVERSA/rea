# Phase 1 — reconstruction témoin, sans correctif fonctionnel

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
- **Secrets absents** : clé éphémère **réservée à la CI**, uniquement pour rendre possible l’installation de diagnostic sur l’émulateur. Ni clé ni APK signé éphémère ne sont publiés. L’artifact interne non signé n’est **pas installable**. Pas de promesse de mise à jour entre runs.

Une configuration partielle/invalide fait échouer la signature ; le pipeline ne retombe pas silencieusement sur une clé différente. V4/`.idsig` est désactivé pour éviter un fichier auxiliaire pris pour l’APK. La clé AOSP partagée de la base n’est jamais utilisée pour signer un mod.

## Configuration requise avant livraison au téléphone

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

Ne rien désinstaller pour l’instant. Une signature différente de celle du paquet installé interdit normalement une mise à jour en place. Prévoir une sauvegarde et un remplacement explicitement consenti, ou un package distinct avec ses adaptations — pas un contournement de vérification de signature.

Après un témoin signé par la clé stable : installer manuellement, relever version/résultat, tester startup et fichiers jetables. Seulement après ce retour, commencer TLS puis HTTP, chaque groupe indépendamment. Aucun droit premium/licence modifié.

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
