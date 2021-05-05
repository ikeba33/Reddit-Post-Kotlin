package com.example.redditposts.repository.api


object QueriManager {
    private val BASE_URL = "https://www.reddit.com"
    val apiClient: ApiClient? = ApiClient()
    val redditApiService: RedditApiService
        get() = apiClient!!.getClient(BASE_URL).create(RedditApiService::class.java) //apiclient create service
    //   get() { return apiClient!!.getClient(BASE_URL).create(RedditApiService::class.java) }



}
    // TODO: 23.04.2021 ostorozno: copy paste:




