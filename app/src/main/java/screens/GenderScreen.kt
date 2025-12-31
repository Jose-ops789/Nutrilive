package screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Female
import androidx.compose.material.icons.filled.Male
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GenderScreen(
    onContinue: (String) -> Unit,
    onBack: () -> Unit
) {
    var selectedGender by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Column {
            // Botón de retroceso
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver", tint = Color.Black)
            }

            // Barra de progreso
            LinearProgressIndicator(
                progress = { 2f / 7f },
                color = Color(0xFF6CE5E8),
                trackColor = Color.LightGray,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .padding(bottom = 8.dp)
            )

            // Texto de paso
            Text(
                text = "2/7",
                color = Color.Gray,
                modifier = Modifier.align(Alignment.End)
            )

            Spacer(modifier = Modifier.height(40.dp))

            // Título
            Text(
                text = "¿Cuál es tu género?",
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(60.dp))

            // Opciones de género
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                GenderOption(
                    label = "Masculino",
                    icon = Icons.Default.Male,
                    isSelected = selectedGender == "Masculino",
                    onClick = { selectedGender = "Masculino" }
                )
                GenderOption(
                    label = "Femenino",
                    icon = Icons.Default.Female,
                    isSelected = selectedGender == "Femenino",
                    onClick = { selectedGender = "Femenino" }
                )
            }
        }

        // Botón inferior
        // Animación suave de color según selección
        val buttonColor by animateColorAsState(
            targetValue = if (selectedGender != null) Color(0xFF47B8C9) else Color.LightGray,
            animationSpec = tween(durationMillis = 500)
        )

        Button(
            onClick = { selectedGender?.let { onContinue(it) } },
            enabled = selectedGender != null,
            colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
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
    name = "Gender Selection Screen"
)
@Composable
fun GenderScreenPreview() {
    GenderScreen(
        onContinue = { gender ->
        },
        onBack = {
        }
    )
}

@Composable
fun GenderOption(label: String, icon: ImageVector, isSelected: Boolean, onClick: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(if (isSelected) Color(0xFF6CE5E8) else Color.Transparent)
                .border(
                    width = 2.dp,
                    color = if (isSelected) Color.Transparent else Color.Black,
                    shape = CircleShape
                )
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = label, tint = Color.Black, modifier = Modifier.size(32.dp))
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(label, color = Color.Black, fontSize = 16.sp)
    }
}

@Preview(name = "Gender Option - Unselected", showBackground = true)
@Composable
fun GenderOptionUnselectedPreview() {
    GenderOption(
        label = "Masculino",
        icon = Icons.Default.Male,
        isSelected = false, // Estado inicial: NO seleccionado
        onClick = { }
    )
}

@Preview(name = "Gender Option - Selected", showBackground = true)
@Composable
fun GenderOptionSelectedPreview() {
    GenderOption(
        label = "Femenino",
        icon = Icons.Default.Female,
        isSelected = true, // Estado: SELECCIONADO
        onClick = { }
    )
}