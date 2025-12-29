// ----------------------------------------------------------------
// --- IMPORTACIONES NECESARIAS (Revisa que Android Studio las tenga todas) ---
// ----------------------------------------------------------------

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Article
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.AvTimer
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.DirectionsWalk
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.QueryStats
import androidx.compose.material.icons.filled.RateReview
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Support
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.ui.platform.LocalContext

// ----------------------------------------------------------------
// --- DESIGN SYSTEM: COLORES ---
// ----------------------------------------------------------------

// ==========================================
// 1. PALETA BASE (Los colores "crudos")
// ==========================================
val PrimaryColor      = Color(0xFF47B8C9) // Cian / Turquesa
val SecondaryColor    = Color(0xFFFBC02D) // Amarillo Ámbar
val BackgroundColor   = Color(0xFFF0F0F0) // Gris muy claro
val SurfaceColor      = Color.White
val ErrorColor        = Color(0xFFD32F2F) // Rojo Alerta
val SuccessColor      = Color(0xFF43A047) // Verde Éxito
val BlueAccent        = Color(0xFF1E88E5) // Azul Intenso

// 2. TIPOGRAFÍA
val TextPrimary       = Color.Black
val TextSecondary     = Color.Gray

// 3. COLORES SEMÁNTICOS (Nutrición)

// --- Macros ---
val MacroCarbColor    = ErrorColor      // Rojo (Atención a los carbos)
val MacroProteinColor = SecondaryColor  // Amarillo/Naranja (Energía)
val MacroFatColor     = BlueAccent      // Azul

// --- Calorías ---
val CalorieBurnColor  = ErrorColor      // Quema (Rojo/Calor)
val CalorieEatenColor = MacroFatColor   // Ingesta (Mismo que grasas/azul)
val CalorieLeftColor  = SuccessColor    // Restante (Verde/Meta)
// --- MAIN ACTIVITY Y NAVEGACIÓN ---

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            AppNavigationRefactored(navController = navController)
        }
    }
}

// Definiciones de Rutas (Resolviendo 'Unresolved reference 'Destinations' .442, .443, etc.)
object Destinations {
    const val HOME_ROUTE = "home"
    const val ACCOUNT_ROUTE = "cuenta"
    const val ADD_ROUTE = "agregar"
    const val PROFILE_ROUTE = "registro"
}

@Composable
fun AppNavigationRefactored(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Destinations.HOME_ROUTE) {
        composable(Destinations.HOME_ROUTE) {
            HomeScreenRefactored(onAccountClick = { navController.navigate(Destinations.ACCOUNT_ROUTE) })
        }
        composable(Destinations.ACCOUNT_ROUTE) {
            AccountScreenRefactored(onBack = { navController.popBackStack() })
        }
        composable(Destinations.ADD_ROUTE) { SimplePlaceholderScreen(Destinations.ADD_ROUTE, "Añadir", navController) }
        composable(Destinations.PROFILE_ROUTE) { SimplePlaceholderScreen(Destinations.PROFILE_ROUTE, "Registro", navController) }
    }
}

// Pantalla de Relleno Simple (Para las rutas no implementadas)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimplePlaceholderScreen(route: String, title: String, navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        },
        bottomBar = { NutriLiveBottomBar(navController) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(BackgroundColor),
            contentAlignment = Alignment.Center
        ) {
            Text("Pantalla de $title en desarrollo...", fontSize = 20.sp, color = TextSecondary)
        }
    }
}

// --- BARRA DE NAVEGACIÓN INFERIOR (Resolviendo 'Unresolved reference

data class BottomNavItem(val label: String, val icon: ImageVector, val route: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NutriLiveBottomBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem("Home", Icons.Filled.Home, Destinations.HOME_ROUTE),
        BottomNavItem("Registro", Icons.AutoMirrored.Filled.Article, Destinations.PROFILE_ROUTE),
        BottomNavItem("Añadir", Icons.Filled.AddCircleOutline, Destinations.ADD_ROUTE),
        BottomNavItem("Perfil", Icons.Filled.Person, Destinations.ACCOUNT_ROUTE),
    )

    val currentRoute = navController.currentDestination?.route ?: Destinations.HOME_ROUTE

    NavigationBar(
        containerColor = SurfaceColor,
        modifier = Modifier.clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
    ) {
        items.forEach { item ->
            val isSelected = currentRoute == item.route
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label, modifier = Modifier.size(24.dp)) },
                label = { Text(item.label) },
                selected = isSelected,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                alwaysShowLabel = true,
                colors = androidx.compose.material3.NavigationBarItemDefaults.colors(
                    selectedIconColor = PrimaryColor,
                    unselectedIconColor = TextSecondary,
                    selectedTextColor = PrimaryColor,
                    indicatorColor = SurfaceColor
                )
            )
        }
    }
}

