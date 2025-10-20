package com.example.nutrilive.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.automirrored.filled.DirectionsRun
import androidx.compose.material.icons.automirrored.filled.DirectionsWalk
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material.icons.automirrored.filled.ShowChart
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Fireplace
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Whatshot
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun FoodDetailScreen() {
    var note by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        //  Encabezado
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Fastfood,
                    contentDescription = "Food icon",
                    tint = Color(0xFFFFC107),
                    modifier = Modifier.size(48.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = "Hamburguesa con queso",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "303 kcal · 150 g",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            }

            IconButton(onClick = { /* para acciones que futurass*/ }) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Cerrar",
                    tint = Color.Red
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 🔹 "Más información"
        Text(
            text = "Más información",
            color = Color.Gray,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.End,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        // 🔹 Nota para el entrenador
        Text(
            text = "Agregar una nota para el entrenar",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = note,
            onValueChange = { note = it },
            placeholder = { Text("Escribe aquí...") },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        // 🔹 Barra inferior
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(vertical = 12.dp, horizontal = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("kcal 303", fontWeight = FontWeight.Bold)

            Button(
                onClick = { /* sin acción */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5CD6C0)),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text("+ Ver (1)", color = Color.White)
            }
        }
    }
}
//pantalla que indica los componenetes de la comida
@Composable
fun FoodNutritionScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //  Encabezado superior
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /* sin acción */ }) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Cerrar"
                )
            }

            IconButton(onClick = { /* sin acción */ }) {
                Icon(
                    imageVector = Icons.Default.Fastfood,
                    contentDescription = "Favorito",
                    tint = Color(0xFF5CD6C0)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        //  Imagen del alimento
        Icon(
            imageVector = Icons.Default.Fastfood,
            contentDescription = "Food Image",
            tint = Color(0xFFFFC107),
            modifier = Modifier.size(80.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        //  Nombre del alimento
        Text(
            text = "Hamburguesa con queso",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        //  Círculo con calorías
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(150.dp)
        ) {
            CircularProgressIndicator(
            progress = { 0.75f },
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFFFF7043),
            strokeWidth = 8.dp,
            trackColor = ProgressIndicatorDefaults.circularIndeterminateTrackColor,
            strokeCap = ProgressIndicatorDefaults.CircularDeterminateStrokeCap,
            )
            Text(
                text = "303\nkcal",
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        //  Macronutrientes
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.fillMaxWidth(0.9f)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Carbs", color = Color(0xFFFF5722))
                Text("112.5 (37.2%)", color = Color.Gray)
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Protein", color = Color(0xFF2196F3))
                Text("57.1 (18.8%)", color = Color.Gray)
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Fat", color = Color(0xFFFFC107))
                Text("133.4 (44.0%)", color = Color.Gray)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider(thickness = 1.dp, color = Color(0xFFE0E0E0))
        Spacer(modifier = Modifier.height(8.dp))

        //  Minerales
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.fillMaxWidth(0.9f)
        ) {
            Text("Colesterol  53 mg (18%)", fontSize = 14.sp)
            Text("Sodio       663 mg (28%)", fontSize = 14.sp)
            Text("Hierro      2.2 mg (12%)", fontSize = 14.sp)
            Text("Potasio     257 mg (7%)", fontSize = 14.sp)
        }

        Spacer(modifier = Modifier.height(24.dp))

        //  Peso y cantidad
        Text("Weight", color = Color.Gray, fontSize = 14.sp)
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text("150 g", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(20.dp))

            OutlinedButton(
                onClick = { /* sin acción */ },
                shape = CircleShape,
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier.size(36.dp)
            ) { Text("-") }

            Text("1", fontSize = 16.sp, modifier = Modifier.padding(horizontal = 8.dp))

            OutlinedButton(
                onClick = { /* sin acción */ },
                shape = CircleShape,
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier.size(36.dp)
            ) { Text("+") }
        }

        Spacer(modifier = Modifier.height(20.dp))

        //  Botón Agregar
        Button(
            onClick = { /* sin acción */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5CD6C0)),
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(50.dp)
        ) {
            Text("+ Agregar", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}
@Composable
fun HomeVisualScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Contenido principal
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .padding(bottom = 70.dp), // espacio para la barra inferior
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Encabezado
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Inicio",
                    tint = Color(0xFF5CD6C0),
                    modifier = Modifier.size(28.dp)
                )

                Text(
                    text = "NutriLife",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Perfil",
                    tint = Color.Gray,
                    modifier = Modifier.size(28.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))


            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F9F9))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Hoy, 22 de diciembre", color = Color.Gray, fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(12.dp))

                    // Círculo central
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.size(140.dp)
                    ) {
                        CircularProgressIndicator(
                        progress = { 0.65f },
                        modifier = Modifier.fillMaxSize(),
                        color = Color(0xFF5CD6C0),
                        strokeWidth = 10.dp,
                        trackColor = ProgressIndicatorDefaults.circularIndeterminateTrackColor,
                        strokeCap = ProgressIndicatorDefaults.CircularDeterminateStrokeCap,
                        )
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("2190", fontWeight = FontWeight.Bold, fontSize = 28.sp)
                            Text("kcal left", color = Color.Gray, fontSize = 14.sp)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))


                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("Eaten", fontWeight = FontWeight.Bold)
                            Text("1634 kcal", color = Color.Gray, fontSize = 13.sp)
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("Burned", fontWeight = FontWeight.Bold)
                            Text("265 kcal", color = Color.Gray, fontSize = 13.sp)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))


                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("Carbs", color = Color(0xFFFF7043))
                            Text("168 / 224 g", fontSize = 13.sp)
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("Protein", color = Color(0xFF2196F3))
                            Text("83 / 120 g", fontSize = 13.sp)
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("Fat", color = Color(0xFFFFC107))
                            Text("70 / 138 g", fontSize = 13.sp)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Actividad física
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("Caminando", color = Color.Gray, fontSize = 13.sp)
                            Text("100 kcal", fontWeight = FontWeight.Bold)
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("Actividad", color = Color.Gray, fontSize = 13.sp)
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text("165 kcal", fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.width(4.dp))
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "Agregar",
                                    tint = Color(0xFF5CD6C0),
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 🔸 Lista de comidas
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                MealItem("Desayuno", "824 / 768 kcal", checked = true)
                MealItem("Almuerzo", "810 / 768 kcal", checked = true)
                MealItem("Cena", "303 / 768 kcal", checked = false)
                MealItem("Aperitivos", "0 / 256 kcal", checked = false)
            }
        }

        // 🔹 Barra inferior fija
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(Color.White)
                .padding(vertical = 12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.Home, contentDescription = "Inicio", tint = Color(0xFF5CD6C0))
                Icon(Icons.Default.Person, contentDescription = "Perfil", tint = Color.Gray)
                Icon(Icons.Default.Settings, contentDescription = "Ajustes", tint = Color.Gray)
            }
        }
    }
}

