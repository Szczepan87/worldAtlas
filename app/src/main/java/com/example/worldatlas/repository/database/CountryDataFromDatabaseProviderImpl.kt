package com.example.worldatlas.repository.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.worldatlas.model.Country

class CountryDataFromDatabaseProviderImpl(database: CountryDatabase) : CountryDataFromDatabaseProvider {
    private val countryDao = database.getCountryDao()
    private val _countriesFromDatabase = MutableLiveData<List<Country>>()
    override val countriesFromDatabase: LiveData<List<Country>>
        get() = _countriesFromDatabase

    override suspend fun updateAndNotifyDatabase(vararg country: List<Country>) {
        country.forEach { countryDao.upsert(it) }
        _countriesFromDatabase.postValue(countryDao.getAllCountries().value)
    }

    override suspend fun retrieveCountriesFromDatabase() {
        _countriesFromDatabase.postValue(countryDao.getAllCountries().value)
    }
}