package com.example.picobotella.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.picobotella.R
import com.example.picobotella.databinding.FragmentAgregarRetoBinding
import com.example.picobotella.view.adapter.RetoAdapter
import com.example.picobotella.view.dialogo.DialogoAgregarReto.showDialogAgregarReto
import com.example.picobotella.viewmodel.JuegoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AgregarRetoFragment : Fragment() {
    private lateinit var binding: FragmentAgregarRetoBinding
    private val juegoViewModel: JuegoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentAgregarRetoBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        controladores()
        observadorViewModel()
    }

    private fun controladores() {
        binding.icContentBarra.ivAtras.setOnClickListener {
            findNavController(). popBackStack()
        }

        binding.floatBtn.setOnClickListener {
            showDialogAgregarReto(requireContext(), juegoViewModel) {
                observadorListaReto()
            }
        }
    }

    private fun observadorViewModel() {
        observadorListaReto()
        observadorProgress()
    }

    private fun observadorProgress() {
        juegoViewModel.progresState.observe(viewLifecycleOwner) { status ->
            binding.progress.isVisible = status
        }
    }

    private fun observadorListaReto() {
        juegoViewModel.obtenerListaReto()
        juegoViewModel.listaReto.observe(viewLifecycleOwner) { lista ->
            val recycler = binding.reciclerview
            val layoutManager = LinearLayoutManager(context)
            layoutManager.reverseLayout = true
            recycler.layoutManager = layoutManager
            val adapter = RetoAdapter(lista, juegoViewModel)
            recycler.adapter = adapter
            adapter.notifyDataSetChanged()

        }
    }
}
