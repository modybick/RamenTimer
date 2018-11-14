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
            // 初期化確認ダイアログ表示
            new AlertDialog.Builder(getActivity())
                
                // TODO テキストをresに
                
                .setTitle("初期化")
                .setMessage("設定をインストール時の状態に戻します")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // OKボタンクリック時、設定初期化を実行
                        SheredPreferences pref = getSharedPreferences(getString(R.string.preference_name), MODE_PRIVATE);
                        pref.edit().clear();                        
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
            return true;
        }
    }
    
}
