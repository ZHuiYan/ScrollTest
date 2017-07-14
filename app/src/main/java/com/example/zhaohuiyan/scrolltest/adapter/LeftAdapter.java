package com.example.zhaohuiyan.scrolltest.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.zhaohuiyan.scrolltest.R;
import com.example.zhaohuiyan.scrolltest.Stock;

import java.util.List;


public class LeftAdapter extends BaseQuickAdapter<Stock,BaseViewHolder> {

	public LeftAdapter(int layoutResId, List<Stock> data) {
		super(layoutResId, data);
	}

	@Override
	protected void convert(BaseViewHolder helper, Stock item) {
		helper.setText(R.id.left_container_textview0,item.getName());
	}

}
