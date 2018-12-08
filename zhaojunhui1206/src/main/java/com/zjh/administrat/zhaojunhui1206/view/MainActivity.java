package com.zjh.administrat.zhaojunhui1206.view;

import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zjh.administrat.zhaojunhui1206.R;
import com.zjh.administrat.zhaojunhui1206.presender.IPresenterImpl;
import com.zjh.administrat.zhaojunhui1206.utils.NewsBean;

public class MainActivity extends AppCompatActivity implements IView {

    private IPresenterImpl mPresenter;
    private PullToRefreshListView listView;
    private String urlStr = "http://www.zhaoapi.cn/product/getProducts?pscid=";
    private int pscid;
    private NewsAdapter mAdapter;
    private NewsBean newsBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.relistView);
        pscid = 1;
        mPresenter = new IPresenterImpl(this);
        //适配器
        mAdapter = new NewsAdapter(this);
        listView.setAdapter(mAdapter);
        //刷新加载
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pscid = 1;
                initData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                initData();
            }
        });
        initData();
    }

    private void initData() {
        mPresenter.startRequest(urlStr+pscid, NewsBean.class);
    }

    @Override
    public void startRequest(Object data) {
        newsBean = (NewsBean) data;

        if (pscid == 1){
            mAdapter.setData(newsBean.getData());
        }else {
            mAdapter.addData(newsBean.getData());
        }
        pscid ++;
        listView.onRefreshComplete();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null){
            mPresenter.OnDetach();
        }
    }

}
