package com.example.zhaohuiyan.scrolltest;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingParent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Wode9 on 2017/7/14.
 */

public class VerticalScroll extends LinearLayout implements NestedScrollingParent{

    private View child;
    public VerticalScroll(Context context) {
        super(context);
        init();
    }

    public VerticalScroll(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public VerticalScroll(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        setOrientation(HORIZONTAL);
    }
    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return true;
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        scrollBy(0, dy);
//        consumed[1] = dy;
    }
    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
//        fling((int) velocityY);
        return false;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        child = findViewById(R.id.left_container_recyclerView);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e("mmm","hitht=" + child.getMeasuredHeight());
        setMeasuredDimension(getMeasuredWidth(), 9000);
    }
}
