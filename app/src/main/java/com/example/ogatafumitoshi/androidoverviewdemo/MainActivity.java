package com.example.ogatafumitoshi.androidoverviewdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import butterknife.OnClick;
import butterknife.ButterKnife;
import android.view.Gravity;
import android.widget.Toast;

public class MainActivity extends Activity {

    @OnClick(R.id.startService)
    void onStartServiceClicked() {
        //Serviceクラスの指定&起動
        startService(new Intent(MainActivity.this, MainService.class));

        // Toastのインスタンスを生成
        Toast toast = Toast.makeText(MainActivity.this, "開始にゃ〜にゃ〜", Toast.LENGTH_LONG);
        // 表示位置を設定
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    @OnClick(R.id.stopService)
    void onStopServiceClicked() {
        Log.d("tag", "stopService");
        // Serviceの停止
        stopService(new Intent(MainActivity.this, MainService.class));

        // Toastのインスタンスを生成
        Toast toast = Toast.makeText(MainActivity.this, "終了にゃ〜にゃ〜", Toast.LENGTH_LONG);
        // 表示位置を設定
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }
}
