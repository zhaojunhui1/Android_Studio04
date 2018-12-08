package com.zjh.administrat.zhaojunhui1206.model;

import com.zjh.administrat.zhaojunhui1206.utils.HttpUtil;

public interface IModel<T> {
    void showData(String urlStr,Class clazz, HttpUtil.CallBack callBack);
}
