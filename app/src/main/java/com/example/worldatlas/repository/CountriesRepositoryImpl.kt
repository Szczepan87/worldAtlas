package com.example.worldatlas.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.worldatlas.model.Country
import com.example.worldatlas.repository.database.CountryDatabase
import com.example.worldatlas.repository.remote.CountriesApiService
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import java.io.IOException

class CountriesRepositoryImpl(
    private val countriesApiService: CountriesApiService,
    countriesDatabase: CountryDatabase
) : CountriesRepository {
    private val _allCountries = MutableLiveData<List<Country>>()
    override val allCountries: LiveData<List<Country>>
        get() = _allCountries
    private val _countriesByContinent = MutableLiveData<List<Country>>()
    override val countriesByContinent: LiveData<List<Country>>
        get() = _countriesByContinent
    private val countriesDao = countriesDatabase.getCountryDao()

    init {
        CoroutineScope(IO).launch {
            val isDatabaseEmpty =
                withContext(IO) { getCountriesListDatabase().isEmpty() }
            if (isDatabaseEmpty){
                Log.d("REPOSITORY", "DATABASE IS EMPTY!!!")
                val remoteList = getCountriesListFromApi()
                saveCountriesToDatabase(remoteList)
                Log.d("REPOSITORY", "UPDATING DATABASE WITH: ${remoteList.first().name}")
            }
            val listFromDatabase:List<Country> =
                withContext(IO) { getCountriesListDatabase()
                }
            _allCountries.postValue(listFromDatabase)
            Log.d("REPOSITORY", "RETRIEVING DATA FROM DATABASE")
            Log.d("REPOSITORY", "FIRST ENTRY IN DATABASE IS ${listFromDatabase.first()?.name}")
        }
        Log.d("REPOSITORY", "INIT BLOC CALLED")
    }

    private fun getCountriesListFromApi(): List<Country> {
        Log.d("REPOSITORY", "getCountriesListFromApi() CALLED")
        val response = countriesApiService.getAllCountries().execute()
        return if (response.isSuccessful) response.body()!! else throw IOException()
    }

    private fun saveCountriesToDatabase(countriesList: List<Country>) {
        Log.d("REPOSITORY", "saveCountriesToDatabase() CALLED")
        countriesList.forEach { countriesDao.upsert(it)
            Log.d("REPOSITORY", "SAVING: ${it.name} TO DATABASE")}
    }

    private suspend fun getCountriesListDatabase(): List<Country> =
        withContext(IO) {
            Log.d("REPOSITORY", "getCountriesListDatabase() CALLED")
            countriesDao.getAllCountries()
        }
}