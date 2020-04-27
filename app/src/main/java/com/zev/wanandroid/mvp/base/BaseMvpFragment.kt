package com.zev.wanandroid.mvp.base

import com.blankj.utilcode.util.LogUtils
import com.jess.arms.base.BaseFragment
import com.jess.arms.mvp.IPresenter

abstract class BaseMvpFragment<P : IPresenter> : BaseFragment<P>() {

    var isViewCreated = false;
    var isVisibleToUser = false;
    var isDataLoaded = false;


    abstract fun lazyLoadData()

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


}