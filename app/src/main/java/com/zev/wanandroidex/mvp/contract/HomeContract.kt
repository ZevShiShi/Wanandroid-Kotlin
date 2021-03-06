package com.zev.wanandroidex.mvp.contract

import com.jess.arms.mvp.IModel
import com.zev.wanandroidex.mvp.base.BaseIView
import com.zev.wanandroidex.mvp.model.base.BaseArrayEntity
import com.zev.wanandroidex.mvp.model.base.BaseEntity
import com.zev.wanandroidex.mvp.model.entity.BannerEntity
import com.zev.wanandroidex.mvp.model.entity.ChapterEntity
import com.zev.wanandroidex.mvp.model.entity.ChapterPageEntity
import io.reactivex.Observable

interface HomeContract {
    interface View : BaseIView {
        fun getBanner(entities: List<BannerEntity>)

        fun getChapterTop(entities: List<ChapterEntity>)

        fun getHomeChapter(entity: ChapterPageEntity)

        fun collectSuccess()

        fun unCollectSuccess()
    }

    interface Model : IModel {
        fun getBanner(): Observable<BaseArrayEntity<BannerEntity>>

        fun getChapterTop(): Observable<BaseArrayEntity<ChapterEntity>>

        fun getHomeChapter(page: Int): Observable<BaseEntity<ChapterPageEntity>>

        fun collectChapter(id: Int): Observable<BaseEntity<Any>>

        fun unCollectChapter(id: Int): Observable<BaseEntity<Any>>
    }

}