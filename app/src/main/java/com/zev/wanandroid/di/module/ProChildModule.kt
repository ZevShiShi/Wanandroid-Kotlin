package com.zev.wanandroid.di.module

import com.zev.wanandroid.mvp.contract.ProChildContract
import com.zev.wanandroid.mvp.contract.ProContract
import com.zev.wanandroid.mvp.model.ProChildModel
import com.zev.wanandroid.mvp.model.ProModel
import dagger.Binds
import dagger.Module


@Module
abstract class ProChildModule {
    @Binds
    abstract fun bindProChildModel(model: ProChildModel?): ProChildContract.Model?
}