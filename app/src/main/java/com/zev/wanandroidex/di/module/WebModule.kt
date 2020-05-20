package com.zev.wanandroidex.di.module

import com.zev.wanandroidex.mvp.contract.WebContract
import com.zev.wanandroidex.mvp.model.WebModel
import dagger.Binds
import dagger.Module


@Module
abstract class WebModule {
    @Binds
    abstract fun bindWebModel(model: WebModel?): WebContract.Model?
}