package com.android.presentation.first

import androidx.fragment.app.Fragment
import com.android.presentation.first.FirstFragmentDirections
import com.core.navigation.BaseFragmentNavigator
import javax.inject.Inject

class FirstNavigator @Inject constructor(
    fragment: Fragment
) : BaseFragmentNavigator(fragment) {

    fun navigateToSecondFragment() {
        navigate(FirstFragmentDirections.actionFirstFragmentToSecondFragment())
    }
}