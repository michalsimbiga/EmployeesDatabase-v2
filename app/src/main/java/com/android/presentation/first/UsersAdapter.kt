package com.android.presentation.first

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.domain.models.User
import com.prosoma.livingwell.databinding.ItemFirstUserBinding

class UsersAdapter : ListAdapter<User, UsersAdapter.UserViewHolder>(ItemCallback) {

    companion object {
        private val ItemCallback = object : DiffUtil.ItemCallback<User>() {

            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id.name == newItem.id.name && oldItem.id.name == newItem.id.name
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFirstUserBinding.inflate(inflater, parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class UserViewHolder(
        private val binding: ItemFirstUserBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: User) {
            binding.user = data

            Glide.with(binding.imageUserItemPhoto)
                .load(data.picture.large)
                .into(binding.imageUserItemPhoto)
        }
    }
}