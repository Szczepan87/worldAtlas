package com.example.worldatlas.repository.database

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.worldatlas.model.Country

class CountryDataFromDatabaseProviderImpl(database: CountryDatabase) : CountryDataFromDatabaseProvider {
    private val countryDao = database.getCountryDao()
    private val _countriesFromDatabase = MutableLiveData<List<Country>>()
    override val countriesFromDatabase: LiveData<List<Country>>
        get() = _countriesFromDatabase

    override suspend fun updateAndNotifyDatabase(countries: List<Country>) {
        countries.forEach { countryDao.upsert(it)
        Log.d("DATABASE PROVIDER", "INSERTING ${it.name} TO DATABASE")}
        _countriesFromDatabase.postValue(countryDao.getAllCountries().value)
        Log.d("DATABASE PROVIDER", "MUTABLE LIVE DATA UPDATED FROM UPDATE METHOD")
    }

    override suspend fun retrieveCountriesFromDatabase() {
        _countriesFromDatabase.postValue(countryDao.getAllCountries().value)
        Log.d("DATABASE PROVIDER", "MUTABLE LIVE DATA UPDATED FROM RETRIEVE METHOD")
    }
}