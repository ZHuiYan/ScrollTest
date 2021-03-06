package com.example.zhaohuiyan.scrolltest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.zhaohuiyan.scrolltest.adapter.LeftAdapter;
import com.example.zhaohuiyan.scrolltest.adapter.RightAdapter;
import com.example.zhaohuiyan.scrolltest.adapter.TitleAdapter;
import com.example.zhaohuiyan.scrolltest.view.MySnapHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.left_container_recyclerView)
    RecyclerView leftContainerRecyclerView;
    @BindView(R.id.right_container_recyclerView)
    RecyclerView rightContainerRecyclerView;
    @BindView(R.id.right_title_recyclerView)
    RecyclerView rightTitleRecyclerView;
    @BindView(R.id.hs_content)
    MyHorizontalScrollView1 hsContent;
    @BindView(R.id.hs_title)
    MyHorizontalScrollView1 hsTitle;
    @BindView(R.id.swipe_target)
    LinearLayout swipeTarget;
    @BindView(R.id.leftTitle)
    View leftTitle;
    @BindView(R.id.lv_portfolio)
    MyPtrClassicFrameLayout lvPortfolio;
    //左侧固定一列数据适配
    private List<Stock> datas = new ArrayList<>();
    private List<Title> titles = new ArrayList<>();
    private LeftAdapter leftAdapter;
    private RightAdapter rightAdapter;
    private TitleAdapter titleAdapter;
    private int[] titlePoint;

    private Handler handler = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        leftTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ScrollerActivity.class);
                startActivity(intent);
            }
        });
        handler = new Handler();
        findView();
        initView();
        initData();
    }

    private void findView() {
        leftContainerRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        rightContainerRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        rightTitleRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));

        hsTitle.setScrollView(hsContent);
        hsContent.setScrollView(hsTitle);

        hsContent.setRightRecyclerView(rightContainerRecyclerView);
        hsContent.setRightTitleRecyclerView(rightTitleRecyclerView);

        rightTitleRecyclerView.setNestedScrollingEnabled(false);

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
               /* LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int first = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
                int last = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                Log.e("horizotal22","fist=" + first + "last=" + last );
                Log.e("horizotal22","childcoutn=" + linearLayoutManager.getChildCount() + " :itemcount=" + linearLayoutManager.getItemCount() );
                Log.e("horizotal22","fist=" + first + "last=" + last);*/
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

        lvPortfolio.disableWhenHorizontalMove(true);
        lvPortfolio.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return !rightContainerRecyclerView.canScrollVertically(-1);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                lvPortfolio.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        lvPortfolio.refreshComplete();
                    }
                },500);
            }
        });
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
        rightTitleRecyclerView.setAdapter(titleAdapter);
        titleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                changeTitle(position);
                sortList(position);
            }
        });

    }

    boolean isGlobal = false;
    //初始化数据源
    private void initData() {
        for (int i = 0; i < 1000; i++) {
            datas.add(new Stock("风华基金" + i, i + "", i + "", i + "", i + "", i + "", i + "", i + ""));
        }
        for (int i = 0; i < 7; i++) {
            if (i == 3){
                titles.add(new Title(i, "title" + i, false, true,1));
            }else {
                titles.add(new Title(i, "title" + i, false, true));
            }

        }
        leftAdapter.setNewData(datas);
        rightAdapter.setNewData(datas);
        titleAdapter.setNewData(titles);
        titlePoint = new int[titles.size()];


        rightTitleRecyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (!isGlobal){
                    isGlobal = true;
                    View view;
                    titlePoint[0] = 0;
                    for (int i=1;i<titles.size();i++){
                        view = rightTitleRecyclerView.getChildAt(i);
                        titlePoint[i] = view.getLeft();
                        Log.e("horizotal4","point=" + titlePoint[i]);
                    }
                    hsContent.setRightPoint(titlePoint);
                    hsTitle.setRightPoint(titlePoint);
                }
            }
        });
    }


    public void sortList(final int pos) {
        Collections.sort(datas, new Comparator<Stock>() {
            @Override
            public int compare(Stock lhs, Stock rhs) {
                Integer left = Integer.parseInt(lhs.getTxt1());
                Integer right = Integer.parseInt(rhs.getTxt1());
                if (titles.get(pos).isCrease()) {
                    return left.compareTo(right);
                } else {
                    return right.compareTo(left);
                }
            }
        });
        leftAdapter.notifyDataSetChanged();
        rightAdapter.notifyDataSetChanged();
    }

    public void changeTitle(int pos) {
        for (Title title : titles) {
            if (title.getId() == pos) {
                title.setShow(true);
                title.setCrease(!title.isCrease());
            } else {
                title.setShow(false);
                title.setCrease(true);
            }
        }
        titleAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (handler != null){
            handler.postDelayed(runnable,10 * 1000);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (handler != null){
            handler.removeCallbacks(runnable);
        }
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Log.e("mmmm","runnable");
            datas.clear();
            for (int i = 0; i < 1000; i++) {
                datas.add(new Stock("风华基金" + i, i + "", i + "", i + "", i + "", i + "", i + "", i + ""));
            }
            leftAdapter.setNewData(datas);
            rightAdapter.setNewData(datas);
            handler.postDelayed(this, 10 * 1000);
        }
    };
}
