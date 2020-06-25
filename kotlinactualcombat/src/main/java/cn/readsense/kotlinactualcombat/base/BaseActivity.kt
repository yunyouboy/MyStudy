package cn.readsense.kotlinactualcombat.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<T> (): AppCompatActivity() where T : IBasePresenter {

    lateinit var presenter: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createP()
    }

    override fun onDestroy() {
        super.onDestroy()

        realease()
    }

    abstract fun createP(): T

    abstract fun realease()
}