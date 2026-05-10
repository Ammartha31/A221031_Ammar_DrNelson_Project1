package com.example.a221031_ammar_drnelson_lab4.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.a221031_ammar_drnelson_lab4.PlantViewModel

@Composable
fun EditScreen(navController: NavHostController, viewModel: PlantViewModel) {
    val family = viewModel.selectedFamily
    val genus = viewModel.selectedGenus
    val species = viewModel.selectedSpecies

    var editedDescription by remember {
        mutableStateOf(species?.desc ?: "")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {

        NatureHubHeader(navController, viewModel)

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Edit Plant Information",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(24.dp))

        // FAMILY
        OutlinedTextField(
            value = family?.name ?: "",
            onValueChange = {},
            readOnly = true,
            label = { Text("Plant Family") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // GENUS
        OutlinedTextField(
            value = genus?.name ?: "",
            onValueChange = {},
            readOnly = true,
            label = { Text("Plant Genus") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // EDITABLE SPECIES
        OutlinedTextField(
            value = species?.name ?: "",
            onValueChange = {},
            readOnly = true,
            label = { Text("Plant Species") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // DESCRIPTION
        OutlinedTextField(
            value = editedDescription,
            onValueChange = {editedDescription = it},
            label = { Text("Description") },
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {

            OutlinedButton(
                onClick = {
                    navController.popBackStack()
                },
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                Text("Cancel")
            }

            Spacer(modifier = Modifier.width(12.dp))

            Button(
                onClick = {
                    viewModel.updateSelectedSpeciesDescription(editedDescription)
                    navController.popBackStack()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text("Save")
            }
        }
    }
}