package com.example.picobotella.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import com.example.picobotella.R
import com.example.picobotella.viewmodel.JuegoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
@AndroidEntryPoint
class Splash : AppCompatActivity() {
    private val juegoViewModel: JuegoViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        juegoViewModel.splashScreen(this)
    }
}