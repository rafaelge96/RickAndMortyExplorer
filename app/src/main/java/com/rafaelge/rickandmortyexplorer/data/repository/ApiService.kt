package com.rafaelge.rickandmortyexplorer.data.repository

import com.rafaelge.rickandmortyexplorer.data.model.CharacterResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("character")
    fun getCharacters(@Query("page") page: Int): Call<CharacterResponse>
}