package com.zev.wanandroidex.mvp.ui.adapter

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.zev.wanandroidex.R
import com.zev.wanandroidex.mvp.model.entity.ChapterEntity

class ProChildAdapter(layoutResId: Int) :
    BaseQuickAdapter<ChapterEntity, BaseViewHolder>(layoutResId) {

    override fun convert(helper: BaseViewHolder?, item: ChapterEntity?) {
        Glide.with(mContext).load(item?.envelopePic)
            .error(R.drawable.logo)
            .placeholder(R.drawable.logo)
            .thumbnail(0.8f)
            .into((helper?.getView(R.id.ivPro) as ImageView))
        helper?.setText(R.id.tvProTitle, item?.title)
        helper?.setText(R.id.tvProAuthor, "${(item?.author ?: item?.shareUser)}\t\t\t${item?.niceDate}")
//        LogUtils.d("item?.niceDate${item?.niceDate}")
    }
}