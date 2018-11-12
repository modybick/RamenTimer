package com.modybick.ramentimer;

import android.preference.PreferenceActivity;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class ConfigActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PreferenceManager preferenceManager = getPreferenceManager();
        preferenceManager.setSharedPreferencesName(getString(R.string.preference_name));

        addPreferencesFromResource(R.xml.preferences);
    }



    /* TODO 戻るボタン押下時にアプリの再起動 */
}
