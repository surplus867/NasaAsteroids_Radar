package com.udacity.asteroidradar.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

// Define n interface AsteroidDao annotated with @Dao
@Dao
interface AsteroidDao {
    // Add getAsteroids as a @Query that selects from databaseasteroid
    @Query("SELECT* FROM databaseasteroid ORDER BY closeApproachDate ASC")
    fun getAsteroids(): LiveData<List<DatabaseAsteroid>>
    // Add getOneDayAsteroids as a Query that selected from databaseasteroid
    @Query("SELECT* FROM databaseasteroid  WHERE closeApproachDate = :date")
    fun getOneDayAsteroids(date: String): LiveData<List<DatabaseAsteroid>>
    // Add getRangeAsteroids FROM databaseasteroid as a Query that selected from databaseasteroid
    @Query("SELECT* FROM databaseasteroid WHERE closeApproachDate BETWEEN :startDate AND :endDate ORDER BY closeApproachDate ASC")
    fun getRangeAsteroids(startDate: String, endDate: String): LiveData<List<DatabaseAsteroid>>
    // Add insertAll as an @Insert that replaces on conflict(or upset)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg asteroids: DatabaseAsteroid)
}
// Add AsteroidDatabase, an abstract class extending RoomDatabase
// Annotate AsteroidDatabase with @Database, including entities and version
@Database(entities = [DatabaseAsteroid::class], version = 1)
abstract class AsteroidDatabase : RoomDatabase() {
    // Create abstract val AsteroidDao
    abstract val AsteroidDao: AsteroidDao
}
// Create an INSTANCE variable to store the AsteroidDatabase singleton
private lateinit var INSTANCE: AsteroidDatabase

// Define a function getDatabase() that returns the AsteroidDatabase INSTANCE
fun getDatabase(context: Context): AsteroidDatabase {
    // Use a synchronized block to check whether INSTANCE is initialized, and if it isn't,
    // Use a DatabaseBuilder to create it
    synchronized(AsteroidDatabase::class.java) {
        if(!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                AsteroidDatabase::class.java,
                "asteroid").build()
        }
        return INSTANCE
    }
}