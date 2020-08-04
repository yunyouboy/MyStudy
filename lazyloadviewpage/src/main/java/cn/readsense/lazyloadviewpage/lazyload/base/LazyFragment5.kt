package cn.readsense.lazyloadviewpage.lazyload.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import cn.readsense.lazyloadviewpage.lazyload.FragmentDelegater

/**
 * BaseFragment
 * TODO 第五版
 */
abstract class LazyFragment5 : Fragment() {
    var mFragmentDelegater: FragmentDelegater? = null
    private lateinit var rootView: View
    private var isViewCreated = false // View加载了
    private var isVisibleStateUP = false // 记录上一次可见的状态

    @Nullable
    override fun onCreateView(@NonNull inflater: LayoutInflater, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View {
        E("onCreateView: ")
        rootView = inflater.inflate(resources.getLayout(layoutRes), container, false)
        isViewCreated = true // 解决奔溃1.1
        initView(rootView) // 初始化控件 findvxxx

        // 解决第一次一直初始化loading一直显示的问题 【第二版2.1】
        if (userVisibleHint) {
            // 手动来分发下
            userVisibleHint = true
        }
        return rootView
    }

    // 判断 Fragment 是否可见 【第一版 1.1】
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        E("setUserVisibleHint")
        if (isViewCreated) {

            // 记录上一次可见的状态: && isVisibleStateUP
            if (isVisibleToUser && !isVisibleStateUP) {
                dispatchUserVisibleHint(true)
            } else if (!isVisibleToUser && isVisibleStateUP) {
                dispatchUserVisibleHint(false)
            }
        }
    }

    // 分发 可见 不可见 的动作 【第一版 1.2】
    private fun dispatchUserVisibleHint(visibleState: Boolean) {

        // 记录上一次可见的状态 实时更新状态
        isVisibleStateUP = visibleState

        // TODO 为了解决第一个问题
        if (visibleState && isParentInvisible) {
            return
        }
        if (visibleState) {
            // 加载数据
            onFragmentLoad() // 都是对第一层有效，嵌套无效

            // TODO 为了解决第二个问题，T1 到 T2 T2里面嵌套的ViewPager的Fragment默认不会分发执行
            // 手动 嵌套 分发执行
            // TODO 为了解决第二个问题，T1 到 T2 T2里面嵌套的ViewPager的Fragment默认不会分发执行
            // 在双重ViewPager嵌套的情况下，第一次滑到Frgment 嵌套ViewPager(fragment)的场景的时候
            // 此时只会加载外层Fragment的数据，而不会加载内嵌viewPager中的fragment的数据，因此，我们
            // 需要在此增加一个当外层Fragment可见的时候，分发可见事件给自己内嵌的所有Fragment显示
            dispatchChildVisibleState(true)
        } else {
            // 停止一切操作
            onFragmentLoadStop() // 都是对第一层有效，嵌套无效

            // TODO 为了解决第二个问题，T1 到 T2 T2里面嵌套的ViewPager的Fragment默认不会分发执行
            dispatchChildVisibleState(false)
        }
    }

    // TODO 为了解决第二个问题，T1 到 T2 T2里面嵌套的ViewPager的Fragment默认不会分发执行
    //  解决：需要手动的分发执行嵌套Fragment里面的
    private fun dispatchChildVisibleState(state: Boolean) {
        val fragmentManager: FragmentManager = childFragmentManager
        val fragments: List<Fragment> = fragmentManager.fragments
        if (fragments != null) {
            for (fragment in fragments) { // 循环遍历 嵌套里面的 子 Fragment 来分发事件操作
                if (fragment is LazyFragment5 && !fragment.isHidden() && fragment.getUserVisibleHint()) {
                    fragment.dispatchUserVisibleHint(state)
                }
            }
        }
    }

    // TODO 判断 父控件 是否不可见， 什么意思？ 例如：  Fragment2_vp1子Fragment  的  父亲/父控件==Fragment2
    private val isParentInvisible: Boolean
        private get() {
            val parentFragment: Fragment? = parentFragment
            if (parentFragment is LazyFragment5) {
                val fragment = parentFragment as LazyFragment5
                return !fragment.isVisibleStateUP
            }
            return false
        }

    // 让子类完成，初始化布局，初始化控件
    protected abstract fun initView(rootView: View)
    protected abstract val layoutRes: Int

    // -->>>停止网络数据请求
    open fun onFragmentLoadStop() {
        E("onFragmentLoadStop")
    }

    // -->>>加载网络数据请求
    open fun onFragmentLoad() {
        E("onFragmentLoad")
    }

    override fun onResume() {
        super.onResume()
        E("onResume")

        // 不可见 到 可见 变化过程  说明可见
        if (userVisibleHint && !isVisibleStateUP) {
            dispatchUserVisibleHint(true)
        }
    }

    override fun onPause() {
        super.onPause()
        E("onPause")

        // 可见 到 不可见  变化过程  说明 不可见
        if (userVisibleHint && isVisibleStateUP) {
            dispatchUserVisibleHint(false)
        }
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