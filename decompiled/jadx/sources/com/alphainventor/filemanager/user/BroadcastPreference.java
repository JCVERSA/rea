package com.alphainventor.filemanager.user;

import android.content.Context;
import android.preference.Preference;
import android.util.AttributeSet;

/* JADX INFO: loaded from: classes.dex */
public class BroadcastPreference extends Preference implements Preference.OnPreferenceClickListener {
    public BroadcastPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOnPreferenceClickListener(this);
    }

    @Override // android.preference.Preference.OnPreferenceClickListener
    public boolean onPreferenceClick(Preference preference) {
        getContext().sendBroadcast(getIntent());
        return true;
    }
}
