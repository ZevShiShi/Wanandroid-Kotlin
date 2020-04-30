package com.zev.wanandroid.mvp.presenter

import android.app.Application
import com.jess.arms.di.scope.FragmentScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import com.zev.wanandroid.app.utils.RxUtils
import com.zev.wanandroid.mvp.base.BaseErrorHandleSubscriber
import com.zev.wanandroid.mvp.contract.ProContract
import com.zev.wanandroid.mvp.contract.SysContract
import com.zev.wanandroid.mvp.model.base.BaseArrayEntity
import com.zev.wanandroid.mvp.model.entity.ProTabEntity
import com.zev.wanandroid.mvp.model.entity.SysEntity
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import javax.inject.Inject

@FragmentScope
class SysPresenter @Inject constructor(model: SysContract.Model?, rootView: SysContract.View?) :
    BasePresenter<SysContract.Model, SysContract.View>(model, rootView) {

    @set:Inject
    var mErrorHandler: RxErrorHandler? = null

    @set:Inject
    var mApplication: Application? = null

    @set:Inject
    var mImageLoader: ImageLoader? = null

    @set:Inject
    var mAppManager: AppManager? = null


    fun getSys() {
        mModel.getSys()
            .compose(RxUtils.applySchedulers(mRootView))
            .subscribe(object :
                BaseErrorHandleSubscriber<BaseArrayEntity<SysEntity>>(mErrorHandler, mRootView) {
                override fun onNext(t: BaseArrayEntity<SysEntity>) {
                    super.onNext(t)
                    if (t.isSuccess() && t.hasData()) {
                        mRootView.getSys(t.data)
                    }
                }
            })
    }

    override fun onDestroy() {
        super.onDestroy()
        mErrorHandler = null
        mApplication = null
        mImageLoader = null
        mAppManager = null

    }
}