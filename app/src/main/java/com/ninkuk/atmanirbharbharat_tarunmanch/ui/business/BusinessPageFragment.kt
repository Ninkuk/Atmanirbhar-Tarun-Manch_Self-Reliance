package com.ninkuk.atmanirbharbharat_tarunmanch.ui.business

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.button.MaterialButton
import com.ninkuk.atmanirbharbharat_tarunmanch.R
import com.ninkuk.atmanirbharbharat_tarunmanch.data.Business
import com.ninkuk.atmanirbharbharat_tarunmanch.ui.categories.CategoryConstants
import com.ninkuk.atmanirbharbharat_tarunmanch.ui.favorites.FavoriteViewModel
import kotlinx.android.synthetic.main.fragment_business_page.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BusinessPageFragment : Fragment() {

    private val args: BusinessPageFragmentArgs by navArgs()
    private lateinit var businessViewModel: BusinessViewModel
    private lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var shareString: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_business_page, container, false)

        businessViewModel = ViewModelProvider(this)[BusinessViewModel::class.java]
        favoriteViewModel = ViewModelProvider(this)[FavoriteViewModel::class.java]

        CoroutineScope(Dispatchers.IO).launch {
            if (businessViewModel.existsInDB(args.businessObject.id)) {
                favFAB.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_baseline_favorite_24
                    )
                )
            } else {
                favFAB.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_baseline_favorite_border_24
                    )
                )
            }
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setData(args.businessObject)

        favFAB.supportImageTintList =
            ContextCompat.getColorStateList(this.requireContext(), R.color.healthColor)

        favFAB.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                if (businessViewModel.existsInDB(args.businessObject.id)) {
                    favoriteViewModel.deleteBusiness(args.businessObject)
                    favFAB.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.ic_baseline_favorite_border_24
                        )
                    )
                } else {
                    favoriteViewModel.addBusiness(args.businessObject)
                    favFAB.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.ic_baseline_favorite_24
                        )
                    )
                }
            }
        }

        backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        businessOptionsMenu.setOnClickListener {
            val popupMenu = PopupMenu(requireContext(), businessOptionsMenu)
            popupMenu.inflate(R.menu.business_options_menu)
            popupMenu.setOnMenuItemClickListener {

                when (it.itemId) {
                    R.id.businessReportButton -> sendBugReportEmail()
                    R.id.businessShareButton -> shareBusiness()
                }

                true
            }

            popupMenu.show()
        }
    }

    private fun shareBusiness() {
        val appId = requireContext().packageName
        val share = Intent(Intent.ACTION_SEND)
        share.type = "text/plain"
        val shareText = "Check out this service!\n\n$shareString"
        share.putExtra(
            Intent.EXTRA_TEXT,
            "$shareText\n\nDownload Atmanirbhar Tarun Manch App Today!\nhttps://play.google.com/store/apps/details?id=$appId"
        )
        startActivity(Intent.createChooser(share, "Share app using"))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.business_options_menu, menu)
    }

    private fun setData(business: Business) {

        // Title text can either be the company name or the category name
        titleText.text =
            if (business.businessName.isEmpty()) business.category else business.businessName

        shareString = titleText.text.toString()

        // Set owners or remove the view if empty
        if (business.owners.isEmpty()) {
            ownersText.visibility = View.GONE
        } else {
            ownersText.text = business.owners
            shareString += "\n" + ownersText.text.toString()
        }

        // Set required description text
        descriptionText.text = business.description
        shareString += "\n" + descriptionText.text.toString()

        setStyle(business)
        setPhoneNumbers(business)
        setEmail(business)

        if (business.locationAddress.isNotEmpty()) {
            addressContainer.visibility = View.VISIBLE
            addressText.text = business.locationAddress
            shareString += "\n" + addressText.text
        }
    }

    private fun setPhoneNumbers(business: Business) {
        for (number in business.phoneNumbers) {
            val btn: MaterialButton =
                this.layoutInflater.inflate(R.layout.layout_phone_button, null) as MaterialButton
            btn.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            btn.text = "+91 $number"
            contactContainer.addView(btn)

            btn.setOnClickListener {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:${btn.text}")
                startActivity(intent)
            }

            shareString += "\n" + btn.text
        }
    }

    private fun setEmail(business: Business) {
        if (business.emailAddress.isNotEmpty()) {
            val btn: MaterialButton =
                this.layoutInflater.inflate(R.layout.layout_email_button, null) as MaterialButton
            btn.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            btn.text = business.emailAddress
            contactContainer.addView(btn)

            btn.setOnClickListener {
                val TO_EMAILS = arrayOf(business.emailAddress)
                val BODY =
                    "\n\n\nSent via Atmanirbhar Tarun Manch App"

                val intent = Intent(Intent.ACTION_SENDTO)
                intent.data = Uri.parse("mailto:")
                intent.putExtra(Intent.EXTRA_EMAIL, TO_EMAILS)
                intent.putExtra(Intent.EXTRA_TEXT, BODY)

                startActivity(
                    Intent.createChooser(
                        intent,
                        "Please choose an application to send the email"
                    )
                )
            }

            shareString += "\n" + btn.text
        }
    }

    private fun sendBugReportEmail() {
        val TO_EMAILS = arrayOf("ninadk03@gmail.com")
        val SUBJECT = "Re: Issue with business information"
        val BODY =
            "\n\n\nCategory: ${args.businessObject.category}\nBusiness ID: ${args.businessObject.id}"

        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:")
        intent.putExtra(Intent.EXTRA_EMAIL, TO_EMAILS)
        intent.putExtra(Intent.EXTRA_SUBJECT, SUBJECT)
        intent.putExtra(Intent.EXTRA_TEXT, BODY)

        startActivity(
            Intent.createChooser(
                intent,
                "Please choose an application to send the email"
            )
        )
    }

    private fun setStyle(business: Business) {
        // Set background color and icon based on category
        when (business.category) {
            CategoryConstants.AUTO -> {
                titleContainer.setBackgroundColor(
                    ContextCompat.getColor(
                        this.requireContext(),
                        R.color.autoColor
                    )
                )
                categoryImage.setImageResource(R.drawable.ic_car)
                categoryImage.imageTintList =
                    ContextCompat.getColorStateList(requireContext(), R.color.black)
                titleText.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                ownersText.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            }

            CategoryConstants.BEAUTY -> {
                titleContainer.setBackgroundColor(
                    ContextCompat.getColor(
                        this.requireContext(),
                        R.color.beautyColor
                    )
                )
                categoryImage.setImageResource(R.drawable.ic_cosmetic)
            }

            CategoryConstants.BOUTIQUE -> {
                titleContainer.setBackgroundColor(
                    ContextCompat.getColor(
                        this.requireContext(),
                        R.color.boutiqueColor
                    )
                )
                categoryImage.setImageResource(R.drawable.ic_shop)
            }

            CategoryConstants.BUILDERS -> {
                titleContainer.setBackgroundColor(
                    ContextCompat.getColor(
                        this.requireContext(),
                        R.color.builderColor
                    )
                )
                categoryImage.setImageResource(R.drawable.ic_construction)
            }

            CategoryConstants.CATERING -> {
                titleContainer.setBackgroundColor(
                    ContextCompat.getColor(
                        this.requireContext(),
                        R.color.cateringColor
                    )
                )
                categoryImage.setImageResource(R.drawable.ic_food)
                categoryImage.imageTintList =
                    ContextCompat.getColorStateList(requireContext(), R.color.black)
                titleText.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                ownersText.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            }

            CategoryConstants.COACHING -> {
                titleContainer.setBackgroundColor(
                    ContextCompat.getColor(
                        this.requireContext(),
                        R.color.coachingColor
                    )
                )
                categoryImage.setImageResource(R.drawable.ic_coaching)
            }

            CategoryConstants.COMPUTER -> {
                titleContainer.setBackgroundColor(
                    ContextCompat.getColor(
                        this.requireContext(),
                        R.color.computerColor
                    )
                )
                categoryImage.setImageResource(R.drawable.ic_electronics)
            }

            CategoryConstants.GURUJI -> {
                titleContainer.setBackgroundColor(
                    ContextCompat.getColor(
                        this.requireContext(),
                        R.color.gurujiColor
                    )
                )
                categoryImage.setImageResource(R.drawable.ic_havan)
                categoryImage.imageTintList =
                    ContextCompat.getColorStateList(requireContext(), R.color.black)
                titleText.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                ownersText.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            }

            CategoryConstants.HANDICRAFT -> {
                titleContainer.setBackgroundColor(
                    ContextCompat.getColor(
                        this.requireContext(),
                        R.color.handicraftColor
                    )
                )
                categoryImage.setImageResource(R.drawable.ic_fabric)
            }

            CategoryConstants.HEALTH -> {
                titleContainer.setBackgroundColor(
                    ContextCompat.getColor(
                        this.requireContext(),
                        R.color.healthColor
                    )
                )
                categoryImage.setImageResource(R.drawable.ic_heart)
            }

            CategoryConstants.INVESTMENT -> {
                titleContainer.setBackgroundColor(
                    ContextCompat.getColor(
                        this.requireContext(),
                        R.color.investmentColor
                    )
                )
                categoryImage.setImageResource(R.drawable.ic_consultancy)
            }

            CategoryConstants.KIRANA -> {
                titleContainer.setBackgroundColor(
                    ContextCompat.getColor(
                        this.requireContext(),
                        R.color.kiranaColor
                    )
                )
                categoryImage.setImageResource(R.drawable.ic_grocery)
            }

            CategoryConstants.OTHER -> {
                titleContainer.setBackgroundColor(
                    ContextCompat.getColor(
                        this.requireContext(),
                        R.color.otherColor
                    )
                )
                categoryImage.setImageResource(R.drawable.ic_hand)
            }

            CategoryConstants.PRINTING -> {
                titleContainer.setBackgroundColor(
                    ContextCompat.getColor(
                        this.requireContext(),
                        R.color.printingColor
                    )
                )
                categoryImage.setImageResource(R.drawable.ic_stationary)
            }
        }
    }
}