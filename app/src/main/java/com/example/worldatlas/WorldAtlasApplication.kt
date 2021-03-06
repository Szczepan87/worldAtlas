package com.example.worldatlas

import android.app.Application
import com.example.worldatlas.di.repositoryModule
import com.example.worldatlas.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WorldAtlasApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@WorldAtlasApplication)
            modules(viewModelModule, repositoryModule)
        }
    }
}