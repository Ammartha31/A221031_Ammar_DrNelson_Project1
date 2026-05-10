package com.example.a221031_ammar_drnelson_lab4.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.a221031_ammar_drnelson_lab4.Screen
import com.example.a221031_ammar_drnelson_lab4.PlantViewModel

@Composable
fun GenusScreen(navController: NavHostController, viewModel: PlantViewModel) {
    val family = viewModel.selectedFamily

    MainLayout(family?.name ?: "Genera", navController, showBack = true) {

        NatureHubHeader(navController, viewModel)

        val genusList = family?.genusList ?: emptyList()

        genusList.forEach { genus ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable {
                        viewModel.selectedGenus = genus
                        navController.navigate(Screen.Species.route)
                    },
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Text(
                    text = genus.name,
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}