package com.example.smartplaylist.data

import com.google.firebase.database.Exclude

data class Event(
    @get:Exclude
    var eventID: String,
    var eventName: String,
    var eventDescription: String? = null,
)
