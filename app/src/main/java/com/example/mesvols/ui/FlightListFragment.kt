package com.example.mesvols.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.example.mesvols.LOG_TAG
import com.example.mesvols.MainRecyclerAdapter
import com.example.mesvols.R
import com.example.mesvols.modelviews.FlightViewModel
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.slidingpanelayout.widget.SlidingPaneLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.flightapp2022.FlightModel

class FlightListFragment : Fragment(),
    MainRecyclerAdapter.FlightItemListener {

    private lateinit var viewModelFlight: FlightViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeLayout: SwipeRefreshLayout
    private lateinit var navController: NavController
    private  lateinit var slidingPaneLayout: SlidingPaneLayout

    val args: FlightListFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val airPname=args.airportName
        val mode=args.radioSelection.lowercase()
        val fDate=args.startDate
        val eDate=args.endDate


        val view = inflater.inflate(R.layout.fragment_flight_list, container, false)


        recyclerView=view.findViewById(R.id.recyclerView)
        slidingPaneLayout=view.findViewById(R.id.sliding_pane_layout)
        //Back Press button
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            FlightListOnBackPressedCallback(slidingPaneLayout)
        )


        swipeLayout = view.findViewById(R.id.swipeLayout)

        swipeLayout.setOnRefreshListener {
            viewModelFlight.getData(airPname,mode,fDate,eDate)
        }


        navController = Navigation.findNavController(
            requireActivity(), R.id.nav_host
        )



        viewModelFlight= ViewModelProviders.of(requireActivity()).get(FlightViewModel::class.java)
        //Log.i(LOG_TAG,"$airPname $mode $fDate $eDate")

        viewModelFlight.getData(airPname,mode,fDate,eDate)


        viewModelFlight.myDataList.observe(viewLifecycleOwner, Observer
        {
            //val myList:List<FlightModel> = it
            val adapter = MainRecyclerAdapter(requireContext(), it,this)
            //println("In volFrag ${myList.size}")
            recyclerView.adapter = adapter
            swipeLayout.isRefreshing = false
            viewModelFlight.icaoValue.value=it.get(0).icao24
            viewModelFlight.icaoValue.value?.let { it1 -> viewModelFlight.getMapPoint(it1) }
            println("First icao ${viewModelFlight.icaoValue.value}")
            /*val monsterNames = StringBuilder()
            for (monster in it) {
                monsterNames.append(monster.estArrivalAirport)
                    .append("\n")
            }*/
            //message.text = monsterNames
            //Log.i(LOG_TAG,monsterNames.toString())
            //Log.i(LOG_TAG,"My data : $airPname and $mode and $fDate and $eDate")

            //view.slid
        })

        // Inflate the layout for this fragment
        return view
    }
    override fun onFlightItemClick(flight: FlightModel) {
        Log.i(LOG_TAG, "Selected monster: ${flight.icao24}")

        viewModelFlight.icaoValue.value=flight.icao24

        slidingPaneLayout.openPane()
    }

}

class FlightListOnBackPressedCallback(
    private val slidingPaneLayout: SlidingPaneLayout
): OnBackPressedCallback(slidingPaneLayout.isSlideable && slidingPaneLayout.isOpen),
    SlidingPaneLayout.PanelSlideListener{

    init {
        slidingPaneLayout.addPanelSlideListener(this)
    }

    override fun handleOnBackPressed() {
        slidingPaneLayout.closePane()
    }

    override fun onPanelSlide(panel: View, slideOffset: Float) {
    }

    override fun onPanelOpened(panel: View) {
        isEnabled = true
    }

    override fun onPanelClosed(panel: View) {
        isEnabled = false
    }
}