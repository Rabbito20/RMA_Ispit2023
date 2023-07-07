package rs.raf.projekat1.rmanutritiont

import rs.raf.projekat1.rmanutritiont.data.api.MealRepository

interface AppContainer {
    val mealsRepository: MealRepository
//    val authRepository: AuthRepository
}

//class AppContainerImpl(/*appContext: Context*/): AppContainer {
//    private val sdk =
//    override val mealsRepository: MealRepository =
//}