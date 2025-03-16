package com.devikiran.assignments.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil3.compose.AsyncImage
import com.devikiran.assignments.R
import com.devikiran.assignments.data.DishType
import com.devikiran.assignments.data.RecommendationData
import com.devikiran.assignments.utils.MainViewModel
import com.devikiran.assignments.utils.Utils
import kotlinx.coroutines.launch


@Composable
fun DishScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel
) {
    Box(
        modifier = modifier
    ) {
        val isTablet = Utils.isTablet(LocalContext.current)

        ResponsiveNavigationUI(
            isTablet = isTablet,
            viewModel = viewModel
        )

        val isEnableAlertDialogue = viewModel.isEnableAlertDialogue.collectAsState()
        if (isEnableAlertDialogue.value) {
            TimePickerDialog(
                onConfirm = {
                    viewModel.initAlertDialogue()
                },
                onDismiss = {
                    viewModel.initAlertDialogue()
                }
            )
        }
    }
}

@Composable
fun ResponsiveNavigationUI(
    isTablet: Boolean,
    viewModel: MainViewModel
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedItem by remember { mutableStateOf("Cook") }

    if (isTablet) {
        Row(modifier = Modifier.fillMaxSize()) {
            SidebarNavigation(selectedItem) { selectedItem = it }
            MainContent(
                viewModel = viewModel,
                onNavDrawerClick = {}
            )
        }
    } else {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                SidebarNavigation(selectedItem) {
                    selectedItem = it
                    scope.launch { drawerState.close() }
                }
            }
        ) {
            MainContent(
                viewModel = viewModel,
                onNavDrawerClick = {
                    scope.launch { drawerState.open() }
                })
        }
    }
}

@Composable
fun SidebarNavigation(
    selectedItem: String,
    onItemSelected: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .width(120.dp)
            .fillMaxHeight()
            .background(Color(0xFFF8F8F8)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        val cookIcon = ImageVector.vectorResource(id = R.drawable.ic_cook)
        val deviceIcon = ImageVector.vectorResource(id = R.drawable.ic_device)
        val manualIcon = ImageVector.vectorResource(id = R.drawable.ic_manual)
        val prefIcon = ImageVector.vectorResource(id = R.drawable.ic_preference)

        NavigationItem(
            "Cook",
            cookIcon,
            selectedItem == "Cook"
        ) { onItemSelected("Cook") }
        NavigationItem(
            "Favourites",
            Icons.Default.Favorite,
            selectedItem == "Favourites"
        ) { onItemSelected("Favourites") }
        NavigationItem("Manual", manualIcon, selectedItem == "Manual") {
            onItemSelected(
                "Manual"
            )
        }
        NavigationItem("Device", deviceIcon, selectedItem == "Device") {
            onItemSelected(
                "Device"
            )
        }
        NavigationItem(
            "Preferences",
            prefIcon,
            selectedItem == "Preferences"
        ) { onItemSelected("Preferences") }
        NavigationItem(
            "Settings",
            Icons.Default.Settings,
            selectedItem == "Settings"
        ) { onItemSelected("Settings") }
    }
}

@Composable
fun NavigationItem(label: String, icon: ImageVector, isSelected: Boolean, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = if (isSelected) Color(0xFFFF6F00) else Color.Gray,
                modifier = Modifier.size(30.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = label,
                fontSize = 14.sp,
                color = if (isSelected) Color(0xFFFF6F00) else Color.Gray
            )
        }

        Box(
            modifier = Modifier
                .size(8.dp)
                .background(
                    color = if (isSelected) Color(0xFFFF6F00) else Color.Transparent,
                    shape = CircleShape
                )
        )
    }
}

