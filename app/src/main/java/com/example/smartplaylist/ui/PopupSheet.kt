package com.example.smartplaylist.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class PopupSheet: BottomSheetDialogFragment() {
    var SongName: String = ""
    var ArtistName: String = ""
    var likesCount: String = ""
    var viewCount: String = ""
    var URL: String = ""
    var flag: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View =
            inflater.inflate(com.example.smartplaylist.R.layout.popupsheet, container, false)
        var editSongName =
            view.findViewById<View>(com.example.smartplaylist.R.id.text_view_popSongName) as TextView
        var editArtistName =
            view.findViewById<View>(com.example.smartplaylist.R.id.text_view_popArtistName) as TextView
        var editViewCount =
            view.findViewById<View>(com.example.smartplaylist.R.id.text_view_popViewCount) as TextView
        var editLikesCount =
            view.findViewById<View>(com.example.smartplaylist.R.id.text_view_popLikesCount) as TextView

        view.findViewById<View>(com.example.smartplaylist.R.id.button).setOnClickListener {
                v: View ->
            val i = Intent(Intent.ACTION_VIEW, Uri.parse(URL))
            startActivity(i)
        }

        editSongName.setText(SongName)
        editArtistName.setText(ArtistName)
        editLikesCount.setText("Likes Count: " + likesCount)
        editViewCount.setText("View Count: " + viewCount)


        //Toast.makeText(getContext(),editArtistName.getText(), Toast.LENGTH_SHORT).show()
        return view

    }
}