package com.example.worldatlas.repository

import android.util.Log
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

    init {
        countryDataFromNetworkProviderImpl.countriesFromNetwork.observeForever(Observer {
            updateCountriesDatabase(it)
            Log.d("REPOSITORY NETWORK OBSERVER","TRIGGERED WITH LIST OF ${it.size} ELEMENTS")
        })
        countryDataFromDatabaseProviderImpl.countriesFromDatabase.observeForever(Observer {
            Log.d("REPOSITORY DATABASE OBSERVER","TRIGGERED WITH LIST OF ${it.size} ELEMENTS")
            _allCountries.postValue(it)
            // Log.d("REPOSITORY DATABASE OBSERVER","TRIGGERED WITH LIST OF ${it.size} ELEMENTS")
        })
        Log.d("REPOSITORY", "INIT BLOC CALLED")
    }

    private fun updateCountriesDatabase(listOfCountries: List<Country>) {
        GlobalScope.launch(Dispatchers.IO) {
            countryDataFromDatabaseProviderImpl.updateAndNotifyDatabase(listOfCountries)
            Log.d("REPOSITORY", "DATABASE UPDATED WITH ${listOfCountries.size} ELEMENTS")
        }
    }

    override suspend fun retrieveCountriesFromDatabase() {
//        countryDataFromDatabaseProviderImpl.countriesFromDatabase.observeForever(
//            Observer { _allCountries.postValue(it)
        Log.d("---------------REPOSITORY--------------------", "MUTABLE LIVE DATA UPDATED BY X ELEMENTS")
    }


    override suspend fun fetchCountriesInformation() {
        countryDataFromNetworkProviderImpl.retrieveCountryDataFromNetwork()
        Log.d("REPOSITORY", "CALLED RETRIEVAL FROM REMOTE DATA SOURCE ")
    }
}