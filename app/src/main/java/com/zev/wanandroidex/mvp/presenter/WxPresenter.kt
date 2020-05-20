package com.zev.wanandroidex.mvp.presenter

import android.app.Application
import com.jess.arms.di.scope.FragmentScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import com.zev.wanandroidex.app.utils.RxUtils
import com.zev.wanandroidex.mvp.base.BaseErrorHandleSubscriber
import com.zev.wanandroidex.mvp.contract.ProContract
import com.zev.wanandroidex.mvp.contract.WxContract
import com.zev.wanandroidex.mvp.model.base.BaseArrayEntity
import com.zev.wanandroidex.mvp.model.entity.ProTabEntity
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject

@FragmentScope
class WxPresenter @Inject constructor(model: WxContract.Model?, rootView: WxContract.View?) :
    BasePresenter<WxContract.Model, WxContract.View>(model, rootView) {

    @set:Inject
    var mErrorHandler: RxErrorHandler? = null

    @set:Inject
    var mApplication: Application? = null

    @set:Inject
    var mImageLoader: ImageLoader? = null

    @set:Inject
    var mAppManager: AppManager? = null


    override fun onDestroy() {
        super.onDestroy()
        mErrorHandler = null
        mApplication = null
        mImageLoader = null
        mAppManager = null

    }
}