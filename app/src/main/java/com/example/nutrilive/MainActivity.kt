package com.example.nutrilive

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Article
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Female
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Male
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.nutrilive.ui.theme.FoodItem
import com.example.nutrilive.ui.theme.MealSelectionScreen
import com.example.nutrilive.ui.theme.NutriliveTheme
import kotlinx.coroutines.delay


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

@Composable
fun AppNavigation(navController: NavHostController) {
    // Estado global para los alimentos añadidos
    val addedFoods = remember { mutableStateOf<List<FoodItem>>(emptyList()) }

    //  cambiamos el destino inicial a "splash"
    NavHost(navController = navController, startDestination = "splash") {

        // nueva pantalla Splash
        composable("splash") {
            SplashScreen(onTimeout = {
                navController.navigate("welcome") {
                    popUpTo("splash") { inclusive = true }
                }
            })
        }

        composable("welcome") {
            WelcomeScreen(onSignUpClick = { navController.navigate("signup") },
            onLoginClick = { navController.navigate("login") }
            )
        }


        composable("signup") {
            SignUpScreen(navController, onBack = { navController.popBackStack() })
        }

        composable("name") {
            NameScreen(onContinue = { name ->

                navController.navigate("gender")
            })
        }
        composable("gender") {
            GenderScreen(
                onContinue = { gender ->
                    // Ir al siguiente paso
                    navController.navigate("birthday")
                },
                onBack = { navController.popBackStack() }
            )
        }
        composable("birthday") {
            BirthdayScreen(onContinue = { day, month, year ->
               
                navController.navigate("height")
            })
        }
        composable("height") {
            HeightScreen(
                onContinue = { heightCm ->

                    navController.navigate("weight")
                },
                onBack = { navController.popBackStack() }
            )
        }
        composable("weight") {
            WeightScreen(
                onContinue = { weightKg ->

                    navController.navigate("ideal_weight")
                },
                onBack = { navController.popBackStack() }
            )
        }
        composable("ideal_weight") {
            IdealWeightScreen(
                onContinue = { idealWeightKg ->

                    navController.navigate("calorie_plan")
                },
                onBack = { navController.popBackStack() }
            )
        }
        composable("calorie_plan") {
            CaloriePlanScreen(
                onStartPlan = {
                    // Aquí podrías ir a tu pantalla principal (por ahora vuelve atrás)
                    navController.navigate("login")
                },
                onBack = { navController.popBackStack() }
            )
        }
        composable("login") {
            LoginScreen(
                onBack = { navController.popBackStack() },
                onSignUpClick = { navController.navigate("signup") },
                onLoginSuccess = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }


        composable("home") {
            HomeScreen(
                navController = navController,
                onAccountClick = { /* abrir perfil o notificaciones */ },
                addedFoods = addedFoods.value
            )
        }
        composable("meal/{type}") { backStackEntry ->
            val type = backStackEntry.arguments?.getString("type") ?: "desayuno"

            MealSelectionScreen(
                mealType = type,
                navController = navController,
                onAddFood = { food ->
                    // ✅ Agregamos alimento seleccionado a la lista global
                    addedFoods.value = addedFoods.value + food
                }
            )
        }






        }








}

@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    // Muestra la pantalla de carga por 3 segundos
    LaunchedEffect(Unit) {
        delay(3000)
        onTimeout()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF6CE5E8)),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Logo
            Image(
                painter = painterResource(id = R.drawable.logo_nutrilife),
                contentDescription = "Logo NutriLife",
                modifier = Modifier.size(120.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Nombre
            Text(
                text = "NutriLife",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Indicador de carga
            CircularProgressIndicator(
                color = Color.Black,
                strokeWidth = 3.dp
            )
        }
    }
}

