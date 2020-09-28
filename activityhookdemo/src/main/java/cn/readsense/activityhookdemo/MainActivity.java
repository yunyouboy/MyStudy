package cn.readsense.activityhookdemo;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.readsense.activityhookdemo.hook.HookHelper;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Zero";

    @BindView(R.id.tv_show)
    TextView tvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_hook1)
    public void onBtnHook1Clicked() {

    }

    @OnClick(R.id.btn_hook2)
    public void onBtnHook2Clicked() {

        File file = new File("a.txt");

        try {
            FileInputStream inputStream = new FileInputStream(file);
//            inputStream.read();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @OnClick(R.id.btn_hook3)
    public void onBtnHook3Clicked() {
        HookHelper.hookInstrumentation(this);
        Intent intent = new Intent(this, TargetActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_hook4)
    public void onBtnHook4Clicked() {
        Log.i(TAG, "onBtnHook4Clicked: " + Build.VERSION.SDK_INT);
        HookHelper.hookAMSAidl();
        HookHelper.hookHandler();
        Intent intent = new Intent(this, TargetActivity.class);
        startActivity(intent);
    }
}