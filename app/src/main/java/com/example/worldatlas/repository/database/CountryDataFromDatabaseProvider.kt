package com.example.worldatlas.repository.database

import androidx.lifecycle.LiveData
import com.example.worldatlas.model.Country

interface CountryDataFromDatabaseProvider {

    val countriesFromDatabase: LiveData<List<Country>>

    suspend fun updateAndNotifyDatabase(vararg country: List<Country>)

    suspend fun retrieveCountriesFromDatabase()
}