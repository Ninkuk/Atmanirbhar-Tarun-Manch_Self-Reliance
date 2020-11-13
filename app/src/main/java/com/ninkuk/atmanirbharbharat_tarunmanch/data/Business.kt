package com.ninkuk.atmanirbharbharat_tarunmanch.data

import android.os.Parcelable
import android.util.Log
import androidx.annotation.Keep
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.getField
import kotlinx.android.parcel.Parcelize
import java.io.Serializable
import java.lang.Exception
import kotlin.reflect.typeOf

@Keep
@Parcelize
data class Business(
    val id: String = "",
    val businessName: String = "",
    val description: String = "",
    val locationAddress: String = "",
    val emailAddress: String = "",
    val owners: String = "",
    val phoneNumbers: List<String> = emptyList(),
    val category: String = ""
) : Serializable, Parcelable {


    companion object {
        fun DocumentSnapshot.toBusiness(): Business {
            try {
                val id = if (getString("id") != null) getString("id") else ""
                val businessName =
                    if (getString("businessName") != null) getString("businessName") else ""
                val description =
                    if (getString("description") != null) getString("description") else ""
                val locationAddress =
                    if (getString("locationAddress") != null) getString("locationAddress") else ""
                val emailAddress =
                    if (getString("emailAddress") != null) getString("emailAddress") else ""
                val owners = if (getString("owners") != null) getString("owners") else ""
                val phoneNumbers =
                    if (get("phoneNumbers") != null) get("phoneNumbers") as List<String> else emptyList<String>()
                val category = if (getString("category") != null) getString("category") else ""

                return Business(
                    id!!,
                    businessName!!,
                    description!!,
                    locationAddress!!,
                    emailAddress!!,
                    owners!!,
                    phoneNumbers,
                    category!!
                )
            } catch (e: Exception) {
                return Business(businessName = ":(")
            }
        }
    }
}