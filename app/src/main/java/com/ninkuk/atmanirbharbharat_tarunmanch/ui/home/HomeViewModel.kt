package com.ninkuk.atmanirbharbharat_tarunmanch.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ninkuk.atmanirbharbharat_tarunmanch.data.Business
import com.ninkuk.atmanirbharbharat_tarunmanch.data.FirebaseService
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _suggestedBusinesses = MutableLiveData<List<Business>>()
    val suggestedBusiness: LiveData<List<Business>> = _suggestedBusinesses

    init {
        viewModelScope.launch {
            _suggestedBusinesses.value = FirebaseService.getRandomBusinessFromAllCategories()
        }
    }
}