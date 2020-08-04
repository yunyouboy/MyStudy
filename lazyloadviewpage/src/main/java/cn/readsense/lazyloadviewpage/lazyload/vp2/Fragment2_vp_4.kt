package cn.readsense.lazyloadviewpage.lazyload.vp2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import cn.readsense.lazyloadviewpage.R
import cn.readsense.lazyloadviewpage.lazyload.FragmentDelegater
import cn.readsense.lazyloadviewpage.lazyload.base.LazyFragment5

class Fragment2_vp_4 : LazyFragment5() {
    protected override val layoutRes: Int
        protected get() = R.layout.fragment_vp_4

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

    companion object {
        @JvmStatic
        fun newIntance(): Fragment {
            val fragment = Fragment2_vp_4()
            fragment.setFragmentDelegater(FragmentDelegater(fragment))
            return fragment
        }
    }
}