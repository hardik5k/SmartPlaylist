package com.example.smartplaylist.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.smartplaylist.R
import com.example.smartplaylist.databinding.FragmentAddSongBinding
import com.example.smartplaylist.databinding.FragmentSongsBinding

class AddSongFragment : DialogFragment() {

    private var _binding1: FragmentAddSongBinding? = null
    private val binding get() = _binding1!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding1 = FragmentAddSongBinding.inflate(inflater, container, false)
        return binding.root
    }


}