package com.example.redditposts.repository.dataBase

import com.example.redditposts.objects.Children

interface CallBackReadPost {
    fun setPost(list: List<Children?>?)

}