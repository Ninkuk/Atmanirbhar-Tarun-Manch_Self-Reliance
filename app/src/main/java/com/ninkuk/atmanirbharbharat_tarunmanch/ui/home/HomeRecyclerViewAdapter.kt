package com.ninkuk.atmanirbharbharat_tarunmanch.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ninkuk.atmanirbharbharat_tarunmanch.R
import com.ninkuk.atmanirbharbharat_tarunmanch.data.Business
import com.ninkuk.atmanirbharbharat_tarunmanch.ui.categories.CategoryConstants
import kotlinx.android.synthetic.main.cell_business.view.*

class HomeRecyclerViewAdapter(private val businessList: List<Business>) :
    RecyclerView.Adapter<HomeRecyclerViewAdapter.HomeRecyclerViewHolder>() {

    inner class HomeRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(business: Business) {
            itemView.cardTitle.text =
                if (business.businessName.isEmpty()) business.owners else business.businessName
            itemView.small_description.text = business.description

            setIcon(business)
        }

        private fun setDarkTheme(color: Int, image: Int) {
            itemView.categoryImageCard.setCardBackgroundColor(
                ContextCompat.getColor(
                    itemView.context,
                    color
                )
            )
            itemView.categoryImage.setImageResource(image)
            itemView.categoryImage.imageTintList =
                ContextCompat.getColorStateList(itemView.context, R.color.black)
        }

        private fun setLightTheme(color: Int, image: Int) {
            itemView.categoryImageCard.setCardBackgroundColor(
                ContextCompat.getColor(
                    itemView.context,
                    color
                )
            )
            itemView.categoryImage.setImageResource(image)
        }

        private fun setIcon(business: Business) {
            when (business.category) {
                CategoryConstants.AUTO -> {
                    setLightTheme(R.color.autoColor, R.drawable.ic_car)
                }

                CategoryConstants.BEAUTY -> {
                    setLightTheme(R.color.beautyColor, R.drawable.ic_cosmetic)
                }

                CategoryConstants.BOUTIQUE -> {
                    setLightTheme(R.color.boutiqueColor, R.drawable.ic_shop)
                }

                CategoryConstants.BUILDERS -> {
                    setLightTheme(R.color.builderColor, R.drawable.ic_construction)
                }

                CategoryConstants.CATERING -> {
                    setDarkTheme(R.color.cateringColor, R.drawable.ic_food)
                }

                CategoryConstants.COACHING -> {
                    setLightTheme(R.color.coachingColor, R.drawable.ic_coaching)
                }

                CategoryConstants.COMPUTER -> {
                    setLightTheme(R.color.computerColor, R.drawable.ic_electronics)
                }

                CategoryConstants.GURUJI -> {
                    setDarkTheme(R.color.gurujiColor, R.drawable.ic_havan)
                }

                CategoryConstants.HANDICRAFT -> {
                    setLightTheme(R.color.handicraftColor, R.drawable.ic_fabric)
                }

                CategoryConstants.HEALTH -> {
                    setLightTheme(R.color.healthColor, R.drawable.ic_heart)
                }

                CategoryConstants.INVESTMENT -> {
                    setLightTheme(R.color.investmentColor, R.drawable.ic_consultancy)
                }

                CategoryConstants.KIRANA -> {
                    setLightTheme(R.color.kiranaColor, R.drawable.ic_grocery)
                }

                CategoryConstants.OTHER -> {
                    setLightTheme(R.color.otherColor, R.drawable.ic_hand)
                }

                CategoryConstants.PRINTING -> {
                    setLightTheme(R.color.printingColor, R.drawable.ic_stationary)
                }
            }
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