package com.google.android.gms.measurement.internal;

import android.content.Context;
import ax.H6.A6;
import ax.H6.AbstractC0885a3;
import ax.H6.B6;
import ax.H6.C0889a7;
import ax.H6.C0906c6;
import ax.H6.C0943g7;
import ax.H6.C0952h7;
import ax.H6.C0960i6;
import ax.H6.C0969j6;
import ax.H6.C0997m7;
import ax.H6.C1013o6;
import ax.H6.C1021p6;
import ax.H6.C1060u6;
import ax.H6.C1068v6;
import ax.H6.H6;
import ax.H6.I6;
import ax.H6.N6;
import ax.H6.O6;
import ax.H6.T6;
import ax.H6.U6;
import ax.H6.Z6;
import ax.H6.n7;
import ax.H6.s7;
import ax.H6.t7;
import ax.H6.y7;
import ax.H6.z7;
import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes3.dex */
public final class F {
    public static final M1<Long> A;
    public static final M1<Boolean> A0;
    public static final M1<Long> B;
    public static final M1<Boolean> B0;
    public static final M1<Long> C;
    public static final M1<Boolean> C0;
    public static final M1<Integer> D;
    public static final M1<Boolean> D0;
    public static final M1<Long> E;
    public static final M1<Boolean> E0;
    public static final M1<Integer> F;
    public static final M1<Boolean> F0;
    public static final M1<Integer> G;
    public static final M1<Boolean> G0;
    public static final M1<Integer> H;
    public static final M1<Boolean> H0;
    public static final M1<Integer> I;
    public static final M1<Boolean> I0;
    public static final M1<Integer> J;
    public static final M1<Boolean> J0;
    public static final M1<Long> K;
    public static final M1<Boolean> K0;
    public static final M1<Boolean> L;
    public static final M1<Boolean> L0;
    public static final M1<String> M;
    public static final M1<Boolean> M0;
    public static final M1<Long> N;
    public static final M1<Boolean> N0;
    public static final M1<Integer> O;
    public static final M1<Boolean> O0;
    public static final M1<Double> P;
    public static final M1<Boolean> P0;
    public static final M1<Integer> Q;
    public static final M1<Boolean> Q0;
    public static final M1<Integer> R;
    public static final M1<Boolean> R0;
    public static final M1<Integer> S;
    public static final M1<Boolean> S0;
    public static final M1<Integer> T;
    public static final M1<Boolean> T0;
    public static final M1<Long> U;
    public static final M1<Boolean> U0;
    public static final M1<Long> V;
    public static final M1<Boolean> V0;
    public static final M1<Integer> W;
    public static final M1<Boolean> W0;
    public static final M1<Integer> X;
    public static final M1<Boolean> X0;
    public static final M1<String> Y;
    public static final M1<Boolean> Y0;
    public static final M1<String> Z;
    public static final M1<Boolean> Z0;
    private static final List<M1<?>> a = DesugarCollections.synchronizedList(new ArrayList());
    public static final M1<String> a0;
    public static final M1<Long> b;
    public static final M1<Long> b0;
    public static final M1<Long> c;
    public static final M1<String> c0;
    public static final M1<Long> d;
    public static final M1<String> d0;
    public static final M1<Long> e;
    public static final M1<String> e0;
    public static final M1<String> f;
    public static final M1<String> f0;
    public static final M1<String> g;
    public static final M1<Long> g0;
    public static final M1<Integer> h;
    public static final M1<Boolean> h0;
    public static final M1<Integer> i;
    public static final M1<Boolean> i0;
    public static final M1<Integer> j;
    public static final M1<Boolean> j0;
    public static final M1<Integer> k;
    public static final M1<Boolean> k0;
    public static final M1<Integer> l;
    public static final M1<Boolean> l0;
    public static final M1<Integer> m;
    public static final M1<Boolean> m0;
    public static final M1<Integer> n;
    public static final M1<Boolean> n0;
    public static final M1<Integer> o;
    public static final M1<Boolean> o0;
    public static final M1<Integer> p;
    public static final M1<Integer> p0;
    public static final M1<Integer> q;
    public static final M1<Boolean> q0;
    public static final M1<String> r;
    public static final M1<Boolean> r0;
    public static final M1<Long> s;
    public static final M1<Boolean> s0;
    public static final M1<Long> t;
    public static final M1<Boolean> t0;
    public static final M1<Long> u;
    public static final M1<Boolean> u0;
    public static final M1<Long> v;
    public static final M1<String> v0;
    public static final M1<Long> w;
    public static final M1<Boolean> w0;
    public static final M1<Long> x;
    public static final M1<Boolean> x0;
    public static final M1<Long> y;
    public static final M1<Boolean> y0;
    public static final M1<Long> z;
    public static final M1<Boolean> z0;

