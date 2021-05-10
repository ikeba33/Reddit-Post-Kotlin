package com.example.redditposts.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.redditposts.objects.Post
import com.example.redditposts.objects.RootData

class RootLifeData: MutableLiveData<List<Post>>() {

    override fun onActive() {
        super.onActive()
        //connect DB
        //innet query
    }

    override fun onInactive() {
        super.onInactive()
        //disconnect DB
    }
}
