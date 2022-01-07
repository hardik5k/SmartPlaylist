package com.example.smartplaylist.ui

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.smartplaylist.databinding.RecyclerViewSongBinding
import com.example.smartplaylist.data.Song


class SongAdapter: RecyclerView.Adapter<SongAdapter.ViewHolder>()  {

    var playlist = mutableListOf<Song>()
    private lateinit var sharedPreferences: SharedPreferences

    inner class ViewHolder(val binding: RecyclerViewSongBinding): RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var context = parent.context
        sharedPreferences = context.getSharedPreferences("swipedsongs", MODE_PRIVATE)
        return ViewHolder(RecyclerViewSongBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.textViewSongname.text = playlist[position].songName
        holder.binding.textViewSongname.isSelected = true
        holder.binding.textViewRank.text = (position + 1).toString()
        holder.binding.textViewArtist.text = playlist[position].artistName
        holder.binding.textViewVotes.text = playlist[position].numberOfVotes
        if(checkSongSwiped(playlist[position].id!!) == 1){
            holder.binding.cardViewVotes.setCardBackgroundColor(Color.parseColor("#B0DCFF"));
        }
        if(checkSongSwiped(playlist[position].id!!) == 0){
            holder.binding.cardViewVotes.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
        }
    }

    override fun getItemCount(): Int {
        return playlist.size
    }

    fun addSong(song: Song){
        val index = playlist.indexOfFirst { it.id == song.id}
        if (index == -1){
            playlist.add(song)
        }
        else{
            playlist[index] = song
        }

        //sort the playlist according to number of votes
        playlist.sortByDescending{
            it.numberOfVotes?.toInt()
        }

        notifyDataSetChanged()
    }
    fun addSongSwiped(id : String ){
        var editor = sharedPreferences.edit()
        editor.putInt(id, 1)
        editor.commit()
    }
    fun checkSongSwiped(id :String): Int {
        return sharedPreferences.getInt(id, 0)
    }
    fun removeSongSwiped(id :String) {
        var editor = sharedPreferences.edit()
        editor.putInt(id, 0)
        editor.commit()
    }

}