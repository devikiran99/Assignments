package com.devikiran.assignments.screens.note_detail

sealed class NoteDetailScreenEvent {
    data class OnNoteTitleChanged(val tile: String) : NoteDetailScreenEvent()
    data class OnNoteContentChanged(val content: String) : NoteDetailScreenEvent()
    data object OnUndo : NoteDetailScreenEvent()
    data object OnRedo : NoteDetailScreenEvent()
    data object OnBackPress : NoteDetailScreenEvent()
}