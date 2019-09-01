package com.redbox.boarder.catalog.page


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.redbox.boarder.network.ApiService
import com.redbox.boarder.network.NetworkModule
import com.redbox.boarder.network.pojo.Page
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PageViewModel : ViewModel() {

    var networkModule = NetworkModule.retrofit
    lateinit var threads: MutableLiveData<Page>


    //TODO Observe threads variable from the fragment & implement the proper recycler view

    fun loadPage(board: String, page: Int = 1) {
        NetworkModule.retrofit.create(ApiService::class.java)
            .getPage(board, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                threads.value = result

            }, { e ->
                print(e.stackTrace)
            })


    }
}
