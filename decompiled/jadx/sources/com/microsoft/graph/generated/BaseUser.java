package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import com.microsoft.graph.extensions.CalendarCollectionPage;
import com.microsoft.graph.extensions.CalendarGroup;
import com.microsoft.graph.extensions.CalendarGroupCollectionPage;
import com.microsoft.graph.extensions.Contact;
import com.microsoft.graph.extensions.ContactCollectionPage;
import com.microsoft.graph.extensions.ContactFolder;
import com.microsoft.graph.extensions.ContactFolderCollectionPage;
import com.microsoft.graph.extensions.DirectoryObject;
import com.microsoft.graph.extensions.DirectoryObjectCollectionPage;
import com.microsoft.graph.extensions.Drive;
import com.microsoft.graph.extensions.DriveCollectionPage;
import com.microsoft.graph.extensions.Event;
import com.microsoft.graph.extensions.EventCollectionPage;
import com.microsoft.graph.extensions.Extension;
import com.microsoft.graph.extensions.ExtensionCollectionPage;
import com.microsoft.graph.extensions.InferenceClassification;
import com.microsoft.graph.extensions.LicenseDetails;
import com.microsoft.graph.extensions.LicenseDetailsCollectionPage;
import com.microsoft.graph.extensions.MailFolder;
import com.microsoft.graph.extensions.MailFolderCollectionPage;
import com.microsoft.graph.extensions.MailboxSettings;
import com.microsoft.graph.extensions.Message;
import com.microsoft.graph.extensions.MessageCollectionPage;
import com.microsoft.graph.extensions.Onenote;
import com.microsoft.graph.extensions.OutlookUser;
import com.microsoft.graph.extensions.PasswordProfile;
import com.microsoft.graph.extensions.Person;
import com.microsoft.graph.extensions.PersonCollectionPage;
import com.microsoft.graph.extensions.PlannerUser;
import com.microsoft.graph.extensions.ProfilePhoto;
import com.microsoft.graph.extensions.ProfilePhotoCollectionPage;
import com.microsoft.graph.extensions.UserActivity;
import com.microsoft.graph.extensions.UserActivityCollectionPage;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class BaseUser extends DirectoryObject implements d {

    @c("onPremisesSyncEnabled")
    @ax.Z8.a
    public Boolean A;
    public transient UserActivityCollectionPage A0;

    @c("passwordPolicies")
    @ax.Z8.a
    public String B;
    private transient l B0;

    @c("passwordProfile")
    @ax.Z8.a
    public PasswordProfile C;
    private transient e C0;

    @c("officeLocation")
    @ax.Z8.a
    public String D;

    @c("postalCode")
    @ax.Z8.a
    public String E;

    @c("preferredLanguage")
    @ax.Z8.a
    public String F;

    @c("provisionedPlans")
    @ax.Z8.a
    public List<Object> G;

    @c("proxyAddresses")
    @ax.Z8.a
    public List<String> H;

    @c("state")
    @ax.Z8.a
    public String I;

    @c("streetAddress")
    @ax.Z8.a
    public String J;

    @c("surname")
    @ax.Z8.a
    public String K;

    @c("usageLocation")
    @ax.Z8.a
    public String L;

    @c("userPrincipalName")
    @ax.Z8.a
    public String M;

    @c("userType")
    @ax.Z8.a
    public String N;

    @c("mailboxSettings")
    @ax.Z8.a
    public MailboxSettings O;

    @c("aboutMe")
    @ax.Z8.a
    public String P;

    @c("birthday")
    @ax.Z8.a
    public Calendar Q;

    @c("hireDate")
    @ax.Z8.a
    public Calendar R;

    @c("interests")
    @ax.Z8.a
    public List<String> S;

    @c("mySite")
    @ax.Z8.a
    public String T;

    @c("pastProjects")
    @ax.Z8.a
    public List<String> U;

    @c("preferredName")
    @ax.Z8.a
    public String V;

    @c("responsibilities")
    @ax.Z8.a
    public List<String> W;

    @c("schools")
    @ax.Z8.a
    public List<String> X;

    @c("skills")
    @ax.Z8.a
    public List<String> Y;
    public transient DirectoryObjectCollectionPage Z;
    public transient DirectoryObjectCollectionPage a0;

    @c("manager")
    @ax.Z8.a
    public DirectoryObject b0;
    public transient DirectoryObjectCollectionPage c0;
    public transient DirectoryObjectCollectionPage d0;
    public transient DirectoryObjectCollectionPage e0;
    public transient DirectoryObjectCollectionPage f0;
    public transient LicenseDetailsCollectionPage g0;
    public transient ExtensionCollectionPage h0;

    @c("accountEnabled")
    @ax.Z8.a
    public Boolean i;

    @c("outlook")
    @ax.Z8.a
    public OutlookUser i0;

    @c("assignedLicenses")
    @ax.Z8.a
    public List<Object> j;
    public transient MessageCollectionPage j0;

    @c("assignedPlans")
    @ax.Z8.a
    public List<Object> k;
    public transient MailFolderCollectionPage k0;

    @c("businessPhones")
    @ax.Z8.a
    public List<String> l;

    @c("calendar")
    @ax.Z8.a
    public com.microsoft.graph.extensions.Calendar l0;

    @c("city")
    @ax.Z8.a
    public String m;
    public transient CalendarCollectionPage m0;

    @c("companyName")
    @ax.Z8.a
    public String n;
    public transient CalendarGroupCollectionPage n0;

    @c("country")
    @ax.Z8.a
    public String o;
    public transient EventCollectionPage o0;

    @c("department")
    @ax.Z8.a
    public String p;
    public transient EventCollectionPage p0;

    @c("displayName")
    @ax.Z8.a
    public String q;
    public transient PersonCollectionPage q0;

    @c("givenName")
    @ax.Z8.a
    public String r;
    public transient ContactCollectionPage r0;

    @c("imAddresses")
    @ax.Z8.a
    public List<String> s;
    public transient ContactFolderCollectionPage s0;

    @c("jobTitle")
    @ax.Z8.a
    public String t;

    @c("inferenceClassification")
    @ax.Z8.a
    public InferenceClassification t0;

    @c("mail")
    @ax.Z8.a
    public String u;

    @c("photo")
    @ax.Z8.a
    public ProfilePhoto u0;

    @c("mailNickname")
    @ax.Z8.a
    public String v;
    public transient ProfilePhotoCollectionPage v0;

    @c("mobilePhone")
    @ax.Z8.a
    public String w;

    @c("drive")
    @ax.Z8.a
    public Drive w0;

    @c("onPremisesImmutableId")
    @ax.Z8.a
    public String x;
    public transient DriveCollectionPage x0;

    @c("onPremisesLastSyncDateTime")
    @ax.Z8.a
    public Calendar y;

    @c("planner")
    @ax.Z8.a
    public PlannerUser y0;

    @c("onPremisesSecurityIdentifier")
    @ax.Z8.a
    public String z;

    @c("onenote")
    @ax.Z8.a
    public Onenote z0;

    @Override // com.microsoft.graph.generated.BaseDirectoryObject, com.microsoft.graph.generated.BaseEntity, ax.Aa.d
    public void c(e eVar, l lVar) {
        this.C0 = eVar;
        this.B0 = lVar;
        if (lVar.w("ownedDevices")) {
            BaseDirectoryObjectCollectionResponse baseDirectoryObjectCollectionResponse = new BaseDirectoryObjectCollectionResponse();
            if (lVar.w("ownedDevices@odata.nextLink")) {
                baseDirectoryObjectCollectionResponse.b = lVar.t("ownedDevices@odata.nextLink").j();
            }
            l[] lVarArr = (l[]) eVar.b(lVar.t("ownedDevices").toString(), l[].class);
            DirectoryObject[] directoryObjectArr = new DirectoryObject[lVarArr.length];
            for (int i = 0; i < lVarArr.length; i++) {
                DirectoryObject directoryObject = (DirectoryObject) eVar.b(lVarArr[i].toString(), DirectoryObject.class);
                directoryObjectArr[i] = directoryObject;
                directoryObject.c(eVar, lVarArr[i]);
            }
            baseDirectoryObjectCollectionResponse.a = Arrays.asList(directoryObjectArr);
            this.Z = new DirectoryObjectCollectionPage(baseDirectoryObjectCollectionResponse, null);
        }
        if (lVar.w("registeredDevices")) {
            BaseDirectoryObjectCollectionResponse baseDirectoryObjectCollectionResponse2 = new BaseDirectoryObjectCollectionResponse();
            if (lVar.w("registeredDevices@odata.nextLink")) {
                baseDirectoryObjectCollectionResponse2.b = lVar.t("registeredDevices@odata.nextLink").j();
            }
            l[] lVarArr2 = (l[]) eVar.b(lVar.t("registeredDevices").toString(), l[].class);
            DirectoryObject[] directoryObjectArr2 = new DirectoryObject[lVarArr2.length];
            for (int i2 = 0; i2 < lVarArr2.length; i2++) {
                DirectoryObject directoryObject2 = (DirectoryObject) eVar.b(lVarArr2[i2].toString(), DirectoryObject.class);
                directoryObjectArr2[i2] = directoryObject2;
                directoryObject2.c(eVar, lVarArr2[i2]);
            }
            baseDirectoryObjectCollectionResponse2.a = Arrays.asList(directoryObjectArr2);
            this.a0 = new DirectoryObjectCollectionPage(baseDirectoryObjectCollectionResponse2, null);
        }
        if (lVar.w("directReports")) {
            BaseDirectoryObjectCollectionResponse baseDirectoryObjectCollectionResponse3 = new BaseDirectoryObjectCollectionResponse();
            if (lVar.w("directReports@odata.nextLink")) {
                baseDirectoryObjectCollectionResponse3.b = lVar.t("directReports@odata.nextLink").j();
            }
            l[] lVarArr3 = (l[]) eVar.b(lVar.t("directReports").toString(), l[].class);
            DirectoryObject[] directoryObjectArr3 = new DirectoryObject[lVarArr3.length];
            for (int i3 = 0; i3 < lVarArr3.length; i3++) {
                DirectoryObject directoryObject3 = (DirectoryObject) eVar.b(lVarArr3[i3].toString(), DirectoryObject.class);
                directoryObjectArr3[i3] = directoryObject3;
                directoryObject3.c(eVar, lVarArr3[i3]);
            }
            baseDirectoryObjectCollectionResponse3.a = Arrays.asList(directoryObjectArr3);
            this.c0 = new DirectoryObjectCollectionPage(baseDirectoryObjectCollectionResponse3, null);
        }
        if (lVar.w("memberOf")) {
            BaseDirectoryObjectCollectionResponse baseDirectoryObjectCollectionResponse4 = new BaseDirectoryObjectCollectionResponse();
            if (lVar.w("memberOf@odata.nextLink")) {
                baseDirectoryObjectCollectionResponse4.b = lVar.t("memberOf@odata.nextLink").j();
            }
            l[] lVarArr4 = (l[]) eVar.b(lVar.t("memberOf").toString(), l[].class);
            DirectoryObject[] directoryObjectArr4 = new DirectoryObject[lVarArr4.length];
            for (int i4 = 0; i4 < lVarArr4.length; i4++) {
                DirectoryObject directoryObject4 = (DirectoryObject) eVar.b(lVarArr4[i4].toString(), DirectoryObject.class);
                directoryObjectArr4[i4] = directoryObject4;
                directoryObject4.c(eVar, lVarArr4[i4]);
            }
            baseDirectoryObjectCollectionResponse4.a = Arrays.asList(directoryObjectArr4);
            this.d0 = new DirectoryObjectCollectionPage(baseDirectoryObjectCollectionResponse4, null);
        }
        if (lVar.w("createdObjects")) {
            BaseDirectoryObjectCollectionResponse baseDirectoryObjectCollectionResponse5 = new BaseDirectoryObjectCollectionResponse();
            if (lVar.w("createdObjects@odata.nextLink")) {
                baseDirectoryObjectCollectionResponse5.b = lVar.t("createdObjects@odata.nextLink").j();
            }
            l[] lVarArr5 = (l[]) eVar.b(lVar.t("createdObjects").toString(), l[].class);
            DirectoryObject[] directoryObjectArr5 = new DirectoryObject[lVarArr5.length];
            for (int i5 = 0; i5 < lVarArr5.length; i5++) {
                DirectoryObject directoryObject5 = (DirectoryObject) eVar.b(lVarArr5[i5].toString(), DirectoryObject.class);
                directoryObjectArr5[i5] = directoryObject5;
                directoryObject5.c(eVar, lVarArr5[i5]);
            }
            baseDirectoryObjectCollectionResponse5.a = Arrays.asList(directoryObjectArr5);
            this.e0 = new DirectoryObjectCollectionPage(baseDirectoryObjectCollectionResponse5, null);
        }
        if (lVar.w("ownedObjects")) {
            BaseDirectoryObjectCollectionResponse baseDirectoryObjectCollectionResponse6 = new BaseDirectoryObjectCollectionResponse();
            if (lVar.w("ownedObjects@odata.nextLink")) {
                baseDirectoryObjectCollectionResponse6.b = lVar.t("ownedObjects@odata.nextLink").j();
            }
            l[] lVarArr6 = (l[]) eVar.b(lVar.t("ownedObjects").toString(), l[].class);
            DirectoryObject[] directoryObjectArr6 = new DirectoryObject[lVarArr6.length];
            for (int i6 = 0; i6 < lVarArr6.length; i6++) {
                DirectoryObject directoryObject6 = (DirectoryObject) eVar.b(lVarArr6[i6].toString(), DirectoryObject.class);
                directoryObjectArr6[i6] = directoryObject6;
                directoryObject6.c(eVar, lVarArr6[i6]);
            }
            baseDirectoryObjectCollectionResponse6.a = Arrays.asList(directoryObjectArr6);
            this.f0 = new DirectoryObjectCollectionPage(baseDirectoryObjectCollectionResponse6, null);
        }
        if (lVar.w("licenseDetails")) {
            BaseLicenseDetailsCollectionResponse baseLicenseDetailsCollectionResponse = new BaseLicenseDetailsCollectionResponse();
            if (lVar.w("licenseDetails@odata.nextLink")) {
                baseLicenseDetailsCollectionResponse.b = lVar.t("licenseDetails@odata.nextLink").j();
            }
            l[] lVarArr7 = (l[]) eVar.b(lVar.t("licenseDetails").toString(), l[].class);
            LicenseDetails[] licenseDetailsArr = new LicenseDetails[lVarArr7.length];
            for (int i7 = 0; i7 < lVarArr7.length; i7++) {
                LicenseDetails licenseDetails = (LicenseDetails) eVar.b(lVarArr7[i7].toString(), LicenseDetails.class);
                licenseDetailsArr[i7] = licenseDetails;
                licenseDetails.c(eVar, lVarArr7[i7]);
            }
            baseLicenseDetailsCollectionResponse.a = Arrays.asList(licenseDetailsArr);
            this.g0 = new LicenseDetailsCollectionPage(baseLicenseDetailsCollectionResponse, null);
        }
        if (lVar.w("extensions")) {
            BaseExtensionCollectionResponse baseExtensionCollectionResponse = new BaseExtensionCollectionResponse();
            if (lVar.w("extensions@odata.nextLink")) {
                baseExtensionCollectionResponse.b = lVar.t("extensions@odata.nextLink").j();
            }
            l[] lVarArr8 = (l[]) eVar.b(lVar.t("extensions").toString(), l[].class);
            Extension[] extensionArr = new Extension[lVarArr8.length];
            for (int i8 = 0; i8 < lVarArr8.length; i8++) {
                Extension extension = (Extension) eVar.b(lVarArr8[i8].toString(), Extension.class);
                extensionArr[i8] = extension;
                extension.c(eVar, lVarArr8[i8]);
            }
            baseExtensionCollectionResponse.a = Arrays.asList(extensionArr);
            this.h0 = new ExtensionCollectionPage(baseExtensionCollectionResponse, null);
        }
        if (lVar.w("messages")) {
            BaseMessageCollectionResponse baseMessageCollectionResponse = new BaseMessageCollectionResponse();
            if (lVar.w("messages@odata.nextLink")) {
                baseMessageCollectionResponse.b = lVar.t("messages@odata.nextLink").j();
            }
            l[] lVarArr9 = (l[]) eVar.b(lVar.t("messages").toString(), l[].class);
            Message[] messageArr = new Message[lVarArr9.length];
            for (int i9 = 0; i9 < lVarArr9.length; i9++) {
                Message message = (Message) eVar.b(lVarArr9[i9].toString(), Message.class);
                messageArr[i9] = message;
                message.c(eVar, lVarArr9[i9]);
            }
            baseMessageCollectionResponse.a = Arrays.asList(messageArr);
            this.j0 = new MessageCollectionPage(baseMessageCollectionResponse, null);
        }
        if (lVar.w("mailFolders")) {
            BaseMailFolderCollectionResponse baseMailFolderCollectionResponse = new BaseMailFolderCollectionResponse();
            if (lVar.w("mailFolders@odata.nextLink")) {
                baseMailFolderCollectionResponse.b = lVar.t("mailFolders@odata.nextLink").j();
            }
            l[] lVarArr10 = (l[]) eVar.b(lVar.t("mailFolders").toString(), l[].class);
            MailFolder[] mailFolderArr = new MailFolder[lVarArr10.length];
            for (int i10 = 0; i10 < lVarArr10.length; i10++) {
                MailFolder mailFolder = (MailFolder) eVar.b(lVarArr10[i10].toString(), MailFolder.class);
                mailFolderArr[i10] = mailFolder;
                mailFolder.c(eVar, lVarArr10[i10]);
            }
            baseMailFolderCollectionResponse.a = Arrays.asList(mailFolderArr);
            this.k0 = new MailFolderCollectionPage(baseMailFolderCollectionResponse, null);
        }
        if (lVar.w("calendars")) {
            BaseCalendarCollectionResponse baseCalendarCollectionResponse = new BaseCalendarCollectionResponse();
            if (lVar.w("calendars@odata.nextLink")) {
                baseCalendarCollectionResponse.b = lVar.t("calendars@odata.nextLink").j();
            }
            l[] lVarArr11 = (l[]) eVar.b(lVar.t("calendars").toString(), l[].class);
            com.microsoft.graph.extensions.Calendar[] calendarArr = new com.microsoft.graph.extensions.Calendar[lVarArr11.length];
            for (int i11 = 0; i11 < lVarArr11.length; i11++) {
                com.microsoft.graph.extensions.Calendar calendar = (com.microsoft.graph.extensions.Calendar) eVar.b(lVarArr11[i11].toString(), com.microsoft.graph.extensions.Calendar.class);
                calendarArr[i11] = calendar;
                calendar.c(eVar, lVarArr11[i11]);
            }
            baseCalendarCollectionResponse.a = Arrays.asList(calendarArr);
            this.m0 = new CalendarCollectionPage(baseCalendarCollectionResponse, null);
        }
        if (lVar.w("calendarGroups")) {
            BaseCalendarGroupCollectionResponse baseCalendarGroupCollectionResponse = new BaseCalendarGroupCollectionResponse();
            if (lVar.w("calendarGroups@odata.nextLink")) {
                baseCalendarGroupCollectionResponse.b = lVar.t("calendarGroups@odata.nextLink").j();
            }
            l[] lVarArr12 = (l[]) eVar.b(lVar.t("calendarGroups").toString(), l[].class);
            CalendarGroup[] calendarGroupArr = new CalendarGroup[lVarArr12.length];
            for (int i12 = 0; i12 < lVarArr12.length; i12++) {
                CalendarGroup calendarGroup = (CalendarGroup) eVar.b(lVarArr12[i12].toString(), CalendarGroup.class);
                calendarGroupArr[i12] = calendarGroup;
                calendarGroup.c(eVar, lVarArr12[i12]);
            }
            baseCalendarGroupCollectionResponse.a = Arrays.asList(calendarGroupArr);
            this.n0 = new CalendarGroupCollectionPage(baseCalendarGroupCollectionResponse, null);
        }
        if (lVar.w("calendarView")) {
            BaseEventCollectionResponse baseEventCollectionResponse = new BaseEventCollectionResponse();
            if (lVar.w("calendarView@odata.nextLink")) {
                baseEventCollectionResponse.b = lVar.t("calendarView@odata.nextLink").j();
            }
            l[] lVarArr13 = (l[]) eVar.b(lVar.t("calendarView").toString(), l[].class);
            Event[] eventArr = new Event[lVarArr13.length];
            for (int i13 = 0; i13 < lVarArr13.length; i13++) {
                Event event = (Event) eVar.b(lVarArr13[i13].toString(), Event.class);
                eventArr[i13] = event;
                event.c(eVar, lVarArr13[i13]);
            }
            baseEventCollectionResponse.a = Arrays.asList(eventArr);
            this.o0 = new EventCollectionPage(baseEventCollectionResponse, null);
        }
        if (lVar.w("events")) {
            BaseEventCollectionResponse baseEventCollectionResponse2 = new BaseEventCollectionResponse();
            if (lVar.w("events@odata.nextLink")) {
                baseEventCollectionResponse2.b = lVar.t("events@odata.nextLink").j();
            }
            l[] lVarArr14 = (l[]) eVar.b(lVar.t("events").toString(), l[].class);
            Event[] eventArr2 = new Event[lVarArr14.length];
            for (int i14 = 0; i14 < lVarArr14.length; i14++) {
                Event event2 = (Event) eVar.b(lVarArr14[i14].toString(), Event.class);
                eventArr2[i14] = event2;
                event2.c(eVar, lVarArr14[i14]);
            }
            baseEventCollectionResponse2.a = Arrays.asList(eventArr2);
            this.p0 = new EventCollectionPage(baseEventCollectionResponse2, null);
        }
        if (lVar.w("people")) {
            BasePersonCollectionResponse basePersonCollectionResponse = new BasePersonCollectionResponse();
            if (lVar.w("people@odata.nextLink")) {
                basePersonCollectionResponse.b = lVar.t("people@odata.nextLink").j();
            }
            l[] lVarArr15 = (l[]) eVar.b(lVar.t("people").toString(), l[].class);
            Person[] personArr = new Person[lVarArr15.length];
            for (int i15 = 0; i15 < lVarArr15.length; i15++) {
                Person person = (Person) eVar.b(lVarArr15[i15].toString(), Person.class);
                personArr[i15] = person;
                person.c(eVar, lVarArr15[i15]);
            }
            basePersonCollectionResponse.a = Arrays.asList(personArr);
            this.q0 = new PersonCollectionPage(basePersonCollectionResponse, null);
        }
        if (lVar.w("contacts")) {
            BaseContactCollectionResponse baseContactCollectionResponse = new BaseContactCollectionResponse();
            if (lVar.w("contacts@odata.nextLink")) {
                baseContactCollectionResponse.b = lVar.t("contacts@odata.nextLink").j();
            }
            l[] lVarArr16 = (l[]) eVar.b(lVar.t("contacts").toString(), l[].class);
            Contact[] contactArr = new Contact[lVarArr16.length];
            for (int i16 = 0; i16 < lVarArr16.length; i16++) {
                Contact contact = (Contact) eVar.b(lVarArr16[i16].toString(), Contact.class);
                contactArr[i16] = contact;
                contact.c(eVar, lVarArr16[i16]);
            }
            baseContactCollectionResponse.a = Arrays.asList(contactArr);
            this.r0 = new ContactCollectionPage(baseContactCollectionResponse, null);
        }
        if (lVar.w("contactFolders")) {
            BaseContactFolderCollectionResponse baseContactFolderCollectionResponse = new BaseContactFolderCollectionResponse();
            if (lVar.w("contactFolders@odata.nextLink")) {
                baseContactFolderCollectionResponse.b = lVar.t("contactFolders@odata.nextLink").j();
            }
            l[] lVarArr17 = (l[]) eVar.b(lVar.t("contactFolders").toString(), l[].class);
            ContactFolder[] contactFolderArr = new ContactFolder[lVarArr17.length];
            for (int i17 = 0; i17 < lVarArr17.length; i17++) {
                ContactFolder contactFolder = (ContactFolder) eVar.b(lVarArr17[i17].toString(), ContactFolder.class);
                contactFolderArr[i17] = contactFolder;
                contactFolder.c(eVar, lVarArr17[i17]);
            }
            baseContactFolderCollectionResponse.a = Arrays.asList(contactFolderArr);
            this.s0 = new ContactFolderCollectionPage(baseContactFolderCollectionResponse, null);
        }
        if (lVar.w("photos")) {
            BaseProfilePhotoCollectionResponse baseProfilePhotoCollectionResponse = new BaseProfilePhotoCollectionResponse();
            if (lVar.w("photos@odata.nextLink")) {
                baseProfilePhotoCollectionResponse.b = lVar.t("photos@odata.nextLink").j();
            }
            l[] lVarArr18 = (l[]) eVar.b(lVar.t("photos").toString(), l[].class);
            ProfilePhoto[] profilePhotoArr = new ProfilePhoto[lVarArr18.length];
            for (int i18 = 0; i18 < lVarArr18.length; i18++) {
                ProfilePhoto profilePhoto = (ProfilePhoto) eVar.b(lVarArr18[i18].toString(), ProfilePhoto.class);
                profilePhotoArr[i18] = profilePhoto;
                profilePhoto.c(eVar, lVarArr18[i18]);
            }
            baseProfilePhotoCollectionResponse.a = Arrays.asList(profilePhotoArr);
            this.v0 = new ProfilePhotoCollectionPage(baseProfilePhotoCollectionResponse, null);
        }
        if (lVar.w("drives")) {
            BaseDriveCollectionResponse baseDriveCollectionResponse = new BaseDriveCollectionResponse();
            if (lVar.w("drives@odata.nextLink")) {
                baseDriveCollectionResponse.b = lVar.t("drives@odata.nextLink").j();
            }
            l[] lVarArr19 = (l[]) eVar.b(lVar.t("drives").toString(), l[].class);
            Drive[] driveArr = new Drive[lVarArr19.length];
            for (int i19 = 0; i19 < lVarArr19.length; i19++) {
                Drive drive = (Drive) eVar.b(lVarArr19[i19].toString(), Drive.class);
                driveArr[i19] = drive;
                drive.c(eVar, lVarArr19[i19]);
            }
            baseDriveCollectionResponse.a = Arrays.asList(driveArr);
            this.x0 = new DriveCollectionPage(baseDriveCollectionResponse, null);
        }
        if (lVar.w("activities")) {
            BaseUserActivityCollectionResponse baseUserActivityCollectionResponse = new BaseUserActivityCollectionResponse();
            if (lVar.w("activities@odata.nextLink")) {
                baseUserActivityCollectionResponse.b = lVar.t("activities@odata.nextLink").j();
            }
            l[] lVarArr20 = (l[]) eVar.b(lVar.t("activities").toString(), l[].class);
            UserActivity[] userActivityArr = new UserActivity[lVarArr20.length];
            for (int i20 = 0; i20 < lVarArr20.length; i20++) {
                UserActivity userActivity = (UserActivity) eVar.b(lVarArr20[i20].toString(), UserActivity.class);
                userActivityArr[i20] = userActivity;
                userActivity.c(eVar, lVarArr20[i20]);
            }
            baseUserActivityCollectionResponse.a = Arrays.asList(userActivityArr);
            this.A0 = new UserActivityCollectionPage(baseUserActivityCollectionResponse, null);
        }
    }
}
