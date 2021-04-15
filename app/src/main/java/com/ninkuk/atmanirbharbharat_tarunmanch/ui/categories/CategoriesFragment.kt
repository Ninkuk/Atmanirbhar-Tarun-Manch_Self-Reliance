package com.ninkuk.atmanirbharbharat_tarunmanch.ui.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ninkuk.atmanirbharbharat_tarunmanch.R
import kotlinx.android.synthetic.main.fragment_categories.*

class CategoriesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        autoCard.setOnClickListener {
            navigateToList(CategoryConstants.AUTO, CategoryConstants.AUTO_SHORT)
        }
        beautyCard.setOnClickListener {
            navigateToList(CategoryConstants.BEAUTY, CategoryConstants.BEAUTY_SHORT)
        }
        boutiqueCard.setOnClickListener {
            navigateToList(CategoryConstants.BOUTIQUE, CategoryConstants.BOUTIQUE_SHORT)
        }
        builderCard.setOnClickListener {
            navigateToList(CategoryConstants.BUILDERS, CategoryConstants.BUILDERS_SHORT)
        }
        cateringCard.setOnClickListener {
            navigateToList(CategoryConstants.CATERING, CategoryConstants.CATERING_SHORT)
        }
        coachingCard.setOnClickListener {
            navigateToList(CategoryConstants.COACHING, CategoryConstants.COACHING_SHORT)
        }
        computerCard.setOnClickListener {
            navigateToList(CategoryConstants.COMPUTER, CategoryConstants.COMPUTER_SHORT)
        }
        gurujiCard.setOnClickListener {
            navigateToList(CategoryConstants.GURUJI, CategoryConstants.GURUJI_SHORT)
        }
        handicraftCard.setOnClickListener {
            navigateToList(CategoryConstants.HANDICRAFT, CategoryConstants.HANDICRAFT_SHORT)
        }
        healthCard.setOnClickListener {
            navigateToList(CategoryConstants.HEALTH, CategoryConstants.HEALTH_SHORT)
        }
        investmentCard.setOnClickListener {
            navigateToList(CategoryConstants.INVESTMENT, CategoryConstants.INVESTMENT_SHORT)
        }
        kiranaCard.setOnClickListener {
            navigateToList(CategoryConstants.KIRANA, CategoryConstants.KIRANA_SHORT)
        }
        printingCard.setOnClickListener {
            navigateToList(CategoryConstants.PRINTING, CategoryConstants.PRINTING_SHORT)
        }
        otherCard.setOnClickListener {
            navigateToList(CategoryConstants.OTHER, CategoryConstants.OTHER_SHORT)
        }
    }

    private fun navigateToList(category: String, categoryShort: String) {
        val action = CategoriesFragmentDirections.categoriesToCategoryList(category, categoryShort)
        findNavController().navigate(action)
    }
}