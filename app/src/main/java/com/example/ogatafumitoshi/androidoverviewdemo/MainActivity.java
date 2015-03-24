package com.example.ogatafumitoshi.androidoverviewdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import butterknife.OnClick;
import butterknife.ButterKnife;

public class MainActivity extends Activity {

    @OnClick(R.id.startService)
    void onStartServiceClicked() {
        //Serviceクラスの指定&起動
        startService(new Intent(MainActivity.this, MainService.class));
    }

    @OnClick(R.id.stopService)
    void onStopServiceClicked() {
        Log.d("tag", "stopService");
        // Serviceの停止
        stopService(new Intent(MainActivity.this, MainService.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }
}
