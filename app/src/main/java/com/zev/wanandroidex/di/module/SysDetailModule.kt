package com.zev.wanandroidex.di.module

import com.zev.wanandroidex.mvp.contract.SysDetailContract
import com.zev.wanandroidex.mvp.model.SysDetailModel
import dagger.Binds
import dagger.Module


@Module
abstract class SysDetailModule {
    @Binds
    abstract fun bindSysDetailModel(model: SysDetailModel?): SysDetailContract.Model?
}