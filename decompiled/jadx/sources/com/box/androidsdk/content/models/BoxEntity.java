package com.box.androidsdk.content.models;

import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class BoxEntity extends BoxJsonObject {
    private static HashMap<String, n> q = new HashMap<>();
    private static final long serialVersionUID = 1626798809346520004L;

    class a implements n {
        a() {
        }

        @Override // com.box.androidsdk.content.models.BoxEntity.n
        public BoxEntity a() {
            return new BoxUser();
        }
    }

    class b implements n {
        b() {
        }

        @Override // com.box.androidsdk.content.models.BoxEntity.n
        public BoxEntity a() {
            return new BoxGroup();
        }
    }

    class c implements n {
        c() {
        }

        @Override // com.box.androidsdk.content.models.BoxEntity.n
        public BoxEntity a() {
            return new BoxRealTimeServer();
        }
    }

    class d implements BoxJsonObject.b<BoxEntity> {
        d() {
        }

        @Override // com.box.androidsdk.content.models.BoxJsonObject.b
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public BoxEntity a(ax.U4.d dVar) {
            return BoxEntity.E(dVar);
        }
    }

    class e implements n {
        e() {
        }

        @Override // com.box.androidsdk.content.models.BoxEntity.n
        public BoxEntity a() {
            return new BoxCollection();
        }
    }

    class f implements n {
        f() {
        }

        @Override // com.box.androidsdk.content.models.BoxEntity.n
        public BoxEntity a() {
            return new BoxComment();
        }
    }

    class g implements n {
        g() {
        }

        @Override // com.box.androidsdk.content.models.BoxEntity.n
        public BoxEntity a() {
            return new BoxCollaboration();
        }
    }

    class h implements n {
        h() {
        }

        @Override // com.box.androidsdk.content.models.BoxEntity.n
        public BoxEntity a() {
            return new BoxEnterprise();
        }
    }

    class i implements n {
        i() {
        }

        @Override // com.box.androidsdk.content.models.BoxEntity.n
        public BoxEntity a() {
            return new BoxFileVersion();
        }
    }

    class j implements n {
        j() {
        }

        @Override // com.box.androidsdk.content.models.BoxEntity.n
        public BoxEntity a() {
            return new BoxEvent();
        }
    }

    class k implements n {
        k() {
        }

        @Override // com.box.androidsdk.content.models.BoxEntity.n
        public BoxEntity a() {
            return new BoxFile();
        }
    }

    class l implements n {
        l() {
        }

        @Override // com.box.androidsdk.content.models.BoxEntity.n
        public BoxEntity a() {
            return new BoxFolder();
        }
    }

    class m implements n {
        m() {
        }

        @Override // com.box.androidsdk.content.models.BoxEntity.n
        public BoxEntity a() {
            return new BoxBookmark();
        }
    }

    public interface n {
        BoxEntity a();
    }

    static {
        D("collection", new e());
        D("comment", new f());
        D("collaboration", new g());
        D("enterprise", new h());
        D("file_version", new i());
        D("event", new j());
        D("file", new k());
        D("folder", new l());
        D("web_link", new m());
        D("user", new a());
        D("group", new b());
        D("realtime_server", new c());
    }

    public BoxEntity() {
    }

    public BoxEntity(ax.U4.d dVar) {
        super(dVar);
    }

    public static void D(String str, n nVar) {
        q.put(str, nVar);
    }

    public static BoxEntity E(ax.U4.d dVar) {
        ax.U4.g gVarE = dVar.E("type");
        if (!gVarE.q()) {
            return null;
        }
        n nVar = q.get(gVarE.j());
        BoxEntity boxEntity = nVar == null ? new BoxEntity() : nVar.a();
        boxEntity.i(dVar);
        return boxEntity;
    }

    public static BoxJsonObject.b<BoxEntity> H() {
        return new d();
    }

    public String J() {
        String strU = u("id");
        return strU == null ? u("item_id") : strU;
    }
}
