package com.zev.wanandroid.mvp.model.entity

data class BannerEntity(
    val desc: String,
    val id: Int,
    val isVisible: Int,
    val order: Int,
    val type: Int,
    val imagePath: String,
    val title: String,
    val url: String
)