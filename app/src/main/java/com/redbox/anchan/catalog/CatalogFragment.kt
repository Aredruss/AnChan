package com.redbox.anchan.catalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.redbox.anchan.R
import com.redbox.anchan.page.PageFragment
import kotlinx.android.synthetic.main.catalog_fragment_layout.*

class CatalogFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.catalog_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button = catalog_board_btn
        val pageFragment = PageFragment()
        val bundle = Bundle()

        bundle.putString("board", button.text.toString())
        pageFragment.arguments = bundle

        button.setOnClickListener {
            val manager = activity?.supportFragmentManager
            manager?.beginTransaction()?.replace(
                R.id.main_activity_cl,
                pageFragment
            )
                ?.addToBackStack("Board")
                ?.commit()
        }
    }
}