package com.redbox.anchan.thread

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.redbox.anchan.R
import com.redbox.anchan.post.PostAdapter

class ThreadFragment : Fragment() {

    lateinit var threadViewModel: ThreadViewModel
    lateinit var board: String
    var number: Int = 0

    //TODO Imlplement another adapter

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

        threadViewModel.observeThread(this) { postList ->


        }

    }

}

