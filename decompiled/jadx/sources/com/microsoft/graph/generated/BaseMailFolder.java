package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import com.microsoft.graph.extensions.Entity;
import com.microsoft.graph.extensions.MailFolder;
import com.microsoft.graph.extensions.MailFolderCollectionPage;
import com.microsoft.graph.extensions.Message;
import com.microsoft.graph.extensions.MessageCollectionPage;
import com.microsoft.graph.extensions.MessageRule;
import com.microsoft.graph.extensions.MessageRuleCollectionPage;
import com.microsoft.graph.extensions.MultiValueLegacyExtendedProperty;
import com.microsoft.graph.extensions.MultiValueLegacyExtendedPropertyCollectionPage;
import com.microsoft.graph.extensions.SingleValueLegacyExtendedProperty;
import com.microsoft.graph.extensions.SingleValueLegacyExtendedPropertyCollectionPage;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class BaseMailFolder extends Entity implements d {

    @c("displayName")
    @ax.Z8.a
    public String f;

    @c("parentFolderId")
    @ax.Z8.a
    public String g;

    @c("childFolderCount")
    @ax.Z8.a
    public Integer h;

    @c("unreadItemCount")
    @ax.Z8.a
    public Integer i;

    @c("totalItemCount")
    @ax.Z8.a
    public Integer j;
    public transient MessageCollectionPage k;
    public transient MessageRuleCollectionPage l;
    public transient MailFolderCollectionPage m;
    public transient SingleValueLegacyExtendedPropertyCollectionPage n;
    public transient MultiValueLegacyExtendedPropertyCollectionPage o;
    private transient l p;
    private transient e q;

    @Override // com.microsoft.graph.generated.BaseEntity, ax.Aa.d
    public void c(e eVar, l lVar) {
        this.q = eVar;
        this.p = lVar;
        if (lVar.w("messages")) {
            BaseMessageCollectionResponse baseMessageCollectionResponse = new BaseMessageCollectionResponse();
            if (lVar.w("messages@odata.nextLink")) {
                baseMessageCollectionResponse.b = lVar.t("messages@odata.nextLink").j();
            }
            l[] lVarArr = (l[]) eVar.b(lVar.t("messages").toString(), l[].class);
            Message[] messageArr = new Message[lVarArr.length];
            for (int i = 0; i < lVarArr.length; i++) {
                Message message = (Message) eVar.b(lVarArr[i].toString(), Message.class);
                messageArr[i] = message;
                message.c(eVar, lVarArr[i]);
            }
            baseMessageCollectionResponse.a = Arrays.asList(messageArr);
            this.k = new MessageCollectionPage(baseMessageCollectionResponse, null);
        }
        if (lVar.w("messageRules")) {
            BaseMessageRuleCollectionResponse baseMessageRuleCollectionResponse = new BaseMessageRuleCollectionResponse();
            if (lVar.w("messageRules@odata.nextLink")) {
                baseMessageRuleCollectionResponse.b = lVar.t("messageRules@odata.nextLink").j();
            }
            l[] lVarArr2 = (l[]) eVar.b(lVar.t("messageRules").toString(), l[].class);
            MessageRule[] messageRuleArr = new MessageRule[lVarArr2.length];
            for (int i2 = 0; i2 < lVarArr2.length; i2++) {
                MessageRule messageRule = (MessageRule) eVar.b(lVarArr2[i2].toString(), MessageRule.class);
                messageRuleArr[i2] = messageRule;
                messageRule.c(eVar, lVarArr2[i2]);
            }
            baseMessageRuleCollectionResponse.a = Arrays.asList(messageRuleArr);
            this.l = new MessageRuleCollectionPage(baseMessageRuleCollectionResponse, null);
        }
        if (lVar.w("childFolders")) {
            BaseMailFolderCollectionResponse baseMailFolderCollectionResponse = new BaseMailFolderCollectionResponse();
            if (lVar.w("childFolders@odata.nextLink")) {
                baseMailFolderCollectionResponse.b = lVar.t("childFolders@odata.nextLink").j();
            }
            l[] lVarArr3 = (l[]) eVar.b(lVar.t("childFolders").toString(), l[].class);
            MailFolder[] mailFolderArr = new MailFolder[lVarArr3.length];
            for (int i3 = 0; i3 < lVarArr3.length; i3++) {
                MailFolder mailFolder = (MailFolder) eVar.b(lVarArr3[i3].toString(), MailFolder.class);
                mailFolderArr[i3] = mailFolder;
                mailFolder.c(eVar, lVarArr3[i3]);
            }
            baseMailFolderCollectionResponse.a = Arrays.asList(mailFolderArr);
            this.m = new MailFolderCollectionPage(baseMailFolderCollectionResponse, null);
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
            this.n = new SingleValueLegacyExtendedPropertyCollectionPage(baseSingleValueLegacyExtendedPropertyCollectionResponse, null);
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
            this.o = new MultiValueLegacyExtendedPropertyCollectionPage(baseMultiValueLegacyExtendedPropertyCollectionResponse, null);
        }
    }
}
