package com.example.picobotella.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.picobotella.R
import com.example.picobotella.databinding.FragmentAgregarRetoBinding
import com.example.picobotella.viewmodel.JuegoViewModel


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
    }

    private fun observadorViewModel() {

    }
}
