package com.example.mesvols

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.flightapp2022.FlightModel
import com.example.mesvols.utilities.Utils
import java.util.*

class MainRecyclerAdapter (val context: Context,
                           val flights: List<FlightModel>,
                           val itemListener: FlightItemListener
):
    RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder>(){


    override fun getItemCount() :Int{
        return flights.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.flight_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val flight = flights[position]
        with(holder) {
            volNom?.text = flight.icao24
            volDepart?.text=flight.estDepartureAirport
            var convertDeparturetime: String? =convertLongToTime(flight.firstSeen!!)
            dateDepart?.text= convertDeparturetime
            volDestination?.text=flight.estArrivalAirport
            var convertArrivaltime: String? =convertLongToTime(flight.lastSeen!!)
            dateDestination?.text=convertArrivaltime
            var getFlightduration=flight.lastSeen-flight.firstSeen
            var convertFlightduration: String? = Utils.formatFlightDuration(getFlightduration)
            volDuration.text=convertFlightduration

        }
        //OnClick of list items
        holder.itemView.setOnClickListener{
            itemListener.onFlightItemClick(flight)
        }
    }


    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val volNom = itemView.findViewById<TextView>(R.id.volNom)
        val volDepart = itemView.findViewById<TextView>(R.id.volDepart)
        val dateDepart = itemView.findViewById<TextView>(R.id.dateDepart)
        val volDestination = itemView.findViewById<TextView>(R.id.volDestination)
        val dateDestination = itemView.findViewById<TextView>(R.id.dateDestination)
        val volDuration=itemView.findViewById<TextView>(R.id.tvDuration)
    }
    interface FlightItemListener {
        fun onFlightItemClick(flight: FlightModel)
    }

    fun convertLongToTime(time: Long): String {
        var myTime:String="%02d:%02d".format(Date(time * 1000).hours, Date(time * 1000).minutes)

        return myTime
    }

}