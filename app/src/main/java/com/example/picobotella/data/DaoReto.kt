package com.example.picobotella.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.picobotella.model.Reto

@Dao
interface DaoReto {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun agregarReto(reto: Reto)

    @Query("SELECT * FROM Reto")
    suspend fun obtenerListaReto():MutableList<Reto>

    @Update
    suspend fun update(reto: Reto)

    @Delete
    suspend fun delete(reto: Reto)

    @Query("SELECT * FROM Reto WHERE id=:id")
    suspend fun obtenerReto(id: Int): Reto

}