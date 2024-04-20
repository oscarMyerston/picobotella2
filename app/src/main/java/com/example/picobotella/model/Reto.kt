package com.example.picobotella.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Reto(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var descripcionReto: String,
)