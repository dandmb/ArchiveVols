package com.example.mesvols.network

import com.example.flightapp2022.FlightModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL =
    "https://opensky-network.org/api/flights/"

// Track by Aircraft
private const val WEB_SERVICE_URL_Aircraft ="https://opensky-network.org/api/tracks/"

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

/**
 * A public interface that exposes the [getPhotos] method
 */
interface FlightApiService {
    /**
     * Returns a [List] of [MarsPhoto] and this method can be called from a Coroutine.
     * The @GET annotation indicates that the "photos" endpoint will be requested with the GET
     * HTTP method
     */
    @GET("departure")
    suspend fun getDepartureFlight(@Query("airport") airport:String,@Query("begin") begin:Long,@Query("end") end:Long) : List<FlightModel>

    @GET("arrival")
    suspend fun getArrivalFlight(@Query("airport") airport:String,@Query("begin") begin:Long,@Query("end") end:Long) : List<FlightModel>

}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */

object FlightApi {
    val retrofitService: FlightApiService by lazy { retrofit.create(FlightApiService::class.java) }
}