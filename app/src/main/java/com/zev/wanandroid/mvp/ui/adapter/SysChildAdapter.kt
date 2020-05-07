package com.zev.wanandroid.mvp.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.zev.wanandroid.R
import com.zev.wanandroid.mvp.model.entity.SysChildEntity
import com.zev.wanandroid.mvp.model.entity.SysEntity

class SysChildAdapter(layoutResId: Int) :
    BaseQuickAdapter<SysChildEntity, BaseViewHolder>(layoutResId) {

    override fun convert(helper: BaseViewHolder?, item: SysChildEntity?) {
        helper?.setBackgroundColor(R.id.llRoot,mContext.resources.getColor(android.R.color.transparent))
        helper?.setText(R.id.tvUnSelect, item?.name)
    }
}