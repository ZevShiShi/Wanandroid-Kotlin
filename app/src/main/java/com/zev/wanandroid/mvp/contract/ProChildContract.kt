package com.zev.wanandroid.mvp.contract

import com.jess.arms.mvp.IModel
import com.jess.arms.mvp.IView
import com.zev.wanandroid.mvp.base.BaseIView
import com.zev.wanandroid.mvp.model.base.BaseArrayEntity
import com.zev.wanandroid.mvp.model.base.BaseEntity
import com.zev.wanandroid.mvp.model.entity.ChapterEntity
import com.zev.wanandroid.mvp.model.entity.ChapterPageEntity
import com.zev.wanandroid.mvp.model.entity.ProTabEntity
import io.reactivex.Observable
import retrofit2.http.Path
import retrofit2.http.Query

interface ProChildContract {

    interface View : BaseIView {

        fun getPro(entity: ChapterPageEntity)
    }

    interface Model : IModel {

        fun getPro(page: Int, id: Int) : Observable<BaseEntity<ChapterPageEntity>>
    }

}