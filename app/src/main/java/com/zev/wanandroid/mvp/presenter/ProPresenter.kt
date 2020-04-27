package com.zev.wanandroid.mvp.presenter

import android.app.Application
import com.jess.arms.di.scope.FragmentScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import com.zev.wanandroid.mvp.contract.ProContract
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject

@FragmentScope
class ProPresenter @Inject constructor(model: ProContract.Model?, rootView: ProContract.View?) :
    BasePresenter<ProContract.Model, ProContract.View>(model, rootView) {

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