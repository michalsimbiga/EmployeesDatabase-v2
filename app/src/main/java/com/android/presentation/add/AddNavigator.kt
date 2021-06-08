package com.android.presentation.add

import androidx.fragment.app.Fragment
import com.core.navigation.BaseFragmentNavigator
import javax.inject.Inject

class AddNavigator @Inject constructor(
    fragment: Fragment
) : BaseFragmentNavigator(fragment) {

    fun navigateToHome() {
        navigate(AddFragmentDirections.actionAddToHome())
    }
}