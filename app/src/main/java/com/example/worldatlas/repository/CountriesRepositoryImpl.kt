package com.example.worldatlas.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.worldatlas.model.Country
import com.example.worldatlas.repository.database.CountryDatabase
import com.example.worldatlas.repository.remote.CountriesApiService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import java.lang.Exception

class CountriesRepositoryImpl(
    countryDatabase: CountryDatabase,
    private val countriesApiService: CountriesApiService
) : CountriesRepository {
    private val countryDao = countryDatabase.getCountryDao()
    private val _allCountries = MutableLiveData<List<Country>>()
    override val allCountries: LiveData<List<Country>>
        get() = _allCountries

    override suspend fun updateCountriesDatabase() {
        val countries: Response<List<Country>> =
            countriesApiService.getAllCountries().await()
        if (countries.isSuccessful) {
            GlobalScope.launch { countries.body()?.forEach { countryDao.upsert(it)
            Log.d("REPOSITORY UPDATING DB WITH", "${it.name}")} }
        }
    }

    override suspend fun retrieveCountriesFromDatabase() {
        _allCountries.postValue(countryDao.getAllCountries().value)
        Log.d("REPOSITORY RETRIEVING FROM DB", "${countryDao.getAllCountries().value?.first()}")
    }
}