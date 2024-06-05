package com.learning.roomdbSample.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.learning.roomdbSample.getOrAwaitValue
import com.learning.roomdbSample.models.Movie
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MoviesDaoTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    lateinit var moviesDatabase: MoviesDatabase
    lateinit var moviesDao: MoviesDao

    @Before
    fun setUp(){
        moviesDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MoviesDatabase::class.java
        ).allowMainThreadQueries().build()
        moviesDao = moviesDatabase.getMoviesDao()
    }


    @Test
    fun insertMovie_expectedSingleMovie() = runBlocking{
        val movie = Movie("",1,"","", "First Movie Title")
        moviesDao.insertMovie(movie)

        val result = moviesDao.getMovies()

        Assert.assertEquals(1, result.size)
        Assert.assertEquals("First Movie Title", result[0].title)
    }

    @Test
    fun deleteMovie_expectedNoResults() = runBlocking {
        val movie = Movie("",1,"","", "First Movie Title")
        moviesDao.insertMovie(movie)

        moviesDao.deleteMovie()

        val result = moviesDao.getMovies()

        Assert.assertEquals(0, result.size)
    }


    @After
    fun tearDown(){
        moviesDatabase.close()
    }
}