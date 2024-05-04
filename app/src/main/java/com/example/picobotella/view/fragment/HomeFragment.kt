package com.example.picobotella.view.fragment

import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.picobotella.R
import com.example.picobotella.databinding.FragmentHomeBinding
import com.example.picobotella.model.Pokemon
import com.example.picobotella.model.Reto
import com.example.picobotella.viewmodel.JuegoViewModel

class HomeFragment : Fragment() {
    // private lateinit var navController: NavController
    private lateinit var listaReto: MutableList<Reto>
    private lateinit var audioFondo: MediaPlayer
    private lateinit var audioGiroBotella: MediaPlayer
    private lateinit var audioMostrarReto: MediaPlayer
    private lateinit var audioBoton: MediaPlayer
    private lateinit var audioSuspenso: MediaPlayer
    private lateinit var listaPokemon: MutableList<Pokemon>

    private val juegoViewModel: JuegoViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    var sonido = false
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
        observadorSonido()
        observadorListaReto()
    }

    private fun observadorListaPokemon() {
        listaPokemon = mutableListOf()
        juegoViewModel.listaPokemon()
        juegoViewModel.listaPokemon.observe(viewLifecycleOwner){ lista ->
            listaPokemon = lista
        }
    }

    private fun observadorListaReto() {
        listaReto = mutableListOf()
        juegoViewModel.obtenerListaReto()
        juegoViewModel.listaReto.observe(viewLifecycleOwner) {  lista ->
            listaReto = lista
        }
    }

    private fun observadorSonido() {
        juegoViewModel.habilitarSonido.observe(viewLifecycleOwner) { status ->
            if (status) {
                audioFondo.setVolume(0f, 0f)
                binding.icContentMenu.idImgVolume.isVisible = !status
                binding.icContentMenu.idImgNoVolume.isVisible = status
            } else {
                audioFondo.setVolume(1f, 1f)
                binding.icContentMenu.idImgVolume.isVisible = !status
                binding.icContentMenu.idImgNoVolume.isVisible = status
            }
        }
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
                val countDownTimer = object : CountDownTimer(4000, 1000) {
                    override fun onTick(p0: Long) {
                         audioSuspenso.start()
                        binding.tvCuentaRegresiva.text = (p0 / 1000).toString()
                    }

                    override fun onFinish() {
                        audioSuspenso.pause()
                        audioMostrarReto.start()
                        juegoViewModel.dialogoMostraReto(
                            requireContext(),
                            audioFondo,
                            juegoViewModel.obtenerDescripcionReto(listaReto),
                            juegoViewModel.obtenerPokemon(listaPokemon),
                            juegoViewModel
                        )
                        audioGiroBotella.pause()
                        audioMostrarReto.start()
                        audioBoton.pause()
                        binding.tvCuentaRegresiva.text = ""
                    }
                }
                countDownTimer.start()
            }
            /*if (status) {
                runBlocking {
                    audioSuspenso.start()
                    juegoViewModel.esperar(3)
                }
                audioSuspenso.pause()

                juegoViewModel.dialogoMostraReto(
                    requireContext(),
                    audioFondo,
                    juegoViewModel.obtenerDescripcionReto(listaReto)
                )
                audioGiroBotella.pause()
                audioMostrarReto.start()
                audioBoton.pause()
            }*/
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
            observadorListaPokemon()
        }

        binding.icContentMenu.idImgEstrella.setOnClickListener {
            audioFondo.pause()
            findNavController().navigate(R.id.action_homeFragment_to_webViewFragment)
            juegoViewModel.statusShowDialog(false)
        }

        binding.icContentMenu.clVolumen.setOnClickListener {
            sonido = !sonido
            juegoViewModel.setHabilitarSonido(sonido)
        }

        binding.icContentMenu.idImgAgregarReto.setOnClickListener {
            audioFondo.pause()
            findNavController().navigate(R.id.action_homeFragment_to_agregarRetoFragment)
            juegoViewModel.statusShowDialog(false)
        }

        binding.icContentMenu.idBtnCompartir.setOnClickListener {
            juegoViewModel.compartirApp(audioFondo, requireActivity())
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