package com.example.nutrilive

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
    // 👇 cambiamos el destino inicial a "splash"
    NavHost(navController = navController, startDestination = "splash") {

        // 👇 nueva pantalla Splash
        composable("splash") {
            SplashScreen(onTimeout = {
                navController.navigate("welcome") {
                    popUpTo("splash") { inclusive = true }
                }
            })
        }

        composable("welcome") {
            WelcomeScreen(onSignUpClick = { navController.navigate("signup") })
        }

        composable("signup") {
            SignUpScreen(navController, onBack = { navController.popBackStack() })
        }

        composable("name") {
            NameScreen(onContinue = { name ->
                // Aquí podrías guardar el nombre o ir al siguiente paso
                navController.navigate("gender") // temporal, solo para probar
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
                // Aquí luego navegaremos al siguiente paso
                navController.navigate("height") // Temporal para probar
            })
        }
        composable("height") {
            HeightScreen(
                onContinue = { heightCm ->
                    // Aquí iría el siguiente paso (p.ej. peso)
                    navController.navigate("weight") // temporal para probar
                },
                onBack = { navController.popBackStack() }
            )
        }
        composable("weight") {
            WeightScreen(
                onContinue = { weightKg ->
                    // Próximamente irá al paso 6 (por ahora temporal)
                    navController.popBackStack()
                },
                onBack = { navController.popBackStack() }
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
fun WelcomeScreen(onSignUpClick: () -> Unit) {
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
        Text("¡Comencemos!", fontSize = 26.sp, fontWeight = FontWeight.Bold)
        Text("Vamos a sumergirnos en NutriLife", color = Color.Black)

        Spacer(modifier = Modifier.height(40.dp))

        // Botón Inscribirse
        Button(
            onClick = { onSignUpClick() },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            shape = RoundedCornerShape(20.dp)
        ) {
            Text("Inscribirse", color = Color.Black)
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Boton niciar sesion
        Button(
            onClick = { /* Aquí podrías abrir otra pantalla */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            shape = RoundedCornerShape(20.dp)
        ) {
            Text("Iniciar sesión", color = Color.Black)
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






@androidx.compose.ui.tooling.preview.Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewWelcomeScreen() {
    NutriliveTheme {
        WelcomeScreen(onSignUpClick = {})
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

