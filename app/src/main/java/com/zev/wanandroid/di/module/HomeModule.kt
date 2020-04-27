package com.zev.wanandroid.di.module

import com.zev.wanandroid.mvp.contract.HomeContract
import com.zev.wanandroid.mvp.contract.MainContract
import com.zev.wanandroid.mvp.model.HomeModel
import com.zev.wanandroid.mvp.model.MainModel
import dagger.Binds
import dagger.Module


@Module
abstract class HomeModule {
    @Binds
    abstract fun bindHomeModel(model: HomeModel?): HomeContract.Model?
}