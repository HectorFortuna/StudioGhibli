package com.hectorfortuna.studioghibli.data.network

import com.hectorfortuna.studioghibli.model.FilmsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {
    @GET("/films")
    suspend fun getFilms(
        @Query("fields") fields: String,
        @Query("limit") limit: Int
    ): List<FilmsResponse>
}