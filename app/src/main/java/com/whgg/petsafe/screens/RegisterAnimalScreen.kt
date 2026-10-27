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

private val RegisterTeal = Color(0xFF009688)
private val RegisterBackground = Color(0xFFF7F9FA)

@Composable
fun RegisterAnimalScreen() {

    var step by remember { mutableIntStateOf(0) }

    var selectedSituation by remember {
        mutableStateOf<String?>(null)
    }

    // DATOS ANIMAL ENCONTRADO
    var foundSpecies by remember { mutableStateOf("") }
    var foundLocation by remember { mutableStateOf("") }
    var foundDate by remember { mutableStateOf("") }
    var foundDescription by remember { mutableStateOf("") }

    // DATOS ANIMAL CON RESPONSABLE
    var animalName by remember { mutableStateOf("") }
    var species by remember { mutableStateOf("") }
    var breed by remember { mutableStateOf("") }
    var sex by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var color by remember { mutableStateOf("") }
    var characteristics by remember { mutableStateOf("") }

    when (step) {

        // SELECCIÓN DE SITUACIÓN
        0 -> RegisterSituationScreen(
            selectedSituation = selectedSituation,
            onSituationSelected = {
                selectedSituation = it
            },
            onContinue = {

                when (selectedSituation) {

                    "Tiene responsable" -> {
                        step = 2
                    }

                    "Encontrado / en calle" -> {
                        step = 1
                    }
                }
            }
        )

        // ENCONTRADO / EN CALLE
        1 -> FoundAnimalForm(
            species = foundSpecies,
            location = foundLocation,
            dateFound = foundDate,
            description = foundDescription,
            onSpeciesChange = {
                foundSpecies = it
            },
            onLocationChange = {
                foundLocation = it
            },
            onDateChange = {
                foundDate = it
            },
            onDescriptionChange = {
                foundDescription = it
            },
            onBack = {
                step = 0
            }
        )

        // TIENE RESPONSABLE
        2 -> OwnerAnimalForm(
            animalName = animalName,
            species = species,
            breed = breed,
            sex = sex,
            birthDate = birthDate,
            weight = weight,
            color = color,
            characteristics = characteristics,
            onAnimalNameChange = {
                animalName = it
            },
            onSpeciesChange = {
                species = it
            },
            onBreedChange = {
                breed = it
            },
            onSexChange = {
                sex = it
            },
            onBirthDateChange = {
                birthDate = it
            },
            onWeightChange = {
                weight = it
            },
            onColorChange = {
                color = it
            },
            onCharacteristicsChange = {
                characteristics = it
            },
            onBack = {
                step = 0
            }
        )
    }
}

