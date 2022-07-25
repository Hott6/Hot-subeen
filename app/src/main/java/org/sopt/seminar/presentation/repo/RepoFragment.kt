package org.sopt.seminar.presentation.repo


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.sopt.seminar.*
import org.sopt.seminar.databinding.FragmentRepoBinding
import org.sopt.seminar.domain.repositories.GithubRepository
import org.sopt.seminar.presentation.follower.FollowerViewModel
import org.sopt.seminar.util.BaseFragment
import org.sopt.seminar.util.enqueueUtil
import javax.inject.Inject

@AndroidEntryPoint
class RepoFragment : BaseFragment<FragmentRepoBinding>(R.layout.fragment_repo) {

    private val viewModel by viewModels<RepoViewModel>()
    private var repoAdapter = RepoAdapter()

    @Inject
    lateinit var githubRepository: GithubRepository

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        initAdapter()
        getRepo()
    }

    private fun initAdapter() {
        binding.rvRepo.adapter = repoAdapter
        recyclerViewDecoration()
        observeRepo()
    }

    private fun getRepo() {
        viewModel.repoList()
    }

    private fun observeRepo() {
        viewModel.getRepo().observe(viewLifecycleOwner) {
            repoAdapter.submitList(it?.toMutableList())
        }
    }

    private fun recyclerViewDecoration() {
        with(binding) {
            rvRepo.addItemDecoration(VerticalItemDecorator(20))
            rvRepo.addItemDecoration(HorizontalItemDecorator(10))
        }
    }

}