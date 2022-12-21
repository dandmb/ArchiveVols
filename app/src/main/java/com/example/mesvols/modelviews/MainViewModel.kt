package com.example.mesvols.modelviews

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.mesvols.data.AirportRepository


class MainViewModel(app:Application): AndroidViewModel(app) {

    private val airportRepo = AirportRepository(app)
    val airportData = airportRepo.airportData

}