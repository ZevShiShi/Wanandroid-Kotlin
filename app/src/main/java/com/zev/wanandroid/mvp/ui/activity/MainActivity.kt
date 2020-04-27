package com.zev.wanandroid.mvp.ui.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.jess.arms.di.component.AppComponent
import com.zev.wanandroid.R
import com.zev.wanandroid.di.component.DaggerMainComponent
import com.zev.wanandroid.mvp.base.BaseMvpActivity
import com.zev.wanandroid.mvp.contract.MainContract
import com.zev.wanandroid.mvp.presenter.MainPresenter
import com.zev.wanandroid.mvp.ui.adapter.MainPagerAdapter
import com.zev.wanandroid.mvp.ui.fragment.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseMvpActivity<MainPresenter>(), MainContract.View {


    private lateinit var mAdapter: MainPagerAdapter

    private val fragmentList = arrayListOf<Fragment>(
        HomeFragment.getInstance(),
        HomeFragment.getInstance(),
        HomeFragment.getInstance(),
        HomeFragment.getInstance()
    )

    private val titles = arrayOf("首页", "项目", "体系", "干货")

    override fun initData(savedInstanceState: Bundle?) {
        mAdapter = MainPagerAdapter(
            supportFragmentManager, fragmentList
            , FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )
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
