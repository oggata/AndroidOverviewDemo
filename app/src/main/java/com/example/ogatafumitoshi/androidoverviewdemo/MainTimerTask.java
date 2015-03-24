package com.example.ogatafumitoshi.androidoverviewdemo;

import java.util.Timer;
import java.util.TimerTask;
import android.os.Handler;
import android.content.Context;

/**
 * Created by ogata.fumitoshi on 2015/03/24.
 */
public class MainTimerTask extends TimerTask {
    private Handler handler;
    private Context context;

    public MainTimerTask(Context context) {
        handler = new Handler();
        this.context = context;
    }

    @Override
    public void run() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                ((MainService)context).overlayView.addStamp();
                ((MainService)context).overlayView.addComment();
            }
        });
    }

}




