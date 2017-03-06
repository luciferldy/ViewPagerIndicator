package com.luciferldy.viewpagerindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

import java.util.ArrayList;

/**
 * Created by Lucifer on 2017/3/5.
 * E-mail: lian_dy@foxmail.com
 */

public class ViewPagerIndicator extends View {

    private static final String LOG_TAG = ViewPagerIndicator.class.getSimpleName();
    private int mWidth;
    private int mHeight;
    private Paint mPaint;
    private ArrayList<PointF> mPoints;
    private float dotRadius; // indicator radius
    private int dotDistance; // distance in indicator
    private int dotCount; // indicator's count
    private int normalColor;
    private int selectedColor;
    private PointF cursorPointF;

    public ViewPagerIndicator(Context context) {
        this(context, null);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.i(LOG_TAG, "IndicatorView constructor with two arguments.");
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ViewPagerIndicator, 0, 0);
        try {
            dotRadius = a.getDimensionPixelOffset(R.styleable.ViewPagerIndicator_indicator_size, 20) * 0.5f;
            dotDistance = a.getDimensionPixelOffset(R.styleable.ViewPagerIndicator_indicator_distance, 20);
            dotCount = a.getInt(R.styleable.ViewPagerIndicator_indicator_count, 5);
            normalColor = a.getColor(R.styleable.ViewPagerIndicator_indicator_normal_color, getResources().getColor(R.color.md_white));
            selectedColor = a.getColor(R.styleable.ViewPagerIndicator_indicator_selected_color, getResources().getColor(R.color.md_grey_700));
        } finally {
            a.recycle();
        }

        getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                Log.i(LOG_TAG, "onPreDraw");
                getViewTreeObserver().removeOnPreDrawListener(this);
                initPoints();
                return true;
            }
        });

        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mWidth = w;
        this.mHeight = h;
        Log.i(LOG_TAG, "width: " + mWidth + ", height: " + mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i(LOG_TAG, "onDraw point size = " + mPoints.size());
        if (mPaint == null)
            return;
        mPaint.setColor(normalColor);
        for (PointF pointF : mPoints) {
            canvas.drawCircle(pointF.x, pointF.y, dotRadius, mPaint);
        }
        mPaint.setColor(selectedColor);
        canvas.drawCircle(cursorPointF.x, cursorPointF.y, dotRadius, mPaint);
    }

    /**
     * Set the current position
     * @param position index of position
     */
    public void setCurrentPosition(int position) {
        if (position >= mPoints.size())
            return;
        cursorPointF.x = mPoints.get(position).x; // 变动 x 就好
        invalidate();
    }

    /**
     * move indicator
     * @param position index of position
     * @param positionOffset offset percent float between 0 and 1
     * @param positionOffsetPixels offset pixels
     */
    public void moveIndicator(int position, float positionOffset, float positionOffsetPixels) {
        Log.i(LOG_TAG, "moveIndicator position = " + position + ", point size = " + mPoints.size());
        if (position >= mPoints.size()) {
//            throw new IllegalArgumentException();
            invalidate();
            return;
        }
        cursorPointF.x = mPoints.get(position).x + positionOffset * (dotDistance + dotRadius * 2);
        invalidate();
    }

    /**
     * init
     */
    private void init() {
        Log.i(LOG_TAG, "init");
        mPoints = new ArrayList<>();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
    }

    private void initPoints() {
        Log.i(LOG_TAG, "initPoints dotCount = " + dotCount);
        mPoints.clear();
        float left = (mWidth - dotRadius * 2 * dotCount - (dotCount - 1) * dotDistance) * 0.5f;
        for (int i = 0; i < dotCount; i++) {
            PointF pointF = new PointF();
            pointF.x = left + dotRadius * 2 * i + dotDistance * i + dotRadius;
            pointF.y = mHeight - dotRadius - 2; // 减去2 pixel
            mPoints.add(pointF);
        }
        cursorPointF = new PointF();
        cursorPointF.x = mPoints.get(0).x;
        cursorPointF.y = mPoints.get(0).y;
        Log.i(LOG_TAG, "initPoints point size = " + mPoints.size());
    }
}
