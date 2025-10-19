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
import androidx.compose.material.icons.filled.Email
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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api // ¡IMPORTACIÓN NECESARIA!
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
import androidx.compose.runtime.setValue
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
// --- MAIN ACTIVITY (CORREGIDA) ---
// ----------------------------------------------------------------

// *CORRECCIÓN 1: Asegura la herencia de ComponentActivity y el uso de Bundle*
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Su tema de Compose
            // NutriliveTheme {
            val navController = rememberNavController()
            AppNavigation(navController = navController)
            // }
        }
    }
}

// ----------------------------------------------------------------
// --- DEFINICIONES DE RUTAS DE NAVEGACIÓN ---
// ----------------------------------------------------------------

object Destinations {
    const val HOME_ROUTE = "home"
    const val ACCOUNT_ROUTE = "account"
    const val ADD_ROUTE = "add"
    const val PROFILE_ROUTE = "profile_main"
}

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Destinations.HOME_ROUTE) {
        composable(Destinations.HOME_ROUTE) {
            HomeScreen(onAccountClick = { navController.navigate(Destinations.ACCOUNT_ROUTE) })
        }
        composable(Destinations.ACCOUNT_ROUTE) {
            AccountScreen(onBack = { navController.popBackStack() })
        }
    }
}

// ----------------------------------------------------------------
// --- PANTALLA DE CUENTA (IMAGEN 2) ---
// ----------------------------------------------------------------

