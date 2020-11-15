package com.ninkuk.atmanirbharbharat_tarunmanch.data

import androidx.lifecycle.LiveData

class BusinessRepository(private val businessDao: BusinessDao) {

    val readAllData: LiveData<List<Business>> = businessDao.readAllData()

    suspend fun addBusiness(business: Business) {
        businessDao.addBusiness(business)
    }

    suspend fun getBusinessById(id: String): Business? {
        return businessDao.getBusinessById(id)
    }

    suspend fun deleteUser(business: Business) {
        businessDao.deleteBusiness(business)
    }
}