package com.zev.wanandroidex.mvp.ui.view

import android.animation.*
import android.content.Context
import android.graphics.PointF
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.animation.*
import android.widget.ImageView
import android.widget.RelativeLayout
import com.zev.wanandroidex.R
import java.util.*

class LikeLayout(context: Context, attrs: AttributeSet?) :
    RelativeLayout(context, attrs) {
    private var params: LayoutParams? = null
    private val icons =
        arrayOfNulls<Drawable>(4)
    private val interpolators =
        arrayOfNulls<Interpolator>(4)
    private var mWidth = 0
    private var mHeight = 0
    private fun initView() {

        // 图片资源
        icons[0] = resources.getDrawable(R.drawable.heart_red)
        icons[1] = resources.getDrawable(R.drawable.heart_red)
        icons[2] = resources.getDrawable(R.drawable.heart_red)
        icons[3] = resources.getDrawable(R.drawable.heart_red)

        // 插值器
        interpolators[0] = AccelerateDecelerateInterpolator() // 在动画开始与结束的地方速率改变比较慢，在中间的时候加速
        interpolators[1] = AccelerateInterpolator() // 在动画开始的地方速率改变比较慢，然后开始加速
        interpolators[2] = DecelerateInterpolator() // 在动画开始的地方快然后慢
        interpolators[3] = LinearInterpolator() // 以常量速率改变
    }

    fun addLoveView(x: Float, y: Float) {
        var x = x
        var y = y
        if (x < 100) {
            x = 101f
        }
        if (y < 100) {
            y = 101f
        }
        mWidth = (x - 100).toInt()
        mHeight = (y - 100).toInt()
        val iv = ImageView(context)
        params = LayoutParams(200, 200)
        iv.layoutParams = params
        iv.setImageDrawable(icons[Random().nextInt(4)])
        addView(iv)

        // 开启动画，并且用完销毁
        val set = getAnimatorSet(iv)
        set.start()
        set.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                // TODO Auto-generated method stub
                super.onAnimationEnd(animation)
                removeView(iv)
            }
        })
    }

    /**
     * 获取动画集合
     *
     * @param iv
     */
    private fun getAnimatorSet(iv: ImageView): AnimatorSet {

        // 1.alpha动画
        val alpha = ObjectAnimator.ofFloat(iv, "alpha", 0.3f, 1f)

        // 2.缩放动画
        val scaleX = ObjectAnimator.ofFloat(iv, "scaleX", 0.2f, 1f)
        val scaleY = ObjectAnimator.ofFloat(iv, "scaleY", 0.2f, 1f)

        // 动画集合
        val set = AnimatorSet()
        set.playTogether(alpha, scaleX, scaleY)
        set.duration = 2000

        // 贝塞尔曲线动画
        val bzier = getBzierAnimator(iv)
        val set2 = AnimatorSet()
        set2.playTogether(set, bzier)
        set2.setTarget(iv)
        return set2
    }

    /**
     * 贝塞尔动画
     */
    private fun getBzierAnimator(iv: ImageView): ValueAnimator {
        // TODO Auto-generated method stub
        val PointFs = getPointFs(iv) // 4个点的坐标
        val evaluator = BasEvaluator(PointFs[1]!!, PointFs[2]!!)
        val valueAnim =
            ValueAnimator.ofObject(evaluator, PointFs[0], PointFs[3])
        valueAnim.addUpdateListener { animation: ValueAnimator ->
            // TODO Auto-generated method stub
            val p = animation.animatedValue as PointF
            iv.x = p.x
            iv.y = p.y
            iv.alpha = 1 - animation.animatedFraction // 透明度
        }
        valueAnim.setTarget(iv)
        valueAnim.duration = 2000
        valueAnim.interpolator = interpolators[Random().nextInt(4)]
        return valueAnim
    }

    private fun getPointFs(iv: ImageView): Array<PointF?> {
        // TODO Auto-generated method stub
        val PointFs = arrayOfNulls<PointF>(4)
        PointFs[0] = PointF() // p0
        PointFs[0]!!.x = mWidth.toFloat()
        PointFs[0]!!.y = mHeight.toFloat()
        PointFs[1] = PointF() // p1
        PointFs[1]!!.x = Random().nextInt(mWidth).toFloat()
        PointFs[1]!!.y =
            Random().nextInt(mHeight / 2) + mHeight / 2 + params!!.height.toFloat()
        PointFs[2] = PointF() // p2
        PointFs[2]!!.x = Random().nextInt(mWidth).toFloat()
        PointFs[2]!!.y = Random().nextInt(mHeight / 2).toFloat()
        PointFs[3] = PointF() // p3
        PointFs[3]!!.x = Random().nextInt(mWidth).toFloat()
        PointFs[3]!!.y = 0f
        return PointFs
    }

    init {
        initView()
    }
}