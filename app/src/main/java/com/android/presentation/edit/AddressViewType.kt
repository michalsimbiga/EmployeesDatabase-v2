package com.android.presentation.edit

import com.android.model.AddressItem

sealed class AddressViewType {
    object AddNew : AddressViewType()
    data class Editable(val address: AddressItem) : AddressViewType()
    data class Filled(val address: AddressItem) : AddressViewType()
}