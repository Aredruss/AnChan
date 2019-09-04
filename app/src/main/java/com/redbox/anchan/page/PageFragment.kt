package com.redbox.anchan.page

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.redbox.anchan.R
import com.redbox.anchan.posts.PostAdapter

class PageFragment : Fragment() {

    lateinit var board: String
    var viewModel  =  PageViewModel()
    val postAdapter = PostAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        board = arguments!!.getString("board")
        return inflater.inflate(R.layout.page_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadPage(board)
       // postAdapter.posts = viewModel.fetchedPage.value


        Log.d("T", board)
    }
}