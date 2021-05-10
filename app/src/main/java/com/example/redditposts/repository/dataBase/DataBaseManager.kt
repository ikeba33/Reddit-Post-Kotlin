package com.example.redditposts.repository.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.redditposts.objects.Post


@Database(entities = arrayOf(Post::class), version = 1, exportSchema = false)
public abstract class DataBaseManager: RoomDatabase() {

    abstract fun postDao(): PostDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile//todo читать что это
        private var INSTANCE: DataBaseManager? = null

        fun getDatabase(context: Context): DataBaseManager {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataBaseManager::class.java,
                    "post-database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}