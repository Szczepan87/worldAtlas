package com.example.worldatlas.di

import android.content.Context
import androidx.room.Room
import com.example.worldatlas.repository.CountriesRepositoryImpl
import com.example.worldatlas.repository.database.CountryDatabase
import com.example.worldatlas.repository.remote.CountriesApiService
import com.example.worldatlas.repository.remote.NoInternetConnectionInterceptor
import com.example.worldatlas.ui.WorldAtlasViewModel
import com.example.worldatlas.utils.COUNTRIES_BASE_URL
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
    viewModel { WorldAtlasViewModel(get()) }
}

val repositoryModule = module {
    single { provideRetrofit(androidContext()) }
    single { provideDatabase(androidContext()) }
    single { CountriesRepositoryImpl(get(), get()) }
}

private fun provideRetrofit(context: Context) = Retrofit.Builder()
    .baseUrl(COUNTRIES_BASE_URL)
    .client(provideOkHttpClient(context))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(CountriesApiService::class.java)

private fun provideDatabase(context: Context) =
    Room.databaseBuilder(context, CountryDatabase::class.java, "countries.db")
        .fallbackToDestructiveMigration()
        .build()

private fun provideOkHttpClient(context: Context) = OkHttpClient.Builder()
    .addInterceptor(NoInternetConnectionInterceptor(context))
    .build()