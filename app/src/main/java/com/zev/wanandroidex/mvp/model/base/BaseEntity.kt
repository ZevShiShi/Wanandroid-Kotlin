package com.zev.wanandroidex.mvp.model.base

data class BaseEntity<T>(
    val data: T,
    val errorCode: Int,
    val errorMsg: String
) {
    fun isSuccess(): Boolean {
        if (errorCode == 0)
            return true
        return false
    }


    fun hasData(): Boolean {
        if (data == null)
            return false
        return true
    }
}