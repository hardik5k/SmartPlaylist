package com.example.smartplaylist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.smartplaylist.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PopupSheet: BottomSheetDialogFragment() {
    var ArtistName: String = ""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getView()?.findViewById<TextView>(R.id.text_view_popArtistName)?.text = "Charley"
        return inflater.inflate(R.layout.popupsheet,container,false)
    }
}