package com.android.presentation.edit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.model.AddressItem
import com.core.extensions.empty
import com.core.getDiffUtilCallback
import com.employeedatabase.databinding.ItemAddAddressBinding
import com.employeedatabase.databinding.ItemAddressBinding
import com.employeedatabase.databinding.ItemEditableAddressBinding

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
                clear()
            }
            binding.discardAddressButton.setOnClickListener {
                onRemoveAddressClick.invoke()
                clear()
            }
        }

        private fun constructAddress() = AddressItem(
            null,
            binding.streetEditableEditText.text.toString(),
            binding.cityEditableEditText.text.toString(),
            binding.zipEditableEditText.text.toString(),
            binding.countryEditableEditText.text.toString()
        )

        private fun clear() {
            binding.streetEditableEditText.setText(String.empty)
            binding.cityEditableEditText.setText(String.empty)
            binding.zipEditableEditText.setText(String.empty)
            binding.countryEditableEditText.setText(String.empty)
        }

    }

    inner class FilledAddressViewHolder(
        private val binding: ItemAddressBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(address: AddressItem) {
            binding.address = address
        }
    }
}