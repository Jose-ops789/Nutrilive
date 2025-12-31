package screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
fun HomeScreen(
    onAccountClick: () -> Unit = {},
    onMealClick: (String) -> Unit = {} // <-- por ahora solo avisa "desayuno", "almuerzo", etc.
) {
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

            // Calorías totales (mock)
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
                            Text(
                                "${CaloriesState.eatenCalories.intValue} kcal",
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("Burned", color = Color.Gray)
                            Text(
                                "${CaloriesState.eatenCalories.intValue} kcal",
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Macronutrientes (mock)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                NutrientCircle("Carbs", "0 / 224 g", Color(0xFFE57373))
                NutrientCircle("Protein", "0 / 128 g", Color(0xFFFFB74D))
                NutrientCircle("Fat", "0 / 138 g", Color(0xFF64B5F6))
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Actividad (mock)
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

            // ✅ Secciones de comida (como tu diseño)
            FoodSection(
                title = "Desayuno",
                imageRes = R.drawable.breakfast,
                kcal = 768,
                onClick = { onMealClick("desayuno") }
            )
            FoodSection(
                title = "Almuerzo",
                imageRes = R.drawable.lunch,
                kcal = 768,
                onClick = { onMealClick("almuerzo") }
            )
            FoodSection(
                title = "Cena",
                imageRes = R.drawable.dinner,
                kcal = 768,
                onClick = { onMealClick("cena") }
            )
            FoodSection(
                title = "Aperitivos",
                imageRes = R.drawable.snack,
                kcal = 256,
                onClick = { onMealClick("aperitivos") }
            )

            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}

@Composable
fun NutrientCircle(title: String, amount: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(70.dp)
                .background(Color.White, CircleShape)
                .padding(6.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .background(Color.Transparent, CircleShape)
                    .padding(6.dp)
            )
            // círculo simple (mock)
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .background(Color.Transparent, CircleShape)
            )
        }

        Spacer(modifier = Modifier.height(4.dp))
        Text(title, color = Color.Gray, fontWeight = FontWeight.Medium)
        Text(amount, fontSize = 12.sp, color = Color.LightGray)
    }
}

@Composable
fun ActivityCard(icon: String, label: String, kcal: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(icon, fontSize = 26.sp)
        Text(label, color = Color.Gray)
        Text(kcal, fontWeight = FontWeight.Bold)
    }
}

/**
 * ✅ ESTA ES LA FUNCIÓN QUE TE DABA ERROR
 * Ahora tiene el parámetro onClick, así ya no sale:
 * "No value passed for parameter 'onClick'"
 */
@Composable
fun FoodSection(
    title: String,
    @DrawableRes imageRes: Int,
    kcal: Int,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
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

@Preview(showSystemUi = true, name = "Home Screen - Full View")
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        onAccountClick = {},
        onMealClick = { mealType ->
            // solo para probar en preview
            println("Meal click: $mealType")
        }
    )
}
