package com.redbox.anchan.thread

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.redbox.anchan.network.ApiService
import com.redbox.anchan.network.NetworkModule
import com.redbox.anchan.network.pojo.Post
import com.redbox.anchan.network.pojo.PostList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers


class ThreadViewModel() : ViewModel() {

    var postList = MutableLiveData<List<Post>>()

    fun getThread(board: String, number: Int) {
        NetworkModule.retrofit.create(ApiService::class.java)
            .getThread(board, number)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<PostList>() {
                override fun onSuccess(t: PostList) {
                    postList.postValue(t.posts)
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }
            })

    }

    fun observeThread(
        lifecycleOwner: LifecycleOwner,
        callback: (List<Post>) -> Unit
    ) = postList.observe(lifecycleOwner, Observer(callback))
}

