package com.udacity.asteroidradar.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.Constants.BASE_URL
import com.udacity.asteroidradar.PictureOfDay
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.nasa.gov/"

/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

private val retrofit_scalar = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()


interface AsteroidApiService {


    // Please tell me know the correct way to get the Api call from the URL
    // Option A?
    @GET("neo/rest/v1/feed")
    suspend fun getFeeds(@Query("api_key") apiKey: String = "4EeE1qpGjVYOkv6Vkps4VQFN76cko2efmIP3UPxm",
                         @Query("start_date") startDate: String, @Query("end_date") endDate: String
    ): JSONObject

    // Option B? Do I need the AsteroidNetwork class as well?
    @GET("neo/rest/v1/feed")
    suspend fun getFeeds(
            @Query("start_date") startDate: String,
            @Query("api_key") apiKey: String
    ): String

    @GET("planetary/apod")
    suspend fun getImageOfDay(
        @Query("api_key") apiKey: String): PictureOfDay
}


    object AsteroidApi {
        val retrofitService: AsteroidApiService by lazy {
          retrofit.create(AsteroidApiService::class.java)
       }

}
