package com.example.smartplaylist.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.smartplaylist.data.Song
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase

class PlaylistViewModel: ViewModel() {
    private var db = FirebaseDatabase.getInstance("https://smartplaylist-f0fa4-default-rtdb.firebaseio.com/").getReference("events")

    private val _result = MutableLiveData<Exception?>()
    val result: LiveData<Exception?> get() = _result

    private val _song = MutableLiveData<Song>()
    val song: LiveData<Song> get() = _song

    fun addSong(song: Song, eventID: String){
        var ref = db.child(eventID)
        song.id = ref.push().key
        ref.child(song.id!!).setValue(song).addOnCompleteListener {
            if (it.isSuccessful) {
                _result.value = null
            } else {
                _result.value = it.exception
            }
        }
    }

    private val childEventListener = object : ChildEventListener{
        override fun onChildAdded(snapshot: DataSnapshot, p1: String?) {
            val song = snapshot.getValue(Song::class.java)
            song?.id = snapshot.key
            _song.value = song!!

        }

        override fun onChildChanged(snapshot: DataSnapshot, p1: String?) {
            val song = snapshot.getValue(Song::class.java)
            song?.id = snapshot.key
            _song.value = song!!
        }

        override fun onChildRemoved(p0: DataSnapshot) {}

        override fun onChildMoved(p0: DataSnapshot, p1: String?) {}

        override fun onCancelled(p0: DatabaseError) {}

    }

    fun getRealTimeUpdate(eventID: String) {
        db = db.child(eventID)
        db.addChildEventListener(childEventListener)
    }

    fun voteSong(song: Song){
        db.child(song.id!!).setValue(song)
            .addOnCompleteListener{
                if (it.isSuccessful){
                    _result.value = null
                }
                else{
                    _result.value = it.exception
                }
            }
    }



    override fun onCleared() {
        super.onCleared()
        db.removeEventListener(childEventListener)
    }


}