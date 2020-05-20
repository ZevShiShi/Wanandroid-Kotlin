package com.zev.wanandroidex.mvp.presenter

import android.app.Application
import com.jess.arms.di.scope.FragmentScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import com.zev.wanandroidex.app.utils.RxUtils
import com.zev.wanandroidex.mvp.contract.ProChildContract
import com.zev.wanandroidex.mvp.model.base.BaseEntity
import com.zev.wanandroidex.mvp.model.entity.ChapterPageEntity
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import javax.inject.Inject

@FragmentScope
class ProChildPresenter @Inject constructor(model: ProChildContract.Model?, rootView: ProChildContract.View?) :
    BasePresenter<ProChildContract.Model, ProChildContract.View>(model, rootView) {

    @set:Inject
    var mErrorHandler: RxErrorHandler? = null

    @set:Inject
    var mApplication: Application? = null

    @set:Inject
    var mImageLoader: ImageLoader? = null

    @set:Inject
    var mAppManager: AppManager? = null


    fun getPro(page:Int,id:Int) {
        mModel.getPro(page,id)
            .compose(RxUtils.applySchedulers(mRootView))
            .subscribe(object :
                ErrorHandleSubscriber<BaseEntity<ChapterPageEntity>>(mErrorHandler) {
                override fun onNext(t: BaseEntity<ChapterPageEntity>) {
                    if (t.isSuccess() && t.hasData()) {
                        mRootView.getPro(t.data)
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