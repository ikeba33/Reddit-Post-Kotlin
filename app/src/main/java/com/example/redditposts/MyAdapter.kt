package com.example.redditposts

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.redditposts.utils.dateConverter
import com.example.redditposts.objects.Children
import com.example.redditposts.objects.Data
import com.example.redditposts.objects.Post
import com.example.redditposts.objects.RootData
import com.example.redditposts.utils.imgLoad
import java.util.*

class MyAdapter(var rootData : RootData, val context: Context): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){



        var user: TextView? = null
        var subreddit: TextView? = null
        var date: TextView? = null
        var rating: TextView? = null
        var comments: TextView? = null
        var topic: TextView? = null
        var thumbnail: ImageButton? = null
        var title: TextView? = null
        var button: Button? = null

        init {
            user = itemView.findViewById(R.id.user)
            subreddit = itemView.findViewById(R.id.subreddit)
            date = itemView.findViewById(R.id.date)
            rating = itemView.findViewById(R.id.rating)
            comments = itemView.findViewById(R.id.comments)
            topic = itemView.findViewById(R.id.topic)
            thumbnail = itemView.findViewById(R.id.thumbnail)
            title = itemView.findViewById(R.id.title)
            button = itemView.findViewById(R.id.btn)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.rv_item,parent,false)
        return MyViewHolder(itemView)
    }

    fun setNewPosts(rootData: RootData){
        var oldSize = getItemCount()
        this.rootData.data.children.addAll(rootData.data.children)
        notifyItemRangeChanged(oldSize, getItemCount())
    }

    override fun getItemCount(): Int {
        return rootData.data.children.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var post: Children? = rootData.data.children.get(position)
        holder.user?.setText(post?.data?.author)
        holder.subreddit?.setText(post?.data?.subreddit)
        holder.comments?.setText(post?.data?.num_comments.toString())
        holder.date?.setText(
            dateConverter(post?.data?.created!!)
                .toString()) // TODO: 27.04.2021   change !! to no null
        holder.title?.setText(post?.data?.title)
        if (post?.data?.thumbnail != "")
            imgLoad(context,post?.data?.thumbnail,holder.thumbnail )     //holder.thumbnail?.setImageResource(rootData?.data?.children?.get(position)?.data?.thumbnail)

        holder.button?.setOnClickListener(View.OnClickListener {
            val builder = CustomTabsIntent.Builder()
            builder.setToolbarColor(
                ContextCompat.getColor(context, R.color.browser_actions_bg_grey))
            builder.addDefaultShareMenuItem()

            val anotherCustom = CustomTabsIntent.Builder().build()

            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(context, Uri.parse(post?.data?.url))

        })
    }

}