package com.example.a221031_ammar_drnelson_lab4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.a221031_ammar_drnelson_lab4.ui.*
import com.example.a221031_ammar_drnelson_lab4.ui.theme.A221031_Ammar_DrNelson_Lab4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            A221031_Ammar_DrNelson_Lab4Theme {
                val navController = rememberNavController()
                val viewModel: PlantViewModel = viewModel()

                NavHost(
                    navController = navController,
                    startDestination = Screen.Family.route
                ) {
                    composable(Screen.Login.route) {
                        LoginScreen(navController, viewModel)
                    }
                    composable(Screen.Family.route) {
                        FamilyScreen(navController, viewModel)
                    }
                    composable(Screen.Genus.route) {
                        GenusScreen(navController, viewModel)
                    }
                    composable(Screen.Species.route) {
                        SpeciesScreen(navController, viewModel)
                    }
                    composable(Screen.Detail.route) {
                        DetailScreen (navController, viewModel)
                    }

                    composable(Screen.Edit.route) {
                        EditScreen(navController, viewModel)
                    }
                }
            }
        }
    }
}
