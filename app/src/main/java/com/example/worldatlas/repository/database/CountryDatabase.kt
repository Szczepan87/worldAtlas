package com.example.worldatlas.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.worldatlas.model.Country
import com.example.worldatlas.utils.CountryTypeConverters

@Database(entities = [Country::class], version = 1)
@TypeConverters(CountryTypeConverters::class)
abstract class CountryDatabase : RoomDatabase() {
    abstract fun getCountryDao(): CountryDao
}