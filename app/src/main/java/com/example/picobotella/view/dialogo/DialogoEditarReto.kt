package com.example.picobotella.view.dialogo

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import com.example.picobotella.databinding.DialogoEditarRetoBinding
import com.example.picobotella.model.Reto
import com.example.picobotella.viewmodel.JuegoViewModel

object DialogoEditarReto {

    fun showDialogoEditReto(
        context: Context,
        juegoViewModel: JuegoViewModel,
        reto: Reto,
        actualizarLista: () -> Unit
    ) {
        val inflater = LayoutInflater.from(context)
        val binding = DialogoEditarRetoBinding.inflate(inflater)
        var alertDialog = AlertDialog.Builder(context).create()
        alertDialog.setCancelable(false)
        alertDialog.setView(binding.root)

        binding.idEditReto.addTextChangedListener {
            binding.idBtnEditar.isEnabled = binding.idEditReto.text.toString().isNotEmpty()
        }

        binding.idEditReto.setText(reto.descripcionReto)
        binding.idBtnCancelar.setOnClickListener {
            alertDialog.dismiss()
        }

        binding.idBtnEditar.setOnClickListener {
            var description = binding.idEditReto.text.toString().trim()
            val reto = Reto(reto.id, description)
            juegoViewModel.updateReto(reto)
            alertDialog.dismiss()
            actualizarLista.invoke()
        }
        alertDialog.show()
    }
}