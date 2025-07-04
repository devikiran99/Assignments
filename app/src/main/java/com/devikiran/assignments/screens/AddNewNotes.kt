package com.devikiran.assignments.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devikiran.assignments.R
import com.devikiran.assignments.data.ActionBarData
import com.devikiran.assignments.view_model.AddNewNoteViewModel

@Composable
fun AddNewNote(addNewNoteViewModel: AddNewNoteViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()

    ) {
        var titleText by remember { mutableStateOf("") }
        var descriptionText by remember { mutableStateOf("") }

        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = titleText,
                onValueChange = { titleText = it },
                placeholder = {
                    Text(
                        text = stringResource(R.string.add_new_note_title),
                        color = colorResource(R.color.white_2),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = FontFamily.SansSerif
                    )
                },
                singleLine = true,
                textStyle = TextStyle(
                    color = colorResource(R.color.white_1),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = FontFamily.SansSerif
                ),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    cursorColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.size(8.dp))

            TextField(
                value = descriptionText,
                onValueChange = { descriptionText = it },
                placeholder = {
                    Text(
                        text = stringResource(R.string.add_new_note_description),
                        color = colorResource(R.color.white_2),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = FontFamily.SansSerif
                    )
                },
                textStyle = TextStyle(
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = FontFamily.SansSerif
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
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

fun insertNoteScreenAppBar(addNewNoteViewModel: AddNewNoteViewModel) = ActionBarData(
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
                        painter = painterResource(R.drawable.ic_close),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                }

                Text(
                    text = "Create Notes",
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.weight(1f)
                )

                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_done),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                }
            }
        }
    }
)
