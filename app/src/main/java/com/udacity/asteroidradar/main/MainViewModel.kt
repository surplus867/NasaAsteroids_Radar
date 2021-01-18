package com.udacity.asteroidradar.main

import android.app.Application
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.*
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.api.AsteroidApiService
import com.udacity.asteroidradar.database.getDatabase


enum class AsteroidsApiStatus { LOADING, ERROR, DONE }
enum class ApiFilter { TODAY, WEEK, SAVED }

class MainViewModel(application: Application) : AndroidViewModel(application) {

    // The internal MutableLiveData String that stores the status of the most recent request
    private val _status = MutableLiveData<AsteroidsApiStatus>()

    // The external immutable LiveData for the request status String
    val status: LiveData<AsteroidsApiStatus>
    get() = _status

    // Image of the day
    private val _dailyPicture = MutableLiveData<PictureOfDay>()
    val dailyPicture: LiveData<PictureOfDay>
    get() = _dailyPicture


    // Internally, we use a MutableLiveData to handle navigation to the selected property
    private val _navigateToSelectedProperty = MutableLiveData<Asteroid>()

    // The external immutable LiveData for the navigation property
    val navigateToSelectedProperty: LiveData<Asteroid>
    get() = _navigateToSelectedProperty

    // set filter for asteroid request
    private val apiFilter = MutableLiveData<ApiFilter>(ApiFilter.SAVED)

    //set database
    private val database = getDatabase(application)

    val asteroidsList = ObservableArrayList<Asteroid>()
    val api = AsteroidApiService.AsteroidApi


//    init {
//        viewModelScope.launch {
//            _status.value = AsteroidsApiStatus.LOADING
//            try {
//
//            }
//
//        }
//    }


    fun displayPropertyDetails(asteroid: Asteroid) {
        _navigateToSelectedProperty.value = asteroid
    }

    fun call() {

    }


    fun showList() {
        asteroidsList.add(Asteroid(0L, "Bob", "2021-01-17", 2.2, 2.4,
                2.6, 2.8, true))

        asteroidsList.add(Asteroid(0L, "Bob", "2021-01-17", 2.2, 2.4,
                2.6, 2.8, true))
    }
}