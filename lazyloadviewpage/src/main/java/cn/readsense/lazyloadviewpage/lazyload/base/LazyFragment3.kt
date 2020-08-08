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
 * TODO 第三版
 */
abstract class LazyFragment3 : Fragment() {
    var mFragmentDelegater: FragmentDelegater? = null
    private var rootView: View? = null
    private var isViewCreated = false // View加载了
    private var isVisibleStateUP = false // TODO 记录上一次可见的状态

    @Nullable
    override fun onCreateView(@NonNull inflater: LayoutInflater, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View {
        E("onCreateView: ")
        if (rootView == null) {
            rootView = inflater.inflate(layoutRes, container, false)
        }
        isViewCreated = true // 解决奔溃1.1
        initView(rootView) // 初始化控件 findvxxx

        // 解决第一次一直初始化loading一直显示的问题 【第二版2.1】
        if (getUserVisibleHint()) {
            // 手动来分发下
            setUserVisibleHint(true)
        }
        return rootView!!
    }

    // 判断 Fragment 是否可见 【第一版 1.1】
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        E("setUserVisibleHint")
        if (isViewCreated) {

            // TODO 记录上一次可见的状态: && isVisibleStateUP
            if (isVisibleToUser && !isVisibleStateUP) {
                dispatchUserVisibleHint(true)
            } else if (!isVisibleToUser && isVisibleStateUP) {
                dispatchUserVisibleHint(false)
            }
        }
    }

    // 分发 可见 不可见 的动作 【第一版 1.2】
    private fun dispatchUserVisibleHint(visibleState: Boolean) {

        // TODO 记录上一次可见的状态 实时更新状态
        isVisibleStateUP = visibleState
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