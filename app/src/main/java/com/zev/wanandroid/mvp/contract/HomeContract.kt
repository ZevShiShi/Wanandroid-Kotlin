package com.zev.wanandroid.mvp.contract

import com.jess.arms.mvp.IModel
import com.jess.arms.mvp.IView
import com.zev.wanandroid.mvp.model.base.BaseArrayEntity
import com.zev.wanandroid.mvp.model.base.BaseEntity
import com.zev.wanandroid.mvp.model.entity.BannerEntity
import com.zev.wanandroid.mvp.model.entity.ChapterEntity
import com.zev.wanandroid.mvp.model.entity.ChapterPageEntity
import io.reactivex.Observable

interface HomeContract {
    interface View : IView {
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