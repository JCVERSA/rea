package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import com.microsoft.graph.extensions.Entity;
import com.microsoft.graph.extensions.InferenceClassificationOverride;
import com.microsoft.graph.extensions.InferenceClassificationOverrideCollectionPage;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class BaseInferenceClassification extends Entity implements d {
    public transient InferenceClassificationOverrideCollectionPage f;
    private transient l g;
    private transient e h;

    @Override // com.microsoft.graph.generated.BaseEntity, ax.Aa.d
    public void c(e eVar, l lVar) {
        this.h = eVar;
        this.g = lVar;
        if (lVar.w("overrides")) {
            BaseInferenceClassificationOverrideCollectionResponse baseInferenceClassificationOverrideCollectionResponse = new BaseInferenceClassificationOverrideCollectionResponse();
            if (lVar.w("overrides@odata.nextLink")) {
                baseInferenceClassificationOverrideCollectionResponse.b = lVar.t("overrides@odata.nextLink").j();
            }
            l[] lVarArr = (l[]) eVar.b(lVar.t("overrides").toString(), l[].class);
            InferenceClassificationOverride[] inferenceClassificationOverrideArr = new InferenceClassificationOverride[lVarArr.length];
            for (int i = 0; i < lVarArr.length; i++) {
                InferenceClassificationOverride inferenceClassificationOverride = (InferenceClassificationOverride) eVar.b(lVarArr[i].toString(), InferenceClassificationOverride.class);
                inferenceClassificationOverrideArr[i] = inferenceClassificationOverride;
                inferenceClassificationOverride.c(eVar, lVarArr[i]);
            }
            baseInferenceClassificationOverrideCollectionResponse.a = Arrays.asList(inferenceClassificationOverrideArr);
            this.f = new InferenceClassificationOverrideCollectionPage(baseInferenceClassificationOverrideCollectionResponse, null);
        }
    }
}
