package com.zjh.administrat.zhaojunhui1206.presender;

public interface IPresender<T> {
   void startRequest(String urlStr, Class clazz);
}
