package com.core.extensions

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView

open class ListItemViewHolder<I : Parcelable, out B : ViewDataBinding>(
    val binding: B
) : RecyclerView.ViewHolder(binding.root) {

    var listItem: I? = null
}

interface ListItemDelegate<I : Parcelable, B : ViewDataBinding> {

    fun itemType(): Class<out I>
    fun createViewHolder(
        parent: ViewGroup,
        viewLifecycleOwner: LifecycleOwner?
    ): ListItemViewHolder<I, B>

    fun bindView(position: Int, item: I, holder: ListItemViewHolder<I, B>)

    fun onViewRecycled(holder: ListItemViewHolder<I, B>)
}

open class LayoutItemDelegate<I : Parcelable, B : ViewDataBinding>(
    private val type: Class<out I>,
    @LayoutRes
    private val layoutId: Int
) : ListItemDelegate<I, B> {

    override fun itemType(): Class<out I> = type

    override fun createViewHolder(
        parent: ViewGroup,
        viewLifecycleOwner: LifecycleOwner?
    ): ListItemViewHolder<I, B> {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<B>(inflater, layoutId, parent, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return ListItemViewHolder(binding)
    }

    override fun bindView(position: Int, item: I, holder: ListItemViewHolder<I, B>) {
        holder.listItem = item
    }

    override fun onViewRecycled(holder: ListItemViewHolder<I, B>) = Unit
}

class CreateItemDelegate<I : Parcelable, B : ViewDataBinding>(
    private val origin: ListItemDelegate<I, B>,
    private val createBlock: ListItemViewHolder<I, B>.() -> Unit
) : ListItemDelegate<I, B> by origin {

    override fun createViewHolder(parent: ViewGroup, viewLifecycleOwner: LifecycleOwner?) =
        origin.createViewHolder(parent, viewLifecycleOwner)
            .apply(createBlock)
}

class BindItemDelegate<I : Parcelable, B : ViewDataBinding>(
    private val origin: ListItemDelegate<I, B>,
    private val bindBlock: ListItemViewHolder<I, B>.(I) -> Unit
) : ListItemDelegate<I, B> by origin {

    override fun bindView(position: Int, item: I, holder: ListItemViewHolder<I, B>) {
        origin.bindView(position, item, holder)
        holder.listItem?.let { holder.bindBlock(it) }
    }
}

inline fun <reified T : Parcelable, B : ViewDataBinding> itemDelegate(
    @LayoutRes layoutId: Int
) = LayoutItemDelegate<T, B>(T::class.java, layoutId)

fun <T : Parcelable, B : ViewDataBinding> ListItemDelegate<T, B>.create(
    block: ListItemViewHolder<T, B>.() -> Unit
) = CreateItemDelegate(this, block)

fun <T : Parcelable, B : ViewDataBinding> ListItemDelegate<T, B>.bind(
    block: ListItemViewHolder<T, B>.(item: T) -> Unit
) = BindItemDelegate(this, block)