package com.zjh.administrat.zhaojunhui1206.view;

import android.content.Context;
import android.provider.ContactsContract;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zjh.administrat.zhaojunhui1206.R;
import com.zjh.administrat.zhaojunhui1206.utils.NewsBean;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.List;

public class NewsAdapter extends BaseAdapter {
    List<NewsBean.DataBean> mData;
    Context mContext;

    public NewsAdapter(Context mContext) {
        this.mContext = mContext;
        mData = new ArrayList<>();
    }

    public void setData(List<NewsBean.DataBean> data) {
        mData.clear();
        if (data != null){
            mData.addAll(data);
        }
        notifyDataSetChanged();
    }

    public void addData(List<NewsBean.DataBean> data) {
        if (data != null){
            mData.addAll(data);
        }
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public NewsBean.DataBean getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NewsHolder mHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.news_item, parent, false);
            mHolder = new NewsHolder(convertView);
        }else {
            mHolder = (NewsHolder) convertView.getTag();
        }
        mHolder.bindData(getItem(position));
        return convertView;
    }

    class NewsHolder{
        ImageView imageView;
        TextView textView1, textView2;
        public NewsHolder(View itemView){
            imageView = itemView.findViewById(R.id.image);
            textView1 = itemView.findViewById(R.id.text1);
            textView2 = itemView.findViewById(R.id.text2);
            itemView.setTag(this);
        }

        public void bindData(NewsBean.DataBean item) {
            textView1.setText(item.getTitle());
            textView2.setText(item.getPrice()+"");

            String img = "";
            int k = item.getImages().length();
            for(int i = 0; i < k; i++) {
                if(item.getImages().substring(i, i+1).equals("|")) {
                    img = item.getImages().substring(i+1, k).trim();
                }
            }

            if (imageView != null){
                ImageLoader.getInstance().displayImage(img, imageView);
            }
        }
    }

}
