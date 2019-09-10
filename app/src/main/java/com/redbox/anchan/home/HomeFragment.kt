package com.redbox.anchan.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.redbox.anchan.R
import com.redbox.anchan.board.BoardAdapter
import com.redbox.anchan.page.PageFragment
import kotlinx.android.synthetic.main.home_fragment_layout.*

class HomeFragment : Fragment() {

    lateinit var viewModel: HomeViewModel
    lateinit var boardAdapter: BoardAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        return inflater.inflate(R.layout.home_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        boardAdapter = BoardAdapter()
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
        val pageFragment = PageFragment()
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


