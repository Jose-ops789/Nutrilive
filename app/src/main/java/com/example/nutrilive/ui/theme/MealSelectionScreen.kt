package com.example.nutrilive.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


data class FoodItem(
    val name: String,
    val calories: Int,
    val grams: Int
)
@Suppress("UNUSED_PARAMETER")
// Función principal
@Composable
fun MealSelectionScreen(
    mealType: String,
    navController: NavController? = null,
    onAddFood: (FoodItem) -> Unit = {}
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedTab by remember { mutableStateOf("Reciente") }

    // 🟢 Lista de seleccionados
    var selectedFoods by remember { mutableStateOf(listOf<FoodItem>()) }

    val foods = sampleFoods(mealType)
        .filter { it.name.contains(searchQuery, ignoreCase = true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        // 🔹 Encabezado
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = mealType.replaceFirstChar { it.uppercase() },
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
            IconButton(onClick = { navController?.popBackStack() }) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Cerrar"
                )
            }
        }

        // 🔹 Buscador
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("Buscar alimento...") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 🔹 Tabs
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            listOf("Reciente", "Favoritos", "Personal").forEach { tab ->
                TextButton(
                    onClick = { selectedTab = tab },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = if (selectedTab == tab) Color(0xFF5CD6C0) else Color.Gray
                    )
                ) {
                    Text(tab, fontWeight = FontWeight.Bold)
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // 🔹 Lista de alimentos
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(foods) { food ->
                val isSelected = selectedFoods.contains(food)

                FoodItemCard(
                    food = food,
                    isSelected = isSelected,
                    onClick = {
                        selectedFoods = if (isSelected) {
                            selectedFoods - food
                        } else {
                            selectedFoods + food
                        }
                    }
                )
            }
        }

        // 🔹 Barra inferior cuando hay seleccionados
        if (selectedFoods.isNotEmpty()) {
            val totalKcal = selectedFoods.sumOf { it.calories }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(vertical = 12.dp, horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("kcal $totalKcal", fontWeight = FontWeight.Bold)

                Button(
                    onClick = {
                        // Más adelante conectaremos esto a la pantalla de detalle
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5CD6C0)),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text("Ver (${selectedFoods.size})", color = Color.White)
                }
            }
        }
    }
}

// 🔹 Modificamos FoodItemCard para mostrar check o botón "+"
@Composable
fun FoodItemCard(food: FoodItem, isSelected: Boolean, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F9F9))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = food.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "${food.calories} kcal · ${food.grams} g",
                    fontSize = 13.sp,
                    color = Color.Gray
                )
            }

            if (isSelected) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Seleccionado",
                    tint = Color(0xFF5CD6C0),
                    modifier = Modifier.size(28.dp)
                )
            } else {
                IconButton(
                    onClick = onClick,
                    modifier = Modifier
                        .size(32.dp)
                        .background(Color(0xFF5CD6C0), CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Agregar",
                        tint = Color.White
                    )
                }
            }
        }
    }
}

// Lista de alimentos según tipo
fun sampleFoods(mealType: String): List<FoodItem> {
    return when (mealType.lowercase()) {
        "desayuno" -> listOf(
            FoodItem("Avena", 150, 100),
            FoodItem("Pan integral con palta", 220, 120),
            FoodItem("Huevos revueltos", 180, 90),
            FoodItem("Yogur natural", 120, 150),
            FoodItem("Batido de frutas", 200, 250)
        )
        "almuerzo" -> listOf(
            FoodItem("Pechuga de pollo", 300, 150),
            FoodItem("Arroz integral", 210, 150),
            FoodItem("Ensalada mixta", 90, 200),
            FoodItem("Sopa de verduras", 120, 250),
            FoodItem("Pasta con atún", 350, 200)
        )
        "cena" -> listOf(
            FoodItem("Hamburguesa con queso", 303, 150),
            FoodItem("Puré de papas", 240, 200),
            FoodItem("Pizza", 200, 100),
            FoodItem("Huevo revuelto", 160, 80),
            FoodItem("Pollo a la parrilla", 350, 300)
        )
        "snack", "aperitivos" -> listOf(
            FoodItem("Barra de cereal", 120, 50),
            FoodItem("Yogur griego", 100, 100),
            FoodItem("Frutos secos", 180, 40),
            FoodItem("Manzana", 80, 120),
            FoodItem("Palomitas de maíz", 150, 100)
        )
        else -> emptyList()
    }
}
