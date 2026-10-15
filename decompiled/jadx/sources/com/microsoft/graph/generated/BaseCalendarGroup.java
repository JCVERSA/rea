package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import com.microsoft.graph.extensions.Calendar;
import com.microsoft.graph.extensions.CalendarCollectionPage;
import com.microsoft.graph.extensions.Entity;
import java.util.Arrays;
import java.util.UUID;

/* JADX INFO: loaded from: classes3.dex */
public class BaseCalendarGroup extends Entity implements d {

    @c("name")
    @ax.Z8.a
    public String f;

    @c("classId")
    @ax.Z8.a
    public UUID g;

    @c("changeKey")
    @ax.Z8.a
    public String h;
    public transient CalendarCollectionPage i;
    private transient l j;
    private transient e k;

    @Override // com.microsoft.graph.generated.BaseEntity, ax.Aa.d
    public void c(e eVar, l lVar) {
        this.k = eVar;
        this.j = lVar;
        if (lVar.w("calendars")) {
            BaseCalendarCollectionResponse baseCalendarCollectionResponse = new BaseCalendarCollectionResponse();
            if (lVar.w("calendars@odata.nextLink")) {
                baseCalendarCollectionResponse.b = lVar.t("calendars@odata.nextLink").j();
            }
            l[] lVarArr = (l[]) eVar.b(lVar.t("calendars").toString(), l[].class);
            Calendar[] calendarArr = new Calendar[lVarArr.length];
            for (int i = 0; i < lVarArr.length; i++) {
                Calendar calendar = (Calendar) eVar.b(lVarArr[i].toString(), Calendar.class);
                calendarArr[i] = calendar;
                calendar.c(eVar, lVarArr[i]);
            }
            baseCalendarCollectionResponse.a = Arrays.asList(calendarArr);
            this.i = new CalendarCollectionPage(baseCalendarCollectionResponse, null);
        }
    }
}
