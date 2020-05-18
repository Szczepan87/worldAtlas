package com.example.worldatlas.repository.database

import androidx.lifecycle.LiveData
import com.example.worldatlas.model.Country

interface CountryDataFromDatabaseProvider {

    val countriesFromDatabase: LiveData<List<Country>>

    val countriesFromDatabaseByContinent: LiveData<List<Country>>

    suspend fun updateAndNotifyDatabase(countries: List<Country>)

    suspend fun retrieveCountriesFromDatabase()

    suspend fun countriesFromDatabaseByContinent(continentName: String)
}