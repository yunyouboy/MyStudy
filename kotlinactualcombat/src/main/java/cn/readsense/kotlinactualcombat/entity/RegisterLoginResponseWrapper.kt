package cn.readsense.kotlinactualcombat.entity

/**
 *Author:qyg
 *DATE:2020/6/19 17:32
 *Description：
 **/

/**
 * 包装Bean

{
"data": {
"admin": false,
"chapterTops": [],
"collectIds": [],
"email": "",
"icon": "",
"id": 66720,
"nickname": "Derry-vip",
"password": "",
"publicName": "Derry-vip",
"token": "",
"type": 0,
"username": "Derry-vip"
},
"errorCode": 0,
"errorMsg": ""
}

{
"data": null,
"errorCode": -1,
"errorMsg": "账号密码不匹配！"
}

 */

data class RegisterLoginResponseWrapper<T>(val data: T, val errorCode: Int, val errorMsg: String)