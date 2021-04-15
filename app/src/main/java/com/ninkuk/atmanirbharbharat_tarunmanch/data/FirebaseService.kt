package com.ninkuk.atmanirbharbharat_tarunmanch.data

import android.annotation.SuppressLint
import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.util.Util.autoId
import com.ninkuk.atmanirbharbharat_tarunmanch.data.Business.Companion.toBusiness
import com.ninkuk.atmanirbharbharat_tarunmanch.ui.categories.CategoryConstants
import kotlinx.coroutines.tasks.await
import java.lang.Exception

object FirebaseService {

    private val db = FirebaseFirestore.getInstance()


    suspend fun getRandomBusinessFromAllCategories(): List<Business> {

        val randBusinessList = arrayListOf<Business>()

        randBusinessList.add(getRandomBusinessFromCategory(CategoryConstants.AUTO_SHORT))
        randBusinessList.add(getRandomBusinessFromCategory(CategoryConstants.BEAUTY_SHORT))
        randBusinessList.add(getRandomBusinessFromCategory(CategoryConstants.BOUTIQUE_SHORT))
        randBusinessList.add(getRandomBusinessFromCategory(CategoryConstants.BUILDERS_SHORT))
        randBusinessList.add(getRandomBusinessFromCategory(CategoryConstants.CATERING_SHORT))
        randBusinessList.add(getRandomBusinessFromCategory(CategoryConstants.COACHING_SHORT))
        randBusinessList.add(getRandomBusinessFromCategory(CategoryConstants.COMPUTER_SHORT))
        randBusinessList.add(getRandomBusinessFromCategory(CategoryConstants.GURUJI_SHORT))
        randBusinessList.add(getRandomBusinessFromCategory(CategoryConstants.HANDICRAFT_SHORT))
        randBusinessList.add(getRandomBusinessFromCategory(CategoryConstants.HEALTH_SHORT))
        randBusinessList.add(getRandomBusinessFromCategory(CategoryConstants.INVESTMENT_SHORT))
        randBusinessList.add(getRandomBusinessFromCategory(CategoryConstants.KIRANA_SHORT))
        randBusinessList.add(getRandomBusinessFromCategory(CategoryConstants.PRINTING_SHORT))
        randBusinessList.add(getRandomBusinessFromCategory(CategoryConstants.OTHER_SHORT))

        return randBusinessList
    }

    @SuppressLint("RestrictedApi")
    private suspend fun getRandomBusinessFromCategory(category: String): Business {
        val randomKey = autoId()!!

        var randDocRef = db.collection(category)
            .whereLessThanOrEqualTo("id", randomKey)
            .orderBy("id", Query.Direction.DESCENDING)
            .limit(1)

        val lessThanSnapshot = randDocRef.get().await()

        try {
            return if (lessThanSnapshot.size() > 0) {
                lessThanSnapshot.first().toBusiness()
            } else {
                randDocRef = db.collection(category)
                    .whereGreaterThan("id", randomKey)
                    .orderBy("id", Query.Direction.DESCENDING)
                    .limit(1)
                val greaterThanSnapshot = randDocRef.get().await()
                if (greaterThanSnapshot.size() > 0) {
                    greaterThanSnapshot.first().toBusiness()
                } else {
                    Business(businessName = ":-(")
                }
            }
        } catch (e: Exception) {
            Log.d("BRUH", "FirebaseService...$e")
        }

        return Business(businessName = ":|")
    }

    suspend fun getBusinessesForCategory(
        category: String,
        startAfterSnapshot: DocumentSnapshot
    ): QuerySnapshot {

        val categoryRef =
            db.collection(category).orderBy("id").startAfter(startAfterSnapshot).limit(5)

        return categoryRef.get().await()
    }

    suspend fun getBusinessesForCategory(
        category: String
    ): QuerySnapshot {

        val categoryRef = db.collection(category).orderBy("id").limit(5)

        return categoryRef.get().await()
    }

    fun addBusinessForApproval(business: Business) {
        val approvalsRef = db.collection("approvals").document()
        business.id = approvalsRef.id
        approvalsRef.set(business)
    }
}