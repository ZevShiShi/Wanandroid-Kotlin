package com.zev.wanandroid.mvp.presenter

import android.app.Application
import com.blankj.utilcode.util.ToastUtils
import com.jess.arms.di.scope.FragmentScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import com.zev.wanandroid.app.utils.RxUtils
import com.zev.wanandroid.mvp.base.BaseErrorHandleSubscriber
import com.zev.wanandroid.mvp.contract.HomeContract
import com.zev.wanandroid.mvp.model.base.BaseArrayEntity
import com.zev.wanandroid.mvp.model.base.BaseEntity
import com.zev.wanandroid.mvp.model.entity.BannerEntity
import com.zev.wanandroid.mvp.model.entity.ChapterEntity
import com.zev.wanandroid.mvp.model.entity.ChapterPageEntity
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import javax.inject.Inject

@FragmentScope
class HomePresenter @Inject constructor(model: HomeContract.Model?, rootView: HomeContract.View?) :
    BasePresenter<HomeContract.Model, HomeContract.View>(model, rootView) {

    @set:Inject
    var mErrorHandler: RxErrorHandler? = null

    @set:Inject
    var mApplication: Application? = null

    @set:Inject
    var mImageLoader: ImageLoader? = null

    @set:Inject
    var mAppManager: AppManager? = null


    fun getBanner() {
        mModel.getBanner()
            .compose(RxUtils.applySchedulers(mRootView))
            .subscribe(object :
                ErrorHandleSubscriber<BaseArrayEntity<BannerEntity>>(mErrorHandler) {
                override fun onNext(t: BaseArrayEntity<BannerEntity>) {
                    if (t.isSuccess() && t.hasData()) {
                        mRootView.getBanner(t.data)
                    }
                }
            })
    }


    fun getChapterTop() {
        mModel.getChapterTop()
            .compose(RxUtils.applySchedulers(mRootView))
            .subscribe(object :
                ErrorHandleSubscriber<BaseArrayEntity<ChapterEntity>>(mErrorHandler) {
                override fun onNext(t: BaseArrayEntity<ChapterEntity>) {
                    if (t.isSuccess() && t.hasData()) {
                        mRootView.getChapterTop(t.data)
                    }
                }
            })
    }


    fun getHomeChapter(page: Int) {
        mModel.getHomeChapter(page)
            .compose(RxUtils.applySchedulers(mRootView))
            .subscribe(object :
                BaseErrorHandleSubscriber<BaseEntity<ChapterPageEntity>>(mErrorHandler,mRootView) {
                override fun onNext(t: BaseEntity<ChapterPageEntity>) {
                    super.onNext(t)
                    if (t.isSuccess() && t.hasData()) {
                        mRootView.getHomeChapter(t.data)
                    }
                }
            })
    }


    fun collectChapter(id: Int) {
        mModel.collectChapter(id)
            .compose(RxUtils.applySchedulers(mRootView))
            .subscribe(object : ErrorHandleSubscriber<BaseEntity<Any>>(mErrorHandler) {
                override fun onNext(t: BaseEntity<Any>) {
                    if (t.isSuccess()) {
                        mRootView.collectSuccess()
                    }
                }

                override fun onError(t: Throwable) {
                    super.onError(t)
                    ToastUtils.showShort(t.cause?.message)
                }
            })
    }

    fun unCollectChapter(id: Int) {
        mModel.unCollectChapter(id)
            .compose(RxUtils.applySchedulers(mRootView))
            .subscribe(object : ErrorHandleSubscriber<BaseEntity<Any>>(mErrorHandler) {
                override fun onNext(t: BaseEntity<Any>) {
                    if (t.isSuccess()) {
                        mRootView.unCollectSuccess()
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