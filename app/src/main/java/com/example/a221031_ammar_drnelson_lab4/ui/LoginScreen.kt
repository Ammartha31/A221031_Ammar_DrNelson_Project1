package com.example.a221031_ammar_drnelson_lab4.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.runtime.*
import com.example.a221031_ammar_drnelson_lab4.data.PlantFamily
import com.example.a221031_ammar_drnelson_lab4.PlantViewModel
import com.example.a221031_ammar_drnelson_lab4.Screen
import com.example.a221031_ammar_drnelson_lab4.data.User

@Composable
fun LoginScreen(navController: NavHostController, viewModel: PlantViewModel) {

    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {

        LoginHeader()

        Spacer(modifier = Modifier.height(32.dp))

        LoginForm(
            userName = userName,
            password = password,
            onuserNameChange = { userName = it },
            onPasswordChange = { password = it }
        )

        Spacer(modifier = Modifier.height(24.dp))

        LoginButton(
            onClick = {

                if (userName.isNotBlank() && password.isNotBlank()) {

                    // save user into ViewModel
                    viewModel.userName.value = userName

                    navController.navigate(Screen.Family.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        TextButton(onClick = { }) {
            Text("Don't have an account? Sign up")
        }
    }
}