// --- PANTALLA DE CUENTA (AccountScreenRefactored)
// Resolviendo 'Unresolved reference 'AccountTopBar' .153
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountTopBar(onBack: () -> Unit) {
    TopAppBar(
        title = { Text("Cuenta", fontSize = 20.sp, fontWeight = FontWeight.Medium, color = TextPrimary) },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.Filled.Notifications, // Placeholder
                    contentDescription = "Logo",
                    tint = TextPrimary
                )
            }
        },
        actions = {
            IconButton(onClick = { /* TODO: Abrir menú de opciones */ }) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "Opciones",
                    tint = TextPrimary
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = SurfaceColor)
    )
}

// Resolviendo 'Unresolved reference 'ProfileCard' .162
@Composable
fun ProfileCard(name: String, email: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {

                val context = LocalContext.current
                val drawableId = context.resources.getIdentifier("profile_placeholder", "drawable", context.packageName)

                if (drawableId != 0) {
                    Image(
                        painter = painterResource(id = drawableId),
                        contentDescription = "Foto de perfil",
                        modifier = Modifier.size(56.dp).clip(CircleShape)
                    )
                } else {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Foto de perfil",
                        modifier = Modifier.size(56.dp).clip(CircleShape).background(BackgroundColor, CircleShape),
                        tint = SurfaceColor
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(text = name, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = TextPrimary)
                    Text(text = email, fontSize = 14.sp, color = TextSecondary)
                }
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Ir a perfil",
                tint = TextSecondary
            )
        }
    }
}

// Componente para agrupar items en una tarjeta
@Composable
fun CardSection(content: @Composable () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            content()
        }
    }
}

// Item de Configuración
@Composable
fun SettingsItem(
    icon: ImageVector,
    label: String,
    onClick: () -> Unit,
    tint: Color = TextPrimary,
    showDivider: Boolean = true
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onClick)
                .padding(vertical = 14.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = icon, contentDescription = label, tint = tint, modifier = Modifier.size(24.dp))
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = label, fontSize = 16.sp, color = tint, fontWeight = FontWeight.Medium)
            }
            if (tint != ErrorColor) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = null,
                    tint = TextSecondary
                )
            }
        }
        if (showDivider) {
            Divider(color = BackgroundColor, thickness = 1.dp, modifier = Modifier.padding(start = 56.dp))
        }
    }
}

