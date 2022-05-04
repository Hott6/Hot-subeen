package org.sopt.seminar

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import org.sopt.seminar.presentation.home.HomeFragment

class HomeViewPagerAdapter(fragmentActivity: HomeFragment) :
    FragmentStateAdapter(fragmentActivity) {
    val fragments = mutableListOf<Fragment>()
    override fun getItemCount() = fragments.size
    override fun createFragment(position: Int): Fragment = fragments[position]
}