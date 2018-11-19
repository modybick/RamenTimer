package com.modybick.ramentimer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import static java.lang.Math.round;

public class MainActivity extends AppCompatActivity {

    private int selectedMinutes;
    private float selectedHardness;

    private int minButton1Value;
    private int minButton2Value;
    private int minButton3Value;

    private float hardButton1Value;
    private float hardButton2Value;
    private float hardButton3Value;

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
    @SuppressWarnings("ConstantConditions")
    private void readPreference() {
        SharedPreferences pref = getSharedPreferences(getString(R.string.preference_name), MODE_PRIVATE);
        minButton1Value = Integer.valueOf(pref.getString("pref_min_button1_value", "3"));
        minButton2Value = Integer.valueOf(pref.getString("pref_min_button2_value", "4"));
        minButton3Value = Integer.valueOf(pref.getString("pref_min_button3_value", "5"));
        hardButton1Value = Float.valueOf(pref.getString("pref_hard_button1_value", "0.8f"));
        hardButton2Value = Float.valueOf(pref.getString("pref_hard_button2_value", "1.0f"));
        hardButton3Value = Float.valueOf(pref.getString("pref_hard_button3_value", "1.2f"));
    }

    //時間選択フラグメントの表示
    private void gotoMinuteFragment() {
        MinuteFragment minuteFragment = new MinuteFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.main_layout, minuteFragment);
        transaction.commit();
    }

    //分選択ボタンが押されたとき
    public void onMinButtonClicked(View v) {

        switch (v.getId()) {
            case R.id.min_button1:
                selectedMinutes = minButton1Value;
                break;
            case R.id.min_button2:
                selectedMinutes = minButton2Value;
                break;
            case R.id.min_button3:
                selectedMinutes = minButton3Value;
                break;
        }
        gotoHardnessFragment();
    }

    //かたさ選択ボタンが押されたとき
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void onHardButtonClicked(View v) {

        switch (v.getId()) {
            case R.id.hard_button1:
                selectedHardness = hardButton1Value;
                break;
            case R.id.hard_button2:
                selectedHardness = hardButton2Value;
                break;
            case R.id.hard_button3:
                selectedHardness = hardButton3Value;
                break;
        }
        startTimer(getString(R.string.timer_message), calcSeconds());
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
        sec = round(this.selectedMinutes * 60 * this.selectedHardness);
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
