package org.sopt.seminar.presentation.follower

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import org.sopt.seminar.HorizontalItemDecorator
import org.sopt.seminar.MyTouchHelperCallback
import org.sopt.seminar.VerticalItemDecorator
import org.sopt.seminar.databinding.FragmentFollowerBinding
import org.sopt.seminar.presentation.detail.DetailActivity

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
        itemClickEvent()
        return binding.root
    }

    private fun initAdapter() {
        followerAdapter = FollowerAdapter()
        binding.rvFollower.adapter = followerAdapter
        recyclerViewDecoration()

        val callback = MyTouchHelperCallback(followerAdapter)
        val touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(binding.rvFollower)
        binding.rvFollower.adapter = followerAdapter
        followerAdapter.startDrag(object : FollowerAdapter.OnStartDragListener {
            override fun onStartDrag(viewHolder: FollowerAdapter.FollowerViewHolder) {
                touchHelper.startDrag(viewHolder)
            }
        })

        followerAdapter.followerList.addAll(
            listOf(
                FollowerData("김수빈", "안드로이드 OB"),
                FollowerData("권용민", "안드로이드 OB"),
                FollowerData("이준원", "안드로이드 YB"),
                FollowerData("최윤정", "안드로이드 YB"),
                FollowerData("최유리", "안드로이드 YB"),
                FollowerData("이강민", "안드로이드 파트장"),
                FollowerData("김태현", "iOS 파트장"),
                FollowerData("김두범", "기획 파트장")
            )
        )
        followerAdapter.notifyDataSetChanged()

    }

    private fun itemClickEvent() {
        followerAdapter.setItemClickListener(object : FollowerAdapter.OnItemClickListener {
            override fun onClick(view: View, position: Int) {
                val name = followerAdapter.followerList[position].name
                val introduce = followerAdapter.followerList[position].introduction
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}