package com.example.lifecycledemo;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.lifecycle.Observer;


import com.example.lifecycledemo.adapter.GoodsAdapter;
import com.example.lifecycledemo.bean.Goods;
import com.example.lifecycledemo.databus.LiveDataBus;
import com.example.lifecycledemo.presenter.GoodsPresenter;
import com.example.lifecycledemo.view.BaseActivity;
import com.example.lifecycledemo.view.IGoodsView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<GoodsPresenter, IGoodsView> implements IGoodsView {



    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.listView);

        presenter.fetch();

        LiveDataBus.getInstance().with("list", ArrayList.class)
                .observe(this, new Observer<ArrayList>() {
                    @Override
                    public void onChanged(ArrayList arrayList) {
                        if(arrayList!=null){
                            Log.i("jett","收到了数据"+arrayList.toString());
                        }
                    }
                });

    }
    @Override
    protected GoodsPresenter createPresenter() {
        return new GoodsPresenter();
    }
    @Override
    public void showGoodsView(List<Goods> goods) {
        listView.setAdapter(new GoodsAdapter(this,goods));
    }

    @Override
    public void showErrorMessage(String msg) {

    }

    @Override
    protected void init() {
        super.init();
        getLifecycle().addObserver(presenter);
    }
}
