package com.learning.roomdbSample

import android.app.Application
import com.learning.roomdbSample.api.MoviesService
import com.learning.roomdbSample.api.RetrofitHelper
import com.learning.roomdbSample.db.MoviesDatabase
import com.learning.roomdbSample.repository.MoviesRepository

class MoviesApplication : Application() {

    lateinit var moviesRepository: MoviesRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val movieService = RetrofitHelper.getInstance().create(MoviesService::class.java)
        val database = MoviesDatabase.getDatabase(applicationContext)
        moviesRepository = MoviesRepository(movieService, database, applicationContext)
    }
}