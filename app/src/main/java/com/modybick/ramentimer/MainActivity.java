package com.modybick.ramentimer;

import android.content.Intent;
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

    /* TODO 設定ファイルの読み込み実装 */

    private int minutes;
    private double hardness;

    private int min_button1_value = 1;
    private int min_button2_value = 3;
    private int min_button3_value = 5;

    private double hard_button1_value = 0.7;
    private double hard_button2_value = 1;
    private double hard_button3_value = 1.1;

    private String timerMessage = "hoge";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //時間選択フラグメントを作成・表示
        MinuteFragment minuteFragment = new MinuteFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.main_layout, minuteFragment);
        transaction.commit();
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
                Intent i = new Intent(getApplicationContext(),ConfigActivity.class);
                startActivity(i);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //分選択ボタンが押されたとき
    public void onMinButtonClicked(View v) {

        switch (v.getId()) {
            case R.id.min_button1:
                minutes = min_button1_value;
            case R.id.min_button2:
                minutes = min_button2_value;
            case R.id.min_button3:
                minutes = min_button3_value;
            default:
                gotoHardnessFragment();
        }
    }

    //かたさ選択ボタンが押されたとき
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void onHardButtonClicked(View v) {

        switch (v.getId()) {
            case R.id.hard_button1:
                hardness = hard_button1_value;
            case R.id.hard_button2:
                hardness = hard_button2_value;
            case R.id.hard_button3:
                hardness = hard_button3_value;
            default:
                startTimer(timerMessage, calcSeconds());
        }
    }

    //かたさ選択フラグメントに遷移する
    public void gotoHardnessFragment(){
        HardnessFragment hardnessFragment = new HardnessFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.main_layout, hardnessFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    //タイマーに設定する時間（秒）を計算
    public int calcSeconds(){
        int sec;
        sec = (int) round(this.minutes * 60 * this.hardness);
        return sec;
    }

    // タイマーをセットし、スタートする
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void startTimer(String message, int sec){
        Intent intent = new Intent(AlarmClock.ACTION_SET_TIMER)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_LENGTH, sec)
                .putExtra(AlarmClock.EXTRA_SKIP_UI, false);
        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
    }

}
