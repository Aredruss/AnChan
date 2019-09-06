package com.redbox.anchan.page

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.redbox.anchan.R
import com.redbox.anchan.network.pojo.Post
import com.redbox.anchan.post.PostAdapter
import kotlinx.android.synthetic.main.page_fragment_layout.*

class PageFragment : Fragment() {

    lateinit var board: String
    lateinit var postAdapter : PostAdapter
    lateinit var viewModel : PageViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(PageViewModel::class.java)
        board = arguments!!.getString("board")!!
        //Log.d("D", viewModel.getPosts().value.isNullOrEmpty().toString())
        return inflater.inflate(R.layout.page_fragment_layout, container, false)
    }

    //Отрисовка Recycler не должна происходить при создании View, но понятного решения лучше я пока не нашел
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        if (viewModel.getPosts().value.isNullOrEmpty()){
            viewModel.loadPage(board)
        }

        //Observing Posts From the ViewModel
        viewModel.getPosts().observe(this, Observer<List<Post>> {
            postAdapter = PostAdapter()
            postAdapter.posts = it
            board_page_rv.adapter = postAdapter
        })

        board_page_rv.layoutManager = LinearLayoutManager(this.context)

    }

}