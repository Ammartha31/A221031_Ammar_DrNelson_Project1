package com.example.a221031_ammar_drnelson_lab4.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.a221031_ammar_drnelson_lab4.data.PlantFamily
import com.example.a221031_ammar_drnelson_lab4.PlantViewModel
import com.example.a221031_ammar_drnelson_lab4.Screen

@Composable
fun FamilyScreen(navController: NavHostController, viewModel: PlantViewModel) {

    MainLayout("", navController) {

        Column(modifier = Modifier.fillMaxSize()) {

            MainScreenHeader(navController, viewModel)

            NatureHubTabs(navController, viewModel)

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(viewModel.plantFamilies) { family ->
                    PlantCard(
                        title = family.name,
                        subtitle = "${family.speciesCount} Species",
                        imageRes = family.image,
                        onClick = {
                            viewModel.selectedFamily = family
                            navController.navigate(Screen.Genus.route)
                        }
                    )
                }
            }
        }
    }
}

