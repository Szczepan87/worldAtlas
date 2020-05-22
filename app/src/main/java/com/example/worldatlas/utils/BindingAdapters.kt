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

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun ImageView.imageUrl(imageUrl: String?) {

        val requestBuilder =
            GlideApp.with(this.context)
                .`as`(PictureDrawable::class.java)
                .error(R.drawable.ic_error_red_200dp)
                .override(200, 100)
                .centerInside()
                .listener(SvgSoftwareLayerSetter())

        val uri = Uri.parse(imageUrl)

        requestBuilder.load(uri).into(this)
    }

    @JvmStatic
    @BindingAdapter("listOfElements")
    fun TextView.listOfElements(listOfElements: List<*>) {
        val textToDisplay = StringBuilder()
        listOfElements.forEach {
            when (it) {
                is String -> textToDisplay.append("$it\n")
                is Currency -> textToDisplay.append("${it.name}\n")
                is Language -> textToDisplay.append("${it.name}\n")
            }
        }
        text = textToDisplay.toString()
    }
}