package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import com.microsoft.graph.extensions.BooleanColumn;
import com.microsoft.graph.extensions.CalculatedColumn;
import com.microsoft.graph.extensions.ChoiceColumn;
import com.microsoft.graph.extensions.CurrencyColumn;
import com.microsoft.graph.extensions.DateTimeColumn;
import com.microsoft.graph.extensions.DefaultColumnValue;
import com.microsoft.graph.extensions.Entity;
import com.microsoft.graph.extensions.LookupColumn;
import com.microsoft.graph.extensions.NumberColumn;
import com.microsoft.graph.extensions.PersonOrGroupColumn;
import com.microsoft.graph.extensions.TextColumn;

/* JADX INFO: loaded from: classes3.dex */
public class BaseColumnDefinition extends Entity implements d {

    @c("boolean")
    @ax.Z8.a
    public BooleanColumn f;

    @c("calculated")
    @ax.Z8.a
    public CalculatedColumn g;

    @c("choice")
    @ax.Z8.a
    public ChoiceColumn h;

    @c("columnGroup")
    @ax.Z8.a
    public String i;

    @c("currency")
    @ax.Z8.a
    public CurrencyColumn j;

    @c("dateTime")
    @ax.Z8.a
    public DateTimeColumn k;

    @c("defaultValue")
    @ax.Z8.a
    public DefaultColumnValue l;

    @c("description")
    @ax.Z8.a
    public String m;

    @c("displayName")
    @ax.Z8.a
    public String n;

    @c("enforceUniqueValues")
    @ax.Z8.a
    public Boolean o;

    @c("hidden")
    @ax.Z8.a
    public Boolean p;

    @c("indexed")
    @ax.Z8.a
    public Boolean q;

    @c("lookup")
    @ax.Z8.a
    public LookupColumn r;

    @c("name")
    @ax.Z8.a
    public String s;

    @c("number")
    @ax.Z8.a
    public NumberColumn t;

    @c("personOrGroup")
    @ax.Z8.a
    public PersonOrGroupColumn u;

    @c("readOnly")
    @ax.Z8.a
    public Boolean v;

    @c("required")
    @ax.Z8.a
    public Boolean w;

    @c("text")
    @ax.Z8.a
    public TextColumn x;
    private transient l y;
    private transient e z;

    @Override // com.microsoft.graph.generated.BaseEntity, ax.Aa.d
    public void c(e eVar, l lVar) {
        this.z = eVar;
        this.y = lVar;
    }
}
