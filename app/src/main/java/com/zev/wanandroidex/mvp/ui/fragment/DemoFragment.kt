package com.zev.wanandroidex.mvp.ui.fragment

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.LogUtils
import com.zev.wanandroidex.R
import kotlinx.android.synthetic.main.fragment_demo.*

/**
 * A simple [Fragment] subclass.
 */
class DemoFragment : Fragment() {

    private var pos = 0
    private val fragments = ArrayList<Fragment>()
    private var toogle = false
    companion object {
        fun getInstance(): DemoFragment {
            return DemoFragment()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_demo, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragments.add(ImageFragment.getInstance(R.drawable.bbb).setCallback(imageCallback))
        fragments.add(ImageFragment.getInstance(R.drawable.rrr).setCallback(imageCallback))
        fragments.add(ImageFragment.getInstance(R.drawable.qqq).setCallback(imageCallback))
        fragments.add(ImageFragment.getInstance(R.drawable.bbb).setCallback(imageCallback))
        fragments.add(ImageFragment.getInstance(R.drawable.rrr).setCallback(imageCallback))
        fragments.add(ImageFragment.getInstance(R.drawable.qqq).setCallback(imageCallback))
        fragments.add(ImageFragment.getInstance(R.drawable.bbb).setCallback(imageCallback))
        fragments.add(ImageFragment.getInstance(R.drawable.rrr).setCallback(imageCallback))
        fragments.add(ImageFragment.getInstance(R.drawable.qqq).setCallback(imageCallback))

        val adapter = MyPageAdapter(childFragmentManager, fragments)
        vpDemo.adapter = adapter
        setupVpParam(ConvertUtils.dp2px(30f))
        setupVpPageMargin(ConvertUtils.dp2px(10f))

        vpDemo.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                pos = position
            }

        })

    }

    private val imageCallback = object : ImageFragment.ImageCallback {
        override fun imageClick() {
            var marginStar: Float
            var marginEnd: Float
            var pageMarginStar : Float
            var pageMarginEnd : Float

            if (!toogle) {
                toogle = true
//                vpDemo.clipChildren = true
//                (view as LinearLayout).clipChildren = true
                marginStar = 30f
                marginEnd = 0f
                pageMarginStar = 10f
                pageMarginEnd = 0f;
            } else {
                toogle = false
//                vpDemo.clipChildren = false
//                (view as LinearLayout).clipChildren = false
                marginStar = 0f
                marginEnd = 30f
                pageMarginStar = 0f
                pageMarginEnd = 10f;
            }

            val anim = ValueAnimator.ofFloat(marginStar, marginEnd)
            anim.duration = 1000
            anim.addUpdateListener { animation ->
                val value = animation?.animatedValue as Float
                setupVpParam(ConvertUtils.dp2px(value))
            }
            anim.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    vpDemo.setCurrentItem(pos,true)
                }
            })
            anim.start()

            val anim2 = ValueAnimator.ofFloat(pageMarginStar, pageMarginEnd)
            anim2.duration = 1000
            anim2.addUpdateListener { animation ->
                val value = animation?.animatedValue as Float
                setupVpPageMargin(ConvertUtils.dp2px(value))
            }
            anim2.start()
            LogUtils.d("ImageCallback===position=${pos}")
        }

    }


    private fun setupVpParam(margin: Int) {
        (vpDemo.layoutParams as LinearLayout.LayoutParams).leftMargin = margin
        (vpDemo.layoutParams as LinearLayout.LayoutParams).rightMargin = margin
    }

    private fun setupVpPageMargin(pageMargin: Int) {
        vpDemo.pageMargin = pageMargin
    }


    class MyPageAdapter(
        private var fm: FragmentManager,
        private var fragments: ArrayList<Fragment>
    ) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        override fun getCount(): Int {
            return fragments.size
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            fm.beginTransaction().hide(`object` as Fragment).commitAllowingStateLoss()
        }


        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val f = super.instantiateItem(container, position) as Fragment
            fm.beginTransaction().show(f).commitAllowingStateLoss()
            return f
        }
    }

}
