package com.example.hw_41.intetface

import com.example.hw_41.data.model.Note

interface OnClickItem {
    fun onLongClick(noteModel: Note)

    fun onClick(noteModel: Note)
}