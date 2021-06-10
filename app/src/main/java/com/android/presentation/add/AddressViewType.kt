package com.android.presentation.add

import com.android.model.AddressItem

sealed class AddressViewType {
    object AddNew : AddressViewType()
    data class Editable(val address: AddressItem) : AddressViewType()
    data class Filled(val address: AddressItem) : AddressViewType()
}