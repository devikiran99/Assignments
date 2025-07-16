package com.devikiran.assignments.screens.note_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.devikiran.assignments.R
import com.devikiran.assignments.data.ActionBarData
import com.devikiran.assignments.data.NoteData


@Composable
fun NotesScreen(notesViewModel: NotesViewModel) {

    StaggeredVerticalGrid(notesViewModel) { note ->
        NoteItem(
            modifier = Modifier.fillMaxWidth(),
            noteData =note,
            onValueChange = {
                notesViewModel.onEvent(it)
            }
        )
    }
}

@Composable
fun NoteItem(
    modifier: Modifier = Modifier,
    noteData: NoteData,
    onValueChange: (NoteListScreenEvent) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        onValueChange(NoteListScreenEvent.OnClick(noteData))

                    },
                    onLongPress = {
                        onValueChange(NoteListScreenEvent.OnLongClick(noteData))

                    }
                )
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = noteData.title, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = if (noteData.content.length > 100) noteData.content.take(100) + "..." else noteData.content,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun StaggeredVerticalGrid(
    notesViewModel: NotesViewModel,
    itemContent: @Composable (NoteData) -> Unit
) {
    val numColumns = 2
    val spacing = 8.dp

    Row(
        modifier = Modifier
            .padding(spacing)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(spacing)
    ) {
        val columns = remember { List(numColumns) { mutableStateListOf<NoteData>() } }

        val noteState = notesViewModel.noteState.collectAsState()
        noteState.value.forEachIndexed { index, item ->
            columns[index % numColumns].add(item)
        }

        columns.forEach { columnItems ->
            Column(
                verticalArrangement = Arrangement.spacedBy(spacing),
                modifier = Modifier.weight(1f)
            ) {
                columnItems.forEach { note ->
                    itemContent(note)
                }
            }
        }
    }
}

@Composable
fun homeScreenActionBar(notesViewModel: NotesViewModel) = ActionBarData(
    topBar = {
        Surface(
            color = colorResource(R.color.white_1),
            tonalElevation = 4.dp,
            modifier = Modifier.statusBarsPadding()
        ) {
            val viewModel: NotesViewModel = hiltViewModel()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                var showDialog by remember { mutableStateOf(false) }

                if (showDialog) {
                    ProfileScreenAlertDialogue()
                }

                Text("Notes", color = colorResource(R.color.black_1))

                Spacer(modifier = Modifier.weight(1f))

                IconButton(
                    onClick = { }
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                }

                Image(
                    painter = painterResource(id = R.drawable.ic_profile_pic),
                    contentDescription = "Clickable Image",
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .clickable {
                            showDialog = !showDialog
                        }
                )
            }
        }
    }
)