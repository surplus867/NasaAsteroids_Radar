package com.udacity.asteroidradar.main

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.*
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.database.getDatabase
import com.udacity.asteroidradar.network.AsteroidNetwork
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


enum class AsteroidsApiStatus { LOADING, ERROR, DONE }
enum class ApiFilter { TODAY, WEEK, SAVED }

class MainViewModel(application: Application) : AndroidViewModel(application), CoroutineScope by MainScope() {

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

    fun displayPropertyDetails(asteroid: Asteroid) {
        _navigateToSelectedProperty.value = asteroid
    }

    fun getFeed() {
        launch(Dispatchers.Main) {
            //_status.value = AsteroidsApiStatus.LOADING
            try {
                val response = AsteroidNetwork.serviceInstance.getFeeds(Constants.API_KEY,
                        "2020-01-29", "2020-01-30")
                if (response.isSuccessful && response.body() != null) {
                    // How to convert
                    //TODO convert response.body into a JSONObject - convert a string to a JSONObject
                        //val astroidListResponse = parseAsteroidsJsonResult(convertedStringIntoJSONObject)
                    Log.d("bilbo", "astroidListResponse: ${response.body().orEmpty()}")
                    // this forEach will add the items to the list
                    //astroidListResponse.forEach { asteroid ->
                    //    asteroidsList.add(asteroid)
                    //}

                } else {
                    Log.d("bilbo", "Error: ${response.body()}")
                }
            } catch (e: Exception) {
                Log.d("bilbo", "Exception: ${e.message}")
            }

        }
    }
}