package com.example.zhaohuiyan.scrolltest;

import android.content.Context;
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
public class MyHorizontalScrollView extends HorizontalScrollView {
    private GestureDetector mGestureDetector;
    private View mView;
    private Context mContext;
    private RecyclerView rightRecyclerView;
    private RecyclerView rightTitleRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private Scroller mScroller;

    public MyHorizontalScrollView(Context context) {
        super(context);
        init(context);
    }

    public MyHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context){
        this.mContext = context;
        mScroller = new Scroller(context);
        mGestureDetector = new GestureDetector(context, new YScrollDetector());
        setFadingEdgeLength(0);
    }

    @Override
    public void computeScroll() {
        Log.e("horizotal","computeScroll:");
        if (mScroller.computeScrollOffset()){
            scrollTo(mScroller.getCurrX(),0);
            postInvalidate();
        }
        super.computeScroll();
    }


    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        //这句的意思就是我滚到哪里，设置进来的空间就滚到哪里
        if (mView != null) {
//            mView.scrollTo(l, t);
            mView.scrollBy(l - oldl,t - oldt);
        }
        if (linearLayoutManager != null){
            int first = linearLayoutManager.findFirstVisibleItemPosition();
            int last = linearLayoutManager.findLastVisibleItemPosition();
            float left = rightTitleRecyclerView.getMeasuredWidth();
//            rightTitleRecyclerView.smoothScrollToPosition(first);
            Log.e("horizotal22","onScrollChanged:" + "first=" + first + ":last=" + last + " :left=" + left);
//            Log.e("horizotal22","onScrollChanged:" + "l=" + l + ": t=" + t );
        }

    }

    private float oldX, oldY, newX, newY;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        Log.e("horizotal","onInterceptTouchEvent:");
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
        Log.e("horizotal","onTouchEvent:");
        return super.onTouchEvent(e);
//        return mGestureDetector.onTouchEvent(e);
    }
    class YScrollDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.e("horizotal","onFling:");
            if (e1 != null && e2 != null && (Math.abs(e1.getX() - e2.getX()) > Math.abs(e1.getY() - e2.getY())))
                return true;
            return super.onFling(e1, e2, velocityX, velocityY);
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.e("horizotal","onScroll:" + "distancex=" + distanceX + ":distancey=" + distanceY);
            if (Math.abs(distanceX) > Math.abs(distanceY)){
                scrollBy((int) distanceX,0);
                return true;
            }
            return super.onScroll(e1,e2,distanceX,distanceY);
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
    public void setRightTitleRecyclerView(RecyclerView rightTitleRecyclerView){
        this.rightTitleRecyclerView = rightTitleRecyclerView;
        linearLayoutManager = (LinearLayoutManager) rightTitleRecyclerView.getLayoutManager();
    }
}