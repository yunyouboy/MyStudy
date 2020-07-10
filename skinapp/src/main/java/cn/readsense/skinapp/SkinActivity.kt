package cn.readsense.skinapp

import android.app.Activity
import android.os.Bundle
import android.view.View
import cn.readsense.skinlib.SkinManager

/**
 * @author 享学课堂 jett
 */
class SkinActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //        LayoutInflater.from(this).setFactory2();
        setContentView(R.layout.activity_skin)

//        findViewById(R.id.tabLayout);
//        Resources resources = getResources();
//        new Resources()
    }

    fun change(view: View?) {
        //换肤，收包裹，皮肤包是独立的apk包，可以来自网络下载
        SkinManager.getInstance().loadSkin("/data/data/cn.readsense.skinapp/skin/skinskin-debug.apk")
    }

    fun restore(view: View?) {
        SkinManager.getInstance().loadSkin(null)
    }
}