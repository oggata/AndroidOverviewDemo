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

/*
 *  OverlayView
 */
@SuppressLint("ViewConstructor")
final class OverlayView extends FrameLayout {

    private Context ctx;

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
                                | FLAG_HARDWARE_ACCELERATED, TRANSLUCENT
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
        addComment();
    }

    public void addComment(){
        Log.d("tag", "set comment");
        TextView scoreTextView = new TextView(ctx);
        scoreTextView.setText("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        scoreTextView.setLayoutParams(new LayoutParams(1000, 1000));
        scoreTextView.setTextSize(35);
        scoreTextView.setTextColor(Color.WHITE);
        this.addView(scoreTextView);
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f,0.0f,2000.0f);
        translateAnimation.setDuration(20000);
        translateAnimation.setFillAfter(true);
        scoreTextView.startAnimation(translateAnimation);
    }
}