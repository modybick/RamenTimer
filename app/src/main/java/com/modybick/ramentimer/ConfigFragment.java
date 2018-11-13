package com.modybick.ramentimer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ConfigFragment extends PreferenceFragmentCompat {

    public ConfigFragment() {
        // Required empty public constructor
    }

    /* TODO 設定の初期化を実装 */

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        PreferenceManager preferenceManager = getPreferenceManager();
        preferenceManager.setSharedPreferencesName(getString(R.string.preference_name));
        addPreferencesFromResource(R.xml.preferences);
    }


}
