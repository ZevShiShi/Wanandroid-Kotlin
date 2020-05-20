package com.zev.wanandroidex.di.module

import com.zev.wanandroidex.mvp.contract.HomeContract
import com.zev.wanandroidex.mvp.model.HomeModel
import dagger.Binds
import dagger.Module


@Module
abstract class HomeModule {
    @Binds
    abstract fun bindHomeModel(model: HomeModel?): HomeContract.Model?
}