package com.ninkuk.atmanirbharbharat_tarunmanch.ui.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ninkuk.atmanirbharbharat_tarunmanch.R
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlinx.android.synthetic.main.fragment_home.*

class FavoriteFragment : Fragment() {

    private lateinit var favoriteViewModel: FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favorite, container, false)

        favoriteViewModel = ViewModelProvider(this)[FavoriteViewModel::class.java]
        val businessList = favoriteViewModel.readAllData
        businessList.observe(viewLifecycleOwner, Observer {
            favoriteRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            favoriteRecyclerView.adapter = FavoriteRecyclerViewAdapter(it)

            if (it.isEmpty()) {
                emptyRecyclerViewText.visibility = View.VISIBLE
                favoriteRecyclerView.visibility = View.GONE
            } else {
                emptyRecyclerViewText.visibility = View.GONE
                favoriteRecyclerView.visibility = View.VISIBLE
            }
        })

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}