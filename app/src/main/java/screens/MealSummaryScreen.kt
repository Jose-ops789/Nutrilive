package screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealSummaryScreen(
    mealType: String,
    foods: List<FoodItem>,
    navController: NavController
) {
    val totalCalories = foods.sumOf { it.calories }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = mealType.replaceFirstChar { it.uppercase() },
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {

            Text(
                text = "Total: $totalCalories kcal",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(foods) { food ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(food.name)
                        Text("${food.calories} kcal")
                    }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {

                    val calories = foods.sumOf { it.calories }

                    when (mealType.lowercase()) {
                        "desayuno" -> CaloriesState.breakfastCalories.intValue += calories
                        "almuerzo" -> CaloriesState.lunchCalories.intValue += calories
                        "cena" -> CaloriesState.dinnerCalories.intValue += calories
                        "aperitivos" -> CaloriesState.snacksCalories.intValue += calories
                    }

                    CaloriesState.eatenCalories.intValue =
                        CaloriesState.breakfastCalories.intValue +
                                CaloriesState.lunchCalories.intValue +
                                CaloriesState.dinnerCalories.intValue +
                                CaloriesState.snacksCalories.intValue

                    navController.navigate("home") {
                        popUpTo("home") { inclusive = true }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar comida")
            }

        }
    }
}
