package com.example.worldatlas.utils

import android.util.Log
import androidx.room.TypeConverter
import com.example.worldatlas.model.Currency
import com.example.worldatlas.model.Language
import com.example.worldatlas.model.RegionalBloc
import com.example.worldatlas.model.Translations
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.text.ParseException

class CountryTypeConverters {

    @TypeConverter
    fun restoreCurrencyList(listOfCurrency: String?): List<Currency?>? {
        return listOfCurrency.fromJson()
    }

    @TypeConverter
    fun saveCurrencyList(listOfCurrency: List<Currency?>?): String? {
        return listOfCurrency.toJson()
    }

    @TypeConverter
    fun restoreStringsList(listOfString: String?): List<String?>? {
        return listOfString.fromJson()
    }

    @TypeConverter
    fun saveStringsList(listOfString: List<String?>?): String? {
        return listOfString.toJson()
    }

    @TypeConverter
    fun restoreLanguagesList(listOfLanguages: String?): List<Language?>? {
        return listOfLanguages.fromJson()
    }

    @TypeConverter
    fun saveLanguagesList(listOfLanguages: List<Language?>?): String? {
        return listOfLanguages.toJson()
    }

    @TypeConverter
    fun restoreDoublesList(listOfDoubles: String?): List<Double?>? {
        return listOfDoubles.fromJson()
    }

    @TypeConverter
    fun saveDoublesList(listOfDoubles: List<Double?>?): String? {
        return listOfDoubles.toJson()
    }

    @TypeConverter
    fun restoreRegionalBlocksList(listOfRegionalBlocks: String?): List<RegionalBloc?>? {
        return listOfRegionalBlocks.fromJson()
    }

    @TypeConverter
    fun saveRegionalBlocksList(listOfRegionalBlocks: List<RegionalBloc?>?): String? {
        return listOfRegionalBlocks.toJson()
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

fun <T> List<T>?.toJson(): String {
    return Gson().toJson(this)
}

fun <T> String?.fromJson(): List<T> {
    return try {
        Gson().fromJson(this, object : TypeToken<List<T>>() {}.type)
    } catch (e: ParseException) {
        Log.d("TYPE CONVERTER", "${e.message}")
        emptyList<T>()
    }
}
