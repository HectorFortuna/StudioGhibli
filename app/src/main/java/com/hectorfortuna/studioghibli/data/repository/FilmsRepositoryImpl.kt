package com.hectorfortuna.studioghibli.data.repository

import com.hectorfortuna.studioghibli.model.FilmsResponse
import com.hectorfortuna.studioghibli.data.network.Service
import javax.inject.Inject

class FilmsRepositoryImpl @Inject constructor(private val api: Service):FilmsRepository {
    override suspend fun getFilms(fields: String, limit: Int): List<FilmsResponse> =
        api.getFilms(fields,limit)

}