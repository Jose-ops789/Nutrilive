package screens

import android.app.DatePickerDialog
import androidx.annotation.DrawableRes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nutrilive.R
import java.text.SimpleDateFormat
import java.util.*
import androidx.compose.ui.graphics.StrokeCap


import androidx.compose.runtime.mutableIntStateOf




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
    onMealClick: (String) -> Unit = {},
            onLogout: () -> Unit
) {
    var showLogoutDialog by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = Background,
        bottomBar = {
            BottomNavigationBar(
                onAccountClick = { showLogoutDialog = true }
            )
        }
    ) { padding ->
        LogoutDialog(
            show = showLogoutDialog,
            onConfirm = {
                showLogoutDialog = false
                // 👉 AQUÍ luego pondrás tu lógica real de cerrar sesión
                onLogout() // por ejemplo: auth.signOut()
            },
            onDismiss = {
                showLogoutDialog = false
            }
        )


        Column(
            modifier = Modifier
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {

            Header(onAccountClick)

            Spacer(modifier = Modifier.height(16.dp))

            CaloriesCard()

            Spacer(modifier = Modifier.height(20.dp))

            QuickStats()

            Spacer(modifier = Modifier.height(20.dp))

            WaterTracker()

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

// Calendario



@Composable
fun CalendarButton() {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    IconButton(onClick = {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                // Aquí recibes la fecha seleccionada
                val selectedDate = "$dayOfMonth/${month + 1}/$year"
                println("Fecha seleccionada: $selectedDate")
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }) {
        Icon(Icons.Default.CalendarToday, contentDescription = "Calendario")
    }
}


/* ---------------------- BOTTOM NAV ---------------------- */

@Composable
fun BottomNavigationBar( onAccountClick: () -> Unit) {
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
            icon = { CalendarButton() },
            label = { Text("Calendario") }
        )

        NavigationBarItem(
            selected = false,
            onClick = onAccountClick,
            icon = { Icon(Icons.Default.Person, null) },
            label = { Text("Cuenta") }
        )


    }
}

/* ---------------------- PREVIEW ---------------------- */

@Preview(showSystemUi = true)
@Composable
fun HomePreview() {
    HomeScreen(
        onAccountClick = {},
        onMealClick = {},
        onLogout = {}
    )
}



@Composable
fun WaterTracker() {
    var glasses by remember { mutableIntStateOf(0) }

    val maxGlasses = 8

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {if (glasses == maxGlasses) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "🎉 Objetivo diario completado",
                color = Color(0xFF2ECC71),
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )
        }


            Text(
                "Agua diaria 💧",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = TextMain
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                "$glasses / $maxGlasses vasos",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = Primary
            )

            Spacer(modifier = Modifier.height(12.dp))

            val animatedProgress by animateFloatAsState(
                targetValue = (glasses.toFloat() / maxGlasses.toFloat()).coerceIn(0f, 1f),
                animationSpec = tween(durationMillis = 600),
                label = "water-progress"
            )

            LinearProgressIndicator(
            progress = { animatedProgress },
            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp),
            color = if (glasses == maxGlasses) Color(0xFF2ECC71) else Primary,
            trackColor = PrimarySoft,
            strokeCap = StrokeCap.Round,
            )


            Spacer(modifier = Modifier.height(16.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(
                    onClick = { if (glasses > 0) glasses-- },
                    enabled = glasses > 0
                ) {
                    Text("-")
                }

                Button(
                    onClick = { if (glasses < maxGlasses) glasses++ },
                    enabled = glasses < maxGlasses
                ) {
                    Text("+")
                }
            }
        }
    }
}

/* ---------------------- QUICK STATS ---------------------- */

@Composable
fun QuickStats() {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        StatCard(
            icon = "🔥",
            label = "Calorías",
            value = CaloriesState.eatenCalories.intValue
        )

        StatCard(
            icon = "🍽️",
            label = "Comidas",
            value = 0
        )

        StatCard(
            icon = "💧",
            label = "Agua",
            value = 0
        )
    }

}

@Composable
private fun StatCard(
    icon: String,
    label: String,
    value: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(horizontal = 4.dp)
            .height(96.dp),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp,
            pressedElevation = 6.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = icon, fontSize = 22.sp)
            Text(text = label, color = TextSecondary, fontSize = 13.sp)
            Text(
                text = value.toString(),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = TextMain
            )
        }
    }
}

/* ---------------------- SNACKBAR FEEDBACK ---------------------- */


@Composable
fun LogoutDialog(
    show: Boolean,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    if (show) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = {
                Text(
                    text = "Cerrar sesión",
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text("¿Estás seguro de que deseas cerrar sesión?")
            },
            confirmButton = {
                TextButton(onClick = onConfirm) {
                    Text(
                        "Cerrar sesión",
                        color = Color.Red,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = onDismiss) {
                    Text("Cancelar")
                }
            }
        )
    }
}