//  Componente de ítem de comida
@Composable
fun MealItem(title: String, subtitle: String, checked: Boolean) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F9F9))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(subtitle, color = Color.Gray, fontSize = 13.sp)
            }
            if (checked) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Completado",
                    tint = Color(0xFF5CD6C0)
                )
            } else {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Agregar",
                    tint = Color(0xFF5CD6C0)
                )
            }
        }
    }
}
@Composable
fun TrainerPlanScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9F9F9))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .padding(bottom = 70.dp) // deja espacio para la barra inferior
        ) {
            // 🔹 Título principal
            Text(
                text = "Plan de Entrenador",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            // 🔹 Tarjetas de objetivos
            TrainerPlanItem("Plan de desayuno", "500 kcal", Icons.Default.Fireplace, true)
            TrainerPlanItem("Plan de entrenamiento", "200 kcal burned",
                Icons.AutoMirrored.Filled.DirectionsRun, true)
            TrainerPlanItem("Objetivo de pasos", "500 kcal", Icons.Default.Fireplace, false)

            Spacer(modifier = Modifier.height(16.dp))

            HorizontalDivider(thickness = 1.dp, color = Color(0xFFE0E0E0))
            Spacer(modifier = Modifier.height(12.dp))

            // 🔹 Nota del entrenador
            Text(
                text = "Nota del entrenador",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            // 🔹 Contenedor de notas
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text("• Centrar en la ingesta de proteínas.", fontWeight = FontWeight.SemiBold)
                    Text(
                        "Añade un huevo o tofu para obtener más proteínas.",
                        color = Color(0xFF4CAF50),
                        fontSize = 13.sp
                    )

                    Text("• Mantente hidratada", fontWeight = FontWeight.SemiBold)
                    Text(
                        "¡No olvides hidratarte después de tu entrenamiento!",
                        color = Color(0xFF4CAF50),
                        fontSize = 13.sp
                    )

                    Text("• Centrarse en Cardio.", fontWeight = FontWeight.SemiBold)
                    Text(
                        "Intenta alcanzar los 10.000 pasos hoy 💪",
                        color = Color(0xFF4CAF50),
                        fontSize = 13.sp
                    )
                }
            }
        }

        // 🔹 Barra inferior fija
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(Color.White)
                .padding(vertical = 12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.Home, contentDescription = "Inicio", tint = Color.Gray)
                Icon(Icons.Default.Fireplace, contentDescription = "Plan", tint = Color(0xFF5CD6C0))
                Icon(Icons.Default.Settings, contentDescription = "Ajustes", tint = Color.Gray)
                Icon(Icons.Default.Person, contentDescription = "Cuenta", tint = Color.Gray)
            }
        }
    }
}

