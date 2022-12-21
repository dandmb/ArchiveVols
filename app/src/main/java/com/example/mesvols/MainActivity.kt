package com.example.mesvols

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.Observer

import com.example.mesvols.modelviews.FlightViewModel
import com.example.mesvols.modelviews.MainViewModel
import java.util.ArrayList


class MainActivity : AppCompatActivity() {
    //private lateinit var viewModel: MainViewModel
    //private lateinit var viewModelFlight: FlightViewModel
    //val airportNamesList = ArrayList<String>()
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        val btnDatePicker = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        val showDatetv=findViewById<TextView>(R.id.selectedDate)
        val spinner=findViewById<Spinner>(R.id.spinner)


        viewModelFlight = ViewModelProviders.of(this).get(FlightViewModel::class.java)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.airportData.observe(this, Observer
        {
            val airportNames = StringBuilder()
            for (airport in it) {
                //airportNames .append(airport.name)
                  //  .append("\n")
                airportNamesList.add(airport.name)
                //Log.i("Airports","${airport.name} ${airport.country}")
            }
            val arrayAdapter=ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,airportNamesList)
            spinner.adapter=arrayAdapter
            //message.text = monsterNames
        })
        spinner.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(applicationContext,"Selected airport is ${airportNamesList[position]}",Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
        // when floationg acition button is clicked
        btnDatePicker.setOnClickListener {

            // Initiation date picker with
            // MaterialDatePicker.Builder.datePicker()
            // and building it using build()
            val datePicker = MaterialDatePicker.Builder.dateRangePicker().build()
            datePicker.show(supportFragmentManager, "DatePicker")

            // Setting up the event for when ok is clicked
            datePicker.addOnPositiveButtonClickListener {
                Toast.makeText(this, "${datePicker.headerText} is selected", Toast.LENGTH_LONG).show()
                showDatetv.text= datePicker.headerText

            }

            // Setting up the event for when cancelled is clicked
            datePicker.addOnNegativeButtonClickListener {
                Toast.makeText(this, "${datePicker.headerText} is cancelled", Toast.LENGTH_LONG).show()
            }

            // Setting up the event for when back button is pressed
            datePicker.addOnCancelListener {
                Toast.makeText(this, "Date Picker Cancelled", Toast.LENGTH_LONG).show()
            }
        }
        */

    }
}