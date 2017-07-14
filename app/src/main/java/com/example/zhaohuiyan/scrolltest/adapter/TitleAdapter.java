package com.example.zhaohuiyan.scrolltest.adapter;

import android.app.Activity;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.zhaohuiyan.scrolltest.MainActivity;
import com.example.zhaohuiyan.scrolltest.R;

import java.util.List;


public class TitleAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

	private Activity activity;
	public TitleAdapter(int layoutResId, List<String> data, Activity activity) {
		super(layoutResId, data);
		this.activity = activity;
	}

	@Override
	protected void convert(BaseViewHolder helper, String item) {
		helper.setText(R.id.tv1,item);
		helper.getView(R.id.tv1).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				((MainActivity)activity).sortList();
			}
		});
	}
}
