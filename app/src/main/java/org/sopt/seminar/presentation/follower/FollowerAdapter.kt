package org.sopt.seminar.presentation.follower

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.seminar.MyTouchHelperCallback
import org.sopt.seminar.databinding.ItemFollowerListBinding
import java.util.*

class FollowerAdapter : RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>(),
    MyTouchHelperCallback.OnItemMoveListener {


    val followerList = mutableListOf<FollowerData>()
    private lateinit var itemClickListener: OnItemClickListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val binding =
            ItemFollowerListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowerViewHolder(binding)

    }
    override fun getItemCount(): Int = followerList.size

    interface OnStartDragListener {
        fun onStartDrag(viewHolder: FollowerViewHolder)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        Collections.swap(followerList, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemSwipe(position: Int) {
        followerList.removeAt(position)
        notifyItemRemoved(position)
    }

    fun afterDragAndDrop() {
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.onBind(followerList[position])
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

    inner class FollowerViewHolder(
        private val binding: ItemFollowerListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: FollowerData) {
            binding.tvName.text = data.name
            binding.tvIntroduce.text = data.introduction
        }
    }

    interface OnItemClickListener {
        fun onClick(data: View, position: Int)
    }

    fun setItemClickListener(itemClickListener: OnItemClickListener) {
        this.itemClickListener = itemClickListener
    }
}