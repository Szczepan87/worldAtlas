package com.example.worldatlas.repository.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.worldatlas.model.Country

@Dao
interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(country: Country)

    @Query("SELECT * FROM country ORDER BY name")
    fun getAllCountries(): List<Country>

    @Query("SELECT * FROM country WHERE region = :continentName")
    fun getAllCountriesFromContinent(continentName: String): LiveData<List<Country>>
}