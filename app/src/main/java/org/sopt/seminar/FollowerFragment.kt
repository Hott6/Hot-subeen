package org.sopt.seminar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.sopt.seminar.databinding.FragmentFollowerBinding

class FollowerFragment : Fragment() {
    private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding ?: error("Binding이 초기화 되지 않았습니다.")
    private lateinit var followerAdapter: FollowerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowerBinding.inflate(layoutInflater, container, false)

        initAdapter()

        return binding.root


    }

    private fun initAdapter(){
        followerAdapter = FollowerAdapter()
        binding.rvFollower.adapter=followerAdapter
        followerAdapter.followerList.addAll(
            listOf(
                FollowerData("김수빈","안드로이드 OB"),
                FollowerData("권용민","안드로이드 OB"),
                FollowerData("이준원","안드로이드 YB"),
                FollowerData("최윤정","안드로이드 YB"),
                FollowerData("최유리","안드로이드 YB"),
                FollowerData("이강민","안드로이드 파트장"),
                FollowerData("김태현","iOS 파트장"),
                FollowerData("김두범","기획 파트장")


            )
        )
        followerAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}