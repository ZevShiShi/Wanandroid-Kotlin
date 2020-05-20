package com.zev.wanandroidex.mvp.base

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.blankj.utilcode.util.LogUtils
import com.jess.arms.base.BaseFragment
import com.jess.arms.mvp.IPresenter
import com.zev.wanandroidex.R
import me.bakumon.statuslayoutmanager.library.OnStatusChildClickListener
import me.bakumon.statuslayoutmanager.library.StatusLayoutManager


abstract class BaseMvpFragment<P : IPresenter> : BaseFragment<P>(), BaseIView {

    var statusLayoutManager: StatusLayoutManager? = null
    var isViewCreated = false;
    var isVisibleToUser = false;
    var isDataLoaded = false;


    protected open fun lazyLoadData() {
    }

    protected open fun reloadingData() {

    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        this.isVisibleToUser = isVisibleToUser
        tryLoadData()
    }

    override fun onResume() {
        super.onResume()
        isViewCreated = true
        tryLoadData()
    }


    private fun dispatchParentVisibleState() {
        val fragments = childFragmentManager.fragments
        if (fragments.isEmpty()) {
            return
        }
        for (f in fragments) {
            if (isVisibleToUser) {
                tryLoadData()
            }
        }
    }


    private fun tryLoadData() {
        LogUtils.d(
            "isViewCreated=$isViewCreated,isVisibleToUser=$isVisibleToUser,isDataLoaded=$isDataLoaded"
        )
        if (isViewCreated && !isVisibleToUser && !isDataLoaded) {
            lazyLoadData()
            isDataLoaded = true
            dispatchParentVisibleState()
        }
    }

    /**
     * 外部调用添加状态布局
     */
    protected fun initStatusLayoutManager(rootView: View) {
        statusLayoutManager = StatusLayoutManager.Builder(rootView)
            .setDefaultLoadingText("拼命加载中...")
            .setDefaultEmptyImg(R.drawable.empty_icon)
            .setDefaultErrorText(R.string.status_layout_manager_error)
            .setDefaultErrorImg(R.drawable.empty_icon)
            .setDefaultLayoutsBackgroundColor(Color.WHITE)
            .setOnStatusChildClickListener(object : OnStatusChildClickListener {
                override fun onErrorChildClick(view: View?) {
                    reloadingData()
                }

                override fun onEmptyChildClick(view: View?) {

                }

                override fun onCustomerChildClick(view: View?) {

                }

            })
            .build()
    }

    override fun initView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(getLayoutId(), container, false)
    }

    abstract fun getLayoutId(): Int

    override fun showMessage(message: String) {
    }

    override fun showEmptyLayout() {
        statusLayoutManager?.showEmptyLayout()
    }

    override fun showSuccessLayout() {
        LogUtils.d("onNextView================");
        statusLayoutManager?.showSuccessLayout()
    }

    override fun showErrorLayout() {
        statusLayoutManager?.showErrorLayout()
    }

    override fun showLoading() {
        statusLayoutManager?.showLoadingLayout()
    }

    override fun hideLoading() {

    }

    override fun setData(data: Any?) {
    }
}