package com.example.picobotella.service

import com.example.picobotella.model.PokemonResponse
import com.example.picobotella.utils.Constants.END_POINT_POKEMONS
import retrofit2.http.GET

interface ApiService {
    @GET(END_POINT_POKEMONS)
    suspend fun getPokemon() : PokemonResponse

}