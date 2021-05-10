package com.example.redditposts

import android.app.Activity
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.redditposts.objects.Children
import com.example.redditposts.objects.Data
import com.example.redditposts.objects.Post
import com.example.redditposts.objects.RootData
import com.example.redditposts.repository.PostsRepository
import com.example.redditposts.repository.dataBase.DataBaseManager
import com.example.redditposts.repository.dataBase.PostDao
import com.example.redditposts.viewModel.MyFactory
import com.example.redditposts.viewModel.PostsViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() ,Observer<List<Post>>{

    lateinit var postsViewModel: PostsViewModel
    lateinit var adapter:MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.layoutManager = LinearLayoutManager(this)
//        var db =Room.databaseBuilder(this, DataBaseManager::class.java,"post-database").build()
//
//        GlobalScope.launch(Dispatchers.IO) {
//
//
//            val postsRepository = PostsRepository(postDao = db.postDao())
//
//        }

        postsViewModel = ViewModelProvider(this)
            .get(PostsViewModel::class.java)
        adapter = MyAdapter(mutableListOf(), this)
        recyclerView.adapter = adapter
    }

    override fun onChanged(it: List<Post>?) {
           it?.let { it1 -> adapter.setNewPosts(it1) }//todo !!
    }

    override fun onStart() {
        super.onStart()
        postsViewModel.liveData.observe(this, this)
    }

    override fun onStop() {
        super.onStop()
        postsViewModel.liveData.removeObserver(this)
    }

}



