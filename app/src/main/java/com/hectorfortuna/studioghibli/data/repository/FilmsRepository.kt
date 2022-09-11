package com.hectorfortuna.studioghibli.data.repository

import com.hectorfortuna.studioghibli.model.FilmsResponse

interface FilmsRepository {
    suspend fun getFilms(fields: String, limit: Int): List<FilmsResponse>
}