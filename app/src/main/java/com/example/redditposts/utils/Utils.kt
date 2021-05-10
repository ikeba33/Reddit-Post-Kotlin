package com.example.redditposts.utils

import com.example.redditposts.objects.Post
import com.example.redditposts.objects.RootData
import java.util.*

fun dateConverter(long: Double){
    var timePost = Date(long.toLong()).time;
    var timeNow = Date().time;
    var keeper = timeNow - timePost;

   keeper = keeper/1000/60/60
    println("Time ago: $keeper")

}

fun convertRootToListOfPost(rootData: RootData): List<Post>{
    var listOfPosts = mutableListOf<Post>()
    for (post in rootData.data.children){
        listOfPosts.add(post.post)
    }
    return listOfPosts
}