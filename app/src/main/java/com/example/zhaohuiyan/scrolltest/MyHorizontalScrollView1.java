package com.example.zhaohuiyan.scrolltest;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.Scroller;

/**
 * 以前找都是针对Activity的，将Activty设置进入里面，这个比较简单，只要将View设置进来就行
 */
public class MyHorizontalScrollView1 extends HorizontalScrollView {
    private GestureDetector mGestureDetector;
    private View mView;
    private Context mContext;
    private RecyclerView rightRecyclerView;
    private RecyclerView rightTitleRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private int[] points;

    public MyHorizontalScrollView1(Context context) {
        super(context);
        init(context);
    }

    public MyHorizontalScrollView1(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        this.mContext = context;
        mGestureDetector = new GestureDetector(context, new YScrollDetector());
        setFadingEdgeLength(0);
    }

    //scrollto和scrollby之后调用的方法
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        //这句的意思就是我滚到哪里，设置进来的空间就滚到哪里

        if (mView != null) {
            mView.scrollTo(l,t);
        }
    }

    private float oldX, oldY, newX, newY;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                oldX = e.getX();
                oldY = e.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                newX = e.getX();
                newY = e.getY();
                if (Math.abs(newX - oldX) < Math.abs(newY - oldY)) {//水平方向移动距离<垂直方向的移动距离
                    return false;
                } else if (rightRecyclerView != null &&!rightRecyclerView.canScrollVertically(-1) && (Math.abs(newX - oldX) < 125)) {
                    return false;
                } else {
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return super.onInterceptTouchEvent(e);
    }
    private int lastX = 0;
    private int touchEventId = -9983761;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            View scroller = (View) msg.obj;
            if (msg.what == touchEventId) {
                if (lastX == scroller.getScrollX()) {
                    handleStop(scroller);
                } else {
                    handler.sendMessageDelayed(handler.obtainMessage(touchEventId, scroller), 5);
                    lastX = scroller.getScrollX();
                }
            }
        }
    };

    private void handleStop(Object view) {
        HorizontalScrollView scroller = (HorizontalScrollView) view;
        lastX = scroller.getScrollX();
        Log.e("horizotal4","lastx=" + lastX);
        int snap = calculateDistanceToFinalSnap();
        smoothScrollTo(snap,0);
        Log.e("horizotal4","snap=" + snap);

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            handler.sendMessageDelayed(handler.obtainMessage(touchEventId, this), 5);
        }
        return super.onTouchEvent(event);
//        return mGestureDetector.onTouchEvent(event);
    }

    private float mXMove;

    /**
     * 上次触发ACTION_MOVE事件时的屏幕坐标
     */
    private float mXLastMove;
    class YScrollDetector extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            int fisrt = linearLayoutManager.findFirstVisibleItemPosition();
            View view = linearLayoutManager.getChildAt(fisrt);
            /*Log.e("horizotal3","first=" + fisrt + ": width=" + view.getMeasuredWidth());
            int targetIndex = (getScrollX() + view.getMeasuredWidth() / 2) / view.getMeasuredWidth();
            int dx = targetIndex * view.getMeasuredWidth() - getScrollX();
            // 第二步，调用startScroll()方法来初始化滚动数据并刷新界面
            mScroller.startScroll(getScrollX(), 0, dx, 0);
            invalidate();*/
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.e("horizotal3","oldx=" + oldX + ": e2.getx=" + e2.getX());
            Log.e("horizotal3",e1==null?"null":"!=null");
            switch (e2.getAction()) {
                case MotionEvent.ACTION_MOVE:
                    mXMove = e2.getX();
                    int scrolledX = (int) (mXMove - mXLastMove);
                    scrollBy(-scrolledX, 0);
                    mXLastMove = mXMove;
                    break;
            }
            return true;
        }
    }

    /**
     * 设置联动的view
     *
     * @param view
     */
    public void setScrollView(View view) {
        mView = view;
    }

    public void setRightRecyclerView(RecyclerView rightRecyclerView) {
        this.rightRecyclerView = rightRecyclerView;
    }
    public void setRightTitleRecyclerView(RecyclerView rightRecyclerView) {
        this.rightTitleRecyclerView = rightRecyclerView;
        linearLayoutManager = (LinearLayoutManager) rightRecyclerView.getLayoutManager();
    }
    public void setRightPoint(int points[]){
        this.points = points;
    }

    public int calculateDistanceToFinalSnap(){
        int targetIndex=0;
        int snap=Math.abs(lastX - points[0]);
        for (int i=0;i<points.length-2;i++){
            Log.e("horizotal4","snap=" + snap + ": "+ Math.abs(lastX - points[i+1]));
            if (snap > Math.abs(lastX - points[i+1])){
                snap = Math.abs(lastX - points[i+1]);
                targetIndex = i+1;
            }
        }
        return points[targetIndex];
    }

}