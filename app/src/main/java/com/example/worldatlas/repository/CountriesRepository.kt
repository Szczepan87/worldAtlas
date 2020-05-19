package com.example.worldatlas.repository

import androidx.lifecycle.LiveData
import com.example.worldatlas.model.Country
import java.lang.Exception

interface CountriesRepository {

    val allCountries: LiveData<List<Country>>

    val countriesByContinent: LiveData<List<Country>>

}