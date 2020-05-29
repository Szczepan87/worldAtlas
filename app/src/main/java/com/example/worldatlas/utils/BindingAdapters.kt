package com.example.worldatlas.utils

import android.graphics.drawable.PictureDrawable
import android.net.Uri
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.worldatlas.R
import com.example.worldatlas.model.Currency
import com.example.worldatlas.model.Language
import com.example.worldatlas.utils.svg.GlideApp
import com.example.worldatlas.utils.svg.SvgSoftwareLayerSetter
import com.google.gson.internal.LinkedTreeMap

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun ImageView.imageUrl(imageUrl: String?) {

        val requestBuilder =
            GlideApp.with(this.context)
                .`as`(PictureDrawable::class.java)
                .error(R.drawable.ic_error_red_200dp)
                .centerCrop()
                .listener(SvgSoftwareLayerSetter())

        val uri = Uri.parse(imageUrl)

        requestBuilder.load(uri).into(this)
    }

    @JvmStatic
    @BindingAdapter("listOfStrings")
    fun TextView.listOfStrings(listOfStrings: List<String>) {
        val textToDisplay = StringBuilder()
        listOfStrings.forEach {
            textToDisplay.append("$it\n")
        }
        text = textToDisplay.toString()
    }

    @JvmStatic
    @BindingAdapter("listOfCurrencies")
    fun TextView.listOfCurrencies(listOfCurrencies: List<Currency>) {
        val textToDisplay = StringBuilder()
        listOfCurrencies.forEach {
            textToDisplay.append("${it.name}\n")
        }
        text = textToDisplay.toString()
    }

    @JvmStatic
    @BindingAdapter("listOfLanguages")
    fun TextView.listOfLanguages(listOfLanguages: List<Language>) {
        val textToDisplay = StringBuilder()
        listOfLanguages.forEach {
            textToDisplay.append("${it.name}\n")
        }
        text = textToDisplay.toString()
    }
}