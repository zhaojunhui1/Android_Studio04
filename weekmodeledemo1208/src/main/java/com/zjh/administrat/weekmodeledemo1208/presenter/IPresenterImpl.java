package com.zjh.administrat.weekmodeledemo1208.presenter;

import com.zjh.administrat.weekmodeledemo1208.model.IModel;
import com.zjh.administrat.weekmodeledemo1208.model.IModelImpl;
import com.zjh.administrat.weekmodeledemo1208.utils.HttpUtil;
import com.zjh.administrat.weekmodeledemo1208.view.IView;

public class IPresenterImpl implements IPresenter {
    private IView iView;
    private IModelImpl iModel;

    public IPresenterImpl(IView iView) {
        this.iView = iView;
        iModel = new IModelImpl();
    }

    public void login(String username, String password){
        iModel.login(username, password, new IModel() {
            @Override
            public void modeldata(Object data) {
                iView.success(data);
            }
        });
    }

    public void show(){
        iModel.show(new IModel() {
            @Override
            public void modeldata(Object data) {
                iView.success(data);
            }
        });
    }

    public void onDetach(){
        if (iView != null){
            iView = null;
        }
        if (iModel != null){
            iModel = null;
        }
    }


}
