package com.example.nutrilive

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Article
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
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
import androidx.navigation.compose.rememberNavController
import com.example.nutrilive.ui.theme.NutriliveTheme
import navigation.AppNavigation


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NutriliveTheme {
                val navController = rememberNavController()
                Surface(modifier = Modifier.fillMaxSize()) {
                    AppNavigation(navController)
                }
            }
        }
    }
}



@Preview(showSystemUi = true, name = "App Navigation")
@Composable
fun AppNavigationPreview() {
    val navController = rememberNavController()
    AppNavigation(navController = navController)
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





































