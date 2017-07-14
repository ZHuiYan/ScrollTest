package com.example.zhaohuiyan.scrolltest;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.zhaohuiyan.scrolltest.adapter.LeftAdapter;
import com.example.zhaohuiyan.scrolltest.adapter.RightAdapter;
import com.example.zhaohuiyan.scrolltest.adapter.TitleAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
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
    @BindView(R.id.left_container_recyclerView)
    RecyclerView leftContainerRecyclerView;
    @BindView(R.id.right_container_recyclerView)
    RecyclerView rightContainerRecyclerView;
    @BindView(R.id.hs_content)
    MyHorizontalScrollView hsContent;
    @BindView(R.id.hs_title)
    MyHorizontalScrollView hsTtitle;

    //左侧固定一列数据适配
    private List<Stock> datas = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    private LeftAdapter leftAdapter;
    private RightAdapter rightAdapter;
    private TitleAdapter titleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        findView();

        initView();
        initData();
    }

    private LinearLayoutManager layoutManager;
    private LinearLayoutManager layoutManager1;

    private float oldX;
    private float oldY;

    private float newX;
    private float newY;
    private boolean isScroll;
    private void findView() {
        layoutManager = new LinearLayoutManager(this);
        layoutManager1 = new LinearLayoutManager(this);

        leftContainerRecyclerView.setLayoutManager(layoutManager);
        rightContainerRecyclerView.setLayoutManager(layoutManager1);

        hsTtitle.setScrollView(hsContent);
        hsContent.setScrollView(hsTtitle);

        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);
        tv5.setOnClickListener(this);
        tv6.setOnClickListener(this);
        tv7.setOnClickListener(this);


        leftContainerRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.e("mmm", "leftContainerRecyclerView:getY=" + leftContainerRecyclerView.getScrollY() + ":dy=" + dy);
                if (recyclerView.getScrollState() != RecyclerView.SCROLL_STATE_IDLE) {
                    if (rightContainerRecyclerView.getScrollState() != RecyclerView.SCROLL_STATE_IDLE) {
                        rightContainerRecyclerView.stopScroll();
                    }
                    rightContainerRecyclerView.scrollBy(dx, dy);
                }
            }
        });
        rightContainerRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.e("mmm", "onScrollStateChanged：newState=" + newState);
               /* if (newState == RecyclerView.SCROLL_STATE_IDLE){
                    isScroll = false;
                }else {
                    isScroll = true;
                }*/
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                Log.e("mmm", "rightContainerRecyclerView:" + ":dy=" + dy);
                if (recyclerView.getScrollState() != RecyclerView.SCROLL_STATE_IDLE) {
                    if (leftContainerRecyclerView.getScrollState() != RecyclerView.SCROLL_STATE_IDLE) {
                        leftContainerRecyclerView.stopScroll();
                    }
                    leftContainerRecyclerView.scrollBy(dx, dy);
                }
            }
        });
//        layoutManager1.se
//        rightContainerRecyclerView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//                switch (event.getAction()){
//                    case MotionEvent.ACTION_DOWN:
//                        oldX = event.getX();
//                        oldY = event.getY();
//
//                        Log.e("mmmm","ACTION_DOWN");
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        newX = event.getX();
//                        newY = event.getY();
//                        Log.e("mmmm","isScroll=" + isScroll);
//                        Log.e("mmmm","ACTION_MOVE:" + "x=" + Math.abs(newX - oldX) + ":y=" + Math.abs(newY - newY));
//                        if (!isScroll){
//                            if (Math.abs(newX - oldX) > Math.abs(newY - oldY)){
//                                rightContainerRecyclerView.scrollBy((int)(newX - oldX),0);
//                            }else {
//                                rightContainerRecyclerView.scrollBy(0,(int)(oldY - newY));
//                            }
//                        }
//                       /*if (event.getX() > event.getY()){
//                            rightContainerRecyclerView.scrollBy((int) event.getX(),0);
//                        }*/
//                        break;
//                    case MotionEvent.ACTION_CANCEL:
//                        Log.e("mmmm","ACTION_CANCEL");
//                        break;
//                }
//                return true;
//            }
//        });

    }

    private void initView() {
        //添加左侧数据
        leftAdapter = new LeftAdapter(R.layout.layout_left_item, datas);
        leftContainerRecyclerView.setAdapter(leftAdapter);
        leftAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Stock stock = (Stock) adapter.getItem(position);
                Toast.makeText(MainActivity.this, stock.getName(), Toast.LENGTH_LONG).show();
            }
        });

        // 添加右边内容数据
        rightAdapter = new RightAdapter(R.layout.layout_right_item_a, datas);
        rightContainerRecyclerView.setAdapter(rightAdapter);
        rightAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Stock stock = (Stock) adapter.getItem(position);
                Toast.makeText(MainActivity.this, stock.getName(), Toast.LENGTH_LONG).show();
            }
        });

        titleAdapter = new TitleAdapter(R.layout.layout_right_tab_a, titles, this);
//        View head = View.inflate(this,R.layout.layout_right_tab_a,null);
//        rightAdapter.addHeaderView(head);
//        View head1 = View.inflate(this,R.layout.layout_left_title,null);
//        leftAdapter.addHeaderView(head1);

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
        titleAdapter.setNewData(titles);
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
        leftAdapter.notifyDataSetChanged();
        rightAdapter.notifyDataSetChanged();
    }

}
