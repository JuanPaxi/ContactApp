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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.panfilosoft.contactapp.data.local.entities.Contact

@Composable
fun EditScreen(
    viewModel: ContactViewModel,
    navController: NavController,
    id: String,
    name: String,
    number: String,
    email: String,
) {

    var name by remember { mutableStateOf(name) }
    val nameChange = { text: String ->
        name = text
    }
    var number by remember { mutableStateOf(number) }
    val numberChange = { text: String ->
        number = text
    }
    var email by remember { mutableStateOf(email) }
    val emailChange = { text: String ->
        email = text
    }

    Scaffold(
        topBar = {
            CustomEditAppBar(
                onTap = {
                    navController.popBackStack()
                },
                onEdit = {
                    viewModel.updateContact(
                        Contact(
                            id = id.toInt(),
                            name = name,
                            number = number,
                            email = email
                        )
                    )
                    navController.popBackStack()
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
fun CustomEditAppBar(
    onTap: () -> Unit = {},
    onEdit: () -> Unit = {}
) {
    Box(
        modifier = Modifier
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
                text = "Edit Contact",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Black
            )
            Spacer(modifier = Modifier.width(8.dp))
            FilledTonalButton(
                onClick = {
                    onEdit()
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