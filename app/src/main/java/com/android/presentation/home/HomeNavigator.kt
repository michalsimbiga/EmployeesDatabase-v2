package com.android.presentation.home

import androidx.fragment.app.Fragment
import com.android.model.EmployeeItem
import com.core.navigation.BaseFragmentNavigator
import javax.inject.Inject

class HomeNavigator @Inject constructor(
    fragment: Fragment
) : BaseFragmentNavigator(fragment) {

    fun navigateToAddFragment() {
        navigate(HomeFragmentDirections.actionHomeToEdit())
    }

    fun navigateToAddFragmentWithEmployee(employee: EmployeeItem) {
        navigate(HomeFragmentDirections.actionHomeToEditWithArg(employee = employee))
    }
}