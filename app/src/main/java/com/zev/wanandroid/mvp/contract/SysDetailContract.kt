package com.zev.wanandroid.mvp.contract

import com.jess.arms.mvp.IModel
import com.jess.arms.mvp.IView
import com.zev.wanandroid.mvp.base.BaseIView
import com.zev.wanandroid.mvp.model.base.BaseEntity
import com.zev.wanandroid.mvp.model.entity.ChapterPageEntity
import io.reactivex.Observable
import retrofit2.http.Path
import retrofit2.http.Query

interface SysDetailContract {

    interface View : BaseIView {

        fun getSysDetail(entity: ChapterPageEntity)
    }

    interface Model : IModel {
        fun getSysDetail(page: Int, id: Int): Observable<BaseEntity<ChapterPageEntity>>
    }

}