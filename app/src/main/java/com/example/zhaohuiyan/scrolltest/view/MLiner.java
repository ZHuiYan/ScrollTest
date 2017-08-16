package com.example.zhaohuiyan.scrolltest.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import android.support.v4.view.NestedScrollingParent;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by zhaohuiyan on 2017/7/13.
 */

public class MLiner extends LinearLayout implements NestedScrollingParent{
    private GestureDetector gestureDetector;
    private RecyclerView rightContainerRecyclerView;
    public MLiner(Context context) {
        super(context);
        init(context);
    }

    public MLiner(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MLiner(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context mContext){
        setOrientation(HORIZONTAL);
        gestureDetector = new GestureDetector(mContext,new MyGestureListener());
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("mmm", "onTouchEvent");
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        Log.e("mmm", "onStartNestedScroll");
        return true;
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        Log.e("mmm", "onNestedPreScroll");
        if (dx > dy){
            consumed[0] = dx;
        }
        super.onNestedPreScroll(target, dx, dy, consumed);
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        Log.e("mmm", "onNestedFling");
        if (velocityX > velocityY){
            return true;
        }
        return super.onNestedFling(target, velocityX, velocityY, consumed);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return true;
    }

    public void setRecy(RecyclerView left,RecyclerView right){
        this.rightContainerRecyclerView = right;
    }
    public class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDown(MotionEvent e) {
            Log.e("mmm", "ondown");
            return true;
        }

        @Override
        public void onShowPress(MotionEvent e) {
            Log.e("mmm", "onShowPress");
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Log.e("mmm", "onSingleTapUp");
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            float dx = Math.abs(e1.getRawX() - e2.getRawX());
            float dy = Math.abs(e1.getRawY() - e2.getRawY());
            Log.e("mmm", "onScroll");
            if (dx > dy){
                scrollBy(0, (int) distanceY);

                return true;
            }else {
                return false;
            }

        }

        @Override
        public void onLongPress(MotionEvent e) {
            Log.e("mmm", "onLongPress");
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.e("mmm", "onFling");
            return true;
        }
    }
}
