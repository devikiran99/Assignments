package com.devikiran.assignments.data

import kotlinx.serialization.Serializable

@Serializable
data class NoteData(
    val title: String = "",
    val content: String = "",
    val color: Long = 1234
)
