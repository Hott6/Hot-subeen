package org.sopt.seminar.presentation.repo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import org.sopt.seminar.HorizontalItemDecorator
import org.sopt.seminar.util.MyTouchHelperCallback
import org.sopt.seminar.VerticalItemDecorator
import org.sopt.seminar.databinding.FragmentRepoBinding
import org.sopt.seminar.presentation.follower.FollowerAdapter

class RepoFragment : Fragment() {
    private var _binding: FragmentRepoBinding? = null
    private val binding get() = _binding ?: error("Binding이 초기화 되지 않았습니다.")
    private lateinit var repoAdapter: RepoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRepoBinding.inflate(layoutInflater, container, false)

        initAdapter()
        return binding.root
    }

    private fun initAdapter() {
        repoAdapter = RepoAdapter()
        binding.rvRepo.adapter = repoAdapter
        recyclerViewDecoration()

        val callback = MyTouchHelperCallback(repoAdapter)
        val touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(binding.rvRepo)
        binding.rvRepo.adapter = repoAdapter
        repoAdapter.startDrag(object : FollowerAdapter.OnStartDragListener {
            override fun onStartDrag(viewHolder: FollowerAdapter.FollowerViewHolder) {
                touchHelper.startDrag(viewHolder)
            }
        })
        repoAdapter.repoList.addAll(
            listOf(
                RepoData("Hott6", "THE SOPT 30기 안드로이드 파트 금잔디파 6조"),
                RepoData("Android-Subin", "WE SOPT 29기 안드로이드 레포지토리"),
                RepoData("AlgorithmPython", "알고리즘 문제 풀이 파이썬"),
                RepoData("Kotlin-Study", "이것저것 코틀린 끄적이기"),
                RepoData("Hot-yuree", "금잔디6조 유리 레포지토리"),
                RepoData("Hot-yoon", "나에게 Hot6를 달라"),
                RepoData("Hot-JunWon", "금잔디6조 준원 레포지토리"),
                RepoData("Hot-Yongmin", "세상에서 가장 핫한 오가니제이션에 모인 개발자들")
            )
        )
        repoAdapter.notifyDataSetChanged()
    }

    private fun recyclerViewDecoration() {
        with(binding) {
            rvRepo.addItemDecoration(VerticalItemDecorator(20))
            rvRepo.addItemDecoration(HorizontalItemDecorator(10))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}