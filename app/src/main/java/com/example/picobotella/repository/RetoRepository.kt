package com.example.picobotella.repository

import android.content.Context
import com.example.picobotella.data.DaoReto
import com.example.picobotella.model.Pokemon
import com.example.picobotella.model.Reto
import com.example.picobotella.service.ApiService
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RetoRepository @Inject constructor(
    @ApplicationContext val context: Context,
    private val daoReto: DaoReto,
    private val apiService: ApiService
){

    suspend fun agregarReto(reto: Reto) {
        withContext(Dispatchers.IO) {
            daoReto.agregarReto(reto)
        }
    }

    suspend fun obtenerListaReto(): MutableList<Reto> {
        return withContext(Dispatchers.IO) {
            daoReto.obtenerListaReto()
        }
    }

    suspend fun  delete(reto: Reto) {
        withContext(Dispatchers.IO) {
            daoReto.delete(reto)
        }
    }

    suspend fun update(reto: Reto) {
        withContext(Dispatchers.IO) {
            daoReto.update(reto)
        }
    }

    suspend fun getListaPokemon(): MutableList<Pokemon> {
        return withContext(Dispatchers.IO) {
            val listaPokemon = apiService.getPokemon()
            listaPokemon.pokemon
        }
    }

}