package com.example.mesvols.network

import com.example.flightapp2022.FlightModel
import com.example.mesvols.data.FlightTrackModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


// Track by Aircraft
private const val BASE_URL ="https://opensky-network.org/api/tracks/"

/**
 * Build the Moshi object with Kotlin adapter factory that Retrofit will be using.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * The Retrofit object with the Moshi converter.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface FlightTrackApiService {

    @GET("all")
    suspend fun getMapPoints(@Query("icao24") airport:String) : FlightTrackModel
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */

object FlightTrackApi {
    val retrofitServiceTrack: FlightTrackApiService by lazy { retrofit.create(FlightTrackApiService::class.java) }
}