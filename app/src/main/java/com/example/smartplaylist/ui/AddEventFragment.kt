package com.example.smartplaylist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.smartplaylist.R
import com.example.smartplaylist.data.Event
import com.example.smartplaylist.databinding.FragmentAddEventBinding
import com.example.smartplaylist.databinding.FragmentHomeCalendarBinding

class AddEventFragment : DialogFragment() {

    lateinit var binding: FragmentAddEventBinding
    lateinit var viewModel: EventlistViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddEventBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(EventlistViewModel::class.java)
        return binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.result.observe(viewLifecycleOwner, {
            val message = if (it == null){
                getString(R.string.eventcreated)
            }else{
                getString(R.string.error, it.message)
            }
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            dismiss()
        })

        binding.buttonAddEvent.setOnClickListener{
            val name = binding.textViewName.text.toString().trim()
            val desc = binding.textViewDesc.text.toString().trim()
            if (name.isEmpty()){
                binding.textViewName.error = "This field is required"
                return@setOnClickListener
            }
            if (desc.isEmpty()){
                binding.textViewDesc.error = "This field is required"
                return@setOnClickListener
            }

            val event = Event()
            event.eventName  = name
            event.eventDescription = desc
            viewModel.addEvent(event)
        }
    }
}