package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import ax.p333ua.O0;
import ax.p333ua.S0;
import ax.p333ua.Z0;
import com.microsoft.graph.extensions.Recipient;
import com.microsoft.graph.extensions.SizeRange;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class BaseMessageRulePredicates implements d {

    @c("isNonDeliveryReport")
    @ax.Z8.a
    public Boolean A;

    @c("isPermissionControlled")
    @ax.Z8.a
    public Boolean B;

    @c("isReadReceipt")
    @ax.Z8.a
    public Boolean C;

    @c("isSigned")
    @ax.Z8.a
    public Boolean D;

    @c("isVoicemail")
    @ax.Z8.a
    public Boolean E;

    @c("withinSizeRange")
    @ax.Z8.a
    public SizeRange F;
    private transient l G;
    private transient e H;

    @c("@odata.type")
    @ax.Z8.a
    public String a;
    private transient com.microsoft.graph.serializer.a b;

    @c("categories")
    @ax.Z8.a
    public List<String> c;

    @c("subjectContains")
    @ax.Z8.a
    public List<String> d;

    @c("bodyContains")
    @ax.Z8.a
    public List<String> e;

    @c("bodyOrSubjectContains")
    @ax.Z8.a
    public List<String> f;

    @c("senderContains")
    @ax.Z8.a
    public List<String> g;

    @c("recipientContains")
    @ax.Z8.a
    public List<String> h;

    @c("headerContains")
    @ax.Z8.a
    public List<String> i;

    @c("messageActionFlag")
    @ax.Z8.a
    public S0 j;

    @c("importance")
    @ax.Z8.a
    public O0 k;

    @c("sensitivity")
    @ax.Z8.a
    public Z0 l;

    @c("fromAddresses")
    @ax.Z8.a
    public List<Recipient> m;

    @c("sentToAddresses")
    @ax.Z8.a
    public List<Recipient> n;

    @c("sentToMe")
    @ax.Z8.a
    public Boolean o;

    @c("sentOnlyToMe")
    @ax.Z8.a
    public Boolean p;

    @c("sentCcMe")
    @ax.Z8.a
    public Boolean q;

    @c("sentToOrCcMe")
    @ax.Z8.a
    public Boolean r;

    @c("notSentToMe")
    @ax.Z8.a
    public Boolean s;

    @c("hasAttachments")
    @ax.Z8.a
    public Boolean t;

    @c("isApprovalRequest")
    @ax.Z8.a
    public Boolean u;

    @c("isAutomaticForward")
    @ax.Z8.a
    public Boolean v;

    @c("isAutomaticReply")
    @ax.Z8.a
    public Boolean w;

    @c("isEncrypted")
    @ax.Z8.a
    public Boolean x;

    @c("isMeetingRequest")
    @ax.Z8.a
    public Boolean y;

    @c("isMeetingResponse")
    @ax.Z8.a
    public Boolean z;

    @Override // ax.Aa.d
    public void c(e eVar, l lVar) {
        this.H = eVar;
        this.G = lVar;
    }

    @Override // ax.Aa.d
    public final com.microsoft.graph.serializer.a d() {
        return this.b;
    }
}
