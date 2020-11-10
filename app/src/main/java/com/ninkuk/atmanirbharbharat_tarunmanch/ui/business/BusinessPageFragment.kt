package com.ninkuk.atmanirbharbharat_tarunmanch.ui.business

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.collection.LLRBNode
import com.ninkuk.atmanirbharbharat_tarunmanch.R
import kotlinx.android.synthetic.main.fragment_business_page.*

class BusinessPageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_business_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        favFAB.supportImageTintList =
            ContextCompat.getColorStateList(this.requireContext(), R.color.healthColor)
    }
}