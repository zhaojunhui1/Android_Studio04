package com.zjh.administrat.zhaojunhui1206.presender;

import com.zjh.administrat.zhaojunhui1206.model.IModelImpl;
import com.zjh.administrat.zhaojunhui1206.utils.HttpUtil;
import com.zjh.administrat.zhaojunhui1206.utils.NewsBean;
import com.zjh.administrat.zhaojunhui1206.view.IView;

public class IPresenterImpl implements IPresender {
    private IModelImpl model;
    private IView mIView;

    public IPresenterImpl(IView mIView) {
        this.mIView = mIView;
        model = new IModelImpl();
    }

    @Override
    public void startRequest(String urlStr, Class clazz) {
        model.showData(urlStr, clazz, new HttpUtil.CallBack() {
            @Override
            public void OnSuccess(Object o) {
                mIView.startRequest(o);
            }
        });
    }

    public void OnDetach(){
        if (model != null){
            model = null;
        }
        if (mIView != null){
            mIView = null;
        }
    }
}
