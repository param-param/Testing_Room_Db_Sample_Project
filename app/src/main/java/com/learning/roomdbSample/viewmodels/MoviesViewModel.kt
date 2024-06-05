package com.learning.roomdbSample.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learning.roomdbSample.models.Movie
import com.learning.roomdbSample.repository.MoviesRepository
import com.learning.roomdbSample.repository.ResponseType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesViewModel(private val repository: MoviesRepository) : ViewModel() {

    init {
        viewModelScope.launch {
            repository.getMovies()
        }
    }

    val movies: LiveData<ResponseType<List<Movie>>>
        get() = repository.movies

}