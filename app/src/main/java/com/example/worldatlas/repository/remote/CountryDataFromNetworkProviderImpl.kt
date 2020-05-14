package com.example.worldatlas.repository.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.worldatlas.model.Country

class CountryDataFromNetworkProviderImpl(private val countriesApiService: CountriesApiService) :
    CountryDataFromNetworkProvider {
    private val _countriesFromNetwork = MutableLiveData<List<Country>>()
    override val countriesFromNetwork: LiveData<List<Country>>
        get() = _countriesFromNetwork

    override suspend fun retrieveCountryDataFromNetwork() {
        val downloadedCountryData = countriesApiService.getAllCountries().await().body()
        _countriesFromNetwork.postValue(downloadedCountryData)
    }
}