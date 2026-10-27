package com.whgg.petsafe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.whgg.petsafe.screens.AlertsScreen
import com.whgg.petsafe.screens.CommunityScreen
import com.whgg.petsafe.screens.EmergencyScreen
import com.whgg.petsafe.screens.HomeScreen
import com.whgg.petsafe.screens.ProfileScreen
import com.whgg.petsafe.screens.RegisterAnimalScreen
import com.whgg.petsafe.ui.theme.PetSafeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            PetSafeTheme {
                PetSafeApp()
            }
        }
    }
}

@Composable
fun PetSafeApp() {

    var selectedScreen by remember {
        mutableIntStateOf(0)
    }

    var showEmergency by remember {
        mutableStateOf(false)
    }

    if (showEmergency) {

        EmergencyScreen(
            onBack = {
                showEmergency = false
            }
        )

    } else {

        Scaffold(
            bottomBar = {

                NavigationBar {

                    NavigationBarItem(
                        selected = selectedScreen == 0,
                        onClick = {
                            selectedScreen = 0
                        },
                        icon = {
                            Text("🏠")
                        },
                        label = {
                            Text("Inicio")
                        }
                    )

                    NavigationBarItem(
                        selected = selectedScreen == 1,
                        onClick = {
                            selectedScreen = 1
                        },
                        icon = {
                            Text("🐾")
                        },
                        label = {
                            Text("Comunidad")
                        }
                    )

                    NavigationBarItem(
                        selected = selectedScreen == 2,
                        onClick = {
                            selectedScreen = 2
                        },
                        icon = {
                            Text("➕")
                        },
                        label = {
                            Text("Registrar")
                        }
                    )

                    NavigationBarItem(
                        selected = selectedScreen == 3,
                        onClick = {
                            selectedScreen = 3
                        },
                        icon = {
                            Text("🔔")
                        },
                        label = {
                            Text("Alertas")
                        }
                    )

                    NavigationBarItem(
                        selected = selectedScreen == 4,
                        onClick = {
                            selectedScreen = 4
                        },
                        icon = {
                            Text("👤")
                        },
                        label = {
                            Text("Perfil")
                        }
                    )
                }
            }
        ) { innerPadding ->

            Box(
                modifier = Modifier.padding(innerPadding)
            ) {

                when (selectedScreen) {

                    0 -> HomeScreen(
                        onEmergencyClick = {
                            showEmergency = true
                        }
                    )

                    1 -> CommunityScreen()

                    2 -> RegisterAnimalScreen()

                    3 -> AlertsScreen()

                    4 -> ProfileScreen()
                }
            }
        }
    }
}