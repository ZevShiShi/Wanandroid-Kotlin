package com.zev.wanandroidex.di.module

import com.zev.wanandroidex.mvp.contract.MainContract
import com.zev.wanandroidex.mvp.model.MainModel
import dagger.Binds
import dagger.Module


@Module
abstract class MainModule {
    @Binds
    abstract fun bindMainModel(model: MainModel?): MainContract.Model?
}