package com.zev.wanandroidex.di.module

import com.zev.wanandroidex.mvp.contract.ProChildContract
import com.zev.wanandroidex.mvp.model.ProChildModel
import dagger.Binds
import dagger.Module


@Module
abstract class ProChildModule {
    @Binds
    abstract fun bindProChildModel(model: ProChildModel?): ProChildContract.Model?
}