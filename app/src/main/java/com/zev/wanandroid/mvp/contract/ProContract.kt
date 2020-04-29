package com.zev.wanandroid.mvp.contract

import com.jess.arms.mvp.IModel
import com.jess.arms.mvp.IView
import com.zev.wanandroid.mvp.base.BaseIView
import com.zev.wanandroid.mvp.model.base.BaseArrayEntity
import com.zev.wanandroid.mvp.model.entity.ProTabEntity
import io.reactivex.Observable

interface ProContract {

    interface View : BaseIView {

        fun getProTab(entities: List<ProTabEntity>)

    }

    interface Model : IModel {

        fun getProTab(): Observable<BaseArrayEntity<ProTabEntity>>
    }

}