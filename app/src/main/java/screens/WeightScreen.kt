package screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WeightScreen(
    onContinue: (Int) -> Unit,
    onBack: () -> Unit
) {
    var weightText by remember { mutableStateOf("") }
    val weight = weightText.toIntOrNull()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            // Botón volver
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Volver",
                    tint = Color.Black
                )
            }

            // Barra de progreso 5/7
            LinearProgressIndicator(
                progress = { 5f / 7f },
                color = Color(0xFF47B8C9),
                trackColor = Color.LightGray,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
            )
            Text(
                text = "5/7",
                color = Color.Gray,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 4.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Título
            Text(
                text = "¿Cuál es tu peso actual?",
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Etiqueta
            Text("Peso (en kg)", color = Color.Gray)

            Spacer(modifier = Modifier.height(8.dp))

            // Campo numérico
            OutlinedTextField(
                value = weightText,
                onValueChange = { text ->
                    if (text.all { it.isDigit() }) weightText = text
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                shape = RoundedCornerShape(8.dp),
                placeholder = { Text("Ej: 70") }
            )
        }

        // Botón continuar
        val isValid = (weight != null && weight in 30..300)
        Button(
            onClick = { weight?.let { onContinue(it) } },
            enabled = isValid,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isValid) Color(0xFF47B8C9) else Color.LightGray
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(50)
        ) {
            Text("Continuar", color = Color.White)
        }
    }
}

@Preview(
    showSystemUi = true,
    name = "Weight Input Screen"
)
@Composable
fun WeightScreenPreview() {
    WeightScreen(
        onContinue = { weightKg ->
        },
        onBack = {
        }
    )
}
