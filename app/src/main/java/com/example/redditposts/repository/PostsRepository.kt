package com.example.redditposts.repository

import androidx.annotation.WorkerThread
import com.example.redditposts.objects.Post
import com.example.redditposts.repository.dataBase.PostDao
import java.util.concurrent.Flow

class PostsRepository(private val postDao: PostDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allPosts: List<Post> = postDao.readAll()


    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(post: Post) {
        postDao.insert(post)
    }
}