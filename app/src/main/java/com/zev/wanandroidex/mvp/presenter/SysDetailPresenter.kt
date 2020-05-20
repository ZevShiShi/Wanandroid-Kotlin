package com.zev.wanandroidex.mvp.presenter

import android.app.Application
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import com.zev.wanandroidex.app.utils.RxUtils
import com.zev.wanandroidex.mvp.base.BaseErrorHandleSubscriber
import com.zev.wanandroidex.mvp.contract.SysDetailContract
import com.zev.wanandroidex.mvp.model.base.BaseEntity
import com.zev.wanandroidex.mvp.model.entity.ChapterPageEntity
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject

@ActivityScope
class SysDetailPresenter @Inject constructor(
    model: SysDetailContract.Model?,
    rootView: SysDetailContract.View?
) :
    BasePresenter<SysDetailContract.Model, SysDetailContract.View>(model, rootView) {

    @set:Inject
    var mErrorHandler: RxErrorHandler? = null

    @set:Inject
    var mApplication: Application? = null

    @set:Inject
    var mImageLoader: ImageLoader? = null

    @set:Inject
    var mAppManager: AppManager? = null


    fun getSysDetail(page: Int, id: Int, loadMore: Boolean) {
        mModel.getSysDetail(page, id)
            .compose(RxUtils.applySchedulers(mRootView))
            .subscribe(object :
                BaseErrorHandleSubscriber<BaseEntity<ChapterPageEntity>>(
                    mErrorHandler,
                    mRootView,
                    loadMore
                ) {
                override fun onNext(t: BaseEntity<ChapterPageEntity>) {
                    super.onNext(t)
                    if (t.isSuccess() && t.hasData()) {
                        mRootView.getSysDetail(t.data)
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