@Composable
fun AccountScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = { AccountTopBar(onBack) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF0F0F0))
                .verticalScroll(rememberScrollState())
        ) {
            ProfileCard(
                name = "Jose",
                email = "jose.ainsley@yourdomain.com",
                onClick = { /* TODO: Navegar a editar perfil */ }
            )
            Spacer(modifier = Modifier.height(24.dp))

            // Sección 1: Rastreadores
            SettingsSection(title = null) {
                SettingsItem(
                    icon = Icons.Filled.AvTimer,
                    label = "Contador de calorías",
                    onClick = { /* TODO: Navegar a Contador de calorías */ }
                )
                SettingsItem(
                    icon = Icons.Filled.WaterDrop,
                    label = "Rastreador de agua",
                    onClick = { /* TODO: Navegar a Rastreador de agua */ }
                )
                SettingsItem(
                    icon = Icons.Filled.DirectionsWalk,
                    label = "Contador de pasos",
                    onClick = { /* TODO: Navegar a Contador de pasos */ }
                )
                SettingsItem(
                    icon = Icons.Filled.FitnessCenter,
                    label = "Rastreador de peso",
                    onClick = { /* TODO: Navegar a Rastreador de peso */ }
                )
                SettingsItem(
                    icon = Icons.Filled.Settings,
                    label = "Preferencias",
                    onClick = { /* TODO: Navegar a Preferencias */ }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Sección 2: Seguridad y Pago
            SettingsSection(title = null) {
                SettingsItem(
                    icon = Icons.Filled.Notifications,
                    label = "Notificación",
                    onClick = { /* TODO: Navegar a Notificaciones */ }
                )
                SettingsItem(
                    icon = Icons.Filled.Payments,
                    label = "Métodos de pago",
                    onClick = { /* TODO: Navegar a Métodos de pago */ }
                )
                SettingsItem(
                    icon = Icons.Filled.MonetizationOn,
                    label = "Facturación y suscripciones",
                    onClick = { /* TODO: Navegar a Facturación */ }
                )
                SettingsItem(
                    icon = Icons.Filled.Lock,
                    label = "Cuenta y seguridad",
                    onClick = { /* TODO: Navegar a Seguridad */ }
                )
                SettingsItem(
                    icon = Icons.Filled.SwapVert,
                    label = "Cuentas vinculadas", // Actualizado el label
                    onClick = { /* TODO: Navegar a Cuentas */ }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Sección 3: General y Soporte (Nuevas Opciones)
            SettingsSection(title = null) {
                SettingsItem(
                    icon = Icons.Filled.Palette,
                    label = "Apariencia de la aplicación",
                    onClick = { /* TODO: Navegar a Apariencia */ }
                )
                SettingsItem(
                    icon = Icons.Filled.QueryStats,
                    label = "Datos y análisis",
                    onClick = { /* TODO: Navegar a Datos y análisis */ }
                )
                SettingsItem(
                    icon = Icons.Filled.Support,
                    label = "Ayuda y soporte",
                    onClick = { /* TODO: Navegar a Ayuda y soporte */ }
                )
                SettingsItem(
                    icon = Icons.Filled.RateReview,
                    label = "Califícanos",
                    onClick = { /* TODO: Abrir tienda para calificar */ }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Sección 4: Cerrar Sesión
            // Implementamos el item de forma diferente para el color rojo
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                // Item de Cerrar Sesión
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(onClick = { /* TODO: Implementar lógica de cerrar sesión */ })
                        .padding(vertical = 14.dp, horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                        contentDescription = "Cerrar sesión",
                        tint = Color(0xFFD32F2F), // Rojo para la acción principal
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "Cerrar sesión",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFFD32F2F) // Rojo para la acción principal
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

// *CORRECCIÓN 3: Añadida anotación ExperimentalMaterial3Api*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountTopBar(onBack: () -> Unit) {
    TopAppBar(
        title = { Text("Cuenta", fontSize = 20.sp, fontWeight = FontWeight.Medium, color = Color.Black) },
        navigationIcon = {
            // Usamos un ícono genérico para el logo ya que R.drawable.ic_cloud_placeholder no existe
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.Filled.Notifications, // Usamos Notifications como placeholder para el logo/nube
                    contentDescription = "Logo",
                    tint = Color.Black
                )
            }
        },
        actions = {
            IconButton(onClick = { /* TODO: Abrir menú de opciones */ }) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "Opciones",
                    tint = Color.Black
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
    )
}

@Composable
fun ProfileCard(name: String, email: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
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

                // *CORRECCIÓN 2: Eliminado try-catch y reemplazado por verificación de recursos*
                val context = LocalContext.current
                val drawableId = context.resources.getIdentifier("profile_placeholder", "drawable", context.packageName)

                if (drawableId != 0) {
                    Image(
                        painter = painterResource(id = drawableId),
                        contentDescription = "Foto de perfil",
                        modifier = Modifier.size(56.dp).clip(CircleShape)
                    )
                } else {
                    // Fallback si el drawable no existe
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Foto de perfil",
                        modifier = Modifier.size(56.dp).clip(CircleShape).background(Color.LightGray, CircleShape),
                        tint = Color.White
                    )
                }
                // *FIN CORRECCIÓN 2*

                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(text = name, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Black)
                    Text(text = email, fontSize = 14.sp, color = Color.Gray)
                }
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Ir a perfil",
                tint = Color.Gray
            )
        }
    }
}

@Composable
fun SettingsSection(title: String?, content: @Composable () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
    ) {
        if (title != null) {
            Text(text = title, modifier = Modifier.padding(16.dp), fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)
            Divider(color = Color(0xFFF0F0F0), thickness = 1.dp)
        }
        content()
    }
}

@Composable
fun SettingsItem(icon: ImageVector, label: String, onClick: () -> Unit) {
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
                Icon(imageVector = icon, contentDescription = label, tint = Color.Black, modifier = Modifier.size(24.dp))
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = label, fontSize = 16.sp, color = Color.Black)
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Ir a",
                tint = Color.Gray
            )
        }
        // Condición para mostrar el divisor entre items
        if (label != "Preferencias" && label != "Cuentas vinculadas" && label != "Califícanos") {
            Divider(color = Color(0xFFF0F0F0), thickness = 1.dp, modifier = Modifier.padding(start = 56.dp))
        }
    }
}


// ----------------------------------------------------------------
// --- PANTALLA HOME (IMAGEN 1) ---
// ----------------------------------------------------------------

