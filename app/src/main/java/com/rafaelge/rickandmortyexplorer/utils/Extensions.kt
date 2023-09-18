package com.rafaelge.rickandmortyexplorer.utils

import android.app.AlertDialog
import android.content.Context
import com.rafaelge.rickandmortyexplorer.R

object Extensions {

    fun showDialog(context: Context, errorMessage: String) {
        val builder = AlertDialog.Builder(context)

        builder.setTitle(errorMessage)
        builder.setPositiveButton(context.getString(R.string.accept_ok_dialog)) { dialog, _ ->
            dialog.dismiss()
        }
        val alertDialog: AlertDialog = builder.create()
        return alertDialog.show()
    }
}