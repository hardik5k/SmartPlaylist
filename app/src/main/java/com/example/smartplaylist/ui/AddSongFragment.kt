package com.example.smartplaylist.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.smartplaylist.ApiInterface
import com.example.smartplaylist.YTData
import com.example.smartplaylist.databinding.FragmentAddSongBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AddSongFragment : DialogFragment() {

    private var _binding1: FragmentAddSongBinding? = null
    private val binding get() = _binding1!!
    private val api_key = "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"
    private val str = "nfs8NYg7yQM"
    private val  part = "snippet,statistics"
    private val fields = "items(id,snippet,statistics)"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth)
        getMyData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding1 = FragmentAddSongBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun getMyData(){
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
        val retrofitData = retrofitBuilder.getData("https://www.googleapis.com/youtube/v3/videos?id=nfs8NYg7yQM&key=AIzaSyClQRybPNCwvDhk2RjT5Q7zVizvlWaSRZk&part=snippet,statistics&fields=items(id,snippet,statistics)")
        retrofitData.enqueue(object : Callback<YTData?> {
            override fun onResponse(call: Call<YTData?>, response: Response<YTData?>) {
                val resbody = response?.body()
                val build = StringBuilder()
                Log.d("hi",resbody.toString())
            }
            override fun onFailure(call: Call<YTData?>, t: Throwable) {
                Log.d("AddSongFragment", "onFailure: "+ t.message)
            }
        })
    }

}


