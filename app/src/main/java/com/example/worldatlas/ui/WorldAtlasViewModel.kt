package com.example.worldatlas.ui

import androidx.lifecycle.*
import com.example.worldatlas.model.Country
import com.example.worldatlas.repository.CountriesRepository
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.Exception

class WorldAtlasViewModel(private val countriesRepository: CountriesRepository) : ViewModel() {

    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>>
        get() = _countries
    private val _exception = MutableLiveData<Exception>(null)
    val exception: LiveData<Exception>
        get() = _exception

    private val countriesObserver = Observer<List<Country>> {
        _countries.postValue(it)
    }

    fun updateCountriesList() {
        viewModelScope.launch { countriesRepository.retrieveCountriesFromDatabase() }
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