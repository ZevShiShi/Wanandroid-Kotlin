package com.zev.wanandroid.di.module

import com.zev.wanandroid.mvp.contract.MainContract
import com.zev.wanandroid.mvp.model.MainModel
import dagger.Binds
import dagger.Module


@Module
abstract class MainModule {
    @Binds
    abstract fun bindMainModel(model: MainModel?): MainContract.Model?
}