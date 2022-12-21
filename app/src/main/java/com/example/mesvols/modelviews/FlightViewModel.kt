package com.example.mesvols.modelviews

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flightapp2022.FlightModel
import com.example.mesvols.LOG_TAG
import com.example.mesvols.data.FlightTrackModel
import com.example.mesvols.network.FlightApi
import com.example.mesvols.network.FlightTrackApi
import kotlinx.coroutines.launch

class FlightViewModel : ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<String>()

    // The external immutable LiveData for the request status
    val status: LiveData<String> = _status


    private val _myData=MutableLiveData<List<FlightModel>>()
    val myDataList:LiveData<List<FlightModel>> = _myData

    private val _myTrackData=MutableLiveData<FlightTrackModel>()
    val myTrackDataObject:LiveData<FlightTrackModel> = _myTrackData

    val icaoValue=MutableLiveData<String?>()

    init {
        //getFlightArrive()
        //getFlightDepart()
    }

    fun getMapPoint(icao:String){
        viewModelScope.launch {
            try {
                val objectResult = FlightTrackApi.retrofitServiceTrack.getMapPoints(icao)
                _status.value = "Success: ${objectResult.path} map points retrieved"
                _myTrackData.value=objectResult
                Log.i("Done successfully: Icao","$icao: $_status")
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
                Log.i("ERROR IN ICAO...",_status.value.toString())
            }
        }
    }


    fun getData(airportName:String,mode:String,fDate:Long,eDate:Long){
        if (mode=="departure"){
            viewModelScope.launch {
                try {
                    val listResult = FlightApi.retrofitService.getDepartureFlight(airportName,fDate,eDate)
                    _status.value = "Success: ${listResult.size} Departure flights retrieved"
                    _myData.value=listResult
                    Log.i("Done successfully","$airportName: $_status.value.toString()")
                } catch (e: Exception) {
                    _status.value = "Failure: ${e.message}"
                    Log.i("Something have happened",_status.value.toString())
                }
            }
        }else{
            viewModelScope.launch {
                try {
                    val listResult = FlightApi.retrofitService.getArrivalFlight(airportName,fDate,eDate)
                    _status.value = "Success: ${listResult.size} Arrival flights retrieved"
                    _myData.value=listResult
                    Log.i("Done successfully",_status.value.toString())

                    /*
                    for (number in listResult) {
                        // For each element in the list, execute this code block
                        Log.i(LOG_TAG,number.icao24.toString())
                    }*/
                } catch (e: Exception) {
                    _status.value = "Failure: ${e.message}"
                    Log.i("a big Faillure",_status.value.toString())
                }
            }
        }
    }



    /*
    private fun getFlightArrive() {
        viewModelScope.launch {
            try {
                val listResult = FlightApi.retrofitService.getArrivalFlight("LFPO",1572172110,1572258510)
                _status.value = "Success: ${listResult.size} Departure flights retrieved"

                Log.i("Done successfully",_status.value.toString())

                /*
                for (number in listResult) {
                    // For each element in the list, execute this code block
                    Log.i(LOG_TAG,number.icao24.toString())
                }*/
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
                Log.i("a big Faillure",_status.value.toString())
            }
        }
    }

    private fun getFlightDepart() {
        viewModelScope.launch {
            try {
                val listResult = FlightApi.retrofitService.getDepartureFlight("LFPO",1572172110,1572258510)
                _status.value = "Success: ${listResult.size} Arrival flights retrieved"
                Log.i("Done successfully",_status.value.toString())
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
                Log.i("a big Faillure",_status.value.toString())
            }
        }
    }
    */
}