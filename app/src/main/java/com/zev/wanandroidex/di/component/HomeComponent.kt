package com.zev.wanandroidex.di.component

import com.jess.arms.di.component.AppComponent
import com.jess.arms.di.scope.FragmentScope
import com.zev.wanandroidex.di.module.HomeModule
import com.zev.wanandroidex.mvp.contract.HomeContract
import com.zev.wanandroidex.mvp.ui.fragment.HomeFragment
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