package com.devikiran.assignments.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.devikiran.assignments.view_model.NotesDetailViewModel

@Composable
fun NoteDetailScreen(notesDetailViewModel: NotesDetailViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()

    ) {

        var titleText by remember { mutableStateOf("Demo") }
        var descriptionText by remember { mutableStateOf("Demo") }

        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {            TextField(
                value = titleText,
                onValueChange = { titleText = it },
                textStyle = TextStyle(
                    color = Color.White,
                    fontSize = 24.sp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    cursorColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )

            TextField(
                value = descriptionText,
                onValueChange = { descriptionText = it },
                textStyle = TextStyle(
                    color = Color.White,
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
                    cursorColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
        }
    }
}

fun noteDetailActionBar(notesDetailViewModel: NotesDetailViewModel) = ActionBarData(
    topBar = {
        Surface(
            color = colorResource(R.color.white_1),
            tonalElevation = 4.dp
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
            )
            {

                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_back),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_undo),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                }

                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_redo),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                }
            }
        }
    }
)