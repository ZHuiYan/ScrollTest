package com.example.zhaohuiyan.scrolltest;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;

/**
 * 以前找都是针对Activity的，将Activty设置进入里面，这个比较简单，只要将View设置进来就行
 */
public class MyHorizontalScrollView extends HorizontalScrollView {

    private View mView;
    private RecyclerView recyclerView;
    private MyPtrClassicFrameLayout ptrRefresh;//首页的滑动控件

    public MyHorizontalScrollView(Context context) {
        super(context);
    }

    public MyHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setPtrRefresh(MyPtrClassicFrameLayout ptrRefresh){
        this.ptrRefresh = ptrRefresh;
    }
    @Override
    public void computeScroll() {
        super.computeScroll();
        if (ptrRefresh != null)
            ptrRefresh.setPullToRefresh(true);
    }

    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        //这句的意思就是我滚到哪里，设置进来的空间就滚到哪里
        if (mView != null) {
            mView.scrollTo(l, t);
        }
        if (ptrRefresh != null)
            ptrRefresh.setPullToRefresh(false);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e("mmmmmm","ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("mmmmmm","ACTION_MOVE");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e("mmmmmm","ACTION_CANCEL");
                break;
        }
        return super.onTouchEvent(ev);

    }

    /**
     * 设置联动的view
     *
     * @param view
     */
    public void setScrollView(View view) {
        mView = view;
    }

    /**
     * 设置横向的recycleView
     */
    public void setHRecycleView(RecyclerView recycleView){
        this.recyclerView = recycleView;
    }
}