package com.panfilosoft.contactapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.panfilosoft.contactapp.ui.navigation.Routes

@Composable
fun HomeScreen(
    viewModel: ContactViewModel,
    navController: NavController
) {
    val contacts by viewModel.contacts.collectAsState()
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .height(62.dp)
                    .fillMaxWidth()
                    .background(Color.Black),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "Contact App",
                    fontWeight = FontWeight.Bold,
                    fontSize = 36.sp,
                    color = Color.White
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Routes.Add.route)
                },
                containerColor = Color(0xFF4FC3F7)

            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "add",
                    tint = Color.Black
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color.Black)
        )
        {
            Row {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(60.dp)
                )
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                ) {
                    Spacer(modifier = Modifier.height(22.dp))
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f)
                    ) {
                        items(contacts) { contact ->
                            ItemContact(
                                name = contact.name,
                                detail = {
                                    navController.navigate(Routes.Detail.route + "/${contact.id}/${contact.name}/${contact.number}/${contact.email}")
                                }
                            )

                        }
                    }
                    Spacer(modifier = Modifier.height(22.dp))
                }
            }
        }
    }
}

@Composable
fun ItemContact(
    modifier: Modifier = Modifier,
    name: String,
    detail: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .height(48.dp)
            .padding(4.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .background(Color.Black)
            .clickable {
                detail()
            },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Spacer(modifier = Modifier.width(4.dp))
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .size(38.dp)
                .background(Color(0xFF4FC3F7)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = name[0].uppercaseChar().toString(),
                fontSize = 26.sp,
                fontWeight = FontWeight.SemiBold

            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = name,
            color = Color(0xFFD1C9C9),
            fontSize = 28.sp
        )
    }
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(4.dp)
    )
}
