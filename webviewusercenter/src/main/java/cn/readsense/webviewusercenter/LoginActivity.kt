package cn.readsense.webviewusercenter

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import cn.readsense.webviewusercenter.databinding.ActivityLoginBinding
import eventbus.LoginEvent
import org.greenrobot.eventbus.EventBus

/**
 *
 */
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Hide Keyboard
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        binding = DataBindingUtil.setContentView(this@LoginActivity, R.layout.activity_login)

        initEvent()
    }

    private fun initEvent() {
        binding.btnLogin.setOnClickListener {
            EventBus.getDefault().post(LoginEvent(binding.lTextEmail.editText?.text.toString()))
            finish()
        }
    }
}