package com.example.mesvols.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.mesvols.R
import com.example.mesvols.modelviews.MainViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.lifecycle.Observer

class HomeFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    var firstDate:Long = 0
    var secondDate:Long=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // To remove back button on appbar
        (requireActivity() as AppCompatActivity).run {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }


        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_home, container, false)

        val btnDatePicker = view.findViewById<FloatingActionButton>(R.id.floatingActionButton)
        val showDatetv=view.findViewById<TextView>(R.id.selectedDate)
        val spinner=view.findViewById<Spinner>(R.id.spinner)
        val airportNamesList = ArrayList<String>()
        val icaoValueList=ArrayList<String>()
        lateinit var icaoToUse:String

        val btnsubmit=view.findViewById<Button>(R.id.btnSubmit)
        val radioGroup=view.findViewById<RadioGroup>(R.id.radioGroup)



        btnsubmit.setOnClickListener{
            val selectedId = radioGroup.checkedRadioButtonId
            val radioButton = view.findViewById<RadioButton>(selectedId);
            val action= HomeFragmentDirections.actionHomeFragmentToFlightListFragment(icaoToUse,radioButton.text.toString(),firstDate,secondDate)
            //Log.i(LOG_TAG,radioButton.text.toString())
            //Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_listVolFragment)
            view.findNavController().navigate(action)
        }
        // when floationg acition button is clicked
        btnDatePicker.setOnClickListener {

            // Initiation date picker with
            // MaterialDatePicker.Builder.datePicker()
            // and building it using build()
            val datePicker = MaterialDatePicker.Builder.dateRangePicker().build()
            datePicker.show(childFragmentManager, "DatePicker")

            // Setting up the event for when ok is clicked
            datePicker.addOnPositiveButtonClickListener {

                var first=it.first.toString()
                first = first.substring(0, Math.min(first.length, 10))
                var second=it.second.toString()
                second = second.substring(0, Math.min(second.length, 10))

                //Log.i(LOG_TAG,first)
                //Log.i(LOG_TAG,second)
                firstDate=first.toLong()
                secondDate=second.toLong()


                Toast.makeText(this@HomeFragment.requireContext(), "${datePicker.headerText} is selected", Toast.LENGTH_SHORT).show()
                showDatetv.text= datePicker.headerText

            }

            // Setting up the event for when cancelled is clicked
            datePicker.addOnNegativeButtonClickListener {

                Toast.makeText(this@HomeFragment.requireContext(), "${datePicker.headerText} is cancelled", Toast.LENGTH_SHORT).show()
            }

            // Setting up the event for when back button is pressed
            datePicker.addOnCancelListener {
                Toast.makeText(this@HomeFragment.requireContext(), "Date Picker Cancelled", Toast.LENGTH_SHORT).show()
            }
        }


        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.airportData.observe(viewLifecycleOwner, Observer
        {
            val airportNames = StringBuilder()
            for (airport in it) {
                //airportNames .append(airport.name)
                //  .append("\n")
                airportNamesList.add(airport.name)
                icaoValueList.add(airport.icao)
                //Log.i("Airports","${airport.name} ${airport.country}")
            }
            val arrayAdapter= ArrayAdapter<String>(activity as Context,android.R.layout.simple_spinner_dropdown_item,airportNamesList)
            spinner.adapter=arrayAdapter
        })

        spinner.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(this@HomeFragment.requireContext(),"Selected airport is ${airportNamesList[position]}",
                    Toast.LENGTH_SHORT).show()
                icaoToUse=icaoValueList[position]

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

        return view
    }

}