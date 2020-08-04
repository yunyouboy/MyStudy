package cn.readsense.lazyloadviewpage.lazyload

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.Nullable
import cn.readsense.lazyloadviewpage.R
import cn.readsense.lazyloadviewpage.lazyload.base.LazyFragment5

// T1  T2 T3 T4 T5 有他呈现的Fragment
class MyFragment : LazyFragment5() {
    lateinit var imageView: ImageView
    lateinit var textView: TextView
    var tabIndex = 0
    private var con: CountDownTimer? = null
    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "$tabIndex fragment onCreate: ")
    }

    override val layoutRes = R.layout.fragment_lazy_loading

    override fun initView(rootView: View) {
        imageView = rootView.findViewById(R.id.iv_content)
        textView = rootView.findViewById(R.id.tv_loading)
        tabIndex = arguments!!.getInt(POSITION)
        Log.d(TAG, "$tabIndex fragment initView: ")
    }

    // 中断更新
    override fun onFragmentLoadStop() {
        super.onFragmentLoadStop()
        tabIndex = arguments!!.getInt(POSITION)
        handler.removeMessages(10)
        if (con != null) {
            con!!.cancel()
        }
        Log.d(TAG, "$tabIndex fragment 暂停一切操作 pause")
        //对UI操作的代表
        textView.text = "李元霸"
    }

    //加载数据
    override fun onFragmentLoad() {
        super.onFragmentLoad()
        getData()
        Log.d(TAG, "$tabIndex fragment 真正更新界面")
    }

    @Nullable
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun getData() {
        con = object : CountDownTimer(1000, 100) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                handler.sendEmptyMessage(0)
            }
        }.start()
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "$tabIndex fragment onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "$tabIndex fragment onPause: ")
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        tabIndex = arguments!!.getInt(POSITION)
        super.setUserVisibleHint(isVisibleToUser)
        Log.d(TAG, "$tabIndex fragment setUserVisibleHint: $isVisibleToUser")
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "$tabIndex fragment onAttach: ")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "$tabIndex fragment onDetach: ")
    }

    private val handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            textView.visibility = View.GONE
            val id: Int = when (tabIndex) {
                1 -> R.drawable.a
                2 -> R.drawable.b
                3 -> R.drawable.c
                4 -> R.drawable.d
                else -> R.drawable.a
            }
            imageView.setImageResource(id)
            imageView.scaleType = ImageView.ScaleType.FIT_XY
            imageView.visibility = View.VISIBLE
            Log.d(TAG, "$tabIndex handleMessage: ")
            //模拟耗时工作
            try {
                Thread.sleep(40)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (con != null) {
            con!!.cancel()
        }
    }

    companion object {
        private const val TAG = "MyFragment"
        const val POSITION = "Position"
        fun newInstance(position: Int): MyFragment {
            val bundle = Bundle()
            bundle.putInt("Position", position)
            val fragment = MyFragment()
            fragment.setFragmentDelegater(FragmentDelegater(fragment))
            fragment.arguments = bundle
            return fragment
        }
    }
}