@Composable
fun MainContent(
    viewModel: MainViewModel,
    onNavDrawerClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        val dishTypeList = viewModel.dishTypeDataList.collectAsState()
        val recommendationData = viewModel.dishDataDataList.collectAsState()

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {

                NavDrawerAndSearchBar(
                    modifier = Modifier
                        .weight(1f),
                    query = "",
                    onNavDrawerClick = onNavDrawerClick,
                    onQueryChange = {})

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ScheduledItemCard(
                        imageUrl = "https://nosh-assignment.s3.ap-south-1.amazonaws.com/jeera-rice.jpg",
                        title = "iTALIAN syhdhbfh hgfhgbfdhgvdfbgdjfgbfhj",
                        time = "06:30 PM"
                    )

                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notification",
                            tint = Color(0xFF1B2370),
                            modifier = Modifier.size(32.dp)
                        )
                    }


                    IconButton(onClick = {}) {
                        Icon(

                            painter = painterResource(id = R.drawable.ic_power),
                            contentDescription = "Power",
                            tint = Color(0xFFE53935),
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }

            }

            Spacer(modifier = Modifier.width(8.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 8.dp, end = 8.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start
            ) {
                item {
                    Text(
                        text = "What's in your Mind?",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        color = Color.Blue,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                item {
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(
                            16.dp,
                            Alignment.CenterHorizontally
                        )
                    ) {
                        items(dishTypeList.value) { dishType ->
                            DishTypesUi(dishType, onClick = {})
                        }
                    }
                }

                item(

                ) {
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(
                            16.dp,
                            Alignment.CenterHorizontally
                        )
                    ) {

                        items(recommendationData.value) { data ->
                            RecommendationDataUi(data, onClick = {
                                viewModel.initAlertDialogue()
                            })
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }

                item {
                    CustomButtons(
                        modifier = Modifier
                            .padding(top = 16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun NavDrawerAndSearchBar(
    modifier: Modifier = Modifier,
    query: String,
    onQueryChange: (String) -> Unit,
    onNavDrawerClick: () -> Unit
) {

    val isTablet = Utils.isTablet(LocalContext.current)
    if (isTablet) {
        TextField(
            value = query,
            onValueChange = onQueryChange,
            placeholder = { Text("Search for dish or ingredient", color = Color.Gray) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = Color.Gray
                )
            },
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(64.dp)
                .clip(RoundedCornerShape(30.dp))
                .background(Color.White)
                .border(1.dp, Color.LightGray, RoundedCornerShape(30.dp)),
            shape = RoundedCornerShape(30.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            singleLine = true
        )
    } else {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(64.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(onClick = {
                onNavDrawerClick()
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.List,
                    contentDescription = "Notification",
                    tint = Color.DarkGray,
                    modifier = Modifier
                        .wrapContentSize()
                        .background(color = Color.Gray, shape = RectangleShape)
                        .padding(12.dp),

                    )
            }

            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Notification",
                    tint = Color.Gray,
                    modifier = Modifier
                        .wrapContentSize()
                        .background(color = Color.LightGray, shape = RoundedCornerShape(8.dp))
                        .padding(12.dp),

                    )
            }
        }
    }
}

@Composable
fun ScheduledItemCard(
    imageUrl: String,
    title: String,
    time: String
) {
    Row(
        modifier = Modifier
            .background(Color(0xFF1B2730), shape = RoundedCornerShape(40.dp))
            .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = "Dish Image",
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(
                text = title,
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .widthIn(max = 100.dp)
                    .horizontalScroll(rememberScrollState())
            )
            Text(
                text = time,
                color = Color.LightGray,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun DishTypesUi(dishType: DishType, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(Color.White)
            .border(1.dp, Color(0xFFEDEDED), RoundedCornerShape(50))
            .clickable { onClick() }
            .padding(top = 4.dp, bottom = 4.dp, end = 16.dp, start = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Image(
                painter = painterResource(id = dishType.imageResId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
            )

            Text(
                text = dishType.dishName,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1B2370)
            )
        }
    }
}


@Composable
fun RecommendationDataUi(
    recommendationData: RecommendationData,
    onClick: (RecommendationData) -> Unit
) {
    val cardBackgroundColor = if (recommendationData.isSelected) Color(0xFF1B2370) else Color.White
    val dishImageBgColor =
        if (recommendationData.isSelected) Color.White else Color.Gray.copy(alpha = 0.3f)
    val dishTextColor = if (recommendationData.isSelected) Color.White else Color(0xFF1B2370)
    val dishSubTextColor =
        if (recommendationData.isSelected) Color.White.copy(alpha = 0.5f) else Color.Black.copy(
            alpha = 0.7f
        )

    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .width(220.dp)
            .clickable { onClick(recommendationData) }
            .padding(4.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = cardBackgroundColor)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .height(220.dp)
                    .width(220.dp)
                    .background(Color.Transparent)
                    .padding(top = 8.dp, start = 8.dp, end = 8.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .width(200.dp)
                        .height(200.dp)
                        .align(Alignment.TopCenter)
                        .clip(RoundedCornerShape(12.dp))
                        .background(dishImageBgColor)

                ) {
                    AsyncImage(
                        model = recommendationData.dishData.imageUrl,
                        contentDescription = "Food Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(140.dp)
                            .align(Alignment.Center)
                            .clip(CircleShape)
                            .background(Color.Transparent)
                    )

                    Image(
                        painter = painterResource(id = R.drawable.ic_food_varient),
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                            .align(Alignment.TopEnd)
                            .background(Color.Transparent, shape = RectangleShape)
                            .padding(top = 8.dp, end = 8.dp)
                    )

                }

                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFFFA500)) // Orange badge
                        .padding(start = 8.dp, end = 8.dp)
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Rating Star",
                            tint = Color.White,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = recommendationData.ratings,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        )
                    }
                }
            }

            Text(
                text = recommendationData.dishData.dishName,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = dishTextColor,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp, top = 8.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier
                    .padding(start = 4.dp, end = 4.dp, bottom = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = "Time",
                    tint = dishSubTextColor,
                    modifier = Modifier.size(14.dp)
                )
                Text(
                    text = "${recommendationData.prepTime} • ${recommendationData.preparationLevel}",
                    color = dishSubTextColor,
                    fontSize = 12.sp
                )
            }
        }
    }
}


