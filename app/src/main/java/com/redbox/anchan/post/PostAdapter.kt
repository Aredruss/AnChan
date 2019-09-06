package com.redbox.anchan.post

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.redbox.anchan.R
import com.redbox.anchan.network.pojo.Post
import kotlinx.android.synthetic.main.post_layout.view.*

class PostAdapter : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    lateinit var posts: List<Post>
    var viewModel: PostViewModel

    init {
        viewModel = PostViewModel()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.post_layout,
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
        holder.viewModel.bind(post)
        holder.bind()

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val viewModel = PostViewModel()
        private val numTv = itemView.post_num_tv
        private val comTv = itemView.post_reply_tv
        private val dateTv = itemView.post_time_tv
        private val postIv = itemView.post_img_iv

        fun bind() {
            numTv.text = viewModel.getNumber()
            dateTv.text = viewModel.getDate()
            comTv.text = viewModel.getComment()
            Glide.with(itemView.context)
                .load(viewModel.getPicturePath())
                .centerCrop()
                .into(postIv)
        }
    }
}