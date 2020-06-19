package cn.readsense.kotlinactualcombat.modules

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cn.readsense.kotlinactualcombat.R
import cn.readsense.kotlinactualcombat.api.WanAndroidAPI
import cn.readsense.kotlinactualcombat.entity.LoginResponse
import cn.readsense.kotlinactualcombat.net.APIClient
import cn.readsense.kotlinactualcombat.net.APIResponse
import com.xiangxue.kotlinproject.config.Flag
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_user_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_login)
    }

    override fun onResume() {
        super.onResume()
        user_login_bt.setOnClickListener(View.OnClickListener {
            when (it.id) {
                R.id.user_login_bt -> {
                    val userName: String = user_phone_et.text.toString()
                    val passWord: String = user_password_et.text.toString()
                    Log.e(Flag.TAG, "userName:$userName,passWord:$passWord")
                    APIClient.instance.instanceRetrofit(WanAndroidAPI::class.java)
                            .loginAction(userName, passWord)
                            .subscribeOn(Schedulers.io())
                            .subscribeOn(AndroidSchedulers.mainThread())
                            .subscribe(object : APIResponse<LoginResponse>(this) {
                                override fun success(data: LoginResponse?) {
                                    // 成功  data UI
                                    Log.e(Flag.TAG, "success:$data")
                                    Toast.makeText(this@LoginActivity, "登录成功😀", Toast.LENGTH_SHORT).show()
                                }

                                override fun failure(errorMsg: String?) {
                                    // 成功  data UI
                                    Log.e(Flag.TAG, "failure errorMsg:$errorMsg")
                                    Toast.makeText(this@LoginActivity, "登录失败 ~ 呜呜呜", Toast.LENGTH_SHORT).show()
                                }

                            })
                }
            }
        })
    }
}
