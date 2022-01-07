package com.example.smartplaylist.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.smartplaylist.ApiInterface
import com.example.smartplaylist.R
import com.example.smartplaylist.YTData
import com.example.smartplaylist.data.Song
import com.example.smartplaylist.databinding.FragmentAddSongBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AddSongFragment : DialogFragment() {

    private var _binding1: FragmentAddSongBinding? = null
    private val binding get() = _binding1!!

    private var channelName: String = ""
    private var titleName: String = ""

    private lateinit var viewModel: PlaylistViewModel

    private val api_key = "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"
    private val str = "nfs8NYg7yQM"
    private val  part = "snippet,statistics"
    private val fields = "items(id,snippet,statistics)"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding1 = FragmentAddSongBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(PlaylistViewModel::class.java)
        return binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.result.observe(viewLifecycleOwner, {
            val message = if (it == null){
                getString(R.string.songadded)
            }else{
                getString(R.string.error, it.message)
            }
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            dismiss()
        })

        binding.buttonAddSong.setOnClickListener{
            val link = binding.textViewLink.text.toString().trim()
            if (link.isEmpty()){
                binding.textViewLink.error = "This field is required"
                return@setOnClickListener
            }
            val linkLength = link.length
            val videoid = link.slice(linkLength - 11..linkLength - 1)
            getMyData(videoid)


        }
    }

    fun getMyData(videoid: String){
        var url = "https://www.googleapis.com/youtube/v3/videos?id=" + videoid + "&key=AIzaSyClQRybPNCwvDhk2RjT5Q7zVizvlWaSRZk&part=snippet,statistics&fields=items(id,snippet,statistics)"
        Log.d("url:", url)
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
        val retrofitData = retrofitBuilder.getData(url)
        retrofitData.enqueue(object : Callback<YTData?> {
            override fun onResponse(call: Call<YTData?>, response: Response<YTData?>) {
                val resbody = response?.body()
                val build = StringBuilder()
                if (resbody != null) {
                    channelName = resbody.items[0].snippet.channelTitle
                    titleName = resbody.items[0].snippet.title
                    var song = Song()
                    song.songName = titleName
                    song.artistName = channelName
                    song.numberOfVotes = "1"
                    viewModel.addSong(song)

                }
            }
            override fun onFailure(call: Call<YTData?>, t: Throwable) {
                Log.d("AddSongFragment", "onFailure: "+ t.message)
            }
        })
    }

}


