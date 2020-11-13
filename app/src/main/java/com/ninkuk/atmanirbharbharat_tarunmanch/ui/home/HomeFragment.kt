package com.ninkuk.atmanirbharbharat_tarunmanch.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ninkuk.atmanirbharbharat_tarunmanch.R
import com.ninkuk.atmanirbharbharat_tarunmanch.ui.categories.CategoriesFragmentDirections
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        val businessList = homeViewModel.suggestedBusiness
        businessList.observe(viewLifecycleOwner, Observer {
            homeRecyclerView.layoutManager = LinearLayoutManager(this.context)
            homeRecyclerView.adapter = HomeRecyclerViewAdapter(it)
        })

//        val businessViewModel = ViewModelProvider(this)[BusinessViewModel::class.java]
//
//        val x = businessViewModel.businessProfile
//        x.observe(viewLifecycleOwner, Observer {
//            testText.text = it[0].businessName
//        })

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        seeAllCats.setOnClickListener {
            findNavController().navigate(R.id.home_to_categories)
        }

        setUpCardListeners()
    }

    private fun setUpCardListeners() {
        kiranaCard.setOnClickListener {
            navigateToList("kirana")
        }
        healthCard.setOnClickListener {
            navigateToList("health")
        }
        cateringCard.setOnClickListener {
            navigateToList("catering")
        }
        coachingCard.setOnClickListener {
            navigateToList("coaching")
        }
        builderCard.setOnClickListener {
            navigateToList("builder")
        }
    }

    private fun navigateToList(category: String) {
        val action = HomeFragmentDirections.homeToCategoryList(category)
        findNavController().navigate(action)
    }
}