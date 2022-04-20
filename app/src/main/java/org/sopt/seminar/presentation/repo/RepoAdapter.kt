package org.sopt.seminar.presentation.repo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.seminar.MyTouchHelperCallback
import org.sopt.seminar.databinding.ItemRepoListBinding
import org.sopt.seminar.presentation.follower.FollowerAdapter
import java.util.*

class RepoAdapter : RecyclerView.Adapter<RepoAdapter.RepoViewHolder>(),
    MyTouchHelperCallback.OnItemMoveListener {

    val repoList = mutableListOf<RepoData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val binding =
            ItemRepoListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepoViewHolder(binding)

    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.onBind(repoList[position])
    }

    override fun getItemCount(): Int = repoList.size

    class RepoViewHolder(
        private val binding: ItemRepoListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: RepoData) {
            binding.tvRepo.text = data.repo
            binding.tvRepomemo.text = data.introduction
        }
    }

    interface OnStartDragListener {
        fun onStartDrag(viewHolder: RepoAdapter.RepoViewHolder)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        Collections.swap(repoList, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemSwipe(position: Int) {
        repoList.removeAt(position)
        notifyItemRemoved(position)
    }

    fun afterDragAndDrop() {
        notifyDataSetChanged()

    }

}