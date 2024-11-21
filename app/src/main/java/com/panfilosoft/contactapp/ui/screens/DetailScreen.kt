package com.panfilosoft.contactapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.panfilosoft.contactapp.data.local.entities.Contact
import com.panfilosoft.contactapp.ui.navigation.Routes

@Composable
fun DetailScreen(
    viewModel: ContactViewModel,
    navController: NavController,
    id: String,
    name: String,
    number: String,
    email: String
) {
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .background(Color.Black)
                    .fillMaxWidth()
            ) {
                Icon(
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            navController.popBackStack()
                        },
                    imageVector = Icons.Outlined.Close,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        },
        floatingActionButton = {
            FilledTonalButton(
                onClick = {
                    navController.navigate(Routes.Edit.route + "/${id}/${name}/${number}/${email}")
                },
                colors = ButtonDefaults.filledTonalButtonColors(
                    containerColor = Color(0xFF42A5F5)
                )
            ) {
                Row {
                    Icon(
                        imageVector = Icons.Outlined.Create,
                        contentDescription = null,
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = "Edit",
                        fontSize = 22.sp,
                        color = Color.White
                    )
                }
            }

        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .background(Color.Black)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(22.dp))
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color.Cyan),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = name[0].uppercaseChar().toString(),
                    fontSize = 52.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(22.dp))
            Spacer(modifier = Modifier.height(22.dp))
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(Modifier.width(32.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = name,
                    fontSize = 35.sp,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(18.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(18.dp))
            Row {
                Spacer(modifier = Modifier.width(20.dp))
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        modifier = Modifier.size(28.dp),
                        imageVector = Icons.Outlined.Call,
                        contentDescription = null,
                        tint = Color(0xFF42A5F5)
                    )
                    Text(
                        text = "Call",
                        fontSize = 18.sp,
                        color = Color(0xFF42A5F5)
                    )
                }
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    Icon(
                        modifier = Modifier.size(28.dp),
                        imageVector = Icons.Outlined.Email,
                        contentDescription = null,
                        tint = Color(0xFF42A5F5)
                    )
                    Text(
                        text = "Message",
                        fontSize = 18.sp,
                        color = Color(0xFF42A5F5)
                    )
                }
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        modifier = Modifier.size(28.dp),
                        imageVector = Icons.Outlined.Settings,
                        contentDescription = null,
                        tint = Color(0xFF42A5F5)
                    )
                    Text(
                        text = "Configuration",
                        fontSize = 18.sp,
                        color = Color(0xFF42A5F5)
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
            }
            Spacer(modifier = Modifier.height(18.dp))
            HorizontalDivider()
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(18.dp))
                Icon(
                    modifier = Modifier.size(35.dp),
                    imageVector = Icons.Outlined.Call,
                    contentDescription = null,
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(14.dp))
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = number,
                        fontSize = 18.sp,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = email,
                        fontSize = 18.sp,
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(18.dp))
                Icon(
                    modifier = Modifier
                        .size(35.dp)
                        .clickable {
                            viewModel.deleteContact(
                                Contact(
                                    id = id.toInt(),
                                    name = name,
                                    number = number,
                                    email = email
                                )
                            )
                            navController.popBackStack()
                        },
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = null,
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(14.dp))
            }
        }
    }

}