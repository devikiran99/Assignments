package com.devikiran.assignments.screens.note_list

import com.devikiran.assignments.data.NoteData

sealed class NoteListScreenEvent {
    data class OnOptionMenuClick(val noteData: NoteData) : NoteListScreenEvent()
    data class OnClick(val noteData: NoteData) : NoteListScreenEvent()
    data class OnLongClick(val noteData: NoteData) : NoteListScreenEvent()
}