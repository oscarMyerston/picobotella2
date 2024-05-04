package com.example.picobotella.view.dialogo

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.example.picobotella.R
import com.example.picobotella.databinding.DialogoMostrarRetoBinding
import com.example.picobotella.model.Pokemon
import com.example.picobotella.viewmodel.JuegoViewModel

object DialogoMostrarReto {

    fun showDialogMostrarReto(context: Context, audioFondo: MediaPlayer, mensajeReto: String, pokemon: Pokemon, juegoViewModel: JuegoViewModel) {
        val inflater = LayoutInflater.from(context)
        val binding = DialogoMostrarRetoBinding.inflate(inflater)
        val alertDialog = AlertDialog.Builder(context).create()
        alertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        alertDialog.setCancelable(false)
        alertDialog.setView(binding.root)
        if (juegoViewModel.isConnect(context)) {
            Glide.with(context).load(pokemon?.img).into(binding.ivReto)
        } else {
            binding.ivReto.setImageResource(R.drawable.logoutiltek)
            Toast.makeText(context, "No tienes conexión a Internet", Toast.LENGTH_LONG).show()
        }
        binding.tvReto.text = mensajeReto
        binding.btnCerrar.setOnClickListener {
            audioFondo.start()
            alertDialog.dismiss()
        }

        // No olvidar
        alertDialog.show()
    }
}