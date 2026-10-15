package com.pcloud.sdk.internal.networking.serialization;

import ax.Sa.x;
import ax.Y8.g;
import ax.Y8.h;
import ax.Y8.i;
import ax.Y8.m;
import ax.p335uc.l;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes3.dex */
public final class ResolutionDeserializer implements h<x> {
    public static final ResolutionDeserializer a = new ResolutionDeserializer();

    private ResolutionDeserializer() {
    }

    @Override // ax.Y8.h
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public x a(i iVar, Type type, g gVar) {
        l.f(iVar, "json");
        l.f(type, "typeOfT");
        l.f(gVar, "context");
        if (!iVar.p() || !iVar.i().y()) {
            throw new m("Invalid size value " + iVar + ".");
        }
        String strJ = iVar.j();
        l.c(strJ);
        int iU = ax.Cc.h.U(strJ, 'x', 0, false, 6, null);
        if (iU == -1) {
            throw new m("Invalid resolution format: " + strJ);
        }
        try {
            String strSubstring = strJ.substring(0, iU);
            l.e(strSubstring, "substring(...)");
            int i = Integer.parseInt(strSubstring);
            String strSubstring2 = strJ.substring(iU + 1);
            l.e(strSubstring2, "substring(...)");
            return new x(i, Integer.parseInt(strSubstring2));
        } catch (NumberFormatException e) {
            throw new m(e);
        }
    }
}
