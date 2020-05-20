package com.zev.wanandroidex.mvp.contract

import com.jess.arms.mvp.IModel
import com.zev.wanandroidex.mvp.base.BaseIView
import com.zev.wanandroidex.mvp.model.base.BaseArrayEntity
import com.zev.wanandroidex.mvp.model.entity.ProTabEntity
import io.reactivex.Observable

interface ProContract {

    interface View : BaseIView {

        fun getProTab(entities: List<ProTabEntity>)

    }

    interface Model : IModel {

        fun getProTab(): Observable<BaseArrayEntity<ProTabEntity>>
    }

}