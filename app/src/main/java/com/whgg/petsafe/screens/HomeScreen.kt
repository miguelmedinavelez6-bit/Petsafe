package com.whgg.petsafe.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val PetSafeTeal = Color(0xFF009688)
private val PetSafeBackground = Color(0xFFF7F9FA)
private val EmergencyRed = Color(0xFFE85D5D)
private val EmergencyBackground = Color(0xFFFFE7E7)

@Composable
fun HomeScreen(
    onEmergencyClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PetSafeBackground)
            .verticalScroll(rememberScrollState())
    ) {

        // ENCABEZADO
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(PetSafeTeal)
                .padding(20.dp)
        ) {

            Text(
                text = "PetSafe",
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Protegemos juntos a quienes no tienen voz",
                color = Color.White,
                fontSize = 14.sp
            )
        }

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            // BUSCADOR
            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text("Buscar animal, propietario o caso...")
                },
                leadingIcon = {
                    Text("🔍", fontSize = 18.sp)
                },
                shape = RoundedCornerShape(16.dp),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // EMERGENCIA
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = EmergencyBackground
                )
            ) {

                Column(
                    modifier = Modifier.padding(18.dp)
                ) {

                    Text(
                        text = "🚨  ¿Es una emergencia?",
                        color = EmergencyRed,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = "Solicita ayuda rápidamente",
                        color = Color(0xFF555555),
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = {
                            onEmergencyClick()
                        },
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = EmergencyRed,
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            text = "Solicitar ayuda",
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(22.dp))

            // ACCIONES RÁPIDAS
            Text(
                text = "Acciones rápidas",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                ActionCard(
                    emoji = "🐾",
                    title = "Registrar animal"
                )

                ActionCard(
                    emoji = "🔎",
                    title = "Reportar perdido"
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                ActionCard(
                    emoji = "📍",
                    title = "Animal encontrado"
                )

                ActionCard(
                    emoji = "🛡️",
                    title = "Posible maltrato"
                )
            }

            Spacer(modifier = Modifier.height(26.dp))

            // COMUNIDAD
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = "Comunidad",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Ver todo",
                    color = PetSafeTeal,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                CommunityStatCard(
                    modifier = Modifier.weight(1f),
                    emoji = "🔎",
                    number = "12",
                    label = "Perdidos\nactivos",
                    background = Color(0xFFFFEEEE)
                )

                CommunityStatCard(
                    modifier = Modifier.weight(1f),
                    emoji = "❤️",
                    number = "8",
                    label = "En\nadopción",
                    background = Color(0xFFF2EEFF)
                )

                CommunityStatCard(
                    modifier = Modifier.weight(1f),
                    emoji = "🐾",
                    number = "3",
                    label = "Necesitan\nayuda",
                    background = Color(0xFFFFF6E5)
                )
            }

            Spacer(modifier = Modifier.height(22.dp))

            // CENTRO DE AYUDA
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 2.dp
                )
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(17.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Row(
                        modifier = Modifier.weight(1f)
                    ) {

                        Text(
                            text = "💊",
                            fontSize = 25.sp
                        )

                        Column(
                            modifier = Modifier.padding(start = 12.dp)
                        ) {

                            Text(
                                text = "Centro de ayuda",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.height(3.dp))

                            Text(
                                text = "Primeros auxilios y orientación animal",
                                color = Color(0xFF666666),
                                fontSize = 12.sp
                            )
                        }
                    }

                    Text(
                        text = "›",
                        color = Color(0xFF777777),
                        fontSize = 30.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(25.dp))
        }
    }
}

@Composable
private fun ActionCard(
    emoji: String,
    title: String
) {

    Card(
        modifier = Modifier
            .fillMaxWidth(0.48f)
            .height(105.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp),
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = emoji,
                fontSize = 25.sp
            )

            Spacer(modifier = Modifier.height(7.dp))

            Text(
                text = title,
                fontWeight = FontWeight.SemiBold,
                fontSize = 13.sp
            )
        }
    }
}

@Composable
private fun CommunityStatCard(
    modifier: Modifier = Modifier,
    emoji: String,
    number: String,
    label: String,
    background: Color
) {

    Card(
        modifier = modifier.height(125.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = background
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = emoji,
                fontSize = 22.sp
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = number,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = label,
                fontSize = 12.sp,
                color = Color(0xFF555555)
            )
        }
    }
}