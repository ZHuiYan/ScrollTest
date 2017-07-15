package com.example.zhaohuiyan.scrolltest.adapter;

import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.zhaohuiyan.scrolltest.R;
import com.example.zhaohuiyan.scrolltest.Stock;

import java.util.List;

public class RightAdapter extends BaseQuickAdapter<Stock,RightAdapter.Holder> {


    public RightAdapter(int layoutResId, List<Stock> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(Holder helper, Stock item) {
        helper.textView1.setText(item.getTxt1());
        helper.textView2.setText(item.getTxt2());
        helper.textView3.setText(item.getTxt3());
        helper.textView4.setText(item.getTxt4());
        helper.textView5.setText(item.getTxt5());
        helper.textView6.setText(item.getTxt6());
        helper.textView7.setText(item.getTxt7());
    }
   public class Holder extends BaseViewHolder{
        TextView textView1;
        TextView textView2;
        TextView textView3;
        TextView textView4;
        TextView textView5;
        TextView textView6;
        TextView textView7;
        public Holder(final View view) {
            super(view);
            textView1 = (TextView) view.findViewById(R.id.right_item_textview0);
            textView2 = (TextView) view.findViewById(R.id.right_item_textview1);
            textView3 = (TextView) view.findViewById(R.id.right_item_textview2);
            textView4 = (TextView) view.findViewById(R.id.right_item_textview3);
            textView5 = (TextView) view.findViewById(R.id.right_item_textview4);
            textView6 = (TextView) view.findViewById(R.id.right_item_textview5);
            textView7 = (TextView) view.findViewById(R.id.right_item_textview6);

        }
    }
}
