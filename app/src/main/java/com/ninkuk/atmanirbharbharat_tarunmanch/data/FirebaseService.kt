package com.ninkuk.atmanirbharbharat_tarunmanch.data

import android.annotation.SuppressLint
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.util.Util.autoId
import com.ninkuk.atmanirbharbharat_tarunmanch.data.Business.Companion.toBusiness
import kotlinx.coroutines.tasks.await
import java.lang.Exception

object FirebaseService {

    private val db = FirebaseFirestore.getInstance()


    suspend fun getRandomBusinessFromAllCategories(): List<Business> {

        val randBusinessList = arrayListOf<Business>()

//        randBusinessList.add(getRandomBusinessFromCategory("auto"))
//        randBusinessList.add(getRandomBusinessFromCategory("beauty"))
//        randBusinessList.add(getRandomBusinessFromCategory("boutique"))
//        randBusinessList.add(getRandomBusinessFromCategory("builder"))
        randBusinessList.add(getRandomBusinessFromCategory("catering"))
        randBusinessList.add(getRandomBusinessFromCategory("coaching"))
        randBusinessList.add(getRandomBusinessFromCategory("computers"))
//        randBusinessList.add(getRandomBusinessFromCategory("guruji"))
//        randBusinessList.add(getRandomBusinessFromCategory("handicraft"))
//        randBusinessList.add(getRandomBusinessFromCategory("health"))
//        randBusinessList.add(getRandomBusinessFromCategory("investment"))
        randBusinessList.add(getRandomBusinessFromCategory("kirana"))
//        randBusinessList.add(getRandomBusinessFromCategory("printing"))
//        randBusinessList.add(getRandomBusinessFromCategory("other"))

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
}