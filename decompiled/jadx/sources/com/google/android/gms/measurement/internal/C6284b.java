package com.google.android.gms.measurement.internal;

import ax.H6.C0893b2;
import ax.H6.C0911d2;
import ax.H6.H6;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
final class C6284b extends M5 {
    private ax.H6.D1 g;
    private final /* synthetic */ J5 h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    C6284b(J5 j5, String str, int i, ax.H6.D1 d1) {
        super(str, i);
        this.h = j5;
        this.g = d1;
    }

    @Override // com.google.android.gms.measurement.internal.M5
    final int a() {
        return this.g.O();
    }

    @Override // com.google.android.gms.measurement.internal.M5
    final boolean i() {
        return this.g.Y();
    }

    @Override // com.google.android.gms.measurement.internal.M5
    final boolean j() {
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:104:0x02b0  */
    /* JADX WARN: Code duplicated, block: B:107:0x02c2  */
    /* JADX WARN: Code duplicated, block: B:112:0x02d0  */
    /* JADX WARN: Code duplicated, block: B:114:0x02d6  */
    /* JADX WARN: Code duplicated, block: B:115:0x02e7  */
    /* JADX WARN: Code duplicated, block: B:117:0x02ed  */
    /* JADX WARN: Code duplicated, block: B:119:0x02f5  */
    /* JADX WARN: Code duplicated, block: B:122:0x0301  */
    /* JADX WARN: Code duplicated, block: B:128:0x0355 A[EDGE_INSN: B:128:0x0355->B:131:0x03a1 BREAK  A[LOOP:0: B:47:0x0117->B:52:0x0148]] */
    /* JADX WARN: Code duplicated, block: B:129:0x037b A[EDGE_INSN: B:129:0x037b->B:131:0x03a1 BREAK  A[LOOP:0: B:47:0x0117->B:52:0x0148]] */
    /* JADX WARN: Code duplicated, block: B:164:0x0150 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:165:0x012d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:166:0x01ea A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:167:0x0173 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:168:0x0191 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:169:0x01af A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:170:0x0179 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:171:0x0197 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:172:0x01c1 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:174:0x015d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:178:0x027d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:179:0x039f A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:180:0x0217 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:181:0x0281 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:182:0x0240 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:183:0x03a1 A[EDGE_INSN: B:183:0x03a1->B:131:0x03a1 BREAK  A[LOOP:0: B:47:0x0117->B:52:0x0148], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:184:0x02cc A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:185:0x028b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:186:0x03a1 A[EDGE_INSN: B:186:0x03a1->B:131:0x03a1 BREAK  A[LOOP:0: B:47:0x0117->B:52:0x0148], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:187:0x02c8 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:188:0x023a A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:189:0x0285 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:190:0x0353 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:191:0x032f A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:192:0x030b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:193:0x03a1 A[EDGE_INSN: B:193:0x03a1->B:131:0x03a1 BREAK  A[LOOP:0: B:47:0x0117->B:52:0x0148], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:194:0x0307 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:196:0x01f2 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:197:0x01f2 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:198:0x01f2 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:46:0x010a  */
    /* JADX WARN: Code duplicated, block: B:49:0x011d  */
    /* JADX WARN: Code duplicated, block: B:52:0x0148 A[LOOP:0: B:47:0x0117->B:52:0x0148, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:56:0x0163  */
    /* JADX WARN: Code duplicated, block: B:62:0x0183  */
    /* JADX WARN: Code duplicated, block: B:63:0x018c  */
    /* JADX WARN: Code duplicated, block: B:69:0x01a1  */
    /* JADX WARN: Code duplicated, block: B:70:0x01aa  */
    /* JADX WARN: Code duplicated, block: B:74:0x01b5  */
    /* JADX WARN: Code duplicated, block: B:79:0x01f8  */
    /* JADX WARN: Code duplicated, block: B:84:0x020c  */
    /* JADX WARN: Code duplicated, block: B:88:0x0232  */
    /* JADX WARN: Code duplicated, block: B:93:0x0265  */
    /* JADX WARN: Code duplicated, block: B:96:0x0277  */
    final boolean k(Long l, Long l2, C0893b2 c0893b2, long j, C6433y c6433y, boolean z) {
        HashSet hashSet;
        Iterator<ax.H6.E1> it;
        ax.B.a aVar;
        Iterator<C0911d2> it2;
        Iterator<ax.H6.E1> it3;
        ax.H6.E1 next;
        boolean z2;
        String strP;
        Object obj;
        Boolean boolC;
        Boolean boolB;
        String str;
        Boolean boolE;
        C0911d2 next2;
        Long lValueOf;
        Double dValueOf;
        ax.H6.E1 next3;
        boolean z3 = H6.a() && this.h.d().G(this.a, F.l0);
        long j2 = this.g.X() ? c6433y.e : j;
        bool = null;
        bool = null;
        bool = null;
        bool = null;
        bool = null;
        bool = null;
        bool = null;
        bool = null;
        bool = null;
        bool = null;
        bool = null;
        Boolean bool = null;
        if (this.h.j().C(2)) {
            this.h.j().K().d("Evaluating filter. audience, filter, event", Integer.valueOf(this.b), this.g.Z() ? Integer.valueOf(this.g.O()) : null, this.h.g().c(this.g.T()));
            this.h.j().K().b("Filter definition", this.h.o().K(this.g));
        }
        if (!this.g.Z() || this.g.O() > 256) {
            this.h.j().L().c("Invalid event filter ID. appId, id", V1.v(this.a), String.valueOf(this.g.Z() ? Integer.valueOf(this.g.O()) : null));
            return false;
        }
        boolean z4 = this.g.V() || this.g.W() || this.g.X();
        if (z && !z4) {
            this.h.j().K().c("Event filter already evaluated true and it is not associated with an enhanced audience. audience ID, filter ID", Integer.valueOf(this.b), this.g.Z() ? Integer.valueOf(this.g.O()) : null);
            return true;
        }
        ax.H6.D1 d1 = this.g;
        String strF0 = c0893b2.f0();
        if (!d1.Y()) {
            hashSet = new HashSet();
            it = d1.U().iterator();
            while (true) {
                if (it.hasNext()) {
                    aVar = new ax.B.a();
                    it2 = c0893b2.g0().iterator();
                    while (true) {
                        if (it2.hasNext()) {
                            it3 = d1.U().iterator();
                            while (true) {
                                if (it3.hasNext()) {
                                    bool = Boolean.TRUE;
                                    break;
                                }
                                next = it3.next();
                                if (next.S() || !next.Q()) {
                                    z2 = false;
                                } else {
                                    z2 = true;
                                }
                                strP = next.P();
                                if (strP.isEmpty()) {
                                    obj = aVar.get(strP);
                                    if (obj instanceof Long) {
                                        if (obj instanceof Double) {
                                            if (obj instanceof String) {
                                                if (obj == null) {
                                                    this.h.j().L().c("Unknown param type. event, param", this.h.g().c(strF0), this.h.g().f(strP));
                                                    break;
                                                }
                                                this.h.j().K().c("Missing param for filter. event, param", this.h.g().c(strF0), this.h.g().f(strP));
                                                bool = Boolean.FALSE;
                                                break;
                                            }
                                            if (next.V()) {
                                                if (next.T()) {
                                                    this.h.j().L().c("No filter for String param. event, param", this.h.g().c(strF0), this.h.g().f(strP));
                                                    break;
                                                }
                                                str = (String) obj;
                                                if (x5.h0(str)) {
                                                    this.h.j().L().c("Invalid param value for number filter. event, param", this.h.g().c(strF0), this.h.g().f(strP));
                                                    break;
                                                }
                                                boolE = M5.e(str, next.N());
                                            } else {
                                                boolE = M5.g((String) obj, next.O(), this.h.j());
                                            }
                                            if (boolE != null) {
                                                break;
                                            }
                                            if (boolE.booleanValue() == z2) {
                                                bool = Boolean.FALSE;
                                                break;
                                            }
                                        } else {
                                            if (next.T()) {
                                                this.h.j().L().c("No number filter for double param. event, param", this.h.g().c(strF0), this.h.g().f(strP));
                                                break;
                                            }
                                            boolB = M5.b(((Double) obj).doubleValue(), next.N());
                                            if (boolB != null) {
                                                break;
                                            }
                                            if (boolB.booleanValue() == z2) {
                                                bool = Boolean.FALSE;
                                                break;
                                            }
                                        }
                                    } else {
                                        if (next.T()) {
                                            this.h.j().L().c("No number filter for long param. event, param", this.h.g().c(strF0), this.h.g().f(strP));
                                            break;
                                        }
                                        boolC = M5.c(((Long) obj).longValue(), next.N());
                                        if (boolC != null) {
                                            break;
                                        }
                                        if (boolC.booleanValue() == z2) {
                                            bool = Boolean.FALSE;
                                            break;
                                        }
                                    }
                                } else {
                                    this.h.j().L().b("Event has empty param name. event", this.h.g().c(strF0));
                                    break;
                                }
                            }
                        } else {
                            next2 = it2.next();
                            if (!hashSet.contains(next2.g0())) {
                                if (next2.l0()) {
                                    if (next2.j0()) {
                                        if (next2.n0()) {
                                            this.h.j().L().c("Unknown value for param. event, param", this.h.g().c(strF0), this.h.g().f(next2.g0()));
                                            break;
                                        }
                                        aVar.put(next2.g0(), next2.h0());
                                    } else {
                                        String strG0 = next2.g0();
                                        if (next2.j0()) {
                                            dValueOf = Double.valueOf(next2.J());
                                        } else {
                                            dValueOf = null;
                                        }
                                        aVar.put(strG0, dValueOf);
                                    }
                                } else {
                                    String strG1 = next2.g0();
                                    if (next2.l0()) {
                                        lValueOf = Long.valueOf(next2.b0());
                                    } else {
                                        lValueOf = null;
                                    }
                                    aVar.put(strG1, lValueOf);
                                }
                            }
                        }
                    }
                } else {
                    next3 = it.next();
                    if (next3.P().isEmpty()) {
                        this.h.j().L().b("null or empty param name in filter. event", this.h.g().c(strF0));
                        break;
                    }
                    hashSet.add(next3.P());
                }
            }
        } else {
            Boolean boolC2 = M5.c(j2, d1.S());
            if (boolC2 != null) {
                if (boolC2.booleanValue()) {
                    hashSet = new HashSet();
                    it = d1.U().iterator();
                    while (true) {
                        if (it.hasNext()) {
                            aVar = new ax.B.a();
                            it2 = c0893b2.g0().iterator();
                            while (true) {
                                if (it2.hasNext()) {
                                    it3 = d1.U().iterator();
                                    while (true) {
                                        if (it3.hasNext()) {
                                            bool = Boolean.TRUE;
                                            break;
                                        }
                                        next = it3.next();
                                        if (next.S()) {
                                            z2 = false;
                                        } else {
                                            z2 = false;
                                        }
                                        strP = next.P();
                                        if (strP.isEmpty()) {
                                            obj = aVar.get(strP);
                                            if (obj instanceof Long) {
                                                if (obj instanceof Double) {
                                                    if (obj instanceof String) {
                                                        if (obj == null) {
                                                            this.h.j().L().c("Unknown param type. event, param", this.h.g().c(strF0), this.h.g().f(strP));
                                                            break;
                                                        }
                                                        this.h.j().K().c("Missing param for filter. event, param", this.h.g().c(strF0), this.h.g().f(strP));
                                                        bool = Boolean.FALSE;
                                                        break;
                                                    }
                                                    if (next.V()) {
                                                        if (next.T()) {
                                                            this.h.j().L().c("No filter for String param. event, param", this.h.g().c(strF0), this.h.g().f(strP));
                                                            break;
                                                        }
                                                        str = (String) obj;
                                                        if (x5.h0(str)) {
                                                            this.h.j().L().c("Invalid param value for number filter. event, param", this.h.g().c(strF0), this.h.g().f(strP));
                                                            break;
                                                        }
                                                        boolE = M5.e(str, next.N());
                                                    } else {
                                                        boolE = M5.g((String) obj, next.O(), this.h.j());
                                                    }
                                                    if (boolE != null) {
                                                        break;
                                                        break;
                                                    }
                                                    if (boolE.booleanValue() == z2) {
                                                        bool = Boolean.FALSE;
                                                        break;
                                                    }
                                                } else if (next.T()) {
                                                    boolB = M5.b(((Double) obj).doubleValue(), next.N());
                                                    if (boolB != null) {
                                                        break;
                                                        break;
                                                    }
                                                    if (boolB.booleanValue() == z2) {
                                                        bool = Boolean.FALSE;
                                                        break;
                                                    }
                                                } else {
                                                    this.h.j().L().c("No number filter for double param. event, param", this.h.g().c(strF0), this.h.g().f(strP));
                                                    break;
                                                }
                                            } else if (next.T()) {
                                                boolC = M5.c(((Long) obj).longValue(), next.N());
                                                if (boolC != null) {
                                                    break;
                                                    break;
                                                }
                                                if (boolC.booleanValue() == z2) {
                                                    bool = Boolean.FALSE;
                                                    break;
                                                }
                                            } else {
                                                this.h.j().L().c("No number filter for long param. event, param", this.h.g().c(strF0), this.h.g().f(strP));
                                                break;
                                            }
                                        } else {
                                            this.h.j().L().b("Event has empty param name. event", this.h.g().c(strF0));
                                            break;
                                        }
                                    }
                                } else {
                                    next2 = it2.next();
                                    if (!hashSet.contains(next2.g0())) {
                                        if (next2.l0()) {
                                            if (next2.j0()) {
                                                if (next2.n0()) {
                                                    this.h.j().L().c("Unknown value for param. event, param", this.h.g().c(strF0), this.h.g().f(next2.g0()));
                                                    break;
                                                }
                                                aVar.put(next2.g0(), next2.h0());
                                            } else {
                                                String strG2 = next2.g0();
                                                if (next2.j0()) {
                                                    dValueOf = Double.valueOf(next2.J());
                                                } else {
                                                    dValueOf = null;
                                                }
                                                aVar.put(strG2, dValueOf);
                                            }
                                        } else {
                                            String strG3 = next2.g0();
                                            if (next2.l0()) {
                                                lValueOf = Long.valueOf(next2.b0());
                                            } else {
                                                lValueOf = null;
                                            }
                                            aVar.put(strG3, lValueOf);
                                        }
                                    }
                                }
                            }
                        } else {
                            next3 = it.next();
                            if (next3.P().isEmpty()) {
                                this.h.j().L().b("null or empty param name in filter. event", this.h.g().c(strF0));
                                break;
                            }
                            hashSet.add(next3.P());
                        }
                    }
                } else {
                    bool = Boolean.FALSE;
                }
            }
        }
        this.h.j().K().b("Event filter result", bool == null ? "null" : bool);
        if (bool == null) {
            return false;
        }
        Boolean bool2 = Boolean.TRUE;
        this.c = bool2;
        if (!bool.booleanValue()) {
            return true;
        }
        this.d = bool2;
        if (z4 && c0893b2.j0()) {
            Long lValueOf2 = Long.valueOf(c0893b2.c0());
            if (this.g.W()) {
                if (!z3 || !this.g.Y()) {
                    l = lValueOf2;
                }
                this.f = l;
            } else {
                if (z3 && this.g.Y()) {
                    lValueOf2 = l2;
                }
                this.e = lValueOf2;
            }
        }
        return true;
    }
}
