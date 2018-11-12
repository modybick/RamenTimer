package com.modybick.ramentimer;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class HardnessFragment extends Fragment {


    public HardnessFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //設定の読み込み
        SharedPreferences pref = getContext().getSharedPreferences(getString(R.string.preference_name), MODE_PRIVATE);
        String button1_text = pref.getString("pref_hard_button1_text", "かため");
        String button2_text = pref.getString("pref_hard_button2_text", "ふつう");
        String button3_text = pref.getString("pref_hard_button3_text", "やわらかめ");

        //ボタンテキストの設定
        View v = inflater.inflate(R.layout.fragment_hardness, container, false);
        Button button1 = v.findViewById(R.id.hard_button1);
        button1.setText(button1_text);
        Button button2 = v.findViewById(R.id.hard_button2);
        button2.setText(button2_text);
        Button button3 = v.findViewById(R.id.hard_button3);
        button3.setText(button3_text);

        return v;
    }
}
