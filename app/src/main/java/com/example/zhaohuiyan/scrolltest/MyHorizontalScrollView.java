package com.example.zhaohuiyan.scrolltest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;

/**
 * 以前找都是针对Activity的，将Activty设置进入里面，这个比较简单，只要将View设置进来就行
 */
public class MyHorizontalScrollView extends HorizontalScrollView {
    private GestureDetector mGestureDetector;
    private View mView;
    private Context mContext;
    private RecyclerView rightRecyclerView;

    public MyHorizontalScrollView(Context context) {
        super(context);
        this.mContext = context;
        mGestureDetector = new GestureDetector(context, new YScrollDetector());
        setFadingEdgeLength(0);
    }

    public MyHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        mGestureDetector = new GestureDetector(context, new YScrollDetector());
        setFadingEdgeLength(0);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
    }


    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        //这句的意思就是我滚到哪里，设置进来的空间就滚到哪里
        if (mView != null) {
            mView.scrollTo(l, t);
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

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return super.onTouchEvent(e);
    }

    /**
     * 如果竖向滑动距离<横向距离，执行横向滑动，否则竖向。如果是ScrollView，则'<'换成'>'
     */
    class YScrollDetector extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return super.onFling(e1, e2, velocityX, velocityY);
        }

       /* @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            if (rightRecyclerView != null){
                if (rightRecyclerView.canScrollVertically(-1)){//如果
                    Logger.e("mmmmmmm","true");
                }else {
                    Logger.e("mmmmmmm","false");
                }
            }else {
                if (Math.abs(distanceY) < Math.abs(distanceX)) {
                    return true;
                }else {

                }
                Logger.e("mmmmmmm","rightRecyclerView==null");
            }
            if (Math.abs(distanceY) < Math.abs(distanceX)) {
                Logger.e("mmmmmmmm","水平");
                return true;
            }else {
                Logger.e("mmmmmmmm","竖直");
            }
            return false;
        }*/
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
}