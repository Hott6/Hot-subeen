package org.sopt.seminar.presentation.follower

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.sopt.seminar.util.MyTouchHelperCallback
import org.sopt.seminar.databinding.ItemFollowerListBinding
import org.sopt.seminar.domain.models.FollowerInfo
import java.util.*

class FollowerAdapter :
    ListAdapter<FollowerInfo, FollowerAdapter.FollowerViewHolder>(DIFFUTIL),
    MyTouchHelperCallback.OnItemMoveListener {

    private lateinit var itemClickListener: OnItemClickListener


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FollowerViewHolder {
        val binding =
            ItemFollowerListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowerViewHolder(binding)

    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.onBind(getItem(position))
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

    class FollowerViewHolder(
        private val binding: ItemFollowerListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(followerData: FollowerInfo) {
            binding.follower = followerData
            Glide.with(binding.root)
                .load(followerData.avatar_url)
                .circleCrop()
                .into(binding.ivProfile)
        }

    }

    companion object {
        val DIFFUTIL = object : DiffUtil.ItemCallback<FollowerInfo>() {
            override fun areItemsTheSame(
                oldItem: FollowerInfo,
                newItem: FollowerInfo
            ): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(
                oldItem: FollowerInfo,
                newItem: FollowerInfo
            ): Boolean {
                return oldItem == newItem
            }
        }
    }


    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        Collections.swap(currentList, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemSwipe(position: Int) {
        currentList.removeAt(position)
        notifyItemRemoved(position)
    }

    interface OnItemClickListener {
        fun onClick(data: View, position: Int)
    }

    fun setItemClickListener(itemClickListener: OnItemClickListener) {
        this.itemClickListener = itemClickListener
    }
}