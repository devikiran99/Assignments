package com.devikiran.assignments.view_model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.devikiran.assignments.data.NoteData
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class NotesDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): ViewModel() {

    val noteData: NoteData = checkNotNull(savedStateHandle.get<NoteData>("note"))


}