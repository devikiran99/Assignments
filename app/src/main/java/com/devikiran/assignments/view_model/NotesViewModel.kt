package com.devikiran.assignments.view_model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.devikiran.assignments.data.NoteData
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): ViewModel() {

    val sampleNoteData = listOf(
        NoteData( "Grocery List", "Buy milk, eggs, and bread."),
        NoteData( "Workout Plan", "Push-ups\nSit-ups\nJogging\nStretching"),
        NoteData( "Quote", "“Be yourself; everyone else is already taken.” — Oscar Wilde"),
        NoteData( "Meeting Notes", "Talked about project timeline and responsibilities.\nFollow-up needed."),
        NoteData( "Meeting Notes", "Talked about project timeline and responsibilities.\nFollow-up needed."),
        NoteData( "Meeting Notes", "Tfdhudg dhfhfdhbgdjhngb jdbfhhgbfb alked about project timeline and responsibilities.\nFollow-up needed."),
        NoteData( "Meeting Notes", "Tfdhudg dhfhfdhbgdjhngb jdbfhhgbfb alked about project timeline and responsibilities.\nFollow-up needed."),
        NoteData("Meeting Notes", "Tfdhudg dhfhfdhbgdjhngb jdbfhhgbfb alked about project timeline and responsibilities.\nFollow-up needed."),
    )


}