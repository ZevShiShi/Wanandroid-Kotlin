package com.zev.wanandroid.mvp.presenter

import android.app.Application
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import com.zev.wanandroid.mvp.contract.MainContract
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject

@ActivityScope
class MainPresenter @Inject constructor(model: MainContract.Model?, rootView: MainContract.View?) :
    BasePresenter<MainContract.Model, MainContract.View>(model, rootView) {

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