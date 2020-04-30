package com.zev.wanandroid.mvp.contract

import com.jess.arms.mvp.IModel
import com.jess.arms.mvp.IView
import com.zev.wanandroid.mvp.base.BaseIView
import com.zev.wanandroid.mvp.model.base.BaseArrayEntity
import com.zev.wanandroid.mvp.model.entity.ProTabEntity
import com.zev.wanandroid.mvp.model.entity.SysEntity
import io.reactivex.Observable

interface SysContract {

    interface View : BaseIView {

        fun getSys(entities: List<SysEntity>)
    }

    interface Model : IModel {
        fun getSys(): Observable<BaseArrayEntity<SysEntity>>
    }

}