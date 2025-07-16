package com.devikiran.assignments.screens.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devikiran.assignments.R
import com.devikiran.assignments.ui.theme.AssignmentsTheme

@Composable
fun Register(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = Color.Black,
                shape = RoundedCornerShape(40.dp)
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 16.dp, start = 16.dp)
            ) {
                Text(
                    text = stringResource(R.string.app_name_to_display),
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 24.sp,
                    color = Color.White,
                )

                Text(
                    text = stringResource(R.string.app_description),
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 14.sp,
                    color = Color.White
                )
            }


            CustomEditText()

            Button(
                onClick = {

                },
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(start = 32.dp, end = 32.dp, top = 32.dp)
            ) {
                Text(
                    text = stringResource(R.string.register)
                )
            }

        }

    }
}

@Composable
fun CustomEditText() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 32.dp, end = 32.dp, top = 32.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var userName by remember { mutableStateOf("") }
        var userEmail by remember { mutableStateOf("") }
        var setPassword by remember { mutableStateOf("") }
        var resetPassword by remember { mutableStateOf("") }
        var isSetPasswordVisible by remember { mutableStateOf(false) }

        OutlinedTextField(
            value = userEmail,
            onValueChange = { userName = it },
            shape = RoundedCornerShape(16.dp),
            label = { Text(text = stringResource(R.string.user_name)) },
            placeholder = { Text(text = stringResource(R.string.dummy_user_name)) },
            modifier = Modifier.fillMaxWidth()
        )


        OutlinedTextField(
            value = userEmail,
            onValueChange = { userEmail = it },
            shape = RoundedCornerShape(16.dp),
            label = { Text(text = stringResource(R.string.user_email)) },
            placeholder = { Text(text = stringResource(R.string.dummy_user_email)) },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = setPassword,
            onValueChange = { setPassword = it },
            label = { Text(text = stringResource(R.string.set_user_password)) },
            placeholder = { Text(text = stringResource(R.string.dummy_user_password)) },
            shape = RoundedCornerShape(16.dp),
            visualTransformation = if (isSetPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (isSetPasswordVisible)
                    painterResource(R.drawable.ic_eye)
                else
                    painterResource(R.drawable.ic_eye_off)

                IconButton(
                    onClick = { isSetPasswordVisible = !isSetPasswordVisible }
                ) {
                    Icon(
                        painter = image,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp))
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = resetPassword,
            onValueChange = { resetPassword = it },
            label = { Text(text = stringResource(R.string.reset_user_password)) },
            shape = RoundedCornerShape(16.dp),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
    }
}


@Composable
fun Login() {

    var userEmail by remember { mutableStateOf("") }
    var setPassword by remember { mutableStateOf("") }
    var isSetPasswordVisible by remember { mutableStateOf(false) }

    Column(modifier = Modifier
        .fillMaxSize()) {

        Spacer(modifier = Modifier.height(64.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 32.dp)
        ) {
            Text(
                text = stringResource(R.string.app_name_to_display),
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily.SansSerif,
                fontSize = 24.sp,
                color = Color.White
            )

            Text(
                text = stringResource(R.string.app_description),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                fontSize = 14.sp,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(64.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 32.dp, end = 32.dp)
        ) {
            OutlinedTextField(
                value = userEmail,
                onValueChange = { userEmail = it },
                label = { Text(stringResource(R.string.user_email)) },
                placeholder = { Text(stringResource(R.string.dummy_user_email)) },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = setPassword,
                onValueChange = { setPassword = it },
                label = { Text(stringResource(R.string.set_user_password)) },
                placeholder = { Text(stringResource(R.string.dummy_user_password)) },
                shape = RoundedCornerShape(16.dp),
                visualTransformation = if (isSetPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val icon = if (isSetPasswordVisible)
                        painterResource(R.drawable.ic_eye)
                    else
                        painterResource(R.drawable.ic_eye_off)

                    IconButton(onClick = { isSetPasswordVisible = !isSetPasswordVisible }) {
                        Icon(
                            painter = icon,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { /* Handle login */ },
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.login))
            }
        }
    }
}