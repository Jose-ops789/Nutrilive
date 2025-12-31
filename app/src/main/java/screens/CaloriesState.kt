package screens



import androidx.compose.runtime.mutableIntStateOf

object CaloriesState {

    // calorías consumidas totales
    val eatenCalories = mutableIntStateOf(0)

    // calorías por comida
    val breakfastCalories = mutableIntStateOf(0)
    val lunchCalories = mutableIntStateOf(0)
    val dinnerCalories = mutableIntStateOf(0)
    val snacksCalories = mutableIntStateOf(0)
}
