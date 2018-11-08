package com.modybick.ramentimer;

import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ConfigActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        //setContentView(R.layout.activity_config);
    }

    /* TODO 設定ファイルの保存実装 */
}
