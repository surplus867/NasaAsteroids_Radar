package com.udacity.asteroidradar.repository

import android.net.Network
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.api.AsteroidApiService.AsteroidApi.retrofitService
import com.udacity.asteroidradar.database.AsteroidDatabase
import com.udacity.asteroidradar.database.asDomainModel
import com.udacity.asteroidradar.main.AsteroidsApiStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AsteroidRepository (private val database: AsteroidDatabase) {


/*
    val asteroidsToday: LiveData<List<Asteroid>> = Transformations.map(database.asteroidDao.getOneDayAsteroids()) {
        it.asDomainModel()
    }

    val asteroidWeek: LiveData<List<Asteroid>> = Transformations.map(database.asteroidDao.getAsteroidWeek(
            getToDat
    ))
*/

}
