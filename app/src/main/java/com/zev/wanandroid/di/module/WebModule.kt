package com.zev.wanandroid.di.module

import com.zev.wanandroid.mvp.contract.WebContract
import com.zev.wanandroid.mvp.model.WebModel
import dagger.Binds
import dagger.Module


@Module
abstract class WebModule {
    @Binds
    abstract fun bindWebModel(model: WebModel?): WebContract.Model?
}