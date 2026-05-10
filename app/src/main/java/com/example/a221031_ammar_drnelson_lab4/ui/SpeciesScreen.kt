package com.example.a221031_ammar_drnelson_lab4.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.a221031_ammar_drnelson_lab4.PlantViewModel
import com.example.a221031_ammar_drnelson_lab4.Screen
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.shape.*
import androidx.compose.foundation.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.material3.*
import androidx.compose.foundation.lazy.*


@Composable
fun SpeciesScreen(navController: NavHostController, viewModel: PlantViewModel) {

    val speciesList = viewModel.plantFamilies
        .flatMap { it.genusList }
        .find { it.name == viewModel.selectedGenus?.name }
        ?.speciesList ?: emptyList()

    MainLayout("Species", navController, showBack = true) {

        NatureHubHeader(navController, viewModel)


        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {items(speciesList) { species ->

                var expanded by remember { mutableStateOf(false) }

                Column {

                    Box {

                        PlantCard(
                            title = species.name,
                            subtitle = "Tap for details",
                            imageRes = species.image,
                            onClick = {
                                viewModel.selectedSpecies = species
                                navController.navigate(Screen.Detail.route)
                            }
                        )

                        IconButton(
                            onClick = {
                                expanded = !expanded
                            },
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(12.dp)
                        ) {

                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = "Expand",
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }

                    AnimatedVisibility(
                        visible = expanded,
                        enter = expandVertically() + fadeIn(),
                        exit = shrinkVertically() + fadeOut()
                    ) {

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
                            )
                        ) {

                            Text(
                                text = species.desc,
                                modifier = Modifier.padding(16.dp),
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

