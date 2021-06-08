package com.android.presentation.main

import android.app.Activity
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.prosoma.core.Constants.DialogRequests.REQUEST_ACCEPT_TERMS
import com.prosoma.core.Constants.FragmentArgs.ARG_DIALOG_REQUEST
import com.core.navigation.BaseActivityNavigator
import com.domain.models.MainNavigator
import com.prosoma.livingwell.R
import javax.inject.Inject

class MainActivityNavigator @Inject constructor(
    private val activity: Activity
) : BaseActivityNavigator(), MainNavigator {

    override val navController: NavController
        get() = activity.findNavController(R.id.navContainerMain)

    override fun showFirstDialog() {
        navigate(
            R.id.action_global_firstDialogFragment,
            bundleOf(ARG_DIALOG_REQUEST to REQUEST_ACCEPT_TERMS)
        )
    }
}