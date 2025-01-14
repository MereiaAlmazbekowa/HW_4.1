package com.example.hw_41.data

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class Note(
    val title: String,
//    val description: String,
//    val color: Int
) : Serializable