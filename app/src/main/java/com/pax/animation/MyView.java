package com.pax.animation;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class MyView extends View {
    public static final float RADIUS = 70f;
    private Point currentPoint;
    private Paint mPaint;
    private static final String TAG = MyView.class.getSimpleName();

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (currentPoint == null) {
            currentPoint = new Point(RADIUS, RADIUS);
            float x = currentPoint.getX();
            float y = currentPoint.getY();
            Log.d(TAG, "onDraw: c: x: " + x + " y: " + y);
            canvas.drawCircle(x, y, RADIUS, mPaint);
            Point startPoint = new Point(RADIUS, RADIUS);
            Point endPoint = new Point(700, 1000);
            ValueAnimator anim = ValueAnimator.ofObject(new PointEvaluator(), startPoint, endPoint);
            anim.setDuration(5000);
//            anim.setRepeatCount(ValueAnimator.INFINITE);
            anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    currentPoint = (Point) valueAnimator.getAnimatedValue();
                    Log.d(TAG, "onAnimationUpdate: currentPoint!!!!");
                    invalidate();
                }
            });
            anim.start();
        } else {
            float x = currentPoint.getX();
            float y = currentPoint.getY();

            Log.d(TAG, "onDraw x: " + x + " y: " + y);
            canvas.drawCircle(x, y, RADIUS, mPaint);
        }
    }
}
