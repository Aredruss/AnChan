package com.redbox.anchan.post

import androidx.lifecycle.MutableLiveData
import com.redbox.anchan.network.pojo.Post
import com.redbox.anchan.util.cleanHtml

class PostViewModel {

    private val number = MutableLiveData<Int>()
    private val comment = MutableLiveData<String>()
    private val date = MutableLiveData<String>()
    private val picPath = MutableLiveData<String>()
    private val tim = MutableLiveData<Long>()
    private val ext = MutableLiveData<String>()
    private val path = MutableLiveData<String>()

    fun bind(post: Post) {
        number.value = post.no
        comment.value = cleanHtml(post.com)
        date.value = post.now
        picPath.value = post.filename
        tim.value = post.tim
        ext.value = post.ext
        path.value = tim.value.toString() + ext.value
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

    fun getPicturePath(): String?
    {
        return "https://i.4cdn.org/co/${this.path.value}"
    }

}