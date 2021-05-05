package com.example.redditposts.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.redditposts.objects.Post
import com.example.redditposts.repository.PostsRepository
import kotlinx.coroutines.launch


class PostViewModel(private val repository: PostsRepository) : ViewModel() {


        // val allPosts: LiveData<List<Post>> = repository.allPosts.asLiveData()


        fun insert(post: Post)= viewModelScope.launch {
            repository.insert(post)
        }
    }

    class WordViewModelFactory(private val repository: PostsRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PostViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return PostViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

