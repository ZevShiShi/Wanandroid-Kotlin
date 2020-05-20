package com.zev.wanandroidex.mvp.ui.activity

import android.os.Bundle
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.jess.arms.di.component.AppComponent
import com.zev.wanandroidex.R
import com.zev.wanandroidex.di.component.DaggerMainComponent
import com.zev.wanandroidex.mvp.base.BaseMvpActivity
import com.zev.wanandroidex.mvp.contract.MainContract
import com.zev.wanandroidex.mvp.presenter.MainPresenter
import com.zev.wanandroidex.mvp.ui.adapter.MainPagerAdapter
import com.zev.wanandroidex.mvp.ui.fragment.DemoFragment
import com.zev.wanandroidex.mvp.ui.fragment.HomeFragment
import com.zev.wanandroidex.mvp.ui.fragment.ProFragment
import com.zev.wanandroidex.mvp.ui.fragment.SysFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseMvpActivity<MainPresenter>(), MainContract.View {


    private lateinit var mAdapter: MainPagerAdapter

    private val fragmentList = arrayListOf(
        HomeFragment.getInstance(),
        ProFragment.getInstance(),
        SysFragment.getInstance(),
        DemoFragment.getInstance()
    )

    private val titles = arrayOf("首页", "项目", "体系", "干货")

    override fun initData(savedInstanceState: Bundle?) {
        mAdapter = MainPagerAdapter(supportFragmentManager)
        mAdapter.updateFragment(fragmentList)
        vpMain.adapter = mAdapter
        mainTab.setViewPager(vpMain, titles)
        vpMain.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }


            override fun onPageSelected(position: Int) {
                mainTab.setCurrentTab(position, true)
            }

        })
    }

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerMainComponent.builder()
            .appComponent(appComponent)
            .view(this)
            .build()
            .inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_main
    }


    override fun showMessage(message: String) {
    }
}
