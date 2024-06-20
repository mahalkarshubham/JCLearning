package com.shubham.jclearning.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shubham.jclearning.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(
                text = "Friends List", style = TextStyle(fontSize = 18.sp), color = Color.Gray
            )
        }, navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                androidx.compose.material3.Icon(
                    imageVector = Icons.Default.Menu, contentDescription = "Menu"
                )
            }
        })
    }, content = { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            FriendsList()
        }
    })
}

data class Friends(
    val profileImage: Int,
    val name: String,
    val email: String,
    val phoneNumber: String,
    val address: String
)

val friends = listOf(
    Friends(
        R.drawable.launcher,
        "Shubham Mahalkar",
        "smahalkar@gmail.com",
        "8087163177",
        "Ch. Sambhaji Nagar"
    ), Friends(
        R.drawable.launcher,
        "Shubham Mahalkar",
        "smahalkar@gmail.com",
        "8087163177",
        "Ch. Sambhaji Nagar"
    )
)

@Composable
fun FriendsList() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn {
            items(friends) { friend ->
                FriendListItem(friends = friend)
            }
        }
    }
}

@Composable
fun FriendListItem(friends: Friends) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = friends.profileImage),
            contentDescription = "ProfileImage",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(shape = CircleShape)
                .size(40.dp)
        )
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = friends.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 16.sp,
                color = Color.Black
            )
            Text(
                text = friends.phoneNumber,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}

@Composable
@Preview
fun ShowHomeScreen() {
    HomeScreen()
}