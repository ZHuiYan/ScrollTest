package com.example.zhaohuiyan.scrolltest.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zhaohuiyan.scrolltest.R;
import com.example.zhaohuiyan.scrolltest.Title;
import com.example.zhaohuiyan.scrolltest.UtilTools;

import java.util.List;


public class TitleGridViewAdapter extends BaseAdapter {
    private List<Title> titles;
    private Context context;
    private TitleHolder titleHolder;

    public TitleGridViewAdapter(Context context, List<Title> titles) {
        this.context = context;
        this.titles = titles;
    }

    public void setTitles(List<Title> titles){
        if (titles == null) return;
        this.titles.clear();
        this.titles.addAll(titles);
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return titles == null ? 0 : titles.size();
    }

    @Override
    public Title getItem(int position) {
        return titles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            titleHolder = new TitleHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_right_tab_a, null);
            titleHolder.llShow = (LinearLayout) convertView.findViewById(R.id.llShow);
            titleHolder.tvName = (TextView) convertView.findViewById(R.id.tv_title);
            titleHolder.ivShow = (ImageView) convertView.findViewById(R.id.iv_show);
            convertView.setTag(titleHolder);
        } else {
            titleHolder = (TitleHolder) convertView.getTag();
        }
        Title item = getItem(position);
        Log.e("sssss","name=" + item.getName());
       /* RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) titleHolder.llShow.getLayoutParams();
        if (item.getType() == 1){
            params.width = (int) UtilTools.dpToPx(context,120f);
        }else {
            params.width = (int) UtilTools.dpToPx(context,94f);
        }
        titleHolder.llShow.setLayoutParams(params);*/
        if (item.isShow()) {
            titleHolder.ivShow.setVisibility(View.VISIBLE);
            if (item.isCrease()) {
                titleHolder.ivShow.setImageResource(R.mipmap.shenglan);
            } else {
                titleHolder.ivShow.setImageResource(R.mipmap.jianglan);
            }
        } else {
            titleHolder.ivShow.setVisibility(View.GONE);
        }
        return convertView;
    }

    class TitleHolder {
        TextView tvName;
        ImageView ivShow;
        LinearLayout llShow;
    }

	/*private Activity activity;
    public TitleGridViewAdapter(int layoutResId, List<Title> data, Activity activity) {
		super(layoutResId, data);
		this.activity = activity;
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
	}*/
}
