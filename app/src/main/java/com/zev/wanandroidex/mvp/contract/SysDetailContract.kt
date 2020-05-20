package com.zev.wanandroidex.mvp.contract

import com.jess.arms.mvp.IModel
import com.zev.wanandroidex.mvp.base.BaseIView
import com.zev.wanandroidex.mvp.model.base.BaseEntity
import com.zev.wanandroidex.mvp.model.entity.ChapterPageEntity
import io.reactivex.Observable

interface SysDetailContract {

    interface View : BaseIView {

        fun getSysDetail(entity: ChapterPageEntity)
    }

    interface Model : IModel {
        fun getSysDetail(page: Int, id: Int): Observable<BaseEntity<ChapterPageEntity>>
    }

}