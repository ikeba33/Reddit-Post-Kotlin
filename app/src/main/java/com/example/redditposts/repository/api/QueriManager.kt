package com.example.redditposts.repository.api

import com.example.redditposts.objects.Constants


object QueriManager {

    val apiClient: ApiClient? = ApiClient()
    val redditApiService: RedditApiService
        get() = apiClient!!.getClient(Constants.BASE_URL).create(RedditApiService::class.java) //apiclient create service
    //   get() { return apiClient!!.getClient(BASE_URL).create(RedditApiService::class.java) }

    //hP4KRZlgHeUkcA






}
    // TODO: 23.04.2021 ostorozno: copy paste:




