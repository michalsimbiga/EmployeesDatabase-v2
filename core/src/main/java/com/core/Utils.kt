package com.core

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

inline fun <reified T> getDiffUtilCallback(crossinline areItemsTheSame: (oldItem: T, newItem: T) -> Boolean): DiffUtil.ItemCallback<T> {
    return object : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T) = areItemsTheSame(oldItem, newItem)

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem == newItem
    }
}