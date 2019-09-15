package com.redbox.anchan.boardlist

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.redbox.anchan.R
import com.redbox.anchan.boardlist.BoardListAdapter.ViewHolder
import com.redbox.anchan.network.pojo.Board
import kotlinx.android.synthetic.main.board_item_layout.view.*

class BoardListAdapter : RecyclerView.Adapter<ViewHolder>() {
    lateinit var boards: List<Board>
    lateinit var hostFragment: BoardListFragment

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.board_item_layout,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return boards.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.codeTv.text = boards[position].board
        holder.nameTv.text = boards[position].title

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        init{
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            hostFragment.openBoard(boards[this.adapterPosition].board)
            Log.d("T", v.toString())
        }

        val codeTv = itemView.board_code_tv
        val nameTv = itemView.board_name_tv


    }
}