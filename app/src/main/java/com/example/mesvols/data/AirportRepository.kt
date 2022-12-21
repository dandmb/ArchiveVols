package com.example.mesvols.data

import android.app.Application

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope

import com.example.mesvols.utilities.FileHelper
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.launch


class AirportRepository(val app: Application) {

    val airportData = MutableLiveData<List<AirportItem>>()

    private val listType = Types.newParameterizedType(
        List::class.java, AirportItem::class.java
    )
    init {

        getAirportData()

    }

    fun getAirportData(){
        val text = FileHelper.getTextFromAssets(app ,"airports.json")
        val moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<List<AirportItem>> =
            moshi.adapter(listType)
        airportData.value = adapter.fromJson(text) ?: emptyList()
    }



}