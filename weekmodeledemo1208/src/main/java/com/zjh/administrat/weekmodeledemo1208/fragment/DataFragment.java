package com.zjh.administrat.weekmodeledemo1208.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.recker.flybanner.FlyBanner;
import com.zjh.administrat.weekmodeledemo1208.R;
import com.zjh.administrat.weekmodeledemo1208.adapter.DataAdapter;
import com.zjh.administrat.weekmodeledemo1208.bean.NewsBean;
import com.zjh.administrat.weekmodeledemo1208.presenter.IPresenterImpl;
import com.zjh.administrat.weekmodeledemo1208.view.IView;

import java.util.ArrayList;
import java.util.List;

public class DataFragment extends android.support.v4.app.Fragment implements IView {

    private IPresenterImpl mPresenter;
    private ListView listView;
    private String path = "http://api.expoon.com/AppNews/getNewsList/type/1/p/1";
    private DataAdapter mAdapter;
    private FlyBanner banner;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.datafragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = new IPresenterImpl(this);
        listView = view.findViewById(R.id.listView);
        banner = view.findViewById(R.id.banner);

        mAdapter = new DataAdapter(getActivity());
        listView.setAdapter(mAdapter);

        mPresenter.show();
        initBanner();
    }
   //轮播图
    private void initBanner() {
        List<String> list=new ArrayList<>();
        list.add("http://f.expoon.com/sub/news/2016/01/21/887844_230x162_0.jpg");
        list.add("http://f.expoon.com/sub/news/2016/01/21/580828_230x162_0.jpg");
        list.add("http://f.expoon.com/sub/news/2016/01/21/745921_230x162_0.jpg");
        banner.setImagesUrl(list);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null){
            mPresenter.onDetach();
        }
    }

    @Override
    public void success(Object data) {
        NewsBean newsBean = (NewsBean) data;
        mAdapter.setDatas(newsBean.getData());

    }


}
