# Étape 2a — Nextcloud TLS (AUD-02), build TEST

## Build TEST disponible

**[Télécharger le ZIP avec `base-nextcloud-tls-test.apk`](https://github.com/JCVERSA/rea/actions/runs/38668804678/artifacts/11942843917)** — artifact privé du dépôt si celui-ci est privé, accès GitHub requis le cas échéant ; rétention 14 jours.

- [Run 38668804678 réussi](https://github.com/JCVERSA/rea/actions/runs/38668804678), source `9375e69`.
- SHA-256 APK : `3cc890d6b5f11399395af69f7460fedea35b02b69602c914eaf27c529cc4a718`.
- [Rapports réels](../analysis/nextcloud-tls/38668804678/) : une modification fonctionnelle ; callback assemblé conforme ; manifeste et ressources binaires identiques ; 6 443 IDs publics et 21 984 classes conservés ; assets identiques.
- Apksigner et alignement réussis ; original et build patché installés/démarrés sur émulateur API 31, processus vivant après six secondes et buffer crash ciblé vide.
- 20 tests de régression exécutés en CI (5 provider, 7 outils, 8 patch) ; 29 localement avec les 9 tests d’inventaire. Le workflow témoin sans patch passe également sur le même commit.
- Signature de TEST temporaire ; pas de release. **Pas encore testé sur le Tecno pour cette version ; pas d’essai réel certificat valide/invalide Nextcloud.**

## Pourquoi maintenant

Le témoin a été confirmé installable et démarrable sur le Tecno BF6 par l’utilisateur. Ce retour ne valide pas toutes les fonctions. Le premier changement est donc isolé : **ne plus poursuivre automatiquement une page Nextcloud après une erreur de certificat TLS**.

## Changement exact

`scripts/nextcloud_tls_patch.py` applique une unique substitution dans `smali/ax/S1/b$f.smali`, méthode `onReceivedSslError` :

```diff
- invoke-virtual {p2}, Landroid/webkit/SslErrorHandler;->proceed()V
+ invoke-virtual {p2}, Landroid/webkit/SslErrorHandler;->cancel()V
```

Le hash SHA-256 de la classe complète doit être exactement `e3e399ea09ec6bb546a774dbc02f079201dadb6039ddef2aeba97c6ab1f1477e` avant patch. Une source inattendue ou une seconde application est refusée. Le script vérifie l’intégralité des classes smali contre l’archive avant d’écrire cet unique changement.

- Branche Nextcloud : garde l’indicateur existant et l’état `invalid_cert`, mais annule au lieu de continuer.
- Autres branches OAuth : appel à `WebViewClient.onReceivedSslError` conservé.
- Certificat valide : ce callback d’erreur n’est pas le chemin normal ; aucun changement de la méthode de connexion ni du callback de credentials.
- **Certificat invalide/autosigné non approuvé : connexion désormais bloquée.** Ne pas désactiver TLS pour contourner cela. Aucune nouvelle exception de certificat par origine n’est encore fournie. La politique originale de confiance système/utilisateur est conservée ; son comportement effectif avec des CA privées reste à tester.
- Nextcloud n’est pas supprimé. Aucun patch HTTP, crypto, favoris ou UI. Aucun droit premium/licence modifié.

**Ce correctif est invisible dans l’accueil.** L’écran d’accueil reste celui de la base ; ce n’est pas encore le redesign.

## Recette et contrôles

Workflow séparé : `.github/workflows/nextcloud-tls.yml`. L’ancien workflow témoin reste sans patch fonctionnel.

```sh
python3 scripts/prepare_witness.py
python3 scripts/nextcloud_tls_patch.py apply
java -Xmx2g -jar .tools/apktool.jar b --copy-original .work/witness/tree -o .work/witness/unsigned.apk
python3 scripts/restore_witness_resources.py
# Signature/alignement selon scripts/sign_witness.sh (SDK 35.0.0 requis).
# Après redécodage du fichier signé dans .work/witness/roundtrip :
REA_BUILD_VARIANT=nextcloud-tls-v1 python3 scripts/verify_witness.py
```

Les deux jobs reconstruisent indépendamment la référence attendue. Après réassemblage, le vérificateur compare toutes les instructions de cette méthode à la modification attendue, en ignorant uniquement les lignes de debug et le renommage des labels. Il exige l’annulation et refuse le retour de `proceed`, un registre changé ou une autre instruction. Les invariants manifeste binaire, ressources compilées, IDs publics, assets, contrat provider et nombre de classes restent exigés.

8 tests de source/normalisation dans `scripts/test_nextcloud_tls_patch.py` ; **pas des essais de handshake TLS**. Les rapports `analysis/nextcloud-tls/<run-id>/` distinguent le contrôle du code, l’installation/démarrage émulateur et les tests réseau non faits. APK de test nommé `base-nextcloud-tls-test.apk`, artifact `TEST-nextcloud-tls-<run-id>`, livré uniquement après succès des contrôles. Aucune clé privée ni release publiée.

## Validation encore nécessaire

Le smoke émulateur bloque la sortie IPv4/IPv6 de l’UID applicatif : il ne peut pas valider une vraie connexion Nextcloud. Aucun compte cloud réel utilisé. Ne pas annoncer AUD-02 validé de bout en bout à partir du seul smoke.

Dans un environnement de test avec compte et fichiers jetables :

1. Serveur Nextcloud au certificat valide : connexion et callback normalement fonctionnels.
2. Certificat expiré, nom d’hôte incorrect, chaîne non approuvée : aucun chargement poursuivi/aucune saisie de credentials sur la page refusée ; retour ou fermeture utilisable (pas de spinner bloqué).
3. Autre fournisseur OAuth : vérifier l’absence de régression.
4. Relance et opérations de fichiers locales usuelles sur le Tecno.

Ne pas importer une CA inconnue ou exposer des credentials réels pour un essai. La gestion explicite des certificats privés et l’UX d’erreur peuvent nécessiter une étape dédiée si l’usage le demande.

Signature temporaire : différence avec l’original et avec le témoin précédent ; mise à jour en place non garantie. Sauvegarder avant tout remplacement consenti, ne pas supprimer de fichiers personnels. La clé stable sera traitée pour la livraison durable, pas comme un blocage de ce test.
