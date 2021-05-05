package com.example.redditposts.repository.api

import com.example.redditposts.objects.RootData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditApiService {

    @GET("r/redditdev/top.json?limit=5")
    fun getResponse() : Call<RootData>

    @GET("/r/redditdev/top.json?limit=5")
    fun getResponse(@Query("before") after: String): Call<RootData>
}