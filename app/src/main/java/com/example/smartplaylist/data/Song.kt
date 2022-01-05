package com.example.smartplaylist.data

import com.google.firebase.database.Exclude

data class Song(
    @get:Exclude
    var id: String? = null,
    var rank: String? = null,
    var songName: String? = null,
    var artistName: String? = null,
    var numberOfVotes: String? = null,
){

}
