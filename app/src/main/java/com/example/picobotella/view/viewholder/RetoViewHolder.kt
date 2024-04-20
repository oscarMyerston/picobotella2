package com.example.picobotella.view.viewholder

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.picobotella.databinding.ItemRetoBinding
import com.example.picobotella.model.Reto
import com.example.picobotella.viewmodel.JuegoViewModel

class RetoViewHolder(
    binding: ItemRetoBinding,
    juegoViewModel: JuegoViewModel) : ViewHolder(binding.root ) {

    private val viewModel = juegoViewModel
    private var binding: ItemRetoBinding

    init {
        this.binding = binding
    }

    fun setItemReto(reto: Reto) {
        binding.tvName.text = reto.descripcionReto
    }
}