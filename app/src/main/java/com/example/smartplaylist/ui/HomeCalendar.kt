package com.example.smartplaylist.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.smartplaylist.R
import com.example.smartplaylist.databinding.FragmentHomeCalendarBinding
import com.example.smartplaylist.data.Event

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeCalendar.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeCalendar : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var binding: FragmentHomeCalendarBinding

    val adapter =  EventAdapter(mutableListOf(
        Event("1", "Event 1", "Desc 1"),
        Event("2", "Event 2", "Desc 2"),
        Event("3", "Event 3", "Desc 3"),
        Event("4", "Event 4", "Desc 4"),
        Event("5", "Event 5", "Desc 5")))
    lateinit var viewModel: EventlistViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_home_calendar, container, false)

        binding = FragmentHomeCalendarBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(EventlistViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->

            // date change event

            // binding.recyclerView.adapter = EventAdapter(..)

            val toast = Toast.makeText(this.context, "hey $dayOfMonth/$month/$year !!", Toast.LENGTH_SHORT).show()
        }

        binding.button.setOnClickListener {

            // host event func
        }

        binding.recyclerView.adapter = adapter

        viewModel.getRealTimeUpdate()

        viewModel.event.observe(viewLifecycleOwner, {

            adapter.addEvent(it)
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeCalendar.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeCalendar().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}