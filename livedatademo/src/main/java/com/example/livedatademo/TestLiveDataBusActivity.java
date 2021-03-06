package com.example.livedatademo;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.livedatademo.databus.LiveDataBus;


public class TestLiveDataBusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livedatabus);
        LiveDataBus.getInstance().with("data", String.class)
                    .observe(this, new Observer<String>() {
                        @Override
                        public void onChanged(String s) {
                            if(s!=null)
                            Toast.makeText(TestLiveDataBusActivity.this, s, Toast.LENGTH_SHORT).show();
                        }
                    });
    }
}
