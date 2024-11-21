package com.panfilosoft.contactapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.panfilosoft.contactapp.data.local.entities.Contact

@Composable
fun AddScreen(
    viewModel: ContactViewModel,
    navController: NavController
) {

    var name by remember { mutableStateOf("") }
    val nameChange = { text: String ->
        name = text
    }
    var number by remember { mutableStateOf("") }
    val numberChange = { text: String ->
        number = text
    }
    var email by remember { mutableStateOf("") }
    val emailChange = { text: String ->
        email = text
    }
    val colors = listOf(
        "0xFF8BC34A",
        "0xFFFF5722",
        "0xFF2196F3",
        "0xFFCDDC39",
        "0xFFFFEB3B",
        "0xFF009688"
    )
    Scaffold(
        topBar = {
            CustomTopAppBar(
                onTap = {
                    navController.popBackStack()
                },
                onSave = {
                    viewModel.insertContact(
                        Contact(
                            name = name,
                            number = number,
                            email = email,
                        )
                    )
                    navController.popBackStack()
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(Color.Black),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(22.dp))
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF42A5F5)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(40.dp),
                    imageVector = Icons.Outlined.AccountBox,
                    contentDescription = "photo"
                )
            }
            Spacer(modifier = Modifier.height(22.dp))
            CustomTextField(
                Icons.Outlined.Person,
                "Name",
                name,
                nameChange
            )
            Spacer(modifier = Modifier.height(18.dp))
            CustomTextField(
                Icons.Outlined.Call,
                "Number",
                number,
                numberChange
            )
            Spacer(modifier = Modifier.height(18.dp))
            CustomTextField(
                Icons.Outlined.Email,
                "Email",
                email,
                emailChange
            )
        }
    }
}

@Composable
fun CustomTopAppBar(
    onTap: () -> Unit = {},
    onSave: () -> Unit = {}
) {
    Box(
        modifier = Modifier
//            .height(22.dp)
            .fillMaxWidth()
            .background(Color.Black),

        ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                    onTap()
                },
                imageVector = Icons.Default.Close,
                contentDescription = "close",
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                modifier = Modifier.weight(0.7f),
                text = " Create Contact",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Black
            )
            Spacer(modifier = Modifier.width(8.dp))
            FilledTonalButton(
                onClick = {
                    onSave()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF42A5F5),
                    contentColor = Color.Black
                )
            ) { Text(text = "Save", color = Color.Black) }
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}


@Composable
fun CustomTextField(
    icon: ImageVector,
    label: String,
    textState: String,
    onTextChange: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .background(Color.Black),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(8.dp))
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.White
        )
        Spacer(modifier = Modifier.width(8.dp))
        OutlinedTextField(
            modifier = Modifier.background(Color.Black),
            value = textState,
            onValueChange = onTextChange,
            label = { Text(text = label) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.Black,
                focusedLabelColor = Color(0xFF42A5F5),
                unfocusedLabelColor = Color.White,
                unfocusedContainerColor = Color.Black,
                unfocusedTextColor = Color.White,
                focusedBorderColor = Color(0xFF42A5F5),
                focusedTextColor = Color.White,
                cursorColor = Color(0xFF42A5F5),

            ),


        )
        Spacer(modifier = Modifier.width(8.dp))
    }
}