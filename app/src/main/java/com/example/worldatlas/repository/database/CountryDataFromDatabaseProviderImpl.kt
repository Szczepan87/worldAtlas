package com.example.worldatlas.repository.database

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.worldatlas.model.Country
import com.example.worldatlas.utils.lazyDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CountryDataFromDatabaseProviderImpl(database: CountryDatabase) :
    CountryDataFromDatabaseProvider {
    private val countryDao = database.getCountryDao()
    private val _countriesFromDatabase = MutableLiveData<List<Country>>()
    override val countriesFromDatabase: LiveData<List<Country>>
        get() = _countriesFromDatabase
    private val _countriesFromDatabaseByContinent = MutableLiveData<List<Country>>()
    override val countriesFromDatabaseByContinent: LiveData<List<Country>>
        get() = _countriesFromDatabaseByContinent

    override suspend fun updateAndNotifyDatabase(countries: List<Country>) {
        countries.forEach {
            countryDao.upsert(it)
            Log.d("DATABASE PROVIDER", "INSERTING ${it.name} TO DATABASE")
        }
        val databaseContent by lazyDeferred { countryDao.getAllCountries() }
        _countriesFromDatabase.postValue(databaseContent.await())
        Log.d("DATABASE PROVIDER", "MUTABLE LIVE DATA UPDATED FROM UPDATE METHOD")
    }

    override suspend fun retrieveCountriesFromDatabase() {
        val databaseContent by lazyDeferred { countryDao.getAllCountries() }
        _countriesFromDatabase.postValue(databaseContent.await())
        Log.d("DATABASE PROVIDER", "MUTABLE LIVE DATA UPDATED FROM RETRIEVE METHOD")
    }

    override suspend fun countriesFromDatabaseByContinent(continentName: String) {
        val countriesByContinent by lazyDeferred { countryDao.getAllCountriesFromContinent(continentName) }
        _countriesFromDatabaseByContinent.postValue(countriesByContinent.await())
        Log.d("DATABASE PROVIDER", "MUTABLE LIVE DATA UPDATED FROM RETRIEVE BY CONTINENT METHOD")
    }
}