package com.example.hw_41.ui.fragments.note

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hw_41.App
import com.example.hw_41.ui.adapters.Adapter
import com.example.hw_41.R
import com.example.hw_41.data.model.Note
import com.example.hw_41.databinding.FragmentNoteListBinding
import com.example.hw_41.utils.PreferenceHelper
import com.example.hw_41.intetface.OnClickItem

class ListNoteFragment : Fragment(), OnClickItem {

    private lateinit var binding: FragmentNoteListBinding
    private lateinit var noteAdapter: Adapter
    private val sharedPreferences = PreferenceHelper()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences.init(requireContext())

        val isLinearLayout = sharedPreferences.isLinearLayout()
        setRecyclerViewLayout(isLinearLayout)
        noteAdapter = Adapter(this, this)
        initialize()
        setupListener()
        getData()
    }

    private fun initialize() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = noteAdapter
        }
    }

    private fun setRecyclerViewLayout(isLinearLayout: Boolean) {
        binding.recyclerView.layoutManager = if (isLinearLayout) {
            LinearLayoutManager(requireContext())
        } else {
            GridLayoutManager(requireContext(), 2)
        }
    }

    private fun setupListener() = with(binding) {
        fabAddNote.setOnClickListener {
            findNavController().navigate(R.id.action_noteListFragment_to_addNoteFragment)
        }
        btnChange.setOnClickListener {

            val isCurrentlyLinear = binding.recyclerView.layoutManager is LinearLayoutManager
            setRecyclerViewLayout(!isCurrentlyLinear)

            sharedPreferences.setLinearLayout(!isCurrentlyLinear)
        }
    }

    private fun getData() {
        App.appDataBase?.noteDao()?.getAll()?.observe(viewLifecycleOwner) { listNote ->
            noteAdapter.submitList(listNote)
        }
    }

    override fun onLongClick(noteModel: Note) {
        val builder = AlertDialog.Builder(requireContext())
        with(builder) {
            setTitle("Удалить заметку?")
            setPositiveButton("Удалить") { _, _ ->
                App.appDataBase?.noteDao()?.deleteNote(noteModel)
                getData()
            }
            setNegativeButton("Отмена") { dialog, _ ->
                dialog.cancel()
            }
            show()
        }
        builder.create()
    }

    override fun onClick(noteModel: Note) {
        val action = ListNoteFragmentDirections.actionNoteListFragmentToAddNoteFragment(noteModel.id)
        findNavController().navigate(action)
    }
}