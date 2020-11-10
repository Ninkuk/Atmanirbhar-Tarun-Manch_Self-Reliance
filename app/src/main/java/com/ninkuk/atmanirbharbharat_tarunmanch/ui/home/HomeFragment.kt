package com.ninkuk.atmanirbharbharat_tarunmanch.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ninkuk.atmanirbharbharat_tarunmanch.R
import com.ninkuk.atmanirbharbharat_tarunmanch.data.Business
import com.ninkuk.atmanirbharbharat_tarunmanch.ui.business.BusinessViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

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

        testBus.setOnClickListener {
            findNavController().navigate(R.id.home_to_business)
        }
    }
}