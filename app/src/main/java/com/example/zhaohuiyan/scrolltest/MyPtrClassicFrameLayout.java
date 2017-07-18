package com.example.zhaohuiyan.scrolltest;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import in.srain.cube.views.ptr.PtrFrameLayout;

public class MyPtrClassicFrameLayout extends PtrFrameLayout {

    private PtrClassicDefaultHeader_my mPtrClassicHeader;

    public MyPtrClassicFrameLayout(Context context) {
        super(context);
        initViews();
    }

    public MyPtrClassicFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    public MyPtrClassicFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initViews();
    }

    private void initViews() {
        mPtrClassicHeader = new PtrClassicDefaultHeader_my(getContext());
        setHeaderView(mPtrClassicHeader);
        addPtrUIHandler(mPtrClassicHeader);
    }

    public PtrClassicDefaultHeader_my getHeader() {
        return mPtrClassicHeader;
    }

    /**
     * Specify the last update time by this key string
     *
     * @param key
     */
    public void setLastUpdateTimeKey(String key) {
        if (mPtrClassicHeader != null) {
            mPtrClassicHeader.setLastUpdateTimeKey(key);
        }
    }

    /**
     * Using an object to specify the last update time.
     *
     * @param object
     */
    public void setLastUpdateTimeRelateObject(Object object) {
        if (mPtrClassicHeader != null) {
            mPtrClassicHeader.setLastUpdateTimeRelateObject(object);
        }
    }
    private float oldX,oldY,newX,newY;
    @Override
    public boolean dispatchTouchEvent(MotionEvent e) {
        switch (e.getAction()){
            case MotionEvent.ACTION_DOWN:
                oldX = e.getX();
                oldY = e.getY();
                Log.e("mmmmmm","ptr:dispatchTouchEvent:ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                newX = e.getX();
                newY = e.getY();
                if (Math.abs(newX - oldX) > Math.abs(newY - oldY)){//水平方向移动距离>垂直方向的移动距离 进行事件分发
                    return dispatchTouchEventSupper(e);
                }else {//否则，自己消费
                    super.dispatchTouchEvent(e);
                }
                oldX = newX;
                oldY = newY;
                Log.e("mmmmmm","ptr:dispatchTouchEvent:ACTION_MOVE");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e("mmmmmm","ptr:dispatchTouchEvent:ACTION_CANCEL");
                break;
        }
        return super.dispatchTouchEvent(e);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e("mmmmmm","ptr:ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("mmmmmm","ptr:ACTION_MOVE");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e("mmmmmm","ptr:ACTION_CANCEL");
                break;
        }
        return super.onTouchEvent(event);
    }
}
