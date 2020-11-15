package com.ninkuk.atmanirbharbharat_tarunmanch.ui.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ninkuk.atmanirbharbharat_tarunmanch.R
import com.ninkuk.atmanirbharbharat_tarunmanch.data.Business
import com.ninkuk.atmanirbharbharat_tarunmanch.ui.categories.CategoryConstants
import com.ninkuk.atmanirbharbharat_tarunmanch.ui.categories.CategoryListFragmentDirections
import kotlinx.android.synthetic.main.cell_business.view.*

class FavoriteRecyclerViewAdapter(private val businessList: List<Business>) :
    RecyclerView.Adapter<FavoriteRecyclerViewAdapter.FavoriteRecyclerViewHolder>() {

    inner class FavoriteRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(business: Business) {
            itemView.cardTitle.text =
                if (business.businessName.isEmpty()) business.owners else business.businessName
            itemView.small_description.text = business.description

            setIcon(business)
        }

        private fun setIcon(business: Business) {
            when (business.category) {
                CategoryConstants.AUTO -> {
                    itemView.categoryImageCard.setBackgroundColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.autoColor
                        )
                    )
                    itemView.categoryImage.setImageResource(R.drawable.ic_car)
                    itemView.categoryImage.imageTintList =
                        ContextCompat.getColorStateList(itemView.context, R.color.black)
                }

                CategoryConstants.BEAUTY -> {
                    itemView.categoryImageCard.setBackgroundColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.beautyColor
                        )
                    )
                    itemView.categoryImage.setImageResource(R.drawable.ic_cosmetic)
                }

                CategoryConstants.BOUTIQUE -> {
                    itemView.categoryImageCard.setBackgroundColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.boutiqueColor
                        )
                    )
                    itemView.categoryImage.setImageResource(R.drawable.ic_shop)
                }

                CategoryConstants.BUILDERS -> {
                    itemView.categoryImageCard.setBackgroundColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.builderColor
                        )
                    )
                    itemView.categoryImage.setImageResource(R.drawable.ic_construction)
                }

                CategoryConstants.CATERING -> {
                    itemView.categoryImageCard.setBackgroundColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.cateringColor
                        )
                    )
                    itemView.categoryImage.setImageResource(R.drawable.ic_food)
                    itemView.categoryImage.imageTintList =
                        ContextCompat.getColorStateList(itemView.context, R.color.black)
                }

                CategoryConstants.COACHING -> {
                    itemView.categoryImageCard.setBackgroundColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.coachingColor
                        )
                    )
                    itemView.categoryImage.setImageResource(R.drawable.ic_coaching)
                }

                CategoryConstants.COMPUTER -> {
                    itemView.categoryImageCard.setBackgroundColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.computerColor
                        )
                    )
                    itemView.categoryImage.setImageResource(R.drawable.ic_electronics)
                }

                CategoryConstants.GURUJI -> {
                    itemView.categoryImageCard.setBackgroundColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.gurujiColor
                        )
                    )
                    itemView.categoryImage.setImageResource(R.drawable.ic_havan)
                    itemView.categoryImage.imageTintList =
                        ContextCompat.getColorStateList(itemView.context, R.color.black)
                }

                CategoryConstants.HANDICRAFT -> {
                    itemView.categoryImageCard.setBackgroundColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.handicraftColor
                        )
                    )
                    itemView.categoryImage.setImageResource(R.drawable.ic_fabric)
                }

                CategoryConstants.HEALTH -> {
                    itemView.categoryImageCard.setBackgroundColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.healthColor
                        )
                    )
                    itemView.categoryImage.setImageResource(R.drawable.ic_heart)
                }

                CategoryConstants.INVESTMENT -> {
                    itemView.categoryImageCard.setBackgroundColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.investmentColor
                        )
                    )
                    itemView.categoryImage.setImageResource(R.drawable.ic_consultancy)
                }

                CategoryConstants.KIRANA -> {
                    itemView.categoryImageCard.setBackgroundColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.kiranaColor
                        )
                    )
                    itemView.categoryImage.setImageResource(R.drawable.ic_grocery)
                }

                CategoryConstants.OTHER -> {
                    itemView.categoryImageCard.setBackgroundColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.otherColor
                        )
                    )
                    itemView.categoryImage.setImageResource(R.drawable.ic_hand)
                }

                CategoryConstants.PRINTING -> {
                    itemView.categoryImageCard.setBackgroundColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.printingColor
                        )
                    )
                    itemView.categoryImage.setImageResource(R.drawable.ic_stationary)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteRecyclerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.cell_business, parent, false)
        return FavoriteRecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteRecyclerViewHolder, position: Int) {
        holder.bindView(businessList[position])

        holder.itemView.setOnClickListener {
            val action =
                FavoriteFragmentDirections.favoriteToBusinessPage(businessList[position])
            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return businessList.size
    }

}