package com.example.worldatlas.ui

import android.util.Log
import androidx.lifecycle.*
import com.example.worldatlas.model.Country
import com.example.worldatlas.repository.CountriesRepository
import com.example.worldatlas.repository.CountriesRepositoryImpl
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.Exception

class WorldAtlasViewModel(private val countriesRepository: CountriesRepositoryImpl) : ViewModel() {

    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>>
        get() = _countries
    private val _exception = MutableLiveData<Exception>(null)
    val exception: LiveData<Exception>
        get() = _exception

    private val countriesObserver = Observer<List<Country>> {
        _countries.postValue(it)
        Log.d("UPDATING VM COUNTRIES OF", "${it.first()}")
    }

    fun updateCountriesList() {
        viewModelScope.launch { countriesRepository.retrieveCountriesFromDatabase()
        Log.d("VIEW MODEL COUNTRIES DATA FIRST ENTRY", "{${countriesRepository.allCountries.value?.first()}}")}
        countriesRepository.allCountries.observeForever { countriesObserver }
    }

    fun updateCountriesDatabase() {
        viewModelScope.launch {
            try {
                countriesRepository.updateCountriesDatabase()
                _exception.postValue(null)
            } catch (e: IOException) {
                _exception.postValue(e)
            }
        }
    }
}