package com.zev.wanandroidex.mvp.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.zev.wanandroidex.R
import com.zev.wanandroidex.mvp.model.entity.SysChildEntity

class SysChildAdapter(layoutResId: Int) :
    BaseQuickAdapter<SysChildEntity, BaseViewHolder>(layoutResId) {

    override fun convert(helper: BaseViewHolder?, item: SysChildEntity?) {
        helper?.setBackgroundColor(R.id.llRoot,mContext.resources.getColor(android.R.color.transparent))
        helper?.setText(R.id.tvUnSelect, item?.name)
    }
}