package com.zev.wanandroidex.mvp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jess.arms.di.component.AppComponent
import com.zev.wanandroidex.R
import com.zev.wanandroidex.di.component.DaggerWxComponent
import com.zev.wanandroidex.mvp.base.BaseMvpFragment
import com.zev.wanandroidex.mvp.contract.WxContract
import com.zev.wanandroidex.mvp.presenter.WxPresenter

/**
 * A simple [Fragment] subclass.
 */
class WxFragment : BaseMvpFragment<WxPresenter>() ,WxContract.View{

    override fun getLayoutId(): Int {
        return R.layout.fragment_wx
    }

    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerWxComponent.builder()
            .appComponent(appComponent)
            .view(this)
            .build()
            .inject(this)
    }

    override fun initData(savedInstanceState: Bundle?) {

    }


}