@Composable
fun WelcomeScreen(
    onSignUpClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF6CE5E8))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logo
        Image(
            painter = painterResource(id = R.drawable.logo_nutrilife),
            contentDescription = "Logo Nutrilife",
            modifier = Modifier.size(100.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Título principal
        Text(
            text = "¡Comencemos!",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Text(
            text = "Vamos a sumergirnos en NutriLife",
            color = Color.Black,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Botón Inscribirse
        Button(
            onClick = onSignUpClick,
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(50.dp)
        ) {
            Text("Inscribirse", color = Color.Black, fontWeight = FontWeight.Medium)
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Botón Iniciar sesión
        Button(
            onClick = onLoginClick,
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(50.dp)
        ) {
            Text("Iniciar sesión", color = Color.Black, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable

fun SignUpScreen(navController: NavController, onBack: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isChecked by remember { mutableStateOf(false) }
    var showPassword by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Únase a NutriLife hoy ✨",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Crea una cuenta NutriLife para hacer un seguimiento de tus comidas, mantenerte activo y alcanzar tus objetivos de salud.",
            fontSize = 14.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            leadingIcon = { Icon(Icons.Filled.Email, contentDescription = "Email") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Contraseña") },
            trailingIcon = {
                val image = if (showPassword) Icons.Filled.VisibilityOff else Icons.Filled.Visibility
                IconButton(onClick = { showPassword = !showPassword }) {
                    Icon(image, contentDescription = "Mostrar/ocultar contraseña")
                }
            },
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = isChecked, onCheckedChange = { isChecked = it })
            Text("Estoy de acuerdo con NutriLife", color = Color.Gray)
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Boton olver
        TextButton(onClick = { onBack() }) {
            Text("← Volver", color = Color(0xFF6CE5E8))
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Boton principal
        Button(
            onClick = { navController.navigate("name") },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6CE5E8)),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(50)
        ) {
            Text("Inscribirse", color = Color.White, fontWeight = FontWeight.Bold)
        }

    }
}
//Primera pantalla para como te llamas

@Composable
fun NameScreen(onContinue: (String) -> Unit) {
    var name by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Barra de progreso
            LinearProgressIndicator(
                progress = {1f / 7f},
                color = Color(0xFF6CE5E8),
                trackColor = Color.LightGray,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .padding(bottom = 8.dp)
            )

            // Texto de paso
            Text(
                text = "1/7",
                color = Color.Gray,
                modifier = Modifier.align(Alignment.End)
            )

            Spacer(modifier = Modifier.height(40.dp))

            // Título
            Text(
                text = "¿Cómo te llamas?",
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(60.dp))

            // Campo de texto
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                textStyle = LocalTextStyle.current.copy(
                    fontSize = 20.sp,
                    color = Color.Black
                ),
                placeholder = { Text("Tu nombre") },
                shape = RoundedCornerShape(8.dp)
            )
        }

        // Botón inferior
        Button(
            onClick = { if (name.isNotBlank()) onContinue(name) },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF47B8C9)),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(50)
        ) {
            Text("Continuar", color = Color.White)
        }
    }
}
@Composable
fun GenderScreen(
    onContinue: (String) -> Unit,
    onBack: () -> Unit
) {
    var selectedGender by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Column {
            // Botón de retroceso
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver", tint = Color.Black)
            }

            // Barra de progreso
            LinearProgressIndicator(
                progress = { 2f / 7f },
                color = Color(0xFF6CE5E8),
                trackColor = Color.LightGray,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .padding(bottom = 8.dp)
            )

            // Texto de paso
            Text(
                text = "2/7",
                color = Color.Gray,
                modifier = Modifier.align(Alignment.End)
            )

            Spacer(modifier = Modifier.height(40.dp))

            // Título
            Text(
                text = "¿Cuál es tu género?",
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(60.dp))

            // Opciones de género
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                GenderOption(
                    label = "Masculino",
                    icon = Icons.Default.Male,
                    isSelected = selectedGender == "Masculino",
                    onClick = { selectedGender = "Masculino" }
                )
                GenderOption(
                    label = "Femenino",
                    icon = Icons.Default.Female,
                    isSelected = selectedGender == "Femenino",
                    onClick = { selectedGender = "Femenino" }
                )
            }
        }

        // Botón inferior
        // Animación suave de color según selección
        val buttonColor by animateColorAsState(
            targetValue = if (selectedGender != null) Color(0xFF47B8C9) else Color.LightGray,
            animationSpec = tween(durationMillis = 500)
        )

        Button(
            onClick = { selectedGender?.let { onContinue(it) } },
            enabled = selectedGender != null,
            colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(50)
        ) {
            Text("Continuar", color = Color.White)
        }

    }
}

