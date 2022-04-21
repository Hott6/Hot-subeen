package org.sopt.seminar.presentation.follower

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import org.sopt.seminar.HorizontalItemDecorator
import org.sopt.seminar.R
import org.sopt.seminar.util.MyTouchHelperCallback
import org.sopt.seminar.VerticalItemDecorator
import org.sopt.seminar.databinding.FragmentFollowerBinding
import org.sopt.seminar.presentation.detail.DetailActivity
import org.sopt.seminar.util.BaseFragment

class FollowerFragment : BaseFragment<FragmentFollowerBinding>(R.layout.fragment_follower) {

    private var followerAdapter = FollowerAdapter()

    override fun initAdapter() {
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
        followerAdapter.submitList(
            listOf(
                FollowerData(
                    "김수빈", "안드로이드 OB"
                ),
                FollowerData(
                    "권용민", "안드로이드 OB"
                ),
                FollowerData(
                    "이준원", "안드로이드 YB"
                ),
                FollowerData(
                    "최윤정", "안드로이드 YB"
                ),
                FollowerData(
                    "최유리", "안드로이드 YB"
                ),
                FollowerData(
                    "이강민", "안드로이드 파트장"
                ),
                FollowerData(
                    "김태현", "iOS 파트장"
                ),
                FollowerData(
                    "김두범", "기획 파트장"
                )

            )
        )

    }

    private fun itemClickEvent() {
        followerAdapter.setItemClickListener(object : FollowerAdapter.OnItemClickListener {
            override fun onClick(view: View, position: Int) {
                val name = followerAdapter.currentList[position].name
                val introduce = followerAdapter.currentList[position].introduction
                val intent = Intent(context, DetailActivity::class.java)
                    .putExtra("name", name)
                    .putExtra("introduction", introduce)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
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