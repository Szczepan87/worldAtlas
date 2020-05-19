package com.example.worldatlas.utils

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.worldatlas.R
import java.net.URL

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun ImageView.imageUrl(imageUrl: String?) {
        val options = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.mipmap.ic_launcher_round)
        Glide.with(this.context)
            .setDefaultRequestOptions(options)
            .load(Uri.parse(imageUrl))
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .into(this)
    }
}