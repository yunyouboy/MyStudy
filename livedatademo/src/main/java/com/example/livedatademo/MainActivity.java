package com.example.livedatademo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.livedatademo.databus.LiveDataBus;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view) {
        if (view.getId() == R.id.btn) {
            Intent intent = new Intent(this, NameActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.btn2) {
            Intent intent = new Intent(this, TestLiveDataBusActivity.class);
            startActivity(intent);

            new Thread(){
                @Override
                public void run() {
                    for (int i = 0; i < 10; i++) {
                        //发送消息
                        LiveDataBus.getInstance().with("data", String.class).postValue("jett");
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }


                    }
                }
            }.start();


        }
    }


}
