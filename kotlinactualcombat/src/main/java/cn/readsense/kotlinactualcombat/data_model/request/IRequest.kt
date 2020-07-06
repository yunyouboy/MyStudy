package cn.readsense.kotlinactualcombat.data_model.request

/**
 *Author:qyg
 *DATE:2020/6/30 17:13
 *Description：
 **/
interface IRequest {
    fun requesstAction(url: String, value: String, responseData: NetWorkResponseData)

    fun requesstAction(url: String, value1: String, value2: String, responseData: NetWorkResponseData)

    fun requesstAction(url: String, value1: String, value2: String, value3: String, responseData: NetWorkResponseData)

    fun requesstAction(url: String, map: HashMap<String, String>, responseData: NetWorkResponseData)

    //fun requesstAction(url: String, responseData: NetWorkResponseData,vararg value: String)
}