@Composable
fun GenderOption(label: String, icon: ImageVector, isSelected: Boolean, onClick: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(if (isSelected) Color(0xFF6CE5E8) else Color.Transparent)
                .border(
                    width = 2.dp,
                    color = if (isSelected) Color.Transparent else Color.Black,
                    shape = CircleShape
                )
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = label, tint = Color.Black, modifier = Modifier.size(32.dp))
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(label, color = Color.Black, fontSize = 16.sp)
    }
}
@Composable
fun BirthdayScreen(onContinue: (String, String, String) -> Unit) {
    var day by remember { mutableStateOf("") }
    var month by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Progreso
            LinearProgressIndicator(
                progress = {3f / 7f},
                color = Color(0xFF47B8C9),
                trackColor = Color.LightGray,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
            )

            Text(
                text = "3/7",
                color = Color.Gray,
                modifier = Modifier.align(Alignment.End).padding(top = 4.dp)
            )

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "¿Cuándo es tu cumpleaños?",
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(50.dp))

            // Campos Día, Mes, Año
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                BirthdayField(label = "Día", value = day, onValueChange = { day = it })
                BirthdayField(label = "Mes", value = month, onValueChange = { month = it })
                BirthdayField(label = "Año", value = year, onValueChange = { year = it })
            }
        }

        // Botón Continuar
        Button(
            onClick = {
                if (day.isNotBlank() && month.isNotBlank() && year.isNotBlank()) {
                    onContinue(day, month, year)
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF47B8C9)),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(50)
        ) {
            Text("Continuar", color = Color.White)
        }
    }
}
// fecha de cumpleaños

