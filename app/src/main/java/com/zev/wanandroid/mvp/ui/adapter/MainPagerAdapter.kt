package com.zev.wanandroid.mvp.ui.adapter

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter

class MainPagerAdapter(
    private val fm: FragmentManager
) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val list  =  ArrayList<Fragment>()

    override fun getItem(position: Int): Fragment {
        return list[position]

    }

    override fun getCount(): Int {
        return list.size
    }


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        fm.beginTransaction().hide(`object` as Fragment).commitAllowingStateLoss()
    }


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val f = super.instantiateItem(container, position) as Fragment
        fm.beginTransaction().show(f).commitAllowingStateLoss()
        return f
    }


    fun addFragment(fragment: Fragment) {
        if (fragment.isAdded)
            return
        list.add(fragment)
    }


    fun updateFragment(list: ArrayList<Fragment>) {
        if (list.isEmpty()) return
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }


    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }

}