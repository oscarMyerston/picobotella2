package com.example.picobotella.viewmodel

import android.app.Activity
import android.content.Intent
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.RotateAnimation
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.picobotella.utils.Constants.TIEMPO
import com.example.picobotella.view.MainActivity

class JuegoViewModel: ViewModel() {
    private val _rotacionBotella = MutableLiveData<RotateAnimation>()
    val rotacionBotella: LiveData<RotateAnimation> get() = _rotacionBotella

    private val _habilitarBoton = MutableLiveData(true)
    val habilitarBoton: LiveData<Boolean> get() = _habilitarBoton

    private val _isCerpentina = MutableLiveData(false)
    val isCerpentina: LiveData<Boolean> get() = _isCerpentina

    private val _estadoRotacion = MutableLiveData(false)
    val estadoRotacion: LiveData<Boolean> get() = _estadoRotacion

    fun splashScreen(activity: Activity) {
        val handler = Handler()
        handler.postDelayed({
            activity.startActivity(Intent(activity, MainActivity::class.java))
            activity.finish()
        },TIEMPO)
    }

    fun girarBotella() {
        _habilitarBoton.value = false
        _isCerpentina.value = true
        _estadoRotacion.value = true
        val grados = (Math.random() *3600) + 1800
        val rotacion = RotateAnimation( 0f, grados.toFloat(),
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f)
        rotacion.fillAfter = true
        rotacion.duration = 3600
        rotacion.interpolator = DecelerateInterpolator()

        rotacion.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                _habilitarBoton.value = true
                _isCerpentina.value = false
                _estadoRotacion.value = false
            }

            override fun onAnimationRepeat(p0: Animation?) {

            }
        })

        _rotacionBotella.value = rotacion
    }

}