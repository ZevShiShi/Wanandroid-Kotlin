package com.zev.wanandroid.mvp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jess.arms.di.component.AppComponent
import com.zev.wanandroid.R
import com.zev.wanandroid.di.component.DaggerProComponent
import com.zev.wanandroid.mvp.base.BaseMvpFragment
import com.zev.wanandroid.mvp.contract.ProContract
import com.zev.wanandroid.mvp.presenter.ProPresenter

/**
 * A simple [Fragment] subclass.
 */
class ProFragment : BaseMvpFragment<ProPresenter>(), ProContract.View {

    override fun initView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_pro, container, false)
    }

    override fun lazyLoadData() {
        TODO("Not yet implemented")
    }

    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerProComponent.builder()
            .appComponent(appComponent)
            .view(this)
            .build()
            .inject(this)
    }

    override fun initData(savedInstanceState: Bundle?) {
        TODO("Not yet implemented")
    }

    override fun setData(data: Any?) {
        TODO("Not yet implemented")
    }

    override fun showMessage(message: String) {
        TODO("Not yet implemented")
    }
}
