package com.example.picobotella.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.RotateAnimation
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.picobotella.model.Reto
import com.example.picobotella.repository.RetoRepository
import com.example.picobotella.utils.Constants.TIEMPO
import com.example.picobotella.view.MainActivity
import com.example.picobotella.view.dialogo.DialogoMostrarReto.showDialogMostrarReto
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class JuegoViewModel(application: Application): AndroidViewModel(application) {
    private val context = getApplication<Application>()
    private val retoRepository = RetoRepository(context)

    private val _rotacionBotella = MutableLiveData<RotateAnimation>()
    val rotacionBotella: LiveData<RotateAnimation> get() = _rotacionBotella

    private val _habilitarBoton = MutableLiveData(true)
    val habilitarBoton: LiveData<Boolean> get() = _habilitarBoton

    private val _isCerpentina = MutableLiveData(false)
    val isCerpentina: LiveData<Boolean> get() = _isCerpentina

    private val _estadoRotacion = MutableLiveData(false)
    val estadoRotacion: LiveData<Boolean> get() = _estadoRotacion

    private val _statusShowDialog = MutableLiveData(false)
    val statusShowDialog: LiveData<Boolean> get() = _statusShowDialog

    private val _habilitarSonido = MutableLiveData(false)
    val habilitarSonido: LiveData<Boolean> get() = _habilitarSonido

    private val _listaReto = MutableLiveData<MutableList<Reto>>()
    val listaReto: LiveData<MutableList<Reto>> get() = _listaReto


    private val _progresState = MutableLiveData(false)
    val progresState: LiveData<Boolean> get() = _progresState

    fun splashScreen(activity: Activity) {
        val executor = Executors.newSingleThreadScheduledExecutor()
        executor.schedule({
            activity.startActivity(Intent(activity, MainActivity::class.java))
            activity.finish()
        }, TIEMPO, TimeUnit.MILLISECONDS)

    }

    fun girarBotella() {
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
                _habilitarBoton.value = false
                _isCerpentina.value = true
            }

            override fun onAnimationEnd(p0: Animation?) {
                _habilitarBoton.value = true
                _isCerpentina.value = false
                _estadoRotacion.value = false
                _statusShowDialog.value = true
            }

            override fun onAnimationRepeat(p0: Animation?) {

            }
        })

        _rotacionBotella.value = rotacion
    }

    fun dialogoMostraReto(context: Context, audioFondo: MediaPlayer, mensajeReto: String) {
        showDialogMostrarReto(context, audioFondo, mensajeReto)
    }

    fun statusShowDialog(status: Boolean) {
        _statusShowDialog.value = status
    }

    suspend fun esperar(tiempo: Int) {
        delay(tiempo * 1000L)
    }

    fun setHabilitarSonido(habilitar: Boolean) {
        _habilitarSonido.value = habilitar
    }

    fun agregarReto(reto: Reto) {
        viewModelScope.launch {
            _progresState.value = true
            try {
                retoRepository.agregarReto(reto)
                _progresState.value = false
            } catch (e: Exception) {
                _progresState .value = false
            }
        }
    }

    fun obtenerListaReto() {
        viewModelScope.launch {
            _progresState.value = true
            try {
                _listaReto.value =  retoRepository.obtenerListaReto()
                _progresState.value = false

            } catch (e: Exception) {
                _progresState.value = false
            }
        }
    }

    fun deleteReto(reto: Reto) {
        viewModelScope.launch {
            _progresState.value = true
            try {
                retoRepository.delete(reto)
                _progresState.value = false
            } catch (e: Exception) {
                _progresState.value = false
            }
        }
    }

    fun updateReto(reto: Reto) {
        viewModelScope.launch {
            _progresState.value = true
            try {
                retoRepository.update(reto)
                _progresState.value = false
            } catch (e: Exception) {
                _progresState.value = false
            }
        }
    }

}