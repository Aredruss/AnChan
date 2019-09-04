package com.redbox.anchan.page


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.redbox.anchan.network.ApiService
import com.redbox.anchan.network.NetworkModule
import com.redbox.anchan.network.pojo.Page
import com.redbox.anchan.network.pojo.Post
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class PageViewModel : ViewModel() {
    var fetchedPage = MutableLiveData<Page>()
    var posts = mutableListOf<Post>()


    //TODO Observe fetchedPage variable from the fragment & implement the proper recycler view

    fun loadPage(board: String, page: Int = 1) {
        NetworkModule.retrofit.create(ApiService::class.java)
            .getPage(board, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                fetchedPage.value = result
                parsePage()
            }, { e ->
                print(e.stackTrace)
            })
    }

    fun parsePage() {
        val threads = fetchedPage!!.value!!.threads
        for (i in 0..threads!!.size) {
            posts.add(threads[i].posts[i])
        }
        Log.d("AAAAAA", posts.toString())
    }
}
