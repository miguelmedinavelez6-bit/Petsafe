package com.whgg.petsafe.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val EmergencyRed = Color(0xFFE85D5D)
private val EmergencyBackground = Color(0xFFF7F9FA)
private val WhatsAppGreen = Color(0xFF25D366)

@Composable
fun EmergencyScreen(
    onBack: () -> Unit
) {

    var step by remember {
        mutableIntStateOf(0)
    }

    var selectedEmergency by remember {
        mutableStateOf<String?>(null)
    }

    var location by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    when (step) {

        // PASO 1: SELECCIONAR EMERGENCIA
        0 -> EmergencySelectionScreen(
            selectedEmergency = selectedEmergency,
            onEmergencySelected = {
                selectedEmergency = it
                step = 1
            },
            onBack = onBack
        )

        // PASO 2: FORMULARIO
        1 -> EmergencyFormScreen(
            emergencyType = selectedEmergency ?: "Emergencia",
            location = location,
            description = description,
            onLocationChange = {
                location = it
            },
            onDescriptionChange = {
                description = it
            },
            onBack = {
                step = 0
            },
            onRequestHelp = {
                step = 2
            }
        )

        // PASO 3: CONFIRMACIÓN
        2 -> EmergencyConfirmationScreen(
            onBackHome = onBack
        )
    }
}

@Composable
private fun EmergencySelectionScreen(
    selectedEmergency: String?,
    onEmergencySelected: (String) -> Unit,
    onBack: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(EmergencyBackground)
            .padding(16.dp)
    ) {

        EmergencyTopBar(
            onBack = onBack
        )

        Spacer(modifier = Modifier.height(18.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFFFE8E8)
            )
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = "¿Qué está pasando?",
                    color = EmergencyRed,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Selecciona la situación para continuar",
                    color = Color(0xFF666666),
                    fontSize = 14.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            EmergencyOption(
                modifier = Modifier.weight(1f),
                emoji = "🐕",
                title = "Animal herido",
                selected = selectedEmergency == "Animal herido",
                onClick = {
                    onEmergencySelected("Animal herido")
                }
            )

            EmergencyOption(
                modifier = Modifier.weight(1f),
                emoji = "🚗",
                title = "Accidente",
                selected = selectedEmergency == "Accidente",
                onClick = {
                    onEmergencySelected("Accidente")
                }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            EmergencyOption(
                modifier = Modifier.weight(1f),
                emoji = "⚠️",
                title = "Posible maltrato grave",
                selected = selectedEmergency == "Posible maltrato grave",
                onClick = {
                    onEmergencySelected("Posible maltrato grave")
                }
            )

            EmergencyOption(
                modifier = Modifier.weight(1f),
                emoji = "🐕",
                title = "Animal agresivo",
                selected = selectedEmergency == "Animal agresivo",
                onClick = {
                    onEmergencySelected("Animal agresivo")
                }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            EmergencyOption(
                modifier = Modifier.weight(1f),
                emoji = "🏠",
                title = "Animal abandonado",
                selected = selectedEmergency == "Animal abandonado",
                onClick = {
                    onEmergencySelected("Animal abandonado")
                }
            )

            EmergencyOption(
                modifier = Modifier.weight(1f),
                emoji = "❓",
                title = "Otra emergencia",
                selected = selectedEmergency == "Otra emergencia",
                onClick = {
                    onEmergencySelected("Otra emergencia")
                }
            )
        }
    }
}

@Composable
private fun EmergencyFormScreen(
    emergencyType: String,
    location: String,
    description: String,
    onLocationChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onBack: () -> Unit,
    onRequestHelp: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(EmergencyBackground)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        EmergencyTopBar(
            onBack = onBack
        )

        Spacer(modifier = Modifier.height(18.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFFFE8E8)
            )
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = "🐕  $emergencyType",
                    color = EmergencyRed,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = "Completa la información para que podamos orientarte",
                    color = Color(0xFF666666),
                    fontSize = 14.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(22.dp))

        Text(
            text = "UBICACIÓN *",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF555555)
        )

        Spacer(modifier = Modifier.height(7.dp))

        OutlinedTextField(
            value = location,
            onValueChange = onLocationChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text("Calle, barrio o punto de referencia")
            },
            shape = RoundedCornerShape(14.dp),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "DESCRIPCIÓN DE LA SITUACIÓN",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF555555)
        )

        Spacer(modifier = Modifier.height(7.dp))

        OutlinedTextField(
            value = description,
            onValueChange = onDescriptionChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp),
            placeholder = {
                Text(
                    "Describe con detalle lo que está ocurriendo..."
                )
            },
            shape = RoundedCornerShape(14.dp)
        )

        Spacer(modifier = Modifier.height(18.dp))

        // FOTO / VIDEO
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .border(
                        width = 1.dp,
                        color = Color(0xFFBBBBBB),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .clickable { },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "📷",
                    fontSize = 30.sp
                )

                Spacer(modifier = Modifier.height(7.dp))

                Text(
                    text = "Agregar foto o video",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        Button(
            onClick = onRequestHelp,
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = EmergencyRed,
                contentColor = Color.White
            )
        ) {

            Text(
                text = "🚨  Solicitar ayuda",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
private fun EmergencyConfirmationScreen(
    onBackHome: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(EmergencyBackground)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        EmergencyTopBar(
            onBack = onBackHome
        )

        Spacer(modifier = Modifier.height(70.dp))

        Text(
            text = "🚨",
            fontSize = 55.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Ayuda solicitada",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Tu reporte ha sido registrado. Usa los canales de contacto para comunicarte directamente.",
            modifier = Modifier.padding(horizontal = 20.dp),
            textAlign = TextAlign.Center,
            color = Color(0xFF666666),
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = WhatsAppGreen,
                contentColor = Color.White
            )
        ) {

            Text(
                text = "💬  Contactar por WhatsApp",
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButton(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            shape = RoundedCornerShape(14.dp)
        ) {

            Text(
                text = "📞  Línea de emergencias 123",
                color = EmergencyRed,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = "Estos son canales de contacto disponibles. La atención depende del organismo correspondiente.",
            modifier = Modifier.padding(horizontal = 12.dp),
            textAlign = TextAlign.Center,
            color = Color(0xFF777777),
            fontSize = 11.sp
        )

        Spacer(modifier = Modifier.height(25.dp))

        OutlinedButton(
            onClick = onBackHome,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp)
        ) {

            Text(
                text = "Volver al inicio",
                color = Color(0xFF555555)
            )
        }
    }
}

@Composable
private fun EmergencyTopBar(
    onBack: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = "‹",
            modifier = Modifier.clickable {
                onBack()
            },
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Urgencias",
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "‹",
            color = Color.Transparent,
            fontSize = 34.sp
        )
    }
}

@Composable
private fun EmergencyOption(
    modifier: Modifier = Modifier,
    emoji: String,
    title: String,
    selected: Boolean,
    onClick: () -> Unit
) {

    val borderColor =
        if (selected) EmergencyRed
        else Color(0xFFE2E2E2)

    val backgroundColor =
        if (selected) Color(0xFFFFEEEE)
        else Color.White

    Card(
        modifier = modifier
            .height(120.dp)
            .border(
                width = if (selected) 2.dp else 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
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
                fontSize = 28.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}