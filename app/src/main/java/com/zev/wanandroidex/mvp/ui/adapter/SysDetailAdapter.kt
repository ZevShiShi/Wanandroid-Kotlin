package com.zev.wanandroidex.mvp.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.zev.wanandroidex.R
import com.zev.wanandroidex.mvp.model.entity.ChapterEntity

class SysDetailAdapter(layoutResId: Int) :
    BaseQuickAdapter<ChapterEntity, BaseViewHolder>(layoutResId) {

    override fun convert(helper: BaseViewHolder?, item: ChapterEntity?) {
        helper?.setText(R.id.tvTitle, item?.title)
        helper?.setText(
            R.id.tvTimeAuthorType,
            "${item?.niceDate}\t\t${(item?.getUser())}\t\t${item?.superChapterName}"
        )
    }
}