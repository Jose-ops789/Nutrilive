package screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CaloriePlanScreen(
    onStartPlan: () -> Unit,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Botón volver
            IconButton(onClick = onBack, modifier = Modifier.align(Alignment.Start)) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Volver",
                    tint = Color.Black
                )
            }

            // Progreso
            LinearProgressIndicator(
                progress = { 1f },
                color = Color(0xFF47B8C9),
                trackColor = Color.LightGray,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
            )
            Text(
                text = "7/7",
                color = Color.Gray,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 4.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Título
            Text(
                text = "¡Tu plan de calorías personalizado está listo!",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Gráfico circular
            Box(contentAlignment = Alignment.Center) {
                Canvas(modifier = Modifier.size(200.dp)) {
                    val strokeWidth = 30.dp.toPx()
                    size.minDimension / 2
                    val style = Stroke(width = strokeWidth, cap = StrokeCap.Round)

                    // Porciones
                    val segments = listOf(
                        Pair(Color(0xFFE74C3C), 0.35f), // Carbs
                        Pair(Color(0xFF3498DB), 0.45f), // Fats
                        Pair(Color(0xFFF39C12), 0.20f)  // Protein
                    )

                    var startAngle = -90f
                    for ((color, proportion) in segments) {
                        val sweepAngle = 360 * proportion
                        drawArc(
                            color = color,
                            startAngle = startAngle,
                            sweepAngle = sweepAngle,
                            useCenter = false,
                            style = style
                        )
                        startAngle += sweepAngle
                    }
                }

                // Texto central
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("2560", fontSize = 36.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                    Text("kcal", color = Color.Gray)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Leyenda
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                LegendItem(Color(0xFFE74C3C), "Carbs")
                LegendItem(Color(0xFF3498DB), "Fats")
                LegendItem(Color(0xFFF39C12), "Protein")
            }
        }

        // Botón inferior
        Button(
            onClick = onStartPlan,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF47B8C9)),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(50)
        ) {
            Text("Comienza tu plan ahora", color = Color.White)
        }
    }
}

@Preview(
    showSystemUi = true,
    name = "Calorie Plan Summary Screen"
)
@Composable
fun CaloriePlanScreenPreview() {
    CaloriePlanScreen(
        onStartPlan = {
        },
        onBack = {
        }
    )
}
@Composable
fun LegendItem(color: Color, label: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(12.dp)
                .background(color = color, shape = CircleShape)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(label, color = Color.Black)
    }
}

@Preview(name = "Legend Item - Carbs", showBackground = true)
@Composable
fun LegendItemCarbsPreview() {
    LegendItem(
        color = Color(0xFFE74C3C), // Color para Carbs
        label = "Carbs"
    )
}

