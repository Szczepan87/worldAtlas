package com.example.worldatlas.repository

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
    private val countryDatabase: CountryDatabase,
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
            GlobalScope.launch { countries.body()?.forEach { countryDao.upsert(it) } }
        }
    }

    override suspend fun retrieveCountriesFromDatabase() {
        _allCountries.postValue(countryDatabase.getCountryDao().getAllCountries().value)
    }
}