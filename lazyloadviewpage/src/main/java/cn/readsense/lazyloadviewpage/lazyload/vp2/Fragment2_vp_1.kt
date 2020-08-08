package cn.readsense.lazyloadviewpage.lazyload.vp2

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import cn.readsense.lazyloadviewpage.R
import cn.readsense.lazyloadviewpage.lazyload.FragmentDelegater
import cn.readsense.lazyloadviewpage.lazyload.base.LazyFragment5

// 同学们：这是T2  嵌套了一层 ViewPager的Fragment2_vp_1
class Fragment2_vp_1 : LazyFragment5() {

    protected override val layoutRes: Int
        get() = R.layout.fragment_vp_1

    protected override fun initView(view: View) {}

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

    override fun onFragmentLoadStop() {
        super.onFragmentLoadStop()
        Log.d(TAG, "onFragmentLoadStop" + " 停止一切更新")
    }

    override fun onFragmentLoad() {
        super.onFragmentLoad()
        Log.d(TAG, "onFragmentLoad" + " 真正更新数据")
    }

    companion object {
        private const val TAG = "Fragment2_vp_1"

        @JvmStatic
        fun newIntance(): Fragment {
            val fragment = Fragment2_vp_1()
            fragment.setFragmentDelegater(FragmentDelegater(fragment))
            return fragment
        }
    }
}