@Composable
fun BirthdayField(label: String, value: String, onValueChange: (String) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(label, color = Color.Gray)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            modifier = Modifier
                .width(90.dp)
                .height(56.dp),
            shape = RoundedCornerShape(8.dp)
        )
    }
}
@Composable
fun HeightScreen(
    onContinue: (Int) -> Unit,
    onBack: () -> Unit
) {
    var heightText by remember { mutableStateOf("") }
    val height = heightText.toIntOrNull()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            // Back
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Volver",
                    tint = Color.Black
                )
            }

            // Progreso 4/7
            LinearProgressIndicator(
                progress = { 4f / 7f },
                color = Color(0xFF47B8C9),
                trackColor = Color.LightGray,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
            )
            Text(
                text = "4/7",
                color = Color.Gray,
                modifier = Modifier.align(Alignment.End).padding(top = 4.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Título
            Text(
                text = "¿Cuál es tu estatura?",
                // si prefieres: "¿Cuál es tu altura?"
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Etiqueta
            Text("Estatura (en cm)", color = Color.Gray)

            Spacer(modifier = Modifier.height(8.dp))

            // Campo numérico
            OutlinedTextField(
                value = heightText,
                onValueChange = { text ->
                    if (text.all { it.isDigit() }) heightText = text
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                shape = RoundedCornerShape(8.dp),
                placeholder = { Text("Ej: 170") }
            )
        }

        // Botón Continuar
        val isValid = (height != null && height in 50..250)
        Button(
            onClick = { height?.let { onContinue(it) } },
            enabled = isValid,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isValid) Color(0xFF47B8C9) else Color.LightGray
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(50)
        ) {
            Text("Continuar", color = Color.White)
        }
    }
}
@Composable
fun WeightScreen(
    onContinue: (Int) -> Unit,
    onBack: () -> Unit
) {
    var weightText by remember { mutableStateOf("") }
    val weight = weightText.toIntOrNull()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            // Botón volver
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Volver",
                    tint = Color.Black
                )
            }

            // Barra de progreso 5/7
            LinearProgressIndicator(
                progress = { 5f / 7f },
                color = Color(0xFF47B8C9),
                trackColor = Color.LightGray,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
            )
            Text(
                text = "5/7",
                color = Color.Gray,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 4.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Título
            Text(
                text = "¿Cuál es tu peso actual?",
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Etiqueta
            Text("Peso (en kg)", color = Color.Gray)

            Spacer(modifier = Modifier.height(8.dp))

            // Campo numérico
            OutlinedTextField(
                value = weightText,
                onValueChange = { text ->
                    if (text.all { it.isDigit() }) weightText = text
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                shape = RoundedCornerShape(8.dp),
                placeholder = { Text("Ej: 70") }
            )
        }

        // Botón continuar
        val isValid = (weight != null && weight in 30..300)
        Button(
            onClick = { weight?.let { onContinue(it) } },
            enabled = isValid,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isValid) Color(0xFF47B8C9) else Color.LightGray
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(50)
        ) {
            Text("Continuar", color = Color.White)
        }
    }
}
@Composable
fun IdealWeightScreen(
    onContinue: (Int) -> Unit,
    onBack: () -> Unit
) {
    var idealWeightText by remember { mutableStateOf("") }
    val idealWeight = idealWeightText.toIntOrNull()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            // Botón volver
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Volver",
                    tint = Color.Black
                )
            }

            // Barra de progreso 6/7
            LinearProgressIndicator(
                progress = { 6f / 7f },
                color = Color(0xFF47B8C9),
                trackColor = Color.LightGray,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
            )
            Text(
                text = "6/7",
                color = Color.Gray,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 4.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Título
            Text(
                text = "¿Cuál es tu peso ideal?",
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Etiqueta
            Text("Peso (en kg)", color = Color.Gray)

            Spacer(modifier = Modifier.height(8.dp))

            // Campo numérico
            OutlinedTextField(
                value = idealWeightText,
                onValueChange = { text ->
                    if (text.all { it.isDigit() }) idealWeightText = text
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                shape = RoundedCornerShape(8.dp),
                placeholder = { Text("Ej: 65") }
            )
        }

        // Botón continuar
        val isValid = (idealWeight != null && idealWeight in 30..300)
        Button(
            onClick = { idealWeight?.let { onContinue(it) } },
            enabled = isValid,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isValid) Color(0xFF47B8C9) else Color.LightGray
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(50)
        ) {
            Text("Continuar", color = Color.White)
        }
    }
}
@Composable
fun CaloriePlanScreen(
    onStartPlan: () -> Unit,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Botón volver
            IconButton(onClick = onBack, modifier = Modifier.align(Alignment.Start)) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Volver",
                    tint = Color.Black
                )
            }

            // Progreso
            LinearProgressIndicator(
                progress = { 1f },
                color = Color(0xFF47B8C9),
                trackColor = Color.LightGray,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
            )
            Text(
                text = "7/7",
                color = Color.Gray,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 4.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Título
            Text(
                text = "¡Tu plan de calorías personalizado está listo!",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Gráfico circular
            Box(contentAlignment = Alignment.Center) {
                Canvas(modifier = Modifier.size(200.dp)) {
                    val strokeWidth = 30.dp.toPx()
                    size.minDimension / 2
                    val style = Stroke(width = strokeWidth, cap = StrokeCap.Round)

                    // Porciones
                    val segments = listOf(
                        Pair(Color(0xFFE74C3C), 0.35f), // Carbs
                        Pair(Color(0xFF3498DB), 0.45f), // Fats
                        Pair(Color(0xFFF39C12), 0.20f)  // Protein
                    )

                    var startAngle = -90f
                    for ((color, proportion) in segments) {
                        val sweepAngle = 360 * proportion
                        drawArc(
                            color = color,
                            startAngle = startAngle,
                            sweepAngle = sweepAngle,
                            useCenter = false,
                            style = style
                        )
                        startAngle += sweepAngle
                    }
                }

                // Texto central
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("2560", fontSize = 36.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                    Text("kcal", color = Color.Gray)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Leyenda
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                LegendItem(Color(0xFFE74C3C), "Carbs")
                LegendItem(Color(0xFF3498DB), "Fats")
                LegendItem(Color(0xFFF39C12), "Protein")
            }
        }

        // Botón inferior
        Button(
            onClick = onStartPlan,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF47B8C9)),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(50)
        ) {
            Text("Comienza tu plan ahora", color = Color.White)
        }
    }
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


