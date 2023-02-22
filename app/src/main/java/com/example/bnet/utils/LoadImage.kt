package com.example.bnet.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.bnet.R

interface LoadImage {

    fun load(context: Context, imageView: ImageView, url: String)

    class Base : LoadImage {
        override fun load(context: Context, imageView: ImageView, url: String) {
            Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .placeholder(R.drawable.holder)
                .error(R.drawable.holder)
                .into(imageView)
        }
    }
}