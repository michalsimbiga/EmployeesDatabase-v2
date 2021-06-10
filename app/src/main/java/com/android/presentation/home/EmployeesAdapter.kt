package com.android.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.model.EmployeeItem
import com.android.presentation.add.AddressViewType
import com.android.presentation.add.AddressesAdapter
import com.core.getDiffUtilCallback
import com.employeedatabase.databinding.ItemEmployeeBinding

class EmployeesAdapter(
    private val onEditClick: (EmployeeItem) -> Unit,
    private val onDeleteClick: (EmployeeItem) -> Unit
) : ListAdapter<EmployeeItem, EmployeesAdapter.UserViewHolder>(
    getDiffUtilCallback { oldItem, newItem -> oldItem == newItem }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemEmployeeBinding.inflate(inflater, parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class UserViewHolder(
        private val binding: ItemEmployeeBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val adapter = AddressesAdapter({},{},{})

        fun bind(data: EmployeeItem) {
            with(binding) {
                rvEmployeeAddresses.adapter = adapter
                adapter.submitList(data.addressess.map { AddressViewType.Filled(it) })

                employee = data
                homeDeleteEmployeeButton.setOnClickListener { onDeleteClick.invoke(data) }
                homeEditEmployeeButton.setOnClickListener { onEditClick.invoke(data) }
            }
        }
    }
}