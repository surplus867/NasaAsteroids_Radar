package com.udacity.asteroidradar.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.asteroidradar.Asteroid

/**
 * * The [ViewModel] that is attached to the [DetailFragment].
 */

class DetailViewModel(asteroidData: Asteroid, app: Application) : AndroidViewModel(app) {

    private val _selectedAsteroid = MutableLiveData<Asteroid>()

    //The external LiveData for the SelectedAsteroid
    val selectedAsteroid: LiveData<Asteroid>
    get() = _selectedAsteroid


    init{
         _selectedAsteroid
        _selectedAsteroid.value = asteroidData
    }
}
