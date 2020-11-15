package com.ninkuk.atmanirbharbharat_tarunmanch.ui.favorites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.ninkuk.atmanirbharbharat_tarunmanch.data.Business
import com.ninkuk.atmanirbharbharat_tarunmanch.data.BusinessDatabase
import com.ninkuk.atmanirbharbharat_tarunmanch.data.BusinessRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {

    public val readAllData: LiveData<List<Business>>
    private val repository: BusinessRepository

    init {
        val businessDao = BusinessDatabase.getDatabase(application).businessDao()
        repository = BusinessRepository(businessDao)
        readAllData = repository.readAllData
    }

    fun addBusiness(business: Business) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addBusiness(business)
        }
    }

    fun deleteBusiness(business: Business) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(business)
        }
    }
}