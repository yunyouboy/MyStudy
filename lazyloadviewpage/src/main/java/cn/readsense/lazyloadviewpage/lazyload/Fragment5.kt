package cn.readsense.lazyloadviewpage.lazyload

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.Nullable
import cn.readsense.lazyloadviewpage.R
import cn.readsense.lazyloadviewpage.lazyload.base.LazyFragment5

class Fragment5 : LazyFragment5() {
    private var btn: Button? = null

    @Nullable
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun initView(rootView: View) {
        btn = rootView?.findViewById(R.id.btn)
        btn?.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, Main2Activity::class.java)
            startActivity(intent)
        })
    }

    protected override val layoutRes: Int
        get() = R.layout.fragment5

    override fun onFragmentLoadStop() {
        super.onFragmentLoadStop()
        Log.d(TAG, "onFragmentLoadStop: fragment 5" + " 停止一切更新")
    }

    override fun onFragmentLoad() {
        super.onFragmentLoad()
        Log.d(TAG, "onFragmentLoad: fragment 5" + " 真正更新数据")
    }

    companion object {
        private val TAG =  /*"Fragment5"*/MyFragment::class.java.simpleName

        @JvmStatic
        fun instance(): Fragment5 {
            val fragment5 = Fragment5()
            fragment5.setFragmentDelegater(FragmentDelegater(fragment5))
            return fragment5
        }
    }
}