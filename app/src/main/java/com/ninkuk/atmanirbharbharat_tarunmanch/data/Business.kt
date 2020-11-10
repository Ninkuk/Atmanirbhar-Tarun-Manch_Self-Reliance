package com.ninkuk.atmanirbharbharat_tarunmanch.data

data class Business(
    val businessName: String,
    val description: String = "",
    val locationAddress: String,
    val emailAddress: String,
    val owners: List<String>,
    val phoneNumbers: List<String>
)