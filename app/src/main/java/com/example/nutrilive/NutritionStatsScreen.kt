package com.example.nutrilive

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NutritionStatsScreen() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Mis Estadísticas", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        containerColor = Color(0xFFF5F5F5) // Gris claro de fondo
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                NutrientCard(
                    title = "Carbohidratos",
                    percentage = "84%",
                    description = "Ligeramente bajo en carbohidratos: considere agregar un refrigerio con carbohidratos complejos.",
                    plannedValue = 240f,
                    actualValue = 200f,
                    maxY = 280f,
                    plannedColor = Color(0xFFEF4444), // Rojo
                    actualColor = Color(0xFF4FB3D3)   // Azul
                )
            }
            item {
                NutrientCard(
                    title = "Proteína",
                    percentage = "93%",
                    description = "Buen trabajo con las proteínas: tus músculos te lo agradecerán.",
                    plannedValue = 150f,
                    actualValue = 120f,
                    maxY = 180f,
                    plannedColor = Color(0xFFF59E0B), // Naranja
                    actualColor = Color(0xFF4FB3D3)   // Azul
                )
            }
        }
    }
}

@Composable
fun NutrientCard(
    title: String,
    percentage: String,
    description: String,
    plannedValue: Float,
    actualValue: Float,
    maxY: Float,
    plannedColor: Color,
    actualColor: Color
) {
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(24.dp)
        ) {
            // Título y Porcentaje
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "$title - ",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = percentage,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Descripción
            Text(
                text = description,
                fontSize = 14.sp,
                color = Color.Gray,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(30.dp))

            // Gráfica Personalizada
            Box(
                modifier = Modifier
                    .height(220.dp)
                    .fillMaxWidth()
            ) {
                // 1. Dibujar los Ejes (Línea negra izquierda y abajo)
                Canvas(modifier = Modifier.matchParentSize()) {
                    val strokeWidth = 3.dp.toPx()

                    // Eje Y (Izquierda)
                    drawLine(
                        color = Color.Black,
                        start = Offset(x = 40.dp.toPx(), y = 0f),
                        end = Offset(x = 40.dp.toPx(), y = size.height - 20.dp.toPx()),
                        strokeWidth = strokeWidth,
                        cap = StrokeCap.Round
                    )

                    // Eje X (Abajo)
                    drawLine(
                        color = Color.Black,
                        start = Offset(x = 30.dp.toPx(), y = size.height - 20.dp.toPx()), // Un poco antes del eje Y para cruzarlo
                        end = Offset(x = size.width, y = size.height - 20.dp.toPx()),
                        strokeWidth = strokeWidth,
                        cap = StrokeCap.Round
                    )
                }

                // 2. Números del Eje Y
                Column(
                    modifier = Modifier.fillMaxHeight().padding(bottom = 20.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = maxY.toInt().toString(), fontSize = 12.sp, color = Color.Gray)
                    Text(text = (maxY * 0.8).toInt().toString(), fontSize = 12.sp, color = Color.Gray)
                    Text(text = " ", fontSize = 12.sp) // Espacio vacío para alinear
                }

                // 3. Las Barras
                Row(
                    modifier = Modifier
                        .padding(start = 50.dp, bottom = 20.dp) // Deja espacio para el eje Y y X
                        .fillMaxSize(),
                    crossAxisAlignment = CrossAxisAlignment.End,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    // Barra Planificado
                    BarColumn(
                        value = plannedValue,
                        max = maxY,
                        color = plannedColor,
                        label = "Planificado"
                    )

                    // Barra Actual
                    BarColumn(
                        value = actualValue,
                        max = maxY,
                        color = actualColor,
                        label = "Actual"
                    )
                }

                // Texto "g" debajo del eje Y
                Text(
                    text = "g",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    modifier = Modifier.align(Alignment.BottomStart).padding(start = 8.dp)
                )
            }
        }
    }
}

@Composable
fun BarColumn(value: Float, max: Float, color: Color, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 8.dp)
    ) {
        // La Barra en sí
        Box(
            modifier = Modifier
                .width(50.dp) // Ancho de la barra
                .fillMaxHeight(fraction = value / max) // Altura basada en porcentaje
                .clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp)) // Bordes redondos arriba
                .background(color)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Etiqueta (Planificado / Actual)
        Text(text = label, fontSize = 12.sp, color = Color.Gray)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewStats() {
    NutritionStatsScreen()
}
// hace falta insertar en el setContent, llama a la función NutritionStatsScreen():