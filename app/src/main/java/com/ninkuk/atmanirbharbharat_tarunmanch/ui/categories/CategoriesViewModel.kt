package com.ninkuk.atmanirbharbharat_tarunmanch.ui.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.DocumentSnapshot
import com.ninkuk.atmanirbharbharat_tarunmanch.data.Business
import com.ninkuk.atmanirbharbharat_tarunmanch.data.Business.Companion.toBusiness
import com.ninkuk.atmanirbharbharat_tarunmanch.data.FirebaseService

class CategoriesViewModel : ViewModel() {

    private val _businessList = MutableLiveData<ArrayList<Business>>()
    val businessList: LiveData<ArrayList<Business>> = _businessList
    val backupList: ArrayList<Business> = ArrayList()

    private lateinit var startAfterSnapshot: DocumentSnapshot

    private val _listLength = MutableLiveData<Int>()
    val listLength: LiveData<Int> = _listLength

    init {
        _businessList.value = ArrayList()
    }

    suspend fun getBusinessList(category: String) {
        val querySnapshot = FirebaseService.getBusinessesForCategory(category, startAfterSnapshot)
        startAfterSnapshot = querySnapshot.last()

        val tempList = arrayListOf<Business>()
        querySnapshot.forEach {
            tempList.add(it.toBusiness())
        }

        _businessList.value?.addAll(tempList)
        _businessList.postValue(_businessList.value)
        _listLength.postValue(tempList.size)
    }

    suspend fun getInitialBusinessList(category: String) {
        _listLength.postValue(0)
        val querySnapshot = FirebaseService.getBusinessesForCategory(category)
        startAfterSnapshot = querySnapshot.last()

        val tempList = arrayListOf<Business>()
        querySnapshot.forEach {
            tempList.add(it.toBusiness())
        }

        _businessList.value?.addAll(tempList)
        _businessList.postValue(_businessList.value)
        _listLength.postValue(tempList.size)

        backupList.addAll(tempList)
    }
}