package org.sopt.seminar.presentation.follower

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.sopt.seminar.*
import org.sopt.seminar.databinding.FragmentFollowerBinding
import org.sopt.seminar.domain.repositories.GithubRepository
import org.sopt.seminar.presentation.detail.DetailActivity
import org.sopt.seminar.util.BaseFragment
import org.sopt.seminar.util.MyTouchHelperCallback
import javax.inject.Inject

@AndroidEntryPoint
class FollowerFragment : BaseFragment<FragmentFollowerBinding>(R.layout.fragment_follower) {

    private val viewModel by viewModels<FollowerViewModel>()
    private var followerAdapter: FollowerAdapter? = FollowerAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        initAdapter()
        viewModel.followerList()
    }

    private fun initAdapter() {
        binding.rvFollower.adapter = followerAdapter
        recyclerViewDecoration()
        observeFollower()
        itemClickEvent()

        val callback = followerAdapter?.let { MyTouchHelperCallback(it) }
        val touchHelper = callback?.let { ItemTouchHelper(it) }
        touchHelper?.attachToRecyclerView(binding.rvFollower)
        binding.rvFollower.adapter = followerAdapter
    }

    private fun observeFollower() {
        viewModel.getFollower().observe(viewLifecycleOwner) {
            followerAdapter?.submitList(it?.toMutableList())
        }
    }

    private fun itemClickEvent() {
        followerAdapter?.setItemClickListener(object : FollowerAdapter.OnItemClickListener {
            override fun onClick(data: View, position: Int) {
                val name = followerAdapter!!.currentList[position].name
                val introduce = followerAdapter!!.currentList[position].bio
                val profile = followerAdapter!!.currentList[position].avatar_url
                val id = followerAdapter!!.currentList[position].login

                val intent = Intent(context, DetailActivity::class.java).apply {
                    putExtra("profile", profile)
                    putExtra("name", name)
                    putExtra("id", id)
                    putExtra("introduce", introduce)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
                startActivity(intent)
            }
        })
    }

    private fun recyclerViewDecoration() {
        with(binding) {
            rvFollower.addItemDecoration(VerticalItemDecorator(10))
            rvFollower.addItemDecoration(HorizontalItemDecorator(10))
        }
    }

    override fun onDestroyView() {
        followerAdapter = null
        super.onDestroyView()
    }
}