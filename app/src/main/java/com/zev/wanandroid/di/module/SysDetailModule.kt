package com.zev.wanandroid.di.module

import com.zev.wanandroid.mvp.contract.SysDetailContract
import com.zev.wanandroid.mvp.contract.WebContract
import com.zev.wanandroid.mvp.model.SysDetailModel
import com.zev.wanandroid.mvp.model.WebModel
import dagger.Binds
import dagger.Module


@Module
abstract class SysDetailModule {
    @Binds
    abstract fun bindSysDetailModel(model: SysDetailModel?): SysDetailContract.Model?
}