@Composable
private fun RegisterSituationScreen(
    selectedSituation: String?,
    onSituationSelected: (String) -> Unit,
    onContinue: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(RegisterBackground)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        Text(
            text = "Registrar animal",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Crea un registro para proteger su historia e información.",
            fontSize = 14.sp,
            color = Color(0xFF666666)
        )

        Spacer(modifier = Modifier.height(22.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFE5F5F3)
            )
        ) {

            Column(
                modifier = Modifier.padding(18.dp)
            ) {

                Text(
                    text = "¿Cuál es la situación del animal?",
                    color = RegisterTeal,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = "Selecciona la opción que mejor describe el caso.",
                    color = Color(0xFF555555),
                    fontSize = 14.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            SituationCard(
                modifier = Modifier.weight(1f),
                emoji = "🏠",
                title = "Tiene responsable",
                description = "Animal con propietario o cuidador",
                selected = selectedSituation == "Tiene responsable",
                onClick = {
                    onSituationSelected("Tiene responsable")
                }
            )

            SituationCard(
                modifier = Modifier.weight(1f),
                emoji = "📍",
                title = "Encontrado / en calle",
                description = "Animal encontrado sin responsable conocido",
                selected = selectedSituation == "Encontrado / en calle",
                onClick = {
                    onSituationSelected("Encontrado / en calle")
                }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            SituationCard(
                modifier = Modifier.weight(1f),
                emoji = "🔎",
                title = "Está perdido",
                description = "Animal reportado como perdido",
                selected = selectedSituation == "Está perdido",
                onClick = {
                    onSituationSelected("Está perdido")
                }
            )

            SituationCard(
                modifier = Modifier.weight(1f),
                emoji = "❤️",
                title = "Fue rescatado",
                description = "Animal que fue rescatado recientemente",
                selected = selectedSituation == "Fue rescatado",
                onClick = {
                    onSituationSelected("Fue rescatado")
                }
            )
        }

        Spacer(modifier = Modifier.height(22.dp))

        if (selectedSituation != null) {

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        text = "Situación seleccionada",
                        fontSize = 12.sp,
                        color = Color(0xFF777777)
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = selectedSituation,
                        color = RegisterTeal,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (
                selectedSituation == "Tiene responsable" ||
                selectedSituation == "Encontrado / en calle"
            ) {

                Button(
                    onClick = onContinue,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = RegisterTeal,
                        contentColor = Color.White
                    )
                ) {

                    Text(
                        text = "Continuar",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }

            } else {

                Text(
                    text = "Este formulario lo agregaremos a continuación.",
                    color = Color(0xFF777777),
                    fontSize = 13.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(25.dp))
    }
}

@Composable
private fun OwnerAnimalForm(
    animalName: String,
    species: String,
    breed: String,
    sex: String,
    birthDate: String,
    weight: String,
    color: String,
    characteristics: String,
    onAnimalNameChange: (String) -> Unit,
    onSpeciesChange: (String) -> Unit,
    onBreedChange: (String) -> Unit,
    onSexChange: (String) -> Unit,
    onBirthDateChange: (String) -> Unit,
    onWeightChange: (String) -> Unit,
    onColorChange: (String) -> Unit,
    onCharacteristicsChange: (String) -> Unit,
    onBack: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(RegisterBackground)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        RegisterTopBar(
            title = "Datos del animal",
            onBack = onBack
        )

        Spacer(modifier = Modifier.height(15.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFE5F5F3)
            )
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = "🐾 Animal con responsable",
                    color = RegisterTeal,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = "Completa la información principal para crear su perfil.",
                    color = Color(0xFF555555),
                    fontSize = 13.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        FieldTitle("FOTO DEL ANIMAL *")

        Spacer(modifier = Modifier.height(7.dp))

        PhotoCard()

        Spacer(modifier = Modifier.height(18.dp))

        FieldTitle("NOMBRE *")

        Spacer(modifier = Modifier.height(6.dp))

        RegisterTextField(
            value = animalName,
            onValueChange = onAnimalNameChange,
            placeholder = "Nombre del animal"
        )

        Spacer(modifier = Modifier.height(18.dp))

        FieldTitle("ESPECIE *")

        Spacer(modifier = Modifier.height(6.dp))

        RegisterTextField(
            value = species,
            onValueChange = onSpeciesChange,
            placeholder = "Ej. Perro, gato..."
        )

        Spacer(modifier = Modifier.height(18.dp))

        FieldTitle("RAZA")

        Spacer(modifier = Modifier.height(6.dp))

        RegisterTextField(
            value = breed,
            onValueChange = onBreedChange,
            placeholder = "Ej. Labrador, mestizo..."
        )

        Spacer(modifier = Modifier.height(18.dp))

        FieldTitle("SEXO *")

        Spacer(modifier = Modifier.height(6.dp))

        RegisterTextField(
            value = sex,
            onValueChange = onSexChange,
            placeholder = "Macho / Hembra"
        )

        Spacer(modifier = Modifier.height(18.dp))

        FieldTitle("FECHA DE NACIMIENTO O EDAD APROXIMADA")

        Spacer(modifier = Modifier.height(6.dp))

        RegisterTextField(
            value = birthDate,
            onValueChange = onBirthDateChange,
            placeholder = "Ej. 12/05/2022 o 4 años"
        )

        Spacer(modifier = Modifier.height(18.dp))

        FieldTitle("PESO")

        Spacer(modifier = Modifier.height(6.dp))

        RegisterTextField(
            value = weight,
            onValueChange = onWeightChange,
            placeholder = "Ej. 18 kg"
        )

        Spacer(modifier = Modifier.height(18.dp))

        FieldTitle("COLOR")

        Spacer(modifier = Modifier.height(6.dp))

        RegisterTextField(
            value = color,
            onValueChange = onColorChange,
            placeholder = "Ej. Café y blanco"
        )

        Spacer(modifier = Modifier.height(18.dp))

        FieldTitle("CARACTERÍSTICAS")

        Spacer(modifier = Modifier.height(6.dp))

        OutlinedTextField(
            value = characteristics,
            onValueChange = onCharacteristicsChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            placeholder = {
                Text(
                    "Señas particulares, comportamiento u otra información..."
                )
            },
            shape = RoundedCornerShape(14.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFF2EEFF)
            )
        ) {

            Text(
                text = "🩺 La historia clínica se mantiene asociada al animal. La información profesional podrá ser completada o validada por un veterinario autorizado.",
                modifier = Modifier.padding(14.dp),
                color = Color(0xFF5D5572),
                fontSize = 12.sp
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = RegisterTeal,
                contentColor = Color.White
            )
        ) {

            Text(
                text = "Registrar animal",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedButton(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp)
        ) {

            Text(
                text = "Cancelar",
                color = Color(0xFF555555)
            )
        }

        Spacer(modifier = Modifier.height(25.dp))
    }
}

@Composable
private fun FoundAnimalForm(
    species: String,
    location: String,
    dateFound: String,
    description: String,
    onSpeciesChange: (String) -> Unit,
    onLocationChange: (String) -> Unit,
    onDateChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onBack: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(RegisterBackground)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        RegisterTopBar(
            title = "Animal encontrado",
            onBack = onBack
        )

        Spacer(modifier = Modifier.height(15.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFE5F5F3)
            )
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = "📍 Registro provisional",
                    color = RegisterTeal,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = "No necesitas conocer todos los datos del animal. Registra únicamente la información que puedas identificar.",
                    color = Color(0xFF555555),
                    fontSize = 13.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(22.dp))

        FieldTitle("FOTO DEL ANIMAL *")

        Spacer(modifier = Modifier.height(7.dp))

        PhotoCard()

        Spacer(modifier = Modifier.height(18.dp))

        FieldTitle("ESPECIE *")

        Spacer(modifier = Modifier.height(6.dp))

        RegisterTextField(
            value = species,
            onValueChange = onSpeciesChange,
            placeholder = "Ej. Perro, gato..."
        )

        Spacer(modifier = Modifier.height(18.dp))

        FieldTitle("UBICACIÓN DONDE FUE ENCONTRADO *")

        Spacer(modifier = Modifier.height(6.dp))

        RegisterTextField(
            value = location,
            onValueChange = onLocationChange,
            placeholder = "Barrio, calle o punto de referencia"
        )

        Spacer(modifier = Modifier.height(18.dp))

        FieldTitle("FECHA DEL ENCUENTRO *")

        Spacer(modifier = Modifier.height(6.dp))

        RegisterTextField(
            value = dateFound,
            onValueChange = onDateChange,
            placeholder = "DD/MM/AAAA"
        )

        Spacer(modifier = Modifier.height(18.dp))

        FieldTitle("INFORMACIÓN ADICIONAL")

        Spacer(modifier = Modifier.height(6.dp))

        OutlinedTextField(
            value = description,
            onValueChange = onDescriptionChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            placeholder = {
                Text(
                    "Color, características visibles, estado del animal..."
                )
            },
            shape = RoundedCornerShape(14.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFFFF6E5)
            )
        ) {

            Text(
                text = "💡 Datos como edad, raza, peso, vacunas o estado clínico podrán ser completados o validados posteriormente por un veterinario.",
                modifier = Modifier.padding(14.dp),
                color = Color(0xFF665C42),
                fontSize = 12.sp
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = RegisterTeal,
                contentColor = Color.White
            )
        ) {

            Text(
                text = "Crear registro provisional",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedButton(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp)
        ) {

            Text(
                text = "Cancelar",
                color = Color(0xFF555555)
            )
        }

        Spacer(modifier = Modifier.height(25.dp))
    }
}

@Composable
private fun RegisterTopBar(
    title: String,
    onBack: () -> Unit
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
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
            text = title,
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
private fun FieldTitle(
    title: String
) {

    Text(
        text = title,
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF444444)
    )
}

@Composable
private fun RegisterTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String
) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(placeholder)
        },
        shape = RoundedCornerShape(14.dp),
        singleLine = true
    )
}

@Composable
private fun PhotoCard() {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .clickable { },
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
                    color = Color(0xFFCCCCCC),
                    shape = RoundedCornerShape(16.dp)
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "📷",
                fontSize = 30.sp
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "Agregar foto",
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
private fun SituationCard(
    modifier: Modifier = Modifier,
    emoji: String,
    title: String,
    description: String,
    selected: Boolean,
    onClick: () -> Unit
) {

    val backgroundColor =
        if (selected) Color(0xFFE5F5F3)
        else Color.White

    val borderColor =
        if (selected) RegisterTeal
        else Color(0xFFE0E0E0)

    Card(
        modifier = modifier
            .height(165.dp)
            .border(
                width = if (selected) 2.dp else 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(18.dp)
            )
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(18.dp),
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
                fontSize = 29.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = description,
                fontSize = 11.sp,
                color = Color(0xFF666666)
            )
        }
    }
}