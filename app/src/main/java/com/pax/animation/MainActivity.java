package com.pax.animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        intValueAnimation();
        Button button = (Button)findViewById(R.id.button_animation);
        initAnimationByXml(button);
    }

    private void intValueAnimation() {
        final ValueAnimator animator = ValueAnimator.ofInt(0, 3);
        animator.setDuration(500);
        animator.setStartDelay(500);
        animator.setRepeatCount(0);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int currentValue = (Integer) valueAnimator.getAnimatedValue();
                Log.d(TAG, "onAnimationUpdate: " + currentValue);
            }
        });
        animator.start();
    }

    private void initAnimationByXml(final Button button){
        Animator animator = AnimatorInflater.loadAnimator(MainActivity.this, R.animator.set_animation);
        animator.start();
        ValueAnimator valueAnimator = ValueAnimator.ofInt(button.getLayoutParams().width, 500);
        valueAnimator.setDuration(2000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int currentValu = (Integer)valueAnimator.getAnimatedValue();
                Log.d(TAG, "onAnimationUpdate: "+ currentValu);
                button.getLayoutParams().width = currentValu;
                button.requestLayout();
            }
        });
        valueAnimator.start();

    }
}
