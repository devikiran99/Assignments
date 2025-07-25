package com.devikiran.assignments.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import com.devikiran.assignments.data.AppData
import com.devikiran.assignments.utils.MainViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel
) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "loading",
        modifier = modifier
            .fillMaxSize()
    ) {
        composable("loading") {

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Loading Data Please Wait....",

                )
            }

        }

        composable("success") {

            val appDataList = viewModel.appDataState.collectAsState()
            AppDataItemListScreen(
                appDataList.value
            )
        }

        //Note list Screen
        composable ("failure") {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Failed !!! Connect to internet and Try again",
                    )

                Button(
                    onClick = {
                        viewModel.getDataFromServer()
                    }
                ) {
                    Text(
                        text = "Try Again",
                    )
                }
            }
        }
    }

    LaunchedEffect(viewModel.navigateTo) {
        viewModel.navigateTo.collectLatest {
            navController.navigate(it)
        }
    }
}

@Composable
fun AppDataItemListScreen(
    appDataList: List<AppData>
    ) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        LazyColumn(
            modifier = Modifier.padding(16.dp)
        ) {
            items(appDataList){ appData ->
                AppItem(appData)
            }
        }
    }
}

@Composable
fun AppItem(appData: AppData) {

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(8.dp)
            .background(
                color = Color.LightGray,
                shape = RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .padding(horizontal =  16.dp, vertical = 4.dp)

    ) {
        AsyncImage(
            model = appData.image,
            contentDescription = null,
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier. width(8.dp))

        Column(
            verticalArrangement = Arrangement.Center
        ) {

            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = appData.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.size(8.dp))

                Text(
                    text = appData.category,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.size(8.dp))

            }

            Text(
                text = appData.description,
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Row(
                horizontalArrangement = Arrangement.Start,
            ) {

                Text(
                    text = appData.price.toString(),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.size(8.dp))

                Text(
                    text = appData.rating.rate.toString(),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
