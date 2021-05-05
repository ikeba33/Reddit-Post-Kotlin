package com.example.redditposts.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.redditposts.MyAdapter
import com.example.redditposts.objects.Post
import com.example.redditposts.objects.RootData
import com.example.redditposts.repository.api.QueriManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostsViewModel(application: Application) : AndroidViewModel(application) {
    var liveData = RootLifeData()

    init {
        //todo check innetConnection
        getPosts()
        //or get data from db bd
    }

    private fun getPosts(){
        val service = QueriManager.redditApiService
        service.getResponse().enqueue(object : Callback<RootData> {
            override fun onFailure(call: Call<RootData>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call<RootData>, response: Response<RootData>) {
                val rootData: RootData? = response.body()
                liveData.value = rootData


                Log.d("aasfasfa", response.body().toString())


            }

        })
    }
}