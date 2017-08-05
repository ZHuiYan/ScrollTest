package com.example.zhaohuiyan.scrolltest;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;


/**
 * 之前都是写在Activity中，感觉代码好多，还是封装到这里好
 */
public class UtilTools {

    /**
     * 计算ListView的高度
     *
     * @param listView
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        /**
         * getAdapter这个方法主要是为了获取到ListView的数据条数，所以设置之前必须设置Adapter
         */
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {

            View listItem = listAdapter.getView(i, null, listView);
            //计算每一项的高度
            listItem.measure(0, 0);
            //总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        //真正的高度需要加上分割线的高度
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    public static void setGridViewWidthBasedOnChildren(GridView gridView) {
        /**
         * getAdapter这个方法主要是为了获取到ListView的数据条数，所以设置之前必须设置Adapter
         */
        ListAdapter listAdapter = gridView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalWidth = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {

            View listItem = listAdapter.getView(i, null, gridView);
            //计算每一项的高度
            listItem.measure(0, 0);
            //总高度
            totalWidth += listItem.getMeasuredWidth();
        }
        Log.e("sssss","width=" + totalWidth);
        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        //真正的高度需要加上分割线的高度
        params.width = (int) (totalWidth - dpToPx(gridView.getContext(),20f));
        gridView.setLayoutParams(params);
    }
    public static void setListViewHeightBasedOnChildren1(RecyclerView listView) {
        /**
         * getAdapter这个方法主要是为了获取到ListView的数据条数，所以设置之前必须设置Adapter
         */
       LinearLayoutManager linearLayoutManager = (LinearLayoutManager) listView.getLayoutManager();
        if (linearLayoutManager == null) {
            return;
        }
//        Log.e("mmm","count=" + linearLayoutManager.getChildCount());
//        Log.e("mmm","count1=" + linearLayoutManager.getItemCount());
        int totalHeight = (int) dpToPx(listView.getContext(),56 * linearLayoutManager.getItemCount());
      /*  int totalHeight = 0;
        try {
            for (int i = 0, len = linearLayoutManager.getItemCount(); i < len; i++) {

                View listItem = linearLayoutManager.findViewByPosition(i);
                //计算每一项的高度
                listItem.measure(0, 0);
                //总高度
                totalHeight += listItem.getMeasuredHeight();
            }
        }catch (Exception e){
            Log.e("mmm","e=" + e.getMessage().toString());
        }*/
        Log.e("mmm","totalHeight=" + totalHeight);

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        //真正的高度需要加上分割线的高度
        params.height = totalHeight ;
        listView.setLayoutParams(params);
    }
    public static float dpToPx(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return dpValue * scale + 0.5f;
    }
    public static int getScreenWidth(Activity activity) {
        DisplayMetrics displaysMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displaysMetrics);
        return displaysMetrics.widthPixels;
    }

    public static int getScreenHeight(Activity activity) {
        DisplayMetrics displaysMetrics = new DisplayMetrics();

        activity.getWindowManager().getDefaultDisplay().getMetrics(displaysMetrics);

        return displaysMetrics.heightPixels;

    }
}
