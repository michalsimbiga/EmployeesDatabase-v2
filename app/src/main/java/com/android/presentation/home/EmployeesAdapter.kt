package com.android.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.model.EmployeeItem
import com.prosoma.livingwell.databinding.ItemHomeEmployeeBinding

class EmployeesAdapter : ListAdapter<EmployeeItem, EmployeesAdapter.UserViewHolder>(ItemCallback) {

    companion object {
        private val ItemCallback = object : DiffUtil.ItemCallback<EmployeeItem>() {

            override fun areItemsTheSame(oldItem: EmployeeItem, newItem: EmployeeItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: EmployeeItem, newItem: EmployeeItem): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHomeEmployeeBinding.inflate(inflater, parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class UserViewHolder(
        private val binding: ItemHomeEmployeeBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: EmployeeItem) {
            binding.employee = data
        }
    }
}