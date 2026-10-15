package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import com.microsoft.graph.extensions.Contact;
import com.microsoft.graph.extensions.ContactCollectionPage;
import com.microsoft.graph.extensions.ContactFolder;
import com.microsoft.graph.extensions.ContactFolderCollectionPage;
import com.microsoft.graph.extensions.Entity;
import com.microsoft.graph.extensions.MultiValueLegacyExtendedProperty;
import com.microsoft.graph.extensions.MultiValueLegacyExtendedPropertyCollectionPage;
import com.microsoft.graph.extensions.SingleValueLegacyExtendedProperty;
import com.microsoft.graph.extensions.SingleValueLegacyExtendedPropertyCollectionPage;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class BaseContactFolder extends Entity implements d {

    @c("parentFolderId")
    @ax.Z8.a
    public String f;

    @c("displayName")
    @ax.Z8.a
    public String g;
    public transient ContactCollectionPage h;
    public transient ContactFolderCollectionPage i;
    public transient SingleValueLegacyExtendedPropertyCollectionPage j;
    public transient MultiValueLegacyExtendedPropertyCollectionPage k;
    private transient l l;
    private transient e m;

    @Override // com.microsoft.graph.generated.BaseEntity, ax.Aa.d
    public void c(e eVar, l lVar) {
        this.m = eVar;
        this.l = lVar;
        if (lVar.w("contacts")) {
            BaseContactCollectionResponse baseContactCollectionResponse = new BaseContactCollectionResponse();
            if (lVar.w("contacts@odata.nextLink")) {
                baseContactCollectionResponse.b = lVar.t("contacts@odata.nextLink").j();
            }
            l[] lVarArr = (l[]) eVar.b(lVar.t("contacts").toString(), l[].class);
            Contact[] contactArr = new Contact[lVarArr.length];
            for (int i = 0; i < lVarArr.length; i++) {
                Contact contact = (Contact) eVar.b(lVarArr[i].toString(), Contact.class);
                contactArr[i] = contact;
                contact.c(eVar, lVarArr[i]);
            }
            baseContactCollectionResponse.a = Arrays.asList(contactArr);
            this.h = new ContactCollectionPage(baseContactCollectionResponse, null);
        }
        if (lVar.w("childFolders")) {
            BaseContactFolderCollectionResponse baseContactFolderCollectionResponse = new BaseContactFolderCollectionResponse();
            if (lVar.w("childFolders@odata.nextLink")) {
                baseContactFolderCollectionResponse.b = lVar.t("childFolders@odata.nextLink").j();
            }
            l[] lVarArr2 = (l[]) eVar.b(lVar.t("childFolders").toString(), l[].class);
            ContactFolder[] contactFolderArr = new ContactFolder[lVarArr2.length];
            for (int i2 = 0; i2 < lVarArr2.length; i2++) {
                ContactFolder contactFolder = (ContactFolder) eVar.b(lVarArr2[i2].toString(), ContactFolder.class);
                contactFolderArr[i2] = contactFolder;
                contactFolder.c(eVar, lVarArr2[i2]);
            }
            baseContactFolderCollectionResponse.a = Arrays.asList(contactFolderArr);
            this.i = new ContactFolderCollectionPage(baseContactFolderCollectionResponse, null);
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
            this.j = new SingleValueLegacyExtendedPropertyCollectionPage(baseSingleValueLegacyExtendedPropertyCollectionResponse, null);
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
            this.k = new MultiValueLegacyExtendedPropertyCollectionPage(baseMultiValueLegacyExtendedPropertyCollectionResponse, null);
        }
    }
}
