package cn.readsense.webviewusercenter

import android.content.Intent
import cn.readsense.webviewbase.app.BaseApplication
import cn.readsense.webviewcommon.autoservice.IUserCenterService
import com.google.auto.service.AutoService

@AutoService(IUserCenterService::class)
class UserCenterService : IUserCenterService {
    override fun isLogined(): Boolean {
        return false
    }

    override fun login() {
        val intent = Intent(BaseApplication.myApplication, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        BaseApplication.myApplication.startActivity(intent)
    }
}