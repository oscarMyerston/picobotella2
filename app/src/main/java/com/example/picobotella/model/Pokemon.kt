package com.example.picobotella.model

import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("")
    val id : Long,
    @SerializedName("img")
    val img : String
)
