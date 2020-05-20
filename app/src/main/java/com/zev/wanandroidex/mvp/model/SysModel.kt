package com.zev.wanandroidex.mvp.model

import android.app.Application
import com.google.gson.Gson
import com.jess.arms.di.scope.FragmentScope
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel
import com.zev.wanandroidex.mvp.contract.SysContract
import com.zev.wanandroidex.mvp.model.api.service.MainService
import com.zev.wanandroidex.mvp.model.base.BaseArrayEntity
import com.zev.wanandroidex.mvp.model.entity.SysEntity
import io.reactivex.Observable
import javax.inject.Inject


@FragmentScope
class SysModel @Inject constructor(repositoryManager: IRepositoryManager?) :
    BaseModel(repositoryManager),
    SysContract.Model {
    @set:Inject
    var mGson: Gson? = null

    @set:Inject
    var mApplication: Application? = null
    override fun getSys(): Observable<BaseArrayEntity<SysEntity>> {
        return mRepositoryManager.obtainRetrofitService(MainService::class.java).getSys()
    }


    override fun onDestroy() {
        super.onDestroy()
        mGson = null
        mApplication = null
    }
}