package org.sopt.seminar.presentation.repo


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.sopt.seminar.*
import org.sopt.seminar.databinding.FragmentRepoBinding
import org.sopt.seminar.domain.repositories.GithubRepository
import org.sopt.seminar.util.BaseFragment
import org.sopt.seminar.util.enqueueUtil
import javax.inject.Inject

@AndroidEntryPoint
class RepoFragment : BaseFragment<FragmentRepoBinding>(R.layout.fragment_repo) {

    private var repoAdapter = RepoAdapter()

    @Inject
    lateinit var githubRepository: GithubRepository

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

    /*private fun addRepoList() {
        val call = ServiceCreator.githubApiService.getRepoInfo()
        call.enqueueUtil(
            onSuccess = {
                repoAdapter.submitList(it)
            }
        )
    }*/

    private fun addRepoList(){
        viewLifecycleOwner.lifecycleScope.launch {
            runCatching { githubRepository.repoList() }
                .onSuccess {
                    repoAdapter.submitList(it)
                }.onFailure { Log.e("수빈,", "안됩니다RepoFragment", it) }
        }
    }

    private fun recyclerViewDecoration() {
        with(binding) {
            rvRepo.addItemDecoration(VerticalItemDecorator(20))
            rvRepo.addItemDecoration(HorizontalItemDecorator(10))
        }
    }

}