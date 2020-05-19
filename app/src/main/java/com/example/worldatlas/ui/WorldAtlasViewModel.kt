package com.example.worldatlas.ui

import androidx.lifecycle.*
import com.example.worldatlas.model.Country
import com.example.worldatlas.repository.CountriesRepositoryImpl
import com.example.worldatlas.utils.lazyDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.Exception

class WorldAtlasViewModel(private val countriesRepository: CountriesRepositoryImpl) : ViewModel() {

    val countries by lazyDeferred { countriesRepository.allCountries }
    val countriesByContinent by lazyDeferred { countriesRepository.countriesByContinent }
    private val _exception = MutableLiveData<Exception>(null)
    val exception: LiveData<Exception>
        get() = _exception

    fun getCountriesByContinent(continentName: String) {
        viewModelScope.launch {
            // get list by continent
        }
        // call repository and update by continent ok
        // observe live data changes in Fragment
    }
}