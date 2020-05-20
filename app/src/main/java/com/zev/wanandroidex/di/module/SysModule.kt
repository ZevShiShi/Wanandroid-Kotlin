package com.zev.wanandroidex.di.module

import com.zev.wanandroidex.mvp.contract.SysContract
import com.zev.wanandroidex.mvp.model.SysModel
import dagger.Binds
import dagger.Module


@Module
abstract class SysModule {
    @Binds
    abstract fun bindSysModel(model: SysModel?): SysContract.Model?
}