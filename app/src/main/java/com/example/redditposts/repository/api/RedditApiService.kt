package com.example.redditposts.repository.api

import com.example.redditposts.objects.Constants
import com.example.redditposts.objects.LoginRequest
import com.example.redditposts.objects.LoginResponse
import com.example.redditposts.objects.RootData
import retrofit2.Call
import retrofit2.http.*

interface RedditApiService {


    @GET("/r/TellMeAFact/top/.json?count=5")
    fun getResponse() : Call<RootData>


    @GET("/r/redditdev/top.json?limit=10")
    fun getResponse(@Query("before") after: String): Call<RootData>

    @POST(Constants.LOGIN_URL)
    @Headers("grant_type:authorization_code," +
            "code:" + Constants.SACRED_CODE+","+
            "redirect_uri:"+Constants.APP_URI)
    fun login(): Call<LoginResponse>
}