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
    fun restoreObjectList(listOfObjects: List<*>): String {
        return listOfObjects.toJson()
    }

    @TypeConverter
    fun restoreCurrencyList(listOfCurrency: String?): List<Currency?>? {
        return listOfCurrency.fromJson()
    }

    @TypeConverter
    fun restoreStringsList(listOfString: String?): List<String?>? {
        return listOfString.fromJson()
    }

    @TypeConverter
    fun restoreLanguagesList(listOfLanguages: String?): List<Language?>? {
        return listOfLanguages.fromJson()
    }

    @TypeConverter
    fun restoreDoublesList(listOfDoubles: String?): List<Double?>? {
        return listOfDoubles.fromJson()
    }

    @TypeConverter
    fun restoreRegionalBlocksList(listOfRegionalBlocks: String?): List<RegionalBloc?>? {
        return listOfRegionalBlocks.fromJson()
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
    return Gson().toJson(this) ?: ""
}

fun <T> String?.fromJson(): List<T> {
    return try {
        Gson().fromJson(this, object : TypeToken<List<T>>() {}.type)
    } catch (e: ParseException) {
        Log.d("TYPE CONVERTER", "${e.message}")
        emptyList<T>()
    }
}
