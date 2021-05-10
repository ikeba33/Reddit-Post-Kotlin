package com.example.redditposts.objects

import com.google.gson.annotations.SerializedName

data class Children(
    @SerializedName( "data")
    val post: Post
)