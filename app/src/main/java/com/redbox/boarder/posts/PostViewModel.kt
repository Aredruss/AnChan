package com.redbox.boarder.posts

import androidx.lifecycle.MutableLiveData
import com.redbox.boarder.network.pojo.Post

class PostViewModel {

    private val number = MutableLiveData<Int>()
    private val comment = MutableLiveData<String>()
    private val date = MutableLiveData<String>()

    fun bind(post: Post) {
        number.value = post.no
        comment.value = post.com
        date.value = post.now
    }

    fun getNumber(): Int? {
        return number.value
    }

    fun getComment(): String? {
        return comment.value
    }

    fun getDate(): String? {
        return date.value
    }


}