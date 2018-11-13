package com.modybick.ramentimer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.provider.AlarmClock;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import static java.lang.Math.round;

public class MainActivity extends AppCompatActivity {

    private int minutes;
    private float hardness;

    private int min_button1_value;
    private int min_button2_value;
    private int min_button3_value;

    private String hard_button1_text;
    private String hard_button2_text;
    private String hard_button3_text;
    private float hard_button1_value;
    private float hard_button2_value;
    private float hard_button3_value;

    private String timerMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //プリファレンスの読み込み
        readPreference();
        //時間選択フラグメントの表示
        gotoMinuteFragment();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.preference_manu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.preference_menu:
                //設定アクティビティを表示
                Intent i = new Intent(getApplicationContext(), ConfigActivity.class);
                startActivity(i);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //プリファレンスの読み込みを行う
    private void readPreference() {
        SharedPreferences pref = getPreferences(MODE_PRIVATE);
        min_button1_value = pref.getInt("pref_min_button1_value", 3);
        min_button2_value = pref.getInt("pref_min_button2_value", 4);
        min_button3_value = pref.getInt("pref_min_button3_value", 5);
        hard_button1_text = pref.getString("pref_hard_button1_text", "かため");
        hard_button2_text = pref.getString("pref_hard_button2_text", "ふつう");
        hard_button3_text = pref.getString("pref_hard_button3_text", "やわらかめ");
        hard_button1_value = pref.getFloat("pref_hard_button1_value", 0.8f);
        hard_button2_value = pref.getFloat("pref_hard_button2_value", 1.0f);
        hard_button3_value = pref.getFloat("pref_hard_button3_value", 1.2f);
        timerMessage = pref.getString("pref_timer_message", "ラーメンが完成しました");
    }
    
    //時間選択フラグメントの表示
    private void gotoMinuteFragment() {
        MinuteFragment minuteFragment = new MinuteFragment();
        //引数としてボタンの値を渡す
        Bundle args = new Bundle();
        args.putInt("button1_value", min_button1_value);
        args.putInt("button2_value", min_button2_value);
        args.putInt("button3_value", min_button3_value);
        minuteFragment.setArguments(arg);
        //遷移開始
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.main_layout, minuteFragment);
        transaction.commit();
    }

    //分選択ボタンが押されたとき
    public void onMinButtonClicked(View v) {

        switch (v.getId()) {
            case R.id.min_button1:
                minutes = min_button1_value;
                break;
            case R.id.min_button2:
                minutes = min_button2_value;
                break;
            case R.id.min_button3:
                minutes = min_button3_value;
                break;
        }
        gotoHardnessFragment();
    }

    //かたさ選択ボタンが押されたとき
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void onHardButtonClicked(View v) {

        switch (v.getId()) {
            case R.id.hard_button1:
                hardness = hard_button1_value;
                break;
            case R.id.hard_button2:
                hardness = hard_button2_value;
                break;
            case R.id.hard_button3:
                hardness = hard_button3_value;
                break;
        }
        startTimer(timerMessage, calcSeconds());
    }

    //かたさ選択フラグメントに遷移する
    private void gotoHardnessFragment() {
        HardnessFragment hardnessFragment = new HardnessFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.main_layout, hardnessFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    //タイマーに設定する時間（秒）を計算
    private int calcSeconds() {
        int sec;
        sec = round(this.minutes * 60 * this.hardness);
        return sec;
    }

    // タイマーをセットし、スタートする
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void startTimer(String message, int sec) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_TIMER)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_LENGTH, sec)
                .putExtra(AlarmClock.EXTRA_SKIP_UI, false);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
