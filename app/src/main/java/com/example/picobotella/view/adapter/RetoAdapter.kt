package com.example.picobotella.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.picobotella.databinding.ItemRetoBinding
import com.example.picobotella.model.Reto
import com.example.picobotella.view.viewholder.RetoViewHolder
import com.example.picobotella.viewmodel.JuegoViewModel

class RetoAdapter(
    private val listaReto:MutableList<Reto>,
    private val juegoViewModel: JuegoViewModel
    ) :
    RecyclerView.Adapter<RetoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RetoViewHolder {
        val binding = ItemRetoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RetoViewHolder(binding, juegoViewModel)
    }

    override fun getItemCount(): Int {
       return listaReto.size
    }

    override fun onBindViewHolder(retoViewHolder: RetoViewHolder, position: Int) {
        val reto = listaReto[position]
        retoViewHolder.setItemReto(reto)
    }
}