@Composable
fun HomeScreen(onAccountClick: () -> Unit) {
    val navController = rememberNavController() // O usa el NavController pasado por AppNavigation

    Scaffold(
        bottomBar = { NutriLiveBottomBar(navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color.White)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
        ) {
            HomeTopBar(onNotificationsClick = onAccountClick)
            Spacer(modifier = Modifier.height(8.dp))
            DateSelector()
            Spacer(modifier = Modifier.height(16.dp))
            DailyCalorieSummaryCard()
            Spacer(modifier = Modifier.height(16.dp))
            MacroNutrientCircularProgressSection()
            Spacer(modifier = Modifier.height(16.dp))
            BurnedCaloriesCard()
            Spacer(modifier = Modifier.height(16.dp))
            MealsSection()
            Spacer(modifier = Modifier.height(24.dp))
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
                tint = Color.Black,
                modifier = Modifier.size(28.dp)
            )
        }

        Text(
            text = "NutriLife",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        IconButton(onClick = onNotificationsClick) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE0E0E0).copy(alpha = 0.5f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = "Notificaciones",
                    tint = Color.Black,
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
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Día anterior", tint = Color.Gray)
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Hoy, 22 de diciembre", fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color.Black)
            Spacer(modifier = Modifier.width(8.dp))
            Icon(Icons.Filled.CalendarToday, contentDescription = "Seleccionar fecha", tint = Color.Black)
        }
        IconButton(onClick = { /* TODO: ir a día siguiente */ }) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Día siguiente", tint = Color.Gray, modifier = Modifier.scale(-1f, 1f))
        }
    }
}

@Composable
fun DailyCalorieSummaryCard() {
    val eatenKcal = 1634
    val leftKcal = 1190
    val burnedKcal = 265
    val totalGoal = eatenKcal + leftKcal
    val leftProgress = leftKcal.toFloat() / totalGoal

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F0F0)),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            CalorieInfoColumn(
                icon = Icons.Filled.Restaurant,
                label = "Eaten",
                value = eatenKcal,
                unit = "kcal",
                color = Color(0xFF1E88E5)
            )

            Box(contentAlignment = Alignment.Center, modifier = Modifier.size(120.dp)) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val strokeWidth = 12.dp.toPx()
                    drawCircle(color = Color.LightGray.copy(alpha = 0.6f), style = Stroke(width = strokeWidth))
                    drawArc(
                        color = Color(0xFF43A047),
                        startAngle = -90f,
                        sweepAngle = 360f * leftProgress,
                        useCenter = false,
                        style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
                    )
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "$leftKcal", fontSize = 32.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                    Text(text = "kcal left", fontSize = 14.sp, color = Color.Gray)
                }
            }

            CalorieInfoColumn(
                icon = Icons.Filled.FitnessCenter,
                label = "Burned",
                value = burnedKcal,
                unit = "kcal",
                color = Color(0xFFD32F2F)
            )
        }
    }
}

@Composable
fun CalorieInfoColumn(icon: ImageVector, label: String, value: Int, unit: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(icon, contentDescription = label, tint = color, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = label, fontSize = 14.sp, color = Color.Gray)
        Text(text = "$value", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        Text(text = unit, fontSize = 12.sp, color = Color.Gray)
    }
}

@Composable
fun MacroNutrientCircularProgressSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        MacroCircularProgress(label = "Carbs", consumed = 168, goal = 224, color = Color(0xFFD32F2F))
        MacroCircularProgress(label = "Protein", consumed = 83, goal = 128, color = Color(0xFFFBC02D))
        MacroCircularProgress(label = "Fat", consumed = 70, goal = 128, color = Color(0xFF1E88E5))
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
            Canvas(modifier = Modifier.fillMaxSize()) {
                val strokeWidth = 8.dp.toPx()
                drawCircle(color = Color.LightGray.copy(alpha = 0.6f), style = Stroke(width = strokeWidth))
                drawArc(
                    color = color,
                    startAngle = -90f,
                    sweepAngle = 360f * animatedProgress,
                    useCenter = false,
                    style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
                )
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "$consumed", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                Text(text = "/$goal g", fontSize = 10.sp, color = Color.Gray)
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = label, fontSize = 14.sp, color = Color.Black)
    }
}

