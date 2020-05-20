package com.zev.wanandroidex.mvp.model

import android.app.Application
import com.google.gson.Gson
import com.jess.arms.di.scope.FragmentScope
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel
import com.zev.wanandroidex.mvp.contract.ProContract
import com.zev.wanandroidex.mvp.contract.WxContract
import com.zev.wanandroidex.mvp.model.api.service.MainService
import com.zev.wanandroidex.mvp.model.base.BaseArrayEntity
import com.zev.wanandroidex.mvp.model.entity.ProTabEntity
import io.reactivex.Observable
import javax.inject.Inject


@FragmentScope
class WxModel @Inject constructor(repositoryManager: IRepositoryManager?) :
    BaseModel(repositoryManager),
    WxContract.Model {
    @set:Inject
    var mGson: Gson? = null

    @set:Inject
    var mApplication: Application? = null

    override fun onDestroy() {
        super.onDestroy()
        mGson = null
        mApplication = null
    }
}