package com.example.smartplaylist.ui

import com.example.smartplaylist.data.Event
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.smartplaylist.R


class EventAdapter(private val context: Context, private val dataset: List<Event>) : RecyclerView.Adapter<EventAdapter.EventViewHolder>()  {

    class EventViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        lateinit var id: String
        val title: TextView = view.findViewById(R.id.title)
        val desc: TextView = view.findViewById(R.id.desc)
        val button: Button = view.findViewById(R.id.button2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {

        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.event_list_element, parent, false)

        return EventViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {

        val item = dataset[position]

        holder.id = item.eventID
        holder.title.text = item.eventName
        holder.desc.text = item.eventDescription

        holder.button.setOnClickListener {

            // change fragment
            
            
        }
    }

    override fun getItemCount() = dataset.size
}