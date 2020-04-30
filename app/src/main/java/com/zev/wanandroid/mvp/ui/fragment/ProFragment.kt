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
import com.zev.wanandroid.mvp.model.entity.ProTabEntity
import com.zev.wanandroid.mvp.presenter.ProPresenter
import com.zev.wanandroid.mvp.ui.adapter.MainPagerAdapter
import kotlinx.android.synthetic.main.fragment_pro.*

/**
 * A simple [Fragment] subclass.
 */
class ProFragment : BaseMvpFragment<ProPresenter>(), ProContract.View {

    private var mAdapter: MainPagerAdapter? = null
    private var fragmentList = ArrayList<Fragment>()
    private var listTab = ArrayList<String>()
    companion object{
        fun getInstance():Fragment {
            return ProFragment()
        }
    }


    override fun lazyLoadData() {
        initStatusLayoutManager(llRoot)
        mPresenter?.getPro()
    }

    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerProComponent.builder()
            .appComponent(appComponent)
            .view(this)
            .build()
            .inject(this)
    }

    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun setData(data: Any?) {

    }

    override fun getProTab(entities: List<ProTabEntity>) {
        fragmentList.clear()
        listTab.clear()
        for (e in entities) {
            listTab.add(e.name)
            fragmentList.add(ProChildFragment.getInstance(e.id))
        }
        mAdapter = MainPagerAdapter(childFragmentManager)
        mAdapter?.updateFragment(fragmentList)
        vpPro.adapter = mAdapter
        proTab.setViewPager(vpPro, listTab.toTypedArray())
    }

    override fun getLayoutId(): Int {
      return R.layout.fragment_pro
    }
}