@Composable
fun TrainerPlanItem(title: String, subtitle: String, icon: androidx.compose.ui.graphics.vector.ImageVector, checked: Boolean) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(title, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(icon, contentDescription = null, tint = Color(0xFFFF9800), modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(subtitle, color = Color.Gray, fontSize = 13.sp)
                }
            }

            if (checked) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Completado",
                    tint = Color(0xFF5CD6C0),
                    modifier = Modifier.size(26.dp)
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(Color(0xFFE0E0E0), RoundedCornerShape(6.dp))
                )
            }
        }
    }
}
@Composable
fun AccountScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9F9F9))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .padding(bottom = 70.dp)
        ) {
            // 🔹 Encabezado
            Text(
                text = "Cuenta",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // 🔹 Perfil de usuario (placeholder)
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                                .background(Color.LightGray)
                                .border(1.dp, Color.Gray, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("J", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column {
                            Text("Jose", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                            Text(
                                "jose.ainsley@yourdomain.com",
                                color = Color.Gray,
                                fontSize = 13.sp
                            )
                        }
                    }

                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // 🔹 Opciones principales
            SettingsSection(
                listOf(
                    Pair("Contador de calorías", Icons.Default.Whatshot),
                    Pair("Contador de pasos", Icons.AutoMirrored.Filled.DirectionsWalk),
                    Pair("Preferencias", Icons.Default.Settings)
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 🔹 Opciones secundarias
            SettingsSection(
                listOf(
                    Pair("Notificación", Icons.Default.Notifications),
                    Pair("Apariencia de la aplicación", Icons.Default.Palette),
                    Pair("Ayuda y soporte", Icons.AutoMirrored.Filled.HelpOutline)
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 🔹 Cerrar sesión
            Text(
                text = "Cerrar sesión",
                color = Color(0xFFE53935),
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        // 🔹 Barra inferior fija
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(Color.White)
                .padding(vertical = 12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.Home, contentDescription = "Inicio", tint = Color.Gray)
                Icon(Icons.Default.Description, contentDescription = "Plan", tint = Color.Gray)
                Icon(Icons.AutoMirrored.Filled.ShowChart, contentDescription = "Insights", tint = Color.Gray)
                Icon(Icons.Default.Person, contentDescription = "Cuenta", tint = Color(0xFF5CD6C0))
            }
        }
    }
}

// 🔸 Reutilizable para las secciones de configuración
@Composable
fun SettingsSection(items: List<Pair<String, androidx.compose.ui.graphics.vector.ImageVector>>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column {
            items.forEachIndexed { index, item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = item.second,
                            contentDescription = null,
                            tint = Color(0xFF5CD6C0),
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(item.first, fontSize = 15.sp)
                    }

                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.size(16.dp)
                    )
                }

                // Separador entre elementos
                if (index != items.lastIndex) {
                    HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)
                }
            }
        }
    }
}

@Composable
fun Divider() {
    TODO("Not yet implemented")
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewFoodDetailScreen() {
    FoodDetailScreen()
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewFoodNutritionScreen() {
    FoodNutritionScreen()
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewHomeVisualScreen() {
    HomeVisualScreen()
}
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewTrainerPlanScreen() {
    TrainerPlanScreen()
}
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewAccountScreen() {
    AccountScreen   ()
}