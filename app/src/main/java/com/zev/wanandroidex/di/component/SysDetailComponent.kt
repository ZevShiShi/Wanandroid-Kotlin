package com.zev.wanandroidex.di.component

import com.jess.arms.di.component.AppComponent
import com.jess.arms.di.scope.ActivityScope
import com.zev.wanandroidex.di.module.SysDetailModule
import com.zev.wanandroidex.mvp.contract.SysDetailContract
import com.zev.wanandroidex.mvp.ui.activity.SysDetailActivity
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