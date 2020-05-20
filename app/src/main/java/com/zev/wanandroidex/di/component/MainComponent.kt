package com.zev.wanandroidex.di.component

import com.jess.arms.di.component.AppComponent
import com.jess.arms.di.scope.ActivityScope
import com.zev.wanandroidex.di.module.MainModule
import com.zev.wanandroidex.mvp.contract.MainContract
import com.zev.wanandroidex.mvp.ui.activity.MainActivity
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