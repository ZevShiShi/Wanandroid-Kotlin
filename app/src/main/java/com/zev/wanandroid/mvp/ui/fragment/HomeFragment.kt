package com.zev.wanandroid.mvp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.jess.arms.di.component.AppComponent
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import com.zev.wanandroid.R
import com.zev.wanandroid.app.utils.GlideImageLoader
import com.zev.wanandroid.di.component.DaggerHomeComponent
import com.zev.wanandroid.mvp.base.BaseMvpFragment
import com.zev.wanandroid.mvp.contract.HomeContract
import com.zev.wanandroid.mvp.model.entity.BannerEntity
import com.zev.wanandroid.mvp.model.entity.ChapterEntity
import com.zev.wanandroid.mvp.model.entity.ChapterPageEntity
import com.zev.wanandroid.mvp.presenter.HomePresenter
import com.zev.wanandroid.mvp.ui.activity.WebActivity
import com.zev.wanandroid.mvp.ui.adapter.ChapterAdapter
import com.zev.wanandroid.mvp.ui.adapter.ChapterAdapter.OnLikeListener
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : BaseMvpFragment<HomePresenter>(), HomeContract.View {

    lateinit var banner: Banner
    lateinit var mAdapter: ChapterAdapter
    private var page: Int = 0
    private var total: Int = 0


    override fun getBanner(entities: List<BannerEntity>) {
        setBanner(entities)
    }

    override fun getChapterTop(entities: List<ChapterEntity>) {
        for (e in entities) {
            e.showTop = true
        }
        mAdapter.addData(0, entities)
    }

    override fun getHomeChapter(entity: ChapterPageEntity) {
        total = entity.total
        mAdapter.addData(entity.datas)
        mAdapter.loadMoreComplete()
        refreshLayout.finishRefresh()
    }

    override fun collectSuccess() {
        ToastUtils.showShort("collectSuccess")
    }

    override fun unCollectSuccess() {
        ToastUtils.showShort("unCollectSuccess")
    }


    private fun setBanner(entities: List<BannerEntity>) {
        if (entities.isEmpty()) return
        banner = Banner(activity)
        banner.layoutParams = ViewGroup.LayoutParams(-1, ConvertUtils.dp2px(200f))
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
        val images = ArrayList<String>()
        for (e in entities) {
            images.add(e.imagePath)
        }
        banner.setImages(images)
        banner.setImageLoader(GlideImageLoader())
        banner.setBannerAnimation(Transformer.DepthPage)
        banner.isAutoPlay(true)
        banner.setDelayTime(2500)
        banner.setIndicatorGravity(BannerConfig.CENTER)
        banner.start()
        banner.setOnBannerListener { position ->
            val entity = entities[position]
            ActivityUtils.startActivity(
                Intent(activity, WebActivity::class.java)
                    .putExtra("url", entity.url)
                    .putExtra("id", entity.id)
            )
        }
        mAdapter.setHeaderView(banner)
        mAdapter.header = true
    }



    override fun lazyLoadData() {
        LogUtils.d("lazyLoadData")
        initStatusLayoutManager(rvHome)
        mAdapter = ChapterAdapter(R.layout.home_item)
        rvHome.layoutManager = LinearLayoutManager(activity)
        mAdapter.setEnableLoadMore(true)
        mAdapter.setOnLoadMoreListener({
            rvHome.postDelayed({
                if (mAdapter.data.size >= total) {
                    mAdapter.loadMoreEnd()
                } else {
                    mPresenter?.getHomeChapter(++page,true)
                }
            }, 1000)

        }, rvHome)
        mAdapter.disableLoadMoreIfNotFullPage()
        mAdapter.setOnItemClickListener { adapter, view, position ->
            val chapter = mAdapter.data[position]
            ActivityUtils.startActivity(
                Intent(activity, WebActivity::class.java)
                    .putExtra("url", chapter.link)
                    .putExtra("id", chapter.id)
                    .putExtra("collect", chapter.collect)
            )
        }

        mAdapter.listener = object : OnLikeListener {
            override fun onLike(like: Boolean, pos: Int) {
                val chapter = mAdapter.data[pos]
                if (like) {
                    mPresenter?.collectChapter(chapter.id)
                } else {
                    mPresenter?.unCollectChapter(chapter.id)
                }
                chapter.collect = like
                mAdapter.refreshNotifyItemChanged(pos)
            }
        }
        rvHome.adapter = mAdapter


        refreshLayout.setOnRefreshListener {
            refreshData()
        }
        mPresenter?.getBanner()
        mPresenter?.getChapterTop()
        mPresenter?.getHomeChapter(page,false)
    }

    private fun refreshData() {
        mAdapter.data.clear()
        mPresenter?.getBanner()
        mPresenter?.getChapterTop()
        mPresenter?.getHomeChapter(page = 0, loadMore = false)
    }

    companion object {
        fun getInstance(): HomeFragment {
            return HomeFragment()
        }
    }


    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerHomeComponent.builder()
            .appComponent(appComponent)
            .view(this)
            .build()
            .inject(this)
    }

    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun setData(data: Any?) {
    }


    override fun reloadingData() {
        super.reloadingData()
        refreshData()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

}
