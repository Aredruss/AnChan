package com.redbox.anchan.thread

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.redbox.anchan.R
import com.redbox.anchan.network.pojo.Post
import com.redbox.anchan.util.cleanHtml
import kotlinx.android.synthetic.main.thread_item_layout.view.*

class ThreadAdapter : RecyclerView.Adapter<ThreadAdapter.ViewHolder>() {

    lateinit var posts: List<Post>
    lateinit var hostFragment: ThreadFragment

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.thread_item_layout,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = posts[position]
        holder.bind(post)

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val numTv = itemView.post_num_tv
        private val comTv = itemView.post_reply_tv
        private val dateTv = itemView.post_time_tv
        private val postIv = itemView.post_img_iv

        fun bind(post: Post) {
            numTv.text = post.no.toString()
            dateTv.text = post.now
            comTv.text = cleanHtml(post.com)

            if (!post.ext.isNullOrBlank()) {
                Glide.with(itemView.context)
                    .load("https://i.4cdn.org/${hostFragment.board}/${post.tim}${post.ext}")
                    .into(postIv)
            } else {
                postIv.visibility = View.GONE
            }

        }
    }
}