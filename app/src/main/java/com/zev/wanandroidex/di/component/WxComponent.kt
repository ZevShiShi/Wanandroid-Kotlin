package com.zev.wanandroidex.di.component

import com.jess.arms.di.component.AppComponent
import com.jess.arms.di.scope.FragmentScope
import com.zev.wanandroidex.di.module.ProModule
import com.zev.wanandroidex.di.module.WxModule
import com.zev.wanandroidex.mvp.contract.ProContract
import com.zev.wanandroidex.mvp.contract.WxContract
import com.zev.wanandroidex.mvp.ui.fragment.ProFragment
import com.zev.wanandroidex.mvp.ui.fragment.WxFragment
import dagger.BindsInstance
import dagger.Component

@FragmentScope
@Component(modules = [WxModule::class], dependencies = [AppComponent::class])
interface WxComponent {

    fun inject(fragment: WxFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun view(view: WxContract.View): Builder

        fun appComponent(appComponent: AppComponent): Builder

        fun build(): WxComponent
    }
}