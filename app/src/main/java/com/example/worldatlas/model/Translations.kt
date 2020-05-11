package com.example.worldatlas.model


import com.google.gson.annotations.SerializedName

data class Translations(
    @SerializedName("br")
    val br: String,
    @SerializedName("de")
    val de: String,
    @SerializedName("es")
    val es: String,
    @SerializedName("fr")
    val fr: String,
    @SerializedName("it")
    val `it`: String,
    @SerializedName("ja")
    val ja: String,
    @SerializedName("pt")
    val pt: String
)