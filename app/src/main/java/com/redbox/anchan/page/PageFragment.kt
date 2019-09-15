package com.redbox.anchan.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.redbox.anchan.R
import com.redbox.anchan.thread.ThreadFragment
import kotlinx.android.synthetic.main.page_fragment_layout.*

class PageFragment : Fragment() {

    lateinit var board: String
    lateinit var pageItemAdapter: PageItemAdapter
    lateinit var viewModel: PageViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(PageViewModel::class.java)
        board = arguments!!.getString("board")!!
        return inflater.inflate(R.layout.page_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        pageItemAdapter = PageItemAdapter()
        pageItemAdapter.hostFragment = this

        if (viewModel.posts.value.isNullOrEmpty()) {
            viewModel.loadPage(board)
        }

        //Observing Posts From the ViewModel
        viewModel.getPosts(this) { posts ->
            pageItemAdapter.posts = posts
            board_page_rv.adapter = pageItemAdapter
            pageItemAdapter.notifyDataSetChanged()

        }

        board_page_rv.layoutManager = GridLayoutManager((this.context), 2)

    }

    fun openThread(board: String, no: Int) {
        val pageFragment = ThreadFragment()
        val bundle = Bundle()

        bundle.putString("board", board)
        bundle.putInt("number", no)
        pageFragment.arguments = bundle
        val manager = activity?.supportFragmentManager
        manager?.beginTransaction()?.replace(
            R.id.main_activity_cl,
            pageFragment
        )
            ?.addToBackStack(board + "_" + no)
            ?.commit()
    }
}

