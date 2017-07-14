package com.example.zhaohuiyan.scrolltest;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Wode9 on 2017/7/12.
 */

public class FullyLinearLayoutManager1 extends LinearLayoutManager {

    private static final String TAG = FullyLinearLayoutManager1.class.getSimpleName();

    public FullyLinearLayoutManager1(Context context) {
        super(context);
    }


    public FullyLinearLayoutManager1(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    private int[] mMeasuredDimension = new int[2];

    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state,
                          int widthSpec, int heightSpec) {

        int width = View.MeasureSpec.getSize(widthSpec);
        int height = 36600;
       /* try {
            View view = recycler.getViewForPosition(0);
            view.measure(0,0);
            height = view.getMeasuredHeight() * getItemCount();
        }catch (Exception e){
            Log.e("mmm","e=" + e.getMessage().toString());
        }*/

        setMeasuredDimension(width, height);
    }


}
