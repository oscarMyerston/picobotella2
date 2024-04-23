package com.example.picobotella.view.dialogo

import android.app.AlertDialog
import android.content.Context
import com.example.picobotella.R
import com.example.picobotella.model.Reto
import com.example.picobotella.viewmodel.JuegoViewModel

fun DialogoEliminarReto(context: Context, viewModel: JuegoViewModel, reto: Reto):AlertDialog {
    val builder = AlertDialog.Builder(context)
    builder.setCancelable(false)
    builder.setTitle(R.string.title_dialog_eliminar)
        .setMessage("\n${reto.descripcionReto}")
        .setPositiveButton("SI") { dialog, with ->
            viewModel.deleteReto(reto)
            dialog.dismiss()
            viewModel.obtenerListaReto()
        }

        .setNegativeButton("NO") { dialog, with ->
            dialog.dismiss()
        }
    return builder.create()
}
