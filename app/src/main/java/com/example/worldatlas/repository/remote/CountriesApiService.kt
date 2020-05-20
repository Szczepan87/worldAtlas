package com.example.worldatlas.repository.remote

import com.example.worldatlas.model.Country
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface CountriesApiService {

    @GET("/rest/v2/all")
    fun getAllCountries(): Call<List<Country>>
}