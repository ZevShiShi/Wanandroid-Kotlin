package com.zev.wanandroid.di.module

import com.zev.wanandroid.mvp.contract.ProContract
import com.zev.wanandroid.mvp.contract.SysContract
import com.zev.wanandroid.mvp.model.ProModel
import com.zev.wanandroid.mvp.model.SysModel
import dagger.Binds
import dagger.Module


@Module
abstract class SysModule {
    @Binds
    abstract fun bindSysModel(model: SysModel?): SysContract.Model?
}