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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nutrilive.ActivityCard
import com.example.nutrilive.BottomNavigationBar
import com.example.nutrilive.NutrientCircle
import com.example.nutrilive.R

@Composable
fun HomeScreen(onAccountClick: () -> Unit = {}) {
    Scaffold(
        bottomBar = { BottomNavigationBar() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .background(Color(0xFFF9F9F9))
                .padding(16.dp)
        ) {
            // Encabezado superior
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.logo_nutrilife),
                    contentDescription = "Logo",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(40.dp)
                )

                Text(
                    text = "NutriLife",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                IconButton(onClick = onAccountClick) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Notificaciones",
                        tint = Color.DarkGray
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Fecha
            Text(
                text = "Hoy, 22 de diciembre",
                fontSize = 16.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Calorías totales
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("2560", fontSize = 38.sp, fontWeight = FontWeight.Bold)
                    Text("kcal left", color = Color.Gray)
                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("Eaten", color = Color.Gray)
                            Text("0 kcal", fontWeight = FontWeight.Bold)
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("Burned", color = Color.Gray)
                            Text("0 kcal", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Macronutrientes
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                NutrientCircle("Carbs", "0 / 224 g", Color(0xFFE57373))
                NutrientCircle("Protein", "0 / 128 g", Color(0xFFFFB74D))
                NutrientCircle("Fat", "0 / 138 g", Color(0xFF64B5F6))
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Actividad
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ActivityCard("🚶‍♂️", "Walking", "0 kcal")
                ActivityCard("💪", "Activity", "0 kcal")
            }

            Spacer(modifier = Modifier.height(24.dp))

            /*
            // Secciones de comida con imágenes
            FoodSection("Desayuno", R.drawable.breakfast, 768)
            FoodSection("Almuerzo", R.drawable.lunch, 768)
            FoodSection("Cena", R.drawable.dinner, 768)
            FoodSection("Aperitivos", R.drawable.snack, 256)*/

            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}

@Preview(
    showSystemUi = true,
    name = "Home Screen - Full View"
)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        onAccountClick = {
        }
    )
}
