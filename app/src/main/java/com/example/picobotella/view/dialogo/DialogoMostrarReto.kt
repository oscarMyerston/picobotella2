package com.example.picobotella.view.dialogo

import android.content.Context
import android.media.MediaPlayer
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.example.picobotella.databinding.DialogoMostrarRetoBinding

object DialogoMostrarReto {

    fun showDialogMostrarReto(context: Context, audioFondo: MediaPlayer, mensajeReto: String) {
        val inflater = LayoutInflater.from(context)
        val binding = DialogoMostrarRetoBinding.inflate(inflater)
        val alertDialog = AlertDialog.Builder(context).create()
        alertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        alertDialog.setCancelable(false)
        alertDialog.setView(binding.root)
        binding.tvReto.text = mensajeReto
        binding.btnCerrar.setOnClickListener {
            audioFondo.start()
            alertDialog.dismiss()
        }

        // No olvidar
        alertDialog.show()
    }
}