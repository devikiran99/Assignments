package com.devikiran.assignments.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.devikiran.assignments.data.ActionBarData
import com.devikiran.assignments.data.NavigationData
import com.devikiran.assignments.view_model.AddNewNoteViewModel
import com.devikiran.assignments.view_model.NotesDetailViewModel
import com.devikiran.assignments.view_model.NotesViewModel


@Composable
fun MainScreen() {

    val navController = rememberNavController()
    var actionBarData by remember { mutableStateOf(ActionBarData()) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { actionBarData.topBar() },
        bottomBar = { actionBarData.bottomBar() }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavigationData.NoteScreen.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(NavigationData.NoteScreen.name) {
                val notesVewModel: NotesViewModel = hiltViewModel()
                actionBarData = homeScreenActionBar(notesVewModel)
                NotesScreen(notesVewModel)
            }

            composable(NavigationData.NoteDetailScreen.name) {
                val noteDetailVewModel: NotesDetailViewModel = hiltViewModel()
                actionBarData = noteDetailActionBar(noteDetailVewModel)
                NoteDetailScreen(noteDetailVewModel)
            }

            composable(NavigationData.AddNewNotesScreen.name) {
                val addNewNoteViewModel: AddNewNoteViewModel = hiltViewModel()
                actionBarData = insertNoteScreenAppBar(addNewNoteViewModel)
                AddNewNote(addNewNoteViewModel)
            }
        }
    }
}