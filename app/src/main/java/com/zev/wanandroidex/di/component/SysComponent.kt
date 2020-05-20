package com.zev.wanandroidex.di.component

import com.jess.arms.di.component.AppComponent
import com.jess.arms.di.scope.FragmentScope
import com.zev.wanandroidex.di.module.SysModule
import com.zev.wanandroidex.mvp.contract.SysContract
import com.zev.wanandroidex.mvp.ui.fragment.SysFragment
import dagger.BindsInstance
import dagger.Component

@FragmentScope
@Component(modules = [SysModule::class], dependencies = [AppComponent::class])
interface SysComponent {

    fun inject(fragment: SysFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun view(view: SysContract.View): Builder

        fun appComponent(appComponent: AppComponent): Builder

        fun build(): SysComponent
    }
}