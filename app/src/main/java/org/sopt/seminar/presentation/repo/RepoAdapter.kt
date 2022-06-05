package org.sopt.seminar.presentation.repo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.seminar.ResponseRepo
import org.sopt.seminar.databinding.ItemRepoListBinding

class RepoAdapter : ListAdapter<ResponseRepo, RepoAdapter.RepoViewHolder>(
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

    class RepoViewHolder(
        private val binding: ItemRepoListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(repoData: ResponseRepo) {
            binding.repo = repoData
        }
    }

    companion object {
        val DIFFUTIL = object : DiffUtil.ItemCallback<ResponseRepo>() {
            override fun areItemsTheSame(
                oldItem: ResponseRepo,
                newItem: ResponseRepo
            ): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(
                oldItem: ResponseRepo,
                newItem: ResponseRepo
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}




