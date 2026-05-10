package com.example.a221031_ammar_drnelson_lab4.data

import android.R
import androidx.annotation.DrawableRes

data class PlantFamily(
    val name: String,
    val speciesCount: String,
    val image: Int,
    val desc: String,
    val genusList: List<PlantGenus>
)

data class PlantGenus(
    val name: String,
    val familyName: String,
    val image: Int,
    val speciesList: List<PlantSpecies>
)

data class PlantSpecies(
    val name: String,
    val genusName: String,
    val image: Int,
    val desc: String
)

data class User(
    val email: String,
    val password: String
)