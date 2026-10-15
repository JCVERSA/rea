package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import com.microsoft.graph.extensions.File;
import com.microsoft.graph.extensions.FileSystemInfo;
import com.microsoft.graph.extensions.Folder;
import com.microsoft.graph.extensions.IdentitySet;
import com.microsoft.graph.extensions.ItemReference;
import com.microsoft.graph.extensions.Package;
import com.microsoft.graph.extensions.Shared;
import com.microsoft.graph.extensions.SharepointIds;
import com.microsoft.graph.extensions.SpecialFolder;
import java.util.Calendar;

/* JADX INFO: loaded from: classes3.dex */
public class BaseRemoteItem implements d {

    @c("@odata.type")
    @ax.Z8.a
    public String a;
    private transient com.microsoft.graph.serializer.a b;

    @c("createdBy")
    @ax.Z8.a
    public IdentitySet c;

    @c("createdDateTime")
    @ax.Z8.a
    public Calendar d;

    @c("file")
    @ax.Z8.a
    public File e;

    @c("fileSystemInfo")
    @ax.Z8.a
    public FileSystemInfo f;

    @c("folder")
    @ax.Z8.a
    public Folder g;

    @c("id")
    @ax.Z8.a
    public String h;

    @c("lastModifiedBy")
    @ax.Z8.a
    public IdentitySet i;

    @c("lastModifiedDateTime")
    @ax.Z8.a
    public Calendar j;

    @c("name")
    @ax.Z8.a
    public String k;

    @c("package")
    @ax.Z8.a
    public Package l;

    @c("parentReference")
    @ax.Z8.a
    public ItemReference m;

    @c("shared")
    @ax.Z8.a
    public Shared n;

    @c("sharepointIds")
    @ax.Z8.a
    public SharepointIds o;

    @c("size")
    @ax.Z8.a
    public Long p;

    @c("specialFolder")
    @ax.Z8.a
    public SpecialFolder q;

    @c("webDavUrl")
    @ax.Z8.a
    public String r;

    @c("webUrl")
    @ax.Z8.a
    public String s;
    private transient l t;
    private transient e u;

    @Override // ax.Aa.d
    public void c(e eVar, l lVar) {
        this.u = eVar;
        this.t = lVar;
    }

    @Override // ax.Aa.d
    public final com.microsoft.graph.serializer.a d() {
        return this.b;
    }
}
