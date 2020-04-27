package com.zev.wanandroid.mvp.model

import android.app.Application
import com.google.gson.Gson
import com.jess.arms.di.scope.FragmentScope
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel
import com.zev.wanandroid.mvp.contract.ProContract
import javax.inject.Inject


@FragmentScope
class ProModel @Inject constructor(repositoryManager: IRepositoryManager?) :
    BaseModel(repositoryManager),
    ProContract.Model {
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