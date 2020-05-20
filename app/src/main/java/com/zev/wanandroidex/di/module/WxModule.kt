package com.zev.wanandroidex.di.module

import com.zev.wanandroidex.mvp.contract.ProContract
import com.zev.wanandroidex.mvp.contract.WxContract
import com.zev.wanandroidex.mvp.model.ProModel
import com.zev.wanandroidex.mvp.model.WxModel
import dagger.Binds
import dagger.Module


@Module
abstract class WxModule {
    @Binds
    abstract fun bindWxModel(model: WxModel?): WxContract.Model?
}