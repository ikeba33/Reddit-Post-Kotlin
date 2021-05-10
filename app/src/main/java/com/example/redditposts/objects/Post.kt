package com.example.redditposts.objects

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey


@Entity

data class Post(

    val author: String,
    @PrimaryKey val created: Double,
    val created_utc: Double,
    val name: String,
    val num_comments: Int,
    val subreddit: String,
    val thumbnail: String,
    val title: String,
    val url: String

)