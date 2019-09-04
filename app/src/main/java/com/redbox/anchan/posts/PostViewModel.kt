package com.redbox.anchan.posts

import androidx.lifecycle.MutableLiveData
import com.redbox.anchan.network.pojo.Post

class PostViewModel {

    private val number = MutableLiveData<Int>()
    private val comment = MutableLiveData<String>()
    private val date = MutableLiveData<String>()

    fun bind(post: Post) {
        number.value = post.no
        comment.value = post.com
        date.value = post.now
    }

    fun getNumber(): String? {
        return number.value.toString()
    }

    fun getComment(): String? {
        return comment.value
    }

    fun getDate(): String? {
        return date.value
    }

}