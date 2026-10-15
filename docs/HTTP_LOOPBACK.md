# Étape 2b — serveur média HTTP local (AUD-04a), TLS conservé

## Périmètre : réduction d’exposition, pas résolution complète d’AUD-04

Les producteurs retrouvés construisent des URL `http://127.0.0.1:<port>/...` via `HttpServerService.j`. Le constructeur `ax.X1.b` appelle pourtant le constructeur NanoHTTPD avec seulement le port, ce qui aboutit à un bind toutes interfaces. Une seule classe applicative appelle ce constructeur NanoHTTPD dans l’archive analysée.

Ce sous-patch explicite **`127.0.0.1` uniquement pour ce pont média**, à l’aide du constructeur existant `(String host, int port)` :

```diff
 .method public constructor <init>(Lcom/alphainventor/filemanager/service/b;I)V
-    .locals 0
+    .locals 1
+    const-string v0, "127.0.0.1"
-    invoke-direct {p0, p2}, Lax/Ba/a;-><init>(I)V
+    invoke-direct {p0, v0, p2}, Lax/Ba/a;-><init>(Ljava/lang/String;I)V
```

Le hash de la classe complète est vérifié avant modification. Tous les autres smali doivent correspondre à la base, sauf l’unique correction TLS précédente, obligatoire et conservée. Le constructeur assemblé est ensuite comparé à celui attendu. Les fonctions de contenu HTTP, Range, ETag, chemins, sous-titres et les autres serveurs ne sont pas modifiées dans cette étape. Ce constat sur le code n’est pas un test de toutes ces fonctions.

### Consommateurs étudiés

- `HttpServerService.j` : générateur d’URL IPv4 loopback.
- `ax.K1.AbstractC0775z` : ouverture externe, playlists vidéo, visualiseur et fichier compagnon via `R6`/`S1.L`.
- `ax.p352z1.b` : lecture et playlists audio.
- `ax.J1.C0734o.x`, `ax.H1.O` : URL de lecture de fichiers locaux.
- `VideoPlayerActivity` : conservation/libération de l’opérateur et analyse des URL de lecture.

[Inventaire de sources et appels](../analysis/http-loopback/consumer-map.json). Aucun producteur d’URL LAN de ce pont n’a été trouvé dans cette recherche statique ; ce n’est pas une preuve de tous les comportements d’un lecteur tiers ou d’une réécriture d’URL à l’exécution. Un client externe qui remplace manuellement le host loopback par l’IP du téléphone ne pourra plus atteindre ce listener : ne pas promettre cette compatibilité. Le serveur FTP/partage LAN distinct n’est pas patché, mais son fonctionnement physique reste à retester.

### Ce qui reste vulnérable / non traité

- L’allowlist de `service.b.j` utilise toujours `startsWith`, et les ajouts média peuvent enlever l’extension ; **ce problème n’est pas corrigé**.
- Les autres applications du même appareil peuvent encore atteindre le port loopback : pas d’authentification par jeton ajoutée.
- L’expiration/révocation des grants et les limites de concurrence ne sont pas changées.
- Les sidecars, dossiers de documents et chemins opaques des backends nécessitent des règles distinctes avant de remplacer la comparaison de préfixe. Ne pas casser ces usages par une égalité naïve.
- Les certificats Nextcloud valides/invalides ne sont toujours pas testés en connexion réelle ; le correctif TLS précédent reste présent.

## Tests reproductibles et limites

Workflow `.github/workflows/http-loopback.yml` :

1. Restaurer la référence complète ; appliquer `nextcloud_tls_patch.py apply`, puis `http_loopback_patch.py apply`.
2. Assembler, garder les ressources compilées/manifeste d’origine, signer avec la clé de TEST, vérifier apksigner/alignement.
3. Redécoder et vérifier les deux patches (`REA_BUILD_VARIANT=tls-http-loopback-v1`) et les invariants.
4. Installer/démarrer original et build sur émulateur Android 12, réseau sortant de l’UID app bloqué.
5. Compiler puis exécuter `tests/android/MediaHttpProbe.java` via `app_process` sur cet émulateur jetable uniquement. Charger **les vraies classes APK**, créer le vrai serveur avec un ContextWrapper minimal et un port temporaire, puis comparer original/build :
   - original : bind wildcard, HTTP 404 sur loopback **et** destination IP propre non-loopback du guest (contrôle positif) ;
   - build : bind `127.0.0.1`, HTTP 404 local toujours utilisable, destination non-loopback inaccessible.

Le client de ce probe est lié à une source loopback (pas un attaquant distant), les destinations sont les interfaces du guest. Les nouvelles connexions sortantes non-loopback sont rejetées en IPv4/IPv6 pendant le probe, en préservant les connexions établies du transport ADB. Ce test vérifie le listener et une route 404 réelle, **pas** l’ouverture de fichiers utilisateur, les backends cloud/NAS, Range/seek, les sous-titres, les permissions d’un processus applicatif normal ou un client sur un autre appareil. Le probe s’exécute en root avec un contexte minimal ; aucun compte ni fichier personnel.

La signature de TEST change entre runs : pas de mise à jour en place garantie ; sauvegarder avant remplacement explicitement consenti, aucune désinstallation automatique. Aucun changement visuel et aucune release publiée.

## Prochain gate physique

Sur le Tecno, fichiers jetables : lecture locale et depuis un backend de test, avance/reprise, playlist audio/vidéo, sous-titres, ouverture dans un lecteur tiers. Vérifier aussi le partage/FTP LAN si utilisé. Rapporter les régressions avant un nouveau groupe de modifications. Ensuite traiter l’autorisation des chemins et les grants avec leurs consommateurs — ne pas déclarer AUD-04 entièrement résolu à ce stade.
