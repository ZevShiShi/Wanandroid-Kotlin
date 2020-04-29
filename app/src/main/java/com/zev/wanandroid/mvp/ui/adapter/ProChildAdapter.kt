package com.zev.wanandroid.mvp.ui.adapter

import android.widget.ImageView
import com.blankj.utilcode.util.LogUtils
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.utils.ArmsUtils
import com.zev.wanandroid.R
import com.zev.wanandroid.mvp.model.entity.ChapterEntity
import kotlinx.android.synthetic.main.pro_item.view.*

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