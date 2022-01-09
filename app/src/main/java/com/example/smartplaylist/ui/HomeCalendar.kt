package com.example.smartplaylist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import com.example.smartplaylist.databinding.FragmentHomeCalendarBinding
import java.text.SimpleDateFormat
import java.util.*

class HomeCalendar : Fragment() {
    private var datekey: String = ""

    lateinit var binding: FragmentHomeCalendarBinding

    val adapter =  EventAdapter()

    lateinit var viewModel: EventlistViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeCalendarBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(EventlistViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter.setListener {
            activity?.supportFragmentManager?.commit {
                replace(this@HomeCalendar.id, SongsFragment(adapter.eventID!!), "")
                addToBackStack(null)
            }
        }

        binding.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->

            var dateID = "$dayOfMonth-$month-$year"
            datekey = dateID
            adapter.clearEvents()
            viewModel.getRealTimeUpdate(dateID)
        }


        binding.button.setOnClickListener {

            AddEventFragment(datekey).show(childFragmentManager, "")
        }

        binding.recyclerView.adapter = adapter
        val sdf = SimpleDateFormat("dd-MM-yyyy")
        var currentDate = sdf.format(Date())

        var date : Int = currentDate.substring(0,2).toInt()
        var month : Int = currentDate.substring(3,5).toInt()
        var year : Int = currentDate.substring(6).toInt()
        month-=1

        currentDate = "$date-$month-$year"
        datekey = currentDate
        viewModel.getRealTimeUpdate(currentDate)

        viewModel.event.observe(viewLifecycleOwner, {

            adapter.addEvent(it)
        })
    }

}