package com.xiamuyao.repidmvclibrary.View;


import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.xiamuyao.repidmvclibrary.R;


/**
 * ================================================
 * 作    者：夏沐尧  Github地址：https://github.com/XiaMuYaoDQX
 * 版    本：1.0
 * 创建日期： 2018/2/23
 * 描    述：转圈Deloading
 * 修订历史：
 * ================================================
 */
public class RotateLoading extends View {

    private static final int DEFAULT_WIDTH = 6;
    private static final int DEFAULT_SHADOW_POSITION = 2;
    private static final int DEFAULT_SPEED_OF_DEGREE = 10;

    private Paint mPaint;

    private RectF loadingRectF; // 旋转弧的绘制矩形
    private RectF shadowRectF; // 旋转阴影的绘制矩形

    private int topDegree = 10;
    private int bottomDegree = 190;

    private float arc;

    private int width;

    private boolean changeBigger = true;

    private int shadowPosition; //阴影与弧的偏差值

    private boolean isStart = false;

    private int color; //圆弧的颜色

    private int speedOfDegree; //旋转速度

    private float speedOfArc;

    public RotateLoading(Context context) {
        super(context);
        initView(context, null);
    }

    public RotateLoading(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public RotateLoading(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        color = Color.WHITE;
        width = dpToPx(context, DEFAULT_WIDTH);
        shadowPosition = dpToPx(getContext(), DEFAULT_SHADOW_POSITION);
        speedOfDegree = DEFAULT_SPEED_OF_DEGREE;

        if (null != attrs) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RotateLoading);
            color = typedArray.getColor(R.styleable.RotateLoading_loading_color, Color.WHITE);
            width = typedArray.getDimensionPixelSize(R.styleable.RotateLoading_loading_width, dpToPx(context, DEFAULT_WIDTH));
            shadowPosition = typedArray.getInt(R.styleable.RotateLoading_shadow_position, DEFAULT_SHADOW_POSITION);
            speedOfDegree = typedArray.getInt(R.styleable.RotateLoading_loading_speed, DEFAULT_SPEED_OF_DEGREE);
            isStart = typedArray.getBoolean(R.styleable.RotateLoading_is_start, false);
            typedArray.recycle();
        }
        speedOfArc = speedOfDegree / 4;
        mPaint = new Paint();
        mPaint.setColor(color);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(width);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        arc = 10;

        loadingRectF = new RectF(2 * width, 2 * width, w - 2 * width, h - 2 * width);
        shadowRectF = new RectF(2 * width + shadowPosition, 2 * width + shadowPosition, w - 2 * width + shadowPosition, h - 2 * width + shadowPosition);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!isStart) {
            return;
        }

        mPaint.setColor(Color.parseColor("#1a000000"));
        canvas.drawArc(shadowRectF, topDegree, arc, false, mPaint);
        canvas.drawArc(shadowRectF, bottomDegree, arc, false, mPaint);

        mPaint.setColor(color);
        canvas.drawArc(loadingRectF, topDegree, arc, false, mPaint);
        canvas.drawArc(loadingRectF, bottomDegree, arc, false, mPaint);

        topDegree += speedOfDegree;
        bottomDegree += speedOfDegree;
        if (topDegree > 360) {
            topDegree = topDegree - 360;
        }
        if (bottomDegree > 360) {
            bottomDegree = bottomDegree - 360;
        }

        if (changeBigger) {
            if (arc < 160) {
                arc += speedOfArc;
                invalidate();
            }
        } else {
            if (arc > speedOfDegree) {
                arc -= 2 * speedOfArc;
                invalidate();
            }
        }
        if (arc >= 160 || arc <= 10) {
            changeBigger = !changeBigger;
            invalidate();
        }
    }

    public void setLoadingColor(int color) {
        this.color = color;
    }

    public int getLoadingColor() {
        return color;
    }

    public void start() {
        if (!isStart) {
            startAnimator();
            isStart = true;
            invalidate();
        }
    }

    public void stop(Runnable runnable) {
        if (runnable == null)
            isStart = false;
        else
            stopAnimator(runnable);
        invalidate();
    }

    public boolean isStart() {
        return isStart;
    }

    private void startAnimator() {
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(this, "scaleX", 0.8f, 1);
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(this, "scaleY", 0.8f, 1);
        scaleXAnimator.setDuration(200);
        scaleXAnimator.setInterpolator(new LinearInterpolator());
        scaleYAnimator.setDuration(200);
        scaleYAnimator.setInterpolator(new LinearInterpolator());
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleXAnimator, scaleYAnimator);
        animatorSet.start();
    }

    private void stopAnimator(final Runnable runnable) {
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(this, "scaleX", 1, 0);
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(this, "scaleY", 1, 0);
        scaleXAnimator.setDuration(300);
        scaleXAnimator.setInterpolator(new LinearInterpolator());
        scaleYAnimator.setDuration(300);
        scaleYAnimator.setInterpolator(new LinearInterpolator());
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleXAnimator, scaleYAnimator);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isStart = false;
                if (runnable != null)
                    runnable.run();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorSet.start();
    }


    public int dpToPx(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, context.getResources().getDisplayMetrics());
    }
}
