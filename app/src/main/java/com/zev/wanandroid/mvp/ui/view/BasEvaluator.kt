package com.zev.wanandroid.mvp.ui.view

import android.animation.TypeEvaluator
import android.graphics.PointF

class BasEvaluator(private val p1: PointF, private val p2: PointF) : TypeEvaluator<PointF> {
    override fun evaluate(fraction: Float, p0: PointF, p3: PointF): PointF {
        // TODO Auto-generated method stub
        val pointf = PointF()

        // 贝塞尔曲线公式  p0*(1-t)^3 + 3p1*t*(1-t)^2 + 3p2*t^2*(1-t) + p3^3
        pointf.x =
            p0.x * (1 - fraction) * (1 - fraction) * (1 - fraction) + 3 * p1.x * fraction * (1 - fraction) * (1 - fraction) + 3 * p2.x * fraction * fraction * (1 - fraction) + p3.x * fraction * fraction * fraction
        pointf.y =
            p0.y * (1 - fraction) * (1 - fraction) * (1 - fraction) + 3 * p1.y * fraction * (1 - fraction) * (1 - fraction) + 3 * p2.y * fraction * fraction * (1 - fraction) + p3.y * fraction * fraction * fraction
        return pointf
    }

}