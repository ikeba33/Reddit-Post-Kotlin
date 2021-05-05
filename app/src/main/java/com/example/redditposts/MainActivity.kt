package com.example.redditposts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.redditposts.objects.Children
import com.example.redditposts.objects.Data
import com.example.redditposts.objects.RootData
import com.example.redditposts.viewModel.PostsViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() ,Observer<RootData>{

    lateinit var postsViewModel: PostsViewModel
    lateinit var adapter:MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.layoutManager = LinearLayoutManager(this)
        postsViewModel = ViewModelProvider(this).get(PostsViewModel::class.java)

        adapter = MyAdapter(RootData(Data("", mutableListOf<Children>())), this)
        recyclerView.adapter = adapter
    }

    override fun onChanged(it: RootData?) {
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



