package com.example.ogatafumitoshi.androidoverviewdemo;

/**
 * Created by ogata.fumitoshi on 2015/03/20.
 */

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.WindowManager;

/*
    バックグラウンドで動くServiceクラスを作成
 */
public final class MainService extends Service {

    private WindowManager windowManager;
    public OverlayView overlayView;
    private OverlayView.Listener overlayListener = new OverlayView.Listener() {
        @Override public void onCancel() {
            //cancelRecording();
        }
        @Override public void onStart() {
            //startRecording();
        }
        @Override public void onStop() {
            //stopRecording();
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        //Log.d("service", "onCreate");
        windowManager = (WindowManager) this.getSystemService(WINDOW_SERVICE);
        overlayView = OverlayView.create(this,overlayListener);
        windowManager.addView(overlayView, OverlayView.createLayoutParams(this));
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Log.d("service", "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        //Log.d("service", "onDestroy");
        super.onDestroy();
        //remove
        windowManager.removeView(overlayView);
        overlayView.cancelComment();
    }

    @Override public IBinder onBind(@NonNull Intent intent) {
        throw new AssertionError("Not supported.");
    }
}