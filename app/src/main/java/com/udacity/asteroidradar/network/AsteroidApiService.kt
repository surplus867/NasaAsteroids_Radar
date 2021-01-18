package com.udacity.asteroidradar.network

import com.udacity.asteroidradar.PictureOfDay
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface AsteroidApiService {

    // grabs asteroids within 7 day date range
    @GET("neo/rest/v1/feed")
    suspend fun getFeeds(@Query("api_key") apiKey: String,
                         @Query("start_date") startDate: String,
                         @Query("end_date") endDate: String
    ): Response<String>

    @GET("planetary/apod")
    suspend fun getImageOfDay(@Query("api_key") apiKey: String): Response<PictureOfDay>
}