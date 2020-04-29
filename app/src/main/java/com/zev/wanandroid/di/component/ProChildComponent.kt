package com.zev.wanandroid.di.component

import com.jess.arms.di.component.AppComponent
import com.jess.arms.di.scope.FragmentScope
import com.zev.wanandroid.di.module.ProChildModule
import com.zev.wanandroid.di.module.ProModule
import com.zev.wanandroid.mvp.contract.ProChildContract
import com.zev.wanandroid.mvp.contract.ProContract
import com.zev.wanandroid.mvp.ui.fragment.ProChildFragment
import com.zev.wanandroid.mvp.ui.fragment.ProFragment
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