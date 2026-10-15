package com.box.androidsdk.content.auth;

import com.box.androidsdk.content.BoxException;
import com.box.androidsdk.content.models.BoxMDMData;
import com.box.androidsdk.content.models.BoxSession;
import com.box.androidsdk.content.requests.BoxRequest;
import com.box.androidsdk.content.requests.BoxResponse;
import com.box.androidsdk.content.utils.SdkUtils;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
class BoxApiAuthentication extends ax.K3.a {

    static class BoxCreateAuthRequest extends BoxRequest<BoxAuthentication.BoxAuthenticationInfo, BoxCreateAuthRequest> {
        private static final long serialVersionUID = 8123965031279971580L;

        public BoxCreateAuthRequest(BoxSession boxSession, String str, String str2, String str3, String str4) {
            super(BoxAuthentication.BoxAuthenticationInfo.class, str, boxSession);
            this.mRequestMethod = BoxRequest.Methods.POST;
            A(BoxRequest.ContentTypes.URL_ENCODED);
            this.mBodyMap.put("grant_type", "authorization_code");
            this.mBodyMap.put("code", str2);
            this.mBodyMap.put("client_id", str3);
            this.mBodyMap.put("client_secret", str4);
            if (boxSession.w() != null) {
                E(boxSession.w(), boxSession.x());
            }
            if (boxSession.y() != null) {
                F(boxSession.y());
            }
            if (boxSession.D() != null) {
                G(boxSession.D().longValue());
            }
        }

        public BoxCreateAuthRequest E(String str, String str2) {
            if (!SdkUtils.l(str)) {
                this.mBodyMap.put("box_device_id", str);
            }
            if (!SdkUtils.l(str2)) {
                this.mBodyMap.put("box_device_name", str2);
            }
            return this;
        }

        public BoxCreateAuthRequest F(BoxMDMData boxMDMData) {
            if (boxMDMData != null) {
                this.mBodyMap.put("box_mdm_data", boxMDMData.z());
            }
            return this;
        }

        public BoxCreateAuthRequest G(long j) {
            this.mBodyMap.put("box_refresh_token_expires_at", Long.toString(j));
            return this;
        }
    }

    static class BoxRefreshAuthRequest extends BoxRequest<BoxAuthentication.BoxAuthenticationInfo, BoxRefreshAuthRequest> {
        private static final long serialVersionUID = 8123965031279971570L;

        public BoxRefreshAuthRequest(BoxSession boxSession, String str, String str2, String str3, String str4) {
            super(BoxAuthentication.BoxAuthenticationInfo.class, str, boxSession);
            this.mContentType = BoxRequest.ContentTypes.URL_ENCODED;
            this.mRequestMethod = BoxRequest.Methods.POST;
            this.mBodyMap.put("grant_type", "refresh_token");
            this.mBodyMap.put("refresh_token", str2);
            this.mBodyMap.put("client_id", str3);
            this.mBodyMap.put("client_secret", str4);
            if (boxSession.w() != null) {
                E(boxSession.w(), boxSession.x());
            }
            if (boxSession.D() != null) {
                F(boxSession.D().longValue());
            }
        }

        public BoxRefreshAuthRequest E(String str, String str2) {
            if (!SdkUtils.l(str)) {
                this.mBodyMap.put("box_device_id", str);
            }
            if (!SdkUtils.l(str2)) {
                this.mBodyMap.put("box_device_name", str2);
            }
            return this;
        }

        public BoxRefreshAuthRequest F(long j) {
            this.mBodyMap.put("box_refresh_token_expires_at", Long.toString(j));
            return this;
        }

        @Override // com.box.androidsdk.content.requests.BoxRequest
        protected void u(BoxResponse<BoxAuthentication.BoxAuthenticationInfo> boxResponse) throws BoxException {
            super.u(boxResponse);
            if (boxResponse.c()) {
                ((BoxAuthentication.BoxAuthenticationInfo) boxResponse.b()).X(this.mSession.E());
            }
        }
    }

    BoxApiAuthentication(BoxSession boxSession) {
        super(boxSession);
        this.b = "https://api.box.com";
    }

    @Override // ax.K3.a
    protected String b() {
        BoxSession boxSession = this.a;
        return (boxSession == null || boxSession.q() == null || this.a.q().K() == null) ? super.b() : String.format("https://api.%s", this.a.q().K());
    }

    BoxCreateAuthRequest c(String str, String str2, String str3) {
        return new BoxCreateAuthRequest(this.a, d(), str, str2, str3);
    }

    protected String d() {
        return String.format(Locale.ENGLISH, "%s/oauth2/token", b());
    }

    BoxRefreshAuthRequest e(String str, String str2, String str3) {
        return new BoxRefreshAuthRequest(this.a, d(), str, str2, str3);
    }
}
