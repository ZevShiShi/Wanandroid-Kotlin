package com.zev.wanandroidex.mvp.presenter

import android.app.Application
import com.jess.arms.di.scope.FragmentScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import com.zev.wanandroidex.app.utils.RxUtils
import com.zev.wanandroidex.mvp.base.BaseErrorHandleSubscriber
import com.zev.wanandroidex.mvp.contract.SysContract
import com.zev.wanandroidex.mvp.model.base.BaseArrayEntity
import com.zev.wanandroidex.mvp.model.entity.SysEntity
import me.jessyan.rxerrorhandler.core.RxErrorHandler
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