    static {
        DesugarCollections.synchronizedSet(new HashSet());
        b = F("measurement.ad_id_cache_time", 10000L, new K1() { // from class: com.google.android.gms.measurement.internal.G
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Long.valueOf(C0906c6.a());
            }
        });
        c = F("measurement.app_uninstalled_additional_ad_id_cache_time", 3600000L, new K1() { // from class: com.google.android.gms.measurement.internal.S
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Long.valueOf(C0906c6.B());
            }
        });
        d = F("measurement.monitoring.sample_period_millis", 86400000L, new K1() { // from class: com.google.android.gms.measurement.internal.f0
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Long.valueOf(C0906c6.Q());
            }
        });
        e = c("measurement.config.cache_time", 86400000L, 3600000L, new K1() { // from class: com.google.android.gms.measurement.internal.r0
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Long.valueOf(C0906c6.D());
            }
        }, false);
        f = F("measurement.config.url_scheme", "https", new K1() { // from class: com.google.android.gms.measurement.internal.E0
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return C0906c6.q();
            }
        });
        g = F("measurement.config.url_authority", "app-measurement.com", new K1() { // from class: com.google.android.gms.measurement.internal.T0
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return C0906c6.p();
            }
        });
        h = F("measurement.upload.max_bundles", 100, new K1() { // from class: com.google.android.gms.measurement.internal.g1
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Integer.valueOf((int) C0906c6.d());
            }
        });
        i = F("measurement.upload.max_batch_size", 65536, new K1() { // from class: com.google.android.gms.measurement.internal.s1
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Integer.valueOf((int) C0906c6.l());
            }
        });
        j = F("measurement.upload.max_bundle_size", 65536, new K1() { // from class: com.google.android.gms.measurement.internal.F1
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Integer.valueOf((int) C0906c6.c());
            }
        });
        k = F("measurement.upload.max_events_per_bundle", 1000, new K1() { // from class: com.google.android.gms.measurement.internal.L
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Integer.valueOf((int) C0906c6.g());
            }
        });
        l = F("measurement.upload.max_events_per_day", 100000, new K1() { // from class: com.google.android.gms.measurement.internal.Q0
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Integer.valueOf((int) C0906c6.h());
            }
        });
        m = F("measurement.upload.max_error_events_per_day", 1000, new K1() { // from class: com.google.android.gms.measurement.internal.e1
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Integer.valueOf((int) C0906c6.f());
            }
        });
        n = F("measurement.upload.max_public_events_per_day", 50000, new K1() { // from class: com.google.android.gms.measurement.internal.n1
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Integer.valueOf((int) C0906c6.i());
            }
        });
        o = F("measurement.upload.max_conversions_per_day", 10000, new K1() { // from class: com.google.android.gms.measurement.internal.A1
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Integer.valueOf((int) C0906c6.e());
            }
        });
        p = F("measurement.upload.max_realtime_events_per_day", 10, new K1() { // from class: com.google.android.gms.measurement.internal.I
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Integer.valueOf((int) C0906c6.k());
            }
        });
        q = F("measurement.store.max_stored_events_per_app", 100000, new K1() { // from class: com.google.android.gms.measurement.internal.O
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Integer.valueOf((int) C0906c6.J());
            }
        });
        r = F("measurement.upload.url", "https://app-measurement.com/a", new K1() { // from class: com.google.android.gms.measurement.internal.N
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return C0906c6.z();
            }
        });
        F("measurement.sgtm.google_signal.url", "https://app-measurement.com/s", new K1() { // from class: com.google.android.gms.measurement.internal.Q
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return C0906c6.s();
            }
        });
        s = F("measurement.upload.backoff_period", 43200000L, new K1() { // from class: com.google.android.gms.measurement.internal.P
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Long.valueOf(C0906c6.Y());
            }
        });
        t = F("measurement.upload.window_interval", 3600000L, new K1() { // from class: com.google.android.gms.measurement.internal.T
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Long.valueOf(C0906c6.o());
            }
        });
        u = F("measurement.upload.interval", 3600000L, new K1() { // from class: com.google.android.gms.measurement.internal.V
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Long.valueOf(C0906c6.b());
            }
        });
        v = F("measurement.upload.realtime_upload_interval", 10000L, new K1() { // from class: com.google.android.gms.measurement.internal.U
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Long.valueOf(C0906c6.R());
            }
        });
        w = F("measurement.upload.debug_upload_interval", 1000L, new K1() { // from class: com.google.android.gms.measurement.internal.X
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Long.valueOf(C0906c6.E());
            }
        });
        x = F("measurement.upload.minimum_delay", 500L, new K1() { // from class: com.google.android.gms.measurement.internal.a0
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Long.valueOf(C0906c6.P());
            }
        });
        y = F("measurement.alarm_manager.minimum_interval", 60000L, new K1() { // from class: com.google.android.gms.measurement.internal.Y
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Long.valueOf(C0906c6.O());
            }
        });
        z = F("measurement.upload.stale_data_deletion_interval", 86400000L, new K1() { // from class: com.google.android.gms.measurement.internal.c0
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Long.valueOf(C0906c6.U());
            }
        });
        A = F("measurement.upload.refresh_blacklisted_config_interval", 604800000L, new K1() { // from class: com.google.android.gms.measurement.internal.b0
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Long.valueOf(C0906c6.S());
            }
        });
        B = F("measurement.upload.initial_upload_delay_time", 15000L, new K1() { // from class: com.google.android.gms.measurement.internal.e0
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Long.valueOf(C0906c6.Z());
            }
        });
        C = F("measurement.upload.retry_time", 1800000L, new K1() { // from class: com.google.android.gms.measurement.internal.d0
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Long.valueOf(C0906c6.n());
            }
        });
        D = F("measurement.upload.retry_count", 6, new K1() { // from class: com.google.android.gms.measurement.internal.g0
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Integer.valueOf((int) C0906c6.m());
            }
        });
        E = F("measurement.upload.max_queue_time", 2419200000L, new K1() { // from class: com.google.android.gms.measurement.internal.i0
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Long.valueOf(C0906c6.j());
            }
        });
        F = F("measurement.lifetimevalue.max_currency_tracked", 4, new K1() { // from class: com.google.android.gms.measurement.internal.h0
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Integer.valueOf((int) C0906c6.G());
            }
        });
        G = F("measurement.audience.filter_result_max_count", 200, new K1() { // from class: com.google.android.gms.measurement.internal.j0
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Integer.valueOf((int) C0906c6.L());
            }
        });
        H = a("measurement.upload.max_public_user_properties", 25);
        I = a("measurement.upload.max_event_name_cardinality", 500);
        J = a("measurement.upload.max_public_event_params", 25);
        K = F("measurement.service_client.idle_disconnect_millis", 5000L, new K1() { // from class: com.google.android.gms.measurement.internal.m0
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Long.valueOf(C0906c6.T());
            }
        });
        Boolean bool = Boolean.FALSE;
        L = F("measurement.test.boolean_flag", bool, new K1() { // from class: com.google.android.gms.measurement.internal.l0
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Boolean.valueOf(C0943g7.f());
            }
        });
        M = F("measurement.test.string_flag", "---", new K1() { // from class: com.google.android.gms.measurement.internal.o0
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return C0943g7.e();
            }
        });
        N = F("measurement.test.long_flag", -1L, new K1() { // from class: com.google.android.gms.measurement.internal.n0
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Long.valueOf(C0943g7.d());
            }
        });
        b("measurement.test.cached_long_flag", -1L, new K1() { // from class: com.google.android.gms.measurement.internal.q0
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Long.valueOf(C0943g7.b());
            }
        });
        O = F("measurement.test.int_flag", -2, new K1() { // from class: com.google.android.gms.measurement.internal.p0
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Integer.valueOf((int) C0943g7.c());
            }
        });
        P = F("measurement.test.double_flag", Double.valueOf(-3.0d), new K1() { // from class: com.google.android.gms.measurement.internal.s0
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Double.valueOf(C0943g7.a());
            }
        });
        Q = F("measurement.experiment.max_ids", 50, new K1() { // from class: com.google.android.gms.measurement.internal.u0
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Integer.valueOf((int) C0906c6.K());
            }
        });
        R = F("measurement.upload.max_item_scoped_custom_parameters", 27, new K1() { // from class: com.google.android.gms.measurement.internal.w0
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Integer.valueOf((int) C0906c6.M());
            }
        });
        S = b("measurement.upload.max_event_parameter_value_length", 100, new K1() { // from class: com.google.android.gms.measurement.internal.v0
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Integer.valueOf((int) C0906c6.I());
            }
        });
        T = F("measurement.max_bundles_per_iteration", 100, new K1() { // from class: com.google.android.gms.measurement.internal.y0
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Integer.valueOf((int) C0906c6.C());
            }
        });
        U = F("measurement.sdk.attribution.cache.ttl", 604800000L, new K1() { // from class: com.google.android.gms.measurement.internal.x0
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Long.valueOf(C0906c6.W());
            }
        });
        V = F("measurement.redaction.app_instance_id.ttl", 7200000L, new K1() { // from class: com.google.android.gms.measurement.internal.B0
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Long.valueOf(C0906c6.X());
            }
        });
        W = F("measurement.rb.attribution.client.min_ad_services_version", 7, new K1() { // from class: com.google.android.gms.measurement.internal.z0
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Integer.valueOf((int) C0906c6.N());
            }
        });
        X = F("measurement.dma_consent.max_daily_dcu_realtime_events", 1, new K1() { // from class: com.google.android.gms.measurement.internal.D0
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Integer.valueOf((int) C0906c6.H());
            }
        });
        Y = F("measurement.rb.attribution.uri_scheme", "https", new K1() { // from class: com.google.android.gms.measurement.internal.C0
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return C0906c6.y();
            }
        });
        Z = F("measurement.rb.attribution.uri_authority", "google-analytics.com", new K1() { // from class: com.google.android.gms.measurement.internal.F0
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return C0906c6.v();
            }
        });
        a0 = F("measurement.rb.attribution.uri_path", "privacy-sandbox/register-app-conversion", new K1() { // from class: com.google.android.gms.measurement.internal.G0
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return C0906c6.w();
            }
        });
        b0 = F("measurement.session.engagement_interval", 3600000L, new K1() { // from class: com.google.android.gms.measurement.internal.J0
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Long.valueOf(C0906c6.F());
            }
        });
        c0 = F("measurement.rb.attribution.app_allowlist", "com.labpixies.flood,com.sofascore.results,games.spearmint.triplecrush,com.block.juggle,io.supercent.linkedcubic,com.cdtg.gunsound,com.corestudios.storemanagementidle,com.cdgames.fidget3d,io.supercent.burgeridle,io.supercent.pizzaidle,jp.ne.ibis.ibispaintx.app,com.dencreak.dlcalculator,com.ebay.kleinanzeigen,de.wetteronline.wetterapp,com.game.shape.shift,com.champion.cubes,bubbleshooter.orig,com.wolt.android,com.master.hotelmaster,com.games.bus.arrival,com.playstrom.dop2,com.huuuge.casino.slots,com.ig.spider.fighting,com.jura.coloring.page,com.rikkogame.ragdoll2,com.ludo.king,com.sigma.prank.sound.haircut,com.crazy.block.robo.monster.cliffs.craft,com.fugo.wow,com.maps.locator.gps.gpstracker.phone,com.gamovation.tileclub,com.pronetis.ironball2,com.meesho.supply,pdf.pdfreader.viewer.editor.free,com.dino.race.master,com.ig.moto.racing,ai.photo.enhancer.photoclear,com.duolingo,com.candle.magic_piano,com.free.vpn.super.hotspot.open,sg.bigo.live,com.cdg.tictactoe,com.zhiliaoapp.musically.go,com.wildspike.wormszone,com.mast.status.video.edit,com.vyroai.photoeditorone,com.pujiagames.deeeersimulator,com.superbinogo.jungleboyadventure,com.trustedapp.pdfreaderpdfviewer,com.artimind.aiart.artgenerator.artavatar,de.cellular.ottohybrid,com.zeptolab.cats.google,in.crossy.daily_crossword", new K1() { // from class: com.google.android.gms.measurement.internal.I0
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return C0906c6.t();
            }
        });
        d0 = F("measurement.rb.attribution.user_properties", "_npa,npa", new K1() { // from class: com.google.android.gms.measurement.internal.L0
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return C0906c6.A();
            }
        });
        e0 = F("measurement.rb.attribution.event_params", "value|currency", new K1() { // from class: com.google.android.gms.measurement.internal.K0
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return C0906c6.r();
            }
        });
        f0 = F("measurement.rb.attribution.query_parameters_to_remove", HttpUrl.FRAGMENT_ENCODE_SET, new K1() { // from class: com.google.android.gms.measurement.internal.N0
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return C0906c6.x();
            }
        });
        g0 = F("measurement.rb.attribution.max_queue_time", 1209600000L, new K1() { // from class: com.google.android.gms.measurement.internal.M0
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Long.valueOf(C0906c6.V());
            }
        });
        Boolean bool2 = Boolean.TRUE;
        h0 = F("measurement.collection.log_event_and_bundle_v2", bool2, new K1() { // from class: com.google.android.gms.measurement.internal.P0
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Boolean.valueOf(C0952h7.a());
            }
        });
        i0 = a("measurement.quality.checksum", bool);
        j0 = F("measurement.audience.use_bundle_end_timestamp_for_non_sequence_property_filters", bool, new K1() { // from class: com.google.android.gms.measurement.internal.O0
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Boolean.valueOf(H6.c());
            }
        });
        k0 = F("measurement.audience.refresh_event_count_filters_timestamp", bool, new K1() { // from class: com.google.android.gms.measurement.internal.R0
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Boolean.valueOf(H6.b());
            }
        });
        l0 = b("measurement.audience.use_bundle_timestamp_for_event_count_filters", bool, new K1() { // from class: com.google.android.gms.measurement.internal.S0
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Boolean.valueOf(H6.d());
            }
        });
        m0 = F("measurement.sdk.collection.last_deep_link_referrer_campaign2", bool, new K1() { // from class: com.google.android.gms.measurement.internal.V0
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Boolean.valueOf(C0889a7.a());
            }
        });
        n0 = F("measurement.integration.disable_firebase_instance_id", bool, new K1() { // from class: com.google.android.gms.measurement.internal.U0
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Boolean.valueOf(y7.b());
            }
        });
        o0 = F("measurement.collection.service.update_with_analytics_fix", bool, new K1() { // from class: com.google.android.gms.measurement.internal.X0
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Boolean.valueOf(z7.a());
            }
        });
        p0 = F("measurement.service.storage_consent_support_version", 203600, new K1() { // from class: com.google.android.gms.measurement.internal.W0
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Integer.valueOf((int) C0969j6.a());
            }
        });
        q0 = F("measurement.service.store_null_safelist", bool2, new K1() { // from class: com.google.android.gms.measurement.internal.Z0
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Boolean.valueOf(A6.b());
            }
        });
        r0 = F("measurement.service.store_safelist", bool2, new K1() { // from class: com.google.android.gms.measurement.internal.Y0
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Boolean.valueOf(A6.c());
            }
        });
        s0 = F("measurement.session_stitching_token_enabled", bool, new K1() { // from class: com.google.android.gms.measurement.internal.c1
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Boolean.valueOf(n7.b());
            }
        });
        t0 = b("measurement.sgtm.service", bool2, new K1() { // from class: com.google.android.gms.measurement.internal.a1
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Boolean.valueOf(t7.d());
            }
        });
        u0 = b("measurement.sgtm.preview_mode_enabled", bool2, new K1() { // from class: com.google.android.gms.measurement.internal.d1
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Boolean.valueOf(t7.c());
            }
        });
        v0 = b("measurement.sgtm.app_allowlist", "de.zalando.mobile.internal,de.zalando.mobile.internal.debug,de.zalando.lounge.dev,grit.storytel.app,com.rbc.mobile.android,com.rbc.mobile.android,com.dylvian.mango.activities,com.home24.android,com.home24.android.staging,se.lf.mobile.android,se.lf.mobile.android.beta,se.lf.mobile.android.rc,se.lf.mobile.android.test,se.lf.mobile.android.test.debug,com.boots.flagship.android,com.boots.flagshiproi.android,de.zalando.mobile,com.trivago,com.getyourguide.android,es.mobail.meliarewards,se.nansen.coop.debug,se.nansen.coop,se.coop.coop.qa,com.booking,com.google.firebaseengage,com.mse.mseapp.dev,com.mse.mseapp,pl.eobuwie.eobuwieapp,br.com.eventim.mobile.app.Android,ch.ticketcorner.mobile.app.Android,de.eventim.mobile.app.Android,dk.billetlugen.mobile.app.Android,nl.eventim.mobile.app.Android,com.asos.app,com.blueshieldca.prod,dk.magnetix.tivoliapp,matas.matas.internal,nl.omoda", new K1() { // from class: com.google.android.gms.measurement.internal.f1
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return C0906c6.u();
            }
        });
        F("measurement.sgtm.upload_queue", bool, new K1() { // from class: com.google.android.gms.measurement.internal.i1
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Boolean.valueOf(t7.e());
            }
        });
        F("measurement.sgtm.google_signal.enable", bool, new K1() { // from class: com.google.android.gms.measurement.internal.h1
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Boolean.valueOf(t7.b());
            }
        });
        w0 = F("measurement.gmscore_feature_tracking", bool2, new K1() { // from class: com.google.android.gms.measurement.internal.k1
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Boolean.valueOf(T6.b());
            }
        });
        x0 = b("measurement.fix_health_monitor_stack_trace", bool2, new K1() { // from class: com.google.android.gms.measurement.internal.j1
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Boolean.valueOf(N6.b());
            }
        });
        y0 = b("measurement.item_scoped_custom_parameters.client", bool2, new K1() { // from class: com.google.android.gms.measurement.internal.m1
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Boolean.valueOf(Z6.b());
            }
        });
        z0 = F("measurement.item_scoped_custom_parameters.service", bool2, new K1() { // from class: com.google.android.gms.measurement.internal.l1
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Boolean.valueOf(Z6.c());
            }
        });
        A0 = b("measurement.rb.attribution.service", bool2, new K1() { // from class: com.google.android.gms.measurement.internal.o1
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Boolean.valueOf(C0997m7.f());
            }
        });
        B0 = b("measurement.rb.attribution.client2", bool2, new K1() { // from class: com.google.android.gms.measurement.internal.q1
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Boolean.valueOf(C0997m7.c());
            }
        });
        C0 = F("measurement.rb.attribution.uuid_generation", bool2, new K1() { // from class: com.google.android.gms.measurement.internal.p1
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Boolean.valueOf(C0997m7.h());
            }
        });
        D0 = F("measurement.rb.attribution.enable_trigger_redaction", bool2, new K1() { // from class: com.google.android.gms.measurement.internal.r1
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Boolean.valueOf(C0997m7.g());
            }
        });
        F("measurement.rb.attribution.followup1.service", bool, new K1() { // from class: com.google.android.gms.measurement.internal.u1
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Boolean.valueOf(C0997m7.d());
            }
        });
        E0 = F("measurement.rb.attribution.registration_regardless_consent", bool, new K1() { // from class: com.google.android.gms.measurement.internal.t1
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Boolean.valueOf(C0997m7.e());
            }
        });
        F0 = F("measurement.rb.attribution.ad_campaign_info", bool, new K1() { // from class: com.google.android.gms.measurement.internal.w1
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Boolean.valueOf(C0997m7.b());
            }
        });
        G0 = b("measurement.rb.attribution.improved_retry", bool2, new K1() { // from class: com.google.android.gms.measurement.internal.v1
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Boolean.valueOf(C0997m7.i());
            }
        });
        H0 = F("measurement.client.sessions.enable_fix_background_engagement", bool, new K1() { // from class: com.google.android.gms.measurement.internal.y1
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Boolean.valueOf(s7.a());
            }
        });
        I0 = F("measurement.client.sessions.enable_pause_engagement_in_background", bool2, new K1() { // from class: com.google.android.gms.measurement.internal.x1
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Boolean.valueOf(s7.b());
            }
        });
        J0 = F("measurement.dma_consent.service_dcu_event2", bool2, new K1() { // from class: com.google.android.gms.measurement.internal.z1
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Boolean.valueOf(C1068v6.b());
            }
        });
        K0 = F("measurement.dma_consent.services_database_update_fix", bool2, new K1() { // from class: com.google.android.gms.measurement.internal.D1
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Boolean.valueOf(C1068v6.a());
            }
        });
        L0 = b("measurement.service.deferred_first_open", bool2, new K1() { // from class: com.google.android.gms.measurement.internal.B1
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Boolean.valueOf(C1021p6.b());
            }
        });
        M0 = b("measurement.gbraid_campaign.gbraid.client.dev", bool, new K1() { // from class: com.google.android.gms.measurement.internal.E1
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Boolean.valueOf(O6.b());
            }
        });
        N0 = b("measurement.gbraid_campaign.gbraid.service", bool, new K1() { // from class: com.google.android.gms.measurement.internal.H1
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Boolean.valueOf(O6.c());
            }
        });
        O0 = b("measurement.increase_param_lengths", bool2, new K1() { // from class: com.google.android.gms.measurement.internal.G1
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Boolean.valueOf(U6.b());
            }
        });
        P0 = F("measurement.disable_npa_for_dasher_and_unicorn", bool2, new K1() { // from class: com.google.android.gms.measurement.internal.J1
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Boolean.valueOf(C1060u6.b());
            }
        });
        Q0 = b("measurement.consent_regional_defaults.service", bool, new K1() { // from class: com.google.android.gms.measurement.internal.I1
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Boolean.valueOf(C0960i6.c());
            }
        });
        R0 = b("measurement.consent_regional_defaults.client2", bool, new K1() { // from class: com.google.android.gms.measurement.internal.L1
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Boolean.valueOf(C0960i6.b());
            }
        });
        S0 = F("measurement.service.consent.pfo_on_fx", bool2, new K1() { // from class: com.google.android.gms.measurement.internal.H
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Boolean.valueOf(B6.c());
            }
        });
        T0 = F("measurement.service.consent.params_on_fx", bool2, new K1() { // from class: com.google.android.gms.measurement.internal.K
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Boolean.valueOf(B6.b());
            }
        });
        U0 = F("measurement.service.consent.app_start_fix", bool2, new K1() { // from class: com.google.android.gms.measurement.internal.J
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Boolean.valueOf(B6.a());
            }
        });
        V0 = b("measurement.consent.stop_reset_on_storage_denied.client", bool2, new K1() { // from class: com.google.android.gms.measurement.internal.M
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Boolean.valueOf(C1013o6.b());
            }
        });
        W0 = b("measurement.consent.stop_reset_on_storage_denied.service", bool2, new K1() { // from class: com.google.android.gms.measurement.internal.W
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Boolean.valueOf(C1013o6.c());
            }
        });
        X0 = F("measurement.consent.scrub_audience_data_analytics_consent", bool2, new K1() { // from class: com.google.android.gms.measurement.internal.k0
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Boolean.valueOf(C1013o6.d());
            }
        });
        Y0 = F("measurement.consent.fix_first_open_count_from_snapshot", bool2, new K1() { // from class: com.google.android.gms.measurement.internal.t0
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Boolean.valueOf(C1013o6.e());
            }
        });
        Z0 = F("measurement.fix_engagement_on_reset_analytics_data", bool2, new K1() { // from class: com.google.android.gms.measurement.internal.H0
            @Override // com.google.android.gms.measurement.internal.K1
            public final Object a() {
                return Boolean.valueOf(I6.a());
            }
        });
    }

    private static <V> M1<V> F(String str, V v2, K1<V> k1) {
        return c(str, v2, v2, k1, false);
    }

    private static <V> M1<V> a(String str, V v2) {
        return c(str, v2, v2, null, false);
    }

    private static <V> M1<V> b(String str, V v2, K1<V> k1) {
        return c(str, v2, v2, k1, true);
    }

    private static <V> M1<V> c(String str, V v2, V v3, K1<V> k1, boolean z2) {
        M1<V> m1 = new M1<>(str, v2, v3, k1, z2);
        if (z2) {
            a.add(m1);
        }
        return m1;
    }

    public static Map<String, String> e(Context context) {
        ax.H6.L2 l2A = ax.H6.L2.a(context.getContentResolver(), ax.H6.X2.a("com.google.android.gms.measurement"), new Runnable() { // from class: ax.M6.d
            @Override // java.lang.Runnable
            public final void run() {
                AbstractC0885a3.m();
            }
        });
        return l2A == null ? Collections.EMPTY_MAP : l2A.b();
    }
}
