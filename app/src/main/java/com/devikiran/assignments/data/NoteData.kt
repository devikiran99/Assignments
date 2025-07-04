package com.devikiran.assignments.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NoteData(
    val title: String,
    val content: String
): Parcelable
