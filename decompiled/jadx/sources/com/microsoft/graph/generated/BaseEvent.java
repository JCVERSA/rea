package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import ax.p333ua.EnumC2423w;
import ax.p333ua.EnumC2429z;
import ax.p333ua.O0;
import ax.p333ua.Z0;
import com.microsoft.graph.extensions.Attachment;
import com.microsoft.graph.extensions.AttachmentCollectionPage;
import com.microsoft.graph.extensions.Calendar;
import com.microsoft.graph.extensions.DateTimeTimeZone;
import com.microsoft.graph.extensions.Event;
import com.microsoft.graph.extensions.EventCollectionPage;
import com.microsoft.graph.extensions.Extension;
import com.microsoft.graph.extensions.ExtensionCollectionPage;
import com.microsoft.graph.extensions.ItemBody;
import com.microsoft.graph.extensions.Location;
import com.microsoft.graph.extensions.MultiValueLegacyExtendedProperty;
import com.microsoft.graph.extensions.MultiValueLegacyExtendedPropertyCollectionPage;
import com.microsoft.graph.extensions.OutlookItem;
import com.microsoft.graph.extensions.PatternedRecurrence;
import com.microsoft.graph.extensions.Recipient;
import com.microsoft.graph.extensions.ResponseStatus;
import com.microsoft.graph.extensions.SingleValueLegacyExtendedProperty;
import com.microsoft.graph.extensions.SingleValueLegacyExtendedPropertyCollectionPage;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class BaseEvent extends OutlookItem implements d {

    @c("location")
    @ax.Z8.a
    public Location A;

    @c("locations")
    @ax.Z8.a
    public List<Location> B;

    @c("isAllDay")
    @ax.Z8.a
    public Boolean C;

    @c("isCancelled")
    @ax.Z8.a
    public Boolean D;

    @c("isOrganizer")
    @ax.Z8.a
    public Boolean E;

    @c("recurrence")
    @ax.Z8.a
    public PatternedRecurrence F;

    @c("responseRequested")
    @ax.Z8.a
    public Boolean G;

    @c("seriesMasterId")
    @ax.Z8.a
    public String H;

    @c("showAs")
    @ax.Z8.a
    public EnumC2429z I;

    @c("type")
    @ax.Z8.a
    public EnumC2423w J;

    @c("attendees")
    @ax.Z8.a
    public List<Object> K;

    @c("organizer")
    @ax.Z8.a
    public Recipient L;

    @c("webLink")
    @ax.Z8.a
    public String M;

    @c("onlineMeetingUrl")
    @ax.Z8.a
    public String N;

    @c("calendar")
    @ax.Z8.a
    public Calendar O;
    public transient EventCollectionPage P;
    public transient ExtensionCollectionPage Q;
    public transient AttachmentCollectionPage R;
    public transient SingleValueLegacyExtendedPropertyCollectionPage S;
    public transient MultiValueLegacyExtendedPropertyCollectionPage T;
    private transient l U;
    private transient e V;

    @c("originalStartTimeZone")
    @ax.Z8.a
    public String l;

    @c("originalEndTimeZone")
    @ax.Z8.a
    public String m;

    @c("responseStatus")
    @ax.Z8.a
    public ResponseStatus n;

    @c("iCalUId")
    @ax.Z8.a
    public String o;

    @c("reminderMinutesBeforeStart")
    @ax.Z8.a
    public Integer p;

    @c("isReminderOn")
    @ax.Z8.a
    public Boolean q;

    @c("hasAttachments")
    @ax.Z8.a
    public Boolean r;

    @c("subject")
    @ax.Z8.a
    public String s;

    @c("body")
    @ax.Z8.a
    public ItemBody t;

    @c("bodyPreview")
    @ax.Z8.a
    public String u;

    @c("importance")
    @ax.Z8.a
    public O0 v;

    @c("sensitivity")
    @ax.Z8.a
    public Z0 w;

    @c("start")
    @ax.Z8.a
    public DateTimeTimeZone x;

    @c("originalStart")
    @ax.Z8.a
    public java.util.Calendar y;

    @c("end")
    @ax.Z8.a
    public DateTimeTimeZone z;

    @Override // com.microsoft.graph.generated.BaseOutlookItem, com.microsoft.graph.generated.BaseEntity, ax.Aa.d
    public void c(e eVar, l lVar) {
        this.V = eVar;
        this.U = lVar;
        if (lVar.w("instances")) {
            BaseEventCollectionResponse baseEventCollectionResponse = new BaseEventCollectionResponse();
            if (lVar.w("instances@odata.nextLink")) {
                baseEventCollectionResponse.b = lVar.t("instances@odata.nextLink").j();
            }
            l[] lVarArr = (l[]) eVar.b(lVar.t("instances").toString(), l[].class);
            Event[] eventArr = new Event[lVarArr.length];
            for (int i = 0; i < lVarArr.length; i++) {
                Event event = (Event) eVar.b(lVarArr[i].toString(), Event.class);
                eventArr[i] = event;
                event.c(eVar, lVarArr[i]);
            }
            baseEventCollectionResponse.a = Arrays.asList(eventArr);
            this.P = new EventCollectionPage(baseEventCollectionResponse, null);
        }
        if (lVar.w("extensions")) {
            BaseExtensionCollectionResponse baseExtensionCollectionResponse = new BaseExtensionCollectionResponse();
            if (lVar.w("extensions@odata.nextLink")) {
                baseExtensionCollectionResponse.b = lVar.t("extensions@odata.nextLink").j();
            }
            l[] lVarArr2 = (l[]) eVar.b(lVar.t("extensions").toString(), l[].class);
            Extension[] extensionArr = new Extension[lVarArr2.length];
            for (int i2 = 0; i2 < lVarArr2.length; i2++) {
                Extension extension = (Extension) eVar.b(lVarArr2[i2].toString(), Extension.class);
                extensionArr[i2] = extension;
                extension.c(eVar, lVarArr2[i2]);
            }
            baseExtensionCollectionResponse.a = Arrays.asList(extensionArr);
            this.Q = new ExtensionCollectionPage(baseExtensionCollectionResponse, null);
        }
        if (lVar.w("attachments")) {
            BaseAttachmentCollectionResponse baseAttachmentCollectionResponse = new BaseAttachmentCollectionResponse();
            if (lVar.w("attachments@odata.nextLink")) {
                baseAttachmentCollectionResponse.b = lVar.t("attachments@odata.nextLink").j();
            }
            l[] lVarArr3 = (l[]) eVar.b(lVar.t("attachments").toString(), l[].class);
            Attachment[] attachmentArr = new Attachment[lVarArr3.length];
            for (int i3 = 0; i3 < lVarArr3.length; i3++) {
                Attachment attachment = (Attachment) eVar.b(lVarArr3[i3].toString(), Attachment.class);
                attachmentArr[i3] = attachment;
                attachment.c(eVar, lVarArr3[i3]);
            }
            baseAttachmentCollectionResponse.a = Arrays.asList(attachmentArr);
            this.R = new AttachmentCollectionPage(baseAttachmentCollectionResponse, null);
        }
        if (lVar.w("singleValueExtendedProperties")) {
            BaseSingleValueLegacyExtendedPropertyCollectionResponse baseSingleValueLegacyExtendedPropertyCollectionResponse = new BaseSingleValueLegacyExtendedPropertyCollectionResponse();
            if (lVar.w("singleValueExtendedProperties@odata.nextLink")) {
                baseSingleValueLegacyExtendedPropertyCollectionResponse.b = lVar.t("singleValueExtendedProperties@odata.nextLink").j();
            }
            l[] lVarArr4 = (l[]) eVar.b(lVar.t("singleValueExtendedProperties").toString(), l[].class);
            SingleValueLegacyExtendedProperty[] singleValueLegacyExtendedPropertyArr = new SingleValueLegacyExtendedProperty[lVarArr4.length];
            for (int i4 = 0; i4 < lVarArr4.length; i4++) {
                SingleValueLegacyExtendedProperty singleValueLegacyExtendedProperty = (SingleValueLegacyExtendedProperty) eVar.b(lVarArr4[i4].toString(), SingleValueLegacyExtendedProperty.class);
                singleValueLegacyExtendedPropertyArr[i4] = singleValueLegacyExtendedProperty;
                singleValueLegacyExtendedProperty.c(eVar, lVarArr4[i4]);
            }
            baseSingleValueLegacyExtendedPropertyCollectionResponse.a = Arrays.asList(singleValueLegacyExtendedPropertyArr);
            this.S = new SingleValueLegacyExtendedPropertyCollectionPage(baseSingleValueLegacyExtendedPropertyCollectionResponse, null);
        }
        if (lVar.w("multiValueExtendedProperties")) {
            BaseMultiValueLegacyExtendedPropertyCollectionResponse baseMultiValueLegacyExtendedPropertyCollectionResponse = new BaseMultiValueLegacyExtendedPropertyCollectionResponse();
            if (lVar.w("multiValueExtendedProperties@odata.nextLink")) {
                baseMultiValueLegacyExtendedPropertyCollectionResponse.b = lVar.t("multiValueExtendedProperties@odata.nextLink").j();
            }
            l[] lVarArr5 = (l[]) eVar.b(lVar.t("multiValueExtendedProperties").toString(), l[].class);
            MultiValueLegacyExtendedProperty[] multiValueLegacyExtendedPropertyArr = new MultiValueLegacyExtendedProperty[lVarArr5.length];
            for (int i5 = 0; i5 < lVarArr5.length; i5++) {
                MultiValueLegacyExtendedProperty multiValueLegacyExtendedProperty = (MultiValueLegacyExtendedProperty) eVar.b(lVarArr5[i5].toString(), MultiValueLegacyExtendedProperty.class);
                multiValueLegacyExtendedPropertyArr[i5] = multiValueLegacyExtendedProperty;
                multiValueLegacyExtendedProperty.c(eVar, lVarArr5[i5]);
            }
            baseMultiValueLegacyExtendedPropertyCollectionResponse.a = Arrays.asList(multiValueLegacyExtendedPropertyArr);
            this.T = new MultiValueLegacyExtendedPropertyCollectionPage(baseMultiValueLegacyExtendedPropertyCollectionResponse, null);
        }
    }
}
