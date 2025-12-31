package screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Article
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
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
@Composable
fun NutrientCircle(title: String, amount: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(70.dp)
                .border(4.dp, color, CircleShape)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("0", fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(title, color = Color.Gray, fontWeight = FontWeight.Medium)
        Text(amount, fontSize = 12.sp, color = Color.LightGray)
    }
}

@Preview(name = "Nutrient Circle - Carbs", showBackground = true)
@Composable
fun NutrientCircleCarbsPreview() {
    // Previsualización para Carbs
    NutrientCircle(
        title = "Carbs",
        amount = "0 / 224 g",
        color = Color(0xFFE57373) // Color de ejemplo
    )
}

@Preview(name = "Nutrient Circle - Protein", showBackground = true)
@Composable
fun NutrientCircleProteinPreview() {
    // Previsualización para Protein
    NutrientCircle(
        title = "Protein",
        amount = "0 / 128 g",
        color = Color(0xFFFFB74D) // Color de ejemplo
    )
}

@Composable
fun ActivityCard(icon: String, label: String, kcal: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(icon, fontSize = 26.sp)
        Text(label, color = Color.Gray)
        Text(kcal, fontWeight = FontWeight.Bold)
    }
}

@Preview(name = "Activity Card - Walking", showBackground = true)
@Composable
fun ActivityCardWalkingPreview() {
    // Ejemplo de previsualización para la actividad "Walking"
    ActivityCard(
        icon = "🚶‍♂️", // Un emoji de ejemplo
        label = "Walking",
        kcal = "0 kcal"
    )
}

@Preview(name = "Activity Card - Activity", showBackground = true)
@Composable
fun ActivityCardActivityPreview() {
    // Ejemplo de previsualización para la actividad genérica
    ActivityCard(
        icon = "💪", // Otro emoji de ejemplo
        label = "Activity",
        kcal = "0 kcal"
    )
}

@Composable
fun FoodSection(title: String, @DrawableRes imageRes: Int, kcal: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = title,
                    modifier = Modifier.size(50.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(title, fontWeight = FontWeight.Bold)
                    Text("0 / $kcal kcal", color = Color.Gray)
                }
            }
            Icon(
                imageVector = Icons.Default.AddCircleOutline,
                contentDescription = "Agregar",
                tint = Color.Gray
            )
        }
    }
}

@Preview(name = "Food Section - Breakfast", showBackground = true)
@Composable
fun FoodSectionBreakfastPreview() {
    FoodSection(
        title = "Desayuno",
        imageRes = android.R.drawable.ic_dialog_info,
        kcal = 768
    )
}

@Preview(name = "Food Section - Snack", showBackground = true)
@Composable
fun FoodSectionSnackPreview() {
    FoodSection(
        title = "Aperitivos",
        imageRes = android.R.drawable.ic_input_add,
        kcal = 256
    )
}

@Composable
fun BottomNavigationBar() {
    NavigationBar(containerColor = Color.White) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Inicio") },
            label = { Text("Inicio") },
            selected = true,
            onClick = {}
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.CalendarToday, contentDescription = "Plan") },
            label = { Text("Plan") },
            selected = false,
            onClick = {}
        )
        NavigationBarItem(
            icon = { Icon(Icons.AutoMirrored.Filled.Article, contentDescription = "Artículos") },
            label = { Text("Artículos") },
            selected = false,
            onClick = {}
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = "Cuenta") },
            label = { Text("Cuenta") },
            selected = false,
            onClick = {}
        )
    }
}

@Preview(name = "Bottom Navigation Bar", showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar()
}