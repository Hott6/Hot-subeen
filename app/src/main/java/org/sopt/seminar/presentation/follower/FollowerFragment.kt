package org.sopt.seminar.presentation.follower

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import org.sopt.seminar.*
import org.sopt.seminar.util.MyTouchHelperCallback
import org.sopt.seminar.databinding.FragmentFollowerBinding
import org.sopt.seminar.presentation.detail.DetailActivity
import org.sopt.seminar.util.BaseFragment

class FollowerFragment : BaseFragment<FragmentFollowerBinding>(R.layout.fragment_follower) {

    private var followerAdapter = FollowerAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        initAdapter()
    }

    private fun initAdapter() {
        binding.rvFollower.adapter = followerAdapter
        recyclerViewDecoration()
        addRepoList()
        itemClickEvent()

        val callback = MyTouchHelperCallback(followerAdapter)
        val touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(binding.rvFollower)
        binding.rvFollower.adapter = followerAdapter
        followerAdapter.startDrag(object : FollowerAdapter.OnStartDragListener {
            override fun onStartDrag(viewHolder: FollowerAdapter.FollowerViewHolder) {
                touchHelper.startDrag(viewHolder)
            }
        })
    }

    private fun addRepoList() {
        val call = ServiceCreator.githubApiService.getFollowingInfo()

        call.enqueueUtil(
            onSuccess = {
                followerAdapter.submitList(it)
            }
        )

    }

    private fun itemClickEvent() {
        followerAdapter.setItemClickListener(object : FollowerAdapter.OnItemClickListener {
            override fun onClick(view: View, position: Int) {
                val name = followerAdapter.currentList[position].name
                val introduce = followerAdapter.currentList[position].bio
                val profile = followerAdapter.currentList[position].avatar_url
                val id = followerAdapter.currentList[position].login

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

}