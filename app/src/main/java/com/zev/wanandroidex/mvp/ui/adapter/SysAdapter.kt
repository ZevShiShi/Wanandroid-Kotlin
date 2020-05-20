package com.zev.wanandroidex.mvp.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.zev.wanandroidex.R
import com.zev.wanandroidex.mvp.model.entity.SysEntity

class SysAdapter(layoutResId: Int) : BaseQuickAdapter<SysEntity, BaseViewHolder>(layoutResId) {

    override fun convert(helper: BaseViewHolder?, item: SysEntity?) {
        if (item?.select!!) {
            helper?.setGone(R.id.llSelect, true)
            helper?.setGone(R.id.llUnSelect, false)
            helper?.setText(R.id.tvSelect, item.name)
        } else {
            helper?.setGone(R.id.llSelect, false)
            helper?.setGone(R.id.llUnSelect, true)
            helper?.setText(R.id.tvUnSelect, item.name)
        }
    }
}