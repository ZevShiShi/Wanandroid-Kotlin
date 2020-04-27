package com.zev.wanandroid.di.component

import com.jess.arms.di.component.AppComponent
import com.jess.arms.di.scope.ActivityScope
import com.zev.wanandroid.di.module.MainModule
import com.zev.wanandroid.mvp.contract.MainContract
import com.zev.wanandroid.mvp.ui.activity.MainActivity
import dagger.BindsInstance
import dagger.Component

@ActivityScope
@Component(modules = [MainModule::class], dependencies = [AppComponent::class])
interface MainComponent {

    fun inject(activity: MainActivity)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun view(view: MainContract.View): Builder

        fun appComponent(appComponent: AppComponent): Builder

        fun build(): MainComponent
    }
}