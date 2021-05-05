package com.example.redditposts.utils

import android.content.Context
import android.widget.ImageView
import com.squareup.picasso.Picasso

    fun imgLoad(
        context: Context?,
        url: String?,
        imageView: ImageView?
    ) {
        Picasso.with(context)
            .load(url)
            .into(imageView)
    }
