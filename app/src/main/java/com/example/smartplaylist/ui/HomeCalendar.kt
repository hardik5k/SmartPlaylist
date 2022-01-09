package com.example.smartplaylist.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import com.example.smartplaylist.R
import com.example.smartplaylist.databinding.FragmentHomeCalendarBinding
import com.example.smartplaylist.data.Event
import java.text.SimpleDateFormat
import java.util.*

class HomeCalendar : Fragment() {


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
            adapter.clearEvents()
            viewModel.getRealTimeUpdate(dateID)
        }


        binding.button.setOnClickListener {

            AddEventFragment().show(childFragmentManager, "")
        }

        binding.recyclerView.adapter = adapter
        val sdf = SimpleDateFormat("d-M-yyyy")
        val currentDate = sdf.format(Date())
        Log.d("date", currentDate)
        viewModel.getRealTimeUpdate(currentDate)

        viewModel.event.observe(viewLifecycleOwner, {

            adapter.addEvent(it)
        })
    }

}