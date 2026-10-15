package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import ax.p333ua.EnumC2384c;
import com.microsoft.graph.extensions.EmailAddress;
import com.microsoft.graph.extensions.Entity;
import com.microsoft.graph.extensions.Event;
import com.microsoft.graph.extensions.EventCollectionPage;
import com.microsoft.graph.extensions.MultiValueLegacyExtendedProperty;
import com.microsoft.graph.extensions.MultiValueLegacyExtendedPropertyCollectionPage;
import com.microsoft.graph.extensions.SingleValueLegacyExtendedProperty;
import com.microsoft.graph.extensions.SingleValueLegacyExtendedPropertyCollectionPage;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class BaseCalendar extends Entity implements d {

    @c("name")
    @ax.Z8.a
    public String f;

    @c("color")
    @ax.Z8.a
    public EnumC2384c g;

    @c("changeKey")
    @ax.Z8.a
    public String h;

    @c("canShare")
    @ax.Z8.a
    public Boolean i;

    @c("canViewPrivateItems")
    @ax.Z8.a
    public Boolean j;

    @c("canEdit")
    @ax.Z8.a
    public Boolean k;

    @c("owner")
    @ax.Z8.a
    public EmailAddress l;
    public transient EventCollectionPage m;
    public transient EventCollectionPage n;
    public transient SingleValueLegacyExtendedPropertyCollectionPage o;
    public transient MultiValueLegacyExtendedPropertyCollectionPage p;
    private transient l q;
    private transient e r;

    @Override // com.microsoft.graph.generated.BaseEntity, ax.Aa.d
    public void c(e eVar, l lVar) {
        this.r = eVar;
        this.q = lVar;
        if (lVar.w("events")) {
            BaseEventCollectionResponse baseEventCollectionResponse = new BaseEventCollectionResponse();
            if (lVar.w("events@odata.nextLink")) {
                baseEventCollectionResponse.b = lVar.t("events@odata.nextLink").j();
            }
            l[] lVarArr = (l[]) eVar.b(lVar.t("events").toString(), l[].class);
            Event[] eventArr = new Event[lVarArr.length];
            for (int i = 0; i < lVarArr.length; i++) {
                Event event = (Event) eVar.b(lVarArr[i].toString(), Event.class);
                eventArr[i] = event;
                event.c(eVar, lVarArr[i]);
            }
            baseEventCollectionResponse.a = Arrays.asList(eventArr);
            this.m = new EventCollectionPage(baseEventCollectionResponse, null);
        }
        if (lVar.w("calendarView")) {
            BaseEventCollectionResponse baseEventCollectionResponse2 = new BaseEventCollectionResponse();
            if (lVar.w("calendarView@odata.nextLink")) {
                baseEventCollectionResponse2.b = lVar.t("calendarView@odata.nextLink").j();
            }
            l[] lVarArr2 = (l[]) eVar.b(lVar.t("calendarView").toString(), l[].class);
            Event[] eventArr2 = new Event[lVarArr2.length];
            for (int i2 = 0; i2 < lVarArr2.length; i2++) {
                Event event2 = (Event) eVar.b(lVarArr2[i2].toString(), Event.class);
                eventArr2[i2] = event2;
                event2.c(eVar, lVarArr2[i2]);
            }
            baseEventCollectionResponse2.a = Arrays.asList(eventArr2);
            this.n = new EventCollectionPage(baseEventCollectionResponse2, null);
        }
        if (lVar.w("singleValueExtendedProperties")) {
            BaseSingleValueLegacyExtendedPropertyCollectionResponse baseSingleValueLegacyExtendedPropertyCollectionResponse = new BaseSingleValueLegacyExtendedPropertyCollectionResponse();
            if (lVar.w("singleValueExtendedProperties@odata.nextLink")) {
                baseSingleValueLegacyExtendedPropertyCollectionResponse.b = lVar.t("singleValueExtendedProperties@odata.nextLink").j();
            }
            l[] lVarArr3 = (l[]) eVar.b(lVar.t("singleValueExtendedProperties").toString(), l[].class);
            SingleValueLegacyExtendedProperty[] singleValueLegacyExtendedPropertyArr = new SingleValueLegacyExtendedProperty[lVarArr3.length];
            for (int i3 = 0; i3 < lVarArr3.length; i3++) {
                SingleValueLegacyExtendedProperty singleValueLegacyExtendedProperty = (SingleValueLegacyExtendedProperty) eVar.b(lVarArr3[i3].toString(), SingleValueLegacyExtendedProperty.class);
                singleValueLegacyExtendedPropertyArr[i3] = singleValueLegacyExtendedProperty;
                singleValueLegacyExtendedProperty.c(eVar, lVarArr3[i3]);
            }
            baseSingleValueLegacyExtendedPropertyCollectionResponse.a = Arrays.asList(singleValueLegacyExtendedPropertyArr);
            this.o = new SingleValueLegacyExtendedPropertyCollectionPage(baseSingleValueLegacyExtendedPropertyCollectionResponse, null);
        }
        if (lVar.w("multiValueExtendedProperties")) {
            BaseMultiValueLegacyExtendedPropertyCollectionResponse baseMultiValueLegacyExtendedPropertyCollectionResponse = new BaseMultiValueLegacyExtendedPropertyCollectionResponse();
            if (lVar.w("multiValueExtendedProperties@odata.nextLink")) {
                baseMultiValueLegacyExtendedPropertyCollectionResponse.b = lVar.t("multiValueExtendedProperties@odata.nextLink").j();
            }
            l[] lVarArr4 = (l[]) eVar.b(lVar.t("multiValueExtendedProperties").toString(), l[].class);
            MultiValueLegacyExtendedProperty[] multiValueLegacyExtendedPropertyArr = new MultiValueLegacyExtendedProperty[lVarArr4.length];
            for (int i4 = 0; i4 < lVarArr4.length; i4++) {
                MultiValueLegacyExtendedProperty multiValueLegacyExtendedProperty = (MultiValueLegacyExtendedProperty) eVar.b(lVarArr4[i4].toString(), MultiValueLegacyExtendedProperty.class);
                multiValueLegacyExtendedPropertyArr[i4] = multiValueLegacyExtendedProperty;
                multiValueLegacyExtendedProperty.c(eVar, lVarArr4[i4]);
            }
            baseMultiValueLegacyExtendedPropertyCollectionResponse.a = Arrays.asList(multiValueLegacyExtendedPropertyArr);
            this.p = new MultiValueLegacyExtendedPropertyCollectionPage(baseMultiValueLegacyExtendedPropertyCollectionResponse, null);
        }
    }
}
