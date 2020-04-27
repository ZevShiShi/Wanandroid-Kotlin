package com.zev.wanandroid.di.component

import com.jess.arms.di.component.AppComponent
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.di.scope.FragmentScope
import com.zev.wanandroid.di.module.HomeModule
import com.zev.wanandroid.di.module.MainModule
import com.zev.wanandroid.mvp.contract.HomeContract
import com.zev.wanandroid.mvp.contract.MainContract
import com.zev.wanandroid.mvp.ui.activity.MainActivity
import com.zev.wanandroid.mvp.ui.fragment.HomeFragment
import dagger.BindsInstance
import dagger.Component

@FragmentScope
@Component(modules = [HomeModule::class], dependencies = [AppComponent::class])
interface HomeComponent {

    fun inject(fragment: HomeFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun view(view: HomeContract.View): Builder

        fun appComponent(appComponent: AppComponent): Builder

        fun build(): HomeComponent
    }
}