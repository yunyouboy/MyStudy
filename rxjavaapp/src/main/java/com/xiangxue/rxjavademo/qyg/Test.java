package com.xiangxue.rxjavademo.qyg;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Author:qyg
 * DATE:2020/6/4 13:56
 * Description：
 **/
public class Test {
    private static final String TAG = Test.class.getSimpleName();
    private Disposable disposable = null;

    private void test() {

        Observable.create(
                //自定义source
                new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                        emitter.onNext("Hello");
                    }
                })//返回ObservableCreate对象，ObservableCreate对象持有自定义source引用

                //ObservableCreate.map
                .map(new Function<String, Integer>() {
                    @Override
                    public Integer apply(String s) throws Exception {
                        return 100;
                    }
                })//返回ObservableMap对象，持有ObservableCreate对象引用

                //ObservableMap.subscribe
                .subscribe(
                        //自定义观察者（重点）
                        new Observer<Integer>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposable = d;
                            }

                            @Override
                            public void onNext(Integer integer) {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
        disposable.dispose();

        disposable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("Hello");
            }
        })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {

                    }
                });
        disposable.dispose();
    }

    private void test2() {
        Observable.create(
                //自定义source
                new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                        emitter.onNext("Hello");
                    }
                })
                .subscribeOn(Schedulers.io())
                //ObservableSubscribeOn.subscribe

                //ObservableCreate.observeOn
                //.observeOn(AndroidSchedulers.mainThread())
                //ObservableObserveOn.subscribe
                .subscribe(
                        //终点（自定义观察者）
                        new Observer<String>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                Log.e(TAG, "onSubscribe 执行线程 " + Thread.currentThread().getName());
                            }

                            @Override
                            public void onNext(String s) {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
    }
}
