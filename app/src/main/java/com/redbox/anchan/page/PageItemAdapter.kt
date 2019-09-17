package com.redbox.anchan.page

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.redbox.anchan.R
import com.redbox.anchan.network.pojo.Post
import kotlinx.android.synthetic.main.page_item_layout.view.*

class PageItemAdapter : RecyclerView.Adapter<PageItemAdapter.PageItemViewHolder>() {
    lateinit var posts: List<Post>
    lateinit var hostTemplateFragment: PageTemplateFragment

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageItemViewHolder {
        return PageItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.page_item_layout,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holderPageItem: PageItemViewHolder, position: Int) {
        val post = posts[position]
        holderPageItem.bind(post)

    }

    inner class PageItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        override fun onClick(v: View?) {
            hostTemplateFragment.openThread(hostTemplateFragment.board, posts[adapterPosition].number)
        }

        init {
            itemView.setOnClickListener(this)
        }

        private val numTv = itemView.page_item_num_tv
        private val dateTv = itemView.page_item_date_tv
        private val postIv = itemView.page_item_iv
        private val infoTv = itemView.page_iteminf_tv
        private val subTv = itemView.page_item_sub_tv

        fun bind(post : Post) {
            numTv.text = post.number.toString()
            dateTv.text = post.date
            infoTv.text = post.replies.toString() + "/" + post.images
            subTv.text = post.sub
            Glide.with(itemView.context)
                .load("https://i.4cdn.org/${hostTemplateFragment.board}/${post.tim}${post.ext}")
                .into(postIv)

        }
    }
}