package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import com.microsoft.graph.extensions.Entity;
import com.microsoft.graph.extensions.Location;
import com.microsoft.graph.extensions.PersonType;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class BasePerson extends Entity implements d {

    @c("displayName")
    @ax.Z8.a
    public String f;

    @c("givenName")
    @ax.Z8.a
    public String g;

    @c("surname")
    @ax.Z8.a
    public String h;

    @c("birthday")
    @ax.Z8.a
    public String i;

    @c("personNotes")
    @ax.Z8.a
    public String j;

    @c("isFavorite")
    @ax.Z8.a
    public Boolean k;

    @c("scoredEmailAddresses")
    @ax.Z8.a
    public List<Object> l;

    @c("phones")
    @ax.Z8.a
    public List<Object> m;

    @c("postalAddresses")
    @ax.Z8.a
    public List<Location> n;

    @c("websites")
    @ax.Z8.a
    public List<Object> o;

    @c("jobTitle")
    @ax.Z8.a
    public String p;

    @c("companyName")
    @ax.Z8.a
    public String q;

    @c("yomiCompany")
    @ax.Z8.a
    public String r;

    @c("department")
    @ax.Z8.a
    public String s;

    @c("officeLocation")
    @ax.Z8.a
    public String t;

    @c("profession")
    @ax.Z8.a
    public String u;

    @c("personType")
    @ax.Z8.a
    public PersonType v;

    @c("userPrincipalName")
    @ax.Z8.a
    public String w;

    @c("imAddress")
    @ax.Z8.a
    public String x;
    private transient l y;
    private transient e z;

    @Override // com.microsoft.graph.generated.BaseEntity, ax.Aa.d
    public void c(e eVar, l lVar) {
        this.z = eVar;
        this.y = lVar;
    }
}