@Composable
fun AccountScreenRefactored(onBack: () -> Unit) {
    Scaffold(
        topBar = { AccountTopBar(onBack) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(BackgroundColor)
                .verticalScroll(rememberScrollState())
        ) {
            ProfileCard(
                name = "Jose",
                email = "jose.mamani@gmail.com",
                onClick = { /* TODO */ }
            )
            Spacer(modifier = Modifier.height(24.dp))

            // Sección 1: Rastreadores
            CardSection(content = {
                SettingsItem(Icons.Filled.AvTimer, "Contador de calorías", {})
                SettingsItem(Icons.Filled.WaterDrop, "Rastreador de agua", {})
                SettingsItem(Icons.Filled.DirectionsWalk, "Contador de pasos", {})
                SettingsItem(Icons.Filled.FitnessCenter, "Rastreador de peso", {})
                SettingsItem(Icons.Filled.Settings, "Preferencias", {}, showDivider = false)
            })

            Spacer(modifier = Modifier.height(24.dp))

            // Sección 2: Seguridad y Pago
            CardSection(content = {
                SettingsItem(Icons.Filled.Notifications, "Notificación", {})
                SettingsItem(Icons.Filled.Payments, "Métodos de pago", {})
                SettingsItem(Icons.Filled.MonetizationOn, "Facturación y suscripciones", {})
                SettingsItem(Icons.Filled.Lock, "Cuenta y seguridad", {})
                SettingsItem(Icons.Filled.SwapVert, "Cuentas vinculadas", {}, showDivider = false)
            })

            Spacer(modifier = Modifier.height(24.dp))

            // Sección 3: General y Soporte
            CardSection(content = {
                SettingsItem(Icons.Filled.Palette, "Apariencia de la aplicación", {})
                SettingsItem(Icons.Filled.QueryStats, "Datos y análisis", {})
                SettingsItem(Icons.Filled.Support, "Ayuda y soporte", {})
                SettingsItem(Icons.Filled.RateReview, "Califícanos", {}, showDivider = false)
            })

            Spacer(modifier = Modifier.height(24.dp))

            // Sección 4: Cerrar Sesión
            CardSection(content = {
                SettingsItem(
                    icon = Icons.AutoMirrored.Filled.ExitToApp,
                    label = "Cerrar sesión",
                    onClick = { /* TODO: Cerrar sesión */ },
                    tint = ErrorColor,
                    showDivider = false
                )
            })

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

// --- PANTALLA HOME (HomeScreenRefactored) ---

@Composable
fun HomeScreenRefactored(onAccountClick: () -> Unit) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { NutriLiveBottomBar(navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(SurfaceColor)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
        ) {
            HomeTopBar(onNotificationsClick = onAccountClick) // Resolviendo 'Unresolved reference 'HomeTopBar' .422
            Spacer(modifier = Modifier.height(8.dp))
            DateSelector() // Resolviendo 'Unresolved reference 'DateSelector' .424
            Spacer(modifier = Modifier.height(16.dp))
            DailyCalorieSummaryCardRefactored()
            Spacer(modifier = Modifier.height(16.dp))
            MacroNutrientCircularProgressSection() // Resolviendo 'Unresolved reference 'MacroNutrientCircularProgressSection' .429
            Spacer(modifier = Modifier.height(16.dp))
            BurnedCaloriesCard() // Resolviendo 'Unresolved reference 'BurnedCaloriesCard' .431
            Spacer(modifier = Modifier.height(16.dp))
            MealsSectionRefactored()
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

// Componente Circular Progress reutilizable
@Composable
fun CustomCircularProgress(
    progress: Float,
    strokeWidth: androidx.compose.ui.unit.Dp,
    color: Color,
    backgroundColor: Color
) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val stroke = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
        drawCircle(color = backgroundColor, style = stroke)
        drawArc(
            color = color,
            startAngle = -90f,
            sweepAngle = 360f * progress,
            useCenter = false,
            style = stroke
        )
    }
}

@Composable
fun DailyCalorieSummaryCardRefactored() {
    val eatenKcal = 1634
    val goalKcal = 2824
    val burnedKcal = 265
    val netKcal = eatenKcal - burnedKcal
    val leftKcal = (goalKcal - netKcal).coerceAtLeast(0)
    val progress = netKcal.toFloat() / goalKcal.toFloat()
    val animatedProgress by animateFloatAsState(
        targetValue = progress.coerceIn(0f, 1f),
        animationSpec = tween(durationMillis = 800)
    )

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = BackgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            CalorieInfoColumn( // Resolviendo 'Unresolved reference 'CalorieInfoColumn' .285
                icon = Icons.Filled.Restaurant,
                label = "Comida",
                value = eatenKcal,
                unit = "calorias",
                color = CalorieEatenColor
            )

            Box(contentAlignment = Alignment.Center, modifier = Modifier.size(120.dp)) {
                CustomCircularProgress(
                    progress = animatedProgress,
                    strokeWidth = 12.dp,
                    color = CalorieLeftColor,
                    backgroundColor = TextSecondary.copy(alpha = 0.2f)
                )
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "$leftKcal", fontSize = 32.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                    Text(text = "calorias restantes", fontSize = 14.sp, color = TextSecondary)
                }
            }

            CalorieInfoColumn(
                icon = Icons.Filled.FitnessCenter,
                label = "Quemado",
                value = burnedKcal,
                unit = "kcal",
                color = CalorieBurnColor
            )
        }
    }
}

@Composable
fun MacroCircularProgress(label: String, consumed: Int, goal: Int, color: Color) {
    val progress = consumed.toFloat() / goal.toFloat()
    val animatedProgress by animateFloatAsState(
        targetValue = progress.coerceAtMost(1f),
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    )

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.size(90.dp)) {
            CustomCircularProgress(
                progress = animatedProgress,
                strokeWidth = 8.dp,
                color = color,
                backgroundColor = TextSecondary.copy(alpha = 0.2f)
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "$consumed", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                Text(text = "/$goal g", fontSize = 10.sp, color = TextSecondary)
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = label, fontSize = 14.sp, color = TextPrimary)
    }
}

