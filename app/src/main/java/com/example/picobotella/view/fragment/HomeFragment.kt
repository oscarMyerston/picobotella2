package com.example.picobotella.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.picobotella.R
import com.example.picobotella.databinding.FragmentHomeBinding
import com.example.picobotella.viewmodel.JuegoViewModel

class HomeFragment : Fragment() {
    private val juegoViewModel: JuegoViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        controladores(view)
        observadorViewModel()
    }

    private fun controladores(view: View) {
        binding.btnGirar.setOnClickListener {
            juegoViewModel.girarBotella()
        }
    }

    private fun observadorViewModel() {
        juegoViewModel.rotacionBotella.observe(viewLifecycleOwner) { rotacion ->
            binding.ivBotella.startAnimation(rotacion)
        }
    }
}