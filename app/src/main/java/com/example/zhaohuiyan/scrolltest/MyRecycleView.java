package com.example.zhaohuiyan.scrolltest;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by Wode9 on 2017/7/11.
 */

public class MyRecycleView extends RecyclerView {

    private RecyclerView mView;

    public MyRecycleView(Context context) {
        super(context);
    }

    public MyRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthSpec, expandSpec);

    }
    /*private float ox,oy;
    @Override
    public boolean onTouchEvent(MotionEvent e) {
        switch (e.getAction()){
            case MotionEvent.ACTION_DOWN:
                ox = e.getX();
                oy = e.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float x = e.getX();
                float y = e.getY();
                Log.e("mmmm","x=" + Math.abs(ox - x) );
                Log.e("mmmm","y=" + Math.abs(oy - y) );
                if (Math.abs(ox - x) > Math.abs(oy - y)){
                    requestDisallowInterceptTouchEvent(false);
                }else {
                    return super.onTouchEvent(e);
                }
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return super.onTouchEvent(e);
    }

    public void setView(RecyclerView mView){
        this.mView = mView;
    }*/
}
