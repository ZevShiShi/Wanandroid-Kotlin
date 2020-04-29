package com.zev.wanandroid.mvp.base

import com.blankj.utilcode.util.LogUtils
import com.zev.wanandroid.mvp.model.base.BaseArrayEntity
import com.zev.wanandroid.mvp.model.base.BaseEntity
import io.reactivex.disposables.Disposable
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber

open class BaseErrorHandleSubscriber<T>(rxErrorHandler: RxErrorHandler?, private var view: BaseIView?) :
    ErrorHandleSubscriber<T>(rxErrorHandler) {

    override fun onSubscribe(d: Disposable) {
        super.onSubscribe(d)
        view?.showLoading()
    }

    override fun onNext(t: T) {
        if (t is BaseEntity<*>) {
            if (t.isSuccess() && t.hasData()) {
                view?.showSuccessLayout()
                return
            }
        }
        if (t is BaseArrayEntity<*>) {
            if (t.isSuccess() && t.hasData()) {
                view?.showSuccessLayout()
                return
            }
        }
        if (t is BaseEntity<*>) {
            if (t.isSuccess()) {
                view?.showSuccessLayout()
                return
            }
        }
        if (t is BaseArrayEntity<*>) {
            if (t.isSuccess()) {
                view?.showSuccessLayout()
                return
            }
        }
        view?.showEmptyLayout()
    }

    override fun onError(t: Throwable) {
        super.onError(t)
        view?.showErrorLayout()
    }


}