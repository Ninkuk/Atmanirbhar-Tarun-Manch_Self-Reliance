package com.ninkuk.atmanirbharbharat_tarunmanch.ui.business

import android.app.Application
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.*
import com.ninkuk.atmanirbharbharat_tarunmanch.data.Business
import com.ninkuk.atmanirbharbharat_tarunmanch.data.BusinessDatabase
import com.ninkuk.atmanirbharbharat_tarunmanch.data.BusinessRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BusinessViewModel(application: Application) : AndroidViewModel(application) {
    suspend fun existsInDB(id: String): Boolean {
        val businessDao = BusinessDatabase.getDatabase(getApplication()).businessDao()
        val repository = BusinessRepository(businessDao)

        return repository.getBusinessById(id) != null
    }

    private val _businessProfile = MutableLiveData<List<Business>>()
    val businessProfile: LiveData<List<Business>> = _businessProfile

    init {

    }
}