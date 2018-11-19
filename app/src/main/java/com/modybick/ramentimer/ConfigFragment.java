package com.modybick.ramentimer;


import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.preference.Preference;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

public class ConfigFragment extends PreferenceFragmentCompat {

    public ConfigFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        PreferenceManager preferenceManager = getPreferenceManager();
        preferenceManager.setSharedPreferencesName(getString(R.string.preference_name));
        addPreferencesFromResource(R.xml.preferences);

        //初期化ボタンにクリックリスナー設定
        Preference pref_ini = findPreference("pref_initialize");
        pref_ini.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public boolean onPreferenceClick(final Preference preference) {
                new AlertDialog.Builder(Objects.requireNonNull(getActivity()))
                        .setTitle(getString(R.string.pref_initialize))
                        .setMessage(getString(R.string.pref_initialize_text))
                        .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // OK button pressed
                                //preference.getEditor().clear();
                            }
                        })
                        .setNegativeButton(getString(R.string.cancel), null)
                        .show();

                return true;
            }
        });

    }


}
