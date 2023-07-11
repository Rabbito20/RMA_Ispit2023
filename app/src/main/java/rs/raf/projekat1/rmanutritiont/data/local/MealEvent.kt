package rs.raf.projekat1.rmanutritiont.data.local

import rs.raf.projekat1.rmanutritiont.data.model.MealFromApi

sealed interface MealEvent {
//    object SaveMeal : MealEvent
    data class SaveMeal(val meal: LocalFavoriteMeal) : MealEvent
    data class DeleteMeal(val meal: LocalFavoriteMeal) : MealEvent

    data class SetMeal(val meal: MealFromApi) : MealEvent

//    data class SetMealArea(val mealArea: String) : MealEvent
//    data class SetMealCategory(val mealCategory: String) : MealEvent
//    data class SetMealInstruct(val mealInstruct: String) : MealEvent
//    data class SetMealThumb(val mealThumb: String) : MealEvent
//    data class SetMealTags(val mealThumb: String?) : MealEvent

//    object ShowDialog : MealEvent
//    object HideDialog : MealEvent

//    data class SortMeals(val sortType: SortType) : MealEvent
}

