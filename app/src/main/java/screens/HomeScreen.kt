package screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nutrilive.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/* ---------------------- COLORS ---------------------- */

private val Background = Color(0xFFF4F6F8)
private val Primary = Color(0xFF3A7BD5)
private val PrimarySoft = Color(0xFFE8F0FB)
private val TextMain = Color(0xFF1C1C1E)
private val TextSecondary = Color(0xFF6E6E73)

/* ---------------------- HOME ---------------------- */

@Composable
fun HomeScreen(
    onAccountClick: () -> Unit = {},
    onMealClick: (String) -> Unit = {}
) {
    Scaffold(
        containerColor = Background,
        bottomBar = { BottomNavigationBar() }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {

            Header(onAccountClick)

            Spacer(modifier = Modifier.height(16.dp))

            CaloriesCard()

            Spacer(modifier = Modifier.height(24.dp))

            MealsSection(onMealClick)

            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}

/* ---------------------- HEADER ---------------------- */

@Composable
private fun Header(onAccountClick: () -> Unit) {
    val dateText = remember {
        val formatter = SimpleDateFormat("EEEE, dd MMMM", Locale.getDefault())
        formatter.format(Date())
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text("Hoy", color = TextSecondary, fontSize = 14.sp)
            Text(
                dateText,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextMain
            )
        }

        IconButton(onClick = onAccountClick) {
            Icon(
                Icons.Default.Notifications,
                contentDescription = null,
                tint = TextSecondary
            )
        }
    }
}

/* ---------------------- CALORIES ---------------------- */

@Composable
private fun CaloriesCard() {

    val animatedCalories = androidx.compose.animation.core.animateIntAsState(
        targetValue = CaloriesState.leftCalories.intValue,
        label = "calories-animation"
    )

    val progress = CaloriesState.eatenCalories.intValue.toFloat() /
            CaloriesState.totalCalories.intValue.toFloat()

    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = PrimarySoft),
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                "Calorías restantes",
                color = TextSecondary,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(160.dp)
            ) {

                CircularProgressIndicator(
                progress = { progress.coerceIn(0f, 1f) },
                modifier = Modifier.fillMaxSize(),
                color = Primary,
                strokeWidth = 10.dp,
                trackColor = Color.White,
                strokeCap = ProgressIndicatorDefaults.CircularDeterminateStrokeCap,
                )

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        "${animatedCalories.value}",
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold,
                        color = Primary
                    )
                    Text("kcal", color = TextSecondary)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Stat("Consumidas", CaloriesState.eatenCalories.intValue)
                Stat("Restantes", CaloriesState.leftCalories.intValue)
            }
        }
    }
}



@Composable
private fun Stat(label: String, value: Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(label, color = TextSecondary, fontSize = 13.sp)
        Text(
            "$value kcal",
            fontWeight = FontWeight.SemiBold,
            color = TextMain
        )
    }
}

/* ---------------------- MEALS ---------------------- */

@Composable
private fun MealsSection(onMealClick: (String) -> Unit) {
    Text(
        "Comidas",
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = TextMain
    )

    Spacer(modifier = Modifier.height(12.dp))

    MealItem("Desayuno", R.drawable.breakfast) { onMealClick("desayuno") }
    MealItem("Almuerzo", R.drawable.lunch) { onMealClick("almuerzo") }
    MealItem("Cena", R.drawable.dinner) { onMealClick("cena") }
    MealItem("Aperitivos", R.drawable.snack) { onMealClick("aperitivos") }
}

/* ---------------------- MEAL ITEM ---------------------- */

@Composable
fun MealItem(
    title: String,
    @DrawableRes image: Int,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier.size(44.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = TextMain,
                modifier = Modifier.weight(1f)
            )

            Icon(
                Icons.Default.AddCircleOutline,
                contentDescription = null,
                tint = Primary,
                modifier = Modifier.size(26.dp)
            )
        }
    }
}

/* ---------------------- BOTTOM NAV ---------------------- */

@Composable
fun BottomNavigationBar() {
    NavigationBar(containerColor = Color.White, tonalElevation = 4.dp) {

        NavigationBarItem(
            selected = true,
            onClick = {},
            icon = { Icon(Icons.Default.Home, null) },
            label = { Text("Inicio") }
        )

        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon(Icons.Default.CalendarToday, null) },
            label = { Text("Calendario") }
        )

        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon(Icons.Default.Person, null) },
            label = { Text("Cuenta") }
        )
    }
}

/* ---------------------- PREVIEW ---------------------- */

@Preview(showSystemUi = true)
@Composable
fun HomePreview() {
    HomeScreen()
}
