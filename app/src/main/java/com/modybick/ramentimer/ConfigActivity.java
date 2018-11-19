package com.modybick.ramentimer;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

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

        showSettingValue();

    }

    @Override
    protected void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(listener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(listener);
    }

    private SharedPreferences.OnSharedPreferenceChangeListener listener =
            new SharedPreferences.OnSharedPreferenceChangeListener() {

                public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                    showSettingValue();
                }
            };

    private void initialize_Alert(final Bundle savedInstanceState) {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.pref_initialize))
                .setMessage(getString(R.string.pref_initialize_message))
                .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // OK button pressed
                        SharedPreferences pref = getSharedPreferences(getString(R.string.preference_name), MODE_PRIVATE);
                        pref.edit().clear().apply();
                        Toast.makeText(ConfigActivity.this, getText(R.string.pref_initialized_message), Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(getString(R.string.cancel), null)
                .show();
    }

    //設定値をsummaryに表示する
    private void showSettingValue() {
        //プリファレンスのインスタンスを取得
        EditTextPreference min1ValuePref = (EditTextPreference) getPreferenceScreen().findPreference("pref_min_button1_value");
        EditTextPreference min2ValuePref = (EditTextPreference) getPreferenceScreen().findPreference("pref_min_button2_value");
        EditTextPreference min3ValuePref = (EditTextPreference) getPreferenceScreen().findPreference("pref_min_button3_value");
        EditTextPreference hard1TextPref = (EditTextPreference) getPreferenceScreen().findPreference("pref_hard_button1_text");
        EditTextPreference hard2TextPref = (EditTextPreference) getPreferenceScreen().findPreference("pref_hard_button2_text");
        EditTextPreference hard3TextPref = (EditTextPreference) getPreferenceScreen().findPreference("pref_hard_button3_text");
        EditTextPreference hard1ValuePref = (EditTextPreference) getPreferenceScreen().findPreference("pref_hard_button1_value");
        EditTextPreference hard2ValuePref = (EditTextPreference) getPreferenceScreen().findPreference("pref_hard_button2_value");
        EditTextPreference hard3ValuePref = (EditTextPreference) getPreferenceScreen().findPreference("pref_hard_button3_value");

        //サマリーの設定
        min1ValuePref.setSummary(min1ValuePref.getText() + getString(R.string.unit_minute));
        min2ValuePref.setSummary(min2ValuePref.getText() + getString(R.string.unit_minute));
        min3ValuePref.setSummary(min3ValuePref.getText() + getString(R.string.unit_minute));
        hard1TextPref.setSummary(hard1TextPref.getText());
        hard2TextPref.setSummary(hard2TextPref.getText());
        hard3TextPref.setSummary(hard3TextPref.getText());
        hard1ValuePref.setSummary(hard1ValuePref.getText());
        hard2ValuePref.setSummary(hard2ValuePref.getText());
        hard3ValuePref.setSummary(hard3ValuePref.getText());
    }
}

