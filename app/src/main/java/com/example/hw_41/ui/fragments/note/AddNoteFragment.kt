package com.example.hw_41.ui.fragments.note

import android.app.AlertDialog
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.hw_41.App
import com.example.hw_41.R
import com.example.hw_41.data.model.Note
import com.example.hw_41.databinding.FragmentNoteAddBinding
import java.text.SimpleDateFormat
import java.util.Date

class AddNoteFragment : Fragment() {

    private lateinit var binding: FragmentNoteAddBinding

    private var noteId: Int? = null
    private var color: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteAddBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvData.text = getCurrentTime()
        updateNote()
        setupListener()
    }

    private fun updateNote() {
        arguments?.let { args ->
            noteId = args.getInt("noteId", -1)
        }
        if (noteId != -1) {
            val note = App.appDataBase?.noteDao()?.getNoteById(noteId!!)
            note?.let { item ->
                binding.etTitle.setText(item.title)
                binding.etDescription.setText(item.description)
                binding.tvData.text = item.data
            }
        }
    }

    private fun setupListener() = with(binding) {
        triTochki.setOnClickListener {
            showColorDialog()
        }
        imgBack.setOnClickListener {
            findNavController().navigateUp()
        }
        tvGotovo.setOnClickListener {
            val title = etTitle.text.toString()
            val text = etDescription.text.toString()
            val data = tvData.text.toString()
            val color = color
            if (noteId != -1) {
                val updateNote = Note(title, text, data, color.hashCode())
                updateNote.id = noteId!!
                App.appDataBase?.noteDao()?.insertNote(updateNote)

            } else {
                App.appDataBase?.noteDao()?.insertNote(Note(title, text, data, color.hashCode()))
            }
            findNavController().navigateUp()
        }
    }

    private fun showColorDialog() {
        val builder = AlertDialog.Builder(requireContext())
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.color_picker, null)
        builder.setView(dialogView)

        val dialog = builder.create()

        dialogView.findViewById<View>(R.id.color_yellow2).setOnClickListener {
            color = ContextCompat.getColor(requireContext(), R.color.yellow2)
            dialog.dismiss()
        }
        dialogView.findViewById<View>(R.id.color_purple2).setOnClickListener {
            color = ContextCompat.getColor(requireContext(), R.color.purple2)
            dialog.dismiss()
        }
        dialogView.findViewById<View>(R.id.color_pink2).setOnClickListener {
            color = ContextCompat.getColor(requireContext(), R.color.pink2)
            dialog.dismiss()
        }
        dialogView.findViewById<View>(R.id.color_red2).setOnClickListener {
            color = ContextCompat.getColor(requireContext(), R.color.red)
            dialog.dismiss()
        }
        dialogView.findViewById<View>(R.id.color_green2).setOnClickListener {
            color = ContextCompat.getColor(requireContext(), R.color.green2)
            dialog.dismiss()
        }
        dialogView.findViewById<View>(R.id.color_blue2).setOnClickListener {
            color = ContextCompat.getColor(requireContext(), R.color.blue2)
            dialog.dismiss()
        }
        dialog.show()

        val window = dialog.window
        val layoutParams = window?.attributes

        layoutParams?.gravity = Gravity.END or Gravity.TOP
        layoutParams?.x = 100
        layoutParams?.y = 100

        window?.attributes = layoutParams
    }


    private fun getCurrentTime(): String {
        val date = SimpleDateFormat("dd MMMM HH:mm")
        return date.format(Date())
    }
}