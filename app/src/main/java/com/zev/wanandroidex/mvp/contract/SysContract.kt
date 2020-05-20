package com.zev.wanandroidex.mvp.contract

import com.jess.arms.mvp.IModel
import com.zev.wanandroidex.mvp.base.BaseIView
import com.zev.wanandroidex.mvp.model.base.BaseArrayEntity
import com.zev.wanandroidex.mvp.model.entity.SysEntity
import io.reactivex.Observable

interface SysContract {

    interface View : BaseIView {

        fun getSys(entities: List<SysEntity>)
    }

    interface Model : IModel {
        fun getSys(): Observable<BaseArrayEntity<SysEntity>>
    }

}