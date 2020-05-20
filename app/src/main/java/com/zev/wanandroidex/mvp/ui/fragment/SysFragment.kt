package com.zev.wanandroidex.mvp.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ActivityUtils
import com.jess.arms.di.component.AppComponent

import com.zev.wanandroidex.R
import com.zev.wanandroidex.di.component.DaggerSysComponent
import com.zev.wanandroidex.mvp.base.BaseMvpFragment
import com.zev.wanandroidex.mvp.contract.SysContract
import com.zev.wanandroidex.mvp.model.entity.SysEntity
import com.zev.wanandroidex.mvp.presenter.SysPresenter
import com.zev.wanandroidex.mvp.ui.activity.SysDetailActivity
import com.zev.wanandroidex.mvp.ui.adapter.SysAdapter
import com.zev.wanandroidex.mvp.ui.adapter.SysChildAdapter
import kotlinx.android.synthetic.main.fragment_sys.*

/**
 * A simple [Fragment] subclass.
 */
class SysFragment : BaseMvpFragment<SysPresenter>(), SysContract.View {

    private var sysAdapter: SysAdapter? = null
    private var sysChildAdapter: SysChildAdapter? = null

    companion object {
        fun getInstance(): Fragment {
            return SysFragment()
        }
    }

    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerSysComponent.builder()
            .appComponent(appComponent)
            .view(this)
            .build()
            .inject(this)
    }

    override fun lazyLoadData() {
        initStatusLayoutManager(llRoot)
        rvSysChild.layoutManager = GridLayoutManager(activity, 2)
        sysChildAdapter = SysChildAdapter(R.layout.sys_item)
        sysChildAdapter?.bindToRecyclerView(rvSysChild)
        sysChildAdapter?.setOnItemClickListener { adapter, view, position ->
            val entity = sysChildAdapter?.data?.get(position)
            ActivityUtils.startActivity(
                Intent(activity, SysDetailActivity::class.java)
                    .putExtra("cid", entity?.id)
                    .putExtra("title", entity?.name)
            )
        }

        rvSys.layoutManager = LinearLayoutManager(activity)
        sysAdapter = SysAdapter(R.layout.sys_item)
        sysAdapter?.bindToRecyclerView(rvSys)
        sysAdapter?.setOnItemClickListener { adapter, view, position ->
            for (t in adapter.data) {
                (t as SysEntity).select = false
            }
            (adapter.data[position] as SysEntity).select = true
            adapter.notifyDataSetChanged()
            sysChildAdapter?.setNewData((adapter.data[position] as SysEntity).children)
        }
        mPresenter?.getSys()
    }

    override fun reloadingData() {
        mPresenter?.getSys()
    }

    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun setData(data: Any?) {
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_sys
    }

    override fun getSys(entities: List<SysEntity>) {
        entities[0].select = true
        sysAdapter?.setNewData(entities)
        sysChildAdapter?.setNewData(entities[0].children)
    }

}
