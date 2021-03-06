package com.example.zhaohuiyan.scrolltest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zhaohuiyan.scrolltest.adapter.LeftAdapter;
import com.example.zhaohuiyan.scrolltest.adapter.RightAdapter;
import com.example.zhaohuiyan.scrolltest.adapter.TitleAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity1 extends AppCompatActivity implements View.OnClickListener {
     @BindView(R.id.tv1)
     TextView tv1;
     @BindView(R.id.tv2)
     TextView tv2;
     @BindView(R.id.tv3)
     TextView tv3;
     @BindView(R.id.tv4)
     TextView tv4;
     @BindView(R.id.tv5)
     TextView tv5;
     @BindView(R.id.tv6)
     TextView tv6;
     @BindView(R.id.tv7)
     TextView tv7;
    @BindView(R.id.ll_ver)
    LinearLayout ll_ver;
   /* @BindView(R.id.title_horsv)
    MyHorizontalScrollView titleHorsv;*/
    @BindView(R.id.left_container_recyclerView)
    RecyclerView leftContainerRecyclerView;
    @BindView(R.id.right_container_recyclerView)
    RecyclerView rightContainerRecyclerView;
    /*@BindView(R.id.content_horsv)
    MyHorizontalScrollView contentHorsv;*/
    /*@BindView(R.id.right_title_recyclerView)
    RecyclerView rightTitleRecyclerView;*/

    //左侧固定一列数据适配
    private List<Stock> datas = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    private LeftAdapter leftAdapter;
    private RightAdapter rightAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        ButterKnife.bind(this);

        findView();

        initView();
        initData();
    }

    private float oX,oY;
    private void findView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setAutoMeasureEnabled(true);

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
        linearLayoutManager1.setAutoMeasureEnabled(true);

        leftContainerRecyclerView.setLayoutManager(linearLayoutManager);
        rightContainerRecyclerView.setLayoutManager(linearLayoutManager1);

        leftContainerRecyclerView.setNestedScrollingEnabled(false);
        rightContainerRecyclerView.setNestedScrollingEnabled(false);

        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);
        tv5.setOnClickListener(this);
        tv6.setOnClickListener(this);
        tv7.setOnClickListener(this);
    }



    private void initView() {
        // 设置两个水平控件的联动
//        titleHorsv.setScrollView(contentHorsv);
//        contentHorsv.setScrollView(titleHorsv);
        //添加左侧数据
        leftAdapter = new LeftAdapter(R.layout.layout_left_item, datas);
        leftContainerRecyclerView.setAdapter(leftAdapter);

        // 添加右边内容数据
        rightAdapter = new RightAdapter(R.layout.layout_right_item_a, datas);
        rightContainerRecyclerView.setAdapter(rightAdapter);


    }


    //初始化数据源
    private void initData() {
        for (int i = 0; i < 200; i++) {
            datas.add(new Stock("风华基金" + i, i + "", i + "", i + "", i + "", i + "", i + "", i + ""));
        }
        for (int i = 0; i <= 7; i++) {
            titles.add("title" + i);
        }
        leftAdapter.setNewData(datas);
        rightAdapter.setNewData(datas);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv1:
            case R.id.tv2:
            case R.id.tv3:
            case R.id.tv4:
            case R.id.tv5:
            case R.id.tv6:
            case R.id.tv7:
                sortList();
                break;
        }
    }

    private boolean isSort;

    public void sortList() {

                Collections.sort(datas, new Comparator<Stock>() {
                    @Override
                    public int compare(Stock lhs, Stock rhs) {
                        Integer left = Integer.parseInt(lhs.getTxt1());
                        Integer right = Integer.parseInt(rhs.getTxt1());
                        if (isSort) {
                            return left.compareTo(right);
                        } else {
                            return right.compareTo(left);
                        }
                    }
                });

                isSort = !isSort;
//                leftAdapter.notifyItemRangeChanged(0,8);
//                rightAdapter.notifyItemRangeChanged(0,8);
                leftAdapter.notifyDataSetChanged();
                rightAdapter.notifyDataSetChanged();



    }

}
