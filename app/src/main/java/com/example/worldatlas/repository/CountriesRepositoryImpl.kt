package com.example.worldatlas.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.worldatlas.model.Country
import com.example.worldatlas.repository.database.CountryDataFromDatabaseProviderImpl
import com.example.worldatlas.repository.remote.CountryDataFromNetworkProviderImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CountriesRepositoryImpl(
    private val countryDataFromDatabaseProviderImpl: CountryDataFromDatabaseProviderImpl,
    private val countryDataFromNetworkProviderImpl: CountryDataFromNetworkProviderImpl
) : CountriesRepository {
    private val _allCountries = MutableLiveData<List<Country>>()
    override val allCountries: LiveData<List<Country>>
        get() = _allCountries

    override suspend fun updateCountriesDatabase() {
        countryDataFromNetworkProviderImpl.retrieveCountryDataFromNetwork()
        countryDataFromNetworkProviderImpl.countriesFromNetwork.observeForever(Observer {
            GlobalScope.launch(Dispatchers.IO) {
                countryDataFromDatabaseProviderImpl.updateAndNotifyDatabase(it)
            }
        })
    }

    override suspend fun retrieveCountriesFromDatabase() {
        countryDataFromDatabaseProviderImpl.countriesFromDatabase.observeForever(
            Observer { _allCountries.postValue(it) })
    }

}