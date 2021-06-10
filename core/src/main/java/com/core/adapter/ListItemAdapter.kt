package com.core.adapter

import android.os.Parcelable
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.core.extensions.ListItemDelegate
import com.core.extensions.ListItemViewHolder

class ListItemAdapter(
    private var viewLifecycleOwner: LifecycleOwner? = null,
    vararg delegates: ListItemDelegate<out Parcelable, out ViewDataBinding>
) : ListAdapter<Parcelable, ListItemViewHolder<Parcelable, ViewDataBinding>>(ItemCallback) {

    companion object {
        object ItemCallback : DiffUtil.ItemCallback<Parcelable>() {
            override fun areItemsTheSame(oldItem: Parcelable, newItem: Parcelable): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Parcelable, newItem: Parcelable): Boolean {
                return oldItem.equals(newItem)
            }
        }
    }

    private val delegatesIndexMap: Map<Class<*>, Int>
    private val delegatesList: List<ListItemDelegate<Parcelable, *>>

    init {
        val map = mutableMapOf<Class<*>, Int>()
        delegates.forEachIndexed { index, delegate ->
            if (map.put(delegate.itemType(), index) != null) throw IllegalArgumentException()
        }

        delegatesIndexMap = map
        delegatesList = delegates.map {
            @Suppress("UNCHECKED_CAST")
            it as? ListItemDelegate<Parcelable, *> ?: throw IllegalArgumentException()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return delegatesIndexMap[getItem(position)::class.java] ?: throw IllegalArgumentException()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListItemViewHolder<Parcelable, ViewDataBinding> =
        delegatesList[viewType].createViewHolder(parent, viewLifecycleOwner)

    override fun onBindViewHolder(
        holder: ListItemViewHolder<Parcelable, ViewDataBinding>,
        position: Int
    ) = delegatesList[getItemViewType(position)].bindView(
        position,
        getItem(position),
        holder as ListItemViewHolder<Parcelable, Nothing>
    )

    override fun onViewRecycled(holder: ListItemViewHolder<Parcelable, ViewDataBinding>) {
        val listItem: Any = holder.listItem ?: return
        val itemViewType: Int = delegatesIndexMap[listItem::class.java] ?: return
        delegatesList[itemViewType].onViewRecycled(holder as ListItemViewHolder<Parcelable, Nothing>)
    }
}