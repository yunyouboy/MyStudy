package cn.readsense.lazyloadviewpage.lazyload.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import cn.readsense.lazyloadviewpage.lazyload.FragmentDelegater

/**
 * BaseFragment
 * TODO 第一版
 */
abstract class LazyFragment1 : Fragment() {
    var mFragmentDelegater: FragmentDelegater? = null
    private var rootView: View? = null
    private var isViewCreated = false

    @Nullable
    override fun onCreateView(@NonNull inflater: LayoutInflater, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View {
        E("onCreateView: ")
        if (rootView == null) {
            rootView = inflater.inflate(layoutRes, container, false)
        }
        isViewCreated = true // TODO 解决奔溃1.1
        initView(rootView) // 初始化控件 findvxxx
        return rootView!!
    }

    // TODO 判断 Fragment 是否可见 【第一版 1.1】
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        E("setUserVisibleHint")
        if (isViewCreated) {
            if (isVisibleToUser) {
                dispatchUserVisibleHint(true)
            } else {
                dispatchUserVisibleHint(false)
            }
        }
    }

    // TODO 分发 可见 不可见 的动作 【第一版 1.2】
    private fun dispatchUserVisibleHint(visibleState: Boolean) {
        if (visibleState) {
            // 加载数据
            onFragmentLoad()
        } else {
            // 停止一切操作
            onFragmentLoadStop()
        }
    }

    // 让子类完成，初始化布局，初始化控件
    protected abstract fun initView(rootView: View?)
    protected abstract val layoutRes: Int

    // -->>>停止网络数据请求
    fun onFragmentLoadStop() {
        E("onFragmentLoadStop")
    }

    // -->>>加载网络数据请求
    fun onFragmentLoad() {
        E("onFragmentLoad")
    }

    override fun onResume() {
        super.onResume()
        E("onResume")
    }

    override fun onPause() {
        super.onPause()
        E("onPause")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        E("onDestroyView")
    }

    // 工具相关而已
    fun setFragmentDelegater(fragmentDelegater: FragmentDelegater?) {
        mFragmentDelegater = fragmentDelegater
    }

    private fun E(string: String) = mFragmentDelegater?.dumpLifeCycle(string)
}