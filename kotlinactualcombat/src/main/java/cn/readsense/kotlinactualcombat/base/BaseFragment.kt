package cn.readsense.kotlinactualcombat.base

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class BaseFragment<P> : Fragment() where P : IBasePresenter {

    protected lateinit var presenter: P
    protected lateinit var myActivity: AppCompatActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        myActivity = context as AppCompatActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        createOK()
    }

    override fun onDestroy() {
        super.onDestroy()
        recycle()
    }

    abstract fun createPresenter(): P
    abstract fun createOK()
    abstract fun recycle()

    fun hideActionBar(): Unit {
        myActivity?.supportActionBar?.hide()
    }

    fun showActionBar(): Unit {
        myActivity?.supportActionBar?.show()
    }
}