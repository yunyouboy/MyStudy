package com.example.materialdesign.coordinator.bean

import com.example.materialdesign.R
import java.util.*

class AuthorInfo {
    private var portrait = R.drawable.xiaoxin
    private var nickName: String? = null
    private var motto: String? = null
    fun getPortrait(): Int {
        return portrait
    }

    fun setPortrait(portrait: Int) {
        this.portrait = portrait
    }

    fun getNickName(): String? {
        return nickName
    }

    fun setNickName(nickName: String?) {
        this.nickName = nickName
    }

    fun getMotto(): String? {
        return motto
    }

    fun setMotto(motto: String?) {
        this.motto = motto
    }

    companion object {
        /**
         * 生成一些测试数据
         *
         * @return
         */
        fun createTestData(): MutableList<AuthorInfo> {
            val authorInfoList: MutableList<AuthorInfo> = ArrayList()
            for (count in 0..5) {
                val authorInfo1 = AuthorInfo()
                authorInfo1.setMotto("美女，喜欢吃青椒吗？")
                authorInfo1.setNickName("蜡笔小新")
                authorInfoList.add(authorInfo1)
                val authorInfo2 = AuthorInfo()
                authorInfo2.setMotto("我是要成为火影的男人")
                authorInfo2.setNickName("鸣人")
                authorInfo2.setPortrait(R.drawable.mingren)
                authorInfoList.add(authorInfo2)
                val authorInfo3 = AuthorInfo()
                authorInfo3.setMotto("触碰万物之理，能控制森罗万象")
                authorInfo3.setNickName("六道仙人")
                authorInfoList.add(authorInfo3)
            }
            return authorInfoList
        }
    }
}