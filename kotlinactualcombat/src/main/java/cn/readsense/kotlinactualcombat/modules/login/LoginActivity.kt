package cn.readsense.kotlinactualcombat.modules.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import cn.readsense.kotlinactualcombat.MainActivity
import cn.readsense.kotlinactualcombat.R
import cn.readsense.kotlinactualcombat.base.BaseActivity
import cn.readsense.kotlinactualcombat.entity.RegisterLoginResponse
import cn.readsense.kotlinactualcombat.modules.login.inter.LoginPresenter
import cn.readsense.kotlinactualcombat.modules.login.inter.LoginView
import cn.readsense.kotlinactualcombat.modules.register.RegisterActivity
import com.xiangxue.kotlinproject.config.Flag
import kotlinx.android.synthetic.main.activity_user_login.*

class LoginActivity : BaseActivity<LoginPresenter>(), LoginView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_login)
    }

    override fun onResume() {
        super.onResume()
        user_login_bt.setOnClickListener(View.OnClickListener {
            val username: String = user_phone_et.text.toString()
            val password: String = user_password_et.text.toString()
            Log.e(Flag.TAG, "userName:$username,passWord:$password")
            presenter.lognAction(this@LoginActivity, username, password)
        })
        user_register_tv.setOnClickListener {
            val intent: Intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    override fun createP(): LoginPresenter = LoginPresenterImpl(this)

    override fun realease() {
        presenter.unAttachView()
    }

    override fun onSuccess(successResponse: RegisterLoginResponse?) {
        // 成功  data UI
        Log.e(Flag.TAG, "success:$successResponse")
        Toast.makeText(this@LoginActivity, "登录成功😀", Toast.LENGTH_SHORT).show()
        val intent: Intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onFailure(errorMessage: String?) {
        // 失败  data UI
        Log.e(Flag.TAG, "failure errorMsg:$errorMessage")
        Toast.makeText(this@LoginActivity, "登录失败 ~ 呜呜呜", Toast.LENGTH_SHORT).show()

    }
}
