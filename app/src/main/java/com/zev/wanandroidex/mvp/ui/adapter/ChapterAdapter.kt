package com.zev.wanandroidex.mvp.ui.adapter

import android.widget.LinearLayout
import android.widget.TextView
import com.blankj.utilcode.util.ConvertUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ldoublem.thumbUplib.ThumbUpView
import com.zev.wanandroidex.R
import com.zev.wanandroidex.mvp.model.entity.ChapterEntity

class ChapterAdapter(layoutResId: Int) :
    BaseQuickAdapter<ChapterEntity, BaseViewHolder>(layoutResId) {

    var header: Boolean = false
    var listener: OnLikeListener? = null


    override fun convert(helper: BaseViewHolder?, item: ChapterEntity?) {
        var tags = ""
        helper?.setGone(R.id.tvTags, item!!.tags.isNotEmpty())
        if (item!!.tags.isNotEmpty()) {
            for (t in item.tags.withIndex()) {
                if (t.index != item.tags.size)
                    tags += t.value.name + "\t"
            }
            helper?.setText(R.id.tvTags, tags)
        }
        helper?.setGone(R.id.tvNew, item.fresh)
        helper?.setGone(R.id.tvTop, item.showTop)
        helper?.setText(R.id.tvTitle, item.title)

        if (item.fresh || item.showTop) {
            (helper?.getView<TextView>(R.id.tvAuthor)?.layoutParams as LinearLayout.LayoutParams)
                .marginStart = ConvertUtils.dp2px(20f)
        } else {
            (helper?.getView<TextView>(R.id.tvAuthor)?.layoutParams as LinearLayout.LayoutParams)
                .marginStart = 0
        }


        if (item.author.isNotEmpty()) {
            helper.setText(R.id.tvAuthor, item.author)
        } else {
            helper.setText(R.id.tvAuthor, item.shareUser)
        }
        helper.setText(R.id.tvDate, item.niceDate)
        helper.setText(R.id.tvChapterName, item.chapterName)

        val zanView = helper.getView<ThumbUpView>(R.id.tpvZan)
        if (item.collect) {
            zanView?.setLike()
        } else {
            zanView?.setUnlike()
        }

        zanView?.setOnThumbUp { like ->
            val pos = if (header) {
                helper.layoutPosition - 1
            } else {
                helper.layoutPosition
            }
            listener?.onLike(like, pos)
        }
    }


    interface OnLikeListener {
        fun onLike(like: Boolean, pos: Int)
    }
}