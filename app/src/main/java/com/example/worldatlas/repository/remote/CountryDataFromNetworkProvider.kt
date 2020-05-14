package com.example.worldatlas.repository.remote

import androidx.lifecycle.LiveData
import com.example.worldatlas.model.Country

interface CountryDataFromNetworkProvider {

    val countriesFromNetwork: LiveData<List<Country>>

    suspend fun retrieveCountryDataFromNetwork()
}