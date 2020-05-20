package com.zev.wanandroidex.mvp.base

import com.jess.arms.mvp.IView

interface BaseIView:IView {

    fun showEmptyLayout()

    fun showErrorLayout()

    fun showSuccessLayout()
}