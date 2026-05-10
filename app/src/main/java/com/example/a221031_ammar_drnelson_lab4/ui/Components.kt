package com.example.a221031_ammar_drnelson_lab4.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.example.a221031_ammar_drnelson_lab4.PlantViewModel
import com.example.a221031_ammar_drnelson_lab4.R
import com.example.a221031_ammar_drnelson_lab4.Screen
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*

@Composable
fun FamilyTopSection(navController: NavHostController, viewModel: PlantViewModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {

        SimpleTab("Family")

        Text(
            text = "Genus",
            modifier = Modifier.clickable {
                viewModel.selectedFamily = null
                navController.navigate(Screen.Genus.route)
            },
            color = MaterialTheme.colorScheme.onBackground
        )

        Text(
            text = "Species",
            modifier = Modifier.clickable {
                viewModel.selectedFamily = null
                viewModel.selectedGenus = null
                navController.navigate(Screen.Species.route)
            },
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
fun NatureHubHeader(navController: NavHostController, viewModel: PlantViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            // BACK BUTTON
            IconButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            if (viewModel.userName.value == null) {

                LoginButton(
                    onClick = {
                        navController.navigate(Screen.Login.route)
                    }
                )

            } else {

                LogoutButton(
                    onClick = {

                        viewModel.userName.value = null

                        navController.navigate(Screen.Family.route) {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
fun MainScreenHeader(
    navController: NavHostController,
    viewModel: PlantViewModel
) {

    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier.size(55.dp)
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = "Nature\nHub",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center
                )
            }

            if (viewModel.userName.value == null) {

                LoginButton(
                    onClick = {
                        navController.navigate(Screen.Login.route)
                    }
                )

            } else {

                Column(
                    horizontalAlignment = Alignment.End
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = "Welcome ${viewModel.userName.value}",
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(end = 4.dp),
                            textAlign = TextAlign.Center
                        )

                        IconButton(
                            onClick = {
                                expanded = !expanded
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = "Menu",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }

                    AnimatedVisibility(
                        visible = expanded,
                        enter = expandVertically(
                            animationSpec = spring(
                                dampingRatio = Spring.DampingRatioMediumBouncy,
                                stiffness = Spring.StiffnessLow
                            )
                        ) + fadeIn(),
                        exit = shrinkVertically() + fadeOut()
                    ) {

                        Card(
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
                            ),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 6.dp
                            )
                        ) {

                            Row(
                                modifier = Modifier
                                    .clickable {

                                        expanded = false

                                        // clear session
                                        viewModel.userName.value = null

                                        navController.navigate(Screen.Family.route) {
                                            popUpTo(0) { inclusive = true }
                                        }
                                    }
                                    .padding(horizontal = 18.dp, vertical = 12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Icon(
                                    imageVector = Icons.Default.Logout,
                                    contentDescription = "Logout",
                                    tint = MaterialTheme.colorScheme.error,
                                    modifier = Modifier.size(20.dp)
                                )

                                Spacer(modifier = Modifier.width(10.dp))

                                Text(
                                    text = "Logout",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.error,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
fun NatureHubTabs(
    navController: NavHostController,
    viewModel: PlantViewModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        SimpleTab("Family")

        Text(
            text = "Genus",
            modifier = Modifier.clickable {
                viewModel.selectedFamily = null
                navController.navigate(Screen.Genus.route)
            },
            color = MaterialTheme.colorScheme.onBackground
        )

        Text(
            text = "Species",
            modifier = Modifier.clickable {
                viewModel.selectedFamily = null
                viewModel.selectedGenus = null
                navController.navigate(Screen.Species.route)
            },
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
fun SimpleTab(title: String) {
    Text(
        text = title,
        color = MaterialTheme.colorScheme.primary,
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(8.dp)
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainLayout(
    title: String,
    navController: NavHostController,
    showBack: Boolean = false,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        content()
    }
}

@Composable
fun PlantCard(title: String, subtitle: String, imageRes: Int, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .height(180.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f))
                        )
                    )
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(12.dp)
            ) {
                Text(title, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(subtitle, color = Color.White.copy(alpha = 0.8f), fontSize = 14.sp)
            }
        }
    }
}

@Composable
fun LoginHeader() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome Back",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Login to continue exploring Nature Hub",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
fun LoginForm(
    userName: String,
    password: String,
    onuserNameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit
) {
    Column {

        OutlinedTextField(
            value = userName,
            onValueChange = onuserNameChange,
            label = { Text("Username") },
            singleLine = true,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun LoginButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(14.dp),
        contentPadding = PaddingValues(
            horizontal = 14.dp,
            vertical = 6.dp
        ),
        modifier = Modifier.height(38.dp)
    ) {
        Text(
            text = "Login",
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun LogoutButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.errorContainer,
            contentColor = MaterialTheme.colorScheme.onErrorContainer
        ),
        modifier = Modifier.height(40.dp)
    ) {
        Text(
            text = "Logout",
            fontWeight = FontWeight.Bold
        )
    }
}