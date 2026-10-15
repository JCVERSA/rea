package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import ax.p333ua.O0;
import ax.p333ua.P0;
import com.microsoft.graph.extensions.Attachment;
import com.microsoft.graph.extensions.AttachmentCollectionPage;
import com.microsoft.graph.extensions.Extension;
import com.microsoft.graph.extensions.ExtensionCollectionPage;
import com.microsoft.graph.extensions.FollowupFlag;
import com.microsoft.graph.extensions.ItemBody;
import com.microsoft.graph.extensions.MultiValueLegacyExtendedProperty;
import com.microsoft.graph.extensions.MultiValueLegacyExtendedPropertyCollectionPage;
import com.microsoft.graph.extensions.OutlookItem;
import com.microsoft.graph.extensions.Recipient;
import com.microsoft.graph.extensions.SingleValueLegacyExtendedProperty;
import com.microsoft.graph.extensions.SingleValueLegacyExtendedPropertyCollectionPage;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class BaseMessage extends OutlookItem implements d {

    @c("replyTo")
    @ax.Z8.a
    public List<Recipient> A;

    @c("conversationId")
    @ax.Z8.a
    public String B;

    @c("uniqueBody")
    @ax.Z8.a
    public ItemBody C;

    @c("isDeliveryReceiptRequested")
    @ax.Z8.a
    public Boolean D;

    @c("isReadReceiptRequested")
    @ax.Z8.a
    public Boolean E;

    @c("isRead")
    @ax.Z8.a
    public Boolean F;

    @c("isDraft")
    @ax.Z8.a
    public Boolean G;

    @c("webLink")
    @ax.Z8.a
    public String H;

    @c("inferenceClassification")
    @ax.Z8.a
    public P0 I;

    @c("flag")
    @ax.Z8.a
    public FollowupFlag J;
    public transient AttachmentCollectionPage K;
    public transient ExtensionCollectionPage L;
    public transient SingleValueLegacyExtendedPropertyCollectionPage M;
    public transient MultiValueLegacyExtendedPropertyCollectionPage N;
    private transient l O;
    private transient e P;

    @c("receivedDateTime")
    @ax.Z8.a
    public Calendar l;

    @c("sentDateTime")
    @ax.Z8.a
    public Calendar m;

    @c("hasAttachments")
    @ax.Z8.a
    public Boolean n;

    @c("internetMessageId")
    @ax.Z8.a
    public String o;

    @c("internetMessageHeaders")
    @ax.Z8.a
    public List<Object> p;

    @c("subject")
    @ax.Z8.a
    public String q;

    @c("body")
    @ax.Z8.a
    public ItemBody r;

    @c("bodyPreview")
    @ax.Z8.a
    public String s;

    @c("importance")
    @ax.Z8.a
    public O0 t;

    @c("parentFolderId")
    @ax.Z8.a
    public String u;

    @c("sender")
    @ax.Z8.a
    public Recipient v;

    @c("from")
    @ax.Z8.a
    public Recipient w;

    @c("toRecipients")
    @ax.Z8.a
    public List<Recipient> x;

    @c("ccRecipients")
    @ax.Z8.a
    public List<Recipient> y;

    @c("bccRecipients")
    @ax.Z8.a
    public List<Recipient> z;

    @Override // com.microsoft.graph.generated.BaseOutlookItem, com.microsoft.graph.generated.BaseEntity, ax.Aa.d
    public void c(e eVar, l lVar) {
        this.P = eVar;
        this.O = lVar;
        if (lVar.w("attachments")) {
            BaseAttachmentCollectionResponse baseAttachmentCollectionResponse = new BaseAttachmentCollectionResponse();
            if (lVar.w("attachments@odata.nextLink")) {
                baseAttachmentCollectionResponse.b = lVar.t("attachments@odata.nextLink").j();
            }
            l[] lVarArr = (l[]) eVar.b(lVar.t("attachments").toString(), l[].class);
            Attachment[] attachmentArr = new Attachment[lVarArr.length];
            for (int i = 0; i < lVarArr.length; i++) {
                Attachment attachment = (Attachment) eVar.b(lVarArr[i].toString(), Attachment.class);
                attachmentArr[i] = attachment;
                attachment.c(eVar, lVarArr[i]);
            }
            baseAttachmentCollectionResponse.a = Arrays.asList(attachmentArr);
            this.K = new AttachmentCollectionPage(baseAttachmentCollectionResponse, null);
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
            this.L = new ExtensionCollectionPage(baseExtensionCollectionResponse, null);
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
            this.M = new SingleValueLegacyExtendedPropertyCollectionPage(baseSingleValueLegacyExtendedPropertyCollectionResponse, null);
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
            this.N = new MultiValueLegacyExtendedPropertyCollectionPage(baseMultiValueLegacyExtendedPropertyCollectionResponse, null);
        }
    }
}
