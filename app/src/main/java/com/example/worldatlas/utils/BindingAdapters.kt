package com.example.worldatlas.utils

import android.graphics.drawable.PictureDrawable
import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.example.worldatlas.R
import com.example.worldatlas.utils.svg.GlideApp
import com.example.worldatlas.utils.svg.SvgSoftwareLayerSetter

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun ImageView.imageUrl(imageUrl: String?) {

        val requestBuilder =
            GlideApp.with(this.context)
                .`as`(PictureDrawable::class.java)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.mipmap.ic_launcher_round)
                .transition(withCrossFade())
                .listener(SvgSoftwareLayerSetter())

        val uri = Uri.parse(imageUrl)

        requestBuilder.load(uri).into(this)
    }
}