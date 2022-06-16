package org.sopt.seminar.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import org.sopt.seminar.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var homeViewPagerAdapter: HomeViewPagerAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initTabLayout()
    }

    private fun initAdapter() {
        val fragmentList = listOf(HomeFollowerFragment(), HomeFollowingFragment())

        homeViewPagerAdapter = HomeViewPagerAdapter(this)
        homeViewPagerAdapter?.fragments?.addAll(fragmentList)
        binding.vpHome.adapter = homeViewPagerAdapter

    }

    private fun initTabLayout() {
        val tabLable = listOf("팔로잉", "팔로워")

        TabLayoutMediator(binding.homeTablayout, binding.vpHome) { tab, position ->
            tab.text = tabLable[position]
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        homeViewPagerAdapter = null
    }
}