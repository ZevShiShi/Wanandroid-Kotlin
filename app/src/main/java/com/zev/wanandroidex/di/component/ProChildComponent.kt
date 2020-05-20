package com.zev.wanandroidex.di.component

import com.jess.arms.di.component.AppComponent
import com.jess.arms.di.scope.FragmentScope
import com.zev.wanandroidex.di.module.ProChildModule
import com.zev.wanandroidex.mvp.contract.ProChildContract
import com.zev.wanandroidex.mvp.ui.fragment.ProChildFragment
import dagger.BindsInstance
import dagger.Component

@FragmentScope
@Component(modules = [ProChildModule::class], dependencies = [AppComponent::class])
interface ProChildComponent {

    fun inject(fragment: ProChildFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun view(view: ProChildContract.View): Builder

        fun appComponent(appComponent: AppComponent): Builder

        fun build(): ProChildComponent
    }
}