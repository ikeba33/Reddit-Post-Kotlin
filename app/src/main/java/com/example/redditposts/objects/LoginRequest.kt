package com.example.redditposts.objects

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("client_id")
    var clientId: String = Constants.CLIENT_ID,

    @SerializedName("SECRET_TOKEN")
     var token: String = Constants.SACRED_CODE,


    var redirect_uri:String = Constants.APP_URI
 )

