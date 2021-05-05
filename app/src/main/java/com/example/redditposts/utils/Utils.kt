package com.example.redditposts.utils

import java.util.*

fun dateConverter(long: Double){
    var timePost = Date(long.toLong()).time;
    var timeNow = Date().time;
    var keeper = timeNow - timePost;

   keeper = keeper/1000/60/60
    println("Time ago: $keeper")

}