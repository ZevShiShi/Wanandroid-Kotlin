package com.zev.wanandroidex.mvp.ui.activity

import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.webkit.WebView
import com.jess.arms.di.component.AppComponent
import com.zev.wanandroidex.app.utils.WebViewManager.WebCallbackEx
import com.zev.wanandroidex.R
import com.zev.wanandroidex.app.utils.WebViewManager
import com.zev.wanandroidex.di.component.DaggerWebComponent
import com.zev.wanandroidex.mvp.base.BaseMvpActivity
import com.zev.wanandroidex.mvp.contract.WebContract
import com.zev.wanandroidex.mvp.presenter.WebPresenter
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : BaseMvpActivity<WebPresenter>(), WebContract.View {

    private val webManager = WebViewManager()
    private var mUrl: String? = null
    var mCollect = false
    var mId = 0
    private var gestureDetector: GestureDetector? = null


    override fun initData(savedInstanceState: Bundle?) {
        mUrl = intent.getStringExtra("url")
        if (mUrl!!.isEmpty()) return
        mCollect = intent.getBooleanExtra("collect", false)
        mId = intent.getIntExtra("id", 0)
        pbLoad.showProgress(true)
        setupFabColor()
        pbLoad.setOnClickListener { finish() }
//        mUrl = "https://world.huanqiu.com/gallery/9CaKrnQhZ5w"

        webManager.setupWebViewWithActivity(
            mUrl,
            this,
            flParent,
            -1,
            -1,
            30,
            object : WebCallbackEx {
                override fun showWebView() {

                }

                override fun hideWebView() {
                }


                override fun onProgress(view: WebView?, newProgress: Int) {
                    if (!isFinishing) {
                        pbLoad.setProgress(newProgress.toFloat())
                    }
                }

                override fun onReceivedTitle(title: String?, url: String?) {
                }

                override fun onGoWebDetail(url: String?) {
                }
            }
        )
        webManager.agentWeb.agentWebSettings.webSettings.loadWithOverviewMode = true
        webManager.agentWeb.agentWebSettings.webSettings.useWideViewPort = true

        gestureDetector = GestureDetector(this, object : GestureDetector.SimpleOnGestureListener() {
            override fun onDoubleTap(e: MotionEvent?): Boolean {
                likeLayout?.addLoveView(e!!.rawX, e.rawY)
                return true
            }
        })
        webManager.webView.setOnTouchListener { v, event -> gestureDetector!!.onTouchEvent(event) }
    }

    override fun onResume() {
        webManager.resumeWeb()
        super.onResume()
    }

    override fun onPause() {
        webManager.pauseWeb()
        super.onPause()
    }

    override fun onDestroy() {
        webManager.destroyWeb()
        super.onDestroy()
    }


    private fun setupFabColor() {
        var color = if (mCollect) {
            resources.getColor(R.color.color_b21026)
        } else {
            resources.getColor(R.color.white)
        }
        pbLoad.setColor(color)
    }

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerWebComponent.builder()
            .appComponent(appComponent)
            .view(this)
            .build()
            .inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_web
    }

    override fun showMessage(message: String) {
    }
}
