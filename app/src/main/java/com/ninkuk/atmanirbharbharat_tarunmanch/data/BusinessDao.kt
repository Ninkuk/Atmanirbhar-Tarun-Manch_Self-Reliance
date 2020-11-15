package com.ninkuk.atmanirbharbharat_tarunmanch.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BusinessDao {

    @Insert
    suspend fun addBusiness(business: Business)

    @Delete
    suspend fun deleteBusiness(business: Business)

    @Query("SELECT * FROM business_table")
    fun readAllData(): LiveData<List<Business>>

    @Query("SELECT * FROM business_table WHERE id=:key")
    suspend fun getBusinessById(key: String): Business?
}