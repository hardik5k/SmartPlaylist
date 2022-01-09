package com.example.smartplaylist.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.smartplaylist.data.NODE_EVENTS
import com.example.smartplaylist.data.Event
import com.google.firebase.FirebaseApp
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase

class EventlistViewModel: ViewModel() {
    private val db = FirebaseDatabase.getInstance("https://smartplaylist-f0fa4-default-rtdb.firebaseio.com/").getReference(NODE_EVENTS)

    private val _result = MutableLiveData<Exception?>()
    val result: LiveData<Exception?> get() = _result

    private val _event = MutableLiveData<Event>()
    val event: LiveData<Event> get() = _event

    fun addEvent(event: Event){

        println("view model")

        event.eventID = db.push().key
        db.child(event.eventID!!).setValue(event).addOnCompleteListener {
            if (it.isSuccessful) {
                _result.value = null
            } else {
                _result.value = it.exception
            }
        }
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

    fun getRealTimeUpdate() {
        db.addChildEventListener(childEventListener)
    }

    override fun onCleared() {
        super.onCleared()
        db.removeEventListener(childEventListener)
    }
}