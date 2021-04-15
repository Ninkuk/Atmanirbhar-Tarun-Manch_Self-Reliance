package com.ninkuk.atmanirbharbharat_tarunmanch.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.ninkuk.atmanirbharbharat_tarunmanch.R
import com.ninkuk.atmanirbharbharat_tarunmanch.data.Business
import com.ninkuk.atmanirbharbharat_tarunmanch.data.FirebaseService
import com.ninkuk.atmanirbharbharat_tarunmanch.ui.categories.CategoryConstants
import kotlinx.android.synthetic.main.fragment_submission_form.*


class SubmissionFormFragment : Fragment() {

    private val categoryTitleList = arrayListOf(
        "Choose a category",
        CategoryConstants.AUTO,
        CategoryConstants.BEAUTY,
        CategoryConstants.BOUTIQUE,
        CategoryConstants.BUILDERS,
        CategoryConstants.CATERING,
        CategoryConstants.COACHING,
        CategoryConstants.COMPUTER,
        CategoryConstants.GURUJI,
        CategoryConstants.HANDICRAFT,
        CategoryConstants.HEALTH,
        CategoryConstants.INVESTMENT,
        CategoryConstants.KIRANA,
        CategoryConstants.PRINTING,
        CategoryConstants.OTHER
    )

    private var selectedCategory = categoryTitleList[0]

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_submission_form, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        cancelBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        submitBtn.setOnClickListener {
            prepareForm()
        }

        categorySpinner.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            categoryTitleList
        )

        categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedCategory = categoryTitleList[position]
                if (position != 0) {
                    spinnerError.visibility = View.GONE
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun prepareForm() {
        val category = getCategoryName()
        val businessName = businessNameET.text.toString()
        val owners = ownersET.text.toString()
        val description = descriptionET.text.toString()
        val locationAddress = locationET.text.toString()
        val phoneNumbers = getPhoneNumbers()
        val emailAddress = getEmailAddress()

        if (phoneNumbers != null && category != null && emailAddress != null) {
            val business = Business(
                "",
                businessName,
                description,
                locationAddress,
                emailAddress,
                owners,
                phoneNumbers,
                category
            )

            try {
                FirebaseService.addBusinessForApproval(business)
            } catch (e: Exception) {
                Toast.makeText(
                    requireContext(),
                    "Sorry! An error occured while submitting your request. Please try again! If the error persists, please contact us.",
                    Toast.LENGTH_SHORT
                ).show()

                val crashlytics = FirebaseCrashlytics.getInstance()
                crashlytics.log(
                    e.message.toString()
                )

                crashlytics.recordException(e)

                return
            }

            categorySpinner.setSelection(0)
            businessNameET.text?.clear()
            ownersET.text?.clear()
            descriptionET.text?.clear()
            locationET.text?.clear()
            phoneNumbersET.text?.clear()
            emailAddressET.text?.clear()
            Toast.makeText(
                requireContext(),
                "Successfully submitted your request!",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().popBackStack()
        }
    }

    private fun getCategoryName(): String? {
        return if (selectedCategory.contentEquals(categoryTitleList[0])) {
            spinnerError.visibility = View.VISIBLE
            null
        } else {
            spinnerError.visibility = View.GONE
            selectedCategory
        }
    }

    private fun getPhoneNumbers(): List<String>? {
        val phoneNumbers: List<String> = phoneNumbersET.text.toString().split(",")
        val phoneList: ArrayList<String> = ArrayList()

        phoneNumbers.forEach {
            val num = it.replace(" ", "")

            if (num.length != 10) {
                phoneNumbersTF.isErrorEnabled = true
                phoneNumbersTF.error =
                    "Please enter valid 10 digit phone numbers. If you are including +91, then remove it and try again."

                return null
            }

            phoneList.add(num)
        }

        phoneNumbersTF.isErrorEnabled = false
        return phoneList
    }

    private fun getEmailAddress(): String? {
        val emailAddress = emailAddressET.text.toString()

        if (emailAddress.isEmpty()) {
            emailAddressTF.isErrorEnabled = true
            emailAddressTF.error = "Please enter a valid email address."

            return null
        }

        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        return if (emailAddress.trim { it <= ' ' }.matches(emailPattern.toRegex())) {
            emailAddressTF.isErrorEnabled = false
            emailAddress
        } else {
            emailAddressTF.isErrorEnabled = true
            emailAddressTF.error = "Please enter a valid email address."
            null
        }
    }
}