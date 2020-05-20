package com.zev.wanandroidex.mvp.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ActivityUtils
import com.jess.arms.di.component.AppComponent
import com.zev.wanandroidex.R
import com.zev.wanandroidex.di.component.DaggerProChildComponent

import com.zev.wanandroidex.mvp.base.BaseMvpFragment
import com.zev.wanandroidex.mvp.contract.ProChildContract
import com.zev.wanandroidex.mvp.model.entity.ChapterPageEntity
import com.zev.wanandroidex.mvp.presenter.ProChildPresenter
import com.zev.wanandroidex.mvp.ui.activity.WebActivity
import com.zev.wanandroidex.mvp.ui.adapter.ProChildAdapter
import kotlinx.android.synthetic.main.fragment_pro_child.*

/**
 * A simple [Fragment] subclass.
 */
class ProChildFragment : BaseMvpFragment<ProChildPresenter>(), ProChildContract.View {

    private var cid = 0
    private var mAdapter: ProChildAdapter? = null
    private var page = 1
    private var total = 0

    companion object {
        fun getInstance(id: Int): Fragment {
            val f = ProChildFragment()
            val b = Bundle()
            b.putInt("cid", id)
            f.arguments = b
            return f
        }
    }

    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerProChildComponent.builder()
            .appComponent(appComponent)
            .view(this)
            .build()
            .inject(this)
    }

    override fun initData(savedInstanceState: Bundle?) {
    }

    override fun setData(data: Any?) {
    }


    override fun lazyLoadData() {
        cid = arguments!!.getInt("cid")
        rvProChild.layoutManager = LinearLayoutManager(context)
        mAdapter = ProChildAdapter(R.layout.pro_item)
        mAdapter?.setEnableLoadMore(true)
        mAdapter?.setOnLoadMoreListener({
            // load more
            if (mAdapter?.data!!.size >= total) {
                mAdapter?.loadMoreEnd()
            } else {
                mPresenter?.getPro(++page, cid)
            }
        }, rvProChild)
        mAdapter?.disableLoadMoreIfNotFullPage()
        mAdapter?.setOnItemClickListener { adapter, view, position ->
            // item click
            val chapter = mAdapter?.data!![position]
            ActivityUtils.startActivity(
                Intent(activity, WebActivity::class.java)
                    .putExtra("url", chapter.link)
                    .putExtra("id", chapter.id)
                    .putExtra("collect", chapter.collect)
            )

        }
        rvProChild.adapter = mAdapter
        mPresenter?.getPro(page, cid)
    }


    override fun getPro(entity: ChapterPageEntity) {
        total = entity.total
        if (entity.curPage == 1) {
            mAdapter?.setNewData(entity.datas)
        } else {
            mAdapter?.addData(entity.datas)
        }
        mAdapter?.loadMoreComplete()
    }

    override fun getLayoutId(): Int {
       return R.layout.fragment_pro_child
    }
}
