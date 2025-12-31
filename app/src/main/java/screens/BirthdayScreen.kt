package screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BirthdayScreen(onContinue: (String, String, String) -> Unit) {
    var day by remember { mutableStateOf("") }
    var month by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Progreso
            LinearProgressIndicator(
                progress = {3f / 7f},
                color = Color(0xFF47B8C9),
                trackColor = Color.LightGray,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
            )

            Text(
                text = "3/7",
                color = Color.Gray,
                modifier = Modifier.align(Alignment.End).padding(top = 4.dp)
            )

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "¿Cuándo es tu cumpleaños?",
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(50.dp))

            // Campos Día, Mes, Año
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                BirthdayField(label = "Día", value = day, onValueChange = { day = it })
                BirthdayField(label = "Mes", value = month, onValueChange = { month = it })
                BirthdayField(label = "Año", value = year, onValueChange = { year = it })
            }
        }

        // Botón Continuar
        Button(
            onClick = {
                if (day.isNotBlank() && month.isNotBlank() && year.isNotBlank()) {
                    onContinue(day, month, year)
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF47B8C9)),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(50)
        ) {
            Text("Continuar", color = Color.White)
        }
    }
}
// fecha de cumpleaños

@Preview(
    showSystemUi = true,
    name = "Birthday Input Screen")
@Composable
fun BirthdayScreenPreview() {
    BirthdayScreen(
        onContinue = { day, month, year ->
        }
    )
}

@Composable
fun BirthdayField(label: String, value: String, onValueChange: (String) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(label, color = Color.Gray)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            modifier = Modifier
                .width(90.dp)
                .height(56.dp),
            shape = RoundedCornerShape(8.dp)
        )
    }
}

@Preview(name = "Birthday Field - Empty", showBackground = true)
@Composable
fun BirthdayFieldEmptyPreview() {
    // 1. Mostrar el campo vacío (estado inicial)
    BirthdayField(
        label = "Día",
        value = "",
        onValueChange = { }
    )
}

@Preview(name = "Birthday Field - Filled", showBackground = true)
@Composable
fun BirthdayFieldFilledPreview() {
    // 2. Mostrar el campo con un valor (estado de llenado)
    BirthdayField(
        label = "Año",
        value = "2001",
        onValueChange = { }
    )
}