@Composable
fun CustomButtons(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {

        CustomButtonWithIcons(
            modifier = Modifier.weight(1f),
            text = "Explore all dishes",
            backgroundColor = Color(0xFFE58332),
            images = listOf(
                R.drawable.ic_salads,
                R.drawable.ic_salads
            )
        )

        val isTablet = Utils.isTablet(LocalContext.current)
        if (isTablet) {
            Spacer(modifier = Modifier.width(8.dp))


            Card(
                modifier = Modifier
                    .height(60.dp)
                    .weight(1f),
                shape = RoundedCornerShape(50.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE58332)),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = "Confused what to cook?",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Composable
fun CustomButtonWithIcons(
    modifier: Modifier = Modifier,
    text: String, backgroundColor: Color,
    images: List<Int>
) {
    Card(
        modifier = modifier
            .height(60.dp),
        shape = RoundedCornerShape(50.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f)
            )

            images.forEach { imageRes ->
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(16.dp))
                )
            }
        }
    }
}


@Composable
fun TimePickerDialog(
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit
) {
    var selectedHour by remember { mutableStateOf(12) }
    var selectedMinute by remember { mutableStateOf(0) }
    var selectedAmPm by remember { mutableStateOf("AM") }

    Dialog(onDismissRequest = { onDismiss() }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Column(
                modifier = Modifier.padding(16.dp), // Padding to prevent content from touching edges
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ) {
                    Text(
                        text = "Schedule Cooking Time",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.Blue
                    )

                    IconButton(onClick = {
                        onDismiss()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Notification",
                            tint = Color.DarkGray,
                            modifier = Modifier
                                .wrapContentSize()
                        )
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier
                            .background(color = Color.LightGray, RoundedCornerShape(16.dp))
                    ) {
                        // Hours Picker
                        ScrollableNumberPicker(
                            range = (1..12).toList(),
                            selectedValue = selectedHour,
                            onValueChange = { selectedHour = it }
                        )

                        Text(
                            text = ":",
                            color = Color.Black.copy(alpha = 0.5f)
                        )

                        // Minutes Picker
                        ScrollableNumberPicker(
                            range = (-1..61).toList(),
                            selectedValue = selectedMinute,
                            onValueChange = { selectedMinute = it }
                        )
                    }

                    AmPmToggle(selectedValue = selectedAmPm, onValueChange = { selectedAmPm = it })
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                ) {

                    Text(
                        text = "Delete",
                        color = Color.Red,
                        fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier.clickable { }
                    )


                    OutlinedButton(
                        onClick = { },
                        border = BorderStroke(2.dp, Color(0xFFE07A3A)),
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFFE07A3A)),
                        modifier = Modifier
                            .height(48.dp)
                            .fillMaxWidth(0.6f)
                    ) {
                        Text(
                            text = "Re-schedule",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontWeight = FontWeight.Bold
                        )
                    }


                    Button(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE07A3A)),
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier
                            .height(48.dp)
                    ) {
                        Text(
                            text = "Cook Now",
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = Color.White,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun <T> ScrollableNumberPicker(range: List<T>, selectedValue: T, onValueChange: (T) -> Unit) {
    val listState =
        rememberLazyListState(initialFirstVisibleItemIndex = range.indexOf(selectedValue))
    Box(
        modifier = Modifier
            .height(100.dp)
            .width(80.dp)
    ) {
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(range.size) { index ->
                val value = range[index]
                val textColor = when (index) {
                    listState.firstVisibleItemIndex -> Color.Blue.copy(alpha = 0.5f)
                    listState.firstVisibleItemIndex + 1 -> Color.Blue
                    listState.firstVisibleItemIndex + 2 -> Color.Blue.copy(alpha = 0.5f)
                    else -> Color.Transparent
                }
                Text(
                    text = "$value",
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable { onValueChange(value) },
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    color = textColor
                )
            }
        }
    }
}

@Composable
fun AmPmToggle(selectedValue: String, onValueChange: (String) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        listOf("AM", "PM").forEach { period ->
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(width = 60.dp, height = 40.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(if (selectedValue == period) Color(0xFF2F3A94) else Color(0xFFE8E8FF))
                    .clickable { onValueChange(period) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = period,
                    color = if (selectedValue == period) Color.White else Color(0xFF2F3A94),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}