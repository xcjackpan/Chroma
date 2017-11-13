package com.aelment.thatj.chroma;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by thatj on 2017-11-12.
 */

public class CircleTimer extends View {

    private static final int START_ANGLE_POINT = 270;

    private Paint paint;
    private RectF rect;

    private float angle;

    public CircleTimer(Context context, AttributeSet attrs) {
        super(context, attrs);

        final int strokeWidth = 15;

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
        // Set color
        paint.setColor(ContextCompat.getColor(getContext(), R.color.grey));

        // Set size 80x80
        rect = new RectF(strokeWidth, strokeWidth, 80 + strokeWidth, 80 + strokeWidth);

        //Initial angle
        angle = 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(rect, START_ANGLE_POINT, angle, false, paint);
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    /**
     * Created by thatj on 2017-11-12.
     */

    public static class TimerAnimation extends Animation {

        private CircleTimer circle;

        private float oldAngle;
        private float newAngle;

        public TimerAnimation(CircleTimer circle, int newAngle) {
            this.oldAngle = circle.getAngle();
            this.newAngle = newAngle;
            this.circle = circle;
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation transformation) {
            float angle = oldAngle + ((newAngle - oldAngle) * interpolatedTime);

            circle.setAngle(angle);
            circle.requestLayout();
        }
    }
}
