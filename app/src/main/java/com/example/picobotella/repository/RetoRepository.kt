package com.example.picobotella.repository

import android.content.Context
import com.example.picobotella.data.DBReto
import com.example.picobotella.data.DaoReto
import com.example.picobotella.model.Reto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RetoRepository(val context: Context) {
    var daoReto : DaoReto = DBReto.getDataBase(context).daoReto()

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

}