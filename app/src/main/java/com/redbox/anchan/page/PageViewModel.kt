package com.redbox.anchan.page


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.redbox.anchan.network.ApiService
import com.redbox.anchan.network.NetworkModule
import com.redbox.anchan.network.pojo.Page
import com.redbox.anchan.network.pojo.Post
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers


class PageViewModel : ViewModel() {
    var fetchedPage = MutableLiveData<Page>()
    var posts = MutableLiveData<List<Post>>()

    fun loadPage(board: String, page: Int = 1) {
        NetworkModule.retrofit.create(ApiService::class.java)
            .getPage(board, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableObserver<Page>() {
                override fun onComplete() {
                    Log.d("Complete", "Fetched results successfully")
                    val threads = fetchedPage.value!!.threads
                    var p = mutableListOf<Post>()
                    for (i in 0 until threads!!.size) {
                        p.add(threads[i].posts[0])
                    }
                    posts.value = p
                }

                override fun onNext(t: Page) {
                    fetchedPage.value = t
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }

            })
    }

    fun getPosts(): LiveData<List<Post>> {
        return posts
    }
}

//{ result ->
//    fetchedPage.value = result
//    Log.d("loading", fetchedPage.value.toString())
//    parsePage()
//}, { e ->
//    print(e.stackTrace)
//}