package com.zev.wanandroid.mvp.model.base

data class BaseArrayEntity<T>(
    val data: List<T>,
    val errorCode: Int,
    val errorMsg: String
) {
    fun isSuccess(): Boolean {
        if (errorCode == 0)
            return true
        return false
    }


    fun hasData(): Boolean {
        if (data.isEmpty())
            return false
        return true
    }
}