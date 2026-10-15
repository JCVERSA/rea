package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import com.microsoft.graph.extensions.EmailAddress;
import com.microsoft.graph.extensions.Extension;
import com.microsoft.graph.extensions.ExtensionCollectionPage;
import com.microsoft.graph.extensions.MultiValueLegacyExtendedProperty;
import com.microsoft.graph.extensions.MultiValueLegacyExtendedPropertyCollectionPage;
import com.microsoft.graph.extensions.OutlookItem;
import com.microsoft.graph.extensions.PhysicalAddress;
import com.microsoft.graph.extensions.ProfilePhoto;
import com.microsoft.graph.extensions.SingleValueLegacyExtendedProperty;
import com.microsoft.graph.extensions.SingleValueLegacyExtendedPropertyCollectionPage;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class BaseContact extends OutlookItem implements d {

    @c("imAddresses")
    @ax.Z8.a
    public List<String> A;

    @c("jobTitle")
    @ax.Z8.a
    public String B;

    @c("companyName")
    @ax.Z8.a
    public String C;

    @c("department")
    @ax.Z8.a
    public String D;

    @c("officeLocation")
    @ax.Z8.a
    public String E;

    @c("profession")
    @ax.Z8.a
    public String F;

    @c("businessHomePage")
    @ax.Z8.a
    public String G;

    @c("assistantName")
    @ax.Z8.a
    public String H;

    @c("manager")
    @ax.Z8.a
    public String I;

    @c("homePhones")
    @ax.Z8.a
    public List<String> J;

    @c("mobilePhone")
    @ax.Z8.a
    public String K;

    @c("businessPhones")
    @ax.Z8.a
    public List<String> L;

    @c("homeAddress")
    @ax.Z8.a
    public PhysicalAddress M;

    @c("businessAddress")
    @ax.Z8.a
    public PhysicalAddress N;

    @c("otherAddress")
    @ax.Z8.a
    public PhysicalAddress O;

    @c("spouseName")
    @ax.Z8.a
    public String P;

    @c("personalNotes")
    @ax.Z8.a
    public String Q;

    @c("children")
    @ax.Z8.a
    public List<String> R;
    public transient ExtensionCollectionPage S;
    public transient SingleValueLegacyExtendedPropertyCollectionPage T;
    public transient MultiValueLegacyExtendedPropertyCollectionPage U;

    @c("photo")
    @ax.Z8.a
    public ProfilePhoto V;
    private transient l W;
    private transient e X;

    @c("parentFolderId")
    @ax.Z8.a
    public String l;

    @c("birthday")
    @ax.Z8.a
    public Calendar m;

    @c("fileAs")
    @ax.Z8.a
    public String n;

    @c("displayName")
    @ax.Z8.a
    public String o;

    @c("givenName")
    @ax.Z8.a
    public String p;

    @c("initials")
    @ax.Z8.a
    public String q;

    @c("middleName")
    @ax.Z8.a
    public String r;

    @c("nickName")
    @ax.Z8.a
    public String s;

    @c("surname")
    @ax.Z8.a
    public String t;

    @c("title")
    @ax.Z8.a
    public String u;

    @c("yomiGivenName")
    @ax.Z8.a
    public String v;

    @c("yomiSurname")
    @ax.Z8.a
    public String w;

    @c("yomiCompanyName")
    @ax.Z8.a
    public String x;

    @c("generation")
    @ax.Z8.a
    public String y;

    @c("emailAddresses")
    @ax.Z8.a
    public List<EmailAddress> z;

    @Override // com.microsoft.graph.generated.BaseOutlookItem, com.microsoft.graph.generated.BaseEntity, ax.Aa.d
    public void c(e eVar, l lVar) {
        this.X = eVar;
        this.W = lVar;
        if (lVar.w("extensions")) {
            BaseExtensionCollectionResponse baseExtensionCollectionResponse = new BaseExtensionCollectionResponse();
            if (lVar.w("extensions@odata.nextLink")) {
                baseExtensionCollectionResponse.b = lVar.t("extensions@odata.nextLink").j();
            }
            l[] lVarArr = (l[]) eVar.b(lVar.t("extensions").toString(), l[].class);
            Extension[] extensionArr = new Extension[lVarArr.length];
            for (int i = 0; i < lVarArr.length; i++) {
                Extension extension = (Extension) eVar.b(lVarArr[i].toString(), Extension.class);
                extensionArr[i] = extension;
                extension.c(eVar, lVarArr[i]);
            }
            baseExtensionCollectionResponse.a = Arrays.asList(extensionArr);
            this.S = new ExtensionCollectionPage(baseExtensionCollectionResponse, null);
        }
        if (lVar.w("singleValueExtendedProperties")) {
            BaseSingleValueLegacyExtendedPropertyCollectionResponse baseSingleValueLegacyExtendedPropertyCollectionResponse = new BaseSingleValueLegacyExtendedPropertyCollectionResponse();
            if (lVar.w("singleValueExtendedProperties@odata.nextLink")) {
                baseSingleValueLegacyExtendedPropertyCollectionResponse.b = lVar.t("singleValueExtendedProperties@odata.nextLink").j();
            }
            l[] lVarArr2 = (l[]) eVar.b(lVar.t("singleValueExtendedProperties").toString(), l[].class);
            SingleValueLegacyExtendedProperty[] singleValueLegacyExtendedPropertyArr = new SingleValueLegacyExtendedProperty[lVarArr2.length];
            for (int i2 = 0; i2 < lVarArr2.length; i2++) {
                SingleValueLegacyExtendedProperty singleValueLegacyExtendedProperty = (SingleValueLegacyExtendedProperty) eVar.b(lVarArr2[i2].toString(), SingleValueLegacyExtendedProperty.class);
                singleValueLegacyExtendedPropertyArr[i2] = singleValueLegacyExtendedProperty;
                singleValueLegacyExtendedProperty.c(eVar, lVarArr2[i2]);
            }
            baseSingleValueLegacyExtendedPropertyCollectionResponse.a = Arrays.asList(singleValueLegacyExtendedPropertyArr);
            this.T = new SingleValueLegacyExtendedPropertyCollectionPage(baseSingleValueLegacyExtendedPropertyCollectionResponse, null);
        }
        if (lVar.w("multiValueExtendedProperties")) {
            BaseMultiValueLegacyExtendedPropertyCollectionResponse baseMultiValueLegacyExtendedPropertyCollectionResponse = new BaseMultiValueLegacyExtendedPropertyCollectionResponse();
            if (lVar.w("multiValueExtendedProperties@odata.nextLink")) {
                baseMultiValueLegacyExtendedPropertyCollectionResponse.b = lVar.t("multiValueExtendedProperties@odata.nextLink").j();
            }
            l[] lVarArr3 = (l[]) eVar.b(lVar.t("multiValueExtendedProperties").toString(), l[].class);
            MultiValueLegacyExtendedProperty[] multiValueLegacyExtendedPropertyArr = new MultiValueLegacyExtendedProperty[lVarArr3.length];
            for (int i3 = 0; i3 < lVarArr3.length; i3++) {
                MultiValueLegacyExtendedProperty multiValueLegacyExtendedProperty = (MultiValueLegacyExtendedProperty) eVar.b(lVarArr3[i3].toString(), MultiValueLegacyExtendedProperty.class);
                multiValueLegacyExtendedPropertyArr[i3] = multiValueLegacyExtendedProperty;
                multiValueLegacyExtendedProperty.c(eVar, lVarArr3[i3]);
            }
            baseMultiValueLegacyExtendedPropertyCollectionResponse.a = Arrays.asList(multiValueLegacyExtendedPropertyArr);
            this.U = new MultiValueLegacyExtendedPropertyCollectionPage(baseMultiValueLegacyExtendedPropertyCollectionResponse, null);
        }
    }
}
