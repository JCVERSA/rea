package com.microsoft.graph.serializer;

import ax.Aa.c;
import ax.Y8.g;
import ax.Y8.h;
import ax.Y8.i;
import ax.Y8.m;
import ax.Y8.o;
import ax.Y8.p;
import ax.Y8.q;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.Calendar;
import java.util.EnumSet;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;

/* JADX INFO: loaded from: classes3.dex */
final class GsonFactory {
    public static Gson a(final ax.p381xa.b bVar) {
        q<Calendar> qVar = new q<Calendar>() { // from class: com.microsoft.graph.serializer.GsonFactory.1
            @Override // ax.Y8.q
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public i a(Calendar calendar, Type type, p pVar) {
                if (calendar == null) {
                    return null;
                }
                try {
                    return new o(ax.Aa.b.b(calendar));
                } catch (Exception e) {
                    bVar.b("Parsing issue on " + calendar, e);
                    return null;
                }
            }
        };
        h<Calendar> hVar = new h<Calendar>() { // from class: com.microsoft.graph.serializer.GsonFactory.2
            @Override // ax.Y8.h
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public Calendar a(i iVar, Type type, g gVar) throws m {
                if (iVar == null) {
                    return null;
                }
                try {
                    return ax.Aa.b.a(iVar.j());
                } catch (ParseException e) {
                    bVar.b("Parsing issue on " + iVar.j(), e);
                    return null;
                }
            }
        };
        q<byte[]> qVar2 = new q<byte[]>() { // from class: com.microsoft.graph.serializer.GsonFactory.3
            @Override // ax.Y8.q
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public i a(byte[] bArr, Type type, p pVar) {
                if (bArr == null) {
                    return null;
                }
                try {
                    return new o(ax.Aa.a.b(bArr));
                } catch (Exception e) {
                    bVar.b("Parsing issue on " + bArr, e);
                    return null;
                }
            }
        };
        h<byte[]> hVar2 = new h<byte[]>() { // from class: com.microsoft.graph.serializer.GsonFactory.4
            @Override // ax.Y8.h
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public byte[] a(i iVar, Type type, g gVar) throws m {
                if (iVar == null) {
                    return null;
                }
                try {
                    return ax.Aa.a.a(iVar.j());
                } catch (ParseException e) {
                    bVar.b("Parsing issue on " + iVar.j(), e);
                    return null;
                }
            }
        };
        q<ax.p397ya.a> qVar3 = new q<ax.p397ya.a>() { // from class: com.microsoft.graph.serializer.GsonFactory.5
            @Override // ax.Y8.q
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public i a(ax.p397ya.a aVar, Type type, p pVar) {
                if (aVar == null) {
                    return null;
                }
                return new o(aVar.toString());
            }
        };
        h<ax.p397ya.a> hVar3 = new h<ax.p397ya.a>() { // from class: com.microsoft.graph.serializer.GsonFactory.6
            @Override // ax.Y8.h
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public ax.p397ya.a a(i iVar, Type type, g gVar) throws m {
                if (iVar == null) {
                    return null;
                }
                try {
                    return ax.p397ya.a.a(iVar.j());
                } catch (ParseException e) {
                    bVar.b("Parsing issue on " + iVar.j(), e);
                    return null;
                }
            }
        };
        q<EnumSet> qVar4 = new q<EnumSet>() { // from class: com.microsoft.graph.serializer.GsonFactory.7
            @Override // ax.Y8.q
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public i a(EnumSet enumSet, Type type, p pVar) {
                if (enumSet == null || enumSet.size() == 0) {
                    return null;
                }
                return c.b(enumSet);
            }
        };
        h<EnumSet> hVar4 = new h<EnumSet>() { // from class: com.microsoft.graph.serializer.GsonFactory.8
            @Override // ax.Y8.h
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public EnumSet a(i iVar, Type type, g gVar) throws m {
                if (iVar == null) {
                    return null;
                }
                return c.a(type, iVar.j());
            }
        };
        q<Duration> qVar5 = new q<Duration>() { // from class: com.microsoft.graph.serializer.GsonFactory.9
            @Override // ax.Y8.q
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public i a(Duration duration, Type type, p pVar) {
                return new o(duration.toString());
            }
        };
        return new com.google.gson.a().c().d(Calendar.class, qVar).d(Calendar.class, hVar).d(GregorianCalendar.class, qVar).d(GregorianCalendar.class, hVar).d(byte[].class, hVar2).d(byte[].class, qVar2).d(ax.p397ya.a.class, qVar3).d(ax.p397ya.a.class, hVar3).d(EnumSet.class, qVar4).d(EnumSet.class, hVar4).d(Duration.class, qVar5).d(Duration.class, new h<Duration>() { // from class: com.microsoft.graph.serializer.GsonFactory.10
            @Override // ax.Y8.h
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public Duration a(i iVar, Type type, g gVar) throws m {
                try {
                    return DatatypeFactory.newInstance().newDuration(iVar.toString());
                } catch (Exception unused) {
                    return null;
                }
            }
        }).e(new FallBackEnumTypeAdapter()).b();
    }
}
