package com.example.smartplaylist.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.smartplaylist.databinding.RecyclerViewSongBinding
import com.example.smartplaylist.data.Song


class SongAdapter: RecyclerView.Adapter<SongAdapter.ViewHolder>()  {

    var playlist = mutableListOf<Song>()

    inner class ViewHolder(val binding: RecyclerViewSongBinding): RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RecyclerViewSongBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.textViewSongname.text = playlist[position].songName
        //holder.binding.textViewSongname.isSelected = true
        holder.binding.textViewRank.text = (position + 1).toString()
        holder.binding.textViewArtist.text = playlist[position].artistName
        holder.binding.textViewVotes.text = playlist[position].numberOfVotes
    }

    override fun getItemCount(): Int {
        return playlist.size
    }

    fun addSong(song: Song){
        if (!playlist.contains((song))){
            playlist.add(song)
        }
        else{
            val index = playlist.indexOf(song)
            playlist[index] = song
        }

        playlist.sortByDescending{
            it.numberOfVotes
        }


        notifyDataSetChanged()
    }


}