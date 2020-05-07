package com.zev.wanandroid.mvp.model.api.service

import com.zev.wanandroid.mvp.model.base.BaseArrayEntity
import com.zev.wanandroid.mvp.model.base.BaseEntity
import com.zev.wanandroid.mvp.model.entity.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MainService {

    @GET("banner/json")
    fun getBanner(): Observable<BaseArrayEntity<BannerEntity>>

    @GET("article/top/json")
    fun getChapterTop(): Observable<BaseArrayEntity<ChapterEntity>>

    @GET("article/list/{page}/json")
    fun getHomeChapter(@Path("page") page: Int): Observable<BaseEntity<ChapterPageEntity>>

    /**
     * 站内文章收藏
     */
    @POST("lg/collect/{id}/json")
    fun collectChapter(@Path("id") id: Int): Observable<BaseEntity<Any>>

    /**
     * 文章取消收藏
     */
    @POST("lg/uncollect_originId/{id}/json")
    fun unCollectChapter(@Path("id") id: Int): Observable<BaseEntity<Any>>


    /**
     * 我的收藏-取消收藏
     */
    @POST("lg/uncollect/{id}/json")
    fun unCollectChapterByMy(
        @Path("id") id: Int,
        @Query("originId") originId: Int
    ): Observable<BaseEntity<Any>>

    /**
     * 站外文章-收藏
     */
    @POST("lg/collect/add/json")
    fun collectChapterByMy(
        @Query("title") title: String,
        @Query("author") author: String,
        @Query("link") link: String
    )
            : Observable<BaseEntity<Any>>


    /**
     * 获取项目tab
     */
    @GET("project/tree/json")
    fun getProTab(): Observable<BaseArrayEntity<ProTabEntity>>

    /**
     * 获取项目
     */
    @GET("project/list/{page}/json")
    fun getPro(@Path("page") page: Int, @Query("cid") id: Int)
            : Observable<BaseEntity<ChapterPageEntity>>

    /**
     * 体系数据tab
     */
    @GET("tree/json")
    fun getSys():Observable<BaseArrayEntity<SysEntity>>

    /**
     * 体系数据详情
     */
    @GET("article/list/{page}/json")
    fun getSysDetail(@Path("page") page: Int, @Query("cid") id: Int)
            : Observable<BaseEntity<ChapterPageEntity>>

}