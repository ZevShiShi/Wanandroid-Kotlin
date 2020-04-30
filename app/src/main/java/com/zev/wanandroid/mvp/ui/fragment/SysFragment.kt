package com.zev.wanandroid.mvp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.jess.arms.di.component.AppComponent

import com.zev.wanandroid.R
import com.zev.wanandroid.di.component.DaggerSysComponent
import com.zev.wanandroid.mvp.base.BaseMvpFragment
import com.zev.wanandroid.mvp.contract.SysContract
import com.zev.wanandroid.mvp.model.entity.SysEntity
import com.zev.wanandroid.mvp.presenter.SysPresenter
import com.zev.wanandroid.mvp.ui.adapter.SysAdapter
import com.zev.wanandroid.mvp.ui.adapter.SysChildAdapter
import kotlinx.android.synthetic.main.fragment_sys.*

/**
 * A simple [Fragment] subclass.
 */
class SysFragment : BaseMvpFragment<SysPresenter>(), SysContract.View {

    private var sysAdapter: SysAdapter? = null
    private var sysChildAdapter: SysChildAdapter? = null

    companion object{
        fun getInstance():Fragment {
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

    override fun initData(savedInstanceState: Bundle?) {
        rvSysChild.layoutManager = GridLayoutManager(activity,2)
        sysChildAdapter = SysChildAdapter(R.layout.sys_item)
        sysChildAdapter?.bindToRecyclerView(rvSysChild)

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
