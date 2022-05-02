package org.sopt.seminar.presentation.follower

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.seminar.util.MyTouchHelperCallback
import org.sopt.seminar.databinding.ItemFollowerListBinding
import java.util.*

class FollowerAdapter : ListAdapter<FollowerData, FollowerAdapter.FollowerViewHolder>(DIFFUTIL),
    MyTouchHelperCallback.OnItemMoveListener {

    private lateinit var itemClickListener: OnItemClickListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
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
        fun onBind(followerData: FollowerData) {
            binding.follower = followerData
        }
    }

    companion object {
        val DIFFUTIL = object : DiffUtil.ItemCallback<FollowerData>() {
            override fun areItemsTheSame(
                oldItem: FollowerData,
                newItem: FollowerData
            ): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(
                oldItem: FollowerData,
                newItem: FollowerData
            ): Boolean {
                return oldItem == newItem
            }
        }
    }


    interface OnStartDragListener {
        fun onStartDrag(viewHolder: FollowerViewHolder)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        Collections.swap(currentList, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemSwipe(position: Int) {
        currentList.removeAt(position)
        notifyItemRemoved(position)
    }

    fun afterDragAndDrop() {
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onClick(data: View, position: Int)
    }

    fun setItemClickListener(itemClickListener: OnItemClickListener) {
        this.itemClickListener = itemClickListener
    }
}