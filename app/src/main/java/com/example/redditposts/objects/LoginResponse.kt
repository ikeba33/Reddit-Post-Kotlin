package com.example.redditposts.objects

import com.google.gson.annotations.SerializedName

data class LoginResponse(@SerializedName("access_token")
                    var authToken: String,
                         var token_type:String,
                         var refresh_token:String
)
