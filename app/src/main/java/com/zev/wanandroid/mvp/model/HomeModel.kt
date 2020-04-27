package com.zev.wanandroid.mvp.model

import android.app.Application
import com.google.gson.Gson
import com.jess.arms.di.scope.FragmentScope
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel
import com.zev.wanandroid.mvp.contract.HomeContract
import com.zev.wanandroid.mvp.model.api.service.MainService
import com.zev.wanandroid.mvp.model.base.BaseArrayEntity
import com.zev.wanandroid.mvp.model.base.BaseEntity
import com.zev.wanandroid.mvp.model.entity.BannerEntity
import com.zev.wanandroid.mvp.model.entity.ChapterEntity
import com.zev.wanandroid.mvp.model.entity.ChapterPageEntity
import io.reactivex.Observable
import javax.inject.Inject


@FragmentScope
class HomeModel @Inject constructor(repositoryManager: IRepositoryManager?) :
    BaseModel(repositoryManager),
    HomeContract.Model {
    @set:Inject
    var mGson: Gson? = null

    @set:Inject
    var mApplication: Application? = null


    override fun getBanner(): Observable<BaseArrayEntity<BannerEntity>> {
        return mRepositoryManager.obtainRetrofitService(MainService::class.java).getBanner()
    }

    override fun getChapterTop(): Observable<BaseArrayEntity<ChapterEntity>> {
        return mRepositoryManager.obtainRetrofitService(MainService::class.java).getChapterTop()
    }

    override fun getHomeChapter(page: Int): Observable<BaseEntity<ChapterPageEntity>> {
        return mRepositoryManager.obtainRetrofitService(MainService::class.java)
            .getHomeChapter(page)
    }

    override fun collectChapter(id: Int): Observable<BaseEntity<Any>> {
        return mRepositoryManager.obtainRetrofitService(MainService::class.java)
            .collectChapter(id)
    }

    override fun unCollectChapter(id: Int): Observable<BaseEntity<Any>> {
        return mRepositoryManager.obtainRetrofitService(MainService::class.java)
            .unCollectChapter(id)
    }


    override fun onDestroy() {
        super.onDestroy()
        mGson = null
        mApplication = null
    }
}