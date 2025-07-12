package com.devikiran.assignments.data.utils

import com.devikiran.assignments.data.NoteData

sealed class NoteDataScreenEvent {
    data class OnOptionMenuClick(val noteData: NoteData) : NoteDataScreenEvent()
    data class OnClick(val noteData: NoteData) : NoteDataScreenEvent()
    data class OnLongClick(val noteData: NoteData) : NoteDataScreenEvent()
}