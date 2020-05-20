package com.zev.wanandroidex.mvp.base

import com.zev.wanandroidex.mvp.model.base.BaseArrayEntity
import com.zev.wanandroidex.mvp.model.base.BaseEntity
import io.reactivex.disposables.Disposable
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber

open class BaseErrorHandleSubscriber<T>(
    rxErrorHandler: RxErrorHandler?,
    private var view: BaseIView?
) :
    ErrorHandleSubscriber<T>(rxErrorHandler) {

    private var loadMore = false

    constructor(rxErrorHandler: RxErrorHandler?, view: BaseIView?, loadMore: Boolean)
            : this(rxErrorHandler, view) {
        this.loadMore = loadMore
    }

    override fun onSubscribe(d: Disposable) {
        super.onSubscribe(d)
        if (!loadMore) {
            view?.showLoading()
        }
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