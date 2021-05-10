package com.example.redditposts.viewModel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.room.Room
import androidx.room.RoomDatabase
import butterknife.internal.Utils
import com.example.redditposts.R
import com.example.redditposts.objects.LoginRequest
import com.example.redditposts.objects.LoginResponse
import com.example.redditposts.objects.Post
import com.example.redditposts.objects.RootData
import com.example.redditposts.repository.PostsRepository
import com.example.redditposts.repository.api.QueriManager
import com.example.redditposts.repository.dataBase.DataBaseManager
import com.example.redditposts.repository.dataBase.PostDao
import com.example.redditposts.utils.convertRootToListOfPost
import kotlinx.coroutines.*
import kotlinx.coroutines.NonCancellable.children
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostsViewModel( application: Application) : AndroidViewModel(application) {
    var liveData = RootLifeData()
    var db = Room.databaseBuilder(application.baseContext, DataBaseManager::class.java,"post-database").build()

    private var prefs: SharedPreferences = application.getSharedPreferences(application.getString(R.string.app_name),
        Activity.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "user_token"
    }

    init {
        //todo check innetConnection

        val cm = application.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        if (isConnected)
//            if (fetchAuthToken()!= null)
                getPostsFromNetwork()
//            else
//                authorization()
        else
            getPostsFromDB()


        db = Room.databaseBuilder(application.baseContext, DataBaseManager::class.java,"post-database").build()
    }

    fun saveAuthToken(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    fun fetchAuthToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }


    private  fun addPostsToDB(list: List<Post>){
        GlobalScope.launch(Dispatchers.IO) {

        val postsRepository = PostsRepository(postDao = db.postDao())


            for (post in list){
//                val ae86 = fun add(){ repository.insert(children.post) }

                if (post != null)
                postsRepository.insert(post)
            }

        }

    }

    private fun getPostsFromDB(){



        GlobalScope.launch(Dispatchers.IO) {

            val postsRepository = PostsRepository(postDao = db.postDao())
            val data = GlobalScope.async(Dispatchers.Default) {
                postsRepository.allPosts
            }

            withContext(Dispatchers.Main){
                liveData.value = data.getCompleted()
            }

            }

    }

    private fun authorization(){
        val service = QueriManager.redditApiService
        service.login().enqueue(object : Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {

                Log.d("token onFailure", t.message.toString())
            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                response.body()?.let { saveAuthToken(it.authToken) }
                getPostsFromNetwork()

                Log.d("token onResponse", response.body().toString())


            }

        })
    }

    private fun getPostsFromNetwork(){
        val service = QueriManager.redditApiService
        service.getResponse().enqueue(object : Callback<RootData> {
            override fun onFailure(call: Call<RootData>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call<RootData>, response: Response<RootData>) {
                val rootData: RootData? = response.body()

                liveData.value = rootData?.let { convertRootToListOfPost(it) }

//                val post = Post("а",213216.2,124124.2,"asfa",3,"asf","","test","")
//                val list = mutableListOf<Post>(post)
               // liveData.value = list

                rootData?.let { addPostsToDB(convertRootToListOfPost(it)) }


                Log.d("aasfasfa", response.body().toString())


            }

        })
    }
}