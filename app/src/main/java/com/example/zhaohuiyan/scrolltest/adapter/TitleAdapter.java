package com.example.zhaohuiyan.scrolltest.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.example.zhaohuiyan.scrolltest.MainActivity;
import com.example.zhaohuiyan.scrolltest.R;
import com.example.zhaohuiyan.scrolltest.Title;

import java.util.List;


public class TitleAdapter extends BaseQuickAdapter<Title,BaseViewHolder> {

	private Activity activity;
	public TitleAdapter(int layoutResId, List<Title> data, Activity activity) {
		super(layoutResId, data);
		this.activity = activity;
		setMultiTypeDelegate(new MultiTypeDelegate<Title>() {
			@Override
			protected int getItemType(Title title) {
				return title.getType();
			}
		});
		getMultiTypeDelegate().registerItemType(Title.MIN,R.layout.layout_right_tab_a)
				.registerItemType(Title.MAX,R.layout.layout_right_tab_a_max);
	}

	@Override
	protected void convert(BaseViewHolder helper, Title item) {
		helper.setText(R.id.tv_title,item.getName());
		ImageView ivShow = helper.getView(R.id.iv_show);
		if (item.isShow()){
			ivShow.setVisibility(View.VISIBLE);
			if (item.isCrease()){
				ivShow.setImageResource(R.mipmap.shenglan);
			}else {
				ivShow.setImageResource(R.mipmap.jianglan);
			}
		}else {
			ivShow.setVisibility(View.GONE);
		}
	}
}
