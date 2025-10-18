package com.example.nutrilive

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nutrilive.ui.theme.NutriliveTheme
import kotlinx.coroutines.delay // 👈 agregado para SplashScreen

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
            SignUpScreen(onBack = { navController.popBackStack() })
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
fun SignUpScreen(onBack: () -> Unit) {
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
            onClick = { /* TODO: lógica de registro */ },
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
    NutriliveTheme {
        SignUpScreen(onBack = {})
    }
}
