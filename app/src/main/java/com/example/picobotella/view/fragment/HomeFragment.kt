package com.example.picobotella.view.fragment

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.picobotella.R
import com.example.picobotella.databinding.FragmentHomeBinding
import com.example.picobotella.viewmodel.JuegoViewModel
import kotlinx.coroutines.runBlocking

class HomeFragment : Fragment() {
    // private lateinit var navController: NavController
    private lateinit var audioFondo: MediaPlayer
    private lateinit var audioGiroBotella: MediaPlayer
    private lateinit var audioMostrarReto: MediaPlayer
    private lateinit var audioBoton: MediaPlayer
    private lateinit var audioSuspenso: MediaPlayer

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
        observadorHabilitarBoton()
        observadorCerpentinaOn()
        observadorShowDialogReto()
        controladoresMultimedia()
    }

    private fun controladoresMultimedia() {
        audioFondo = MediaPlayer.create(context, R.raw.musicafondo)
        audioGiroBotella = MediaPlayer.create(context, R.raw.audiobotella)
        audioMostrarReto = MediaPlayer.create(context, R.raw.audioreto)
        audioBoton = MediaPlayer.create(context, R.raw.audioboton)
        audioSuspenso = MediaPlayer.create(context, R.raw.audiosuspenso)
        audioFondo.start()
    }

    private fun observadorShowDialogReto() {
        juegoViewModel.statusShowDialog.observe(viewLifecycleOwner) { status ->
            if (status) {
                runBlocking {
                    audioSuspenso.start()
                    juegoViewModel.esperar(3)
                }
                audioSuspenso.pause()
                val mensajeReto = "Debes tomar dos tragos"
                juegoViewModel.dialogoMostraReto(requireContext(), audioFondo, mensajeReto)
                audioGiroBotella.pause()
                audioMostrarReto.start()
                audioBoton.pause()
            }
        }
    }

    private fun observadorCerpentinaOn() {
        juegoViewModel.isCerpentina.observe(viewLifecycleOwner) { status ->
            binding.lottieCerpentina.isVisible = status
            binding.lottieCerpentina.playAnimation()
        }
    }

    private fun observadorHabilitarBoton() {
        juegoViewModel.habilitarBoton.observe(viewLifecycleOwner) { habilitarBoton ->
            binding.btnGirar.isVisible = habilitarBoton
        }

    }

    private fun controladores(view: View) {
        // navController = Navigation.findNavController(view)

        binding.icContentMenu.idImgReglas.setOnClickListener {
            audioFondo.pause()
            findNavController().navigate(R.id.action_homeFragment_to_reglasJuegoFragment)
            juegoViewModel.statusShowDialog(false)
        }

        binding.btnGirar.setOnClickListener {
            juegoViewModel.girarBotella()
        }
    }

    private fun observadorViewModel() {
        juegoViewModel.estadoRotacion.observe(viewLifecycleOwner) { estadoRotacion ->
            if (estadoRotacion) {
                audioBoton.start()
                audioFondo.pause()
                audioGiroBotella.start()
                juegoViewModel.rotacionBotella.observe(viewLifecycleOwner) { rotacion ->
                    binding.ivBotella.startAnimation(rotacion)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        audioBoton.pause()
    }

    override fun onPause() {
        super.onPause()
        audioFondo.pause()
    }
}