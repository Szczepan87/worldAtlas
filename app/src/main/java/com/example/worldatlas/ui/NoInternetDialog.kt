package com.example.worldatlas.ui

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.worldatlas.R

typealias OnOKDialogButtonClickListener = (Boolean) -> Unit

class NoInternetDialog : DialogFragment() {

    var onOKDialogButtonClickListener: OnOKDialogButtonClickListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(requireActivity())

        builder.setTitle(getString(R.string.no_data))
        builder.setMessage(getString(R.string.no_db_detected))
        builder.setPositiveButton(
            "OK"
        ) { _, _ -> onOKDialogButtonClickListener?.invoke(true) }

        return builder.create()
    }
}