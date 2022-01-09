package com.example.smartplaylist.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.smartplaylist.databinding.FragmentSongsBinding

class SongsFragment(private var eventID: Int) : Fragment() {

    private var _binding: FragmentSongsBinding? = null
    private val binding get() = _binding!!

    private val adapter = SongAdapter()
    private lateinit var viewModel: PlaylistViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val toast = Toast.makeText(this.context, "hey $eventID works!!", Toast.LENGTH_SHORT).show()

        _binding = FragmentSongsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(PlaylistViewModel::class.java)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewSongs.adapter = adapter

        binding.plusButton.setOnClickListener{
            AddSongFragment().show(childFragmentManager, "")
        }

        viewModel.getRealTimeUpdate()

        viewModel.song.observe(viewLifecycleOwner, {
            adapter.addSong(it)
        })


        val itemTouchHelper = ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerViewSongs)
    }

    private var simpleCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT.or(ItemTouchHelper.RIGHT)){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            val currentSong = adapter.playlist[position]


            when(direction){
                ItemTouchHelper.RIGHT -> {
                    if (adapter.checkSongSwiped(currentSong.id!!) == 0){
                        currentSong.numberOfVotes = ((currentSong.numberOfVotes!!.toInt()) + 1).toString()
                        viewModel.voteSong(currentSong)
                        adapter.addSongSwiped(currentSong.id!!)
                    }

                }
                ItemTouchHelper.LEFT ->{
                    if (adapter.checkSongSwiped(currentSong.id!!) == 1){
                        currentSong.numberOfVotes = ((currentSong.numberOfVotes!!.toInt()) - 1).toString()
                        viewModel.voteSong(currentSong)
                        adapter.removeSongSwiped(currentSong.id!!)
                    }
                }
            }
            binding.recyclerViewSongs.adapter?.notifyDataSetChanged()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }



}