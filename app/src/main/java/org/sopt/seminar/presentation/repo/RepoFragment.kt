package org.sopt.seminar.presentation.repo


import android.os.Bundle
import android.view.View
import org.sopt.seminar.*
import org.sopt.seminar.databinding.FragmentRepoBinding
import org.sopt.seminar.util.BaseFragment

class RepoFragment : BaseFragment<FragmentRepoBinding>(R.layout.fragment_repo) {

    private var repoAdapter = RepoAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        initAdapter()
    }

    private fun initAdapter() {
        binding.rvRepo.adapter = repoAdapter
        recyclerViewDecoration()
        addRepoList()
    }

    private fun addRepoList() {
        val call = ServiceCreator.githubApiService.getRepoInfo()
        call.enqueueUtil(
            onSuccess = {
                repoAdapter.submitList(it)
            }
        )
    }


    private fun recyclerViewDecoration() {
        with(binding) {
            rvRepo.addItemDecoration(VerticalItemDecorator(20))
            rvRepo.addItemDecoration(HorizontalItemDecorator(10))
        }
    }

}