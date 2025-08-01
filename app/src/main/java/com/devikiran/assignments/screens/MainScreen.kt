package com.devikiran.assignments.screens

import android.webkit.WebView
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.devikiran.assignments.MainActivity
import com.devikiran.assignments.R
import com.devikiran.assignments.data.GHRepoData
import com.devikiran.assignments.utils.MainViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun MainScreen(
    activity: MainActivity,
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
            val ghRepoDataList = viewModel.ghRepoDataState.collectAsState()
            GhRepoItemListScreen(
                ghRepoDataList.value,
                onQueryChange = { viewModel.searchByName(it) },
                onItemClicked = { viewModel.onItemClicked(it) }
            )
        }

        composable("failure") {
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

        composable("loadGitRepo") {
            val ghRepoData = viewModel.ghRepoData
            GitRepoWebView(
                ghRepoData,
                onBackPressed = { viewModel.onBackPressed("success") }
            )
        }
    }

    BackHandler {
        if (navController.currentDestination?.route == "success") {
            activity.finish()
        } else {
            viewModel.onBackPressed("success")
        }
    }

    LaunchedEffect(viewModel.navigateTo) {
        viewModel.navigateTo.collectLatest {
            navController.navigate(it)
        }
    }
}

@Composable
fun GhRepoItemListScreen(
    ghRepoDataList: List<GHRepoData>,
    onQueryChange: (String) -> Unit,
    onItemClicked: (GHRepoData) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            GhRepoSearchBar(
                onQueryChange = onQueryChange,

                )

            LazyColumn(
                modifier = Modifier.padding(16.dp)
            ) {
                items(
                    items = ghRepoDataList
                ) { ghRepoData ->
                    GhRepoItem(ghRepoData, onItemClicked)
                }
            }
        }
    }
}

@Composable
fun GhRepoItem(
    ghRepoData: GHRepoData,
    onItemClicked: (GHRepoData) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable {
                onItemClicked(ghRepoData)
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)

    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = ghRepoData.id.toString(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = ghRepoData.name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = ghRepoData.htmlUrl,
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun GhRepoSearchBar(
    onQueryChange: (String) -> Unit,
) {
    var query by remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        value = query,
        onValueChange = {
            query = it
            onQueryChange(query)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        placeholder = { Text(text = stringResource(R.string.search_placeholder)) },
        singleLine = true,
        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = "Search Icon")
        },
        shape = RoundedCornerShape(8.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            disabledContainerColor = MaterialTheme.colorScheme.surface
        )
    )
}


@Composable
fun GitRepoWebView(
    ghRepoData: GHRepoData?,
    onBackPressed: () -> Unit
) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(
                onClick = onBackPressed
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null
                )

            }

            Spacer(modifier = Modifier.size(8.dp))

            Text(
                text = ghRepoData!!.name,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f),
                color = MaterialTheme.colorScheme.onSurface
            )

        }
        AndroidView(
            factory = { context ->
                WebView(context).apply {
                    loadUrl(ghRepoData!!.htmlUrl)
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    }

}


