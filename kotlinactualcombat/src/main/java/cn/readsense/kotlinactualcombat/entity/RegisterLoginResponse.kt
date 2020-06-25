package cn.readsense.kotlinactualcombat.entity

/**
 *Author:qyg
 *DATE:2020/6/19 17:24
 *Description：
 **/

/**
 * data 登录成功 需要把这个Bean 给 UI

"data": {
"admin": false,
"chapterTops": [],
"collectIds": [],
"email": [],
"icon": "",
"id": 66720,
"nickname": "Derry-vip",
"password": "",
"publicName": "Derry-vip",
"token": "",
"type": 0,
"username": "Derry-vip"
}


 */
data class RegisterLoginResponse(val admin: Boolean,
                                 val chapterTops: List<*>,
                                 val collectIds: List<*>,
                                 val email: String?,
                                 val icon: String?,
                                 val id: Int,
                                 val nickname: String?,
                                 val password: String?,
                                 val publicName: String?,
                                 val token: String?,
                                 val type: Int,
                                 val username: String?
)