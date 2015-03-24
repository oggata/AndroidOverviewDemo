package com.example.ogatafumitoshi.androidoverviewdemo;

/**
 * Created by ogata.fumitoshi on 2015/03/20.
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import com.google.common.base.Stopwatch;

import static android.graphics.PixelFormat.TRANSLUCENT;
import static android.view.WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED;
import static android.view.WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR;
import static android.view.WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
import static android.view.WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;
import static android.view.WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
import static android.view.WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
import static android.view.WindowManager.LayoutParams.TYPE_TOAST;
import static android.view.WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;

import java.util.Timer;
import java.util.TimerTask;
import android.widget.ImageView;
import android.widget.LinearLayout;
/*
 *  OverlayView
 */
@SuppressLint("ViewConstructor")
final class OverlayView extends FrameLayout {

    private Context ctx;
    private Timer timer;

    static OverlayView create(Context context, Listener listener) {
        Log.d("service", "OverlayView");
        return new OverlayView(context, listener);
    }

    static WindowManager.LayoutParams createLayoutParams(Context context) {
        Resources res = context.getResources();
        //画面の上部に表示
        int width = 2000;
        int height = 2000;
        final WindowManager.LayoutParams params =
                new WindowManager.LayoutParams(
                        width, height,
                        TYPE_TOAST,
                        FLAG_NOT_FOCUSABLE
                                | FLAG_NOT_TOUCHABLE
                                | FLAG_LAYOUT_NO_LIMITS
                                | FLAG_LAYOUT_INSET_DECOR
                                | FLAG_LAYOUT_IN_SCREEN
                                | FLAG_HARDWARE_ACCELERATED,
                        TRANSLUCENT
                );
        params.gravity = Gravity.LEFT | Gravity.TOP;
        return params;
    }

    interface Listener {
        void onCancel();
        void onStart();
        void onStop();
    }

    private final Listener listener;

    private OverlayView(Context context, Listener listener) {
        super(context);
        this.listener = listener;
        this.ctx = context;
        inflate(context, R.layout.overlay_view, this);
        ButterKnife.inject(this);

        //timer (5秒毎に処理を実行する)
        timer = new Timer();
        TimerTask timerTask = new MainTimerTask(ctx);
        timer.scheduleAtFixedRate(timerTask, 0, 3000);
    }

    public void cancelComment(){
        timer.cancel();
        timer = null;
    }

    public void addComment(){
        //Log.d("tag", "set comment");
        //テキストの生成
        TextView comment = new TextView(ctx);
        comment.setText("吾輩は猫である。名前はまだ無い。どこで生れたかとんと見当がつかぬ。");
        comment.setLayoutParams(new LayoutParams(2000, 1000));
        comment.setTextSize(35);
        comment.setTextColor(Color.WHITE);
        this.addView(comment);

        //アニメーション制御
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f,0.0f,2000.0f);
        translateAnimation.setDuration(20000);
        translateAnimation.setFillAfter(true);
        comment.startAnimation(translateAnimation);
    }

    private LinearLayout.LayoutParams createParam(int w, int h){
        return new LinearLayout.LayoutParams(w, h);
    }

    public void addStamp(){
        //viewの生成
        ImageView stampImg = new ImageView(ctx);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(300,300);
        stampImg.setLayoutParams(lp);
        stampImg.setImageResource(R.drawable.cat);
        this.addView(stampImg);

        //アニメーション制御
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f,0.0f,2000.0f);
        translateAnimation.setDuration(20000);
        translateAnimation.setFillAfter(true);
        stampImg.startAnimation(translateAnimation);
    }
}