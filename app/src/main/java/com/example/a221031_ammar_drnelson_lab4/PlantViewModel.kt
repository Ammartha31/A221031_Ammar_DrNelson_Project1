package com.example.a221031_ammar_drnelson_lab4

import com.example.a221031_ammar_drnelson_lab4.data.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.a221031_ammar_drnelson_lab4.R

class PlantViewModel : ViewModel() {

    var selectedFamily: PlantFamily? = null
    var selectedGenus: PlantGenus? = null
    var selectedSpecies: PlantSpecies? = null
    var userName = mutableStateOf<String?>(null)
    var plantFamilies by mutableStateOf(sampleData())
    fun updateSelectedSpeciesDescription(newDescription: String) {

        val familyIndex = plantFamilies.indexOfFirst {
            it.name == selectedFamily?.name
        }
        if (familyIndex == -1) return

        val genusIndex = plantFamilies[familyIndex]
            .genusList.indexOfFirst {
                it.name == selectedGenus?.name
            }
        if (genusIndex == -1) return

        val speciesIndex = plantFamilies[familyIndex]
            .genusList[genusIndex]
            .speciesList.indexOfFirst {
                it.name == selectedSpecies?.name
            }
        if (speciesIndex == -1) return

        val updatedSpecies =
            selectedSpecies?.copy(desc = newDescription) ?: return

        val updatedFamilies = plantFamilies.toMutableList()

        val updatedGenusList =
            updatedFamilies[familyIndex].genusList.toMutableList()

        val updatedSpeciesList =
            updatedGenusList[genusIndex].speciesList.toMutableList()

        updatedSpeciesList[speciesIndex] = updatedSpecies

        updatedGenusList[genusIndex] =
            updatedGenusList[genusIndex].copy(
                speciesList = updatedSpeciesList
            )

        updatedFamilies[familyIndex] =
            updatedFamilies[familyIndex].copy(
                genusList = updatedGenusList
            )

        plantFamilies = updatedFamilies
        selectedSpecies = updatedSpecies
    }

    private fun sampleData(): List<PlantFamily> {
        return listOf(
            PlantFamily(
                name = "Acanthaceae",
                speciesCount = "120",
                image = R.drawable.plant1,
                desc = "Family description",
                genusList = listOf(
                    PlantGenus(
                        name = "Acanthopale",
                        familyName = "Acanthaceae",
                        image = R.drawable.plant11,
                        speciesList = listOf(
                            PlantSpecies(
                                name = "Acanthopale corfertiflora",
                                genusName = "Acanthopale",
                                image = R.drawable.plant111,
                                desc = "soft-wooded shrub, up to 3m high, found in African montane forests."
                            ),
                            PlantSpecies(
                                name = "Acanthopale madagascariensis",
                                genusName = "Acanthopale",
                                image = R.drawable.plant112,
                                desc = "white flowers with yellow centers and purple tinges, reaching up to 40 cm in height. Shrimp plant species"
                            ),
                            PlantSpecies(
                                name = "Acanthopale decempedalis",
                                genusName = "Acanthopale",
                                image = R.drawable.plant113,
                                desc = "found in Cameroon, Equatorial Guinea, and Nigeria. Its natural habitats are subtropical or tropical moist lowland forests"
                            )
                        )
                    ),
                    PlantGenus(
                        name = "Hypoestes",
                        familyName = "Acanthaceae",
                        image = R.drawable.plant12,
                        speciesList = listOf(
                            PlantSpecies(
                                name = "Hypoestes Aristata",
                                genusName = "Hypoestes",
                                image = R.drawable.plant121,
                                desc = "fast-growing shrub that grows up to 1.5 m high. It produces soft, hairy, dark green oval leaves and has attractive lilac pink or white flowers."
                            ),
                            PlantSpecies(
                                name = "Hypoestes comosa Benoist",
                                genusName = "Hypoestes",
                                image = R.drawable.plant122,
                                desc = "tropical plant native to Madagascar. Prefers bright, indirect light and consistent moisture."
                            )
                        )
                    )
                )
            ),

            PlantFamily(
                name = "Achariaceae",
                speciesCount = "120",
                image = R.drawable.plant2,
                desc = "Family description",
                genusList = listOf(
                    PlantGenus(
                        name = "Caloncoba",
                        familyName = "Achariaceae",
                        image = R.drawable.plant21,
                        speciesList = listOf(
                            PlantSpecies(
                                name = "Caloncoba crepiniana",
                                genusName = "Caloncoba",
                                image = R.drawable.plant211,
                                desc = "showy white and yellow flowers, slightly larger than Caloncoba welwitschii."
                            ),
                            PlantSpecies(
                                name = "Caloncoba flagellifera",
                                genusName = "Caloncoba",
                                image = R.drawable.plant212,
                                desc = "rainforest tree native to Cameroon, Gabon and Congo regions."
                            )
                        )
                    )
                )
            ),

            PlantFamily(
                name = "Achatocarpaceae",
                speciesCount = "32",
                image = R.drawable.plant3,
                desc = "Family description",
                genusList = listOf(
                    PlantGenus(
                        name = "Justicia",
                        familyName = "Acanthaceae",
                        image = R.drawable.plant2,
                        speciesList = emptyList()
                    )
                )
            ),

            PlantFamily(
                name = "Acoraceae",
                speciesCount = "18",
                image = R.drawable.plant4,
                desc = "Family description",
                genusList = listOf(
                    PlantGenus(
                        name = "Justicia",
                        familyName = "Acanthaceae",
                        image = R.drawable.plant2,
                        speciesList = emptyList()
                    )
                )
            ),

            PlantFamily(
                name = "Actinidiaceae",
                speciesCount = "0",
                image = R.drawable.plant5,
                desc = "Family description",
                genusList = emptyList()
            ),

            PlantFamily(
                name = "Aextoxicaceae",
                speciesCount = "0",
                image = R.drawable.plant6,
                desc = "Family description",
                genusList = emptyList()
            )
        )
    }
}