package com.ninkuk.atmanirbharbharat_tarunmanch.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Entity(tableName = "business_table")
@Parcelize
data class Business(
    @PrimaryKey
    var id: String = "",
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
                val crashlytics = FirebaseCrashlytics.getInstance()
                crashlytics.log(
                    "Error occurred in business id ${getString("id")} of category ${
                        getString(
                            "category"
                        )
                    }"
                )
                crashlytics.recordException(e)
                return Business(businessName = "An error occurred")
            }
        }
    }
}