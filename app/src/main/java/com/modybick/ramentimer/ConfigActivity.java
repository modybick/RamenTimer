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
    
    PreferenceScreen preferenceScreen = findPreference("pref_initialize");
    preferenceScreen.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
        @Override
        public boolean onPreferenceClick(Prefrence preference) {
            // TODO 初期化ボタンクリック時の処理
            
            return true;
        }
    }
    
}
