package com.ninkuk.atmanirbharbharat_tarunmanch.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ninkuk.atmanirbharbharat_tarunmanch.R
import com.ninkuk.atmanirbharbharat_tarunmanch.data.Business
import kotlinx.android.synthetic.main.cell_business.view.*

class HomeRecyclerViewAdapter(private val businessList: List<Business>) :
    RecyclerView.Adapter<HomeRecyclerViewAdapter.HomeRecyclerViewHolder>() {

    inner class HomeRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(business: Business) {
            itemView.cardTitle.text =
                if (business.businessName.isEmpty()) business.owners else business.businessName
            itemView.small_description.text = business.description

            //TODO when to set category icon
        }
    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeRecyclerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.cell_business, parent, false)
        return HomeRecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeRecyclerViewHolder, position: Int) {
        holder.bindView(businessList[position])

        holder.itemView.setOnClickListener {
            val action = HomeFragmentDirections.homeToBusiness(businessList[position])
            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return businessList.size
    }
}