package com.example.lifecycledemo.model;



import com.example.lifecycledemo.bean.Goods;

import java.util.List;

public interface IGoodsModel {
    void loadGoodsData(OnLoadListener onLoadListener);
    interface OnLoadListener{
        void onComplete(List<Goods> goods);
        void onError(String msg);
    }
}