@Composable
fun BurnedCaloriesCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F0F0)),
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
                color = Color(0xFF43A047)
            )
            BurnedActivityItem(
                icon = Icons.Filled.FitnessCenter,
                label = "Activity",
                calories = 165,
                color = Color(0xFFFBC02D)
            )
            IconButton(onClick = { /* TODO: Abrir pantalla para añadir actividad */ }) {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFE0E0E0).copy(alpha = 0.5f))
                        .border(1.dp, Color.LightGray, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Filled.Add, contentDescription = "Añadir actividad", tint = Color.Black, modifier = Modifier.size(30.dp))
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
        Text(text = label, fontSize = 14.sp, color = Color.Gray)
        Text(text = "$calories", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        Text(text = "kcal", fontSize = 12.sp, color = Color.Gray)
    }
}

@Composable
fun MealsSection() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Meals", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.padding(vertical = 8.dp))

        // Si los Drawables no existen, usarán el fallback del Icono de Restaurante.
        MealCardItem(
            mealType = "Desayuno",
            consumedKcal = 824,
            totalKcal = 768,
            iconRes = LocalContext.current.resources.getIdentifier("ic_breakfast_placeholder", "drawable", LocalContext.current.packageName),
            onClick = { /* TODO: Navegar a detalles de desayuno */ }
        )
        Spacer(modifier = Modifier.height(8.dp))
        MealCardItem(
            mealType = "Almuerzo",
            consumedKcal = 810,
            totalKcal = 768,
            iconRes = LocalContext.current.resources.getIdentifier("ic_lunch_placeholder", "drawable", LocalContext.current.packageName),
            onClick = { /* TODO: Navegar a detalles de almuerzo */ }
        )
        Spacer(modifier = Modifier.height(8.dp))
        MealCardItem(
            mealType = "Cena",
            consumedKcal = 0,
            totalKcal = 768,
            iconRes = LocalContext.current.resources.getIdentifier("ic_dinner_placeholder", "drawable", LocalContext.current.packageName),
            onClick = { /* TODO: Navegar a detalles de cena o añadir */ },
            isAddButton = true
        )
    }
}

@Composable
fun MealCardItem(
    mealType: String,
    consumedKcal: Int,
    totalKcal: Int,
    @DrawableRes iconRes: Int,
    onClick: () -> Unit,
    isAddButton: Boolean = false
) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F0F0)),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Usamos el recurso si existe (la función superior ya nos da 0 si no existe)
                if (iconRes != 0) {
                    Image(
                        painter = painterResource(id = iconRes),
                        contentDescription = mealType,
                        modifier = Modifier.size(40.dp)
                    )
                } else {
                    Icon(
                        imageVector = Icons.Filled.Restaurant,
                        contentDescription = mealType,
                        modifier = Modifier.size(40.dp),
                        tint = Color.Gray
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(text = mealType, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Black)
                    if (isAddButton) {
                        Text(text = "Añadir comida", fontSize = 14.sp, color = Color.Gray)
                    } else {
                        Text(text = "$consumedKcal/$totalKcal kcal", fontSize = 14.sp, color = Color.Gray)
                    }
                }
            }

            if (isAddButton) {
                IconButton(onClick = onClick) {
                    Icon(Icons.Filled.Add, contentDescription = "Añadir $mealType", tint = Color.Black, modifier = Modifier.size(24.dp))
                }
            } else {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Detalles de $mealType",
                    tint = Color.Black,
                    modifier = Modifier.scale(-1f, 1f)
                )
            }
        }
    }
}

// *CORRECCIÓN 3: Añadida anotación ExperimentalMaterial3Api*
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
        containerColor = Color.White,
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
                    selectedIconColor = Color(0xFF47B8C9),
                    unselectedIconColor = Color.Gray,
                    selectedTextColor = Color(0xFF47B8C9),
                    indicatorColor = Color.White
                )
            )
        }
    }
}

data class BottomNavItem(val label: String, val icon: ImageVector, val route: String)
