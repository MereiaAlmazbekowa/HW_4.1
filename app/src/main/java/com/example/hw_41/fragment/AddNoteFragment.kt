package com.example.hw_41.fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hw_41.data.Note
import com.example.hw_41.databinding.FragmentNoteAddBinding
import com.example.hw_41.setBackStackData

class AddNoteFragment : Fragment() {

    private lateinit var binding: FragmentNoteAddBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteAddBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListeners()
    }

    private fun setUpListeners() {
        binding.buttonSaveNote.setOnClickListener {
            val title = binding.editTextTitle.text.toString()
            val description = binding.editTextDescription.text.toString()
            val color = getRandomColor()

            val note = Note(
                title = title
            )
            setBackStackData("note_key", note, true)

            setBackStackData("note_key", note, true)
        }
    }

    private fun getRandomColor(): Int {
        val colors = listOf(Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE, Color.CYAN)
        return colors.random()
    }
}