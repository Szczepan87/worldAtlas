package com.example.worldatlas.utils

import androidx.room.TypeConverter
import com.example.worldatlas.model.Currency
import com.example.worldatlas.model.Language
import com.example.worldatlas.model.RegionalBloc
import com.example.worldatlas.model.Translations
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CountryTypeConverters {

    @TypeConverter
    fun restoreCurrencyList(listOfCurrency: String?): List<Currency?>? {
        return Gson().fromJson(
            listOfCurrency,
            object :
                TypeToken<List<Currency?>?>() {}.type
        )
    }

    @TypeConverter
    fun saveCurrencyList(listOfCurrency: List<Currency?>?): String? {
        return Gson().toJson(listOfCurrency)
    }

    @TypeConverter
    fun restoreStringsList(listOfString: String?): List<String?>? {
        return Gson().fromJson(
            listOfString,
            object :
                TypeToken<List<String?>?>() {}.type
        )
    }

    @TypeConverter
    fun saveStringsList(listOfString: List<String?>?): String? {
        return Gson().toJson(listOfString)
    }

    @TypeConverter
    fun restoreLanguagesList(listOfLanguages: String?): List<Language?>? {
        return Gson().fromJson(
            listOfLanguages,
            object :
                TypeToken<List<Language?>?>() {}.type
        )
    }

    @TypeConverter
    fun saveLanguagesList(listOfLanguages: List<Language?>?): String? {
        return Gson().toJson(listOfLanguages)
    }

    @TypeConverter
    fun restoreDoublesList(listOfDoubles: String?): List<Double?>? {
        return Gson().fromJson(
            listOfDoubles,
            object :
                TypeToken<List<Double?>?>() {}.type
        )
    }

    @TypeConverter
    fun saveDoublesList(listOfDoubles: List<Double?>?): String? {
        return Gson().toJson(listOfDoubles)
    }

    @TypeConverter
    fun restoreRegionalBlocksList(listOfRegionalBlocks: String?): List<RegionalBloc?>? {
        return Gson().fromJson(
            listOfRegionalBlocks,
            object :
                TypeToken<List<RegionalBloc?>?>() {}.type
        )
    }

    @TypeConverter
    fun saveRegionalBlocksList(listOfRegionalBlocks: List<RegionalBloc?>?): String? {
        return Gson().toJson(listOfRegionalBlocks)
    }

    @TypeConverter
    fun restoreTranslationsList(translations: String?): Translations? {
        return Gson().fromJson(
            translations,
            object :
                TypeToken<Translations?>() {}.type
        )
    }

    @TypeConverter
    fun saveTranslationsList(translations: Translations?): String? {
        return Gson().toJson(translations)
    }
}