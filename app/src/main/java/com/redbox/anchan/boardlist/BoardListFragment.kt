package com.redbox.anchan.boardlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.redbox.anchan.R
import com.redbox.anchan.page.PageTemplateFragment
import kotlinx.android.synthetic.main.home_fragment_layout.*

class BoardListFragment : Fragment() {

    lateinit var viewModel: BoardListViewModel
    var boardAdapter = BoardListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(BoardListViewModel::class.java)
        return inflater.inflate(R.layout.home_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        boardAdapter.hostFragment = this
        val boardRv = home_board_rv

        viewModel.getBoards()

        viewModel.observeBoards(this) { it ->
            boardAdapter.boards = it
            boardRv.adapter = boardAdapter
            boardAdapter.notifyDataSetChanged()
        }

        home_board_rv.layoutManager = LinearLayoutManager(this.context)

    }

    fun openBoard(boardString: String) {
        val pageFragment = PageTemplateFragment()
        val bundle = Bundle()

        bundle.putString("board", boardString)
        pageFragment.arguments = bundle

        val manager = activity?.supportFragmentManager
        manager?.beginTransaction()?.replace(
            R.id.main_activity_cl,
            pageFragment
        )
            ?.addToBackStack(boardString)
            ?.commit()
    }

}


