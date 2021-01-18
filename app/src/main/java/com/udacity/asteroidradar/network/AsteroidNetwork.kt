package com.udacity.asteroidradar.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class AsteroidNetwork private constructor() {
    private val networkService: AsteroidApiService

    init {
        /**
         * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
         * full Kotlin compatibility
         */
       val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

       val retrofit = Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(OkHttpClient())
                .baseUrl(Constants.BASE_URL)
                .build()

        networkService = retrofit.create<AsteroidApiService>(AsteroidApiService::class.java)
    }

    companion object {
        private var networkServiceClient: AsteroidNetwork? = null
        val serviceInstance: AsteroidApiService
            get() {
                if (networkServiceClient == null) {
                    networkServiceClient = AsteroidNetwork()
                }
                return networkServiceClient!!.networkService
            }
    }
}