package com.devikiran.assignments.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.devikiran.assignments.data.ActionBarData
import com.devikiran.assignments.data.NoteData
import com.devikiran.assignments.data.NoteListScreenData
import com.devikiran.assignments.screens.note_detail.NoteDetailScreen
import com.devikiran.assignments.screens.note_detail.noteDetailActionBar
import com.devikiran.assignments.screens.note_list.NotesScreen
import com.devikiran.assignments.screens.note_list.homeScreenActionBar
import com.devikiran.assignments.screens.note_detail.NotesDetailViewModel
import com.devikiran.assignments.screens.note_list.NotesViewModel

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
            startDestination = NoteListScreenData,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(""){

            }

            composable(""){

            }

            //Note list Screen
            composable<NoteListScreenData> {

                val notesVewModel: NotesViewModel = hiltViewModel()
                actionBarData = homeScreenActionBar(notesVewModel)

                NotesScreen(notesVewModel)

                notesVewModel.navigateTo = { noteData ->
                    navController.navigate(noteData)
                }
            }

            //Note Detail Screen
            composable<NoteData> {
                val noteData = it.toRoute<NoteData>()
                val noteDetailVewModel: NotesDetailViewModel = hiltViewModel()

                LaunchedEffect(Unit) {
                    noteDetailVewModel.initNoteData(noteData)
                }

                noteDetailVewModel.navigateTo = {
                    navController.popBackStack()
                }

                val noteDetailData = noteDetailVewModel.noteDetailDataState.collectAsState()

                if (noteDetailData.value != null) {
                    actionBarData = noteDetailActionBar (
                        onValueChange = {
                            noteDetailVewModel.onEvent(it)
                        }
                    )
                    NoteDetailScreen(noteDetailData.value!!,
                        onValueChange = {
                            noteDetailVewModel.onEvent(it)
                        }
                    )
                }
            }
        }
    }
}