package com.example.smartplaylist.data

import com.google.firebase.database.Exclude

data class Event(
    @get:Exclude
    var eventID: String? = null,
    var eventName: String? = null,
    var eventDescription: String? = null,
)
