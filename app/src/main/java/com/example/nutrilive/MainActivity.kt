package com.example.nutrilive

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nutrilive.ui.theme.NutriliveTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NutriliveTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF6CE5E8)
                ) {
                    WelcomeScreen()
                }
            }
        }
    }
}

@Composable
fun WelcomeScreen() {
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
            contentDescription = "Logo NutriLife",
            modifier = Modifier.size(120.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Título
        Text(
            text = "¡Comencemos!",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        // Subtítulo
        Text(
            text = "Vamos a sumergirnos en NutriLife",
            fontSize = 16.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Botones
        Button(
            onClick = { /* TODO: iniciar con Google */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .width(250.dp)
                .height(50.dp)
        ) {
            Text(text = "Continuar cuenta Google", color = Color.Black)
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { /* TODO: registrar */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .width(250.dp)
                .height(50.dp)
        ) {
            Text(text = "Inscribirse", color = Color.Black)
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { /* TODO: iniciar sesión */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .width(250.dp)
                .height(50.dp)
        ) {
            Text(text = "Iniciar sesión", color = Color.Black)
        }
    }
}
@Composable
@androidx.compose.ui.tooling.preview.Preview(showBackground = true, showSystemUi = true)
fun PreviewWelcomeScreen() {
    com.example.nutrilive.ui.theme.NutriliveTheme {
        WelcomeScreen()
    }
}
