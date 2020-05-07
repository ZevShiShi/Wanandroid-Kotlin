package com.zev.wanandroid.mvp.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ActivityUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.jess.arms.di.component.AppComponent
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.zev.wanandroid.R
import com.zev.wanandroid.di.component.DaggerSysDetailComponent
import com.zev.wanandroid.mvp.base.BaseMvpActivity
import com.zev.wanandroid.mvp.contract.SysDetailContract
import com.zev.wanandroid.mvp.contract.WebContract
import com.zev.wanandroid.mvp.model.entity.ChapterPageEntity
import com.zev.wanandroid.mvp.presenter.SysDetailPresenter
import com.zev.wanandroid.mvp.presenter.WebPresenter
import com.zev.wanandroid.mvp.ui.adapter.SysDetailAdapter
import kotlinx.android.synthetic.main.activity_sys.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class SysDetailActivity : BaseMvpActivity<SysDetailPresenter>(), SysDetailContract.View {

    private var mAdapter: SysDetailAdapter? = null
    private var cid = 0
    private var curPage = 0
    private var total = 0

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerSysDetailComponent.builder()
            .appComponent(appComponent)
            .view(this)
            .build()
            .inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_sys
    }


    override fun initData(savedInstanceState: Bundle?) {
        initStatusLayoutManager(rvSysDetail)
        cid = intent.getIntExtra("cid", 0)
        val name = intent.getStringExtra("title")
        title = name
//        tvTitle.text = name
        rvSysDetail.layoutManager = LinearLayoutManager(this)
        mAdapter = SysDetailAdapter(R.layout.sys_detail_item)
        mAdapter?.setEnableLoadMore(true)
        mAdapter?.setOnLoadMoreListener({
            if (mAdapter?.data?.size!! >= total) {
                mAdapter?.loadMoreEnd()
            } else {
                mPresenter?.getSysDetail(curPage, cid, true)
            }
        }, rvSysDetail)
        mAdapter?.disableLoadMoreIfNotFullPage()
        mAdapter?.setOnItemClickListener { adapter, view, position ->
            val chapter = mAdapter?.data!![position]
            ActivityUtils.startActivity(
                Intent(this@SysDetailActivity, WebActivity::class.java)
                    .putExtra("url", chapter.link)
                    .putExtra("id", chapter.id)
                    .putExtra("collect", chapter.collect)
            )
        }
        rvSysDetail.adapter = mAdapter

        refreshLayout.setOnRefreshListener {
            curPage = 0
            mPresenter?.getSysDetail(page = curPage, id = cid, loadMore = true)
        }
        mPresenter?.getSysDetail(curPage, cid, false)
    }


    override fun getSysDetail(entity: ChapterPageEntity) {
        total = entity.total
        curPage++
        refreshLayout.finishRefresh()
        if (entity.curPage == 1) {
            mAdapter?.setNewData(entity.datas)
            return
        }
        mAdapter?.addData(entity.datas)
        mAdapter?.loadMoreComplete()
    }

    override fun showErrorLayout() {
        super.showErrorLayout()
        refreshLayout.finishRefresh()
        mAdapter?.loadMoreFail()
    }
}
