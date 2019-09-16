package com.redbox.anchan.thread

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.redbox.anchan.R
import kotlinx.android.synthetic.main.thread_fragment_layout.*

class ThreadFragment : Fragment() {

    lateinit var threadViewModel: ThreadViewModel
    var postAdapter = ThreadAdapter()
    lateinit var board: String
    var number: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        threadViewModel = ViewModelProviders.of(this).get(ThreadViewModel::class.java)
        board = arguments!!.getString("board")!!
        number = arguments!!.getInt("number")
        return inflater.inflate(R.layout.thread_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        if (threadViewModel.postList.value.isNullOrEmpty()) {
            threadViewModel.getThread(board, number)
        }

        thread_posts_rv.layoutManager = LinearLayoutManager(this.context)
        postAdapter.hostFragment = this

        threadViewModel.observeThread(this) { postList ->
            postAdapter.posts = postList
            thread_posts_rv.adapter = postAdapter
            postAdapter.notifyItemInserted(postList.size+1)
        }

    }

}

