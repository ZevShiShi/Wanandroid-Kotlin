package com.zev.wanandroid.mvp.base

import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.AttributeSet
import android.view.InflateException
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import butterknife.ButterKnife
import butterknife.Unbinder
import com.blankj.utilcode.util.BarUtils
import com.jess.arms.base.delegate.IActivity
import com.jess.arms.integration.cache.Cache
import com.jess.arms.integration.cache.CacheType
import com.jess.arms.integration.lifecycle.ActivityLifecycleable
import com.jess.arms.mvp.IPresenter
import com.jess.arms.utils.ArmsUtils.obtainAppComponentFromContext
import com.jess.arms.utils.ThirdViewUtil
import com.trello.rxlifecycle2.android.ActivityEvent
import com.zev.wanandroid.R
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject
import me.imid.swipebacklayout.lib.app.SwipeBackActivity
import javax.inject.Inject

abstract class BaseMvpActivity<P : IPresenter> : SwipeBackActivity(), IActivity,
    ActivityLifecycleable {

    private val mLifecycleSubject: BehaviorSubject<ActivityEvent> = BehaviorSubject.create()

    private var mCache: Cache<Any, Any>? = null

    private var mUnbinder: Unbinder? = null

    @set:Inject
    @Nullable
    protected var mPresenter: P? = null


    @NonNull
    @Synchronized
    override fun provideCache(): Cache<String, Any> {
        if (mCache == null) {
            mCache = obtainAppComponentFromContext(this).cacheFactory()
                .build(CacheType.ACTIVITY_CACHE)
        }
        return mCache as Cache<String, Any>
    }


    @NonNull
    override fun provideLifecycleSubject(): Subject<ActivityEvent> {
        return mLifecycleSubject
    }


    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        val view = ThirdViewUtil.convertAutoView(name, context, attrs)
        return view ?: super.onCreateView(name, context, attrs)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            val layoutResId = initView(savedInstanceState)
            if (layoutResId != 0) {
                setContentView(layoutResId)
                mUnbinder = ButterKnife.bind(this)
            }
        } catch (e: Exception) {
            if (e is InflateException) throw e
            e.printStackTrace()
        }
        setLightStateMode()
        initData(savedInstanceState)
    }


    override fun onDestroy() {
        super.onDestroy()
        if (mUnbinder != null && mUnbinder != Unbinder.EMPTY) {
            mUnbinder?.unbind()
        }
        this.mUnbinder = null
        mPresenter?.onDestroy()
        mPresenter = null
    }

    override fun useEventBus(): Boolean {
        return true
    }

    override fun useFragment(): Boolean {
        return true
    }


    protected fun setLightStateMode() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            setStatusBar(this, resources.getColor(R.color.white, null))
            BarUtils.setStatusBarLightMode(this, true)
        } else {
            setStatusBar(this, resources.getColor(R.color.white))
            setOPPOStatusTextColor(true, this)
        }
    }


    fun setStatusBar(activity: Activity, color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.window.statusBarColor = color
        } else {
            val decorView = activity.window.decorView as ViewGroup
            val statusBarView = View(activity)
            val lp = ViewGroup.LayoutParams(-1, 0)
            statusBarView.setBackgroundColor(color)
            decorView.addView(statusBarView, lp)
        }
    }


    private val SYSTEM_UI_FLAG_OP_STATUS_BAR_TINT = 0x00000010

    protected open fun setOPPOStatusTextColor(
        lightStatusBar: Boolean,
        activity: Activity
    ) {
        val window = activity.window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        }
        var vis = window.decorView.systemUiVisibility
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            vis = if (lightStatusBar) {
                vis or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                vis and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            vis = if (lightStatusBar) {
                vis or SYSTEM_UI_FLAG_OP_STATUS_BAR_TINT
            } else {
                vis and SYSTEM_UI_FLAG_OP_STATUS_BAR_TINT.inv()
            }
        }
        window.decorView.systemUiVisibility = vis
    }

}