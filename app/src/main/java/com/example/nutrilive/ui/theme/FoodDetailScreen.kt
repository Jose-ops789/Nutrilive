package com.example.nutrilive.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material3.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
        // 🔹 Encabezado
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
        // 🔸 Encabezado superior
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

        // 🔸 Imagen del alimento
        Icon(
            imageVector = Icons.Default.Fastfood,
            contentDescription = "Food Image",
            tint = Color(0xFFFFC107),
            modifier = Modifier.size(80.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 🔸 Nombre del alimento
        Text(
            text = "Hamburguesa con queso",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        // 🔸 Círculo con calorías
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

        // 🔸 Macronutrientes
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