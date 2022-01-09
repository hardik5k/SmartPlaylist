package com.example.smartplaylist.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.smartplaylist.data.NODE_EVENTS
import com.example.smartplaylist.data.Event
import com.google.firebase.FirebaseApp
import com.google.firebase.database.*
import java.text.SimpleDateFormat
import java.util.*

class EventlistViewModel: ViewModel() {
    private val db = FirebaseDatabase.getInstance("https://smartplaylist-f0fa4-default-rtdb.firebaseio.com/").getReference()
    private val db1 = FirebaseDatabase.getInstance("https://smartplaylist-f0fa4-default-rtdb.firebaseio.com/").getReference("events")
    private var ref: DatabaseReference = db.child("9-0-2022")

    private val _result = MutableLiveData<Exception?>()
    val result: LiveData<Exception?> get() = _result

    private val _event = MutableLiveData<Event>()
    val event: LiveData<Event> get() = _event

    fun addEvent(event: Event){
        event.eventID = ref.push().key
        ref.child(event.eventID!!).setValue(event).addOnCompleteListener {
            if (it.isSuccessful) {
                _result.value = null
            } else {
                _result.value = it.exception
            }
        }
//        db1.child(event.eventID!!).setValue(event).addOnCompleteListener {
//            if (it.isSuccessful) {
//                _result.value = null
//            } else {
//                _result.value = it.exception
//            }
//        }
    }

    private val childEventListener = object : ChildEventListener{
        override fun onChildAdded(snapshot: DataSnapshot, p1: String?) {
            val event = snapshot.getValue(Event::class.java)
            event?.eventID = snapshot.key
            _event.value = event!!

        }

        override fun onChildChanged(snapshot: DataSnapshot, p1: String?) {
            val event = snapshot.getValue(Event::class.java)
            event?.eventID = snapshot.key
            _event.value = event!!
        }

        override fun onChildRemoved(p0: DataSnapshot) {}

        override fun onChildMoved(p0: DataSnapshot, p1: String?) {}

        override fun onCancelled(p0: DatabaseError) {}

    }

    fun getRealTimeUpdate(dateID: String) {
        ref = db.child(dateID)
        ref.addChildEventListener(childEventListener)
    }

    override fun onCleared() {
        super.onCleared()
        db.removeEventListener(childEventListener)
    }
}