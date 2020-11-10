package com.ninkuk.atmanirbharbharat_tarunmanch.data

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

object FirebaseService {

    private val db = FirebaseFirestore.getInstance()
    private val businessCollection = db.collection("business")

    suspend fun getAllBusinesses(): List<Business> {
        return try {
            businessCollection.get().await().toObjects(Business::class.java)
        } catch (e: Exception) {
            emptyList()
        }
    }
}