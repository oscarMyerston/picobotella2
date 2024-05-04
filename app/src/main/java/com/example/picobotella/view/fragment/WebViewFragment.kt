package com.example.picobotella.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.picobotella.R
import com.example.picobotella.databinding.FragmentHomeBinding
import com.example.picobotella.databinding.FragmentWebViewBinding
import com.example.picobotella.viewmodel.JuegoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebViewFragment : Fragment() {

    private val juegoViewModel: JuegoViewModel by viewModels()
    private lateinit var binding: FragmentWebViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWebViewBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        controladores()
    }

    private fun controladores() {
        binding.icContentBarra.tvTituloBarra.text = getString(R.string.calificacion)
        binding.icContentBarra.ivAtras.setOnClickListener {
            findNavController().popBackStack()
        }
        asignarWebView()
    }

    private fun asignarWebView() {
        val urlWeb = juegoViewModel.obtenerUrlApp(requireActivity())

        binding.webview.apply {
            settings.apply {
                javaScriptEnabled = true
            }
            webViewClient = WebViewClient()
            loadUrl(urlWeb)
        }
    }

}