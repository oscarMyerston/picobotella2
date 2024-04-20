package com.example.picobotella.view.dialogo

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import com.example.picobotella.databinding.DialogoAgregarRetoBinding
import com.example.picobotella.model.Reto
import com.example.picobotella.viewmodel.JuegoViewModel

object DialogoAgregarReto {
    fun showDialogAgregarReto(
        context: Context,
        juegoViewModel: JuegoViewModel,
        actualizarLista:() -> Unit
        ) {
        val inflater = LayoutInflater.from(context)
        val binding = DialogoAgregarRetoBinding.inflate(inflater)
        val alertDialog = AlertDialog.Builder(context).create()
        alertDialog.setCancelable(false)
        alertDialog.setView(binding.root)

        binding.idEditPeniencia.addTextChangedListener {
            binding.idBtnGuardar.isEnabled = binding.idEditPeniencia.toString().isNotEmpty()
        }

        binding.idBtnCancelar.setOnClickListener {
            alertDialog.dismiss()
        }

        binding.idBtnGuardar.setOnClickListener {
            val descripcion = binding.idEditPeniencia.text.toString().trim()
            val reto = Reto(descripcionReto = descripcion)
            juegoViewModel.agregarReto(reto)
            alertDialog.dismiss()
            actualizarLista.invoke()
        }

        alertDialog.show()
    }
}