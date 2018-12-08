package com.zjh.administrat.weekmodeledemo1208.model;

import com.zjh.administrat.weekmodeledemo1208.bean.NewsBean;
import com.zjh.administrat.weekmodeledemo1208.bean.UserBean;
import com.zjh.administrat.weekmodeledemo1208.utils.HttpUtil;

public class IModelImpl {

    public void login(String username, String password, final IModel iModel) {
        HttpUtil.getRequest("http://www.xieast.com/api/user/login.php?username=" + username + "&password=" + password, UserBean.class, new HttpUtil.CallBack<UserBean>() {
            @Override
            public void OnSuccess(UserBean userBean) {
                int code = userBean.getCode();
                if (code == 100) {
                    iModel.modeldata(userBean.getMsg());
                } else if (code == 101) {
                    iModel.modeldata(userBean.getMsg());
                } else if (code == 102) {
                    iModel.modeldata(userBean.getMsg());
                } else if (code == 103) {
                    iModel.modeldata(userBean.getMsg());
                }
            }

        });
    }

    public void show(final IModel iModel) {
         HttpUtil.getRequest("http://www.xieast.com/api/news/news.php", NewsBean.class, new HttpUtil.CallBack<NewsBean>() {
             @Override
             public void OnSuccess(NewsBean newsBean) {
                 iModel.modeldata(newsBean);
             }
         });
    }

}
