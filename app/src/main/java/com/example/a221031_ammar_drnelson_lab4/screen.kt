package com.example.a221031_ammar_drnelson_lab4

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Family : Screen("family_screen")
    object Genus : Screen("genus_screen")
    object Species : Screen("species_screen")
    object Detail : Screen("detail_screen")
    object Edit : Screen("edit_screen")
}