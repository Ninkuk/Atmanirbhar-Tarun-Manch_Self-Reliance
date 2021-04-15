package com.ninkuk.atmanirbharbharat_tarunmanch.ui.business

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.ninkuk.atmanirbharbharat_tarunmanch.data.BusinessDatabase
import com.ninkuk.atmanirbharbharat_tarunmanch.data.BusinessRepository

class BusinessViewModel(application: Application) : AndroidViewModel(application) {
    suspend fun existsInDB(id: String): Boolean {
        val businessDao = BusinessDatabase.getDatabase(getApplication()).businessDao()
        val repository = BusinessRepository(businessDao)

        return repository.getBusinessById(id) != null
    }
}