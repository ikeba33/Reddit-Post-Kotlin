package com.example.redditposts.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.redditposts.repository.PostsRepository

class MyFactory(val application: Application, val postsRepository: PostsRepository): ViewModelProvider.AndroidViewModelFactory(application) {

//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        return PostsViewModel(application, postsRepository) as T
//    }
}