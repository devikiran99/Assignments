package com.devikiran.assignments.screens.note_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devikiran.assignments.data.NoteData
import com.devikiran.assignments.screens.utils.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val repository: NoteRepository
): ViewModel() {



    private val sampleNoteData = listOf(
        NoteData( "Grocery List", "Buy milk, eggs, and bread."),
        NoteData( "Workout Plan", "Push-ups\nSit-ups\nJogging\nStretching"),
        NoteData( "Quote", "“Be yourself; everyone else is already taken.” — Oscar Wilde"),
        NoteData( "Meeting Notes", "Talked about project timeline and responsibilities.\nFollow-up needed."),
        NoteData( "Meeting Notes", "Talked about project timeline and responsibilities.\nFollow-up needed."),
        NoteData( "Meeting Notes", "Tfdhudg dhfhfdhbgdjhngb jdbfhhgbfb alked about project timeline and responsibilities.\nFollow-up needed."),
        NoteData( "Meeting Notes", "Tfdhudg dhfhfdhbgdjhngb jdbfhhgbfb alked about project timeline and responsibilities.\nFollow-up needed."),
        NoteData("Meeting Notes", "Tfdhudg dhfhfdhbgdjhngb jdbfhhgbfb alked about project timeline and responsibilities.\nFollow-up needed."),
    )

    lateinit var navigateTo: (NoteData) -> Unit

    private val _noteState = MutableStateFlow(ArrayList<NoteData>())
    val noteState: StateFlow<ArrayList<NoteData>> = _noteState

    init {
        _noteState.update { ArrayList(sampleNoteData)  }
    }

    fun onEvent(event: NoteListScreenEvent) {
        when (event) {
            is NoteListScreenEvent.OnClick -> {
                viewModelScope.launch {
                    navigateTo(event.noteData)
                }
            }
            is NoteListScreenEvent.OnLongClick -> {}
            is NoteListScreenEvent.OnOptionMenuClick -> {}
        }
    }
}