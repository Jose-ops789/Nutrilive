package screens

import androidx.compose.runtime.mutableIntStateOf

object CaloriesState {

    // 🔥 Total diario
    val totalCalories = mutableIntStateOf(2560)

    // 🔥 Consumidas
    val eatenCalories = mutableIntStateOf(0)

    // 🔥 Restantes
    val leftCalories = mutableIntStateOf(2560)

    // Por comida
    val breakfastCalories = mutableIntStateOf(0)
    val lunchCalories = mutableIntStateOf(0)
    val dinnerCalories = mutableIntStateOf(0)
    val snacksCalories = mutableIntStateOf(0)

    fun recalculate() {
        eatenCalories.intValue =
            breakfastCalories.intValue +
                    lunchCalories.intValue +
                    dinnerCalories.intValue +
                    snacksCalories.intValue

        leftCalories.intValue =
            totalCalories.intValue - eatenCalories.intValue
    }
}
