package com.example.smartplaylist.ui

import android.app.Activity
import com.example.smartplaylist.data.Event
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smartplaylist.R
//import android.R

import android.widget.FrameLayout
import androidx.fragment.app.FragmentActivity


class EventAdapter() : RecyclerView.Adapter<EventAdapter.EventViewHolder>()  {

    var eventList = mutableListOf<Event>()

    private var listener: (() -> Unit)? = null
    fun setListener(listener: (() -> Unit)?) {
        this.listener = listener
    }

    constructor(sampleList: MutableList<Event>) : this() {

        eventList = sampleList
    }

    class EventViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        var id: String? = null
        val title: TextView = view.findViewById(R.id.title)
        val desc: TextView = view.findViewById(R.id.desc)
        val button: Button = view.findViewById(R.id.button2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {

        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.event_list_element, parent, false)

        return EventViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {

        val item = eventList[position]

        holder.id = item.eventID
        holder.title.text = item.eventName
        holder.desc.text = item.eventDescription

        holder.button.setOnClickListener {

            // change fragment

            listener?.invoke()
        }
    }

    override fun getItemCount() = eventList.size


    fun addEvent(event: Event) {

        eventList.add(event)
    }
}