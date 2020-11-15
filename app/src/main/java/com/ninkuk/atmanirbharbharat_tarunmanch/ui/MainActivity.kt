package com.ninkuk.atmanirbharbharat_tarunmanch.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.ninkuk.atmanirbharbharat_tarunmanch.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.nav_host_fragment)
        bottom_nav_view.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.categoryListFragment -> bottom_nav_view.visibility = View.GONE
                R.id.businessPageFragment -> bottom_nav_view.visibility = View.GONE
                else -> bottom_nav_view.visibility = View.VISIBLE
            }
        }
    }
}