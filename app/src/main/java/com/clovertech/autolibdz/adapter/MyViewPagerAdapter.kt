package com.clovertech.autolibdz.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MyViewPagerAdapter(manager : FragmentManager) : FragmentPagerAdapter(manager) {

    private val fragmentList : MutableList<Fragment> = ArrayList()

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    fun addFragment(fragment: Fragment){
        fragmentList.add(fragment)
    }
}