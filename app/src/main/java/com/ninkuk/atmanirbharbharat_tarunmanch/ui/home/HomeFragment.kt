package com.ninkuk.atmanirbharbharat_tarunmanch.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ninkuk.atmanirbharbharat_tarunmanch.R
import com.ninkuk.atmanirbharbharat_tarunmanch.ui.categories.CategoryConstants
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]

        val businessList = homeViewModel.suggestedBusiness
        businessList.observe(viewLifecycleOwner, Observer {
            homeRecyclerView.layoutManager = LinearLayoutManager(this.context)
            homeRecyclerView.adapter = HomeRecyclerViewAdapter(it)

            if (it.isNotEmpty()) {
                progressIndicator.visibility = View.GONE
            }
        })

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        seeAllCats.setOnClickListener {
            findNavController().navigate(R.id.home_to_categories)
        }

        tarunManchLogo.setOnClickListener {
            nestedScrollView.smoothScrollTo(0, 0)
        }



        setUpCardListeners()
    }

    private fun setUpCardListeners() {
        kiranaCard.setOnClickListener {
            navigateToList(CategoryConstants.KIRANA, CategoryConstants.KIRANA_SHORT)
        }
        healthCard.setOnClickListener {
            navigateToList(CategoryConstants.HEALTH, CategoryConstants.HEALTH_SHORT)
        }
        cateringCard.setOnClickListener {
            navigateToList(CategoryConstants.CATERING, CategoryConstants.CATERING_SHORT)
        }
        coachingCard.setOnClickListener {
            navigateToList(CategoryConstants.COACHING, CategoryConstants.COACHING_SHORT)
        }
        builderCard.setOnClickListener {
            navigateToList(CategoryConstants.BUILDERS, CategoryConstants.BUILDERS_SHORT)
        }
    }

    private fun navigateToList(category: String, categoryShort: String) {
        val action = HomeFragmentDirections.homeToCategoryList(category, categoryShort)
        findNavController().navigate(action)
    }
}