package com.zev.wanandroid.di.component

import com.jess.arms.di.component.AppComponent
import com.jess.arms.di.scope.FragmentScope
import com.zev.wanandroid.di.module.ProModule
import com.zev.wanandroid.di.module.SysModule
import com.zev.wanandroid.mvp.contract.ProContract
import com.zev.wanandroid.mvp.contract.SysContract
import com.zev.wanandroid.mvp.ui.fragment.ProFragment
import com.zev.wanandroid.mvp.ui.fragment.SysFragment
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