package com.android.presentation.add

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.model.AddressItem
import com.core.getDiffUtilCallback
import com.prosoma.livingwell.databinding.ItemAddAddressBinding
import com.prosoma.livingwell.databinding.ItemAddressBinding
import com.prosoma.livingwell.databinding.ItemEditableAddressBinding

class AddressesAdapter(
    private val onAddNewAddressClick: () -> Unit,
    private val onConfirmAddressClick: (AddressItem) -> Unit,
    private val onRemoveAddressClick: () -> Unit,
) : ListAdapter<AddressViewType, RecyclerView.ViewHolder>(
    getDiffUtilCallback { oldItem, newItem -> oldItem == newItem }
) {

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is AddressViewType.AddNew -> 0
            is AddressViewType.Editable -> 1
            is AddressViewType.Filled -> 2
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            0 -> {
                val binding = ItemAddAddressBinding.inflate(inflater, parent, false)
                AddNewAddressViewHolder(binding)
            }
            1 -> {
                val binding = ItemEditableAddressBinding.inflate(inflater, parent, false)
                EditableAddressViewHolder(binding)
            }
            2 -> {
                val binding = ItemAddressBinding.inflate(inflater, parent, false)
                FilledAddressViewHolder(binding)
            }
            else -> throw Error()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            0 -> (holder as AddNewAddressViewHolder).bind()
            1 -> (holder as EditableAddressViewHolder).bind()
            2 -> (holder as FilledAddressViewHolder).bind((getItem(position) as AddressViewType.Filled).address)
        }
    }

    inner class AddNewAddressViewHolder(
        private val binding: ItemAddAddressBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.addAddressButton.setOnClickListener { onAddNewAddressClick.invoke() }
        }
    }

    inner class EditableAddressViewHolder(
        private val binding: ItemEditableAddressBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.acceptAddressButton.setOnClickListener {
                onConfirmAddressClick.invoke(constructAddress())
            }
            binding.discardAddressButton.setOnClickListener { onRemoveAddressClick.invoke() }
        }

        private fun constructAddress() = AddressItem(1, "asd")
    }

    inner class FilledAddressViewHolder(
        private val binding: ItemAddressBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(address: AddressItem) {
            binding.address = address
        }

        private fun constructAddress() = AddressItem(1, "asd")
    }
}