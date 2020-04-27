package com.zev.wanandroid.di.component

import com.jess.arms.di.component.AppComponent
import com.jess.arms.di.scope.ActivityScope
import com.zev.wanandroid.di.module.WebModule
import com.zev.wanandroid.mvp.contract.WebContract
import com.zev.wanandroid.mvp.ui.activity.WebActivity
import dagger.BindsInstance
import dagger.Component

@ActivityScope
@Component(modules = [WebModule::class], dependencies = [AppComponent::class])
interface WebComponent {

    fun inject(activity: WebActivity)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun view(view: WebContract.View): Builder

        fun appComponent(appComponent: AppComponent): Builder

        fun build(): WebComponent
    }
}