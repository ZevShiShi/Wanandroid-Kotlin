package com.zev.wanandroidex.mvp.model

import android.app.Application
import com.google.gson.Gson
import com.jess.arms.di.scope.FragmentScope
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel
import com.zev.wanandroidex.mvp.contract.ProChildContract
import com.zev.wanandroidex.mvp.model.api.service.MainService
import com.zev.wanandroidex.mvp.model.base.BaseEntity
import com.zev.wanandroidex.mvp.model.entity.ChapterPageEntity
import io.reactivex.Observable
import javax.inject.Inject


@FragmentScope
class ProChildModel @Inject constructor(repositoryManager: IRepositoryManager?) :
    BaseModel(repositoryManager),
    ProChildContract.Model {
    @set:Inject
    var mGson: Gson? = null

    @set:Inject
    var mApplication: Application? = null
    override fun getPro(page: Int, id: Int): Observable<BaseEntity<ChapterPageEntity>> {
        return mRepositoryManager.obtainRetrofitService(MainService::class.java).getPro(page, id)
    }


    override fun onDestroy() {
        super.onDestroy()
        mGson = null
        mApplication = null
    }
}