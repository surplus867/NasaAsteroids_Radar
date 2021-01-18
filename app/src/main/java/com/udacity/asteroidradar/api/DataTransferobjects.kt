package com.udacity.asteroidradar.api

import com.squareup.moshi.JsonClass
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.database.DatabaseAsteroid

/**
 * DataTransferObjects fo in this file. These are responsible for parsing responses from the server
 * or formatting objects to send to the server. You should convert these to domain objects before
 * using them.
 */


@JsonClass(generateAdapter = true)
data class NetworkAsteroidContainer(val asteroids: ArrayList<NetworkAsteroid>)

@JsonClass(generateAdapter = true)
data class NetworkAsteroid(
        val id: Long,
        val codename: String,
        val closeApproachData: String,
        val absoluteMagnitude: Double,
        val estimateDiameter: Double,
        val relativeVelocity: Double,
        val distanceFromEarth: Double,
        val isPotentiallyHazardous: Boolean)

/**
 * Convert Network results to database objects
 */

fun NetworkAsteroidContainer.asDomainModel(): List<Asteroid> {
    return asteroids.map {
        Asteroid(
                id = it.id,
                codename = it.codename,
                closeApproachDate = it.closeApproachData,
                absoluteMagnitude = it.absoluteMagnitude,
                estimatedDiameter = it.estimateDiameter,
                relativeVelocity = it.relativeVelocity,
                distanceFromEarth = it.distanceFromEarth,
                isPotentiallyHazardous = it.isPotentiallyHazardous)

    }
}

//fun NetworksteroidsContainer.asDatabaseModel(): Array<DatabaseAsteroid> {
//    return asteroids.map{
//        DatabaseAsteroid(
//        id = it.id,
//        codename = it.codename,
//        closeApproachDate = it.closeApproachDate,
//        absoluteMagitude = it.absoluteMagitude,
//        estimateDiamter = it.estimateDiameter,
//        relativeVelocity = it.relativeVelocity,
//        distanceFromEarth = it.distanceFromEarth,
//        isPotentallyHazardous = it.isPotentallyHazardous
//        )
//    }.toTypeArray()*/
//}