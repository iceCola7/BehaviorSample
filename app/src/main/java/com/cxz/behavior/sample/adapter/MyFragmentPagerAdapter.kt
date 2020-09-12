package com.cxz.behavior.sample.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * @author chenxz
 * @date 2020/9/11
 * @desc
 */
class MyFragmentPagerAdapter : FragmentPagerAdapter {

    private var fragmentList = mutableListOf<Fragment>()

    constructor(fm: FragmentManager, fragmentList: MutableList<Fragment>) : super(fm) {
        this.fragmentList = fragmentList
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }
}