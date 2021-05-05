package com.example.redditposts.repository.dataBase

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.redditposts.objects.Children
import com.example.redditposts.objects.Post

interface PostDao {
    @Insert
    fun insert(vararg post: Post?)

    // Удаление Person из бд
    @Delete
    fun delete(post: Children?)

    // Получение всех Person из бд
    @Query("SELECT * FROM post")
    fun readAll(): List<Post>
}