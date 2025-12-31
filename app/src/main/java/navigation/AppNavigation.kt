package navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import screens.BirthdayScreen
import screens.CaloriePlanScreen
import screens.GenderScreen
import screens.HeightScreen
import com.example.nutrilive.HomeScreen
import screens.IdealWeightScreen
import com.example.nutrilive.LoginScreen
import screens.NameScreen
import screens.SignUpScreen
import com.example.nutrilive.screens.SplashScreen
import screens.WeightScreen
import com.example.nutrilive.screens.WelcomeScreen



@Composable
fun AppNavigation(navController: NavHostController) {
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
            HomeScreen(onAccountClick = { /* abrir perfil o notificaciones */ })
        }



    }
}