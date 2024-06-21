package com.shubham.jclearning.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shubham.jclearning.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(
                text = "Friends List",
                fontWeight = FontWeight.SemiBold,
                style = TextStyle(fontSize = 22.sp),
                color = Color.Black
            )
        }, navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                androidx.compose.material3.Icon(
                    imageVector = Icons.Default.Menu, contentDescription = "Menu"
                )
            }
        })
    }) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            FriendsList(modifier = Modifier.align(Alignment.TopCenter))
        }
    }
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
        R.drawable.ic_launcher_background,
        "Shubham Mahalkar",
        "smahalkar@gmail.com",
        "8087163177",
        "Ch. Sambhaji Nagar"
    ), Friends(
        R.drawable.ic_launcher_background,
        "Pallavi Mahalkar",
        "pallavi@gmail.com",
        "0123456789",
        "Ch. Sambhaji Nagar"
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FriendsList(modifier: Modifier = Modifier) {
    val modalBottomSheetState = rememberModalBottomSheetState()
    var selectedFriend by remember { mutableStateOf<Friends?>(null) }

    Column(
        modifier.fillMaxSize(), verticalArrangement = Arrangement.Top
    ) {
        LazyColumn(modifier = modifier) {
            items(friends) { friend ->
                FriendListItem(friend) {
                    selectedFriend = friend
                }
            }
        }
        selectedFriend.let { friendData ->
            friendData?.let {
                UserDetailBottomSheet(friendData, modalBottomSheetState)
                println("selected data is: ${friendData.name}")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailBottomSheet(user: Friends, modalBottomSheetState: SheetState) {
    ModalBottomSheet(
        onDismissRequest = { },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = user.name, style = MaterialTheme.typography.labelSmall)
            Text(text = user.email, style = MaterialTheme.typography.labelSmall)
            Text(text = user.phoneNumber, style = MaterialTheme.typography.labelSmall)
            Text(text = user.address, style = MaterialTheme.typography.labelSmall)
        }
    }
}

@Composable
fun FriendListItem(friends: Friends, onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .indication(indication = rememberRipple(bounded = true),
                interactionSource = remember { MutableInteractionSource() })
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = friends.profileImage),
                contentDescription = "ProfileImage",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .size(45.dp)
            )
            Column(
                modifier = Modifier.padding(
                    start = 12.dp, end = 12.dp, top = 8.dp, bottom = 8.dp
                )
            ) {
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
}

@Composable
@Preview
fun ShowHomeScreen() {
    HomeScreen()
}