package com.ninkuk.atmanirbharbharat_tarunmanch.ui.about

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ninkuk.atmanirbharbharat_tarunmanch.R
import kotlinx.android.synthetic.main.fragment_about.*

class AboutFragment : Fragment() {

    private lateinit var appId: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        appId = requireContext().packageName

        // Navigates to new business submission form
        requestFormBtn.setOnClickListener { findNavController().navigate(R.id.about_to_submission) }

        // Contacts
        prashantBadweContact.setOnClickListener { callNumber("9926528546") }
        sunilDharmadhikariContact.setOnClickListener { callNumber("9826421460") }
        sameerPanseContact.setOnClickListener { callNumber("9303241424") }
        sunilDeshpandeContact.setOnClickListener { callNumber("9826493633") }

        // Sends blank email to me
        ninadKulkarniEmail.setOnClickListener {
            sendEmail(
                arrayOf("ninadk03@gmail.com"),
                "Re: Suggestion or Query [Atmanirbhar Tarun Manch App]",
                ""
            )
        }

        // Sends bug report with device details
        bugReport.setOnClickListener {
            sendEmail(
                arrayOf("ninadk03@gmail.com"),
                "Re: Bug Report [Atmanirbhar Tarun Manch App]",
                "Please include as much information as you can and attach screenshots if possible." +
                        "\n\nSummary: " +
                        "\n\nSteps to reproduce: " +
                        "\n\n---------------------------------" +
                        "\nDEVICE INFO" +
                        "\nApp version: 1.0" +
                        "\nAndroid Version: ${Build.VERSION.SDK_INT}" +
                        "\nManufacturer: ${Build.MANUFACTURER}" +
                        "\nPhone Model: ${Build.MODEL}" +
                        "\n---------------------------------" +
                        "\n\nThank you for reporting a bug! We will try to fix this issue as soon as possible!"
            )
        }

        // Open my Github profile
        githubBtn.setOnClickListener { navigateToGithub() }

        // Opens the play store listing page for rating and feedback
        rateBtn.setOnClickListener { navigateToPlayStore() }

        // Shares the plain text link to the play store listing
        shareBtn.setOnClickListener { shareApplication() }
    }


    private fun callNumber(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:+91 $phoneNumber")
        startActivity(intent)
    }


    private fun sendEmail(emailAddress: Array<String>, subject: String, bodyCopy: String) {
        val TO_EMAILS = arrayOf(emailAddress)
        val SUBJECT = subject
        val BODY = bodyCopy

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


    private fun navigateToGithub() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("https://github.com/Ninkuk")
        startActivity(
            Intent.createChooser(
                intent,
                "Select an application..."
            )
        )
    }


    private fun navigateToPlayStore() {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("market://details?id=$appId")
            )
        )
    }


    private fun shareApplication() {
        val share = Intent(Intent.ACTION_SEND)
        share.type = "text/plain"
        share.putExtra(
            Intent.EXTRA_TEXT,
            "Download Atmanirbhar Tarun Manch App Today!\n\nhttps://play.google.com/store/apps/details?id=$appId"
        )
        startActivity(Intent.createChooser(share, "Share app using"))
    }
}