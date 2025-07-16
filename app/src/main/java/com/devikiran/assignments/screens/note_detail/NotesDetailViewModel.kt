package com.devikiran.assignments.screens.note_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devikiran.assignments.data.NoteData
import com.devikiran.assignments.data.NoteDetailActionBarData
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class NotesDetailViewModel @Inject constructor(): ViewModel() {

    private val _noteDetailDataState = MutableStateFlow<NoteData?>(null)
    val noteDetailDataState: StateFlow<NoteData?> = _noteDetailDataState

    private val _noteDetailActioBarState = MutableStateFlow<NoteDetailActionBarData>(
        NoteDetailActionBarData()
    )
    val noteDetailActioBarState: StateFlow<NoteDetailActionBarData> = _noteDetailActioBarState


    private var noteOldData: NoteData? = null
    private var noteNewData: NoteData? = null

    lateinit var navigateTo: () -> Unit

    fun initNoteData(noteData: NoteData?) {
        if(noteData != null) {
            viewModelScope.launch {
                noteOldData = noteData
                _noteDetailDataState.update { noteData }
            }
        }
    }


    fun onEvent(event: NoteDetailScreenEvent) {
        when (event) {
            NoteDetailScreenEvent.OnBackPress -> {
                navigateTo()
            }

            is NoteDetailScreenEvent.OnNoteContentChanged -> {
                viewModelScope.launch {
                    val noteDetailData = _noteDetailDataState.value ?: return@launch
                    _noteDetailDataState.update {
                        noteNewData = noteDetailData.copy(content = event.content)
                        noteNewData
                    }
                }

                viewModelScope.launch {
                    _noteDetailActioBarState.update {
                        it.copy(isRedo = true, isUndo = false)
                    }
                }
            }

            is NoteDetailScreenEvent.OnNoteTitleChanged -> {
                viewModelScope.launch {
                    val noteDetailData = _noteDetailDataState.value ?: return@launch

                    _noteDetailDataState.update {
                        noteNewData = noteDetailData.copy(title = event.tile)
                        noteNewData
                    }
                }

                viewModelScope.launch {
                    _noteDetailActioBarState.update {
                        it.copy(isRedo = true, isUndo = false)
                    }
                }
            }

            NoteDetailScreenEvent.OnRedo -> {
                viewModelScope.launch {
                    _noteDetailDataState.update {
                        noteOldData
                    }
                }

                viewModelScope.launch {
                    _noteDetailActioBarState.update {
                        it.copy(isRedo = false, isUndo = true)
                    }
                }
            }

            NoteDetailScreenEvent.OnUndo -> {
                viewModelScope.launch {
                    _noteDetailDataState.update {
                        noteNewData
                    }
                }

                viewModelScope.launch {
                    _noteDetailActioBarState.update {
                        it.copy(isRedo = true, isUndo = false)
                    }
                }
            }
        }
    }
}