package com.android.presentation.main

import android.app.Activity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.core.navigation.BaseActivityNavigator
import com.domain.models.MainNavigator
import com.employeedatabase.R
import javax.inject.Inject

class MainActivityNavigator @Inject constructor(
    private val activity: Activity
) : BaseActivityNavigator(), MainNavigator {

    override val navController: NavController
        get() = activity.findNavController(R.id.navHostFragment)

}