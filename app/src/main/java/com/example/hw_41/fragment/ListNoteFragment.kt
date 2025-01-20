package com.example.hw_41.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hw_41.adapter.Adapter
import com.example.hw_41.data.Note
import com.example.hw_41.R
import com.example.hw_41.databinding.FragmentNoteListBinding

class ListNoteFragment : Fragment() {

    private lateinit var binding: FragmentNoteListBinding
    private lateinit var noteAdapter: Adapter
    private var list: ArrayList<Note> = ArrayList()

    private val sharedPreferences by lazy {
        requireActivity().getSharedPreferences("notes_prefs", Context.MODE_PRIVATE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteAdapter = Adapter()
        initialize()
        setUpListeners()
    }

    private fun initialize() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = noteAdapter
        }
    }

    private fun setUpListeners() = with(binding) {
        fabAddNote.setOnClickListener {
            findNavController().navigate(R.id.action_noteListFragment_to_addNoteFragment)
        }
    }
}