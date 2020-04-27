package com.zev.wanandroid.di.module

import com.zev.wanandroid.mvp.contract.ProContract
import com.zev.wanandroid.mvp.model.ProModel
import dagger.Binds
import dagger.Module


@Module
abstract class ProModule {
    @Binds
    abstract fun bindProModel(model: ProModel?): ProContract.Model?
}