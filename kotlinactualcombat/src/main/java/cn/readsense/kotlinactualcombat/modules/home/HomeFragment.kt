package cn.readsense.kotlinactualcombat.modules.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import cn.readsense.kotlinactualcombat.R
import cn.readsense.kotlinactualcombat.adapter.HomeInfoListAdapter
import cn.readsense.kotlinactualcombat.data_model.request.NetWorkResponseData
import cn.readsense.kotlinactualcombat.data_model.request.RequestAPI
import cn.readsense.kotlinactualcombat.entity.HomeDataResponse
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.xiangxue.kotlinproject.config.Flag
import kotlinx.android.synthetic.main.fragment_home.*
import okhttp3.Response

class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Toast.makeText(activity, "首页", Toast.LENGTH_LONG).show()
        val root: View? = inflater.inflate(R.layout.fragment_home, container, false)
        return root ?: super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        requestHomeData()
    }

    private fun requestHomeData() {
        RequestAPI.requesstAction(Flag.SERVER_URL, "1", object : NetWorkResponseData()/**生命匿名内部类对象**/ {
            override fun requestFaliure(message: String?) {
                Log.e(Flag.TAG, "requestHomeData requestError info:$message")
                showResultError(message)
            }

            override fun requestSuccess(response: Response) {
                val resultDatata = response.body()?.string().toString()
                Log.e(Flag.TAG, "成功  数据在response里面  获取后台给我们的JSON 字符串 resultData:$resultDatata")
                val gson: Gson = Gson()
                val homeDataResponse = gson.fromJson(resultDatata, HomeDataResponse::class.java)
                showResultSuccess(homeDataResponse)
            }

        })
    }

    /**
     * TODO 首页的画面展示【成功】
     */
    private fun showResultSuccess(result: HomeDataResponse) {
        text_home.text = "欢迎同学们的到来>>>>>>>>>>>"
        lv_home.adapter = HomeInfoListAdapter(context!!, result.data.news_list)
        //home_listview.adapter = context?.myRun { HomeInfoListAdapter(it, result.data.news_list) }

        // 两者图片的显示
        Glide.with(iv_top).load(result.data.company_list[0].image).into(iv_top)
        Glide.with(iv_bottom).load(result.data.ad_list[0].image).into(iv_bottom)
    }

    /**
     * TODO 首页的画面展示【失败】
     */
    private fun showResultError(errorMsg: String?) {
        Toast.makeText(activity, "首页数据请求失败: errorMsg:$errorMsg", Toast.LENGTH_SHORT).show()
    }

    object aaa : NetWorkResponseData() {
        override fun requestFaliure(message: String?) {
            TODO("Not yet implemented")
        }

        override fun requestSuccess(response: Response) {
            TODO("Not yet implemented")
        }

    }

    var jjjj = object :NetWorkResponseData(){
        override fun requestFaliure(message: String?) {
            TODO("Not yet implemented")
        }

        override fun requestSuccess(response: Response) {
            TODO("Not yet implemented")
        }

    }
}