@Composable
fun LoginScreen(
    onBack: () -> Unit,
    onSignUpClick: () -> Unit,
    onLoginSuccess: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rememberMe by remember { mutableStateOf(false) }
    var showPassword by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(horizontalAlignment = Alignment.Start) {

            // Botón volver
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Volver",
                    tint = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Título
            Text(
                text = "¡Bienvenido de nuevo! 👋",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = "Inicia sesión para continuar tu viaje hacia una vida más saludable.",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Email
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                leadingIcon = { Icon(Icons.Filled.Email, contentDescription = "Email") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Contraseña
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Contraseña") },
                trailingIcon = {
                    val image = if (showPassword) Icons.Filled.VisibilityOff else Icons.Filled.Visibility
                    IconButton(onClick = { showPassword = !showPassword }) {
                        Icon(image, contentDescription = "Mostrar/ocultar contraseña")
                    }
                },
                visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Recordar y contraseña olvidada
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = rememberMe, onCheckedChange = { rememberMe = it })
                    Text("Acuérdate de mí", color = Color.Gray)
                }
                TextButton(onClick = { /* acción recuperar contraseña */ }) {
                    Text("¿Has olvidado tu contraseña?", color = Color(0xFF47B8C9))
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Registrarse
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("¿No tienes una cuenta?", color = Color.Gray)
                Spacer(modifier = Modifier.width(4.dp))
                TextButton(onClick = onSignUpClick) {
                    Text("Inscribirse", color = Color(0xFF47B8C9))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))



        }

        // Botón principal
        Button(
            onClick = {
                // Validación simple de ejemplo
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    onLoginSuccess()
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF47B8C9)),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(50)
        ) {
            Text("Iniciar sesión", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}


@Composable
fun HomeScreen(
    navController: NavController,
    onAccountClick: () -> Unit = {},
    addedFoods: List<FoodItem> = emptyList()
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

                    val totalCalories = addedFoods.sumOf { it.calories }

                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("Eaten", color = Color.Gray)
                            Text("$totalCalories kcal", fontWeight = FontWeight.Bold)
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

            // Secciones de comida con navegación
            FoodSection("Desayuno", R.drawable.breakfast, 768) {
                navController.navigate("meal/desayuno")
            }
            FoodSection("Almuerzo", R.drawable.lunch, 768) {
                navController.navigate("meal/almuerzo")
            }
            FoodSection("Cena", R.drawable.dinner, 768) {
                navController.navigate("meal/cena")
            }
            FoodSection("Aperitivos", R.drawable.snack, 256) {
                navController.navigate("meal/snack")
            }

            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}

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

@Composable
fun ActivityCard(icon: String, label: String, kcal: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(icon, fontSize = 26.sp)
        Text(label, color = Color.Gray)
        Text(kcal, fontWeight = FontWeight.Bold)
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









@androidx.compose.ui.tooling.preview.Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewSignUpScreen() {
    val fakeNavController = rememberNavController() // se usa solo para preview
    NutriliveTheme {
        SignUpScreen(navController = fakeNavController, onBack = {})
    }
}

