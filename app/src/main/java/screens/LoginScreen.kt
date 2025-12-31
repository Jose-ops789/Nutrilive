package screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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

@Preview(
    showSystemUi = true,
    name = "Login Screen"
)
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        onBack = {
        },
        onSignUpClick = {
        },
        onLoginSuccess = {
        }
    )
}
