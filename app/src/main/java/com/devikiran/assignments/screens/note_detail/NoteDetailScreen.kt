package com.devikiran.assignments.screens.note_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devikiran.assignments.R
import com.devikiran.assignments.data.ActionBarData
import com.devikiran.assignments.data.NoteData

@Composable
fun NoteDetailScreen(noteDetailData: NoteData, onValueChange: (NoteDetailScreenEvent) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()

    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = noteDetailData.title,
                onValueChange = { value ->
                    onValueChange(NoteDetailScreenEvent.OnNoteTitleChanged(value))
                                },
                textStyle = TextStyle(
                    color = colorResource(R.color.black_2),
                    fontSize = 24.sp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(colorResource(R.color.white_1)),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    cursorColor = colorResource(R.color.black_2),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )

            TextField(
                value = noteDetailData.content,
                onValueChange = { value ->
                    onValueChange(NoteDetailScreenEvent.OnNoteContentChanged(value))
                },
                textStyle = TextStyle(
                    color = colorResource(R.color.black_2),
                    fontSize = 18.sp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .weight(1f)
                    .align(Alignment.Start),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    cursorColor = colorResource(R.color.black_2),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
        }
    }
}

fun noteDetailActionBar(onValueChange: (NoteDetailScreenEvent) -> Unit) = ActionBarData(
    topBar = {
        Surface(
            color = colorResource(R.color.white_1),
            tonalElevation = 4.dp,
            modifier = Modifier.statusBarsPadding()
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
            )
            {
                IconButton(
                    onClick = {
                        onValueChange(NoteDetailScreenEvent.OnBackPress)
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_back),
                        contentDescription = null,
                        tint = colorResource(R.color.gray_1)
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                IconButton(
                    onClick = {
                        onValueChange(NoteDetailScreenEvent.OnRedo)

                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_undo),
                        contentDescription = null,
                        tint = colorResource(R.color.gray_1)
                    )
                }

                IconButton(
                    onClick = {
                        onValueChange(NoteDetailScreenEvent.OnUndo)
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_redo),
                        contentDescription = null,
                        tint = colorResource(R.color.gray_1)
                    )
                }
            }
        }
    }
)