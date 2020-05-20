package com.zev.wanandroidex.di.module

import com.zev.wanandroidex.mvp.contract.ProContract
import com.zev.wanandroidex.mvp.model.ProModel
import dagger.Binds
import dagger.Module


@Module
abstract class ProModule {
    @Binds
    abstract fun bindProModel(model: ProModel?): ProContract.Model?
}