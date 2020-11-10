package com.ninkuk.atmanirbharbharat_tarunmanch.ui.business

import androidx.lifecycle.*
import com.ninkuk.atmanirbharbharat_tarunmanch.data.Business
import com.ninkuk.atmanirbharbharat_tarunmanch.data.FirebaseService
import kotlinx.coroutines.launch

class BusinessViewModel : ViewModel() {
    private val _businessProfile = MutableLiveData<List<Business>>()
    val businessProfile: LiveData<List<Business>> = _businessProfile

    init {
        viewModelScope.launch {
            _businessProfile.value = FirebaseService.getAllBusinesses()
        }
    }
}