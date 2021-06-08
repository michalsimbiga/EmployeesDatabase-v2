package com.android.presentation.first

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.core.Constants.FragmentArgs.ARG_DIALOG_REQUEST
import com.core.ui.BaseDialogFragment
import com.prosoma.livingwell.R
import com.prosoma.livingwell.databinding.DialogFragmentFirstBinding

class FirstDialogFragment : BaseDialogFragment<DialogFragmentFirstBinding>() {

    override val layoutId: Int = R.layout.dialog_fragment_first

    private val requestName: String
        get() = arguments?.getString(ARG_DIALOG_REQUEST) ?: throw IllegalStateException()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setPositiveButton("YES") { _, _ ->
                findNavController().previousBackStackEntry?.savedStateHandle?.set(requestName, true)
                dismiss()
            }
            .setNegativeButton("NO") { _, _ ->
                findNavController().previousBackStackEntry?.savedStateHandle?.set(
                    requestName,
                    false
                )
                dismiss()
            }
            .create()
    }
}