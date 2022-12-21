package com.example.mesvols.data

data class FlightTrackModel(
    val callsign: String,
    val endTime: Int,
    val icao24: String,
    val path: List<List<Any>>,
    val startTime: Int
)