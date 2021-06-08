package com.android.presentation.home

import androidx.fragment.app.Fragment
import com.core.navigation.BaseFragmentNavigator
import javax.inject.Inject

class HomeNavigator @Inject constructor(
    fragment: Fragment
) : BaseFragmentNavigator(fragment) {

    fun navigateToAddFragment() {
        navigate(HomeFragmentDirections.actionHomeToEdit())
    }
}