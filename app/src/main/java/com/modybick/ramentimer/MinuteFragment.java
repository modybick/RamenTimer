package com.modybick.ramentimer;


import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

public class MinuteFragment extends Fragment {

    public MinuteFragment() {
        // Required empty public constructor
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        SharedPreferences pref = Objects.requireNonNull(getContext()).getSharedPreferences(getString(R.string.preference_name), MODE_PRIVATE);
        int button1_value = Integer.valueOf(Objects.requireNonNull(pref.getString("pref_min_button1_value", "3")));
        int button2_value = Integer.valueOf(Objects.requireNonNull(pref.getString("pref_min_button2_value", "4")));
        int button3_value = Integer.valueOf(Objects.requireNonNull(pref.getString("pref_min_button3_value", "5")));

        View v = inflater.inflate(R.layout.fragment_minute, container, false);

        Button button1 = v.findViewById(R.id.min_button1);
        button1.setText(String.valueOf(button1_value) + getString(R.string.unit_minute));
        Button button2 = v.findViewById(R.id.min_button2);
        button2.setText(String.valueOf(button2_value) +getString(R.string.unit_minute));
        Button button3 = v.findViewById(R.id.min_button3);
        button3.setText(String.valueOf(button3_value) + getString(R.string.unit_minute));

        return v;
    }
}
