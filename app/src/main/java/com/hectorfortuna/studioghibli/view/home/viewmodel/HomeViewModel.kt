package com.hectorfortuna.studioghibli.view.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hectorfortuna.studioghibli.core.State
import com.hectorfortuna.studioghibli.model.FilmsResponse
import com.hectorfortuna.studioghibli.data.repository.FilmsRepository
import com.hectorfortuna.studioghibli.di.modules.qualifiers.Io
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: FilmsRepository,
    @Io val ioDispatcher: CoroutineDispatcher
    ): ViewModel() {

    private val _response = MutableLiveData<State<List<FilmsResponse>>>()
    val response: LiveData<State<List<FilmsResponse>>>
        get() = _response

    fun getFilms(fields: String, limit: Int){
        viewModelScope.launch {
            try {
                _response.value = State.loading(false)
                val response = withContext(ioDispatcher){
                    repository.getFilms(fields,limit)
                }
                _response.value = State.success(response)
            } catch (t: Throwable){
                _response.value = State.loading(false)
                _response.value = State.error(t)
            }
        }
    }

}