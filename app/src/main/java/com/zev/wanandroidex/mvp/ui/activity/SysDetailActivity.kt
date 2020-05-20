package com.zev.wanandroidex.mvp.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ActivityUtils
import com.jess.arms.di.component.AppComponent
import com.zev.wanandroidex.R
import com.zev.wanandroidex.di.component.DaggerSysDetailComponent
import com.zev.wanandroidex.mvp.base.BaseMvpActivity
import com.zev.wanandroidex.mvp.contract.SysDetailContract
import com.zev.wanandroidex.mvp.model.entity.ChapterPageEntity
import com.zev.wanandroidex.mvp.presenter.SysDetailPresenter
import com.zev.wanandroidex.mvp.ui.adapter.SysDetailAdapter
import kotlinx.android.synthetic.main.activity_sys.*

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
