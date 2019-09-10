package com.redbox.anchan.home

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.redbox.anchan.network.ApiService
import com.redbox.anchan.network.NetworkModule
import com.redbox.anchan.network.pojo.Board
import com.redbox.anchan.network.pojo.BoardList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class HomeViewModel() : ViewModel() {

    val boardList = MutableLiveData<List<Board>>()

    fun getBoards() {
        NetworkModule.retrofit.create(ApiService::class.java)
            .getBoards()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<BoardList>() {
                override fun onSuccess(t: BoardList) {
                 boardList.postValue(t.boards)
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }
            })
    }

    fun observeBoards(
        lifecycleOwner: LifecycleOwner,
        callback: (List<Board>) -> Unit
    ) = boardList.observe(lifecycleOwner, Observer(callback))

}