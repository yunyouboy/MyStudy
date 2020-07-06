package cn.readsense.kotlinactualcombat.entity

/**
 *Author:qyg
 *DATE:2020/6/30 17:04
 *Description：
 **/
class HomeDataResponse {
    var errcode: Int = 0
    lateinit var errmsg: String
    var errdialog: Int = 0
    lateinit var data: DataBean


    data class DataBean(
            /**
             * image : http://resource.ffu365.com/upload/images/default/2016-06-04/57529302a2f602.54526763.jpg
             * link : http://m.ffu365.com/static/bas/1.html
             */
            val ad_list: List<AdListBean>,

            /**
             * image : http://resource.ffu365.com/upload/images/default/2016-06-02/574fd1fb4b91d8.93153953.jpg
             * link : http://app.ffu365.com/pages/company.html
             */
            val company_list: List<CompanyListBean>,

            /**
             * title : APP产品定位及功能介绍
             * create_time : 2016-05-01
             * link : http://m.ffu365.com/static/News/news.html?id=10
             */
            val news_list: List<NewsListBean>
    )

    data class AdListBean(val image: String, val link: String)

    data class CompanyListBean(val image: String, val link: String)

    data class NewsListBean(val title: String, val create_time: String, val link: String)
}


