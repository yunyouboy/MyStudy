package cn.readsense.kotlinactualcombat.modules.register

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import cn.readsense.kotlinactualcombat.R
import cn.readsense.kotlinactualcombat.base.BaseActivity
import cn.readsense.kotlinactualcombat.entity.RegisterLoginResponse
import cn.readsense.kotlinactualcombat.modules.register.inter.RegisterPresenter
import cn.readsense.kotlinactualcombat.modules.register.inter.RegisterView
import com.xiangxue.kotlinproject.config.Flag
import kotlinx.android.synthetic.main.activity_user_register.*

class RegisterActivity : BaseActivity<RegisterPresenter>(), RegisterView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_register)
    }

    override fun onResume() {
        super.onResume()
        user_register_bt.setOnClickListener(View.OnClickListener {
            var username: String = user_phone_et.text.toString()
            var password: String = user_password_et.text.toString()
            presenter.registerAction(this@RegisterActivity, username, password, password)
        })
    }


    override fun createP(): RegisterPresenter = RegisterPresenterImpl(this)

    override fun realease() {
        presenter.unAttachView()
    }

    override fun onSuccess(registerLoginResponse: RegisterLoginResponse?) {
        // 成功  data UI
        Log.e(Flag.TAG, "success:$registerLoginResponse")
        Toast.makeText(this@RegisterActivity, "注册成功😀", Toast.LENGTH_SHORT).show()
    }

    override fun onFailure(errorMsg: String?) {
        // 失败  data UI
        Log.e(Flag.TAG, "failure errorMsg:$errorMsg")
        Toast.makeText(this@RegisterActivity, "注册失败 ~ 呜呜呜", Toast.LENGTH_SHORT).show()
    }
}