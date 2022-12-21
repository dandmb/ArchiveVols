package com.example.mesvols.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.mesvols.MainRecyclerAdapter
import com.example.mesvols.R
import com.example.mesvols.modelviews.FlightViewModel
import androidx.lifecycle.Observer
import com.example.flightapp2022.FlightModel
import com.example.mesvols.LOG_TAG
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapFragment : Fragment(),OnMapReadyCallback {

    private lateinit var mMap:GoogleMap
    private lateinit var navController: NavController
    private lateinit var viewModelFlight: FlightViewModel
    //val args: MapFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
        val mapFragment = supportFragmentManager.findFragmentById(
            R.id.map_fragment
        ) as? SupportMapFragment
        mapFragment?.getMapAsync { googleMap ->
            addMarkers(googleMap)
        }
        */

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_map, container, false)

        setHasOptionsMenu(true)
        navController = Navigation.findNavController(
            requireActivity(), R.id.nav_host
        )

        (requireActivity() as AppCompatActivity).run {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        viewModelFlight= ViewModelProviders.of(requireActivity()).get(FlightViewModel::class.java)

        if (viewModelFlight.icaoValue.value == null){
            viewModelFlight.myTrackDataObject.observe(viewLifecycleOwner, Observer
            {
                //val myList:List<FlightModel> = it

                val myList:List<List<Any>> = it.path


                //println("In volFrag ${myList.size}")

                /*val monsterNames = StringBuilder()
                for (monster in it) {
                    monsterNames.append(monster.estArrivalAirport)
                        .append("\n")
                }*/
                //message.text = monsterNames
                Log.i(LOG_TAG,myList.size.toString())
                //Log.i(LOG_TAG,"My data : $airPname and $mode and $fDate and $eDate")

                //view.slid
            })
        }else{

            viewModelFlight.icaoValue.value?.let { viewModelFlight.getMapPoint(it) }
            viewModelFlight.myTrackDataObject.observe(viewLifecycleOwner, Observer
            {
                //val myList:List<FlightModel> = it

                val myList:List<List<Any>> = it.path


                //println("In volFrag ${myList.size}")

                /*val monsterNames = StringBuilder()
                for (monster in it) {
                    monsterNames.append(monster.estArrivalAirport)
                        .append("\n")
                }*/
                //message.text = monsterNames
                Log.i(LOG_TAG,myList.size.toString())
                //Log.i(LOG_TAG,"My data : $airPname and $mode and $fDate and $eDate")

                //view.slid
            })
        }


        return view
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item?.itemId == android.R.id.home) {
            navController.navigateUp()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        if (googleMap != null) {
            mMap=googleMap

            val sydney=LatLng(-34.0,151.0)
            mMap.addMarker(MarkerOptions().position(sydney).title("In sydney"))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        }

    }

}