package com.devikiran.assignments.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devikiran.assignments.R
import com.devikiran.assignments.ui.theme.AssignmentsTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreenAlertDialogue(modifier: Modifier = Modifier) {

   BasicAlertDialog(
       onDismissRequest = {  }
   ) {
       Surface(
           shape = RoundedCornerShape(20.dp),
           tonalElevation = 8.dp,
           color = colorResource(R.color.teal_200),
           modifier = Modifier
               .wrapContentSize()
               .padding(24.dp)
       ) {
           ProfileScreen()
       }
   }
}

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {

    var userName by remember { mutableStateOf("") }
    var isUserNameEditable by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(vertical = 32.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(R.drawable.ic_profile_pic),
            contentDescription = null,
            modifier = Modifier
                .size(64.dp)
                .align(Alignment.CenterHorizontally)
                .clip(RoundedCornerShape(32.dp))
        )

        TextField(
            value = userName,
            onValueChange = { if (isUserNameEditable) userName = it },
            placeholder = {
                Text(
                    text = "Name",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = FontFamily.SansSerif
                )
            },
            readOnly = !isUserNameEditable,
            singleLine = true,
            trailingIcon = {
                IconButton(onClick = { isUserNameEditable = !isUserNameEditable }) {
                    Icon(
                        imageVector = if (isUserNameEditable) Icons.Default.Check else Icons.Default.Edit,
                        contentDescription = if (isUserNameEditable) "Done Editing" else "Edit",
                        tint = colorResource(R.color.gray_2)
                    )
                }
            },
            textStyle = TextStyle(
                color = Color.White,
                fontSize = 16.sp,
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
                .padding(horizontal = 32.dp)
        )


        Text(
            text = "abc@gmail.com",
            fontFamily = FontFamily.SansSerif,
            fontSize = 16.sp,
            color = Color.White,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.size(32.dp))

        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp),
            shape = RoundedCornerShape(32.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.gray_1),
                contentColor = Color.Black
            ),
        ) {
            Text(
                text = "Logout",
                fontFamily = FontFamily.SansSerif,
                fontSize = 16.sp,
                color = Color.White,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Preview
@Composable
private fun PreviewScreen() {
    AssignmentsTheme {
//        ProfileScreen()
        ProfileScreenAlertDialogue()
    }
}