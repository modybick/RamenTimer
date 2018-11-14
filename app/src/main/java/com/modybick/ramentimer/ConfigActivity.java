package com.modybick.ramentimer;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;

@SuppressLint("ExportedPreferenceActivity")
public class ConfigActivity extends PreferenceActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PreferenceManager preferenceManager = getPreferenceManager();
        preferenceManager.setSharedPreferencesName(getString(R.string.preference_name));
        addPreferencesFromResource(R.xml.preferences);

        //初期化ボタンにクリックリスナー設定
        Preference pref_ini = findPreference("pref_initialize");
        pref_ini.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(final Preference preference) {
                initialize_Alert(savedInstanceState);
                return true;
            }
        });
    }

    private void initialize_Alert(final Bundle savedInstanceState) {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.pref_initialize))
                .setMessage(getString(R.string.pref_initialize_text))
                .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // OK button pressed
                        SharedPreferences pref = getSharedPreferences(getString(R.string.preference_name), MODE_PRIVATE);
                        pref.edit().clear().apply();
                        onCreate(savedInstanceState);
                    }
                })
                .setNegativeButton(getString(R.string.cancel), null)
                .show();

    }

}

