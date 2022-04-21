package org.sopt.seminar.presentation.repo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.seminar.util.MyTouchHelperCallback
import org.sopt.seminar.databinding.ItemRepoListBinding
import java.util.*

class RepoAdapter : ListAdapter<RepoData, RepoAdapter.RepoViewHolder>(
    DIFFUTIL
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val binding =
            ItemRepoListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepoViewHolder(binding)

    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    override fun getItemCount(): Int = currentList.size

    class RepoViewHolder(
        private val binding: ItemRepoListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(repoData: RepoData) {
          binding.repo = repoData
        }
    }

    companion object {
        val DIFFUTIL = object : DiffUtil.ItemCallback<RepoData>() {
            override fun areItemsTheSame(
                oldItem: RepoData,
                newItem: RepoData
            ): Boolean {
                return oldItem.repo == newItem.repo
            }

            override fun areContentsTheSame(
                oldItem: RepoData,
                newItem: RepoData
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

//    interface OnStartDragListener {
//        fun onStartDrag(viewHolder: RepoAdapter.RepoViewHolder)
//    }
//
//    override fun onItemMove(fromPosition: Int, toPosition: Int) {
//        Collections.swap(repoList, fromPosition, toPosition)
//        notifyItemMoved(fromPosition, toPosition)
//    }
//
//    override fun onItemSwipe(position: Int) {
//        repoList.removeAt(position)
//        notifyItemRemoved(position)
//    }
//
//    fun afterDragAndDrop() {
//        notifyDataSetChanged()

    }

