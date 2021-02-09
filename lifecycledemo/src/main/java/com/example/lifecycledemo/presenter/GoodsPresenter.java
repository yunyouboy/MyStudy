package com.example.lifecycledemo.presenter;

import android.util.Log;

import androidx.lifecycle.LifecycleOwner;


import com.example.lifecycledemo.bean.Goods;
import com.example.lifecycledemo.model.GoodsModel;
import com.example.lifecycledemo.model.IGoodsModel;
import com.example.lifecycledemo.view.IGoodsView;

import java.util.List;

public class GoodsPresenter<T extends IGoodsView> extends BasePresenter{

    IGoodsModel iGoodsModel=new GoodsModel();

    /**
     * 执行业务逻辑
     */
    public void fetch(){
        if(iGoodsView !=null && iGoodsModel!=null){
            iGoodsModel.loadGoodsData(new IGoodsModel.OnLoadListener() {
                @Override
                public void onComplete(List<Goods> goods) {
                    ((IGoodsView)iGoodsView.get()).showGoodsView(goods);
                }

                @Override
                public void onError(String msg) {

                }
            });
        }
    }

    @Override
    void onCreateX(LifecycleOwner owner) {
        super.onCreateX(owner);
        Log.i("jett","create");
    }

    @Override
    void onDestory(LifecycleOwner owner) {
        super.onDestory(owner);
        Log.i("jett","destroy");
    }
}
