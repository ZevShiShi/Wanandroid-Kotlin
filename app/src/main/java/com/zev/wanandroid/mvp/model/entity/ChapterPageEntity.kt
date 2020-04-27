package com.zev.wanandroid.mvp.model.entity

data class ChapterPageEntity(
    val curPage: Int,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int,
    val datas: ArrayList<ChapterEntity>
)
