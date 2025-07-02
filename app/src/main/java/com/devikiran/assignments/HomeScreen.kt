package com.devikiran.assignments

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devikiran.assignments.ui.theme.AssignmentsTheme

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_map),
                    contentDescription = null
                )

                Spacer(modifier = Modifier.size(20.dp))

                Text(
                    text = stringResource(R.string.top_bar_text),
                    fontSize = 14.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.ExtraBold,
                    color = colorResource(R.color.gray_2),
                    modifier = Modifier
                        .weight(1f)
                )

                IconButton(
                    onClick = { }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_search),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                }

                IconButton(
                    onClick = { }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_vector),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                }

            }

            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.background(color = colorResource(R.color.white_1))
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(end = 16.dp)
                        .background(color = colorResource(R.color.white_1)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.passion_text),
                        fontSize = 14.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.Gray,
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(end = 8.dp)
                    )

                    Box(
                        modifier = Modifier
                            .height(1.dp)
                            .fillMaxWidth()
                            .background(Color.Gray)
                    )
                }
                PassionIconListItem()
            }

            PassionTextListItem()

            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .background(color = colorResource(R.color.white_1))
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(start = 8.dp, end = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.live_text),
                        fontSize = 14.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.Gray,
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(end = 8.dp)
                    )

                    Box(
                        modifier = Modifier
                            .height(1.dp)
                            .fillMaxWidth()
                            .background(Color.Gray)
                    )
                }

                Spacer(modifier = Modifier.size(8.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .padding(start = 16.dp, end = 16.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.White)
                        .border(1.dp, color = colorResource(R.color.gray_2), RoundedCornerShape(16.dp))
                ){
                    Row(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Box(
                            modifier = Modifier
                                .wrapContentWidth()
                                .height(IntrinsicSize.Min)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_rectangle_button),
                                contentDescription = null,
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier.matchParentSize()
                            )

                            Text(
                                text = stringResource(R.string.passion_text_all),
                                fontSize = 14.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.Gray,
                                modifier = Modifier.padding(horizontal = 24.dp, vertical = 4.dp)
                            )
                        }
                        Spacer(modifier = Modifier.size(24.dp))


                        Text(
                            text = stringResource(R.string.passion_text_planned_activity),
                            fontSize = 14.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.Gray,
                            modifier = Modifier
                                .wrapContentWidth()
                                .padding(end = 8.dp)
                        )

                        Spacer(modifier = Modifier.size(24.dp))

                        Text(
                            text = stringResource(R.string.passion_text_live_activity),
                            fontSize = 14.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.Gray,
                            modifier = Modifier
                                .wrapContentWidth()
                                .padding(end = 8.dp)
                        )
                    }
                }

                ProfileScreen()
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PassionIconListItem() {

    val iconResourceId = arrayListOf(
        R.drawable.ic_vector,
        R.drawable.ic_vector_1,
        R.drawable.ic_brain,
        R.drawable.ic_cycle,
        R.drawable.ic_heart,
        R.drawable.ic_bi_leaf_fill,
        R.drawable.ic_chat_info
    )

    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 16.dp, top = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        iconResourceId.forEach { resId ->

            Image(
                painter = painterResource(resId),
                contentDescription = null
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PassionTextListItem() {

    val tags = arrayListOf(
        R.string.passion_text_all,
        R.string.passion_text_bmx,
        R.string.passion_text_debate,
        R.string.passion_text_donate,
        R.string.passion_text_critical,
        R.string.passion_text_community,
        R.string.passion_text_basket_ball
    )
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 16.dp, top = 16.dp)
            .background(color = Color.White),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        tags.forEach { tag ->
            Text(
                text = stringResource(tag),
                color = colorResource(R.color.gray_2),
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
fun ProfileScreen() {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = 16.dp)
            .background(color = Color.White)
    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 8.dp)
        ) {

            Image(
                painter = painterResource(id = R.drawable.ic_profile_pic),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(32.dp))
                    .border(
                        width = 2.dp,
                        color = colorResource(R.color.purple_1),
                        shape = RoundedCornerShape(32.dp)
                    )
            )

            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            ) {

                Text(
                    text = stringResource(R.string.user_name),
                    fontSize = 14.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Gray
                )

                Text(
                    text = stringResource(R.string.user_last_visit),
                    fontSize = 14.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Gray
                )
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .width(120.dp)
                    .height(48.dp)
            ) {

                Image(
                    painter = painterResource(id = R.drawable.ic_rectangle),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.matchParentSize()
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                ) {

                    Image(
                        painter = painterResource(R.drawable.ic_hands_up),
                        contentDescription = null
                    )

                    Text(
                        text = stringResource(R.string.user_interest),
                        fontSize = 14.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.Gray,
                        modifier = Modifier
                            .padding(start = 16.dp)
                    )

                }
            }
        }


        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp, top = 16.dp)
        ){
            Icon(
                painter = painterResource(R.drawable.ic_location),
                tint = colorResource(R.color.purple_1),
                contentDescription = null
            )

            Text(
                text = stringResource(R.string.user_current_address),
                fontSize = 14.sp,
                fontFamily = FontFamily.SansSerif,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Gray,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp, end = 32.dp)
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(top = 16.dp, start = 8.dp, end = 8.dp)
        ){

            Icon(
                painter = painterResource(R.drawable.ic_calender),
                tint = colorResource(R.color.purple_1),
                contentDescription = null
            )

            Text(
                text = stringResource(R.string.current_date),
                fontSize = 14.sp,
                fontFamily = FontFamily.SansSerif,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Gray,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp, end = 16.dp)
            )

            Image(
                painter = painterResource(R.drawable.ic_people),
                contentDescription = null
            )

            Text(
                text = stringResource(R.string.number_of_join),
                fontSize = 14.sp,
                fontFamily = FontFamily.SansSerif,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Gray,
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp)
            )
        }

        Spacer(modifier = Modifier.size(16.dp))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .wrapContentSize()
                .clip(RoundedCornerShape(12.dp))
        ) {

            Image(
                painter = painterResource(id = R.drawable.ic_user),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.matchParentSize()
            )

            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(colorResource(R.color.gray_1))
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_emily_profile),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(54.dp)
                            .padding(top = 8.dp, start = 8.dp, bottom = 8.dp)
                            .clip(RoundedCornerShape(32.dp))
                            .border(
                                width = 2.dp,
                                color = colorResource(R.color.purple_1),
                                shape = RoundedCornerShape(32.dp)
                            )
                    )

                    Column(
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
                    ) {

                        Text(
                            text = stringResource(R.string.user_name_emily),
                            fontSize = 14.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.Gray
                        )

                        Text(
                            text = stringResource(R.string.emily_last_visit),
                            fontSize = 14.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.Gray
                        )
                    }
                }


                Spacer(modifier = Modifier.size(64.dp))

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .padding(horizontal = 16.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(colorResource(R.color.gray_1))
                ) {

                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(start = 8.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_joseph_profile),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(32.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .border(
                                    width = 2.dp,
                                    color = Color.Unspecified,
                                    shape = RoundedCornerShape(16.dp)
                                )

                        )

                        Column(
                            verticalArrangement = Arrangement.SpaceEvenly,
                            horizontalAlignment = Alignment.Start,
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 8.dp, end = 8.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight(),
                                horizontalArrangement = Arrangement.Start,

                            ) {
                                Text(
                                    text = stringResource(R.string.user_name_joseph),
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily.SansSerif,
                                    fontStyle = FontStyle.Italic,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color.Gray,
                                    modifier = Modifier.weight(1f)
                                )

                                Text(
                                    text = stringResource(R.string.joseph_joined_date),
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily.SansSerif,
                                    fontStyle = FontStyle.Italic,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color.Gray
                                )
                            }


                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight(),
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_location),
                                    contentDescription = null,
                                    tint = Color.White
                                )


                                Text(
                                    text = stringResource(R.string.joseph_current_location),
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily.SansSerif,
                                    fontStyle = FontStyle.Italic,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color.Gray,
                                    modifier = Modifier
                                        .padding(start = 8.dp)
                                )
                            }
                        }

                        IconButton(
                            onClick = { },
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_cancel),
                                contentDescription = null,
                                modifier = Modifier.size(20.dp),
                                tint = Color.Unspecified
                            )
                        }

                    }

                }

                Spacer(modifier = Modifier.size(16.dp))

               Column(
                   verticalArrangement = Arrangement.SpaceEvenly,
                   horizontalAlignment = Alignment.CenterHorizontally,
                   modifier = Modifier
                       .fillMaxWidth()
                       .background(colorResource(R.color.gray_1))
               ) {

                   Text(
                       text = stringResource(R.string.emily_status_text),
                       fontSize = 14.sp,
                       fontFamily = FontFamily.SansSerif,
                       fontStyle = FontStyle.Italic,
                       fontWeight = FontWeight.ExtraBold,
                       color = Color.White,
                       modifier = Modifier
                           .padding(start = 8.dp, end = 24.dp)
                   )

                   Row(
                       horizontalArrangement = Arrangement.Start,
                       verticalAlignment = Alignment.CenterVertically,
                       modifier = Modifier
                           .padding(start = 16.dp)
                   ) {

                       Icon(
                           painter = painterResource(R.drawable.ic_heart),
                           contentDescription = null,
                           tint = Color.Unspecified,
                           modifier = Modifier
                               .size(20.dp)
                               .padding(end = 8.dp)
                       )

                       Text(
                           text = "7",
                           fontSize = 14.sp,
                           fontFamily = FontFamily.SansSerif,
                           fontStyle = FontStyle.Italic,
                           fontWeight = FontWeight.ExtraBold,
                           color = Color.White,
                           modifier = Modifier
                               .padding(end = 16.dp)
                       )

                       Icon(
                           painter = painterResource(R.drawable.ic_comment),
                           contentDescription = null,
                           tint = Color.Unspecified,
                           modifier = Modifier
                               .size(20.dp)
                               .padding(end = 8.dp)
                       )

                       Text(
                           text = "2",
                           fontSize = 14.sp,
                           fontFamily = FontFamily.SansSerif,
                           fontStyle = FontStyle.Italic,
                           fontWeight = FontWeight.ExtraBold,
                           color = Color.White,
                           modifier = Modifier
                               .padding(end = 16.dp)
                       )

                       Icon(
                           painter = painterResource(R.drawable.ic_forward),
                           contentDescription = null,
                           tint = Color.Unspecified,
                           modifier = Modifier
                               .size(20.dp)
                               .padding(end = 8.dp)
                       )

                       Text(
                           text = "12",
                           fontSize = 14.sp,
                           fontFamily = FontFamily.SansSerif,
                           fontStyle = FontStyle.Italic,
                           fontWeight = FontWeight.ExtraBold,
                           color = Color.White,
                           modifier = Modifier
                               .padding(end = 16.dp)
                       )

                       Spacer(modifier = Modifier.weight(1f))

                       IconButton(
                           onClick = { },
                       ) {
                           Icon(
                               painter = painterResource(R.drawable.ic_option),
                               contentDescription = null,
                               modifier = Modifier.size(20.dp),
                               tint = Color.Unspecified
                           )
                       }
                   }

               }
            }


        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(color = colorResource(R.color.white_1))
                .padding(bottom = 8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .align(Alignment.BottomCenter)
                    .clip(RoundedCornerShape(32.dp))
                    .background(Color.White)
            ) {
                IconButton(onClick = { }) {
                    Icon(
                        painter = painterResource(R.drawable.ic_home),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = Color.Unspecified
                    )
                }

                IconButton(onClick = { }) {
                    Icon(
                        painter = painterResource(R.drawable.ic_book),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = Color.Unspecified
                    )
                }

                Spacer(modifier = Modifier.width(64.dp))

                IconButton(onClick = { }) {
                    Icon(
                        painter = painterResource(R.drawable.ic_chat),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = Color.Unspecified
                    )
                }

                IconButton(onClick = { }) {
                    Icon(
                        painter = painterResource(R.drawable.ic_open_book),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = Color.Unspecified
                    )
                }
            }

            IconButton(
                onClick = { },
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = 12.dp)
                    .size(64.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_add_background),
                    contentDescription = null,
                    modifier = Modifier.size(64.dp),
                    tint = Color.Unspecified
                )
            }
        }
    }
}

@Preview
@Composable
fun Preview(modifier: Modifier = Modifier) {

    AssignmentsTheme {
        HomeScreen()
    }

}