data class MealData(
    val mealType: String,
    val consumedKcal: Int,
    val totalKcal: Int,
    val isAddButton: Boolean = false
)

@Composable
fun MealsSectionRefactored() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Comidas",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        val meals = listOf(
            MealData("Desayuno", 824, 768),
            MealData("Almuerzo", 810, 768),
            MealData("Cena", 0, 768, isAddButton = true)
        )

        meals.forEachIndexed { index, meal ->
            MealCardItemRefactored(meal)
            if (index < meals.size - 1) {
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun MealCardItemRefactored(meal: MealData, onClick: () -> Unit = {}) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = BackgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.Restaurant,
                    contentDescription = meal.mealType,
                    modifier = Modifier.size(40.dp),
                    tint = TextSecondary
                )

                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(text = meal.mealType, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = TextPrimary)
                    if (meal.isAddButton) {
                        Text(text = "Añadir comida", fontSize = 14.sp, color = TextSecondary)
                    } else {
                        val color = if (meal.consumedKcal > meal.totalKcal) ErrorColor else TextSecondary
                        Text(
                            text = "${meal.consumedKcal}/${meal.totalKcal} kcal",
                            fontSize = 14.sp,
                            color = color
                        )
                    }
                }
            }

            if (meal.isAddButton) {
                Icon(Icons.Filled.Add, contentDescription = "Añadir ${meal.mealType}", tint = TextPrimary, modifier = Modifier.size(24.dp))
            } else {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "Detalles de ${meal.mealType}",
                    tint = TextSecondary
                )
            }
        }
    }
}

@Composable
fun HomeTopBar(onNotificationsClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = { /* TODO: Abrir menú lateral */ }) {
            Icon(
                imageVector = Icons.Filled.AddCircleOutline,
                contentDescription = "Menu",
                tint = TextPrimary,
                modifier = Modifier.size(28.dp)
            )
        }

        Text(
            text = "NutriLife",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
        )

        IconButton(onClick = onNotificationsClick) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(BackgroundColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = "Notificaciones",
                    tint = TextPrimary,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@Composable
fun DateSelector() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = { /* TODO: ir a día anterior */ }) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Día anterior", tint = TextSecondary)
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Hoy, 22 de diciembre", fontSize = 16.sp, fontWeight = FontWeight.Medium, color = TextPrimary)
            Spacer(modifier = Modifier.width(8.dp))
            Icon(Icons.Filled.CalendarToday, contentDescription = "Seleccionar fecha", tint = TextPrimary)
        }
        IconButton(onClick = { /* TODO: ir a día siguiente */ }) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Día siguiente", tint = TextSecondary, modifier = Modifier.scale(-1f, 1f))
        }
    }
}

@Composable
fun CalorieInfoColumn(icon: ImageVector, label: String, value: Int, unit: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(icon, contentDescription = label, tint = color, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = label, fontSize = 14.sp, color = TextSecondary)
        Text(text = "$value", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
        Text(text = unit, fontSize = 12.sp, color = TextSecondary)
    }
}

@Composable
fun MacroNutrientCircularProgressSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        MacroCircularProgress(label = "Carbohidratos", consumed = 168, goal = 224, color = MacroCarbColor)
        MacroCircularProgress(label = "Proteinas", consumed = 83, goal = 128, color = MacroProteinColor)
        MacroCircularProgress(label = "Fat", consumed = 70, goal = 128, color = MacroFatColor)
    }
}

@Composable
fun BurnedCaloriesCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = BackgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BurnedActivityItem(
                icon = Icons.Filled.DirectionsWalk,
                label = "Walking",
                calories = 100,
                color = CalorieLeftColor
            )
            BurnedActivityItem(
                icon = Icons.Filled.FitnessCenter,
                label = "Activity",
                calories = 165,
                color = MacroProteinColor
            )
            IconButton(onClick = { /* TODO: Abrir pantalla para añadir actividad */ }) {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(SurfaceColor)
                        .border(1.dp, TextSecondary.copy(alpha = 0.5f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Filled.Add, contentDescription = "Añadir actividad", tint = TextPrimary, modifier = Modifier.size(30.dp))
                }
            }
        }
    }
}

@Composable
fun BurnedActivityItem(icon: ImageVector, label: String, calories: Int, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(icon, contentDescription = label, tint = color, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = label, fontSize = 14.sp, color = TextSecondary)
        Text(text = "$calories", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
        Text(text = "kcal", fontSize = 12.sp, color = TextSecondary)
    }
}