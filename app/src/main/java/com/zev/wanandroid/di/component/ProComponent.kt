package com.zev.wanandroid.di.component

import com.jess.arms.di.component.AppComponent
import com.jess.arms.di.scope.FragmentScope
import com.zev.wanandroid.di.module.ProModule
import com.zev.wanandroid.mvp.contract.ProContract
import com.zev.wanandroid.mvp.ui.fragment.ProFragment
import dagger.BindsInstance
import dagger.Component

@FragmentScope
@Component(modules = [ProModule::class], dependencies = [AppComponent::class])
interface ProComponent {

    fun inject(fragment: ProFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun view(view: ProContract.View): Builder

        fun appComponent(appComponent: AppComponent): Builder

        fun build(): ProComponent
    }
}