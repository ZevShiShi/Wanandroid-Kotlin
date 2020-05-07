package com.zev.wanandroid.di.component

import com.jess.arms.di.component.AppComponent
import com.jess.arms.di.scope.ActivityScope
import com.zev.wanandroid.di.module.SysDetailModule
import com.zev.wanandroid.di.module.WebModule
import com.zev.wanandroid.mvp.contract.SysDetailContract
import com.zev.wanandroid.mvp.contract.WebContract
import com.zev.wanandroid.mvp.ui.activity.SysDetailActivity
import com.zev.wanandroid.mvp.ui.activity.WebActivity
import dagger.BindsInstance
import dagger.Component

@ActivityScope
@Component(modules = [SysDetailModule::class], dependencies = [AppComponent::class])
interface SysDetailComponent {

    fun inject(activity: SysDetailActivity)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun view(view: SysDetailContract.View): Builder

        fun appComponent(appComponent: AppComponent): Builder

        fun build(): SysDetailComponent
    }
}